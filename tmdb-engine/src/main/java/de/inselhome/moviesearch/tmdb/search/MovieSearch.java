package de.inselhome.moviesearch.tmdb.search;

import java.io.IOException;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.inselhome.moviesearch.api.domain.MoviePreview;
import de.inselhome.moviesearch.tmdb.constants.APIConstants;
import de.inselhome.moviesearch.tmdb.domain.MoviePreviewImpl;
import de.inselhome.moviesearch.tmdb.io.JSONHttp;
import de.inselhome.moviesearch.tmdb.objects.MovieSearchResult;
import de.inselhome.moviesearch.tmdb.objects.MovieSearchResultPage;
import de.inselhome.moviesearch.tmdb.util.ImageUtils;

public class MovieSearch {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieSearch.class);

    private String apiKey;
    private String baseUrl;
    private String query;

    public MovieSearch(final String apiKey, final String baseUrl, final String query) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.query = query;
    }

    public int getNumberOfPages() {
        MovieSearchResultPage resultPage = getResultPage(1);
        return resultPage.getTotal_pages();
    }

    public List<MoviePreview> getResult() {
        List<MoviePreview> moviePreviews = new ArrayList<>();
        int pages = getNumberOfPages();

        for (int page = 1; page <= pages; page++) {
            List<MovieSearchResult> pageResults = getSearchResult(page);
            List<MoviePreview> moviePagePreviews = convertToMoviePreview(pageResults);

            if (!moviePagePreviews.isEmpty()) {
                moviePreviews.addAll(moviePagePreviews);
            }
        }

        return moviePreviews;
    }

    private List<MoviePreview> convertToMoviePreview(final List<MovieSearchResult> searchResults) {
        List<MoviePreview> previews = new ArrayList<>(searchResults.size());

        previews.addAll(searchResults.stream().map(searchResult -> new MoviePreviewImpl(String.valueOf(searchResult.getId()), searchResult.getTitle(),
                createCover(searchResult.getPoster_path()))).collect(Collectors.toList()));

        return previews;
    }

    private String createCover(final String posterPath) {
        String rawUrl = String.format("%s%s", APIConstants.COVER_BASE_URL, posterPath);
        LOGGER.debug("Download cover from url: {}", rawUrl);

        try {
            URL url = new URL(rawUrl);
            return ImageUtils.covertToBase64(url);
        } catch (IOException e) {
            LOGGER.warn("Unable to download and convert movie from '{}'", rawUrl);
        }

        return null;
    }

    private List<MovieSearchResult> getSearchResult(final int page) {
        MovieSearchResultPage resultPage = getResultPage(page);
        return resultPage.getResults();
    }

    private MovieSearchResultPage getResultPage(final int page) {
        try {
            URL pageUrl = new MovieSearchURLFactory(apiKey, baseUrl, query, page).build();
            JSONObject response = JSONHttp.read(pageUrl);

            return MovieSearchResultPage.create(response);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}

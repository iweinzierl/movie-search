package de.inselhome.moviesearch.tmdb.search;

import java.io.IOException;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.inselhome.moviesearch.tmdb.util.ListUtil;
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

    public List<MoviePreview> getResult(Integer maxResults) {
        List<MoviePreview> moviePreviews = new ArrayList<>();
        int pages = getNumberOfPages();

        for (int page = 1; page <= pages; page++) {
            LOGGER.debug("Process page {} of {}", page, pages);
            List<MovieSearchResult> pageResults = getSearchResult(page);
            List<MoviePreview> moviePagePreviews = convertToMoviePreview(pageResults);

            if (!moviePagePreviews.isEmpty() && maxResults == null) {
                moviePreviews.addAll(moviePagePreviews);
            } else if (!moviePagePreviews.isEmpty()) {
                moviePreviews = ListUtil.add(moviePagePreviews, moviePreviews, maxResults);

                if (moviePreviews.size() >= maxResults) {
                    return moviePreviews;
                }
            }
        }

        return moviePreviews;
    }

    private List<MoviePreview> convertToMoviePreview(final List<MovieSearchResult> searchResults) {
        List<MoviePreview> previews = new ArrayList<>(searchResults.size());

        previews.addAll(searchResults.stream().map(searchResult -> new MoviePreviewImpl(
                String.valueOf(searchResult.getId()),
                searchResult.getTitle(),
                createCoverUri(searchResult.getPoster_path()),
                searchResult.getDescription()))
                .collect(Collectors.toList()));

        return previews;
    }

    private String createCoverUri(final String posterPath) {
        return String.format("%s%s", APIConstants.COVER_BASE_URL, posterPath);
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

package de.inselhome.moviesearch.tmdb.get;

import java.io.IOException;

import java.net.URL;

import java.time.LocalDate;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.inselhome.moviesearch.api.domain.Movie;
import de.inselhome.moviesearch.tmdb.constants.APIConstants;
import de.inselhome.moviesearch.tmdb.domain.MovieImpl;
import de.inselhome.moviesearch.tmdb.io.JSONHttp;
import de.inselhome.moviesearch.tmdb.objects.GenreResult;
import de.inselhome.moviesearch.tmdb.objects.MovieResult;
import de.inselhome.moviesearch.tmdb.util.ImageUtils;

public class MovieGet {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieGet.class);

    private final String apiKey;
    private final String baseUrl;
    private final int movieId;

    public MovieGet(final String apiKey, final String baseUrl, final int movieId) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.movieId = movieId;
    }

    public Movie getMovie() {
        MovieResult movieResult = getMovieResult();
        return convertToMovie(movieResult);
    }

    private MovieResult getMovieResult() {

        try {
            URL url = new MovieGetURLFactory(apiKey, baseUrl, movieId).build();

            LOGGER.debug("Read movie details from '{}'", url);

            JSONObject response = JSONHttp.read(url);

            return MovieResult.create(response);

        } catch (JSONException | IOException e) {
            LOGGER.error("Unable to load movie: {}", e.getMessage(), e);
        }

        return null;
    }

    private Movie convertToMovie(final MovieResult movieResult) {

        Movie movie = new MovieImpl(movieResult.getTitle());
        movie.setOriginalTitle(movieResult.getOriginal_title());
        movie.setDescription(movieResult.getOverview());
        movie.setGenres(convertToGenre(movieResult.getGenres()));
        movie.setCover(createCover(movieResult.getPoster_path()));
        movie.setLength(movieResult.getRuntime());
        movie.setPublishDate(LocalDate.now());
        // movie.setPublishDate(movieResult.getRelease_date());

        return movie;
    }

    private Set<String> convertToGenre(final List<GenreResult> genreResults) {
        return genreResults.stream().map(GenreResult::getName).collect(Collectors.toSet());
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
}
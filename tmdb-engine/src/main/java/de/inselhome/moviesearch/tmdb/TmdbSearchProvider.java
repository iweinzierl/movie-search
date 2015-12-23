package de.inselhome.moviesearch.tmdb;

import de.inselhome.moviesearch.api.SearchProvider;
import de.inselhome.moviesearch.api.domain.Movie;
import de.inselhome.moviesearch.api.domain.MoviePreview;
import de.inselhome.moviesearch.tmdb.constants.APIConstants;
import de.inselhome.moviesearch.tmdb.constants.ConfigurationConstants;
import de.inselhome.moviesearch.tmdb.get.MovieGet;
import de.inselhome.moviesearch.tmdb.search.MovieSearch;

import java.util.List;
import java.util.Map;

public class TmdbSearchProvider implements SearchProvider {

    private String apiKey;

    public TmdbSearchProvider(final String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public void setProperties(final Map<String, String> properties) {
        apiKey = properties.get(ConfigurationConstants.API_KEY);
    }

    @Override
    public List<MoviePreview> search(final String searchString) {
        return search(searchString, null);
    }

    @Override
    public List<MoviePreview> search(final String searchString, final Integer maxResults) {
        MovieSearch movieSearch = new MovieSearch(apiKey, APIConstants.API_ENDPOINT_URL, searchString);
        return movieSearch.getResult(maxResults);
    }

    @Override
    public Movie get(String movieId) {
        MovieGet getMovie = new MovieGet(apiKey, APIConstants.API_ENDPOINT_URL,
                Integer.valueOf(movieId));
        return getMovie.getMovie();
    }

    @Override
    public Movie get(final MoviePreview moviePreview) {
        MovieGet getMovie = new MovieGet(apiKey, APIConstants.API_ENDPOINT_URL,
                Integer.valueOf(moviePreview.getMovieId()));
        return getMovie.getMovie();
    }
}

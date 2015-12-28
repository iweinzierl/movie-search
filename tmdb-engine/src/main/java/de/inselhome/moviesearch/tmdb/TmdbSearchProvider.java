package de.inselhome.moviesearch.tmdb;

import com.google.common.base.Strings;
import de.inselhome.moviesearch.api.SearchProvider;
import de.inselhome.moviesearch.api.domain.Movie;
import de.inselhome.moviesearch.api.domain.MoviePreview;
import de.inselhome.moviesearch.tmdb.constants.ConfigurationConstants;
import de.inselhome.moviesearch.tmdb.domain.DomainTransformer;
import de.inselhome.moviesearch.tmdb.domain.MovieResult;
import de.inselhome.moviesearch.tmdb.domain.SearchResult;
import de.inselhome.moviesearch.tmdb.get.MovieClient;
import de.inselhome.moviesearch.tmdb.get.MovieFactory;
import de.inselhome.moviesearch.tmdb.search.MovieSearchFactory;
import de.inselhome.moviesearch.tmdb.search.SearchClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit.Call;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TmdbSearchProvider implements SearchProvider {

    public static final String BASE_URL = "http://api.themoviedb.org/";
    public static final String BASE_LANGUAGE = "de";

    private static final Logger LOG = LoggerFactory.getLogger(TmdbSearchProvider.class);
    private static final DomainTransformer transformer = new DomainTransformer();

    private String apiKey;
    private String language;

    public TmdbSearchProvider(final String apiKey) {
        this(apiKey, BASE_LANGUAGE);
    }

    public TmdbSearchProvider(final String apiKey, final String language) {
        this.apiKey = apiKey;
        this.language = language;
    }

    @Override
    public void setProperties(final Map<String, String> properties) {
        String apiKey = properties.get(ConfigurationConstants.API_KEY);
        if (!Strings.isNullOrEmpty(apiKey)) {
            this.apiKey = apiKey;
        }

        String language = properties.get(ConfigurationConstants.LANGUAGE);
        if (!Strings.isNullOrEmpty(language)) {
            this.language = language;
        }
    }

    @Override
    public List<MoviePreview> search(final String searchString) {
        return search(searchString, null);
    }

    @Override
    public List<MoviePreview> search(final String searchString, final Integer maxResults) {
        SearchClient searchClient = new MovieSearchFactory(BASE_URL).createSearchClient();
        Call<SearchResult> searchCall = searchClient.search(apiKey, searchString, language);
        try {
            SearchResult searchResult = searchCall.execute().body();
            return collectAllSearchResults(searchClient, searchString, searchResult.getTotalPages());

        } catch (IOException e) {
            LOG.error("Unknown error while searching for movie", e);
        }

        return null;
    }

    private List<MoviePreview> collectAllSearchResults(SearchClient client, String query, int totalPages) {
        final List<MoviePreview> collection = new ArrayList<>();

        for (int i = 1; i <= totalPages; i++) {
            Call<SearchResult> call = client.search(apiKey, query, "de", i);
            try {
                SearchResult searchResult = call.execute().body();
                collection.addAll(transformer.transform(searchResult.getResults()));
            } catch (IOException e) {
                LOG.error("Unknown error while searching for movie, page = {}", i, e);
            }
        }

        return collection;
    }

    @Override
    public Movie get(String movieId) {
        MovieClient client = new MovieFactory(BASE_URL).createMovieClient();
        Call<MovieResult> movieResultCall = client.getMovie(Integer.valueOf(movieId), apiKey, language);

        try {
            MovieResult movieResult = movieResultCall.execute().body();
            return transformer.transform(movieResult);
        } catch (IOException e) {
            LOG.error("Unknown error while fetching movie: {}", movieId, e);
        }

        return null;
    }

    @Override
    public Movie get(final MoviePreview moviePreview) {
        return get(moviePreview.getMovieId());
    }
}

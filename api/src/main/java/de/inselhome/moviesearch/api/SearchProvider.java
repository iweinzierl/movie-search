package de.inselhome.moviesearch.api;

import java.util.List;
import java.util.Map;

import de.inselhome.moviesearch.api.domain.Movie;
import de.inselhome.moviesearch.api.domain.MoviePreview;

public interface SearchProvider {

    void setProperties(Map<String, String> properties);

    List<MoviePreview> search(String searchString);

    List<MoviePreview> search(String searchString, Integer maxResults);

    Movie get(MoviePreview moviePreview);
}

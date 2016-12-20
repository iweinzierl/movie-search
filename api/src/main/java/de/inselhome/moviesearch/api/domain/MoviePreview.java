package de.inselhome.moviesearch.api.domain;

import java.util.List;

public interface MoviePreview {

    String getMovieId();

    String getTitle();

    String getCover();

    String getDescription();

    List<String> getGenres();
}

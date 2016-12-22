package de.inselhome.moviesearch.api.domain;

import java.time.LocalDate;
import java.util.List;

public interface MoviePreview {

    String getMovieId();

    String getTitle();

    String getCover();

    String getDescription();

    LocalDate getPublishDate();

    int getLength();

    List<String> getGenres();
}

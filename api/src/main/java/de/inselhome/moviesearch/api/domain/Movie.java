package de.inselhome.moviesearch.api.domain;

import java.time.LocalDate;

import java.util.Set;

public interface Movie {

    void setId(String id);

    String getId();

    void setTitle(String title);

    String getTitle();

    void setOriginalTitle(String originalTitle);

    String getOriginalTitle();

    void setDescription(String description);

    String getDescription();

    void setCoverUrl(String cover);

    String getCoverUrl();

    void setGenres(Set<String> genres);

    Set<String> getGenres();

    void setLength(Integer length);

    Integer getLength();

    void setPublished(LocalDate publishDate);

    LocalDate getPublished();
}

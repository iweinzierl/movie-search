package de.inselhome.moviesearch.tmdb.domain;

import java.time.LocalDate;

import java.util.Set;

import de.inselhome.moviesearch.api.domain.Movie;

public class MovieImpl implements Movie {

    private String id;

    private String title;

    private String originalTitle;

    private String cover;

    private String description;

    private Set<String> genres;

    private Integer length;

    private LocalDate publishDate;

    public MovieImpl(final String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void setOriginalTitle(final String originalTitle) {
        this.originalTitle = originalTitle;
    }

    @Override
    public String getOriginalTitle() {
        return originalTitle;
    }

    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Set<String> getGenres() {
        return genres;
    }

    @Override
    public void setLength(final Integer length) {
        this.length = length;
    }

    @Override
    public Integer getLength() {
        return length;
    }

    public void setPublished(final LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public LocalDate getPublished() {
        return publishDate;
    }

    public void setGenres(final Set<String> genres) {
        this.genres = genres;
    }

    public void setCoverUrl(final String cover) {
        this.cover = cover;
    }

    public String getCoverUrl() {
        return cover;
    }
}

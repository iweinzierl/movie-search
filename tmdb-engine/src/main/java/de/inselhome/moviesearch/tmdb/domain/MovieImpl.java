package de.inselhome.moviesearch.tmdb.domain;

import java.time.LocalDate;

import java.util.Set;

import de.inselhome.moviesearch.api.domain.Movie;

public class MovieImpl implements Movie {

    private Long id;

    private String title;

    private String cover;

    private Set<String> genres;

    public MovieImpl(final String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void setOriginalTitle(final String originalTitle) { }

    @Override
    public String getOriginalTitle() {
        return null;
    }

    @Override
    public void setDescription(final String description) { }

    @Override
    public String getDescription() {
        return null;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Set<String> getGenres() {
        return genres;
    }

    @Override
    public void setLength(final int length) { }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public void setPublishDate(final LocalDate publishDate) { }

    @Override
    public LocalDate getPublishDate() {
        return null;
    }

    public void setGenres(final Set<String> genres) {
        this.genres = genres;
    }

    public void setCover(final String cover) {
        this.cover = cover;
    }

    public String getCover() {
        return cover;
    }
}

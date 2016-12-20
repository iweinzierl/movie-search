package de.inselhome.moviesearch.tmdb.domain;

import de.inselhome.moviesearch.api.domain.MoviePreview;

import java.util.List;

public class MoviePreviewImpl implements MoviePreview {

    private String id;
    private String title;
    private String cover;
    private String description;
    private List<String> genres;

    public MoviePreviewImpl(final String id, final String title, final String cover, final String description, final List<String> genres) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.description = description;
        this.genres = genres;
    }

    @Override
    public String getMovieId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getCover() {
        return cover;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<String> getGenres() {
        return genres;
    }
}

package de.inselhome.moviesearch.tmdb.domain;

import de.inselhome.moviesearch.api.domain.MoviePreview;

public class MoviePreviewImpl implements MoviePreview {

    private String id;
    private String title;
    private String cover;

    public MoviePreviewImpl(final String id, final String title) {
        this.id = id;
        this.title = title;
    }

    public MoviePreviewImpl(final String id, final String title, final String cover) {
        this.id = id;
        this.title = title;
        this.cover = cover;
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
}

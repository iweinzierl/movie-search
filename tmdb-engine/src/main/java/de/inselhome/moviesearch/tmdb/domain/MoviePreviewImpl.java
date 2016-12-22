package de.inselhome.moviesearch.tmdb.domain;

import de.inselhome.moviesearch.api.domain.MoviePreview;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class MoviePreviewImpl implements MoviePreview {

    private String id;
    private String title;
    private String cover;
    private String description;
    private LocalDate publishDate;
    private int length;
    private List<String> genres;

    @Override
    public String getMovieId() {
        return id;
    }
}

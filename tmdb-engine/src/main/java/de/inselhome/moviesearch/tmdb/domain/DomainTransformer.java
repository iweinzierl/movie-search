package de.inselhome.moviesearch.tmdb.domain;

import de.inselhome.moviesearch.api.domain.Movie;
import de.inselhome.moviesearch.api.domain.MoviePreview;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DomainTransformer {

    public MoviePreview transform(MovieSearchResult movieResult) {
        return new MoviePreviewImpl(
                String.valueOf(movieResult.getId()),
                movieResult.getTitle(),
                movieResult.getBackdropPath(),
                movieResult.getOverview()
        );
    }

    public List<MoviePreview> transform(List<MovieSearchResult> movieResults) {
        Stream<MoviePreview> moviePreviewStream = movieResults.parallelStream().map(this::transform);
        return moviePreviewStream.collect(Collectors.toList());
    }

    public Movie transform(MovieResult movieResult) {
        Movie movie = new MovieImpl(movieResult.getTitle());
        movie.setCoverUrl(movieResult.getBackdropPath());
        movie.setDescription(movieResult.getOverview());
        movie.setGenres(transform(movieResult.getGenres()));
        movie.setId(String.valueOf(movieResult.getId()));
        movie.setLength(movieResult.getRuntime());
        movie.setOriginalTitle(movieResult.getOriginalTitle());
        movie.setPublished(movieResult.getReleaseDate());
        return movie;
    }

    private String transform(GenreResult genreResult) {
        return genreResult.getName();
    }

    private Set<String> transform(Set<GenreResult> genres) {
        Stream<String> genresStream = genres.parallelStream().map(this::transform);
        return genresStream.collect(Collectors.toSet());
    }
}

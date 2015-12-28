package de.inselhome.moviesearch.tmdb.domain;

import de.inselhome.moviesearch.api.domain.MoviePreview;

import java.util.List;
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
}

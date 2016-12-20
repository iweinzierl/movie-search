package de.inselhome.moviesearch.tmdb.domain;

import com.google.common.base.Strings;
import de.inselhome.moviesearch.api.domain.Movie;
import de.inselhome.moviesearch.api.domain.MoviePreview;
import de.inselhome.moviesearch.tmdb.constants.APIConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DomainTransformer {

    @Autowired
    private GenreFinder genreFinder;

    public MoviePreview transform(MovieSearchResult movieResult) {
        List<String> genreIds = movieResult.getGenreIds()
                .stream()
                .map(integer -> String.valueOf(integer))
                .collect(Collectors.toList());

        return new MoviePreviewImpl(
                String.valueOf(movieResult.getId()),
                movieResult.getTitle(),
                createCoverUri(movieResult.getPosterPath()),
                movieResult.getOverview(),
                genreFinder.find(genreIds)
        );
    }

    public List<MoviePreview> transform(List<MovieSearchResult> movieResults) {
        Stream<MoviePreview> moviePreviewStream = movieResults.parallelStream().map(this::transform);
        return moviePreviewStream.collect(Collectors.toList());
    }

    public Movie transform(MovieResult movieResult) {
        Movie movie = new MovieImpl(movieResult.getTitle());
        movie.setCoverUrl(createCoverUri(movieResult.getPosterPath()));
        movie.setDescription(movieResult.getOverview());
        movie.setGenres(transform(movieResult.getGenres()));
        movie.setId(String.valueOf(movieResult.getId()));
        movie.setLength(movieResult.getRuntime());
        movie.setOriginalTitle(movieResult.getOriginalTitle());
        movie.setPublished(movieResult.getReleaseDate());
        return movie;
    }

    public String transform(GenreResult genreResult) {
        return genreResult.getName();
    }

    public Set<String> transform(Set<GenreResult> genres) {
        Stream<String> genresStream = genres.parallelStream().map(this::transform);
        return genresStream.collect(Collectors.toSet());
    }

    private String createCoverUri(final String posterPath) {
        if (Strings.isNullOrEmpty(posterPath)) {
            return null;
        }

        return String.format("%s%s", APIConstants.COVER_BASE_URL, posterPath);
    }
}

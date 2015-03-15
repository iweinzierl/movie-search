package de.inselhome.moviesearch.tmdb;

import java.util.List;

import de.inselhome.moviesearch.api.domain.MoviePreview;

public class ConvertApp {

    public static final String API_KEY = "653ce9ba629b16be9a1f5b1e4faf3697";
    public static final String SEARCH_TITLE = "Pirates of the Caribbean - Fluch der Karibik 2";

    public static void main(final String[] args) {

        TmdbSearchProvider searchProvider = new TmdbSearchProvider(API_KEY);
        List<MoviePreview> searchResult = searchProvider.search(SEARCH_TITLE);

        System.out.println("Found " + searchResult.size() + " movies");
    }
}

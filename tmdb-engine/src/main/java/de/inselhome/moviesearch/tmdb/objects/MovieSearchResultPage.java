package de.inselhome.moviesearch.tmdb.objects;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieSearchResultPage {

    private int page;
    private int total_pages;
    private int total_results;

    private List<MovieSearchResult> results;

    public int getPage() {
        return page;
    }

    public void setPage(final int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(final int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(final int total_results) {
        this.total_results = total_results;
    }

    public List<MovieSearchResult> getResults() {
        return results;
    }

    public void setResults(final List<MovieSearchResult> results) {
        this.results = results;
    }

    public static MovieSearchResultPage create(final JSONObject json) throws JSONException {
        MovieSearchResultPage page = new MovieSearchResultPage();
        page.setPage(json.getInt("page"));
        page.setTotal_pages(json.getInt("total_pages"));
        page.setTotal_results(json.getInt("total_results"));

        List<MovieSearchResult> results = new ArrayList<MovieSearchResult>();
        JSONArray resultsArray = json.getJSONArray("results");

        for (int index = 0; index < resultsArray.length(); index++) {
            results.add(MovieSearchResult.create(resultsArray.getJSONObject(index)));
        }

        page.setResults(results);

        return page;
    }
}

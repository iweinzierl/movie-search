package de.inselhome.moviesearch.tmdb.domain;

import java.util.List;

public class SearchResult {

    private int page;
    private int totalPages;
    private int totalResults;

    private List<MovieSearchResult> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<MovieSearchResult> getResults() {
        return results;
    }

    public void setResults(List<MovieSearchResult> results) {
        this.results = results;
    }
}

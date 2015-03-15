package de.inselhome.moviesearch.tmdb.objects;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieSearchResult {

    private int id;

    private String title;
    private String original_title;
    private String release_date;

    private boolean adult;
    private String backdrop_path;
    private String poster_path;

    private double popularity;
    private double vote_average;
    private int vote_count;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(final String original_title) {
        this.original_title = original_title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(final String release_date) {
        this.release_date = release_date;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(final boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(final String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(final String poster_path) {
        this.poster_path = poster_path;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(final double popularity) {
        this.popularity = popularity;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(final double vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(final int vote_count) {
        this.vote_count = vote_count;
    }

    public static MovieSearchResult create(final JSONObject json) throws JSONException {
        MovieSearchResult result = new MovieSearchResult();
        result.setAdult(json.getBoolean("adult"));
        result.setBackdrop_path(json.getString("backdrop_path"));
        result.setId(json.getInt("id"));
        result.setOriginal_title("original_title");
        result.setPopularity(json.getDouble("popularity"));
        result.setPoster_path(json.getString("poster_path"));
        result.setRelease_date(json.getString("release_date"));
        result.setTitle(json.getString("title"));
        result.setVote_average(json.getDouble("vote_average"));
        result.setVote_count(json.getInt("vote_count"));

        return result;
    }
}

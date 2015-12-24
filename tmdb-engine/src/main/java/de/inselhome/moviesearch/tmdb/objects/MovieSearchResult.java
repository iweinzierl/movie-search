package de.inselhome.moviesearch.tmdb.objects;

import de.inselhome.moviesearch.tmdb.util.JsonUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieSearchResult {

    private Integer id;

    private String title;
    private String original_title;
    private String release_date;
    private String description;

    private Boolean adult;
    private String backdrop_path;
    private String poster_path;

    private Double popularity;
    private Double vote_average;
    private Integer vote_count;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
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

    public Boolean isAdult() {
        return adult;
    }

    public void setAdult(final Boolean adult) {
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

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(final Double popularity) {
        this.popularity = popularity;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(final Double vote_average) {
        this.vote_average = vote_average;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(final Integer vote_count) {
        this.vote_count = vote_count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static MovieSearchResult create(final JSONObject json) throws JSONException {
        MovieSearchResult result = new MovieSearchResult();
        result.setAdult(JsonUtils.getBoolean(json, "adult"));
        result.setBackdrop_path(JsonUtils.getString(json, "backdrop_path"));
        result.setId(JsonUtils.getInt(json, "id"));
        result.setOriginal_title(JsonUtils.getString(json, "original_title"));
        result.setPopularity(JsonUtils.getDouble(json, "popularity"));
        result.setPoster_path(JsonUtils.getString(json, "poster_path"));
        result.setRelease_date(JsonUtils.getString(json, "release_date"));
        result.setTitle(JsonUtils.getString(json, "title"));
        result.setVote_average(JsonUtils.getDouble(json, "vote_average"));
        result.setVote_count(JsonUtils.getInt(json, "vote_count"));
        result.setDescription(JsonUtils.getString(json, "overview"));

        return result;
    }
}

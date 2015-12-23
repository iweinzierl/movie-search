package de.inselhome.moviesearch.tmdb.objects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieResult {

    private int id;
    private String title;
    private String original_title;
    private String release_date;
    private String poster_path;
    private Integer runtime;
    private List<GenreResult> genres;

    private boolean adult;
    private String backdrop_path;
    private long budget;
    private String homepage;
    private String overview;
    private double popularity;
    private double vote_average;
    private Integer vote_count;

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

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(final String poster_path) {
        this.poster_path = poster_path;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(final Integer runtime) {
        this.runtime = runtime;
    }

    public List<GenreResult> getGenres() {
        return genres;
    }

    public void setGenres(final List<GenreResult> genres) {
        this.genres = genres;
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

    public long getBudget() {
        return budget;
    }

    public void setBudget(final long budget) {
        this.budget = budget;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(final String homepage) {
        this.homepage = homepage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(final String overview) {
        this.overview = overview;
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

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(final Integer vote_count) {
        this.vote_count = vote_count;
    }

    public static MovieResult create(final JSONObject json) throws JSONException {
        MovieResult movie = new MovieResult();

        movie.setId(getIntegerIfNotNull(json, "id"));
        movie.setTitle(json.getString("title"));
        movie.setOriginal_title(json.getString("original_title"));
        movie.setRelease_date(json.getString("release_date"));
        movie.setPoster_path(json.getString("poster_path"));
        movie.setRuntime(getIntegerIfNotNull(json, "runtime"));

        movie.setAdult(json.getBoolean("adult"));
        movie.setBackdrop_path(json.getString("backdrop_path"));
        movie.setBudget(json.getLong("budget"));
        movie.setHomepage(json.getString("homepage"));
        movie.setOverview(json.getString("overview"));
        movie.setPopularity(json.getDouble("popularity"));
        movie.setVote_average(json.getDouble("vote_average"));
        movie.setVote_count(getIntegerIfNotNull(json, "vote_count"));

        JSONArray genresArray = json.getJSONArray("genres");
        List<GenreResult> genres = new ArrayList<GenreResult>();

        for (int index = 0; index < genresArray.length(); index++) {
            genres.add(GenreResult.create(genresArray.getJSONObject(index)));
        }

        movie.setGenres(genres);

        return movie;
    }

    public static Integer getIntegerIfNotNull(final JSONObject json, final String property) throws JSONException {
        if (json.isNull(property)) {
            return null;
        } else {
            return json.getInt(property);
        }
    }
}

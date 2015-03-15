package de.inselhome.moviesearch.tmdb.objects;

import org.json.JSONException;
import org.json.JSONObject;

public class GenreResult {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public static GenreResult create(final JSONObject json) throws JSONException {
        GenreResult genre = new GenreResult();
        genre.setId(json.getInt("id"));
        genre.setName(json.getString("name"));

        return genre;
    }
}

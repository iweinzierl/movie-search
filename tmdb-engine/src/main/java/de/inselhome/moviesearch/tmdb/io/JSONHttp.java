package de.inselhome.moviesearch.tmdb.io;

import java.io.IOException;

import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import de.inselhome.moviesearch.tmdb.util.HttpUtil;

public class JSONHttp {
    public static JSONObject read(final URL url) throws IOException, JSONException {
        String response = HttpUtil.getResponse(url);

        if (response != null && response.length() > 0) {
            return new JSONObject(response);
        }

        return null;
    }
}

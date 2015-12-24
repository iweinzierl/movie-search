package de.inselhome.moviesearch.tmdb.util;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    public static Boolean getBoolean(JSONObject json, String property) throws JSONException {
        if (!json.isNull(property)) {
            return json.getBoolean(property);
        }

        return null;
    }

    public static Integer getInt(JSONObject json, String property) throws JSONException {
        if (!json.isNull(property)) {
            return json.getInt(property);
        }

        return null;
    }

    public static Double getDouble(JSONObject json, String property) throws JSONException {
        if (!json.isNull(property)) {
            return json.getDouble(property);
        }

        return null;
    }

    public static String getString(JSONObject json, String property) throws JSONException {
        if (!json.isNull(property)) {
            return json.getString(property);
        }

        return null;
    }
}

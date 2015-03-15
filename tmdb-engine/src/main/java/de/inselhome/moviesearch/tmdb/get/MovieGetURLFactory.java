package de.inselhome.moviesearch.tmdb.get;

import java.io.UnsupportedEncodingException;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MovieGetURLFactory {

    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final String DEFAULT_LANGUAGE = "de";

    public static final String METHOD = "movie";

    public static final String API_KEY_PARAMETER = "api_key=";
    public static final String LANGUAGE_PARAMETER = "language=";
    public static final String FIRST_DELIMITER = "?";
    public static final String DELIMITER = "&";

    private final String apiKey;
    private final String baseUrl;
    private final int movieId;

    public MovieGetURLFactory(final String apiKey, final String baseUrl, final int movieId) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.movieId = movieId;
    }

    public URL build() throws MalformedURLException {
        StringBuilder sb = new StringBuilder(baseUrl);
        sb.append(METHOD);

        sb.append("/");
        sb.append(movieId);

        String tmp = sb.toString();
        if (tmp.indexOf(FIRST_DELIMITER) <= 0) {
            sb.append(FIRST_DELIMITER);
        } else {
            sb.append(DELIMITER);
        }

        sb.append(API_KEY_PARAMETER);
        sb.append(apiKey);

        try {
            sb.append(DELIMITER);
            sb.append(LANGUAGE_PARAMETER);
            sb.append(URLEncoder.encode(DEFAULT_LANGUAGE, DEFAULT_CHARSET));
        } catch (UnsupportedEncodingException uee) {
            sb.append(DEFAULT_LANGUAGE);
        }

        String url = sb.toString();

        return new URL(url);
    }
}

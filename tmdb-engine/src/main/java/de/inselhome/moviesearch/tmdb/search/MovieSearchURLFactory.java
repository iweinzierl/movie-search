package de.inselhome.moviesearch.tmdb.search;

import java.io.UnsupportedEncodingException;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MovieSearchURLFactory {

    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final String DEFAULT_LANGUAGE = "de";

    public static final String METHOD = "search/movie";

    public static final String QUERY_PARAMETER = "query=";
    public static final String API_KEY_PARAMETER = "api_key=";
    public static final String LANGUAGE_PARAMETER = "language=";
    public static final String PAGE_PARAMETER = "page=";
    public static final String FIRST_DELIMITER = "?";
    public static final String DELIMITER = "&";

    private String apiKey;
    private String baseUrl;
    private String query;

    private int page;

    public MovieSearchURLFactory(final String apiKey, final String baseUrl, final String query, final int page) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.query = query;
        this.page = page;
    }

    public URL build() throws MalformedURLException {
        StringBuilder sb = new StringBuilder(baseUrl);
        sb.append(METHOD);

        String tmp = sb.toString();
        if (tmp.indexOf(FIRST_DELIMITER) <= 0) {
            sb.append(FIRST_DELIMITER);
        } else {
            sb.append(DELIMITER);
        }

        sb.append(QUERY_PARAMETER);

        try {
            sb.append(URLEncoder.encode(query, DEFAULT_CHARSET));
        } catch (UnsupportedEncodingException uee) {
            sb.append(query);
        }

        try {
            sb.append(DELIMITER);
            sb.append(LANGUAGE_PARAMETER);
            sb.append(URLEncoder.encode(DEFAULT_LANGUAGE, DEFAULT_CHARSET));
        } catch (UnsupportedEncodingException uee) {
            sb.append(DEFAULT_LANGUAGE);
        }

        sb.append(DELIMITER);
        sb.append(PAGE_PARAMETER);
        sb.append(String.valueOf(page));

        sb.append(DELIMITER);
        sb.append(API_KEY_PARAMETER);
        sb.append(apiKey);

        String url = sb.toString();

        return new URL(url);
    }

}

package de.inselhome.moviesearch.tmdb.search;

import de.inselhome.moviesearch.tmdb.domain.SearchResult;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

public interface SearchClient {

    @Headers({"Accept: application/json"})
    @GET("/3/search/movie")
    Call<SearchResult> search(
            @Query("api_key") String apiKey,
            @Query("query") String searchQuery,
            @Query("language") String language);

    @Headers({"Accept: application/json"})
    @GET("/3/search/movie")
    Call<SearchResult> search(
            @Query("api_key") String apiKey,
            @Query("query") String searchQuery,
            @Query("language") String language,
            @Query("page") int page);
}

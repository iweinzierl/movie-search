package de.inselhome.moviesearch.tmdb.get;

import de.inselhome.moviesearch.tmdb.domain.MovieResult;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface MovieClient {

    @GET("/3/movie/{id}")
    Call<MovieResult> getMovie(
            @Path("id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language);
}

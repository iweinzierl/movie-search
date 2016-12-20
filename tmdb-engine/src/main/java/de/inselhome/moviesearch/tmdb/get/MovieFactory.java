package de.inselhome.moviesearch.tmdb.get;

import com.google.common.base.Strings;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Component
public class MovieFactory {

    @Value("${tmdb.api.baseUrl}")
    private String baseUrl;

    public MovieClient createMovieClient() {
        OkHttpClient client = createClient();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .build()
                .create(MovieClient.class);
    }

    private OkHttpClient createClient() {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(5000, TimeUnit.MILLISECONDS);
        client.setReadTimeout(15000, TimeUnit.MILLISECONDS);
        client.interceptors().add(createLoggingInterceptor());

        return client;
    }

    private static HttpLoggingInterceptor createLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    private static Gson createGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, typeOfT, context) -> {
                    String date = json.getAsString();
                    if (!Strings.isNullOrEmpty(date)) {
                        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    }
                    return null;
                })
                .create();
    }
}

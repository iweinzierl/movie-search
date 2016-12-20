package de.inselhome.moviesearch.tmdb.domain;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GenreFinder {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenreFinder.class);

    private Map<String, String> genresMap;

    @PostConstruct
    public void setup() {
        InputStream in = getClass().getResourceAsStream("/tmdb_genres.json");
        genresMap = (Map<String, String>) new Gson().fromJson(new InputStreamReader(in), Map.class);

        LOGGER.info("initialized GenreFinder with {} genres", genresMap.size());
    }

    List<String> find(List<String> genreIds) {
        return genresMap.keySet()
                .parallelStream()
                .filter(genreIds::contains)
                .map(s -> genresMap.get(s))
                .collect(Collectors.toList());
    }
}

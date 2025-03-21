package org.example.springfromscratchex.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.NonNull;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MovieInfoResponse(MovieInfoResult movieInfoResult) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record MovieInfoResult(MovieInfo movieInfo) {}

@JsonIgnoreProperties(ignoreUnknown = true)
public record MovieInfo(
            String showTm,
            List<Nation> nations,
            List<GenreNm> genreNm,
            List<Directors> directors,
            List<Actors> actors
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Nation(String nationNm) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record GenreNm(String genreNm) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Directors(String peopleNm) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Actors(String peopleNm) {}
}

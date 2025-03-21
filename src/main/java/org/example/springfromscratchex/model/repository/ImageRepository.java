package org.example.springfromscratchex.model.repository;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.springfromscratchex.model.dto.MovieDTO;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

@Repository
public class ImageRepository implements APIClientRepository {
    Logger logger = Logger.getLogger(ImageRepository.class.getName());
    final String clientId = dotenv.get("NAVER_CLIENT_ID");
    final String clientSecret = dotenv.get("NAVER_CLIENT_SECRET");

    public String getImage(MovieDTO movie) throws IOException, InterruptedException {
    String baseURL = "https://openapi.naver.com/v1/search/image";
    String query = URLEncoder.encode("%s 포스터".formatted(movie.name()), StandardCharsets.UTF_8);
    String url = "%s?query=%s&display=1".formatted(baseURL, query);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                .GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode() >= 400) {
            throw new IOException("Http status code is " + response.statusCode() );
        }
        logger.info(response.body());
        return response.body();
    }
}

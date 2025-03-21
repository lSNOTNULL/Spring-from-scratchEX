package org.example.springfromscratchex.model.repository;

import org.example.springfromscratchex.model.dto.MovieDTO;
import org.example.springfromscratchex.model.dto.MovieParam;
import org.example.springfromscratchex.model.dto.MovieResponse;
import org.example.springfromscratchex.model.repository.APIClientRepository;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Repository
public class MovieRepository implements APIClientRepository {
    public static final String baseURL = "https://www.kobis.or.kr/kobisopenapi/webservice/rest";
    public static final String key = dotenv.get("MOVIE_KEY");

public static String callAPI(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                // 예시 : http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=82ca741a2844c5c180a208137bb92bd7&targetDt=20120101

                //"%s?key=%s&targetDt=%s"
                // action = "searchDailyBoxOfficeList"
                // format = "json"
                // url = "%s/%s.%s?key=%s&targetDt=%s"
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {

        return response.body();
    }
        throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
}
}

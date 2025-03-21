package org.example.springfromscratchex.service;

import org.example.springfromscratchex.model.dto.*;
import org.example.springfromscratchex.model.repository.ImageRepository;
import org.example.springfromscratchex.model.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.example.springfromscratchex.model.repository.APIClientRepository.objectMapper;
import static org.example.springfromscratchex.model.repository.MovieRepository.*;

@Service
public class MovieService {
    final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDTO> getMovies() throws Exception {
//        logger.info("getMovies called");
        // yyyymmdd
        LocalDate nowDate = LocalDate.now();
        nowDate = nowDate.minusDays(1);
        String nowDateStr = nowDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        MovieParam param = new MovieParam(nowDateStr);
//        MovieParam param = new MovieParam(date);
        return getMovies(param);
        // Repository -> getmovies의 return값을 불러옴.
        // -> 레이어드 아키텍쳐 고려하여 Service로 getmovies 이동
    }
    public List<MovieDTO> getMovies(MovieParam param) throws Exception {

//        "%s?key=%s&targetDt=%s"
        String action = "boxoffice/searchDailyBoxOfficeList";
        String format = "json";
        String url = "%s/%s.%s?key=%s&targetDt=%s".formatted(baseURL,action,format, key,param.targetDate());
        System.out.println(url);
        String responseBody = MovieRepository.callAPI(url);

        MovieResponse movieResponse = objectMapper.readValue(responseBody, MovieResponse.class);

        // targetDt : yyyymmdd
        return movieResponse.boxOfficeResult().dailyBoxOfficeList()
                .stream().map((v) -> new MovieDTO(Long.parseLong(v.rank()), v.movieCd(), v.movieNm(), v.openDt(), Long.parseLong(v.audiAcc()))).toList();
    }
    public List<MovieInfoDTO> getMovieInfo() throws Exception {

        return getMovies().stream().map((v) ->{
            // getMovies() 의 return값을 map 을 사용해 v 파라미터를 차례대로 넣게 되고, 그값은 getMovieInfo의 movie 파라미터 값으로 할당된다.
            try {
                return getMovieInfo(v);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList(); // 할당된 최종 값들을 List형태로 반환.
    }


    public MovieInfoDTO getMovieInfo(MovieDTO movie) throws Exception {
        String action = "movie/searchMovieInfo";
        String format = "json";
        String url = "%s/%s.%s?key=%s&movieCd=%s".formatted(baseURL,action,format, key,movie.code());
        String responseBody = callAPI(url);
        System.out.println(responseBody);
        MovieInfoResponse movieInfoResponse = objectMapper.readValue(responseBody, MovieInfoResponse.class);// DTO 형식으로 역직렬화 후 변수에 담음.

        MovieInfoResponse.MovieInfo info = movieInfoResponse.movieInfoResult().movieInfo();
        // MovieInfoResponse DTO 중에서도 MovieInfo 레코드의 내용을 가지는 info 변수 선언

       return new MovieInfoDTO(movie,
               info.nations().stream().map(MovieInfoResponse.Nation::nationNm).toList(),
               info.genreNm() != null ? info.genreNm().stream().map(MovieInfoResponse.GenreNm::genreNm).toList() : List.of(),
               // genreNm이 null 반환하는 문제 삼항연산자 활용하여 null을 공백처리.
               info.directors().stream().map(MovieInfoResponse.Directors::peopleNm).toList(),
               info.actors().stream().map(MovieInfoResponse.Actors::peopleNm).toList(),
               Long.parseLong(info.showTm())
        // MovieInfoDTO 레코드에 있는 내용 전부를 정제하여 리턴
       );
       // 기존의 movie 값 + info값을 함께 리턴
    }


}

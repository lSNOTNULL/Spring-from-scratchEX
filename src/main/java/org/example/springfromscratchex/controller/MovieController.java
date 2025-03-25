package org.example.springfromscratchex.controller;

import org.example.springfromscratchex.model.dto.MovieDTO;
import org.example.springfromscratchex.model.dto.MovieInfoDTO;
import org.example.springfromscratchex.service.GeminiService;
import org.example.springfromscratchex.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class MovieController {
    final MovieService movieService;
    final GeminiService geminiService;

    public MovieController(MovieService movieService, GeminiService geminiService) {
        this.movieService = movieService;
        this.geminiService = geminiService;
    }


    @GetMapping("/")
    public String index(Model model) throws Exception {
//        List<MovieDTO> movies = movieService.getMovies();
        List<MovieInfoDTO> movies = movieService.getMovieInfo();
        // getMovieInfo 안에 기존의 getMovies 결과값도 담겨있으므로 최종적으로 Info정보까지 추가로 출력하게 된다.
        model.addAttribute("movies", movies);

        String recommendation = movieService.generatePrompt(movies);
        model.addAttribute("recommendation", recommendation);

        return "index";
    }
}

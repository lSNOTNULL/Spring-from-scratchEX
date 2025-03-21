package org.example.springfromscratchex.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springfromscratchex.model.dto.ImageResponse;
import org.example.springfromscratchex.model.dto.MovieDTO;
import org.example.springfromscratchex.model.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

//@Service
//public class ImageService {
//    final ImageService imagerepository;
//    ObjectMapper mapper = new ObjectMapper();
//
//    public ImageService(ImageService imagerepository) {
//        this.imagerepository = imagerepository;
//    }
//
//    public String getImage(MovieDTO movie) throws JsonProcessingException {
//        String responseBody = imagerepository.getImage(movie);
//        ImageResponse imageResponse = mapper.readValue(responseBody, ImageResponse.class);
//        String imageUrl = imageResponse.item().get(0).link();
//        return URLDecoder.decode(imageUrl, StandardCharsets.UTF_8);
//    }
//}

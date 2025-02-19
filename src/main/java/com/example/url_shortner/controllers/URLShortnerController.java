package com.example.url_shortner.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.url_shortner.domain.dtos.URLShortnerDto;
import com.example.url_shortner.domain.entities.URLShortnerEntity;
import com.example.url_shortner.mappers.Mapper;
import com.example.url_shortner.services.URLShortnerService;

@RestController
public class URLShortnerController {

    private URLShortnerService urlShortnerService;

    private Mapper<URLShortnerEntity, URLShortnerDto> urlMapper;

    public URLShortnerController(URLShortnerService urlShortnerService,
            Mapper<URLShortnerEntity, URLShortnerDto> urlMapper) {
        this.urlShortnerService = urlShortnerService;
        this.urlMapper = urlMapper;
    }

    @PostMapping(path = "/short")
    public ResponseEntity<URLShortnerDto> shortenURL(@RequestBody URLShortnerDto urlShortnerDto) {
        URLShortnerEntity urlShortnerEntity = urlMapper.mapFromDto(urlShortnerDto);
        URLShortnerEntity result = urlShortnerService.createShortUrl(urlShortnerEntity);
        URLShortnerDto response = urlMapper.mapToDto(result);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "/short")
    public ResponseEntity<List<URLShortnerDto>> getAllURLShortnerDtos() {
        List<URLShortnerEntity> allUrlShortnerEntities = urlShortnerService.findAllShortenedEntities();
        if (allUrlShortnerEntities == null) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
        }
        List<URLShortnerDto> allUrlShortnerDtos = allUrlShortnerEntities.stream()
                .map(urlMapper::mapToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(allUrlShortnerDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/short/{short_url}")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable("short_url") String shortUrl) {
        Optional<URLShortnerEntity> shortURLEntity = urlShortnerService.getOriginalUrl(shortUrl);

        return shortURLEntity
                .map(entity -> ResponseEntity.status(HttpStatus.FOUND)
                        .header("Location", entity.getOriginalUrl())
                        .build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

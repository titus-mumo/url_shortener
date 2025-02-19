package com.example.url_shortner.services;

import java.util.List;
import java.util.Optional;

import com.example.url_shortner.domain.entities.URLShortnerEntity;

public interface URLShortnerService {
    URLShortnerEntity createShortUrl(URLShortnerEntity urlShortnerEntity);

    List<URLShortnerEntity> findAllShortenedEntities();

    Optional<URLShortnerEntity> getOriginalUrl(String shortUrl);

}

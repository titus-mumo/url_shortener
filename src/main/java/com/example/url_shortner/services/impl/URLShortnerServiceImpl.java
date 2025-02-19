package com.example.url_shortner.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.example.url_shortner.domain.entities.URLShortnerEntity;
import com.example.url_shortner.repositories.URLShortnerRepository;
import com.example.url_shortner.services.GenerateShortURLService;
import com.example.url_shortner.services.URLShortnerService;

@Service
public class URLShortnerServiceImpl implements URLShortnerService {

    private URLShortnerRepository urlShortnerRepository;
    private GenerateShortURLService generateShortURLService;

    public URLShortnerServiceImpl(URLShortnerRepository urlShortnerRepository,
            GenerateShortURLService generateShortURLService) {
        this.urlShortnerRepository = urlShortnerRepository;
        this.generateShortURLService = generateShortURLService;
    };

    @Override
    public URLShortnerEntity createShortUrl(URLShortnerEntity urlShortnerEntity) {
        if (urlShortnerRepository.findByOriginalUrl(urlShortnerEntity.getOriginalUrl()).isPresent()) {
            return urlShortnerRepository.findByOriginalUrl(urlShortnerEntity.getOriginalUrl()).get();
        }
        String shortUrl = generateShortURLService.generateUniqueShortId();
        urlShortnerEntity.setShortenedUrl(shortUrl);
        return urlShortnerRepository.save(urlShortnerEntity);
    }

    @Override
    public List<URLShortnerEntity> findAllShortenedEntities() {
        return StreamSupport.stream(urlShortnerRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<URLShortnerEntity> getOriginalUrl(String shortUrl) {
        return Optional.ofNullable(urlShortnerRepository.findByShortenedUrl(shortUrl)).orElse(null);
    }
}

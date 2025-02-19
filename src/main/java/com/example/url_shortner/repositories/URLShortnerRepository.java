package com.example.url_shortner.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.url_shortner.domain.entities.URLShortnerEntity;

@Repository
public interface URLShortnerRepository extends CrudRepository<URLShortnerEntity, String> {
    Optional<URLShortnerEntity> findByShortenedUrl(String shortenedUrl);

    Optional<URLShortnerEntity> findByOriginalUrl(String origininalUrl);
}

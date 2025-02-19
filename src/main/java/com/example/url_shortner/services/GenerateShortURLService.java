package com.example.url_shortner.services;

import org.springframework.stereotype.Service;

import com.example.url_shortner.repositories.URLShortnerRepository;

import java.security.SecureRandom;
import java.util.Random;

@Service
public class GenerateShortURLService {

    private URLShortnerRepository urlShortenerRepository;

    public GenerateShortURLService(URLShortnerRepository urlShortenerRepository) {
        this.urlShortenerRepository = urlShortenerRepository;
    }

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int ID_LENGTH = 6;
    private static final Random RANDOM = new SecureRandom();

    public String generateUniqueShortId() {
        // TODO: Figure out here
        String newId;
        do {
            newId = generateRandomString();
        } while (urlShortenerRepository.findByShortenedUrl(newId).isPresent());

        return newId;
    }

    private String generateRandomString() {
        StringBuilder sb = new StringBuilder(ID_LENGTH);
        for (int i = 0; i < ID_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}

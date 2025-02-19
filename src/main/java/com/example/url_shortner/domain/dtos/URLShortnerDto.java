package com.example.url_shortner.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class URLShortnerDto {

    private Integer id;

    private String shortenedUrl;

    private String originalUrl;

}

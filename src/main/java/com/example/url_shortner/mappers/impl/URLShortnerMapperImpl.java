package com.example.url_shortner.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.url_shortner.domain.dtos.URLShortnerDto;
import com.example.url_shortner.domain.entities.URLShortnerEntity;
import com.example.url_shortner.mappers.Mapper;

@Component
public class URLShortnerMapperImpl implements Mapper<URLShortnerEntity, URLShortnerDto> {

    private ModelMapper modelMapper;

    public URLShortnerMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public URLShortnerDto mapToDto(URLShortnerEntity urlShortnerEntity) {
        return modelMapper.map(urlShortnerEntity, URLShortnerDto.class);
    }

    @Override
    public URLShortnerEntity mapFromDto(URLShortnerDto urlShortnerDto) {
        return modelMapper.map(urlShortnerDto, URLShortnerEntity.class);
    }
}

package com.example.url_shortner.mappers;

public interface Mapper<A, B> {

    B mapToDto(A a);

    A mapFromDto(B b);
}

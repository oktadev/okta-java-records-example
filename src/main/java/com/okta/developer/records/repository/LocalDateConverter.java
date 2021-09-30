package com.okta.developer.records.repository;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;


public class LocalDateConverter implements Converter<String, LocalDate> {
    @Override
    public LocalDate convert(String s) {
        return LocalDate.parse(s);
    }
}

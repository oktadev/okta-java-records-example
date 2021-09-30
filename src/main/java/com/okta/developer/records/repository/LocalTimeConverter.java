package com.okta.developer.records.repository;


import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;

public class LocalTimeConverter implements Converter<String, LocalTime> {
    @Override
    public LocalTime convert(String s) {
        return LocalTime.parse(s);
    }


}

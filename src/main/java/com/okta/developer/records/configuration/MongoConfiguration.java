package com.okta.developer.records.configuration;

import com.okta.developer.records.repository.LocalDateConverter;
import com.okta.developer.records.repository.LocalTimeConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;

@Configuration
public class MongoConfiguration {

    @Bean
    public MongoCustomConversions mongoCustomConversions() {

        return new MongoCustomConversions(
            Arrays.asList(
                    new LocalDateConverter(),
                    new LocalTimeConverter()));
    }
}

package com.okta.developer.records.configuration;

import com.okta.developer.records.repository.LocalDateConverter;
import com.okta.developer.records.repository.LocalTimeConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

@Configuration
public class MongoConfiguration extends AbstractReactiveMongoConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    protected void configureConverters(MongoCustomConversions.MongoConverterConfigurationAdapter adapter) {
        adapter.registerConverter(new LocalDateConverter());
        adapter.registerConverter(new LocalTimeConverter());
    }
}

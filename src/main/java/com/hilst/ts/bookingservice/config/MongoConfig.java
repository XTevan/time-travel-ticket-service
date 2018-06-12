package com.hilst.ts.bookingservice.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.hilst.ts.bookingservice.repositories")
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${mongo.database}")
    private String databaseName;

    @Value("${mongo.host}")
    private String host;

    @Value("${mongo.port}")
    private Integer port;


    @Override
    public MongoClient mongoClient() {
        return new MongoClient(host,port);
    }

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }


}

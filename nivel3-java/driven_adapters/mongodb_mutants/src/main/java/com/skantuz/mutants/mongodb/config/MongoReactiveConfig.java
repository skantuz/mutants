package com.skantuz.mutants.mongodb.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.skantuz.mutants.mongodb.repository")
public class MongoReactiveConfig extends AbstractReactiveMongoConfiguration {

    @Value("${spring.data.mongodb.host}")
    private String dataBase;

    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create();
    }

    @Override
    protected String getDatabaseName() {
        return dataBase;
    }
}

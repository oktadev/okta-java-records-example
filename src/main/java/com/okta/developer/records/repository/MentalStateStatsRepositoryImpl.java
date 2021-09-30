package com.okta.developer.records.repository;

import com.okta.developer.records.domain.EndOfGame;
import com.okta.developer.records.domain.MentalStateDamage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import reactor.core.publisher.Flux;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public class MentalStateStatsRepositoryImpl implements MentalStateStatsRepository {


    private final ReactiveMongoTemplate mongoTemplate;

    @Autowired
    public MentalStateStatsRepositoryImpl(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Flux<MentalStateDamage> queryMentalStateAverageDamage() {
        Aggregation aggregation = newAggregation(
                group("mentalState")
                        .first("mentalState").as("mentalState")
                        .avg("damageToPlayers").as("damageToPlayers")
                        .avg("damageTaken").as("damageTaken")
                        .avg("damageToStructures").as("damageToStructures"),
                project("mentalState", "damageToPlayers", "damageTaken", "damageToStructures")
                );
        return mongoTemplate.aggregate(aggregation, EndOfGame.class, MentalStateDamage.class);
    }

}

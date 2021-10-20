package com.okta.developer.records.service;

import com.okta.developer.records.domain.EndOfGame;
import com.okta.developer.records.domain.MentalStateDamage;
import reactor.core.publisher.Flux;

public interface StatsService {

    Flux<MentalStateDamage> queryMentalStateAverageDamage();

    Flux<EndOfGame> getAll();
}

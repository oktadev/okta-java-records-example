package com.okta.developer.records.repository;

import com.okta.developer.records.domain.MentalStateDamage;
import reactor.core.publisher.Flux;

public interface MentalStateStatsRepository {

    Flux<MentalStateDamage> queryMentalStateAverageDamage();

}

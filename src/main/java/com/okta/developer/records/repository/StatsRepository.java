package com.okta.developer.records.repository;

import com.okta.developer.records.domain.EndOfGame;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface StatsRepository extends ReactiveSortingRepository<EndOfGame, String>, MentalStateStatsRepository {

}

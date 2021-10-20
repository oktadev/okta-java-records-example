package com.okta.developer.records.service;

import com.okta.developer.records.domain.EndOfGame;
import com.okta.developer.records.domain.MentalStateDamage;
import com.okta.developer.records.repository.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class DefaultStatsService implements StatsService {

    @Autowired
    private StatsRepository statsRepository;

    @Override
    public Flux<MentalStateDamage> queryMentalStateAverageDamage() {
        return statsRepository.queryMentalStateAverageDamage();
    }

    @Override
    public Flux<EndOfGame> getAll() {
        return statsRepository.findAll();
    }
}

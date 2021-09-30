package com.okta.developer.records.controller;

import com.okta.developer.records.domain.EndOfGame;
import com.okta.developer.records.domain.MentalStateDamage;
import com.okta.developer.records.repository.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class StatsController {

    @Autowired
    private StatsRepository statsRepository;

    @GetMapping("/endOfGame")
    public Flux<EndOfGame> getAllEndOfGame(){
        return statsRepository.findAll();
    }

    @GetMapping("/mentalStateAverageDamage")
    public Flux<MentalStateDamage> getMentalStateAverageDamage(){
        return statsRepository.queryMentalStateAverageDamage();
    }

}

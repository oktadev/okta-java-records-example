package com.okta.developer.records.controller;

import com.okta.developer.records.domain.EndOfGame;
import com.okta.developer.records.domain.MentalStateDamage;
import com.okta.developer.records.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class StatsController {

    @Autowired
    private StatsService statsService;

    @GetMapping("/endOfGame")
    public Flux<EndOfGame> getAllEndOfGame(){
        return statsService.getAll();
    }

    @GetMapping("/mentalStateAverageDamage")
    public Flux<MentalStateDamage> getMentalStateAverageDamage(){
        return statsService.queryMentalStateAverageDamage();
    }

}

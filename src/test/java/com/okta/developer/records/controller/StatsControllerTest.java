package com.okta.developer.records.controller;

import com.okta.developer.records.domain.EndOfGame;
import com.okta.developer.records.domain.MentalStateDamage;
import com.okta.developer.records.service.StatsService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockOidcLogin;

@WebFluxTest
public class StatsControllerTest {

    private static Logger logger = LoggerFactory.getLogger(StatsControllerTest.class);

    @MockBean
    private StatsService statsService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testGet_noAuth_returnsNotAuthorized(){

        webTestClient
                .get().uri("/endofgame")
                .exchange()
                .expectStatus().is3xxRedirection();
    }

    @Test
    public void testGet_withOidcLogin_returnsOk(){

        EndOfGame endOfGame = new EndOfGame("1", LocalDate.now(), LocalTime.now(), "happy", 1, 1, 1);

        given(statsService.getAll()).willReturn(Flux.just(endOfGame));


        webTestClient.mutateWith(mockOidcLogin())
                .get().uri("/endOfGame")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                    .jsonPath("$.length()").isNumber()
                    .jsonPath("$.length()").isEqualTo("1")
                    .jsonPath("$[0].mentalState").isEqualTo("happy")
                    .jsonPath("$[0].damageTaken").isNumber()
                    .jsonPath("$[0].damageToPlayers").isNumber()
                    .jsonPath("$[0].damageToStructures").isNumber()
                    .jsonPath("$[0].date").isNotEmpty()
                    .jsonPath("$[0].timeOfDay").isNotEmpty()
                    .consumeWith(response -> logger.info(response.toString()));

    }

    @Test
    public void testGetMentalStateAverageDamage_withOidcLogin_returnsOk(){

        MentalStateDamage mentalStateDamage = new MentalStateDamage("happy", 0.0, 0.0, 0.0);

        given(statsService.queryMentalStateAverageDamage()).willReturn(Flux.just(mentalStateDamage));

        webTestClient
                .mutateWith(mockOidcLogin())
                .get().uri("/mentalStateAverageDamage")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.length()").isEqualTo("1")
                .jsonPath("$.[0].mentalState").isEqualTo("happy")
                .consumeWith(response -> logger.info(response.toString()));
    }

}

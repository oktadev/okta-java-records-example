package com.okta.developer.records.controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.Container;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockOidcLogin;

@SpringBootTest
@AutoConfigureWebTestClient
public class StatsControllerTest {

    private static Logger logger = LoggerFactory.getLogger(StatsControllerTest.class);

    @Autowired
    private WebTestClient webTestClient;

    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:bionic"))
            .withExposedPorts(27017)
            .withCopyFileToContainer(MountableFile.forClasspathResource("stats.json"),
                    "/stats.json")
            .withEnv("MONGO_INIT_DATABASE", "fortnite");

    @BeforeAll
    public static void setUp() throws IOException, InterruptedException {
        mongoDBContainer.setPortBindings(List.of("27017:27017"));
        mongoDBContainer.start();

        Container.ExecResult result = mongoDBContainer.execInContainer("mongoimport",
                "--verbose", "--db=fortnite", "--collection=stats", "--file=/stats.json", "--jsonArray");
        logger.info(result.getStdout());
        logger.info(result.getStderr());
        logger.info("exit code={}", result.getExitCode());
    }

    @Test
    public void testGet_noAuth_returnsNotAuthorized(){

        webTestClient
                .get().uri("/endofgame")
                .exchange()
                .expectStatus().is3xxRedirection();
    }

    @Test
    public void testGet_withOidcLogin_returnsOk(){

        webTestClient.mutateWith(mockOidcLogin())
                .get().uri("/endOfGame")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                    .jsonPath("$.length()").isNumber()
                    .jsonPath("$.length()").isEqualTo("87")
                    .jsonPath("$[0].mentalState").isNotEmpty()
                    .jsonPath("$[0].damageTaken").isNumber()
                    .jsonPath("$[0].damageToPlayers").isNumber()
                    .jsonPath("$[0].damageToStructures").isNumber()
                    .jsonPath("$[0].date").isNotEmpty()
                    .jsonPath("$[0].timeOfDay").isNotEmpty()
                    .consumeWith(response -> logger.info(response.toString()));

    }

    @Test
    public void testGetMentalStateAverageDamage_withOidcLogin_returnsOk(){
        webTestClient
                .mutateWith(mockOidcLogin())
                .get().uri("/mentalStateAverageDamage")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.length()").isEqualTo("2")
                .consumeWith(response -> logger.info(response.toString()));
    }

    @AfterAll
    public static void tearDown(){
        mongoDBContainer.stop();
    }

}

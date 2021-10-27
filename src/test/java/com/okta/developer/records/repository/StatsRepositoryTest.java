package com.okta.developer.records.repository;

import com.okta.developer.records.configuration.MongoConfiguration;
import com.okta.developer.records.domain.EndOfGame;
import com.okta.developer.records.domain.MentalStateDamage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.Container;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@Import(MongoConfiguration.class)
public class StatsRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(StatsRepositoryTest.class);

    @Autowired
    private StatsRepository statsRepository;

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
    public void testGetAll(){
        Flux<EndOfGame> stats = statsRepository.findAll();
        List<EndOfGame> result = stats.collectList().block();

        assertThat(result).isNotEmpty();
        assertThat(result).size().isEqualTo(87);
    }

    @Test
    public void testQueryMentalStateAverageDamage(){
        Flux<MentalStateDamage> stats = statsRepository.queryMentalStateAverageDamage();
        List<MentalStateDamage> result = stats.collectList().block();

        assertThat(result).isNotEmpty();
        assertThat(result).size().isEqualTo(2);
        assertThat(result.get(0).mentalState()).isIn("sober", "high");
        assertThat(result.get(1).mentalState()).isIn("sober", "high");

        logger.info(result.get(0).toString());
        logger.info(result.get(1).toString());
    }

    @AfterAll
    public static void tearDown(){
        mongoDBContainer.stop();
    }

}

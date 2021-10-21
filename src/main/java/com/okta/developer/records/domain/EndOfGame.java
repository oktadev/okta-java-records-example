package com.okta.developer.records.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Document(collection = "stats")
public record EndOfGame(String id, LocalDate date, LocalTime timeOfDay,
                        String mentalState, Integer damageTaken,
                        Integer damageToPlayers, Integer damageToStructures) {

    public EndOfGame {
        Objects.requireNonNull(date);
        Objects.requireNonNull(timeOfDay);
        Objects.requireNonNull(mentalState);
        Objects.requireNonNull(damageTaken);
        Objects.requireNonNull(damageToPlayers);
        Objects.requireNonNull(damageToStructures);
    }
}

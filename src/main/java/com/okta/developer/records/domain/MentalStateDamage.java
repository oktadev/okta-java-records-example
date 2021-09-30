package com.okta.developer.records.domain;

public record MentalStateDamage(String mentalState,
                                Double damageToPlayers,
                                Double damageToStructures,
                                Double damageTaken) {
}

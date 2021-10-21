package com.okta.developer.records.domain;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EndOfGameTest {

    private static final Logger logger = LoggerFactory.getLogger(EndOfGameTest.class);

    private EndOfGame getTestEndOfGame() {

        return new EndOfGame("1", LocalDate.of(2018, 12, 12),
                LocalTime.of(15, 15), "sober",
                10, 10, 10);
    }

    @Test
    public void equalsTest() {
        EndOfGame eog1 = getTestEndOfGame();
        EndOfGame eog2 = getTestEndOfGame();

        assertTrue(eog1.equals(eog2));
        assertEquals(eog1, eog2);
        assertEquals(eog1.hashCode(), eog2.hashCode());
    }

    @Test
    public void toStringTest() {
        EndOfGame eog = getTestEndOfGame();
        logger.info(eog.toString());

        assertEquals("EndOfGame[id=1, date=2018-12-12, timeOfDay=15:15, mentalState=sober, " +
                        "damageTaken=10, damageToPlayers=10, damageToStructures=10]",
                eog.toString());
    }

    @Test
    public void accessorTest() {
        EndOfGame eog = getTestEndOfGame();
        assertEquals("sober", eog.mentalState());
    }

}

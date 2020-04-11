package org.monnet.namescoring.service;

import static org.testng.Assert.assertEquals;

import org.monnet.namescoring.entity.Name;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ScoringServiceFirstNameImplTest {

    private ScoringService scoringService;

    @BeforeClass
    public void setup() {
        this.scoringService = new ScoringServiceFirstNameImpl();
    }

    @Test
    public void testComputeNameScore_ExampleLinda() {
        final Name nameToScore = new Name("LINDA");
        final Integer expectedResult = 40;
        final Integer actualResult = this.scoringService.computeNameScore(nameToScore);

        assertEquals(actualResult, expectedResult);
    }

}
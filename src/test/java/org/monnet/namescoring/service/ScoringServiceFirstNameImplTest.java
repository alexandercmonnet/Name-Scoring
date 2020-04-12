package org.monnet.namescoring.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.monnet.namescoring.entity.CharacterScoreMap;
import org.monnet.namescoring.entity.LinearUpperCaseCharacterScoreMap;
import org.monnet.namescoring.entity.Name;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ScoringServiceFirstNameImplTest {

    private ScoringService scoringServiceFirstNameImpl;
    private CharacterScoreMap linearUpperCaseCharacterScoreMap;

    @BeforeClass
    public void setup() {
        this.linearUpperCaseCharacterScoreMap = new LinearUpperCaseCharacterScoreMap();
        this.scoringServiceFirstNameImpl = new ScoringServiceFirstNameImpl(linearUpperCaseCharacterScoreMap);
    }

    @Test
    public void testComputeNameScore_ExampleLinda() throws Exception {
        final Name nameToScore = new Name("LINDA");
        final Integer expectedResult = 40; // 12 + 9 + 14 + 4 + 1 = 40
        final Integer actualResult = this.scoringServiceFirstNameImpl.computeNameScore(nameToScore);

        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testComputeNameScore_ExampleMary() throws Exception {
        final Name nameToScore = new Name("MARY");
        final Integer expectedResult = 57; // 13 + 1 + 18 + 25 = 57
        final Integer actualResult = this.scoringServiceFirstNameImpl.computeNameScore(nameToScore);

        assertEquals(actualResult, expectedResult);
    }


    @Test
    public void testComputeNameListScore_ExampleList() throws Exception {
        final List<Name> namesToScore = new ArrayList<>(); 
        namesToScore.add(new Name("LINDA"));
        namesToScore.add(new Name("MARY"));

        final Integer expectedResult = 154; //(40 * 1) + (57 * 2) = 154
        final Integer actualResult = this.scoringServiceFirstNameImpl.computeNameListScore(namesToScore);

        assertEquals(actualResult, expectedResult);
    }

    @Test 
    public void testComputeNameScore_ThrowsErrorOnUnmappedValue() {
        final Name nameToScore = new Name("LINDA!@");

        assertThrows(() -> this.scoringServiceFirstNameImpl.computeNameScore(nameToScore));
    }

}
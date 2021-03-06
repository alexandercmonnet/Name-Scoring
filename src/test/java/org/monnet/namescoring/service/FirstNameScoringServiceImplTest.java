package org.monnet.namescoring.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.monnet.namescoring.entity.Name;
import org.monnet.namescoring.pointmap.CharacterScoreMap;
import org.monnet.namescoring.pointmap.LinearUpperCaseLetterScoreMap;
import org.monnet.namescoring.service.implementation.FirstNameScoringServiceImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FirstNameScoringServiceImplTest {

    private ScoringService scoringServiceFirstNameImpl;
    private CharacterScoreMap linearUpperCaseCharacterScoreMap;

    @BeforeClass
    public void setup() {
        this.linearUpperCaseCharacterScoreMap = new LinearUpperCaseLetterScoreMap();
        this.scoringServiceFirstNameImpl = new FirstNameScoringServiceImpl(linearUpperCaseCharacterScoreMap);
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
    public void testComputeNameListScore_SimpleList() throws Exception {
        final List<Name> namesToScore = new ArrayList<>(); 
        namesToScore.add(new Name("LINDA"));
        namesToScore.add(new Name("MARY"));

        final Integer expectedResult = 154; //(40 * 1) + (57 * 2) = 137
        final Integer actualResult = this.scoringServiceFirstNameImpl.computeNameListScore(namesToScore);

        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testComputeNameListScore_WithAscSortedSuppliedExampleList() throws Exception {
        final List<Name> namesToScore = new ArrayList<>(); 
        namesToScore.add(new Name("BARBARA"));
        namesToScore.add(new Name("HAI"));
        namesToScore.add(new Name("JERE"));
        namesToScore.add(new Name("LINDA"));
        namesToScore.add(new Name("LYNWOOD"));
        namesToScore.add(new Name("MARY"));
        namesToScore.add(new Name("PATRICIA"));
        namesToScore.add(new Name("SHON"));
        namesToScore.add(new Name("VINCENZO"));

        final Integer expectedResult = 3194;
        final Integer actualResult = this.scoringServiceFirstNameImpl.computeNameListScore(namesToScore);

        assertEquals(actualResult, expectedResult);
    }

    @Test 
    public void testComputeNameScore_ThrowsErrorOnUnmappedValue() {
        final Name nameToScore = new Name("LINDA!@");
        assertThrows(() -> this.scoringServiceFirstNameImpl.computeNameScore(nameToScore));
    }
}

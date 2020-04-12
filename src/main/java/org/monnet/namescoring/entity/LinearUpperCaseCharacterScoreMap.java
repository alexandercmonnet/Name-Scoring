package org.monnet.namescoring.entity;

import java.util.HashMap;

/**
 * The character to point-value lookup implementation for only upper-case alphabetical letters in a linear
 *  point system starting with A at 1 point and ending with Z at 26 points.
 */
public class LinearUpperCaseCharacterScoreMap extends CharacterScoreMap {

    public LinearUpperCaseCharacterScoreMap() {

        this.characterScoreLookupMap = new HashMap<>();
        characterScoreLookupMap.put("A", 1);
        characterScoreLookupMap.put("B", 2);
        characterScoreLookupMap.put("C", 3);
        characterScoreLookupMap.put("D", 4);
        characterScoreLookupMap.put("E", 5);
        characterScoreLookupMap.put("F", 6);
        characterScoreLookupMap.put("G", 7);
        characterScoreLookupMap.put("H", 8);
        characterScoreLookupMap.put("I", 9);
        characterScoreLookupMap.put("J", 10);
        characterScoreLookupMap.put("K", 11);
        characterScoreLookupMap.put("L", 12);
        characterScoreLookupMap.put("M", 13);
        characterScoreLookupMap.put("N", 14);
        characterScoreLookupMap.put("O", 15);
        characterScoreLookupMap.put("P", 16);
        characterScoreLookupMap.put("Q", 17);
        characterScoreLookupMap.put("R", 18);
        characterScoreLookupMap.put("S", 19);
        characterScoreLookupMap.put("T", 20);
        characterScoreLookupMap.put("U", 21);
        characterScoreLookupMap.put("V", 22);
        characterScoreLookupMap.put("W", 23);
        characterScoreLookupMap.put("X", 24);
        characterScoreLookupMap.put("Y", 25);
        characterScoreLookupMap.put("Z", 26);
    }
}
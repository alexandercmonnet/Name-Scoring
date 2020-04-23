package org.monnet.namescoring.pointmap;

import java.util.HashMap;

/**
 * The character to point-value lookup implementation for only upper-case alphabetical letters in a linear
 *  point system starting with A at 1 point and ending with Z at 26 points.
 */
public class LinearUpperCaseLetterScoreMap extends CharacterScoreMap {

    private String[] characters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public LinearUpperCaseLetterScoreMap() {

        this.characterScoreLookupMap = new HashMap<>();

        Integer characterPointValue = 1;
        for (String character : this.characters) {
            characterScoreLookupMap.put(character, characterPointValue);
            characterPointValue++;
        }
    }
}
package org.monnet.namescoring.pointmap;

import java.util.HashMap;

/**
 * The character to point-value lookup implementation for only upper-case alphabetical letters in a linear
 *  point system starting with a at 1 point and ending with z at 26 points.
 */
public class LinearLowerCaseLetterScoreMap extends CharacterScoreMap {

    private String[] characters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    public LinearLowerCaseLetterScoreMap() {

        this.characterScoreLookupMap = new HashMap<>();

        Integer characterPointValue = 1;
        for (String character : this.characters) {
            characterScoreLookupMap.put(character, characterPointValue);
            characterPointValue++;
        }
    }
}
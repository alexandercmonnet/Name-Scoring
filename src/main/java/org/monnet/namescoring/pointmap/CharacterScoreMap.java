package org.monnet.namescoring.pointmap;

import java.util.Map;
import java.util.Optional;

/**
 * The abstract implementation for all character to point-value lookup implementations.
 */
public abstract class CharacterScoreMap {

    protected Map<String, Integer> characterScoreLookupMap;

    /**
     * Finds and returns the integer value for the character passed in. If the character
     *  is not in stored values, an empty optional is returned.
     * @param character The character to lookup
     * @return Optional score of the character
     */
    public final Optional<Integer> getCharacterScore(char character) {
        String characterAsString = String.valueOf(character);
        return this.getCharacterScore(characterAsString);
    }

    /**
     * Finds and returns the integer value for the character passed in. If the character
     *  is not in stored values, an empty optional is returned.
     * @param character The character to lookup
     * @return Optional score of the character
     */
    public final Optional<Integer> getCharacterScore(String character) {
        return Optional.ofNullable(this.characterScoreLookupMap.get(character));
    }
}
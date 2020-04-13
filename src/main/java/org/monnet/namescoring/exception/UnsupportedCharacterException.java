package org.monnet.namescoring.exception;

import org.monnet.namescoring.entity.CharacterScoreMap;

/**
 * This exception expresses that the application was given an unsupported character to score. Either
 *  implement the character in a score map {@link CharacterScoreMap} or alert the user to the un-supported character. 
 */
public class UnsupportedCharacterException extends Exception {

    private static final long serialVersionUID = 4509242987139994938L;
    
    public UnsupportedCharacterException(char unsupportedCharacter) {
        super("The following character is not supported: " + unsupportedCharacter);
    }
}
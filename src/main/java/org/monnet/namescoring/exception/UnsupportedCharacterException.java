package org.monnet.namescoring.exception;

import lombok.Getter;

/**
 * This exception expresses that the scoring service was given an unsupported character
 *  and that the input is not valid. 
 */
@Getter
public class UnsupportedCharacterException extends Exception {

    private static final long serialVersionUID = 4509242987139994938L;
    
	private String unsupportedCharacter;

    public UnsupportedCharacterException(char unsupportedCharacter) {
        super("The following character is not supported: " + unsupportedCharacter);
    }
}
package org.monnet.namescoring.service;

import java.util.List;

import org.monnet.namescoring.entity.Name;
import org.monnet.namescoring.exception.UnsupportedCharacterException;

/**
 * The NameScoringService interface should be implemented by any class that
 *  will be used to provide a score based on a list of names. 
 * 
 * This interface's purpose is to provide a consistent method signature for various implementations of 
 *  scoring algorithms that can be used to score a list of {@link Name} objects. Such algorithms might 
 *  score {@link Name} objects based on particular member fields such as {@link Name#getFirstName()} or
 *  by the object's position in the list. 
 */
public interface NameScoringService {

    /**
     * Computes a single name's score based on the contents of the name.
     * @param name the name to compute a score for
     * @return an integer representing the numerical score for the name
     */
    public Integer computeNameScore(Name name) throws UnsupportedCharacterException;
    
    /**
     * Computes a singular score for a list of names.
     * @param namesList a list of names to compute a score for
     * @return an integer representing the numerical score for the list of names
     */
    public Integer computeNameListScore(List<Name> namesList) throws UnsupportedCharacterException;
}
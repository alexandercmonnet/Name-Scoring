package org.monnet.namescoring.service;

import java.util.List;

import org.monnet.namescoring.entity.Name;
import org.monnet.namescoring.exception.UnsupportedCharacterException;

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
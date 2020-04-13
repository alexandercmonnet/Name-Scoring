package org.monnet.namescoring.service.implmentation;

import java.util.List;
import java.util.Optional;

import org.monnet.namescoring.entity.CharacterScoreMap;
import org.monnet.namescoring.entity.Name;
import org.monnet.namescoring.exception.UnsupportedCharacterException;
import org.monnet.namescoring.service.ScoringService;

import lombok.AllArgsConstructor;

/**
 * This service implementation calculates the score for names based on their {@link Name#getFirstName()} value
 * and by multiplying the score for the first name by the object's position in the list (the list starts at 1).
 * This implementation allows for a {@link CharacterScoreMap} object to be passed in to provide a score value to 
 * each letter in the first name field.
 */
@AllArgsConstructor
public class FirstNameScoringServiceImpl implements ScoringService {

    /**
     * A mapping of characters to numerical values
     */
    private CharacterScoreMap characterPointMap;

	@Override
	public Integer computeNameScore(Name name) throws UnsupportedCharacterException {

        String firstName = name.getFirstName();
        Integer runningTally = 0;

        for (char character : firstName.toCharArray()){
            Optional<Integer> characterScore = this.characterPointMap.getCharacterScore(character);
            if(characterScore.isPresent()) {
                runningTally += characterScore.get();
            } else {
                throw new UnsupportedCharacterException(character);
            }
        }
        
        return runningTally;
	}

	@Override
	public Integer computeNameListScore(List<Name> namesList) throws UnsupportedCharacterException {

        Integer runningTally = 0;
        int iteratorPosition = 1;
        for(Name name : namesList){
            runningTally += this.computeNameScore(name) * iteratorPosition;
            iteratorPosition++;
        }
        
		return runningTally;
	}

    

}

package org.monnet.namescoring.service.implmentation;

import java.util.List;
import java.util.Optional;

import org.monnet.namescoring.entity.CharacterScoreMap;
import org.monnet.namescoring.entity.Name;
import org.monnet.namescoring.exception.UnsupportedCharacterException;
import org.monnet.namescoring.service.NameScoringService;

/**
 * This service implementation calculates the score for names based on their {@link Name#getFirstName()} value
 */
public class FirstNameScoringServiceImpl implements NameScoringService {

    /**
     * A mapping of character to numerical value
     */
    private CharacterScoreMap characterPointMap;

    public FirstNameScoringServiceImpl(CharacterScoreMap characterPointMap) {
        this.characterPointMap = characterPointMap;
    }

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

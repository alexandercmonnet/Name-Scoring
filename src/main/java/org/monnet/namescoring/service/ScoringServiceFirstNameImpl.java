package org.monnet.namescoring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.monnet.namescoring.entity.Name;

public class ScoringServiceFirstNameImpl implements ScoringService {

    Map<String, Integer> letterScoreMap;

    public ScoringServiceFirstNameImpl() {

        //Setting up a default value map of characters to numerical values.
        this.letterScoreMap = new HashMap<>();
        this.letterScoreMap.put("A", 1);
        this.letterScoreMap.put("B", 2);
        this.letterScoreMap.put("C", 3);
        this.letterScoreMap.put("D", 4);
        this.letterScoreMap.put("E", 5);
        this.letterScoreMap.put("F", 6);
        this.letterScoreMap.put("G", 7);
        this.letterScoreMap.put("H", 8);
        this.letterScoreMap.put("I", 9);
        this.letterScoreMap.put("J", 10);
        this.letterScoreMap.put("K", 11);
        this.letterScoreMap.put("L", 12);
        this.letterScoreMap.put("M", 13);
        this.letterScoreMap.put("N", 14);
        this.letterScoreMap.put("O", 15);
        this.letterScoreMap.put("P", 16);
        this.letterScoreMap.put("Q", 17);
        this.letterScoreMap.put("R", 18);
        this.letterScoreMap.put("S", 19);
        this.letterScoreMap.put("T", 20);
        this.letterScoreMap.put("U", 21);
        this.letterScoreMap.put("V", 22);
        this.letterScoreMap.put("W", 23);
        this.letterScoreMap.put("X", 24);
        this.letterScoreMap.put("Y", 25);
        this.letterScoreMap.put("Z", 26);
    }

    public ScoringServiceFirstNameImpl(Map<String, Integer> letterScoreMap) {
        this.letterScoreMap = letterScoreMap;
    }

	@Override
	public Integer computeNameScore(Name name) {

        String firstName = name.getFirstName();
        Integer runningTally = 0;

        for(int i = 0; i < firstName.length(); i++) {
            runningTally += this.letterScoreMap.computeIfAbsent(firstName.substring(i, i + 1), key -> { throw new  RuntimeException("Unsupported Key: " + key); });
        }
        
		return runningTally;
	}

	@Override
	public Integer computeNameListScore(List<Name> namesList) {
		return 0;
	}

    

}

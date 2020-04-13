package org.monnet.namescoring;

import java.util.List;

import org.monnet.namescoring.entity.Name;
import org.monnet.namescoring.exception.UnsupportedCharacterException;
import org.monnet.namescoring.service.NameScoringService;
import org.monnet.namescoring.service.NameSortingService;

import lombok.AllArgsConstructor;

/**
 * This class acts as the programmatic entry point to the Name Scoring
 * application and can be consumed by CLI, Web Service, or other such
 * entry points.
 */
@AllArgsConstructor
public class NameScoring {

    private NameScoringService scoringService;
    private NameSortingService sortingService;

    public Integer calculateScore(List<Name> namesList) throws UnsupportedCharacterException {
        this.sortingService.sortNameList(namesList);
        return this.scoringService.computeNameListScore(namesList);
    }
}


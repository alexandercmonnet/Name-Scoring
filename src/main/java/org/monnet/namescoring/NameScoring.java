package org.monnet.namescoring;

import java.util.List;

import org.monnet.namescoring.entity.Name;
import org.monnet.namescoring.exception.UnsupportedCharacterException;
import org.monnet.namescoring.service.ScoringService;
import org.monnet.namescoring.service.SortingService;

import lombok.AllArgsConstructor;

/**
 * This class acts as the programmatic entry point to the Name Scoring
 * application and can be consumed by CLI, Web Service, or other such
 * entry points.
 */
@AllArgsConstructor
public class NameScoring {

    private ScoringService scoringService;
    private SortingService sortingService;

    public Integer calculateScore(List<Name> namesList) throws UnsupportedCharacterException {
        this.sortingService.sortNameList(namesList);
        return this.scoringService.computeNameListScore(namesList);
    }
}


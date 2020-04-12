package org.monnet.namescoring.application;

import java.util.List;

import org.monnet.namescoring.entity.Name;
import org.monnet.namescoring.exception.UnsupportedCharacterException;
import org.monnet.namescoring.service.NameScoringService;
import org.monnet.namescoring.service.NameSortingService;

import lombok.AllArgsConstructor;

/**
 * This class acts as the main driver for the name scoring application by tieing in a
 *  sorting algorithm before calculating the score of the names.
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


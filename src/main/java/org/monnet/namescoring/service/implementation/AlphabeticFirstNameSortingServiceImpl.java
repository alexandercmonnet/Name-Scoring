package org.monnet.namescoring.service.implementation;

import java.util.Collections;
import java.util.List;

import org.monnet.namescoring.entity.Name;
import org.monnet.namescoring.service.SortingService;

/**
 * Name sorting service that implements Collections.sort to sort the name
 *  list in alphabetic order based on the {@link Name#getFirstName()} value.
 */
public class AlphabeticFirstNameSortingServiceImpl implements SortingService {

    @Override
    public void sortNameList(List<Name> namesList) {
        Collections.sort(namesList, (name1, name2) -> name1.getFirstName().compareTo(name2.getFirstName()));
    }
}
package org.monnet.namescoring.service;

import java.util.List;

import org.monnet.namescoring.entity.Name;

public interface NameSortingService {

    /**
     * Sorts the name list based on the interface implementation.
     * @param namesList a list of names to sort.
     */
    public void sortNameList(List<Name> namesList);
}
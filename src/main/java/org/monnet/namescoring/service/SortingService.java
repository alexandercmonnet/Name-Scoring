package org.monnet.namescoring.service;

import java.util.List;

import org.monnet.namescoring.entity.Name;

/**
 * The SortingService interface should be implemented by any class that
 *  will be used to sort a list of names. 
 * 
 * This interface's purpose is to provide a consistent method signature for various implementations of 
 *  sorting algorithms that can be used to sort a list of {@link Name} objects such as algorithms that 
 *  implement strategies like merge sort, quick sort, tim sort, etc or algorithms that seek to sort 
 *  {@link Name} objects based on various member fields such as {@link Name#getFirstName()} 
 */
public interface SortingService {

    /**
     * Sorts the name list based on the interface implementation.
     * @param namesList a list of names to sort.
     */
    public void sortNameList(List<Name> namesList);
}
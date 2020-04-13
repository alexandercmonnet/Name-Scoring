package org.monnet.namescoring.service;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.monnet.namescoring.entity.Name;
import org.monnet.namescoring.service.implmentation.AlphabeticFirstNameSortingServiceImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AlphabeticFirstNameSortingServiceImplTest {

    private NameSortingService nameSortingService;

    @BeforeClass
    public void setup() {
        this.nameSortingService = new AlphabeticFirstNameSortingServiceImpl();
    }

    @Test
    public void testComputeNameListScore_TwoNamesSwap() throws Exception {
        final List<Name> namesToSort = new ArrayList<>(); 
        namesToSort.add(new Name("MARY"));
        namesToSort.add(new Name("LINDA"));
        
        final List<Name> expectedNameList = new ArrayList<>(); 
        expectedNameList.add(new Name("LINDA"));
        expectedNameList.add(new Name("MARY"));

        nameSortingService.sortNameList(namesToSort);
        assertEquals(namesToSort, expectedNameList);
    }

    @Test
    public void testComputeNameListScore_TwoNamesSameOrder() throws Exception {
        final List<Name> namesToSort = new ArrayList<>(); 
        namesToSort.add(new Name("LINDA"));
        namesToSort.add(new Name("MARY"));
        
        final List<Name> expectedNameList = new ArrayList<>(); 
        expectedNameList.add(new Name("LINDA"));
        expectedNameList.add(new Name("MARY"));

        nameSortingService.sortNameList(namesToSort);
        assertEquals(namesToSort, expectedNameList);
    }

    @Test
    public void testComputeNameListScore_ExampleList() throws Exception {
        final List<Name> namesToSort = new ArrayList<>(); 
        namesToSort.add(new Name("MARY"));  
        namesToSort.add(new Name("PATRICIA"));
        namesToSort.add(new Name("LINDA"));
        namesToSort.add(new Name("BARBARA"));
        namesToSort.add(new Name("VINCENZO"));
        namesToSort.add(new Name("SHON"));
        namesToSort.add(new Name("LYNWOOD"));
        namesToSort.add(new Name("JERE"));
        namesToSort.add(new Name("HAI"));
        
        final List<Name> expectedNameList = new ArrayList<>(); 
        expectedNameList.add(new Name("BARBARA"));
        expectedNameList.add(new Name("HAI"));
        expectedNameList.add(new Name("JERE"));
        expectedNameList.add(new Name("LINDA"));
        expectedNameList.add(new Name("LYNWOOD"));
        expectedNameList.add(new Name("MARY"));
        expectedNameList.add(new Name("PATRICIA"));
        expectedNameList.add(new Name("SHON"));
        expectedNameList.add(new Name("VINCENZO"));

        nameSortingService.sortNameList(namesToSort);
        assertEquals(namesToSort, expectedNameList);
    }
}

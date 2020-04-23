package org.monnet.namescoring.service;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.monnet.namescoring.entity.Name;
import org.monnet.namescoring.service.implementation.AlphabeticLastNameSortingServiceImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AlphabeticLastNameSortingServiceImplTest {

    private SortingService nameSortingService;

    @BeforeClass
    public void setup() {
        this.nameSortingService = new AlphabeticLastNameSortingServiceImpl();
    }

    @Test
    public void testComputeNameListScore_TwoNamesSwap() throws Exception {
        final List<Name> namesToSort = new ArrayList<>(); 
        namesToSort.add(Name.builder().lastName("MARY").build());
        namesToSort.add(Name.builder().lastName("LINDA").build());
        
        final List<Name> expectedNameList = new ArrayList<>(); 
        expectedNameList.add(Name.builder().lastName("LINDA").build());
        expectedNameList.add(Name.builder().lastName("MARY").build());

        nameSortingService.sortNameList(namesToSort);
        assertEquals(namesToSort, expectedNameList);
    }

    @Test
    public void testComputeNameListScore_TwoNamesSameOrder() throws Exception {
        final List<Name> namesToSort = new ArrayList<>(); 
        namesToSort.add(Name.builder().lastName("LINDA").build());
        namesToSort.add(Name.builder().lastName("MARY").build());
        
        final List<Name> expectedNameList = new ArrayList<>(); 
        expectedNameList.add(Name.builder().lastName("LINDA").build());
        expectedNameList.add(Name.builder().lastName("MARY").build());

        nameSortingService.sortNameList(namesToSort);
        assertEquals(namesToSort, expectedNameList);
    }

    @Test
    public void testComputeNameListScore_ExampleList() throws Exception {
        final List<Name> namesToSort = new ArrayList<>(); 
        namesToSort.add(Name.builder().lastName("MARY").build());  
        namesToSort.add(Name.builder().lastName("PATRICIA").build());
        namesToSort.add(Name.builder().lastName("LINDA").build());
        namesToSort.add(Name.builder().lastName("BARBARA").build());
        namesToSort.add(Name.builder().lastName("VINCENZO").build());
        namesToSort.add(Name.builder().lastName("SHON").build());
        namesToSort.add(Name.builder().lastName("LYNWOOD").build());
        namesToSort.add(Name.builder().lastName("JERE").build());
        namesToSort.add(Name.builder().lastName("HAI").build());
        
        final List<Name> expectedNameList = new ArrayList<>(); 
        expectedNameList.add(Name.builder().lastName("BARBARA").build());
        expectedNameList.add(Name.builder().lastName("HAI").build());
        expectedNameList.add(Name.builder().lastName("JERE").build());
        expectedNameList.add(Name.builder().lastName("LINDA").build());
        expectedNameList.add(Name.builder().lastName("LYNWOOD").build());
        expectedNameList.add(Name.builder().lastName("MARY").build());
        expectedNameList.add(Name.builder().lastName("PATRICIA").build());
        expectedNameList.add(Name.builder().lastName("SHON").build());
        expectedNameList.add(Name.builder().lastName("VINCENZO").build());

        nameSortingService.sortNameList(namesToSort);
        assertEquals(namesToSort, expectedNameList);
    }
}

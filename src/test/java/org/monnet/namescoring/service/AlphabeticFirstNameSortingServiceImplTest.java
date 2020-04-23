package org.monnet.namescoring.service;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.monnet.namescoring.entity.Name;
import org.monnet.namescoring.service.implementation.AlphabeticFirstNameSortingServiceImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AlphabeticFirstNameSortingServiceImplTest {

    private SortingService nameSortingService;

    @BeforeClass
    public void setup() {
        this.nameSortingService = new AlphabeticFirstNameSortingServiceImpl();
    }

    @Test
    public void testComputeNameListScore_TwoNamesSwap() throws Exception {
        final List<Name> namesToSort = new ArrayList<>(); 
        namesToSort.add(Name.builder().firstName("MARY").build());
        namesToSort.add(Name.builder().firstName("LINDA").build());
        
        final List<Name> expectedNameList = new ArrayList<>(); 
        expectedNameList.add(Name.builder().firstName("LINDA").build());
        expectedNameList.add(Name.builder().firstName("MARY").build());

        nameSortingService.sortNameList(namesToSort);
        assertEquals(namesToSort, expectedNameList);
    }

    @Test
    public void testComputeNameListScore_TwoNamesSameOrder() throws Exception {
        final List<Name> namesToSort = new ArrayList<>(); 
        namesToSort.add(Name.builder().firstName("LINDA").build());
        namesToSort.add(Name.builder().firstName("MARY").build());
        
        final List<Name> expectedNameList = new ArrayList<>(); 
        expectedNameList.add(Name.builder().firstName("LINDA").build());
        expectedNameList.add(Name.builder().firstName("MARY").build());

        nameSortingService.sortNameList(namesToSort);
        assertEquals(namesToSort, expectedNameList);
    }

    @Test
    public void testComputeNameListScore_ExampleList() throws Exception {
        final List<Name> namesToSort = new ArrayList<>(); 
        namesToSort.add(Name.builder().firstName("MARY").build());  
        namesToSort.add(Name.builder().firstName("PATRICIA").build());
        namesToSort.add(Name.builder().firstName("LINDA").build());
        namesToSort.add(Name.builder().firstName("BARBARA").build());
        namesToSort.add(Name.builder().firstName("VINCENZO").build());
        namesToSort.add(Name.builder().firstName("SHON").build());
        namesToSort.add(Name.builder().firstName("LYNWOOD").build());
        namesToSort.add(Name.builder().firstName("JERE").build());
        namesToSort.add(Name.builder().firstName("HAI").build());
        
        final List<Name> expectedNameList = new ArrayList<>(); 
        expectedNameList.add(Name.builder().firstName("BARBARA").build());
        expectedNameList.add(Name.builder().firstName("HAI").build());
        expectedNameList.add(Name.builder().firstName("JERE").build());
        expectedNameList.add(Name.builder().firstName("LINDA").build());
        expectedNameList.add(Name.builder().firstName("LYNWOOD").build());
        expectedNameList.add(Name.builder().firstName("MARY").build());
        expectedNameList.add(Name.builder().firstName("PATRICIA").build());
        expectedNameList.add(Name.builder().firstName("SHON").build());
        expectedNameList.add(Name.builder().firstName("VINCENZO").build());

        nameSortingService.sortNameList(namesToSort);
        assertEquals(namesToSort, expectedNameList);
    }
}

package org.aj.lists.filter.model;

import org.aj.database.common.IDataTable;
import org.aj.lists.api.FilterColumnsEnum;
import org.aj.lists.api.IFilterDataColumnNames;
import org.aj.lists.api.IFilterEquipmentDataModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.mockito.Mockito;

import java.util.*;
import java.util.stream.Stream;

public class FilterModelValidateFilterColumnDataTest {
    private final IDataTable dataTable;
    private final IFilterDataColumnNames filterDataColumnNames;
    private final DataSourceSetupForTest dataSourceSetupForTest;
    private final FilterDataColumnNamesSetupForTest filterDataColumnNamesSetupForTest;

    public FilterModelValidateFilterColumnDataTest() {
        dataSourceSetupForTest = new DataSourceSetupForTest();
        filterDataColumnNamesSetupForTest = new FilterDataColumnNamesSetupForTest();

        dataTable = Mockito.mock(IDataTable.class);
        filterDataColumnNames = Mockito.mock(IFilterDataColumnNames.class);

        setUp();
    }

    private void setUp() {
        dataSourceSetupForTest.setupDataSource(dataTable);
        filterDataColumnNamesSetupForTest.setupFilterDataColumnNames(filterDataColumnNames);
    }

    @TestFactory
    Stream<DynamicTest> filterTestCases() {
        List<FilterModelValidateFilterColumnDataTestHelper> inputList =
                Arrays.asList(
                        new FilterModelValidateFilterColumnDataTestHelper(
                                "Aircraft", null, null,
                                2, 3, 24,
                                "Aircraft", "Aircraft",
                                "Fighters", "Transport",
                                "AN-12", "UNKNOWN"),
                        new FilterModelValidateFilterColumnDataTestHelper(
                                null, null, null,
                                3, 5, 34,
                                "ADA", "Aircraft",
                                "G/A", "Transport",
                                "AN-12", "UNKNOWN"),
                        new FilterModelValidateFilterColumnDataTestHelper(
                                null, "SAM", null,
                                2, 1, 24,
                                "ADA", "ADA",
                                "SAM", "SAM",
                                "SA-2", "Star Streak")
                );

        return inputList.stream().
                map(validateFilter -> DynamicTest.dynamicTest(
                        "familyFilter: " + validateFilter.getFamilyFilterInput(),
                        () -> {
                            IFilterEquipmentDataModel filterModel = new FilterEquipmentDataModel(dataTable,
                                    filterDataColumnNames);
                            Map<FilterColumnsEnum, Optional<List<String>>> filterResult =
                                    filterModel.applyFilterValues(validateFilter.getFamilyFilterInput(),
                                            validateFilter.getGroupFilterInput(),
                                            validateFilter.getTypeFilterInput());
                            Optional<List<String>> optional = filterResult.get(FilterColumnsEnum.family);
                            List<String> FilteredColumnsData = optional.orElseGet(ArrayList::new);

                            Assertions.assertEquals(validateFilter.getFamilyFilterReturnListLength(),
                                    FilteredColumnsData.size());
                        }
                ));
    }

}

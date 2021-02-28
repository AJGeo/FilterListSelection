package org.aj.lists.filter.model.filtered_data;

import org.aj.database.common.IDataTable;
import org.aj.lists.api.IFilterDataColumnNames;
import org.aj.lists.api.IFilterEquipmentDataModel;
import org.aj.lists.filter.model.FilterEquipmentDataModel;
import org.aj.lists.filter.model.create.FilterDataColumnNamesSetupForTest;
import org.aj.lists.filter.model.data.DataSourceSetupForTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class FilterModelValidateFilterDataTest {
    private final IDataTable dataTable;
    private final IFilterDataColumnNames filterDataColumnNames;
    private final DataSourceSetupForTest dataSourceSetupForTest;
    private final FilterDataColumnNamesSetupForTest filterDataColumnNamesSetupForTest;

    public FilterModelValidateFilterDataTest() {
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
    Stream<DynamicTest> filterFamilyFilteredDataValidateTestCases() {
        FilteredDataTestsInput filteredDataTestsInput = new FilteredDataTestsInput();

        List<FilteredDataTestParameters> inputList = filteredDataTestsInput.getTestParameters();

        Stream<DynamicTest> streamReturn = Stream.concat(
                getFilterFamilyLengthTestStream(inputList),
                getFilterFamilyFirstRecordTestStream(inputList));

        return Stream.concat(streamReturn, filterFamilyLestRecordTestStream(inputList));
    }

    private Stream<DynamicTest> getFilterFamilyLengthTestStream(
            List<FilteredDataTestParameters> inputList) {
        return inputList.stream().
                map(filteredDataTestParameters -> DynamicTest.dynamicTest(
                        "Filtered data Count; " +
                                getAppliedFilterString(filteredDataTestParameters),
                        () -> {
                            IFilterEquipmentDataModel filterModel = getFilterEquipmentDataModel();

                            filterModel.applyFilterValues(
                                    filteredDataTestParameters.getFamilyFilterInput(),
                                    filteredDataTestParameters.getGroupFilterInput(),
                                    filteredDataTestParameters.getTypeFilterInput());

                            List<List<String>> filteredData = filterModel.getFilteredData();

                            Assertions.assertEquals(filteredDataTestParameters.getFilteredRecordsCount(),
                                    filteredData.size());
                        }
                ));
    }

    private Stream<DynamicTest> getFilterFamilyFirstRecordTestStream(
            List<FilteredDataTestParameters> inputList) {
        return inputList.stream().
                map(filteredDataTestParameters -> DynamicTest.dynamicTest(
                        "Filtered data First Record; " +
                                getAppliedFilterString(filteredDataTestParameters),
                        () -> {
                            IFilterEquipmentDataModel filterModel = getFilterEquipmentDataModel();

                            filterModel.applyFilterValues(
                                    filteredDataTestParameters.getFamilyFilterInput(),
                                    filteredDataTestParameters.getGroupFilterInput(),
                                    filteredDataTestParameters.getTypeFilterInput());

                            List<List<String>> filteredData = filterModel.getFilteredData();

                            Optional<List<String>> firstRecord = filteredData.stream().findFirst();

                            Assertions.assertEquals(filteredDataTestParameters.getFirstRecord(),
                                    firstRecord.get());
                        }
                ));
    }

    private Stream<DynamicTest> filterFamilyLestRecordTestStream(List<FilteredDataTestParameters> inputList) {
        return inputList.stream().
                map(filteredDataTestParameters -> DynamicTest.dynamicTest(
                        "Filtered data Last Record; " +
                                getAppliedFilterString(filteredDataTestParameters),
                        () -> {
                            IFilterEquipmentDataModel filterModel = getFilterEquipmentDataModel();

                            filterModel.applyFilterValues(
                                    filteredDataTestParameters.getFamilyFilterInput(),
                                    filteredDataTestParameters.getGroupFilterInput(),
                                    filteredDataTestParameters.getTypeFilterInput());

                            List<List<String>> filteredData = filterModel.getFilteredData();

                            Optional<List<String>> lastRecord = Optional.ofNullable(
                                    filteredData.get(filteredData.size() - 1));

                            Assertions.assertEquals(filteredDataTestParameters.getLastRecord(),
                                    lastRecord.get());
                        }
                ));
    }

    private String getAppliedFilterString(FilteredDataTestParameters filteredDataTestParameters) {
        return "FILTERS: " +
                filteredDataTestParameters.getFamilyFilterInput() + ", " +
                filteredDataTestParameters.getGroupFilterInput() + ", " +
                filteredDataTestParameters.getTypeFilterInput();
    }

    private IFilterEquipmentDataModel getFilterEquipmentDataModel() {
        return new FilterEquipmentDataModel(dataTable, filterDataColumnNames);
    }


}

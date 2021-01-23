package org.aj.lists.filter.model.columndata;

import org.aj.database.common.IDataTable;
import org.aj.lists.api.FilterColumnsEnum;
import org.aj.lists.api.IFilterDataColumnNames;
import org.aj.lists.api.IFilterEquipmentDataModel;
import org.aj.lists.filter.model.FilterEquipmentDataModel;
import org.aj.lists.filter.model.create.FilterDataColumnNamesSetupForTest;
import org.aj.lists.filter.model.data.DataSourceSetupForTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    Stream<DynamicTest> filterFamilyColumnValidateTestCases() {
        FilterColumnDataTestsInput filterColumnDataTestsInput = new FilterColumnDataTestsInput();
        List<FilterColumnDataTestParameters> inputList = filterColumnDataTestsInput.getTestParameters();

        Stream<DynamicTest> streamReturn = Stream.concat(
                filterLengthTestStream(inputList, FilterColumnsEnum.family),
                filterFirstValueTestStream(inputList, FilterColumnsEnum.family));

        return Stream.concat(streamReturn,
                filterLastValueTestStream(inputList, FilterColumnsEnum.family));
    }

    @TestFactory
    Stream<DynamicTest> filterGroupColumnValidateTestCases() {
        FilterColumnDataTestsInput filterColumnDataTestsInput = new FilterColumnDataTestsInput();
        List<FilterColumnDataTestParameters> inputList = filterColumnDataTestsInput.getTestParameters();

        Stream<DynamicTest> streamReturn = Stream.concat(
                filterLengthTestStream(inputList, FilterColumnsEnum.group),
                filterFirstValueTestStream(inputList, FilterColumnsEnum.group));

        return Stream.concat(streamReturn,
                filterLastValueTestStream(inputList, FilterColumnsEnum.group));
    }

    @TestFactory
    Stream<DynamicTest> filterTypeColumnValidateTestCases() {
        FilterColumnDataTestsInput filterColumnDataTestsInput = new FilterColumnDataTestsInput();
        List<FilterColumnDataTestParameters> inputList = filterColumnDataTestsInput.getTestParameters();

        Stream<DynamicTest> streamReturn = Stream.concat(
                filterLengthTestStream(inputList, FilterColumnsEnum.type),
                filterFirstValueTestStream(inputList, FilterColumnsEnum.type));

        return Stream.concat(streamReturn,
                filterLastValueTestStream(inputList, FilterColumnsEnum.type));
    }

    private Stream<DynamicTest> filterLengthTestStream(List<FilterColumnDataTestParameters> inputList,
                                                       FilterColumnsEnum filterColumnsEnum) {
        return inputList.stream().
                map(filterColumnDataTestParameters -> DynamicTest.dynamicTest(
                        filterColumnsEnum.name().toUpperCase() +
                                " Column Length; " +
                                getAppliedFilterString(filterColumnDataTestParameters),
                        () -> {
                            IFilterEquipmentDataModel filterModel = getFilterEquipmentDataModel();

                            Map<FilterColumnsEnum, Optional<List<String>>> filterResult =
                                    filterModel.applyFilterValues(
                                            filterColumnDataTestParameters.getFamilyFilterInput(),
                                            filterColumnDataTestParameters.getGroupFilterInput(),
                                            filterColumnDataTestParameters.getTypeFilterInput());

                            Optional<List<String>> optional = filterResult.get(filterColumnsEnum);
                            List<String> FilteredColumnsData = optional.orElseGet(ArrayList::new);

                            Assertions.assertEquals(
                                    getExpectedListLength(filterColumnsEnum, filterColumnDataTestParameters),
                                    FilteredColumnsData.size());
                        }
                ));
    }

    private int getExpectedListLength(FilterColumnsEnum filterColumnsEnum,
                                      FilterColumnDataTestParameters filterColumnDataTestParameters) {
        switch (filterColumnsEnum) {
            case family:
                return filterColumnDataTestParameters.getFamilyFilterReturnListLength();
            case group:
                return filterColumnDataTestParameters.getGroupFilterReturnListLength();
            default:
                return filterColumnDataTestParameters.getTypeFilterReturnListLength();
        }
    }

    private Stream<DynamicTest> filterFirstValueTestStream(List<FilterColumnDataTestParameters> inputList,
                                                           FilterColumnsEnum filterColumnsEnum) {
        return inputList.stream().
                map(filterColumnDataTestParameters -> DynamicTest.dynamicTest(
                        filterColumnsEnum.name().toUpperCase() +
                                " Column First Value; " +
                                getAppliedFilterString(filterColumnDataTestParameters),
                        () -> {
                            IFilterEquipmentDataModel filterModel = getFilterEquipmentDataModel();

                            Map<FilterColumnsEnum, Optional<List<String>>> filterResult =
                                    filterModel.applyFilterValues(
                                            filterColumnDataTestParameters.getFamilyFilterInput(),
                                            filterColumnDataTestParameters.getGroupFilterInput(),
                                            filterColumnDataTestParameters.getTypeFilterInput());

                            Optional<List<String>> optional = filterResult.get(filterColumnsEnum);
                            List<String> FilteredColumnsData = optional.orElseGet(ArrayList::new);

                            Optional<String> firstValue = FilteredColumnsData.stream().findFirst();

                            Assertions.assertEquals(
                                    getExpectedFirstValue(filterColumnsEnum, filterColumnDataTestParameters),
                                    firstValue.get());
                        }
                ));
    }

    private String getExpectedFirstValue(FilterColumnsEnum filterColumnsEnum,
                                         FilterColumnDataTestParameters filterColumnDataTestParameters) {
        switch (filterColumnsEnum) {
            case family:
                return filterColumnDataTestParameters.getFamilyFilterReturnListFirstValue();
            case group:
                return filterColumnDataTestParameters.getGroupFilterReturnListFirstValue();
            default:
                return filterColumnDataTestParameters.getTypeFilterReturnListFirstValue();
        }
    }

    private Stream<DynamicTest> filterLastValueTestStream(List<FilterColumnDataTestParameters> inputList,
                                                          FilterColumnsEnum filterColumnsEnum) {
        return inputList.stream().
                map(filterColumnDataTestParameters -> DynamicTest.dynamicTest(
                        filterColumnsEnum.name().toUpperCase() +
                                " Column Last Value; " +
                                getAppliedFilterString(filterColumnDataTestParameters),
                        () -> {
                            IFilterEquipmentDataModel filterModel = getFilterEquipmentDataModel();

                            Map<FilterColumnsEnum, Optional<List<String>>> filterResult =
                                    filterModel.applyFilterValues(
                                            filterColumnDataTestParameters.getFamilyFilterInput(),
                                            filterColumnDataTestParameters.getGroupFilterInput(),
                                            filterColumnDataTestParameters.getTypeFilterInput());

                            Optional<List<String>> optional = filterResult.get(filterColumnsEnum);
                            List<String> FilteredColumnsData = optional.orElseGet(ArrayList::new);

                            Optional<String> lastValue = Optional.ofNullable(
                                    FilteredColumnsData.get(FilteredColumnsData.size() - 1));

                            Assertions.assertEquals(
                                    getExpectedLastValue(filterColumnsEnum, filterColumnDataTestParameters),
                                    lastValue.get());
                        }
                ));
    }

    private String getExpectedLastValue(FilterColumnsEnum filterColumnsEnum,
                                        FilterColumnDataTestParameters filterColumnDataTestParameters) {
        switch (filterColumnsEnum) {
            case family:
                return filterColumnDataTestParameters.getFamilyFilterReturnListLastValue();
            case group:
                return filterColumnDataTestParameters.getGroupFilterReturnListLastValue();
            default:
                return filterColumnDataTestParameters.getTypeFilterReturnListLastValue();
        }
    }

    private String getAppliedFilterString(FilterColumnDataTestParameters filterColumnDataTestParameters) {
        return "FILTERS: " +
                filterColumnDataTestParameters.getFamilyFilterInput() + ", " +
                filterColumnDataTestParameters.getGroupFilterInput() + ", " +
                filterColumnDataTestParameters.getTypeFilterInput();
    }

    private IFilterEquipmentDataModel getFilterEquipmentDataModel() {
        return new FilterEquipmentDataModel(dataTable, filterDataColumnNames);
    }
}

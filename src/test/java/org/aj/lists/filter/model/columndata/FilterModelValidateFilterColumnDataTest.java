package org.aj.lists.filter.model.columndata;

import org.aj.database.common.IDataTable;
import org.aj.lists.api.FilterColumnsEnum;
import org.aj.lists.api.IFilterDataColumnNames;
import org.aj.lists.api.IFilterEquipmentDataModel;
import org.aj.lists.filter.model.data.DataSourceSetupForTest;
import org.aj.lists.filter.model.FilterEquipmentDataModel;
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
    Stream<DynamicTest> filterFamilyColumnValidateTestCases() {
        List<ValidateFilterColumnDataTestHelper> inputList = getTestParameters();

        Stream<DynamicTest> filterFamilyLengthTestStream = inputList.stream().
                map(validateFilter -> DynamicTest.dynamicTest(
                        "Family Column Length; Filters: " + validateFilter.getFamilyFilterInput() + ", " +
                                validateFilter.getGroupFilterInput() + ", " +
                                validateFilter.getTypeFilterInput(),
                        () -> {
                            IFilterEquipmentDataModel filterModel = getFilterEquipmentDataModel();

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
        Stream<DynamicTest> filterFamilyFirstValueTestStream = inputList.stream().
                map(validateFilter -> DynamicTest.dynamicTest(
                        "Family Column First Value; Filters: " + validateFilter.getFamilyFilterInput() + ", " +
                                validateFilter.getGroupFilterInput() + ", " +
                                validateFilter.getTypeFilterInput(),
                        () -> {
                            IFilterEquipmentDataModel filterModel = getFilterEquipmentDataModel();

                            Map<FilterColumnsEnum, Optional<List<String>>> filterResult =
                                    filterModel.applyFilterValues(
                                            validateFilter.getFamilyFilterInput(),
                                            validateFilter.getGroupFilterInput(),
                                            validateFilter.getTypeFilterInput());

                            Optional<List<String>> optional = filterResult.get(FilterColumnsEnum.family);
                            List<String> FilteredColumnsData = optional.orElseGet(ArrayList::new);

                            Optional<String> firstValue = FilteredColumnsData.stream().findFirst();

                            Assertions.assertEquals(validateFilter.getFamilyFilterReturnListFirstValue(),
                                    firstValue.get());
                        }
                ));
        Stream<DynamicTest> filterFamilyLastValueTestStream = inputList.stream().
                map(validateFilter -> DynamicTest.dynamicTest(
                        "Family Column Last Value; Filters: " + validateFilter.getFamilyFilterInput() + ", " +
                                validateFilter.getGroupFilterInput() + ", " +
                                validateFilter.getTypeFilterInput(),
                        () -> {
                            IFilterEquipmentDataModel filterModel = getFilterEquipmentDataModel();

                            Map<FilterColumnsEnum, Optional<List<String>>> filterResult =
                                    filterModel.applyFilterValues(
                                            validateFilter.getFamilyFilterInput(),
                                            validateFilter.getGroupFilterInput(),
                                            validateFilter.getTypeFilterInput());

                            Optional<List<String>> optional = filterResult.get(FilterColumnsEnum.family);
                            List<String> FilteredColumnsData = optional.orElseGet(ArrayList::new);

                            Optional<String> lastValue = Optional.ofNullable(
                                    FilteredColumnsData.get(FilteredColumnsData.size() - 1));

                            Assertions.assertEquals(validateFilter.getFamilyFilterReturnListLastValue(),
                                    lastValue.get());
                        }
                ));

        Stream<DynamicTest> streamReturn = Stream.concat(filterFamilyLengthTestStream, filterFamilyFirstValueTestStream);
        streamReturn = Stream.concat(streamReturn, filterFamilyLastValueTestStream);

        return streamReturn;
    }

    @TestFactory
    Stream<DynamicTest> filterGroupColumnValidateTestCases() {
        List<ValidateFilterColumnDataTestHelper> inputList = getTestParameters();

        Stream<DynamicTest> filterGroupLengthTestStream = inputList.stream().
                map(validateFilter -> DynamicTest.dynamicTest(
                        "Group Column Length; Filters: " + validateFilter.getFamilyFilterInput() + ", " +
                                validateFilter.getGroupFilterInput() + ", " +
                                validateFilter.getTypeFilterInput(),
                        () -> {
                            IFilterEquipmentDataModel filterModel = getFilterEquipmentDataModel();

                            Map<FilterColumnsEnum, Optional<List<String>>> filterResult =
                                    filterModel.applyFilterValues(validateFilter.getFamilyFilterInput(),
                                            validateFilter.getGroupFilterInput(),
                                            validateFilter.getTypeFilterInput());

                            Optional<List<String>> optional = filterResult.get(FilterColumnsEnum.group);
                            List<String> FilteredColumnsData = optional.orElseGet(ArrayList::new);

                            Assertions.assertEquals(validateFilter.getGroupFilterReturnListLength(),
                                    FilteredColumnsData.size());
                        }
                ));
        Stream<DynamicTest> filterGroupFirstValueTestStream = inputList.stream().
                map(validateFilter -> DynamicTest.dynamicTest(
                        "Group Column First Value; Filters: " + validateFilter.getFamilyFilterInput() + ", " +
                                validateFilter.getGroupFilterInput() + ", " +
                                validateFilter.getTypeFilterInput(),
                        () -> {
                            IFilterEquipmentDataModel filterModel = getFilterEquipmentDataModel();

                            Map<FilterColumnsEnum, Optional<List<String>>> filterResult =
                                    filterModel.applyFilterValues(
                                            validateFilter.getFamilyFilterInput(),
                                            validateFilter.getGroupFilterInput(),
                                            validateFilter.getTypeFilterInput());

                            Optional<List<String>> optional = filterResult.get(FilterColumnsEnum.group);
                            List<String> FilteredColumnsData = optional.orElseGet(ArrayList::new);

                            Optional<String> firstValue = FilteredColumnsData.stream().findFirst();

                            Assertions.assertEquals(validateFilter.getGroupFilterReturnListFirstValue(),
                                    firstValue.get());
                        }
                ));
        Stream<DynamicTest> filterGroupLastValueTestStream = inputList.stream().
                map(validateFilter -> DynamicTest.dynamicTest(
                        "Group Column Last Value; Filters: " + validateFilter.getFamilyFilterInput() + ", " +
                                validateFilter.getGroupFilterInput() + ", " +
                                validateFilter.getTypeFilterInput(),
                        () -> {
                            IFilterEquipmentDataModel filterModel = getFilterEquipmentDataModel();

                            Map<FilterColumnsEnum, Optional<List<String>>> filterResult =
                                    filterModel.applyFilterValues(
                                            validateFilter.getFamilyFilterInput(),
                                            validateFilter.getGroupFilterInput(),
                                            validateFilter.getTypeFilterInput());

                            Optional<List<String>> optional = filterResult.get(FilterColumnsEnum.group);
                            List<String> FilteredColumnsData = optional.orElseGet(ArrayList::new);

                            Optional<String> lastValue = Optional.ofNullable(
                                    FilteredColumnsData.get(FilteredColumnsData.size() - 1));

                            Assertions.assertEquals(validateFilter.getGroupFilterReturnListLastValue(),
                                    lastValue.get());
                        }
                ));

        Stream<DynamicTest> streamReturn = Stream.concat(filterGroupLengthTestStream, filterGroupFirstValueTestStream);
        streamReturn = Stream.concat(streamReturn, filterGroupLastValueTestStream);

        return streamReturn;
    }

    @TestFactory
    Stream<DynamicTest> filterTypeColumnValidateTestCases() {
        List<ValidateFilterColumnDataTestHelper> inputList = getTestParameters();

        Stream<DynamicTest> filterTypeLengthTestStream = inputList.stream().
                map(validateFilter -> DynamicTest.dynamicTest(
                        "Type Column Length; Filters: " + validateFilter.getFamilyFilterInput() + ", " +
                                validateFilter.getGroupFilterInput() + ", " +
                                validateFilter.getTypeFilterInput(),
                        () -> {
                            IFilterEquipmentDataModel filterModel = getFilterEquipmentDataModel();

                            Map<FilterColumnsEnum, Optional<List<String>>> filterResult =
                                    filterModel.applyFilterValues(validateFilter.getFamilyFilterInput(),
                                            validateFilter.getGroupFilterInput(),
                                            validateFilter.getTypeFilterInput());

                            Optional<List<String>> optional = filterResult.get(FilterColumnsEnum.type);
                            List<String> FilteredColumnsData = optional.orElseGet(ArrayList::new);

                            Assertions.assertEquals(validateFilter.getTypeFilterReturnListLength(),
                                    FilteredColumnsData.size());
                        }
                ));
        Stream<DynamicTest> filterTypeFirstValueTestStream = inputList.stream().
                map(validateFilter -> DynamicTest.dynamicTest(
                        "Type Column First Value; Filters: " + validateFilter.getFamilyFilterInput() + ", " +
                                validateFilter.getGroupFilterInput() + ", " +
                                validateFilter.getTypeFilterInput(),
                        () -> {
                            IFilterEquipmentDataModel filterModel = getFilterEquipmentDataModel();

                            Map<FilterColumnsEnum, Optional<List<String>>> filterResult =
                                    filterModel.applyFilterValues(
                                            validateFilter.getFamilyFilterInput(),
                                            validateFilter.getGroupFilterInput(),
                                            validateFilter.getTypeFilterInput());

                            Optional<List<String>> optional = filterResult.get(FilterColumnsEnum.type);
                            List<String> FilteredColumnsData = optional.orElseGet(ArrayList::new);

                            Optional<String> firstValue = FilteredColumnsData.stream().findFirst();

                            Assertions.assertEquals(validateFilter.getTypeFilterReturnListFirstValue(),
                                    firstValue.get());
                        }
                ));
        Stream<DynamicTest> filterTypeLastValueTestStream = inputList.stream().
                map(validateFilter -> DynamicTest.dynamicTest(
                        "Type Column Last Value; Filters: " + validateFilter.getFamilyFilterInput() + ", " +
                                validateFilter.getGroupFilterInput() + ", " +
                                validateFilter.getTypeFilterInput(),
                        () -> {
                            IFilterEquipmentDataModel filterModel = getFilterEquipmentDataModel();

                            Map<FilterColumnsEnum, Optional<List<String>>> filterResult =
                                    filterModel.applyFilterValues(
                                            validateFilter.getFamilyFilterInput(),
                                            validateFilter.getGroupFilterInput(),
                                            validateFilter.getTypeFilterInput());

                            Optional<List<String>> optional = filterResult.get(FilterColumnsEnum.type);
                            List<String> FilteredColumnsData = optional.orElseGet(ArrayList::new);

                            Optional<String> lastValue = Optional.ofNullable(
                                    FilteredColumnsData.get(FilteredColumnsData.size() - 1));

                            Assertions.assertEquals(validateFilter.getTypeFilterReturnListLastValue(),
                                    lastValue.get());
                        }
                ));

        Stream<DynamicTest> streamReturn = Stream.concat(filterTypeLengthTestStream, filterTypeFirstValueTestStream);
        streamReturn = Stream.concat(streamReturn, filterTypeLastValueTestStream);

        return streamReturn;
    }


    private IFilterEquipmentDataModel getFilterEquipmentDataModel() {
        return new FilterEquipmentDataModel(dataTable, filterDataColumnNames);
    }
    private List<ValidateFilterColumnDataTestHelper> getTestParameters() {
        return Arrays.asList(
                new ValidateFilterColumnDataTestHelper(
                        "Aircraft", null, null,
                        2, 4, 24,
                        "", "Aircraft",
                        "", "Transport",
                        "", "UNKNOWN"),
                getNoFilterValidateFilterColumnDataTestHelper(),
                new ValidateFilterColumnDataTestHelper(
                        null, "SAM", null,
                        2, 2, 7,
                        "", "ADA",
                        "", "SAM",
                        "", "Star Streak"),
                getNoFilterValidateFilterColumnDataTestHelper(),
                new ValidateFilterColumnDataTestHelper(
                        null, null, "SA-14",
                        2, 3, 2,
                        "", "ADA",
                        "", "SAM",
                        "", "SA-14"),
                getNoFilterValidateFilterColumnDataTestHelper());
    }

    private  ValidateFilterColumnDataTestHelper getNoFilterValidateFilterColumnDataTestHelper() {
        return new ValidateFilterColumnDataTestHelper(
                null, null, null,
                3, 6, 35,
                "", "Aircraft",
                "", "Transport",
                "", "UNKNOWN");
    }
}

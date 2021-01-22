package org.aj.lists.filter.model.create;

import org.aj.database.common.IDataTable;
import org.aj.lists.api.FilterColumnsEnum;
import org.aj.lists.api.IFilterDataColumnNames;
import org.aj.lists.api.IFilterEquipmentDataModel;
import org.aj.lists.filter.model.data.DataSourceSetupForTest;
import org.aj.lists.filter.model.columndata.FilterDataColumnNamesSetupForTest;
import org.aj.lists.filter.model.FilterEquipmentDataModel;
import org.aj.lists.filter.model.util.FilterModelTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class FilterModelStartupColumnDataTest {
    private IDataTable dataTable;
    private IFilterDataColumnNames filterDataColumnNames;
    private final DataSourceSetupForTest dataSourceSetupForTest;
    private final FilterDataColumnNamesSetupForTest filterDataColumnNamesSetupForTest;

    public FilterModelStartupColumnDataTest() {
        dataSourceSetupForTest = new DataSourceSetupForTest();
        filterDataColumnNamesSetupForTest = new FilterDataColumnNamesSetupForTest();

        dataTable = Mockito.mock(IDataTable.class);
        filterDataColumnNames = Mockito.mock(IFilterDataColumnNames.class);
    }

    @BeforeEach
    void setUp() {
        dataSourceSetupForTest.setupDataSource(dataTable);
        filterDataColumnNamesSetupForTest.setupFilterDataColumnNames(filterDataColumnNames);
    }

    @Test
    void confirmFilteredColumnsDataFamilyStartState() {
        System.out.println("Confirm FilteredColumnsData Family On Startup");

        IFilterEquipmentDataModel filterEquipmentDataModel = new FilterEquipmentDataModel(dataTable, filterDataColumnNames);

        String[] expectedArray = new String[]{"", "ADA", "Aircraft"};
        String[] returnedArray =
                FilterModelTestUtil.getFilterColumnData(filterEquipmentDataModel, FilterColumnsEnum.family);

        assertArrayEquals(expectedArray, returnedArray);
    }

    @Test
    void confirmFilteredColumnsDataGroupStartState() {
        System.out.println("Confirm FilteredColumnsData Group On Startup");

        IFilterEquipmentDataModel filterEquipmentDataModel = new FilterEquipmentDataModel(dataTable, filterDataColumnNames);

        String[] expectedArray = new String[]{"", "Fighters", "G/A", "Man Port SAM", "SAM", "Transport"};
        String[] returnedArray =
                FilterModelTestUtil.getFilterColumnData(filterEquipmentDataModel, FilterColumnsEnum.group);

        assertArrayEquals(expectedArray, returnedArray);
    }

    @Test
    void confirmFilteredColumnsDataTypeLengthStartState() {
        System.out.println("Confirm FilteredColumnsData Type Length On Startup");

        IFilterEquipmentDataModel filterEquipmentDataModel = new FilterEquipmentDataModel(dataTable, filterDataColumnNames);

        int expectedLength = 35;
        String[] returnedArray =
                FilterModelTestUtil.getFilterColumnData(filterEquipmentDataModel, FilterColumnsEnum.type);

        assertEquals(expectedLength, returnedArray.length);
    }

    @ParameterizedTest(name = "name={0}: ({1}) = result={2}")
    @CsvSource(value = {
            "First, 0, ''", "Second, 1, AN-12", "Middle, 16, JAVELIN", "Last, 34, UNKNOWN"
    })
    void confirmFilteredColumnsDataTypeValuesStartState(String partitionString, int index,
                                                        String expectedResult) {
        IFilterEquipmentDataModel filterEquipmentDataModel = new FilterEquipmentDataModel(dataTable, filterDataColumnNames);

        String[] returnedArray =
                FilterModelTestUtil.getFilterColumnData(filterEquipmentDataModel, FilterColumnsEnum.type);
        String typeValue = returnedArray[index];
        assertEquals(typeValue, expectedResult);
    }
}

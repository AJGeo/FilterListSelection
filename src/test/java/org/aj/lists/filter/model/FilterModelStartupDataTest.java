package org.aj.lists.filter.model;

import org.aj.database.common.IDataTable;
import org.aj.lists.api.FilterColumnsEnum;
import org.aj.lists.api.IFilterDataColumnNames;
import org.aj.lists.api.IFilterEquipmentDataModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilterModelStartupDataTest {
    private IDataTable dataTable;
    private IFilterDataColumnNames filterDataColumnNames;
    private final DataSourceSetupForTest dataSourceSetupForTest;
    private final FilterDataColumnNamesSetupForTest filterDataColumnNamesSetupForTest;

    public FilterModelStartupDataTest() {
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
    void confirmFilteredDataTypeLengthStartState() {
        System.out.println("Confirm FilteredColumnsData Type Length On Startup");

        IFilterEquipmentDataModel filterEquipmentDataModel = new FilterEquipmentDataModel(dataTable, filterDataColumnNames);

        int expectedLength = 39;
        List<List<String>> returnedData = filterEquipmentDataModel.getFilteredData();

        assertEquals(expectedLength, returnedData.size());
    }

    @ParameterizedTest(name = "name={0}: ({1}{2}{3}{4}) = result={5}")
    @CsvSource(value = {
            "First, 0, '', '', '', true",
            "Middle, 20, Aircraft, Fighters, MIG-17, true",
            "Last, 38, Aircraft, Transport, GULFSTREAM 2, true"
    })
    void confirmFilteredColumnsDataTypeValuesStartState(String partitionString, int index,
                                                        String family, String group, String type,
                                                        boolean expectedResult) {
        IFilterEquipmentDataModel filterEquipmentDataModel = new FilterEquipmentDataModel(dataTable, filterDataColumnNames);

        List<List<String>> returnedData = filterEquipmentDataModel.getFilteredData();

        String familyDataValue = returnedData.get(index).get(0);
        String groupDataValue = returnedData.get(index).get(1);
        String typeDataValue = returnedData.get(index).get(2);

        boolean valuesTested = familyDataValue.equals(family)
                && groupDataValue.equals(group)
                && typeDataValue.equals(type);

        Assertions.assertTrue(valuesTested = expectedResult);
    }
}

package org.aj.lists.filter.model.create;

import org.aj.database.common.IDataTable;
import org.aj.lists.api.IFilterDataColumnNames;
import org.aj.lists.api.IFilterEquipmentDataModel;
import org.aj.lists.filter.model.FilterEquipmentDataModel;
import org.aj.lists.filter.model.data.DataSourceSetupForTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterModelStartupFilteredDataTest {
    private final IDataTable dataTable;
    private final IFilterDataColumnNames filterDataColumnNames;
    private final DataSourceSetupForTest dataSourceSetupForTest;
    private final FilterDataColumnNamesSetupForTest filterDataColumnNamesSetupForTest;

    public FilterModelStartupFilteredDataTest() {
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

        int expectedLength = 38;
        List<List<String>> returnedData = filterEquipmentDataModel.getFilteredData();

        assertEquals(expectedLength, returnedData.size());
    }

    @ParameterizedTest(name = "name={0}: ({1}{2}{3}) = result={4}")
    @CsvSource(value = {
            "0, 'ADA', 'Man Port SAM', 'JAVELIN', true",
            "20, Aircraft, Fighters, MIG-19, true",
            "37, Aircraft, Transport, GULFSTREAM 2, true"
    })
    void confirmFilteredColumnsDataTypeValuesStartState(int index,
                                                        String family, String group, String type,
                                                        boolean expectedResult) {
        IFilterEquipmentDataModel filterEquipmentDataModel =
                new FilterEquipmentDataModel(dataTable, filterDataColumnNames);

        List<List<String>> returnedData = filterEquipmentDataModel.getFilteredData();

        String familyDataValue = returnedData.get(index).get(0);
        String groupDataValue = returnedData.get(index).get(1);
        String typeDataValue = returnedData.get(index).get(2);

        boolean valuesTested = familyDataValue.equals(family)
                && groupDataValue.equals(group)
                && typeDataValue.equals(type);

        assertEquals(expectedResult, valuesTested);
    }
}

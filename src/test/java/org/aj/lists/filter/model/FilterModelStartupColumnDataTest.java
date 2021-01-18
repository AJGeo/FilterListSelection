package org.aj.lists.filter.model;

import org.aj.database.common.IDataTable;
import org.aj.lists.api.FilterColumnsEnum;
import org.aj.lists.api.IFilterDataColumnNames;
import org.aj.lists.api.IFilterEquipmentDataModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import javax.management.ConstructorParameters;
import java.util.List;
import java.util.Optional;

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
                getFilterColumnData(filterEquipmentDataModel, FilterColumnsEnum.family);

        assertArrayEquals(expectedArray, returnedArray);
    }

    @Test
    void confirmFilteredColumnsDataGroupStartState() {
        System.out.println("Confirm FilteredColumnsData Group On Startup");

        IFilterEquipmentDataModel filterEquipmentDataModel = new FilterEquipmentDataModel(dataTable, filterDataColumnNames);

        String[] expectedArray = new String[]{"", "Fighters", "G/A", "Man Port SAM", "SAM", "Transport"};
        String[] returnedArray =
                getFilterColumnData(filterEquipmentDataModel, FilterColumnsEnum.group);

        assertArrayEquals(expectedArray, returnedArray);
    }

    @Test
    void confirmFilteredColumnsDataTypeLengthStartState() {
        System.out.println("Confirm FilteredColumnsData Type Length On Startup");

        IFilterEquipmentDataModel filterEquipmentDataModel = new FilterEquipmentDataModel(dataTable, filterDataColumnNames);

        int expectedLength = 35;
        String[] returnedArray =
                getFilterColumnData(filterEquipmentDataModel, FilterColumnsEnum.type);

        assertEquals(expectedLength, returnedArray.length);
    }

    @ParameterizedTest(name = "name={0}: ({1}{2}) = result={3}")
    @CsvSource(value ={
            "First,0,", "Second,1,AN-12"
    })
    void confirmFilteredColumnsDataTypeValuesStartState(String partitionString, int index,
                                                                String expectedResult) {
        IFilterEquipmentDataModel filterEquipmentDataModel = new FilterEquipmentDataModel(dataTable, filterDataColumnNames);

        String[] returnedArray =
                getFilterColumnData(filterEquipmentDataModel, FilterColumnsEnum.type);
        String typeValue=returnedArray[index];
        assertEquals(typeValue,expectedResult);
    }

    private String[] getFilterColumnData(IFilterEquipmentDataModel filterEquipmentDataModel,
                                         FilterColumnsEnum filterColumns) {
        Optional<List<String>> optional = filterEquipmentDataModel.getFilteredColumnsData().get(filterColumns);
        if (optional.isPresent()) {
            List<String> FilteredColumnsDataFamily = optional.get();
            return FilteredColumnsDataFamily.toArray(new String[0]);
        }
        return null;
    }
}

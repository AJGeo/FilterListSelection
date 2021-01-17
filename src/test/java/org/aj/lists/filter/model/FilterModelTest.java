package org.aj.lists.filter.model;

import org.aj.database.common.IDataTable;
import org.aj.lists.api.FilterColumnsEnum;
import org.aj.lists.api.IFilterDataColumnNames;
import org.aj.lists.api.IFilterEquipmentDataModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FilterModelTest {
    private IDataTable dataTable;
    private IFilterDataColumnNames filterDataColumnNames;
    private final DataSourceSetupForTest dataSourceSetupForTest;
    private final FilterDataColumnNamesSetupForTest filterDataColumnNamesSetupForTest;

    public FilterModelTest() {
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
    public void createModel() {
        System.out.println("Create FilterModel");


        IFilterEquipmentDataModel filterModel = new FilterEquipmentDataModel(dataTable, filterDataColumnNames);
        assertNotNull(filterModel);
    }

    @Test
    void createModelWithNullNullParameterTest() {
        System.out.println("Create FilterModel with null Parameter");

        Assertions.assertThrows(Error.class, () -> {
            IFilterEquipmentDataModel filterModel = new FilterEquipmentDataModel(null, null);
        });
    }

    @Test
    void createModelWithNullParameterTest() {
        System.out.println("Create FilterModel with null Parameter");

        Assertions.assertThrows(Error.class, () -> {
            IFilterEquipmentDataModel filterModel = new FilterEquipmentDataModel(dataTable, null);
        });
    }

    @Test
    void confirmFilteredColumnsDataFamilyStartState() {
        System.out.println("Confirm FilteredColumnsData Record Count On Startup");

        IFilterEquipmentDataModel filterEquipmentDataModel = new FilterEquipmentDataModel(dataTable, filterDataColumnNames);
        String[] expectedArray = new String[]{"", "ADA", "Aircraft"};
        String[] returnedArray = null;

        Optional<List<String>> optional = filterEquipmentDataModel.getFilteredColumnsData().get(FilterColumnsEnum.Family);
        if (optional.isPresent()) {
            List<String> FilteredColumnsDataFamily = optional.get();
            returnedArray = new String[FilteredColumnsDataFamily.size()];
            returnedArray = FilteredColumnsDataFamily.toArray(returnedArray);
        }

        assertArrayEquals(expectedArray, returnedArray);
    }

}
package org.aj.lists.filter.model.create;

import org.aj.database.common.IDataTable;
import org.aj.lists.api.IFilterDataColumnNames;
import org.aj.lists.api.IFilterEquipmentDataModel;
import org.aj.lists.filter.model.data.DataSourceSetupForTest;
import org.aj.lists.filter.model.columndata.FilterDataColumnNamesSetupForTest;
import org.aj.lists.filter.model.FilterEquipmentDataModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class FilterModelStartupStateTest {
    private IDataTable dataTable;
    private IFilterDataColumnNames filterDataColumnNames;
    private final DataSourceSetupForTest dataSourceSetupForTest;
    private final FilterDataColumnNamesSetupForTest filterDataColumnNamesSetupForTest;

    public FilterModelStartupStateTest() {
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
}
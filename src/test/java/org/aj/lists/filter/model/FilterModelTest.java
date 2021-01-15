package org.aj.lists.filter.model;

import org.aj.database.common.IDataTable;
import org.aj.lists.api.IFilterDataColumnNames;
import org.aj.lists.api.IFilterModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
    }

    @Test
    public void createModel() {
        System.out.println("Create FilterModel");

        dataTable = Mockito.mock(IDataTable.class);
        dataSourceSetupForTest.setupDataSource(dataTable);

        filterDataColumnNames = Mockito.mock(IFilterDataColumnNames.class);
        filterDataColumnNamesSetupForTest.setupFilterDataColumnNames(filterDataColumnNames);

        IFilterModel filterModel = new FilterModel(dataTable, filterDataColumnNames);
        assertNotNull(filterModel);
    }

    @Test
    void createModelWithNullParameterTest() {
        System.out.println("Create FilterModel with null Parameter");

        Assertions.assertThrows(Error.class, () -> {
            IFilterModel filterModel = new FilterModel(null, null);
        });
    }

}
package org.aj.lists.filter.model;

import org.aj.database.common.IDataRow;
import org.aj.database.common.IDataTable;
import org.aj.lists.api.IFilterModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FilterModelTest {
    private IDataTable dataTable;
    private DataSourceForTest dataSourceForTest;

    public FilterModelTest() {
        dataSourceForTest = new DataSourceForTest();
        dataTable = Mockito.mock(IDataTable.class);
    }

    @Test
    public void createModel() {
        System.out.println("Create FilterModel");

        dataTable = Mockito.mock(IDataTable.class);
        dataSourceForTest.setupDataSource(dataTable);

        IFilterModel filterModel = new FilterModel(dataTable);
        assertNotNull(filterModel);
    }

}
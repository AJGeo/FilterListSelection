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

    @Test
    public void createModel() {
        System.out.println("Create FilterModel");

        IDataTable dataTable = Mockito.mock(IDataTable.class);
        setupDataSource(dataTable);

        IFilterModel filterModel = new FilterModel(dataTable);
        assertNotNull(filterModel);
    }

    private void setupDataSource(IDataTable dataTable) {
        Mockito.when(dataTable.getColumnIndex("group")).thenReturn(0);
        Mockito.when(dataTable.getColumnIndex("type")).thenReturn(1);
        Mockito.when(dataTable.getColumnIndex("subType")).thenReturn(2);

        List<IDataRow> dataRows = Arrays.asList(
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"),
                createDataRow("Aircraft", "Fighter", "F16"));

        Mockito.when(dataTable.getRows()).thenReturn(java.util.Optional.of(dataRows));
    }

    private IDataRow createDataRow(String... strings) {
        IDataRow dataRow = Mockito.mock(IDataRow.class);
        Mockito.when(dataRow.getString(0)).thenReturn(java.util.Optional.ofNullable(strings[0]));
        Mockito.when(dataRow.getString(1)).thenReturn(java.util.Optional.ofNullable(strings[1]));
        Mockito.when(dataRow.getString(2)).thenReturn(java.util.Optional.ofNullable(strings[2]));

        return dataRow;
    }
}
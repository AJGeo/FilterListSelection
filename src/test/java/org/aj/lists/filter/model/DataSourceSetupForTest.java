package org.aj.lists.filter.model;

import org.aj.database.common.IDataRow;
import org.aj.database.common.IDataTable;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class DataSourceSetupForTest {
    public void setupDataSource(IDataTable dataTable) {
        Mockito.when(dataTable.getColumnIndex("family")).thenReturn(0);
        Mockito.when(dataTable.getColumnIndex("group")).thenReturn(1);
        Mockito.when(dataTable.getColumnIndex("type")).thenReturn(2);

        List<IDataRow> dataRows = Arrays.asList(
                createDataRow("Aircraft", "Fighters", "F-16"),
                createDataRow("Aircraft", "Fighters", "F-4"),
                createDataRow("Aircraft", "Fighters", "F-5"),
                createDataRow("Aircraft", "Fighters", "F-7"),
                createDataRow("Aircraft", "Fighters", "Hawk"),
                createDataRow("Aircraft", "Fighters", "Hunter"),
                createDataRow("Aircraft", "Fighters", "L39"),
                createDataRow("Aircraft", "Fighters", "MIG-17"),
                createDataRow("Aircraft", "Fighters", "MIG-19"),
                createDataRow("Aircraft", "Fighters", "MIG-21"),
                createDataRow("Aircraft", "Fighters", "MIG-23"),
                createDataRow("Aircraft", "Fighters", "MIG-25"),
                createDataRow("Aircraft", "Fighters", "MIG-29"),
                createDataRow("Aircraft", "Fighters", "UNKNOWN"),
                createDataRow("Aircraft", "G/A", "BN-2"),
                createDataRow("Aircraft", "G/A", "FIAT G91"),
                createDataRow("Aircraft", "G/A", "GALEB"),
                createDataRow("Aircraft", "G/A", "Hawk"),
                createDataRow("Aircraft", "G/A", "Hunter"),
                createDataRow("Aircraft", "G/A", "MIG-29"),
                createDataRow("Aircraft", "Transport", "AN-12"),
                createDataRow("Aircraft", "Transport", "AN-22"),
                createDataRow("Aircraft", "Transport", "AN-26"),
                createDataRow("Aircraft", "Transport", "C-130"),
                createDataRow("Aircraft", "Transport", "DC-6"),
                createDataRow("Aircraft", "Transport", "GULFSTREAM 2"),
                createDataRow("ADA", "Man Port SAM", "JAVELIN"),
                createDataRow("ADA", "Man Port SAM", "JAVELIN"),
                createDataRow("ADA", "Man Port SAM", "SA-14"),
                createDataRow("ADA", "Man Port SAM", "SA-16"),
                createDataRow("ADA", "Man Port SAM", "SA-18"),
                createDataRow("ADA", "Man Port SAM", "Starburst"),
                createDataRow("ADA", "Man Port SAM", "Stinger"),
                createDataRow("ADA", "SAM", "SA-2"),
                createDataRow("ADA", "SAM", "SA-9"),
                createDataRow("ADA", "SAM", "SA-11"),
                createDataRow("ADA", "SAM", "SA-13"),
                createDataRow("ADA", "SAM", "SA-14"),
                createDataRow("ADA", "SAM", "Star Streak"));

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

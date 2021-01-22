package org.aj.lists.filter.model.filtered_data;

import org.aj.database.common.IDataTable;
import org.aj.lists.api.IFilterDataColumnNames;
import org.aj.lists.filter.model.columndata.FilterDataColumnNamesSetupForTest;
import org.aj.lists.filter.model.data.DataSourceSetupForTest;
import org.aj.lists.filter.model.util.FilterModelTestUtil;
import org.aj.lists.filter.model.columndata.ValidateFilterColumnDataTestHelper;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Stream;

public class FilterModelValidateFilterDataTest {
    private final IDataTable dataTable;
    private final IFilterDataColumnNames filterDataColumnNames;
    private final DataSourceSetupForTest dataSourceSetupForTest;
    private final FilterDataColumnNamesSetupForTest filterDataColumnNamesSetupForTest;

    public FilterModelValidateFilterDataTest() {
        dataSourceSetupForTest = new DataSourceSetupForTest();
        filterDataColumnNamesSetupForTest = new FilterDataColumnNamesSetupForTest();

        dataTable = Mockito.mock(IDataTable.class);
        filterDataColumnNames = Mockito.mock(IFilterDataColumnNames.class);

        setUp();
    }

    private void setUp() {
        dataSourceSetupForTest.setupDataSource(dataTable);
        filterDataColumnNamesSetupForTest.setupFilterDataColumnNames(filterDataColumnNames);
    }

    @TestFactory
    Stream<DynamicTest> filterFamilyColumnValidateTestCases() {
        List<ValidateFilterColumnDataTestHelper> inputList = FilterModelTestUtil.getTestParameters();
    }
}

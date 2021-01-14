package org.aj.lists.filter.model;

import org.aj.lists.api.FilterColumnsEnum;
import org.aj.lists.api.IFilterDataColumnNames;
import org.mockito.Mockito;

public class FilterDataColumnNamesSetupForTest {

    public void setupFilterDataColumnNames(IFilterDataColumnNames filterDataColumnNames) {
        String FAMILY_VALUE = "family";
        Mockito.when(filterDataColumnNames.getColumnName(FilterColumnsEnum.Family)).thenReturn(FAMILY_VALUE);
        String GROUP_VALUE = "group";
        Mockito.when(filterDataColumnNames.getColumnName(FilterColumnsEnum.Group)).thenReturn(GROUP_VALUE);
        String TYPE_VALUE = "type";
        Mockito.when(filterDataColumnNames.getColumnName(FilterColumnsEnum.Type)).thenReturn(TYPE_VALUE);
    }
}

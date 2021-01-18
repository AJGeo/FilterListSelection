package org.aj.lists.filter.model;

import org.aj.lists.api.FilterColumnsEnum;
import org.aj.lists.api.IFilterDataColumnNames;
import org.mockito.Mockito;

public class FilterDataColumnNamesSetupForTest {

    public void setupFilterDataColumnNames(IFilterDataColumnNames filterDataColumnNames) {
        String FAMILY_VALUE = "family";
        Mockito.when(filterDataColumnNames.getColumnName(FilterColumnsEnum.family)).thenReturn(FAMILY_VALUE);
        String GROUP_VALUE = "group";
        Mockito.when(filterDataColumnNames.getColumnName(FilterColumnsEnum.group)).thenReturn(GROUP_VALUE);
        String TYPE_VALUE = "type";
        Mockito.when(filterDataColumnNames.getColumnName(FilterColumnsEnum.type)).thenReturn(TYPE_VALUE);
    }
}

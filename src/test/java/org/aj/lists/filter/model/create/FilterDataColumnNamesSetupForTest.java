package org.aj.lists.filter.model.create;

import org.aj.lists.api.FilterColumnsEnum;
import org.aj.lists.api.IFilterDataColumnNames;
import org.mockito.Mockito;

public class FilterDataColumnNamesSetupForTest {

    public void setupFilterDataColumnNames(IFilterDataColumnNames filterDataColumnNames) {
        final String FAMILY_VALUE = "family";
        final String GROUP_VALUE = "group";
        final String TYPE_VALUE = "type";
        Mockito.when(filterDataColumnNames.getColumnName(FilterColumnsEnum.family)).thenReturn(FAMILY_VALUE);
        Mockito.when(filterDataColumnNames.getColumnName(FilterColumnsEnum.group)).thenReturn(GROUP_VALUE);
        Mockito.when(filterDataColumnNames.getColumnName(FilterColumnsEnum.type)).thenReturn(TYPE_VALUE);
    }
}

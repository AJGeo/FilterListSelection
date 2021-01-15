package org.aj.lists.filter.model;

import org.aj.database.common.IDataTable;
import org.aj.lists.api.FilterColumnsEnum;
import org.aj.lists.api.IFilterDataColumnNames;
import org.aj.lists.api.IFilterModel;

import java.util.*;

public class FilterModel implements IFilterModel {
    private final IDataTable dataTable;
    private final IFilterDataColumnNames filterDataColumnNames;
    private int familyColumnIndex;
    private int groupColumnIndex;
    private int typeColumnIndex;

    public FilterModel(IDataTable dataTable, IFilterDataColumnNames filterDataColumnNames) throws Error {
        if (dataTable == null || filterDataColumnNames == null)
            throw new Error("A column name was not defined");

        this.dataTable = dataTable;
        this.filterDataColumnNames = filterDataColumnNames;

        filter(null, null, null);
    }

    private final void retriveColumnIndexsFromDataTable() {
//         familyColumnIndex = dataTable.getColumnIndex(FAMILY_DB_COLUMN);
//         groupColumnIndex = dataTable.getColumnIndex(GROUP_DB_COLUMN);
//         typeColumnIndex = dataTable.getColumnIndex(TYPE_DB_COLUMN);

    }

    @Override
    public final Optional<Map<FilterColumnsEnum, Optional<List<String>>>> filter(String familyFilterValue,
                                                                                 String groupFilterValue,
                                                                                 String typeFilterValue) {
        Set<String> familySet = new HashSet();
        Set<String> groupSet = new HashSet<>();
        Set<String> typeSet = new HashSet<>();


        return null;
    }

    @Override
    public final Optional<String[][]> getFilteredData() {
        return Optional.of(new String[0][]);
    }
}

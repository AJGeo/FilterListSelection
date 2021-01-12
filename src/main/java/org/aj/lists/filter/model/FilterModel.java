package org.aj.lists.filter.model;

import org.aj.database.common.IDataTable;
import org.aj.lists.api.FilterColumnsEnum;
import org.aj.lists.api.IFilterModel;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FilterModel implements IFilterModel {
    private final IDataTable dataTable;

    public FilterModel(IDataTable dataTable) {
        this.dataTable = dataTable;
    }

    @Override
    public final Optional<Map<FilterColumnsEnum, Optional<List<String>>>> Filter(String groupFilterValue,
                                                                                 String typeFilterValue,
                                                                                 String subtypeFilterValue) {
        return null;
    }

    @Override
    public final Optional<String[][]> getFilteredData() {
        return Optional.of(new String[0][]);
    }
}

package org.aj.lists.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IFilterModel {
    public Optional<Map<FilterColumnsEnum, Optional<List<String>>>> Filter(final String groupFilterValue,
                                                                final String typeFilterValue,
                                                                final String subtypeFilterValue);

    public Optional<String[][]> getFilteredData();
}

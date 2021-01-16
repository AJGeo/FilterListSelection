package org.aj.lists.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IFilterModel {
    Optional<Map<FilterColumnsEnum, Optional<List<String>>>> filter(final String groupFilterValue,
                                                                    final String typeFilterValue,
                                                                    final String subtypeFilterValue);

    Optional<String[][]> getFilteredData();
}

package org.aj.lists.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IFilterEquipmentDataModel {
    Optional<Map<FilterColumnsEnum, Optional<List<String>>>> applyFilterValues(final String familyFilterValue,
                                                                               final String groupFilterValue,
                                                                               final String typeFilterValue);

    Map<FilterColumnsEnum, Optional<List<String>>> getFilteredColumnsData();

    Optional<List<List<String>>> getFilteredData();
}

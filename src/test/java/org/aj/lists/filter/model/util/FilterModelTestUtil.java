package org.aj.lists.filter.model.util;

import org.aj.lists.api.FilterColumnsEnum;
import org.aj.lists.api.IFilterEquipmentDataModel;

import java.util.List;
import java.util.Optional;

public class FilterModelTestUtil {
    public static String[] getFilterColumnData(IFilterEquipmentDataModel filterEquipmentDataModel,
                                         FilterColumnsEnum filterColumns) {
        Optional<List<String>> optional = filterEquipmentDataModel.getFilteredColumnsData().get(filterColumns);
        if (optional.isPresent()) {
            List<String> FilteredColumnsData = optional.get();
            return FilteredColumnsData.toArray(new String[0]);
        }
        return null;
    }
}

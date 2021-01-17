package org.aj.lists.filter.model;

import org.aj.database.common.IDataRow;
import org.aj.database.common.IDataTable;
import org.aj.lists.api.FilterColumnsEnum;
import org.aj.lists.api.IFilterDataColumnNames;
import org.aj.lists.api.IFilterEquipmentDataModel;

import java.util.*;
import java.util.stream.Collectors;

public class FilterEquipmentDataModel implements IFilterEquipmentDataModel {
    private final IDataTable dataTable;
    private final IFilterDataColumnNames filterDataColumnNames;
    private final List<List<String>> filteredDataList;
    private final Map<FilterColumnsEnum, Optional<List<String>>> filterColumnDataMap;
    private int familyColumnIndex;
    private int groupColumnIndex;
    private int typeColumnIndex;

    public FilterEquipmentDataModel(IDataTable dataTable, IFilterDataColumnNames filterDataColumnNames) throws Error {
        if (dataTable == null || filterDataColumnNames == null)
            throw new Error("A column name was not defined");

        this.dataTable = dataTable;
        this.filterDataColumnNames = filterDataColumnNames;
        filteredDataList = new ArrayList<>();
        filterColumnDataMap = new HashMap<>();

        retrieveColumnIndexesFromDataTable();

        applyFilterValues(null, null, null);
    }

    private void retrieveColumnIndexesFromDataTable() {
        familyColumnIndex = dataTable.getColumnIndex(filterDataColumnNames.getColumnName(FilterColumnsEnum.Family));
        groupColumnIndex = dataTable.getColumnIndex(filterDataColumnNames.getColumnName(FilterColumnsEnum.Group));
        typeColumnIndex = dataTable.getColumnIndex(filterDataColumnNames.getColumnName(FilterColumnsEnum.Type));
    }

    @Override
    public final Optional<Map<FilterColumnsEnum, Optional<List<String>>>> applyFilterValues(String familyFilterValue,
                                                                                            String groupFilterValue,
                                                                                            String typeFilterValue) {
        if (dataTable.getRows().isEmpty())
            return Optional.empty();

        Set<String> familySet = new HashSet<>();
        familySet.add("");
        Set<String> groupSet = new HashSet<>();
        groupSet.add("");
        Set<String> typeSet = new HashSet<>();
        typeSet.add("");

        filteredDataList.clear();
        for (IDataRow row : dataTable.getRows().get()) {
            String familyDataValue = row.getString(familyColumnIndex).isPresent() ?
                    row.getString(familyColumnIndex).get() : null;
            String groupDataValue = row.getString(groupColumnIndex).isPresent() ?
                    row.getString(groupColumnIndex).get() : null;
            String typeDataValue = row.getString(typeColumnIndex).isPresent() ?
                    row.getString(typeColumnIndex).get() : null;

            if (isFamilyFilterPass(familyDataValue, familyFilterValue) &&
                    isGroupFilterPass(groupDataValue, groupFilterValue) &&
                    isTypeFilterPass(typeDataValue, typeFilterValue)) {
                Optional.ofNullable(familyDataValue).ifPresent(familySet::add);
                Optional.ofNullable(groupDataValue).ifPresent(groupSet::add);
                Optional.ofNullable(typeDataValue).ifPresent(typeSet::add);

                addFilteredListValues(familyDataValue, groupDataValue, typeDataValue);
            }
        }
        sortFilteredListValues();

        List<String> familyList = familySet.stream().sorted().collect(Collectors.toList());
        List<String> groupList = groupSet.stream().sorted().collect(Collectors.toList());
        List<String> typeList = typeSet.stream().sorted().collect(Collectors.toList());


        filterColumnDataMap.put(FilterColumnsEnum.Family, Optional.of(familyList));
        filterColumnDataMap.put(FilterColumnsEnum.Group, Optional.of(groupList));
        filterColumnDataMap.put(FilterColumnsEnum.Type, Optional.of(typeList));

        return Optional.of(filterColumnDataMap);
    }

    private boolean isFamilyFilterPass(String dataValue, String familyFilterValue) {
        return familyFilterValue == null || dataValue.equals(familyFilterValue);
    }

    private boolean isGroupFilterPass(String dataValue, String groupFilterValue) {
        return groupFilterValue == null || dataValue.equals(groupFilterValue);
    }

    private boolean isTypeFilterPass(String dataValue, String typeFilterValue) {
        return typeFilterValue == null || dataValue.equals(typeFilterValue);
    }

    private void addFilteredListValues(String... dataValues) {
        filteredDataList.add(new ArrayList<>(
                Arrays.asList(dataValues)));
    }

    private void sortFilteredListValues() {
        if (filteredDataList.isEmpty())
            return;

        Comparator<List<String>> compareFirstToLastListValue = Comparator.comparing(familyValue -> familyValue.get(0));
        for (int count = 1; count < filteredDataList.get(0).size(); ++count) {
            final int countFinal = count;
            compareFirstToLastListValue = compareFirstToLastListValue.thenComparing(groupValue -> groupValue.get(countFinal));
        }
        filteredDataList.sort(compareFirstToLastListValue);
    }

    @Override
    public Map<FilterColumnsEnum, Optional<List<String>>> getFilteredColumnsData() {
        return filterColumnDataMap;
    }

    @Override
    public final Optional<List<List<String>>> getFilteredData() {
        return Optional.of(filteredDataList);
    }
}

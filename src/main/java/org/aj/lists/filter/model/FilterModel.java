package org.aj.lists.filter.model;

import org.aj.database.common.IDataRow;
import org.aj.database.common.IDataTable;
import org.aj.lists.api.FilterColumnsEnum;
import org.aj.lists.api.IFilterDataColumnNames;
import org.aj.lists.api.IFilterModel;

import java.util.*;
import java.util.stream.Collectors;

public class FilterModel implements IFilterModel {
    private final IDataTable dataTable;
    private final IFilterDataColumnNames filterDataColumnNames;
    private final List<List<String>> filteredList;
    private int familyColumnIndex;
    private int groupColumnIndex;
    private int typeColumnIndex;

    public FilterModel(IDataTable dataTable, IFilterDataColumnNames filterDataColumnNames) throws Error {
        if (dataTable == null || filterDataColumnNames == null)
            throw new Error("A column name was not defined");

        this.dataTable = dataTable;
        this.filterDataColumnNames = filterDataColumnNames;
        filteredList = new ArrayList<>();

        retrieveColumnIndexesFromDataTable();

        filter(null, null, null);
    }

    private final void retrieveColumnIndexesFromDataTable() {
        familyColumnIndex = dataTable.getColumnIndex(filterDataColumnNames.getColumnName(FilterColumnsEnum.Family));
        groupColumnIndex = dataTable.getColumnIndex(filterDataColumnNames.getColumnName(FilterColumnsEnum.Group));
        typeColumnIndex = dataTable.getColumnIndex(filterDataColumnNames.getColumnName(FilterColumnsEnum.Type));
    }

    @Override
    public final Optional<Map<FilterColumnsEnum, Optional<List<String>>>> filter(String familyFilterValue,
                                                                                 String groupFilterValue,
                                                                                 String typeFilterValue) {
        if (!dataTable.getRows().isPresent())
            return Optional.empty();

        Set<String> familySet = new HashSet<>();
        familySet.add("");
        Set<String> groupSet = new HashSet<>();
        groupSet.add("");
        Set<String> typeSet = new HashSet<>();
        typeSet.add("");

        filteredList.clear();
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

        return null;
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
        filteredList.add(new ArrayList<>(
                Arrays.asList(dataValues)));
    }

    private void sortFilteredListValues() {
        if (filteredList.isEmpty())
            return;

        Comparator<List<String>> compareFirstToLastListValue = Comparator.comparing(familyValue -> familyValue.get(0));
        for (int count = 1; count < filteredList.get(0).size(); ++count) {
            final int countFinal = count;
            compareFirstToLastListValue = compareFirstToLastListValue.thenComparing(groupValue -> groupValue.get(countFinal));
        }
        filteredList.sort(compareFirstToLastListValue);
    }

    @Override
    public final Optional<String[][]> getFilteredData() {
        return Optional.of(new String[0][]);
    }
}

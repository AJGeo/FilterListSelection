package org.aj.lists.filter.model.filtered_data;

import java.util.List;

public class FilteredDataParameters {
    private final String familyFilterInput;
    private final String groupFilterInput;
    private final String typeFilterInput;
    private final int filteredRecordsCount;
    private final List<String> firstRecord;
    private final List<String> lastRecord;

    public FilteredDataParameters(String familyFilterInput, String groupFilterInput, String typeFilterInput,
                                  int filteredRecordsCount, List<String> firstRecord, List<String> lastRecord) {
        this.familyFilterInput = familyFilterInput;
        this.groupFilterInput = groupFilterInput;
        this.typeFilterInput = typeFilterInput;
        this.filteredRecordsCount = filteredRecordsCount;
        this.firstRecord = firstRecord;
        this.lastRecord = lastRecord;
    }

    public String getFamilyFilterInput() {
        return familyFilterInput;
    }

    public String getGroupFilterInput() {
        return groupFilterInput;
    }

    public String getTypeFilterInput() {
        return typeFilterInput;
    }

    public int getFilteredRecordsCount() {
        return filteredRecordsCount;
    }

    public List<String> getFirstRecord() {
        return firstRecord;
    }

    public List<String> getLastRecord() {
        return lastRecord;
    }
}

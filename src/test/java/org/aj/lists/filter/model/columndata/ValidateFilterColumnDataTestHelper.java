package org.aj.lists.filter.model.columndata;

import java.util.Arrays;
import java.util.List;

public class ValidateFilterColumnDataTestHelper {
    private final String familyFilterInput;
    private final String groupFilterInput;
    private final String typeFilterInput;
    private final int familyFilterReturnListLength;
    private final String familyFilterReturnListFirstValue;
    private final String familyFilterReturnListLastValue;
    private final int groupFilterReturnListLength;
    private final String groupFilterReturnListFirstValue;
    private final String groupFilterReturnListLastValue;
    private final int typeFilterReturnListLength;
    private final String typeFilterReturnListFirstValue;
    private final String typeFilterReturnListLastValue;

    public ValidateFilterColumnDataTestHelper(String familyFilterInput,
                                              String groupFilterInput,
                                              String typeFilterInput,
                                              int familyFilterReturnListLength,
                                              int groupFilterReturnListLength,
                                              int typeFilterReturnListLength,
                                              String familyFilterReturnListFirstValue,
                                              String familyFilterReturnListLastValue,
                                              String groupFilterReturnListFirstValue,
                                              String groupFilterReturnListLastValue,
                                              String typeFilterReturnListFirstValue,
                                              String typeFilterReturnListLastValue) {
        this.familyFilterInput = familyFilterInput;
        this.groupFilterInput = groupFilterInput;
        this.typeFilterInput = typeFilterInput;
        this.familyFilterReturnListLength = familyFilterReturnListLength;
        this.familyFilterReturnListFirstValue = familyFilterReturnListFirstValue;
        this.familyFilterReturnListLastValue = familyFilterReturnListLastValue;
        this.groupFilterReturnListLength = groupFilterReturnListLength;
        this.groupFilterReturnListFirstValue = groupFilterReturnListFirstValue;
        this.groupFilterReturnListLastValue = groupFilterReturnListLastValue;
        this.typeFilterReturnListLength = typeFilterReturnListLength;
        this.typeFilterReturnListFirstValue = typeFilterReturnListFirstValue;
        this.typeFilterReturnListLastValue = typeFilterReturnListLastValue;
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

    public int getFamilyFilterReturnListLength() {
        return familyFilterReturnListLength;
    }

    public String getFamilyFilterReturnListFirstValue() {
        return familyFilterReturnListFirstValue;
    }

    public String getFamilyFilterReturnListLastValue() {
        return familyFilterReturnListLastValue;
    }

    public int getGroupFilterReturnListLength() {
        return groupFilterReturnListLength;
    }

    public String getGroupFilterReturnListFirstValue() {
        return groupFilterReturnListFirstValue;
    }

    public String getGroupFilterReturnListLastValue() {
        return groupFilterReturnListLastValue;
    }

    public int getTypeFilterReturnListLength() {
        return typeFilterReturnListLength;
    }

    public String getTypeFilterReturnListFirstValue() {
        return typeFilterReturnListFirstValue;
    }

    public String getTypeFilterReturnListLastValue() {
        return typeFilterReturnListLastValue;
    }
}

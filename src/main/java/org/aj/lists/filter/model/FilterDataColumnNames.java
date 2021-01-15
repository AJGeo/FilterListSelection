package org.aj.lists.filter.model;

import org.aj.lists.api.FilterColumnsEnum;
import org.aj.lists.api.IFilterDataColumnNames;

public class FilterDataColumnNames
        implements IFilterDataColumnNames {

    private String familyColumnName;
    private String groupColumnName;
    private String typeColumnName;

    public FilterDataColumnNames(String familyColumnName, String groupColumnName, String typeColumnName) throws Error {
        if (familyColumnName == null || groupColumnName == null || typeColumnName == null)
            throw new Error("A column name was not defined");

        if (familyColumnName.isBlank() || groupColumnName.isBlank() || typeColumnName.isBlank())
            throw new Error("A column name was not populated");


        this.familyColumnName = familyColumnName;
        this.groupColumnName = groupColumnName;
        this.typeColumnName = typeColumnName;
    }

    @Override
    public String getColumnName(FilterColumnsEnum filterColumns) {
        switch (filterColumns) {
            case Family:
                return getFamilyColumnName();
            case Group:
                return getGroupColumnName();
            default:
                return getTypeColumnName();
        }
    }

    private String getFamilyColumnName() {
        return familyColumnName;
    }

    private String getGroupColumnName() {
        return groupColumnName;
    }

    private String getTypeColumnName() {
        return typeColumnName;
    }
}

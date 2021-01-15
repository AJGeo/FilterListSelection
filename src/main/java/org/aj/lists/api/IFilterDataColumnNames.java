package org.aj.lists.api;

@FunctionalInterface
public interface IFilterDataColumnNames {
    public String getColumnName(FilterColumnsEnum filterColumns);
}

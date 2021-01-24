package org.aj.lists.api;

@FunctionalInterface
public interface IFilterDataColumnNames {
    String getColumnName(FilterColumnsEnum filterColumns);
}

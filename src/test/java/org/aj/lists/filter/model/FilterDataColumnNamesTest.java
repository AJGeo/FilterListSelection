package org.aj.lists.filter.model;

import org.aj.lists.api.FilterColumnsEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilterDataColumnNamesTest {
    private final FilterDataColumnNames filterDataColumnNamesDefault;
    private final String FAMILY_VALUE = "family";
    private final String GROUP_VALUE = "group";
    private final String TYPE_VALUE = "type";

    public FilterDataColumnNamesTest() {
        this.filterDataColumnNamesDefault = new FilterDataColumnNames(
                FAMILY_VALUE,
                GROUP_VALUE,
                TYPE_VALUE);
    }

    @Test
    void createCorrectTest() {
        System.out.println("Create FilterDataColumnNames Correct");

        assertNotNull(filterDataColumnNamesDefault);
    }

    @Test
    void createWithNullParameterTest() {
        System.out.println("Create FilterDataColumnNames with null Parameter");

        Assertions.assertThrows(Error.class, () -> {
            FilterDataColumnNames filterDataColumnNames = new FilterDataColumnNames(
                    FAMILY_VALUE,
                    GROUP_VALUE,
                    null);
        });
    }

    @Test
    void createWithEmptyParameterTest() {
        System.out.println("Create FilterDataColumnNames with null Parameter");

        assertThrows(Error.class, () -> {
            FilterDataColumnNames filterDataColumnNames = new FilterDataColumnNames(
                    FAMILY_VALUE,
                    GROUP_VALUE,
                    "  ");
        });
    }

    @Test
    void getColumnNameFamilyTest() {
        System.out.println("Test getColumnName family");

        assertEquals(FAMILY_VALUE, filterDataColumnNamesDefault.getColumnName(FilterColumnsEnum.family));
    }

    @Test
    void getColumnNameGroupTest() {
        System.out.println("Test getColumnName group");

        assertEquals(GROUP_VALUE, filterDataColumnNamesDefault.getColumnName(FilterColumnsEnum.group));
    }

    @Test
    void getColumnNameTypeTest() {
        System.out.println("Test getColumnName type");

        assertEquals(TYPE_VALUE, filterDataColumnNamesDefault.getColumnName(FilterColumnsEnum.type));
    }
}
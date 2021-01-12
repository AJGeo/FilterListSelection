package org.aj.lists.filter.model;

import org.aj.lists.api.IFilterModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilterModelTest {

    @Test
    public void createModel() {
        System.out.println("Create FilterModel");
        IFilterModel filterModel = new FilterModel();
        assertNotNull(filterModel);
    }
}
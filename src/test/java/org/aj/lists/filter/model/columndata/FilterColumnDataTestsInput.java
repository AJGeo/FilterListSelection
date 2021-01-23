package org.aj.lists.filter.model.columndata;

import java.util.Arrays;
import java.util.List;

public class FilterColumnDataTestsInput {
    List<FilterColumnDataTestParameters> getTestParameters() {
        return Arrays.asList(
                new FilterColumnDataTestParameters(
                        "Aircraft", null, null,
                        2, 4, 24,
                        "", "Aircraft",
                        "", "Transport",
                        "", "UNKNOWN"),
                getNoFilterValidateFilterColumnDataTestHelper(),
                new FilterColumnDataTestParameters(
                        null, "SAM", null,
                        2, 2, 7,
                        "", "ADA",
                        "", "SAM",
                        "", "Star Streak"),
                getNoFilterValidateFilterColumnDataTestHelper(),
                new FilterColumnDataTestParameters(
                        null, null, "SA-14",
                        2, 3, 2,
                        "", "ADA",
                        "", "SAM",
                        "", "SA-14"),
                getNoFilterValidateFilterColumnDataTestHelper());
    }

    private FilterColumnDataTestParameters getNoFilterValidateFilterColumnDataTestHelper() {
        return new FilterColumnDataTestParameters(
                null, null, null,
                3, 6, 35,
                "", "Aircraft",
                "", "Transport",
                "", "UNKNOWN");
    }

}

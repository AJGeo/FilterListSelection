package org.aj.lists.filter.model.filtered_data;

import java.util.Arrays;
import java.util.List;

public class FilteredDataTestsParameters {
    /* EXERCISE
1) Explain what is covered with these tests
2) What test is still out standing
 */
    public List<FilteredDataParameters> getTestParameters() {
        return Arrays.asList(
                new FilteredDataParameters(
                        "Aircraft", null, null,
                        26,
                        Arrays.asList("Aircraft", "Fighters", "F-16"),
                        Arrays.asList("Aircraft", "Transport", "GULFSTREAM 2")),
                getNoFilterValidateFilteredDataTestParameters(),
                new FilteredDataParameters(
                        null, "SAM", null,
                        6,
                        Arrays.asList("ADA", "SAM", "SA-11"),
                        Arrays.asList("ADA", "SAM", "Star Streak")),
                getNoFilterValidateFilteredDataTestParameters(),
                new FilteredDataParameters(
                        null, null, "SA-14",
                        2,
                        Arrays.asList("ADA", "Man Port SAM", "SA-14"),
                        Arrays.asList("ADA", "SAM", "SA-14")),
                getNoFilterValidateFilteredDataTestParameters());
    }

    private FilteredDataParameters getNoFilterValidateFilteredDataTestParameters() {
        return new FilteredDataParameters(
                null, null, null,
                39,
                Arrays.asList("ADA", "Man Port SAM", "JAVELIN"),
                Arrays.asList("Aircraft", "Transport", "GULFSTREAM 2"));
    }
}

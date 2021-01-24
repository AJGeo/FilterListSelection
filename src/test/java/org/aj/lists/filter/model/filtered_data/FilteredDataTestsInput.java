package org.aj.lists.filter.model.filtered_data;

import java.util.Arrays;
import java.util.List;

public class FilteredDataTestsInput {
    /* EXERCISE
1) Explain what is covered with these tests
2) What test is still out standing
 */
    public List<FilteredDataTestParameters> getTestParameters() {
        return Arrays.asList(
                new FilteredDataTestParameters(
                        "Aircraft", null, null,
                        26,
                        Arrays.asList("Aircraft", "Fighters", "F-16"),
                        Arrays.asList("Aircraft", "Transport", "GULFSTREAM 2")),
                getNoFilterValidateFilteredDataTestParameters(),
                new FilteredDataTestParameters(
                        null, "SAM", null,
                        6,
                        Arrays.asList("ADA", "SAM", "SA-11"),
                        Arrays.asList("ADA", "SAM", "Star Streak")),
                getNoFilterValidateFilteredDataTestParameters(),
                new FilteredDataTestParameters(
                        null, null, "SA-14",
                        2,
                        Arrays.asList("ADA", "Man Port SAM", "SA-14"),
                        Arrays.asList("ADA", "SAM", "SA-14")),
                getNoFilterValidateFilteredDataTestParameters());
    }

    private FilteredDataTestParameters getNoFilterValidateFilteredDataTestParameters() {
        return new FilteredDataTestParameters(
                null, null, null,
                38,
                Arrays.asList("ADA", "Man Port SAM", "JAVELIN"),
                Arrays.asList("Aircraft", "Transport", "GULFSTREAM 2"));
    }
}

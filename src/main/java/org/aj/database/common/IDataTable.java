package org.aj.database.common;

import java.util.List;
import java.util.Optional;

public interface IDataTable {
    int getColumnIndex(String name);

    Optional<List<IDataRow>> getRows();
}

package org.aj.database.common;

import java.util.List;
import java.util.Optional;

public interface IDataTable {
    public int getColumnIndex(String name);

    public Optional<List<IDataRow>> getRows();
}

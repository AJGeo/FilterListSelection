package org.aj.database.common;

import java.util.Optional;

public interface IDataRow {
    public Optional<String> getString(int columnIndex);
}

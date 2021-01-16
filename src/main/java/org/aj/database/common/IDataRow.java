package org.aj.database.common;

import java.util.Optional;

public interface IDataRow {
    Optional<String> getString(int columnIndex);
}

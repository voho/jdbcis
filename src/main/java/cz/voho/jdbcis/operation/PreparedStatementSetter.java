package cz.voho.jdbcis.operation;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Sets various properties on the provided prepared statement object.
 * Can be used e.g. to setup query parameters.
 */
@FunctionalInterface
public interface PreparedStatementSetter {
    /**
     * Applies operations on the given prepared statement object.
     * @param preparedStatement target prepared statement to modify
     * @throws SQLException on SQL error
     */
    void apply(PreparedStatement preparedStatement) throws SQLException;
}

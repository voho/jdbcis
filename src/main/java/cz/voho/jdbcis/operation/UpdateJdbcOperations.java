package cz.voho.jdbcis.operation;

import java.sql.SQLException;

/**
 * Interface for JDBC UPDATE/DELETE/TRUNCATE operations.
 */
interface UpdateJdbcOperations {
    /**
     * Executes an SQL update query (in general, it can be any query without a result) and returns number of updated rows.
     * @param sql SQL to execute (must be not SELECT)
     * @return number of updated rows
     * @throws SQLException when the query execution fails
     */
    long update(String sql) throws SQLException;

    /**
     * Executes an SQL update query (in general, it can be any query without a result) and returns number of updated rows.
     * @param sql SQL to execute (must be not SELECT)
     * @param preparedStatementSetter prepared statement setter
     * @return number of updated rows
     * @throws SQLException when the query execution fails
     */
    long update(String sql, PreparedStatementSetter preparedStatementSetter) throws SQLException;
}

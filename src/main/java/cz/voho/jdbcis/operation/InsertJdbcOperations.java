package cz.voho.jdbcis.operation;

import java.sql.SQLException;

/**
 * Interface for JDBC INSERT operations.
 */
public interface InsertJdbcOperations {
    /**
     * Executes an SQL INSERT query and returns a number of inserted rows.
     * @param sql SQL to execute (must by INSERT)
     * @return number of inserted rows
     * @throws SQLException when the query execution fails
     */
    long insert(String sql) throws SQLException;

    /**
     * Executes an SQL INSERT query and returns a number of inserted rows.
     * @param sql SQL to execute (must by INSERT)
     * @param preparedStatementSetter prepared statement setter
     * @return number of inserted rows
     * @throws SQLException when the query execution fails
     */
    long insert(String sql, PreparedStatementSetter preparedStatementSetter) throws SQLException;

    /**
     * Executes an SQL INSERT query and returns the generated key.
     * @param sql SQL to execute (must by INSERT)
     * @param generatedKeyRowMapper generated key row mapper
     * @param <K> type of key
     * @return generated key
     * @throws SQLException when the query execution fails
     */
    <K> K insertSingleAndGetKey(String sql, RowMapper<K> generatedKeyRowMapper) throws SQLException;

    /**
     * Executes an SQL INSERT query and returns the generated key.
     * @param sql SQL to execute (must by INSERT)
     * @param generatedKeyRowMapper generated key row mapper
     * @param preparedStatementSetter prepared statement setter
     * @param <K> type of key
     * @return generated key
     * @throws SQLException when the query execution fails
     */
    <K> K insertSingleAndGetKey(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper<K> generatedKeyRowMapper) throws SQLException;

    /**
     * Executes an SQL INSERT query and returns the generated numeric key as long.
     * @param sql SQL to execute (must by INSERT)
     * @return generated numeric key
     * @throws SQLException when the query execution fails
     */
    Long insertSingleAndGetFirstLongKey(String sql) throws SQLException;

    /**
     * Executes an SQL INSERT query and returns the generated numeric key as long.
     * @param sql SQL to execute (must by INSERT)
     * @param preparedStatementSetter prepared statement setter
     * @return generated numeric key
     * @throws SQLException when the query execution fails
     */
    Long insertSingleAndGetFirstLongKey(String sql, PreparedStatementSetter preparedStatementSetter) throws SQLException;
}

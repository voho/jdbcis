package cz.voho.jdbcis.operation;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for JDBC SELECT operations.
 */
interface SelectJdbcOperations {
    /**
     * Executes a query and uses the provided row mapper to map its results into domain models.
     * @param sql SQL query to execute (SELECT)
     * @param rowMapper row mapper
     * @param <T> domain model object type
     * @return list of domain model objects
     * @throws SQLException on SQL error
     */
    <T> List<T> queryForList(String sql, RowMapper<T> rowMapper) throws SQLException;

    /**
     * Executes a query and uses the provided row mapper to map its results into domain models.
     * @param sql SQL query to execute (SELECT)
     * @param preparedStatementSetter to set prepared statement parameters
     * @param rowMapper row mapper
     * @param <T> domain model object type
     * @return list of domain model objects
     * @throws SQLException on SQL error
     */
    <T> List<T> queryForList(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper<T> rowMapper) throws SQLException;

    /**
     * Executes a query and uses the provided row mapper to map its result into domain model.
     * The query must return a single row, otherwise, an exception is thrown.
     * @param sql SQL query to execute (SELECT)
     * @param rowMapper row mapper
     * @param <T> domain model object type
     * @return domain model object
     * @throws SQLException on SQL error
     * @see cz.voho.jdbcis.exception.IncorrectResultSizeSQLException
     */
    <T> T queryForSingle(String sql, RowMapper<T> rowMapper) throws SQLException;

    /**
     * Executes a query and uses the provided row mapper to map its result into domain model.
     * The query must return a single row, otherwise, an exception is thrown.
     * @param sql SQL query to execute (SELECT)
     * @param preparedStatementSetter to set prepared statement parameters
     * @param rowMapper row mapper
     * @param <T> domain model object type
     * @return domain model object
     * @throws SQLException on SQL error
     * @see cz.voho.jdbcis.exception.IncorrectResultSizeSQLException
     */
    <T> T queryForSingle(String sql, PreparedStatementSetter preparedStatementSetter, RowMapper<T> rowMapper) throws SQLException;
}

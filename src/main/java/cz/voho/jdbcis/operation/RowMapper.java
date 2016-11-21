package cz.voho.jdbcis.operation;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * An interface of a row mapper.
 * Row mapper is an object that converts rows from result sets to domain models.
 * @param <T> resulting object type
 */
@FunctionalInterface
public interface RowMapper<T> {
    /**
     * Converts a single row from a result set into a domain model instance.
     * @param resultSet result set
     * @return domain model instance
     * @throws SQLException on SQL error
     */
    T apply(ResultSet resultSet) throws SQLException;
}

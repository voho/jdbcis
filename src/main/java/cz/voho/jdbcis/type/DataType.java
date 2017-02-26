package cz.voho.jdbcis.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Abstraction for a single data type.
 * Provides easy and transparent conversion between SQL and Java data type.
 * Removes the necessity of dealing with the {@link ResultSet#wasNull()} methods.
 */
public interface DataType<T> {
    T getFromResultSet(ResultSet resultSet, int columnIndex) throws SQLException;

    default T getFromResultSet(ResultSet resultSet, String columnName) throws SQLException {
        return getFromResultSet(resultSet, resultSet.findColumn(columnName));
    }

    void setToPreparedStatement(PreparedStatement preparedStatement, int parameterIndex, T value) throws SQLException;
}

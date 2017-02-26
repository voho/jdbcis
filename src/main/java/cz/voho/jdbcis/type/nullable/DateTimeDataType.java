package cz.voho.jdbcis.type.nullable;

import cz.voho.jdbcis.type.DataType;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * Abstraction for the LocalDateTime type.
 * @see Types#TIMESTAMP
 */
public class DateTimeDataType implements DataType<LocalDateTime> {
    @Override
    public LocalDateTime getFromResultSet(final ResultSet resultSet, final int columnIndex) throws SQLException {
        final Timestamp result = resultSet.getTimestamp(columnIndex);
        if (result == null || resultSet.wasNull()) {
            return null;
        }
        return result.toLocalDateTime();
    }

    @Override
    public void setToPreparedStatement(final PreparedStatement preparedStatement, final int parameterIndex, final LocalDateTime value) throws SQLException {
        preparedStatement.setObject(parameterIndex, value == null ? null : Timestamp.valueOf(value), Types.TIMESTAMP);
    }
}

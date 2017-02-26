package cz.voho.jdbcis.type.nullable;

import cz.voho.jdbcis.type.DataType;

import java.sql.*;
import java.time.LocalTime;

/**
 * Abstraction for the LocalTime type.
 * @see Types#TIME
 */
public class TimeDataType implements DataType<LocalTime> {
    @Override
    public LocalTime getFromResultSet(final ResultSet resultSet, final int columnIndex) throws SQLException {
        final Time result = resultSet.getTime(columnIndex);
        if (result == null || resultSet.wasNull()) {
            return null;
        }
        return result.toLocalTime();
    }

    @Override
    public void setToPreparedStatement(final PreparedStatement preparedStatement, final int parameterIndex, final LocalTime value) throws SQLException {
        preparedStatement.setObject(parameterIndex, value == null ? null : Time.valueOf(value), Types.TIME);
    }
}

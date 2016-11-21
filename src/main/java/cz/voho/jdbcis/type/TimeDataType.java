package cz.voho.jdbcis.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.time.LocalTime;

/**
 * Abstraction for the LocalTime type.
 * @see Types#TIME
 */
public class TimeDataType implements DataType<LocalTime> {
    @Override
    public LocalTime getNullableFromResultSet(final ResultSet resultSet, final int columnIndex) throws SQLException {
        final Time result = resultSet.getTime(columnIndex);
        if (result == null || resultSet.wasNull()) {
            return null;
        }
        return result.toLocalTime();
    }

    @Override
    public LocalTime getNullableFromResultSet(final ResultSet resultSet, final String columnName) throws SQLException {
        final Time result = resultSet.getTime(columnName);
        if (result == null || resultSet.wasNull()) {
            return null;
        }
        return result.toLocalTime();
    }

    @Override
    public void setNullableToPreparedStatement(final PreparedStatement preparedStatement, final int parameterIndex, final LocalTime value) throws SQLException {
        preparedStatement.setObject(parameterIndex, value, Types.TIME);
    }
}
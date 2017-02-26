package cz.voho.jdbcis.type.nullable;

import cz.voho.jdbcis.type.DataType;

import java.sql.*;
import java.time.LocalDate;

/**
 * Abstraction for the LocalDate type.
 * @see Types#DATE
 */
public class DateDataType implements DataType<LocalDate> {
    @Override
    public LocalDate getFromResultSet(final ResultSet resultSet, final int columnIndex) throws SQLException {
        final Date result = resultSet.getDate(columnIndex);
        if (result == null || resultSet.wasNull()) {
            return null;
        }
        return result.toLocalDate();
    }

    @Override
    public void setToPreparedStatement(final PreparedStatement preparedStatement, final int parameterIndex, final LocalDate value) throws SQLException {
        preparedStatement.setObject(parameterIndex, value == null ? null : Date.valueOf(value), Types.DATE);
    }
}

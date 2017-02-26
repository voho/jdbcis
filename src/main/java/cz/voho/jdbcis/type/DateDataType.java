package cz.voho.jdbcis.type;

import java.sql.*;
import java.time.LocalDate;

/**
 * Abstraction for the LocalDate type.
 * @see Types#DATE
 */
public class DateDataType implements DataType<LocalDate> {
    @Override
    public LocalDate getNullableFromResultSet(final ResultSet resultSet, final int columnIndex) throws SQLException {
        final Date result = resultSet.getDate(columnIndex);
        if (result == null || resultSet.wasNull()) {
            return null;
        }
        return result.toLocalDate();
    }

    @Override
    public void setNullableToPreparedStatement(final PreparedStatement preparedStatement, final int parameterIndex, final LocalDate value) throws SQLException {
        preparedStatement.setObject(parameterIndex, Date.valueOf(value), Types.DATE);
    }
}

package cz.voho.jdbcis.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Abstraction for the Long type.
 * @see Types#BIGINT
 */
public class LongDataType implements DataType<Long> {
    @Override
    public Long getNullableFromResultSet(final ResultSet resultSet, final int columnIndex) throws SQLException {
        final long result = resultSet.getLong(columnIndex);
        if (resultSet.wasNull()) {
            return null;
        }
        return result;
    }

    @Override
    public Long getNullableFromResultSet(final ResultSet resultSet, final String columnName) throws SQLException {
        final long result = resultSet.getLong(columnName);
        if (resultSet.wasNull()) {
            return null;
        }
        return result;
    }

    @Override
    public void setNullableToPreparedStatement(final PreparedStatement preparedStatement, final int parameterIndex, final Long value) throws SQLException {
        preparedStatement.setObject(parameterIndex, value, Types.BIGINT);
    }
}

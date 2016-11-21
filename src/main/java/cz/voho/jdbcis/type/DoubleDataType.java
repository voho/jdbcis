package cz.voho.jdbcis.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Abstraction for the Double type.
 * @see Types#DOUBLE
 */
public class DoubleDataType implements DataType<Double> {
    @Override
    public Double getNullableFromResultSet(final ResultSet resultSet, final int columnIndex) throws SQLException {
        final double result = resultSet.getDouble(columnIndex);
        if (resultSet.wasNull()) {
            return null;
        }
        return result;
    }

    @Override
    public Double getNullableFromResultSet(final ResultSet resultSet, final String columnName) throws SQLException {
        final double result = resultSet.getDouble(columnName);
        if (resultSet.wasNull()) {
            return null;
        }
        return result;
    }

    @Override
    public void setNullableToPreparedStatement(final PreparedStatement preparedStatement, final int parameterIndex, final Double value) throws SQLException {
        preparedStatement.setObject(parameterIndex, value, Types.DOUBLE);
    }
}

package cz.voho.jdbcis.type.nullable;

import cz.voho.jdbcis.type.DataType;

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
    public Double getFromResultSet(final ResultSet resultSet, final int columnIndex) throws SQLException {
        final double result = resultSet.getDouble(columnIndex);
        if (resultSet.wasNull()) {
            return null;
        }
        return result;
    }

    @Override
    public void setToPreparedStatement(final PreparedStatement preparedStatement, final int parameterIndex, final Double value) throws SQLException {
        preparedStatement.setObject(parameterIndex, value, Types.DOUBLE);
    }
}

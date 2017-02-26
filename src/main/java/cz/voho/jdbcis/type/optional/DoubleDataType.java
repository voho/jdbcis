package cz.voho.jdbcis.type.optional;

import cz.voho.jdbcis.type.DataType;
import cz.voho.jdbcis.type.NullableDataTypes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.OptionalDouble;

/**
 * Abstraction for the Double type.
 * @see Types#DOUBLE
 */
public class DoubleDataType implements DataType<OptionalDouble> {
    @Override
    public OptionalDouble getFromResultSet(final ResultSet resultSet, final int columnIndex) throws SQLException {
        Double nullable = NullableDataTypes.DOUBLE.getFromResultSet(resultSet, columnIndex);
        return nullable == null ? OptionalDouble.empty() : OptionalDouble.of(nullable);
    }

    @Override
    public void setToPreparedStatement(final PreparedStatement preparedStatement, final int parameterIndex, final OptionalDouble value) throws SQLException {
        NullableDataTypes.DOUBLE.setToPreparedStatement(preparedStatement, parameterIndex, value.isPresent() ? value.getAsDouble() : null);
    }
}

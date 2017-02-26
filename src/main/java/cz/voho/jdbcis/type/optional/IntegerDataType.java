package cz.voho.jdbcis.type.optional;

import cz.voho.jdbcis.type.DataType;
import cz.voho.jdbcis.type.NullableDataTypes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.OptionalInt;

/**
 * Abstraction for the Integer type.
 * @see Types#INTEGER
 */
public class IntegerDataType implements DataType<OptionalInt> {
    @Override
    public OptionalInt getFromResultSet(final ResultSet resultSet, final int columnIndex) throws SQLException {
        Integer nullable = NullableDataTypes.INTEGER.getFromResultSet(resultSet, columnIndex);
        return nullable == null ? OptionalInt.empty() : OptionalInt.of(nullable);
    }

    @Override
    public void setToPreparedStatement(final PreparedStatement preparedStatement, final int parameterIndex, final OptionalInt value) throws SQLException {
        NullableDataTypes.INTEGER.setToPreparedStatement(preparedStatement, parameterIndex, value.isPresent() ? value.getAsInt() : null);
    }
}

package cz.voho.jdbcis.type.optional;

import cz.voho.jdbcis.type.DataType;
import cz.voho.jdbcis.type.NullableDataTypes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.OptionalLong;

/**
 * Abstraction for the Long type.
 * @see Types#BIGINT
 */
public class LongDataType implements DataType<OptionalLong> {
    @Override
    public OptionalLong getFromResultSet(final ResultSet resultSet, final int columnIndex) throws SQLException {
        Long nullable = NullableDataTypes.LONG.getFromResultSet(resultSet, columnIndex);
        return nullable == null ? OptionalLong.empty() : OptionalLong.of(nullable);
    }

    @Override
    public void setToPreparedStatement(final PreparedStatement preparedStatement, final int parameterIndex, final OptionalLong value) throws SQLException {
        NullableDataTypes.LONG.setToPreparedStatement(preparedStatement, parameterIndex, value.isPresent() ? value.getAsLong() : null);
    }
}

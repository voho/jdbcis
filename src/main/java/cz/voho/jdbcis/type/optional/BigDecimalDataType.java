package cz.voho.jdbcis.type.optional;

import cz.voho.jdbcis.type.DataType;
import cz.voho.jdbcis.type.NullableDataTypes;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Optional;

/**
 * Abstraction for the BigDecimal type.
 * @see Types#DECIMAL
 */
public class BigDecimalDataType implements DataType<Optional<BigDecimal>> {
    @Override
    public Optional<BigDecimal> getFromResultSet(final ResultSet resultSet, final int columnIndex) throws SQLException {
        return Optional.ofNullable(NullableDataTypes.BIG_DECIMAL.getFromResultSet(resultSet, columnIndex));
    }

    @Override
    public void setToPreparedStatement(final PreparedStatement preparedStatement, final int parameterIndex, final Optional<BigDecimal> value) throws SQLException {
        NullableDataTypes.BIG_DECIMAL.setToPreparedStatement(preparedStatement, parameterIndex, value.isPresent() ? value.get() : null);
    }
}

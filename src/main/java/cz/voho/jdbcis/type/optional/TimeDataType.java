package cz.voho.jdbcis.type.optional;

import cz.voho.jdbcis.type.DataType;
import cz.voho.jdbcis.type.NullableDataTypes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalTime;
import java.util.Optional;

/**
 * Abstraction for the LocalTime type.
 * @see Types#TIME
 */
public class TimeDataType implements DataType<Optional<LocalTime>> {
    @Override
    public Optional<LocalTime> getFromResultSet(final ResultSet resultSet, final int columnIndex) throws SQLException {
        return Optional.ofNullable(NullableDataTypes.TIME.getFromResultSet(resultSet, columnIndex));
    }

    @Override
    public void setToPreparedStatement(final PreparedStatement preparedStatement, final int parameterIndex, final Optional<LocalTime> value) throws SQLException {
        NullableDataTypes.TIME.setToPreparedStatement(preparedStatement, parameterIndex, value.isPresent() ? value.get() : null);
    }
}

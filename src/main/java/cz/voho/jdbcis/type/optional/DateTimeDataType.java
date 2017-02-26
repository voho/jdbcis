package cz.voho.jdbcis.type.optional;

import cz.voho.jdbcis.type.DataType;
import cz.voho.jdbcis.type.NullableDataTypes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Abstraction for the LocalDateTime type.
 * @see Types#TIMESTAMP
 */
public class DateTimeDataType implements DataType<Optional<LocalDateTime>> {
    @Override
    public Optional<LocalDateTime> getFromResultSet(final ResultSet resultSet, final int columnIndex) throws SQLException {
        return Optional.ofNullable(NullableDataTypes.DATE_TIME.getFromResultSet(resultSet, columnIndex));
    }

    @Override
    public void setToPreparedStatement(final PreparedStatement preparedStatement, final int parameterIndex, final Optional<LocalDateTime> value) throws SQLException {
        NullableDataTypes.DATE_TIME.setToPreparedStatement(preparedStatement, parameterIndex, value.isPresent() ? value.get() : null);
    }
}

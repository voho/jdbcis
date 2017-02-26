package cz.voho.jdbcis.type.optional;

import cz.voho.jdbcis.type.DataType;
import cz.voho.jdbcis.type.NullableDataTypes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Abstraction for the LocalDate type.
 * @see Types#DATE
 */
public class DateDataType implements DataType<Optional<LocalDate>> {
    @Override
    public Optional<LocalDate> getFromResultSet(final ResultSet resultSet, final int columnIndex) throws SQLException {
        return Optional.ofNullable(NullableDataTypes.DATE.getFromResultSet(resultSet, columnIndex));
    }

    @Override
    public void setToPreparedStatement(final PreparedStatement preparedStatement, final int parameterIndex, final Optional<LocalDate> value) throws SQLException {
        NullableDataTypes.DATE.setToPreparedStatement(preparedStatement, parameterIndex, value.isPresent() ? value.get() : null);
    }
}

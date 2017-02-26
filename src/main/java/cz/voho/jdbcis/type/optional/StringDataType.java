package cz.voho.jdbcis.type.optional;

import cz.voho.jdbcis.type.DataType;
import cz.voho.jdbcis.type.NullableDataTypes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Abstraction for the String type.
 */
public class StringDataType implements DataType<Optional<String>> {
    public Optional<String> getFromResultSet(final ResultSet resultSet, final int columnIndex) throws SQLException {
        return Optional.ofNullable(NullableDataTypes.STRING.getFromResultSet(resultSet, columnIndex));
    }

    public void setToPreparedStatement(final PreparedStatement preparedStatement, final int parameterIndex, final Optional<String> value) throws SQLException {
        NullableDataTypes.STRING.setToPreparedStatement(preparedStatement, parameterIndex, value.isPresent() ? value.get() : null);
    }
}

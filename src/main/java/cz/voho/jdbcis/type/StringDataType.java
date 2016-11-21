package cz.voho.jdbcis.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Abstraction for the String type.
 */
public class StringDataType implements DataType<String> {
    public String getNullableFromResultSet(final ResultSet resultSet, final int columnIndex) throws SQLException {
        return resultSet.getString(columnIndex);
    }

    @Override
    public String getNullableFromResultSet(final ResultSet resultSet, final String columnLabel) throws SQLException {
        return resultSet.getString(columnLabel);
    }

    public void setNullableToPreparedStatement(final PreparedStatement preparedStatement, final int parameterIndex, final String value) throws SQLException {
        preparedStatement.setString(parameterIndex, value);
    }
}

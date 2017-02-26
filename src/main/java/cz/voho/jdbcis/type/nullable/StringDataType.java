package cz.voho.jdbcis.type.nullable;

import cz.voho.jdbcis.type.DataType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Abstraction for the String type.
 */
public class StringDataType implements DataType<String> {
    public String getFromResultSet(final ResultSet resultSet, final int columnIndex) throws SQLException {
        return resultSet.getString(columnIndex);
    }

    public void setToPreparedStatement(final PreparedStatement preparedStatement, final int parameterIndex, final String value) throws SQLException {
        preparedStatement.setString(parameterIndex, value);
    }
}

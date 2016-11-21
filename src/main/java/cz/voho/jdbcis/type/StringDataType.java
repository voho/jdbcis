package cz.voho.jdbcis.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vojta on 21/11/2016.
 */
public class StringDataType implements DataType<String> {
    public String getNullableFromResultSet(ResultSet resultSet, int columnIndex) throws SQLException {
        return resultSet.getString(columnIndex);
    }

    @Override
    public String getNullableFromResultSet(ResultSet resultSet, String columnLabel) throws SQLException {
        return resultSet.getString(columnLabel);
    }

    public void setNullableToPreparedStatement(PreparedStatement preparedStatement, int parameterIndex, String value) throws SQLException {
        preparedStatement.setString(parameterIndex, value);
    }
}

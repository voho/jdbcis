package cz.voho.jdbcis.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by vojta on 21/11/2016.
 */
public class IntegerDataType implements DataType<Integer> {
    @Override
    public Integer getNullableFromResultSet(ResultSet resultSet, int columnIndex) throws SQLException {
        int result = resultSet.getInt(columnIndex);
        if (resultSet.wasNull()) {
            return null;
        }
        return result;
    }

    @Override
    public Integer getNullableFromResultSet(ResultSet resultSet, String columnName) throws SQLException {
        int result = resultSet.getInt(columnName);
        if (resultSet.wasNull()) {
            return null;
        }
        return result;
    }

    @Override
    public void setNullableToPreparedStatement(PreparedStatement preparedStatement, int parameterIndex, Integer value) throws SQLException {
        preparedStatement.setObject(parameterIndex, value, Types.INTEGER);
    }
}

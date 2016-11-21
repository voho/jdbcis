package cz.voho.jdbcis.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by vojta on 21/11/2016.
 */
public class LongDataType implements DataType<Long> {
    @Override
    public Long getNullableFromResultSet(ResultSet resultSet, int columnIndex) throws SQLException {
        long result = resultSet.getLong(columnIndex);
        if (resultSet.wasNull()) {
            return null;
        }
        return result;
    }

    @Override
    public Long getNullableFromResultSet(ResultSet resultSet, String columnName) throws SQLException {
        long result = resultSet.getLong(columnName);
        if (resultSet.wasNull()) {
            return null;
        }
        return result;
    }

    @Override
    public void setNullableToPreparedStatement(PreparedStatement preparedStatement, int parameterIndex, Long value) throws SQLException {
        preparedStatement.setObject(parameterIndex, value, Types.BIGINT);
    }
}

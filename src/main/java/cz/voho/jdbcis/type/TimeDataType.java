package cz.voho.jdbcis.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.time.LocalTime;

/**
 * Created by vojta on 21/11/2016.
 */
public class TimeDataType implements DataType<LocalTime> {
    @Override
    public LocalTime getNullableFromResultSet(ResultSet resultSet, int columnIndex) throws SQLException {
        Time result = resultSet.getTime(columnIndex);
        if (result == null || resultSet.wasNull()) {
            return null;
        }
        return result.toLocalTime();
    }

    @Override
    public LocalTime getNullableFromResultSet(ResultSet resultSet, String columnName) throws SQLException {
        Time result = resultSet.getTime(columnName);
        if (result == null || resultSet.wasNull()) {
            return null;
        }
        return result.toLocalTime();
    }

    @Override
    public void setNullableToPreparedStatement(PreparedStatement preparedStatement, int parameterIndex, LocalTime value) throws SQLException {
        preparedStatement.setObject(parameterIndex, value, Types.TIME);
    }
}

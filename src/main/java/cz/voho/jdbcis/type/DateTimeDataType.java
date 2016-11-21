package cz.voho.jdbcis.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;

/**
 * Created by vojta on 21/11/2016.
 */
public class DateTimeDataType implements DataType<LocalDateTime> {
    @Override
    public LocalDateTime getNullableFromResultSet(ResultSet resultSet, int columnIndex) throws SQLException {
        Timestamp result = resultSet.getTimestamp(columnIndex);
        if (result == null || resultSet.wasNull()) {
            return null;
        }
        return result.toLocalDateTime();
    }

    @Override
    public LocalDateTime getNullableFromResultSet(ResultSet resultSet, String columnName) throws SQLException {
        Timestamp result = resultSet.getTimestamp(columnName);
        if (result == null || resultSet.wasNull()) {
            return null;
        }
        return result.toLocalDateTime();
    }

    @Override
    public void setNullableToPreparedStatement(PreparedStatement preparedStatement, int parameterIndex, LocalDateTime value) throws SQLException {
        preparedStatement.setObject(parameterIndex, Timestamp.valueOf(value), Types.TIMESTAMP);
    }
}

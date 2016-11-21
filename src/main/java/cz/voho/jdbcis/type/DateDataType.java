package cz.voho.jdbcis.type;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

/**
 * Created by vojta on 21/11/2016.
 */
public class DateDataType implements DataType<LocalDate> {
    @Override
    public LocalDate getNullableFromResultSet(ResultSet resultSet, int columnIndex) throws SQLException {
        Date result = resultSet.getDate(columnIndex);
        if (result == null || resultSet.wasNull()) {
            return null;
        }
        return result.toLocalDate();
    }

    @Override
    public LocalDate getNullableFromResultSet(ResultSet resultSet, String columnName) throws SQLException {
        Date result = resultSet.getDate(columnName);
        if (result == null || resultSet.wasNull()) {
            return null;
        }
        return result.toLocalDate();
    }

    @Override
    public void setNullableToPreparedStatement(PreparedStatement preparedStatement, int parameterIndex, LocalDate value) throws SQLException {
        preparedStatement.setObject(parameterIndex, Date.valueOf(value), Types.DATE);
    }
}

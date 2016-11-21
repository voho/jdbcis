package cz.voho.jdbcis.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by vojta on 21/11/2016.
 */
public class DoubleDataType implements DataType<Double> {
    @Override
    public Double getNullableFromResultSet(ResultSet resultSet, int columnIndex) throws SQLException {
        double result = resultSet.getDouble(columnIndex);
        if (resultSet.wasNull()) {
            return null;
        }
        return result;
    }

    @Override
    public Double getNullableFromResultSet(ResultSet resultSet, String columnName) throws SQLException {
        double result = resultSet.getDouble(columnName);
        if (resultSet.wasNull()) {
            return null;
        }
        return result;
    }

    @Override
    public void setNullableToPreparedStatement(PreparedStatement preparedStatement, int parameterIndex, Double value) throws SQLException {
        preparedStatement.setObject(parameterIndex, value, Types.DOUBLE);
    }
}

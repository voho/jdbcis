package cz.voho.jdbcis.type;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by vojta on 21/11/2016.
 */
public class BigDecimalDataType implements DataType<BigDecimal> {
    @Override
    public BigDecimal getNullableFromResultSet(ResultSet resultSet, int columnIndex) throws SQLException {
        return resultSet.getBigDecimal(columnIndex);
    }

    @Override
    public BigDecimal getNullableFromResultSet(ResultSet resultSet, String columnName) throws SQLException {
        return resultSet.getBigDecimal(columnName);
    }

    @Override
    public void setNullableToPreparedStatement(PreparedStatement preparedStatement, int parameterIndex, BigDecimal value) throws SQLException {
        preparedStatement.setObject(parameterIndex, value, Types.DECIMAL);
    }
}

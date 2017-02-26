package cz.voho.jdbcis.type.nullable;

import cz.voho.jdbcis.type.DataType;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Abstraction for the BigDecimal type.
 * @see Types#DECIMAL
 */
public class BigDecimalDataType implements DataType<BigDecimal> {
    @Override
    public BigDecimal getFromResultSet(final ResultSet resultSet, final int columnIndex) throws SQLException {
        return resultSet.getBigDecimal(columnIndex);
    }

    @Override
    public void setToPreparedStatement(final PreparedStatement preparedStatement, final int parameterIndex, final BigDecimal value) throws SQLException {
        preparedStatement.setObject(parameterIndex, value, Types.DECIMAL);
    }
}

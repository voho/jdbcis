package cz.voho.jdbcis.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Abstraction for the Integer type.
 * @see Types#INTEGER
 */
public class IntegerDataType implements DataType<Integer> {
    @Override
    public Integer getNullableFromResultSet(final ResultSet resultSet, final int columnIndex) throws SQLException {
        final int result = resultSet.getInt(columnIndex);
        if (resultSet.wasNull()) {
            return null;
        }
        return result;
    }

    @Override
    public void setNullableToPreparedStatement(final PreparedStatement preparedStatement, final int parameterIndex, final Integer value) throws SQLException {
        preparedStatement.setObject(parameterIndex, value, Types.INTEGER);
    }
}

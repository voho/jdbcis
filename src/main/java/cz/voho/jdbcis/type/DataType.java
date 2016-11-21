package cz.voho.jdbcis.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Abstraction for a single data type.
 * Provides easy and transparent conversion between SQL and Java data type.
 * Removes the necessity of dealing with the {@link ResultSet#wasNull()} methods.
 */
public interface DataType<T> {
    BigDecimalDataType BIG_DECIMAL = new BigDecimalDataType();
    DateTimeDataType DATE_TIME = new DateTimeDataType();
    DateDataType DATE = new DateDataType();
    DoubleDataType DOUBLE = new DoubleDataType();
    IntegerDataType INTEGER = new IntegerDataType();
    LongDataType LONG = new LongDataType();
    StringDataType STRING = new StringDataType();
    TimeDataType TIME = new TimeDataType();

    T getNullableFromResultSet(ResultSet resultSet, int columnIndex) throws SQLException;

    T getNullableFromResultSet(ResultSet resultSet, String columnName) throws SQLException;

    void setNullableToPreparedStatement(PreparedStatement preparedStatement, int parameterIndex, T value) throws SQLException;
}

package cz.voho.jdbcis.operation.model;

import cz.voho.jdbcis.operation.RowMapper;
import cz.voho.jdbcis.type.DataType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Test model object.
 */
public class TestRow {
    public static RowMapper<TestRow> ROW_MAPPER = resultSet -> {
        TestRow row = new TestRow();
        // primary key
        row.setcLong(DataType.LONG.getNullableFromResultSet(resultSet, "c_pk"));
        // other columns
        row.setcLocalDate(DataType.DATE.getNullableFromResultSet(resultSet, "c_date"));
        row.setcLocalDateTime(DataType.DATE_TIME.getNullableFromResultSet(resultSet, "c_timestamp"));
        row.setcDouble(DataType.DOUBLE.getNullableFromResultSet(resultSet, "c_double"));
        row.setcInteger(DataType.INTEGER.getNullableFromResultSet(resultSet, "c_integer"));
        row.setcLong(DataType.LONG.getNullableFromResultSet(resultSet, "c_bigint"));
        row.setcString(DataType.STRING.getNullableFromResultSet(resultSet, "c_text"));
        row.setcLocalTime(DataType.TIME.getNullableFromResultSet(resultSet, "c_time"));
        return row;
    };

    private BigDecimal cBigDecimal;
    private LocalDate cLocalDate;
    private LocalDateTime cLocalDateTime;
    private Double cDouble;
    private Integer cInteger;
    private Long cLong;
    private String cString;
    private LocalTime cLocalTime;

    public BigDecimal getcBigDecimal() {
        return cBigDecimal;
    }

    public void setcBigDecimal(BigDecimal cBigDecimal) {
        this.cBigDecimal = cBigDecimal;
    }

    public LocalDate getcLocalDate() {
        return cLocalDate;
    }

    public void setcLocalDate(LocalDate cLocalDate) {
        this.cLocalDate = cLocalDate;
    }

    public LocalDateTime getcLocalDateTime() {
        return cLocalDateTime;
    }

    public void setcLocalDateTime(LocalDateTime cLocalDateTime) {
        this.cLocalDateTime = cLocalDateTime;
    }

    public Double getcDouble() {
        return cDouble;
    }

    public void setcDouble(Double cDouble) {
        this.cDouble = cDouble;
    }

    public Integer getcInteger() {
        return cInteger;
    }

    public void setcInteger(Integer cInteger) {
        this.cInteger = cInteger;
    }

    public Long getcLong() {
        return cLong;
    }

    public void setcLong(Long cLong) {
        this.cLong = cLong;
    }

    public String getcString() {
        return cString;
    }

    public void setcString(String cString) {
        this.cString = cString;
    }

    public LocalTime getcLocalTime() {
        return cLocalTime;
    }

    public void setcLocalTime(LocalTime cLocalTime) {
        this.cLocalTime = cLocalTime;
    }
}

package cz.voho.jdbcis.operation.model;

import cz.voho.jdbcis.operation.RowMapper;
import cz.voho.jdbcis.type.DataType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test model object.
 */
public class TestRow {
    public static RowMapper<TestRow> ROW_MAPPER = resultSet -> {
        TestRow row = new TestRow();

        // primary key
        row.setcPK(DataType.LONG.getNullableFromResultSet(resultSet, "c_pk"));
        // other columns
        row.setcBigDecimal(DataType.BIG_DECIMAL.getNullableFromResultSet(resultSet, "c_decimal"));
        row.setcLocalDate(DataType.DATE.getNullableFromResultSet(resultSet, "c_date"));
        row.setcLocalDateTime(DataType.DATE_TIME.getNullableFromResultSet(resultSet, "c_timestamp"));
        row.setcDouble(DataType.DOUBLE.getNullableFromResultSet(resultSet, "c_double"));
        row.setcInteger(DataType.INTEGER.getNullableFromResultSet(resultSet, "c_integer"));
        row.setcLong(DataType.LONG.getNullableFromResultSet(resultSet, "c_bigint"));
        row.setcShortString(DataType.STRING.getNullableFromResultSet(resultSet, "c_varchar"));
        row.setcLongString(DataType.STRING.getNullableFromResultSet(resultSet, "c_text"));
        row.setcLocalTime(DataType.TIME.getNullableFromResultSet(resultSet, "c_time"));

        // primary key
        assertThat(DataType.LONG.getNullableFromResultSet(resultSet, 1)).isEqualTo(row.getcPK());
        // other columns
        assertThat(DataType.BIG_DECIMAL.getNullableFromResultSet(resultSet, 2)).isEqualTo(row.getcBigDecimal());
        assertThat(DataType.DATE.getNullableFromResultSet(resultSet, 3)).isEqualTo(row.getcLocalDate());
        assertThat(DataType.DATE_TIME.getNullableFromResultSet(resultSet, 4)).isEqualTo(row.getcLocalDateTime());
        assertThat(DataType.DOUBLE.getNullableFromResultSet(resultSet, 5)).isEqualTo(row.getcDouble());
        assertThat(DataType.INTEGER.getNullableFromResultSet(resultSet, 6)).isEqualTo(row.getcInteger());
        assertThat(DataType.LONG.getNullableFromResultSet(resultSet, 7)).isEqualTo(row.getcLong());
        assertThat(DataType.STRING.getNullableFromResultSet(resultSet, 8)).isEqualTo(row.getcShortString());
        assertThat(DataType.STRING.getNullableFromResultSet(resultSet, 9)).isEqualTo(row.getcLongString());
        assertThat(DataType.TIME.getNullableFromResultSet(resultSet, 10)).isEqualTo(row.getcLocalTime());

        return row;
    };

    private Long cPK;
    private BigDecimal cBigDecimal;
    private LocalDate cLocalDate;
    private LocalDateTime cLocalDateTime;
    private Double cDouble;
    private Integer cInteger;
    private Long cLong;
    private String cShortString;
    private String cLongString;
    private LocalTime cLocalTime;

    public Long getcPK() {
        return cPK;
    }

    public void setcPK(final Long cPK) {
        this.cPK = cPK;
    }

    public BigDecimal getcBigDecimal() {
        return cBigDecimal;
    }

    public void setcBigDecimal(final BigDecimal cBigDecimal) {
        this.cBigDecimal = cBigDecimal;
    }

    public LocalDate getcLocalDate() {
        return cLocalDate;
    }

    public void setcLocalDate(final LocalDate cLocalDate) {
        this.cLocalDate = cLocalDate;
    }

    public LocalDateTime getcLocalDateTime() {
        return cLocalDateTime;
    }

    public void setcLocalDateTime(final LocalDateTime cLocalDateTime) {
        this.cLocalDateTime = cLocalDateTime;
    }

    public Double getcDouble() {
        return cDouble;
    }

    public void setcDouble(final Double cDouble) {
        this.cDouble = cDouble;
    }

    public Integer getcInteger() {
        return cInteger;
    }

    public void setcInteger(final Integer cInteger) {
        this.cInteger = cInteger;
    }

    public Long getcLong() {
        return cLong;
    }

    public void setcLong(final Long cLong) {
        this.cLong = cLong;
    }

    public String getcShortString() {
        return cShortString;
    }

    public void setcShortString(final String cShortString) {
        this.cShortString = cShortString;
    }

    public String getcLongString() {
        return cLongString;
    }

    public void setcLongString(final String cLongString) {
        this.cLongString = cLongString;
    }

    public LocalTime getcLocalTime() {
        return cLocalTime;
    }

    public void setcLocalTime(final LocalTime cLocalTime) {
        this.cLocalTime = cLocalTime;
    }
}

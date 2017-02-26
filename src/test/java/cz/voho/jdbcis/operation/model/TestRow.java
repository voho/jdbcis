package cz.voho.jdbcis.operation.model;

import cz.voho.jdbcis.operation.RowMapper;
import cz.voho.jdbcis.type.NullableDataTypes;

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
        row.setcPK(NullableDataTypes.LONG.getFromResultSet(resultSet, "c_pk"));
        // other columns
        row.setcBigDecimal(NullableDataTypes.BIG_DECIMAL.getFromResultSet(resultSet, "c_decimal"));
        row.setcLocalDate(NullableDataTypes.DATE.getFromResultSet(resultSet, "c_date"));
        row.setcLocalDateTime(NullableDataTypes.DATE_TIME.getFromResultSet(resultSet, "c_timestamp"));
        row.setcDouble(NullableDataTypes.DOUBLE.getFromResultSet(resultSet, "c_double"));
        row.setcInteger(NullableDataTypes.INTEGER.getFromResultSet(resultSet, "c_integer"));
        row.setcLong(NullableDataTypes.LONG.getFromResultSet(resultSet, "c_bigint"));
        row.setcShortString(NullableDataTypes.STRING.getFromResultSet(resultSet, "c_varchar"));
        row.setcLongString(NullableDataTypes.STRING.getFromResultSet(resultSet, "c_text"));
        row.setcLocalTime(NullableDataTypes.TIME.getFromResultSet(resultSet, "c_time"));

        // primary key
        assertThat(NullableDataTypes.LONG.getFromResultSet(resultSet, 1)).isEqualTo(row.getcPK());
        // other columns
        assertThat(NullableDataTypes.BIG_DECIMAL.getFromResultSet(resultSet, 2)).isEqualTo(row.getcBigDecimal());
        assertThat(NullableDataTypes.DATE.getFromResultSet(resultSet, 3)).isEqualTo(row.getcLocalDate());
        assertThat(NullableDataTypes.DATE_TIME.getFromResultSet(resultSet, 4)).isEqualTo(row.getcLocalDateTime());
        assertThat(NullableDataTypes.DOUBLE.getFromResultSet(resultSet, 5)).isEqualTo(row.getcDouble());
        assertThat(NullableDataTypes.INTEGER.getFromResultSet(resultSet, 6)).isEqualTo(row.getcInteger());
        assertThat(NullableDataTypes.LONG.getFromResultSet(resultSet, 7)).isEqualTo(row.getcLong());
        assertThat(NullableDataTypes.STRING.getFromResultSet(resultSet, 8)).isEqualTo(row.getcShortString());
        assertThat(NullableDataTypes.STRING.getFromResultSet(resultSet, 9)).isEqualTo(row.getcLongString());
        assertThat(NullableDataTypes.TIME.getFromResultSet(resultSet, 10)).isEqualTo(row.getcLocalTime());

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

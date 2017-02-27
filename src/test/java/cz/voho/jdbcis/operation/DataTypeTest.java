package cz.voho.jdbcis.operation;

import cz.voho.jdbcis.type.NullableDataTypes;
import cz.voho.jdbcis.type.OptionalDataTypes;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

import static org.assertj.core.api.Assertions.assertThat;

public class DataTypeTest extends AbstractOperationTest {
    @Test
    public void testReadingEmptyNullableDataTypes() throws SQLException {
        toTest().queryForSingle("SELECT * from e", new RowMapper<Void>() {
            @Override
            public Void apply(ResultSet resultSet) throws SQLException {
                assertThat(NullableDataTypes.BIG_DECIMAL.getFromResultSet(resultSet, "c_decimal")).isNull();
                assertThat(NullableDataTypes.DATE.getFromResultSet(resultSet, "c_date")).isNull();
                assertThat(NullableDataTypes.DATE_TIME.getFromResultSet(resultSet, "c_timestamp")).isNull();
                assertThat(NullableDataTypes.DOUBLE.getFromResultSet(resultSet, "c_double")).isNull();
                assertThat(NullableDataTypes.INTEGER.getFromResultSet(resultSet, "c_integer")).isNull();
                assertThat(NullableDataTypes.LONG.getFromResultSet(resultSet, "c_bigint")).isNull();
                assertThat(NullableDataTypes.STRING.getFromResultSet(resultSet, "c_text")).isNull();
                assertThat(NullableDataTypes.TIME.getFromResultSet(resultSet, "c_time")).isNull();
                return null;
            }
        });
    }

    @Test
    public void testReadingEmptyOptionalDataTypes() throws SQLException {
        toTest().queryForSingle("SELECT * from e", new RowMapper<Void>() {
            @Override
            public Void apply(ResultSet resultSet) throws SQLException {
                assertThat(OptionalDataTypes.BIG_DECIMAL.getFromResultSet(resultSet, "c_decimal")).isNotPresent();
                assertThat(OptionalDataTypes.DATE.getFromResultSet(resultSet, "c_date")).isNotPresent();
                assertThat(OptionalDataTypes.DATE_TIME.getFromResultSet(resultSet, "c_timestamp")).isNotPresent();
                assertThat(OptionalDataTypes.DOUBLE.getFromResultSet(resultSet, "c_double")).isNotPresent();
                assertThat(OptionalDataTypes.INTEGER.getFromResultSet(resultSet, "c_integer")).isNotPresent();
                assertThat(OptionalDataTypes.LONG.getFromResultSet(resultSet, "c_bigint")).isNotPresent();
                assertThat(OptionalDataTypes.STRING.getFromResultSet(resultSet, "c_text")).isNotPresent();
                assertThat(OptionalDataTypes.TIME.getFromResultSet(resultSet, "c_time")).isNotPresent();
                return null;
            }
        });
    }

    @Test
    public void testWritingEmptyNullableDataTypes() throws SQLException {
        assertThat(toTest().insert("INSERT INTO e (c_decimal) VALUES (?)", ps -> NullableDataTypes.BIG_DECIMAL.setToPreparedStatement(ps, 1, null))).isEqualTo(1L);
        assertThat(toTest().insert("INSERT INTO e (c_date) VALUES (?)", ps -> NullableDataTypes.DATE.setToPreparedStatement(ps, 1, null))).isEqualTo(1L);
        assertThat(toTest().insert("INSERT INTO e (c_timestamp) VALUES (?)", ps -> NullableDataTypes.DATE_TIME.setToPreparedStatement(ps, 1, null))).isEqualTo(1L);
        assertThat(toTest().insert("INSERT INTO e (c_double) VALUES (?)", ps -> NullableDataTypes.DOUBLE.setToPreparedStatement(ps, 1, null))).isEqualTo(1L);
        assertThat(toTest().insert("INSERT INTO e (c_integer) VALUES (?)", ps -> NullableDataTypes.INTEGER.setToPreparedStatement(ps, 1, null))).isEqualTo(1L);
        assertThat(toTest().insert("INSERT INTO e (c_bigint) VALUES (?)", ps -> NullableDataTypes.LONG.setToPreparedStatement(ps, 1, null))).isEqualTo(1L);
        assertThat(toTest().insert("INSERT INTO e (c_text) VALUES (?)", ps -> NullableDataTypes.STRING.setToPreparedStatement(ps, 1, null))).isEqualTo(1L);
        assertThat(toTest().insert("INSERT INTO e (c_time) VALUES (?)", ps -> NullableDataTypes.TIME.setToPreparedStatement(ps, 1, null))).isEqualTo(1L);
    }

    @Test
    public void testWritingEmptyOptionalDataTypes() throws SQLException {
        assertThat(toTest().insert("INSERT INTO e (c_decimal) VALUES (?)", ps -> OptionalDataTypes.BIG_DECIMAL.setToPreparedStatement(ps, 1, Optional.empty()))).isEqualTo(1L);
        assertThat(toTest().insert("INSERT INTO e (c_date) VALUES (?)", ps -> OptionalDataTypes.DATE.setToPreparedStatement(ps, 1, Optional.empty()))).isEqualTo(1L);
        assertThat(toTest().insert("INSERT INTO e (c_timestamp) VALUES (?)", ps -> OptionalDataTypes.DATE_TIME.setToPreparedStatement(ps, 1, Optional.empty()))).isEqualTo(1L);
        assertThat(toTest().insert("INSERT INTO e (c_double) VALUES (?)", ps -> OptionalDataTypes.DOUBLE.setToPreparedStatement(ps, 1, OptionalDouble.empty()))).isEqualTo(1L);
        assertThat(toTest().insert("INSERT INTO e (c_integer) VALUES (?)", ps -> OptionalDataTypes.INTEGER.setToPreparedStatement(ps, 1, OptionalInt.empty()))).isEqualTo(1L);
        assertThat(toTest().insert("INSERT INTO e (c_bigint) VALUES (?)", ps -> OptionalDataTypes.LONG.setToPreparedStatement(ps, 1, OptionalLong.empty()))).isEqualTo(1L);
        assertThat(toTest().insert("INSERT INTO e (c_text) VALUES (?)", ps -> OptionalDataTypes.STRING.setToPreparedStatement(ps, 1, Optional.empty()))).isEqualTo(1L);
        assertThat(toTest().insert("INSERT INTO e (c_time) VALUES (?)", ps -> OptionalDataTypes.TIME.setToPreparedStatement(ps, 1, Optional.empty()))).isEqualTo(1L);
    }
}

package cz.voho.jdbcis.operation;

import cz.voho.jdbcis.operation.model.TestRow;
import cz.voho.jdbcis.type.NullableDataTypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SingleQueriesTest extends AbstractOperationTest {
    @Test
    public void queryForSingleRowUsingPreparedStatement() throws Exception {
        final TestRow row = toTest().queryForSingle(
                "SELECT c_pk,c_decimal,c_date,c_timestamp,c_double,c_integer,c_bigint,c_varchar,c_text,c_time FROM t WHERE c_pk = ?",
                preparedStatement -> NullableDataTypes.LONG.setToPreparedStatement(preparedStatement, 1, 1L),
                TestRow.ROW_MAPPER);

        assertThat(row.getcLongString()).isEqualTo("Predefined-1");
    }

    @Test
    public void queryForSingleRowUsingRawValues() throws Exception {
        final TestRow row = toTest().queryForSingle(
                "SELECT c_pk,c_decimal,c_date,c_timestamp,c_double,c_integer,c_bigint,c_varchar,c_text,c_time FROM t WHERE c_pk = 1",
                TestRow.ROW_MAPPER);

        assertThat(row.getcLongString()).isEqualTo("Predefined-1");
    }
}
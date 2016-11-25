package cz.voho.jdbcis.operation;

import cz.voho.jdbcis.operation.model.TestRow;
import cz.voho.jdbcis.type.DataType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ListQueriesTest extends AbstractOperationTest {
    @Test
    public void queryForListUsingPreparedStatement() throws Exception {
        final List<TestRow> list = toTest().queryForList(
                "SELECT c_pk,c_decimal,c_date,c_timestamp,c_double,c_integer,c_bigint,c_varchar,c_text,c_time FROM t WHERE c_text LIKE ?",
                preparedStatement -> DataType.STRING.setNullableToPreparedStatement(preparedStatement, 1, "Predefined-%"),
                TestRow.ROW_MAPPER
        );

        assertThat(list)
                .extracting(TestRow::getcLongString)
                .contains(
                        "Predefined-1",
                        "Predefined-2",
                        "Predefined-3",
                        "Predefined-4",
                        "Predefined-5"
                );
    }

    @Test
    public void queryForListUsingRawValues() throws Exception {
        final List<TestRow> list = toTest().queryForList(
                "SELECT c_pk,c_decimal,c_date,c_timestamp,c_double,c_integer,c_bigint,c_varchar,c_text,c_time FROM t WHERE c_text LIKE 'Predefined-%'",
                TestRow.ROW_MAPPER
        );

        assertThat(list)
                .extracting(TestRow::getcLongString)
                .contains(
                        "Predefined-1",
                        "Predefined-2",
                        "Predefined-3",
                        "Predefined-4",
                        "Predefined-5"
                );
    }
}
package cz.voho.jdbcis.operation;

import cz.voho.jdbcis.operation.model.TestRow;
import cz.voho.jdbcis.type.DataType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SingleQueriesTest extends AbstractOperationTest {
    @Test
    public void queryForSingleRowUsingPreparedStatement() throws Exception {
        TestRow row = toTest().queryForSingle(
                "SELECT * FROM t WHERE c_pk = ?",
                preparedStatement -> DataType.LONG.setNullableToPreparedStatement(preparedStatement, 1, 1L),
                TestRow.ROW_MAPPER);

        assertThat(row.getcString()).isEqualTo("Predefined-1");
    }


    @Test
    public void queryForSingleRowUsingRawValues() throws Exception {
        TestRow row = toTest().queryForSingle(
                "SELECT * FROM t WHERE c_pk = 1",
                TestRow.ROW_MAPPER);

        assertThat(row.getcString()).isEqualTo("Predefined-1");
    }
}
package cz.voho.jdbcis.operation;

import cz.voho.jdbcis.type.DataType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class InsertQueriesTest extends AbstractOperationTest {
    @Test
    public void testInsertSingleRowUsingPreparedStatement() throws Exception {
        final long actual = toTest().insert("INSERT INTO t (c_text) VALUES (?)", preparedStatement -> {
            DataType.STRING.setNullableToPreparedStatement(preparedStatement, 1, "testInsertSingleRowUsingPreparedStatement");
        });

        assertThat(actual).isEqualTo(1);
    }

    @Test
    public void testInsertMultipleRowsUsingPreparedStatement() throws Exception {
        final long actual = toTest().insert("INSERT INTO t (c_text) VALUES (?), (?)", preparedStatement -> {
            DataType.STRING.setNullableToPreparedStatement(preparedStatement, 1, "testInsertMultipleRowsUsingPreparedStatement-1");
            DataType.STRING.setNullableToPreparedStatement(preparedStatement, 2, "testInsertMultipleRowsUsingPreparedStatement-2");
        });

        assertThat(actual).isEqualTo(2);
    }

    @Test
    public void testInsertSingleRowUsingRawValues() throws Exception {
        final long actual = toTest().insert("INSERT INTO t (c_text) VALUES ('testInsertSingleRowUsingRawValues')");

        assertThat(actual).isEqualTo(1);
    }

    @Test
    public void testInsertSingleRowAndGetKeyUsingPreparedStatement() throws Exception {
        final Long actual = toTest().insertSingleAndGetFirstLongKey("INSERT INTO t (c_text) VALUES (?)", preparedStatement -> {
            DataType.STRING.setNullableToPreparedStatement(preparedStatement, 1, "testInsertSingleRowAndGetKeyUsingPreparedStatement");
        });

        assertThat(actual).isGreaterThan(1);
    }

    @Test
    public void testInsertSingleRowAndGetKeyUsingRawValues() throws Exception {
        final Long actual = toTest().insertSingleAndGetFirstLongKey("INSERT INTO t (c_text) VALUES ('testInsertSingleRowAndGetKeyUsingRawValues')");

        assertThat(actual).isGreaterThan(1);
    }
}
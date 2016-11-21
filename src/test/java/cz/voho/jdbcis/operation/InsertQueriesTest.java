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
        final long actual = toTest().insert("INSERT INTO configuration (property_id, property_value) VALUES (?, ?)", preparedStatement -> {
            DataType.INTEGER.setNullableToPreparedStatement(preparedStatement, 1, 1);
            DataType.STRING.setNullableToPreparedStatement(preparedStatement, 2, "type-1");
        });

        assertThat(actual).isEqualTo(1);
    }

    @Test
    public void testInsertMultipleRowsUsingPreparedStatement() throws Exception {
        final long actual = toTest().insert("INSERT INTO configuration (property_id, property_value) VALUES (?, ?), (?, ?)", preparedStatement -> {
            DataType.INTEGER.setNullableToPreparedStatement(preparedStatement, 1, 1);
            DataType.STRING.setNullableToPreparedStatement(preparedStatement, 2, "type-1");
            DataType.INTEGER.setNullableToPreparedStatement(preparedStatement, 3, 2);
            DataType.STRING.setNullableToPreparedStatement(preparedStatement, 4, "type-2");
        });

        assertThat(actual).isEqualTo(2);
    }

    @Test
    public void testInsertSingleRowUsingRawValues() throws Exception {
        final long actual = toTest().insert("INSERT INTO configuration (property_id, property_value) VALUES (2, 'type-2')");

        assertThat(actual).isEqualTo(1);
    }
}
package cz.voho.jdbcis.operation;

import cz.voho.jdbcis.type.DataType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UpdateQueriesTest extends AbstractOperationTest {
    @Test
    public void updateSingleRowUsingPreparedStatement() throws Exception {
        long actual = toTest().update("UPDATE t SET c_text = ?", preparedStatement -> {
            DataType.STRING.setNullableToPreparedStatement(preparedStatement, 1, "updateSingleRowUsingPreparedStatement");
        });

        assertThat(actual).isEqualTo(5L);
    }

    @Test
    public void updateSingleRowUsingRawValues() throws Exception {
        long actual = toTest().update("UPDATE t SET c_text = 'updateSingleRowUsingRawValues'");

        assertThat(actual).isEqualTo(5L);
    }
}
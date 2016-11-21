package cz.voho.jdbcis.operation;

import cz.voho.jdbcis.type.DataType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UpdateQueriesTest extends AbstractOperationTest {
    @Test
    public void updateSingleRowUsingPreparedStatement() throws Exception {
        toTest().update("UPDATE configuration SET property_value = ? WHERE property_id = ?", preparedStatement -> {
            DataType.STRING.setNullableToPreparedStatement(preparedStatement, 1, "updated-type");
            DataType.INTEGER.setNullableToPreparedStatement(preparedStatement, 2, 1000);
        });
    }

    @Test
    public void updateSingleRowUsingRawValues() throws Exception {
        toTest().update("UPDATE configuration SET property_value = 'updated-type' WHERE property_id = 1000");
    }
}
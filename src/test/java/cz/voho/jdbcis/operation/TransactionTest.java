package cz.voho.jdbcis.operation;

import cz.voho.jdbcis.type.DataType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TransactionTest extends AbstractOperationTest {
    @Test
    public void testTransaction() throws SQLException {
        toTest().runInTransaction(jdbcOperations -> {
            jdbcOperations.insert("INSERT INTO configuration (property_id, property_value) VALUES (2001, 'type-2001')");
            jdbcOperations.insert("INSERT INTO configuration (property_id, property_value) VALUES (2002, 'type-2002')");
            jdbcOperations.insert("INSERT INTO configuration (property_id, property_value) VALUES (2003, 'type-2003')");
        });

        assertThat(getRowCount()).isEqualTo(3);
    }

    @Test
    public void testTransactionRollbackOnRuntimeError() throws SQLException {
        toTest().runInTransaction(jdbcOperations -> {
            jdbcOperations.insert("INSERT INTO configuration (property_id, property_value) VALUES (2001, 'type-2001')");
            jdbcOperations.insert("INSERT INTO configuration (property_id, property_value) VALUES (2002, 'type-2002')");
            jdbcOperations.insert("INSERT INTO configuration (property_id, property_value) VALUES (2003, 'type-2003')");
            throw new IllegalStateException("Abort transaction!");
        });

        assertThat(getRowCount()).isEqualTo(0);
    }

    private int getRowCount() throws SQLException {
        return toTest().queryForSingle("SELECT COUNT(*) AS count FROM configuration WHERE property_id >= 2000", resultSet -> DataType.INTEGER.getNullableFromResultSet(resultSet, "count"));
    }
}

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
        assertThat(getRowCount()).isGreaterThan(0);

        toTest().runInTransaction(jdbcOperations -> {
            jdbcOperations.update("DELETE FROM t");
        });

        assertThat(getRowCount()).isEqualTo(0);
    }

    @Test
    public void testTransactionRollbackOnRuntimeError() throws SQLException {
        toTest().runInTransaction(jdbcOperations -> {
            jdbcOperations.update("DELETE FROM t");
            throw new IllegalStateException("Abort transaction!");
        });

        assertThat(getRowCount()).isGreaterThan(0);
    }

    private int getRowCount() throws SQLException {
        return toTest().queryForSingle("SELECT COUNT(*) AS count FROM t", resultSet -> DataType.INTEGER.getNullableFromResultSet(resultSet, "count"));
    }
}

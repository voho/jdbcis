package cz.voho.jdbcis.operation;

import cz.voho.jdbcis.type.NullableDataTypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TransactionTest extends AbstractOperationTest {
    @Test
    public void testTransaction() throws SQLException {
        int numRowsBefore = getRowCount();

        toTest().runInTransaction(jdbcOperations -> {
            jdbcOperations.update("DELETE FROM t");
        });

        int numRowsAfter = getRowCount();

        assertThat(numRowsBefore).isGreaterThan(0);
        assertThat(numRowsAfter).isEqualTo(0);
    }

    @Test
    public void testTransactionRollbackOnRuntimeError() throws SQLException {
        int numRowsBefore = getRowCount();

        toTest().runInTransaction(jdbcOperations -> {
            jdbcOperations.update("DELETE FROM t");
            throw new IllegalStateException("Abort transaction!");
        });

        int numRowsAfter = getRowCount();

        assertThat(numRowsBefore).isGreaterThan(0);
        assertThat(numRowsAfter).isEqualTo(numRowsBefore);
    }

    private int getRowCount() throws SQLException {
        return toTest().queryForSingle("SELECT COUNT(*) AS count FROM t", resultSet -> NullableDataTypes.INTEGER.getFromResultSet(resultSet, "count"));
    }
}

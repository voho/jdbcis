package cz.voho.jdbcis.operation;

import java.sql.SQLException;

/**
 * Interface for running JDBC transactions.
 */
public interface TransactionJdbcOperations {
    /**
     * Executes a transaction using the callback provided.
     * @param transactionOperations callback to perform JDBC operations
     * @throws SQLException when something goes bad inside the transaction
     */
    void runInTransaction(JdbcTransactionCallback transactionOperations) throws SQLException;
}

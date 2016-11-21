package cz.voho.jdbcis.operation;

/**
 * Transaction callback.
 */
@FunctionalInterface
public interface JdbcTransactionCallback {
    /**
     * Method that contains the transaction body.
     * The provided JDBC operations object should be used to perform all transaction actions.
     * Transaction will be rolled back if any exception is thrown in this method.
     * @param jdbcOperations JDBC operations
     * @throws Exception when something goes wrong
     */
    void execute(JdbcOperations jdbcOperations) throws Exception;
}

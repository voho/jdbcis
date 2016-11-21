package cz.voho.jdbcis.integration;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connection manager that has a single connection only.
 * This class does not manage the lifecycle of the provided connection.
 */
public class SingleConnectionManager implements ConnectionManager {
    private final Connection connection;

    public SingleConnectionManager(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection acquireConnection() throws SQLException {
        return connection;
    }

    @Override
    public void releaseConnection(final Connection unusedConnection) throws SQLException {
        if (unusedConnection != connection) {
            throw new IllegalArgumentException("The given connection does not belong to this class.");
        }
    }
}

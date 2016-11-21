package cz.voho.jdbcis.integration;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Connection manager decorator that count number of underlying active connections.
 * The counter is increased after connection is obtainted.
 * The counter is decreased after connection is released.
 */
public class CountingConnectionManager implements ConnectionManager {
    private final ConnectionManager delegate;
    private final AtomicInteger unreleasedConnectionCounter;

    public CountingConnectionManager(final ConnectionManager delegate) {
        this.delegate = delegate;
        this.unreleasedConnectionCounter = new AtomicInteger(0);
    }

    @Override
    public Connection acquireConnection() throws SQLException {
        final Connection connection = delegate.acquireConnection();
        unreleasedConnectionCounter.incrementAndGet();
        return connection;
    }

    @Override
    public void releaseConnection(final Connection unusedConnection) throws SQLException {
        delegate.releaseConnection(unusedConnection);
        unreleasedConnectionCounter.decrementAndGet();
    }

    /**
     * Returns the count of unreleased connections.
     * @return number of connections
     */
    public int getUnreleasedConnectionCount() {
        return unreleasedConnectionCounter.get();
    }
}

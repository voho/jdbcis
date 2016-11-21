package cz.voho.jdbcis.integration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connection manager implementation for a regular {@link DataSource}.
 */
public class DataSourceConnectionManager implements ConnectionManager {
    private final DataSource dataSource;

    public DataSourceConnectionManager(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Connection acquireConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void releaseConnection(final Connection unusedConnection) throws SQLException {
        // Note: not doing anything with the connection intentionally
        // (in most connection pools, the idle connections will time out eventually)
    }
}

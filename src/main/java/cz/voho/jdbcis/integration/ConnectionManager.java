package cz.voho.jdbcis.integration;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Abstraction that acquires and releases JDBC connections.
 * This is a bridge between the library and any external connection providing mechanism, e.g. connection pool.
 */
public interface ConnectionManager {
    /**
     * Should return a valid connection.
     * The connection can be e.g. taken from a pool.
     * @return valid JDBC connection
     * @throws SQLException if something goes wrong while obtaining the connection
     */
    Connection acquireConnection() throws SQLException;

    /**
     * Provides the given connection back to the manager (once it is no longer needed).
     * The connection can be e.g. given back to the pool.
     * @param unusedConnection unused connection to release
     * @throws SQLException if something goes wrong while releasing the connection
     */
    void releaseConnection(Connection unusedConnection) throws SQLException;
}

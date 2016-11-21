package cz.voho.jdbcis.operation;

import cz.voho.jdbcis.integration.CountingConnectionManager;
import cz.voho.jdbcis.integration.DataSourceConnectionManager;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.junit.After;
import org.junit.Before;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

abstract class AbstractOperationTest {
    private CountingConnectionManager connectionManager;

    JdbcOperations toTest;

    @Before
    public void setUp() throws SQLException {
        final JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        dataSource.setUser("sa");
        dataSource.setPassword("");

        final Connection connection = dataSource.getConnection();
        RunScript.execute(connection, new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("test.sql")));
        System.out.println("Created in-memory database: " + connection.getMetaData().getURL());

        connectionManager = new CountingConnectionManager(new DataSourceConnectionManager(dataSource));
        toTest = new JdbcOperations(connectionManager);
    }

    @After
    public void tearDown() {
        assertThat(connectionManager.getUnreleasedConnectionCount()).isZero();
        System.out.println("Valid connection handling verified for: " + getClass().getSimpleName());
    }
}

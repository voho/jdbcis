package cz.voho.jdbcis.operation;

import cz.voho.jdbcis.exception.IncorrectResultSizeSQLException;
import cz.voho.jdbcis.integration.ConnectionManager;
import cz.voho.jdbcis.integration.SingleConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of all the JDBC operations.
 */
public class JdbcOperations implements SelectJdbcOperations, InsertJdbcOperations, UpdateJdbcOperations, TransactionJdbcOperations {
    private final ConnectionManager connectionManager;

    public JdbcOperations(final ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public <T> List<T> queryForList(final String sql, final RowMapper<T> rowMapper) throws SQLException {
        return queryForList(sql, null, rowMapper);
    }

    @Override
    public <T> List<T> queryForList(final String sql, final PreparedStatementSetter preparedStatementSetter, final RowMapper<T> rowMapper) throws SQLException {
        final Connection connection = acquireConnection();
        final List<T> result = new ArrayList<T>();

        try (PreparedStatement preparedStatement = prepareStatement(connection, sql)) {
            if (preparedStatementSetter != null) {
                preparedStatementSetter.apply(preparedStatement);
            }
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    final T mappedRow = rowMapper.apply(resultSet);
                    result.add(mappedRow);
                }
            }
        } finally {
            releaseConnection(connection);
        }

        return result;
    }

    @Override
    public <T> T queryForSingle(final String sql, final RowMapper<T> rowMapper) throws SQLException {
        return queryForSingle(sql, null, rowMapper);
    }

    @Override
    public <T> T queryForSingle(final String sql, final PreparedStatementSetter preparedStatementSetter, final RowMapper<T> rowMapper) throws SQLException {
        final List<T> list = queryForList(sql, preparedStatementSetter, rowMapper);

        if (list.size() == 1) {
            return list.get(0);
        } else {
            throw new IncorrectResultSizeSQLException(1, list.size());
        }
    }

    @Override
    public long insert(final String sql) throws SQLException {
        return insert(sql, null);
    }

    @Override
    public long insert(final String sql, final PreparedStatementSetter preparedStatementSetter) throws SQLException {
        final Connection connection = acquireConnection();

        try (PreparedStatement preparedStatement = prepareStatement(connection, sql)) {
            if (preparedStatementSetter != null) {
                preparedStatementSetter.apply(preparedStatement);
            }

            return preparedStatement.executeUpdate();
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public <K> K insertSingleAndGetKey(final String sql, final RowMapper<K> generatedKeyRowMapper) throws SQLException {
        return insertSingleAndGetKey(sql, null, generatedKeyRowMapper);
    }

    @Override
    public <K> K insertSingleAndGetKey(final String sql, final PreparedStatementSetter preparedStatementSetter, final RowMapper<K> generatedKeyRowMapper) throws SQLException {
        final Connection connection = acquireConnection();

        try (PreparedStatement preparedStatement = prepareStatement(connection, sql)) {
            if (preparedStatementSetter != null) {
                preparedStatementSetter.apply(preparedStatement);
            }

            final int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 1) {
                try (ResultSet generatedKeysResultSet = preparedStatement.getGeneratedKeys()) {
                    return generatedKeyRowMapper.apply(generatedKeysResultSet);
                }
            } else {
                throw new IncorrectResultSizeSQLException(1, affectedRows);
            }
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public Long insertSingleAndGetFirstLongKey(final String sql) throws SQLException {
        return insertSingleAndGetFirstLongKey(sql, null);
    }

    @Override
    public Long insertSingleAndGetFirstLongKey(final String sql, final PreparedStatementSetter preparedStatementSetter) throws SQLException {
        return insertSingleAndGetKey(sql, preparedStatementSetter, resultSet -> resultSet.getLong(1));
    }

    @Override
    public long update(final String sql) throws SQLException {
        return update(sql, null);
    }

    @Override
    public long update(final String sql, final PreparedStatementSetter preparedStatementSetter) throws SQLException {
        final Connection connection = acquireConnection();

        try (PreparedStatement preparedStatement = prepareStatement(connection, sql)) {
            if (preparedStatementSetter != null) {
                preparedStatementSetter.apply(preparedStatement);
            }

            return preparedStatement.executeUpdate();
        } finally {
            releaseConnection(connection);
        }
    }

    private Connection acquireConnection() throws SQLException {
        return connectionManager.acquireConnection();
    }

    private void releaseConnection(final Connection connection) throws SQLException {
        connectionManager.releaseConnection(connection);
    }

    private PreparedStatement prepareStatement(final Connection connection, final String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    @Override
    public void runInTransaction(final JdbcTransactionCallback transactionOperations) throws SQLException {
        final Connection connection = acquireConnection();
        final boolean previousAutoCommit = connection.getAutoCommit();

        try {
            connection.setAutoCommit(false);
            final JdbcOperations transactionSpecificOperations = new JdbcOperations(new SingleConnectionManager(connection));
            transactionOperations.execute(transactionSpecificOperations);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(previousAutoCommit);
            releaseConnection(connection);
        }
    }
}

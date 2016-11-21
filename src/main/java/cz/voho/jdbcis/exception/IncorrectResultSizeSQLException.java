package cz.voho.jdbcis.exception;

import java.sql.SQLException;

/**
 * Exception thrown in cases when the result size does not correspond the expectations.
 */
public class IncorrectResultSizeSQLException extends SQLException {
    private final long expectedResultSize;
    private final long actualResultSize;

    public IncorrectResultSizeSQLException(final long expectedResultSize, final long actualResultSize) {
        super(String.format("Incorrect result size (expected = %d, actual = %d).", expectedResultSize, actualResultSize));
        this.expectedResultSize = expectedResultSize;
        this.actualResultSize = actualResultSize;
    }

    public long getExpectedResultSize() {
        return expectedResultSize;
    }

    public long getActualResultSize() {
        return actualResultSize;
    }
}

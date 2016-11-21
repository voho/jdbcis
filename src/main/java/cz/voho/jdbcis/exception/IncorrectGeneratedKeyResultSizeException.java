package cz.voho.jdbcis.exception;

import java.sql.SQLException;

/**
 * Exception thrown when incorrect number of rows is generated for keys.
 */
public class IncorrectGeneratedKeyResultSizeException extends SQLException {
    public IncorrectGeneratedKeyResultSizeException() {
        super("Incorrect result size for generated keys. Only one row is expected. Please check your INSERT SQL query.");
    }
}

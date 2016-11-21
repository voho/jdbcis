package cz.voho.jdbcis.operation.model;

/**
 * Test model object.
 */
public class ConfigurationRow {
    private final int id;
    private final String value;

    public ConfigurationRow(final int id, final String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}

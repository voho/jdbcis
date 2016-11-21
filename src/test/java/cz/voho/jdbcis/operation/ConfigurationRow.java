package cz.voho.jdbcis.operation;

class ConfigurationRow {
    private final int id;
    private final String value;

    ConfigurationRow(final int id, final String value) {
        this.id = id;
        this.value = value;
    }

    int getId() {
        return id;
    }

    String getValue() {
        return value;
    }
}

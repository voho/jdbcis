package cz.voho.jdbcis.operation;

import cz.voho.jdbcis.operation.model.ConfigurationRow;
import cz.voho.jdbcis.type.DataType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SingleQueriesTest extends AbstractOperationTest {
    @Test
    public void queryForSingleRowUsingPreparedStatement() throws Exception {
        final ConfigurationRow item = toTest().queryForSingle(
                "SELECT * FROM configuration WHERE property_id = ?",
                preparedStatement -> DataType.INTEGER.setNullableToPreparedStatement(preparedStatement, 1, 101),
                resultSet -> new ConfigurationRow(
                        DataType.INTEGER.getNullableFromResultSet(resultSet, "property_id"),
                        DataType.STRING.getNullableFromResultSet(resultSet, "property_value")
                )
        );

        assertThat(item)
                .extracting(ConfigurationRow::getId, ConfigurationRow::getValue)
                .containsOnly(101, "value-101");
    }

    @Test
    public void queryForSingleRowUsingRawValues() throws Exception {
        final ConfigurationRow item = toTest().queryForSingle(
                "SELECT * FROM configuration WHERE property_id = 101",
                resultSet -> new ConfigurationRow(
                        DataType.INTEGER.getNullableFromResultSet(resultSet, "property_id"),
                        DataType.STRING.getNullableFromResultSet(resultSet, "property_value")
                )
        );

        assertThat(item)
                .extracting(ConfigurationRow::getId, ConfigurationRow::getValue)
                .containsOnly(101, "value-101");
    }
}
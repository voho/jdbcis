package cz.voho.jdbcis.operation;

import cz.voho.jdbcis.operation.model.ConfigurationRow;
import cz.voho.jdbcis.type.DataType;
import org.assertj.core.groups.Tuple;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ListQueriesTest extends AbstractOperationTest {
    @Test
    public void queryForListUsingPreparedStatement() throws Exception {
        final List<ConfigurationRow> list = toTest().queryForList(
                "SELECT * FROM configuration WHERE property_id BETWEEN ? AND ? ORDER BY property_id",
                preparedStatement -> {
                    DataType.INTEGER.setNullableToPreparedStatement(preparedStatement, 1, 101);
                    DataType.INTEGER.setNullableToPreparedStatement(preparedStatement, 2, 103);
                }, resultSet -> new ConfigurationRow(
                        DataType.INTEGER.getNullableFromResultSet(resultSet, "property_id"),
                        DataType.STRING.getNullableFromResultSet(resultSet, "property_value")
                ));

        assertThat(list)
                .extracting(ConfigurationRow::getId, ConfigurationRow::getValue)
                .containsExactly(
                        Tuple.tuple(101, "value-101"),
                        Tuple.tuple(102, "value-102"),
                        Tuple.tuple(103, "value-103")
                );
    }

    @Test
    public void queryForListUsingRawValues() throws Exception {
        final List<ConfigurationRow> list = toTest().queryForList(
                "SELECT * FROM configuration WHERE property_id BETWEEN 101 AND 103 ORDER BY property_id",
                resultSet -> new ConfigurationRow(
                        DataType.INTEGER.getNullableFromResultSet(resultSet, "property_id"),
                        DataType.STRING.getNullableFromResultSet(resultSet, "property_value")
                ));

        assertThat(list)
                .extracting(ConfigurationRow::getId, ConfigurationRow::getValue)
                .containsExactly(
                        Tuple.tuple(101, "value-101"),
                        Tuple.tuple(102, "value-102"),
                        Tuple.tuple(103, "value-103")
                );
    }
}
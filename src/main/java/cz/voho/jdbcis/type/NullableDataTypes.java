package cz.voho.jdbcis.type;

import cz.voho.jdbcis.type.nullable.*;

public final class NullableDataTypes {
    public static final BigDecimalDataType BIG_DECIMAL = new BigDecimalDataType();
    public static final DateDataType DATE = new DateDataType();
    public static final DateTimeDataType DATE_TIME = new DateTimeDataType();
    public static final DoubleDataType DOUBLE = new DoubleDataType();
    public static final IntegerDataType INTEGER = new IntegerDataType();
    public static final LongDataType LONG = new LongDataType();
    public static final StringDataType STRING = new StringDataType();
    public static final TimeDataType TIME = new TimeDataType();

    private NullableDataTypes() {
        // utility class
    }
}

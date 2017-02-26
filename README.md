# JDBCis

[![Travis](https://travis-ci.org/voho/jdbcis.svg?branch=master)](https://travis-ci.org/voho/jdbcis) 
[![codecov.io](https://codecov.io/github/voho/jdbcis/coverage.svg?branch=master)](https://codecov.io/github/voho/jdbcis?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/a3439d1582cc405788b5e1d4d8fda8e7)](https://www.codacy.com/app/vojtech-hordejcuk/jdbcis?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=voho/jdbcis&amp;utm_campaign=Badge_Grade)

Thin layer on top of JDBC providing higher-level abstractions and convenience operations.

## Features

- execute transactions easily
- extract your SQL queries from the code and move it to resources
- read entities from database using custom row mappers
- set query parameters safely using prepared statement setters
- avoid NULL-related problems while reading/writing to JDBC objects
- obtain auto-generated keys easily

## Artifacts

### Maven

You can include this library in your Maven project simply using the Jitpack service.

This has two steps. Step one, include this repository:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Step two, add this dependency (you can find the latest version in `pom.xml` file):

```xml
<dependency>
    <groupId>com.github.voho</groupId>
    <artifactId>jdbcis</artifactId>
    <version>{SPECIFY_VERSION_HERE}</version>
</dependency>
```

The latest version available is shown here:

[![Release](https://jitpack.io/v/voho/jdbcis.svg)](https://jitpack.io/#voho/jdbcis)

## How-to

### Create the JDBCis instance
 
First, let us prepare an extension point that will integrate your connection manager (e.g. a pool) with PureJDBC.
All you have to do is to provide an implementation of the `ConnectionManager` interface.
This interface has two methods:

* `acquireConnection` - specify a way how PureJDBC can obtain a new connection, e.g. acquire from a connection pool
* `releaseConnection` - specify what to do with a connection that is no longer needed by PureJDBC, e.g. return back to a connection pool or simply do nothing

Example: integration with [HikariCP](https://github.com/brettwooldridge/HikariCP):

```java
HikariDataSource dataSource = new HikariDataSource();
dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/simpsons");
dataSource.setUsername("bart");
dataSource.setPassword("51mp50n");

ConnectionManager connectionManager = new DataSourceConnectionManager(dataSource);
JdbcOperations pureJdbc = new JdbcOperations(cm);
```

### Read operations

#### Load a list of rows from a table

```java
PreparedStatementSetter setter = statement -> statement.setLongOrNull(18);
String sql = "SELECT * FROM person WHERE age > ?";
List<Person> people = pureJdbc.queryForList(sql, setter, mapper);
```

#### Load a single row from a table

```java
PreparedStatementSetter setter = statement -> statement.setLongOrNull(42);
String sql = "SELECT * FROM person WHERE id = ?";
Optional<Person> person = pureJdbc.queryForSingle(sql, setter, mapper);
```

### Write operations

#### Insert row to a table and get generated key (if any)

```java
PreparedStatementSetter setter = statement -> {
    statement.setStringOrNull(1, "John Doe");
    statement.setLongOrNull(2, 32);
};
String sql = "INSERT INTO person (name, age) VALUES (?, ?)";
Long newPrimaryKey = pureJdbc.insertAndGetSingleLongKey(sql, setter);
```

#### Update rows in a table

```java
PreparedStatementSetter setter = statement -> {
    statement.setStringOrNull(1, "John Doe");
    statement.setLongOrNull(2, 32);
    statement.setLongOrNull(3, 204);
};
String sql = "UPDATE person SET name = ?, age = ? WHERE id = ?";
pureJdbc.update(sql, setter);
```

#### Delete rows in a table

```java
PreparedStatementSetter setter = statement -> {
    statement.setLongOrNull(1, 10024);
};
String sql = "DELETE FROM person WHERE id = ?";
pureJdbc.update(sql, setter);
```

#### Inject parameters into Prepared Statement

A `PreparedStatementSetter` instance can be used to set up various parameters on a prepared statement.
Note that the prepared statement is in fact a decorated object, providing more features than the original.
For getting the original, you can use the `getInnerPreparedStatement()` method.

```java
PreparedStatementSetter setter = (decoratedPreparedStatement) -> {
  decoratedPreparedStatement.setStringOrNull(1, "value");
  decoratedPreparedStatement.setLongOrNull(2, 42L);
};
```

#### Create custom row mapper

A `RowMapper` instance can be used to extract result set into a domain model object instance.
Note that the result set is in fact a decorated object, providing more features than the original.
For getting the original, you can use the `getInnerResultSet()` method.

```java
RowMapper<Person> rowMapper = (decoratedResultSet) -> {
  Person person = new Person();
  person.setName(decoratedResultSet.getNonNullString("name"));
  person.setAge(decoratedResultSet.getNonNullInteger("age"));
  return person;
};
```

## Outro

Thank you very much for using the library.
If you have any feedback or feature wishes, please use the `Issues` section.
I will be also very happy to learn about the library usages.
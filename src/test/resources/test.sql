DROP TABLE t IF EXISTS;

CREATE TABLE t (
  c_pk BIGINT PRIMARY KEY AUTO_INCREMENT,
  c_decimal DECIMAL,
  c_date DATE,
  c_timestamp TIMESTAMP,
  c_double DOUBLE,
  c_integer INTEGER,
  c_bigint BIGINT,
  c_varchar VARCHAR(100),
  c_text TEXT,
  c_time TIME
);

INSERT INTO t (c_text) VALUES ('Predefined-1');
INSERT INTO t (c_text) VALUES ('Predefined-2');
INSERT INTO t (c_text) VALUES ('Predefined-3');
INSERT INTO t (c_text) VALUES ('Predefined-4');
INSERT INTO t (c_text) VALUES ('Predefined-5');
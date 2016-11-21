DROP TABLE configuration IF EXISTS;

CREATE TABLE configuration (
  property_id    INTEGER,
  property_value TEXT
);

INSERT INTO configuration (property_id, property_value) VALUES (101, 'value-101');
INSERT INTO configuration (property_id, property_value) VALUES (102, 'value-102');
INSERT INTO configuration (property_id, property_value) VALUES (103, 'value-103');

INSERT INTO configuration (property_id, property_value) VALUES (1000, 'original-value');
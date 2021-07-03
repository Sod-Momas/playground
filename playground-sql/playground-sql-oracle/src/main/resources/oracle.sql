CREATE TABLE plants
    ( plant_id    NUMBER PRIMARY KEY,
      common_name VARCHAR2(64) );

INSERT INTO plants VALUES (1, 'African Violet');
INSERT INTO plants VALUES (2, 'Amaryllis');

ALTER TABLE plants ADD ( latin_name VARCHAR2(40) );

GRANT READ ON plants TO scott;

REVOKE READ ON plants FROM scott;

DROP TABLE plants;

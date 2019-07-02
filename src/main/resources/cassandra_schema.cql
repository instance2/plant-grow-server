CREATE KEYSPACE plant_params WITH REPLICATION = {'class':'SimpleStrategy', 'replication_factor':1};
USE plant_params;

CREATE TABLE records (id UUID PRIMARY KEY, timestamp BIGINT, param VARCHAR, value DECIMAL, unit VARCHAR);
CREATE INDEX ON records (timestamp);
CREATE INDEX ON records (param);

CREATE TABLE commands (id UUID PRIMARY KEY, timestamp BIGINT, command VARCHAR);
CREATE INDEX ON commands (timestamp);
CREATE INDEX ON commands (command);

CREATE TYPE params(
    param VARCHAR,
    min DECIMAL,
    max DECIMAL,
    unit VARCHAR,
    active BOOLEAN
    );

CREATE TABLE settings (id VARCHAR PRIMARY KEY, timestamp BIGINT, params set<frozen<params>>, active boolean);



CREATE SCHEMA IF NOT EXISTS ${schema_name};

SET SCHEMA ${schema_name};

create table if not exists ${schema_name}.job
(
    id    SERIAL PRIMARY KEY,
    name text,
    description text,
    created_on timestamp default now(),
    created_by text not null
);
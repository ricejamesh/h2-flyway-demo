-- Simple example of modifying initial table to add a new column.

-- Add a new column to the job object.
alter table ${schema_name}.job add column success boolean;

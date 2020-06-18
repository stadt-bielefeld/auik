#!/bin/sh -e
echo "###Setting up database###"

db_name=fisumwelt
schema_file=fisumwelt_schema.sql
user=auikadmin
password=secret
psql --command "CREATE DATABASE $db_name;" >> /dev/null
psql -d $db_name --command "CREATE EXTENSION postgis;" >> /dev/null
#Create user and assign roles
psql -d $db_name --command "CREATE USER auikadmin with password 'secret';" >> /dev/null
psql -d $db_name --command "ALTER USER auikadmin with superuser;" >> /dev/null

echo "Database: $db_name"
echo "Example user(password): $user($password)"

echo "Applying schema"
psql -d $db_name -f $schema_file >> /dev/null

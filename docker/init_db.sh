#!/bin/sh -e
echo "###Setting up database###"

schema_dir=/opt/auik_db
schema_file=$schema_dir/version1_0_schema.sql
update_file_1_1_0=$schema_dir/updateTo_1_1_0.sql
import_script=$schema_dir/import_csv.sql

psql --command "CREATE DATABASE $DB_NAME;" >> /dev/null
psql -d $DB_NAME --command "CREATE EXTENSION postgis;" >> /dev/null
#Create user and assign roles
psql -d $DB_NAME --command "CREATE USER ${USERNAME} with password '${USER_PW}';" >> /dev/null

echo "Database: $DB_NAME"
echo "Example user(password): ${USERNAME}(${USER_PW})"

echo "Applying schema"
psql -d $DB_NAME -f $schema_file >> /dev/null
psql -d $DB_NAME -f $update_file_1_1_0 >> /dev/null
psql -d $DB_NAME -f $import_script


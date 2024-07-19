#!/bin/sh -e
echo "###Setting up database###"

schema_dir=/opt/auik_db
schema_file=$schema_dir/version1_0_schema.sql
update_file_1_1_0=$schema_dir/updateTo_1_1_0.sql
import_script=$schema_dir/import_csv.sql

psql -q --command "CREATE DATABASE $DB_NAME;"
psql -q -d $DB_NAME --command "CREATE EXTENSION postgis;"
#Create user and assign roles
psql -q -d $DB_NAME --command "CREATE USER ${USERNAME} with password '${USER_PW}';"

echo "Applying schema"
psql -q -d $DB_NAME -f $schema_file
psql -q -d $DB_NAME -f $update_file_1_1_0
psql -q -d $DB_NAME -f $import_script


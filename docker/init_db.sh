#!/bin/sh -e
echo "###Setting up database###"

DB_NAME=${DB_NAME:-fisumwelt}
USERNAME=${USERNAME:-auikadmin}
USER_PW=${USER_PW:-$POSTGRES_PASSWORD}

schema_dir=/opt/auik_db
schema_file=$schema_dir/version1_0_schema.sql
update_file_1_1_0=$schema_dir/updateTo_1_1_0.sql
import_script=$schema_dir/import_csv.sql

call_psql() {
    psql -q -v ON_ERROR_STOP=on $@
}

createdb $DB_NAME
psql -c "CREATE USER ${USERNAME} with password '${USER_PW}';"

echo "Applying schema"
export PGDATABASE=$DB_NAME
call_psql -f $schema_file
call_psql -f $update_file_1_1_0

# Import example data
call_psql -f $import_script <$schema_dir/import.csv

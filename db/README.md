# AUIK DB Docker image

## Building

```shell
docker build -t auik_db .
```

## Start container

```shell
docker run -d -p 5432:5432 auik_db
```

while the command is

```shell
docker run -d -p [exposed port]:[internal port] auik_db
```

## Connect to database

```shell
psql -U auikadmin -d fisumwelt -p 5432 -h localhost
```

## Load all the tables of a schema from Postgres to Spatialite (with or without geometry) and without the views:

```shell
ogr2ogr --config PG_LIST_ALL_TABLES YES -f "SQLite" fisumwelt.sqlite -progress PG:"dbname='fisumwelt' \
active_schema=public schemas=awsv,basis,elka,indeinl,labor,oberflgw host='localhost' port='5432' user='auikadmin' password='secret' " -lco LAUNDER=yes \
-dsco SPATIALITE=yes -lco SPATIAL_INDEX=yes -gt 65536
```

Documentation: https://gdal.org/drivers/vector/pg.html

TODO: FIX Multi-column primary keys in oberflgw.afs_stoffe and basis.prioritaet
```
0...10...20..Warning 1: Multi-column primary key in 'prioritaet' detected but not supported.
.30...40...50...60...70...80...90...Warning 1: Multi-column primary key in 'afs_stoffe' detected but not supported.
100 - done.
```


## Connect to sqlite database

```shell
sqlite3 fisumwelt.sqlite
```

Example sql statement:
```shell
select * from "elka.e_betrieb";
```

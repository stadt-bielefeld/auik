--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.5
-- Dumped by pg_dump version 9.4.5
-- Started on 2020-04-01 15:14:48

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 6 (class 2615 OID 5899599)
-- Name: awsv; Type: SCHEMA; Schema: -; Owner: auikadmin
--

CREATE SCHEMA awsv;


ALTER SCHEMA awsv OWNER TO auikadmin;

--
-- TOC entry 7 (class 2615 OID 5899600)
-- Name: basis; Type: SCHEMA; Schema: -; Owner: auikadmin
--

CREATE SCHEMA basis;


ALTER SCHEMA basis OWNER TO auikadmin;

--
-- TOC entry 8 (class 2615 OID 5899601)
-- Name: elka; Type: SCHEMA; Schema: -; Owner: auikadmin
--

CREATE SCHEMA elka;


ALTER SCHEMA elka OWNER TO auikadmin;

--
-- TOC entry 9 (class 2615 OID 5899603)
-- Name: indeinl; Type: SCHEMA; Schema: -; Owner: auikadmin
--

CREATE SCHEMA indeinl;


ALTER SCHEMA indeinl OWNER TO auikadmin;

--
-- TOC entry 10 (class 2615 OID 5899604)
-- Name: labor; Type: SCHEMA; Schema: -; Owner: auikadmin
--

CREATE SCHEMA labor;


ALTER SCHEMA labor OWNER TO auikadmin;

--
-- TOC entry 11 (class 2615 OID 5899605)
-- Name: oberflgw; Type: SCHEMA; Schema: -; Owner: auikadmin
--

CREATE SCHEMA oberflgw;


ALTER SCHEMA oberflgw OWNER TO auikadmin;

--
-- TOC entry 356 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 4814 (class 0 OID 0)
-- Dependencies: 356
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 357 (class 3079 OID 5899606)
-- Name: postgis; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS postgis WITH SCHEMA public;


--
-- TOC entry 4815 (class 0 OID 0)
-- Dependencies: 357
-- Name: EXTENSION postgis; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION postgis IS 'PostGIS geometry, geography, and raster spatial types and functions';


SET search_path = public, pg_catalog;

--
-- TOC entry 2195 (class 1247 OID 5900971)
-- Name: box3d_extent; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE box3d_extent;


ALTER TYPE box3d_extent OWNER TO postgres;

--
-- TOC entry 4816 (class 0 OID 0)
-- Dependencies: 2195
-- Name: TYPE box3d_extent; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TYPE box3d_extent IS 'postgis type: A box composed of x min, ymin, zmin, xmax, ymax, zmax. Often used to return the extent of a geometry.';


--
-- TOC entry 2196 (class 1247 OID 5900972)
-- Name: chip; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE chip;


ALTER TYPE chip OWNER TO postgres;

--
-- TOC entry 2197 (class 1247 OID 5900975)
-- Name: dblink_pkey_results; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE dblink_pkey_results AS (
	"position" integer,
	colname text
);


ALTER TYPE dblink_pkey_results OWNER TO postgres;

--
-- TOC entry 2201 (class 0 OID 0)
-- Name: gbtreekey16; Type: SHELL TYPE; Schema: public; Owner: postgres
--

CREATE TYPE gbtreekey16;


--
-- TOC entry 1481 (class 1255 OID 5900977)
-- Name: gbtreekey16_in(cstring); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbtreekey16_in(cstring) RETURNS gbtreekey16
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbtreekey_in';


ALTER FUNCTION public.gbtreekey16_in(cstring) OWNER TO postgres;

--
-- TOC entry 1482 (class 1255 OID 5900978)
-- Name: gbtreekey16_out(gbtreekey16); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbtreekey16_out(gbtreekey16) RETURNS cstring
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbtreekey_out';


ALTER FUNCTION public.gbtreekey16_out(gbtreekey16) OWNER TO postgres;

--
-- TOC entry 2200 (class 1247 OID 5900976)
-- Name: gbtreekey16; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE gbtreekey16 (
    INTERNALLENGTH = 16,
    INPUT = gbtreekey16_in,
    OUTPUT = gbtreekey16_out,
    ALIGNMENT = int4,
    STORAGE = plain
);


ALTER TYPE gbtreekey16 OWNER TO postgres;

--
-- TOC entry 2205 (class 0 OID 0)
-- Name: gbtreekey32; Type: SHELL TYPE; Schema: public; Owner: postgres
--

CREATE TYPE gbtreekey32;


--
-- TOC entry 1483 (class 1255 OID 5900981)
-- Name: gbtreekey32_in(cstring); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbtreekey32_in(cstring) RETURNS gbtreekey32
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbtreekey_in';


ALTER FUNCTION public.gbtreekey32_in(cstring) OWNER TO postgres;

--
-- TOC entry 1484 (class 1255 OID 5900982)
-- Name: gbtreekey32_out(gbtreekey32); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbtreekey32_out(gbtreekey32) RETURNS cstring
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbtreekey_out';


ALTER FUNCTION public.gbtreekey32_out(gbtreekey32) OWNER TO postgres;

--
-- TOC entry 2204 (class 1247 OID 5900980)
-- Name: gbtreekey32; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE gbtreekey32 (
    INTERNALLENGTH = 32,
    INPUT = gbtreekey32_in,
    OUTPUT = gbtreekey32_out,
    ALIGNMENT = int4,
    STORAGE = plain
);


ALTER TYPE gbtreekey32 OWNER TO postgres;

--
-- TOC entry 2209 (class 0 OID 0)
-- Name: gbtreekey4; Type: SHELL TYPE; Schema: public; Owner: postgres
--

CREATE TYPE gbtreekey4;


--
-- TOC entry 1485 (class 1255 OID 5900985)
-- Name: gbtreekey4_in(cstring); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbtreekey4_in(cstring) RETURNS gbtreekey4
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbtreekey_in';


ALTER FUNCTION public.gbtreekey4_in(cstring) OWNER TO postgres;

--
-- TOC entry 1486 (class 1255 OID 5900986)
-- Name: gbtreekey4_out(gbtreekey4); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbtreekey4_out(gbtreekey4) RETURNS cstring
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbtreekey_out';


ALTER FUNCTION public.gbtreekey4_out(gbtreekey4) OWNER TO postgres;

--
-- TOC entry 2208 (class 1247 OID 5900984)
-- Name: gbtreekey4; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE gbtreekey4 (
    INTERNALLENGTH = 4,
    INPUT = gbtreekey4_in,
    OUTPUT = gbtreekey4_out,
    ALIGNMENT = int4,
    STORAGE = plain
);


ALTER TYPE gbtreekey4 OWNER TO postgres;

--
-- TOC entry 2213 (class 0 OID 0)
-- Name: gbtreekey8; Type: SHELL TYPE; Schema: public; Owner: postgres
--

CREATE TYPE gbtreekey8;


--
-- TOC entry 1487 (class 1255 OID 5900989)
-- Name: gbtreekey8_in(cstring); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbtreekey8_in(cstring) RETURNS gbtreekey8
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbtreekey_in';


ALTER FUNCTION public.gbtreekey8_in(cstring) OWNER TO postgres;

--
-- TOC entry 1488 (class 1255 OID 5900990)
-- Name: gbtreekey8_out(gbtreekey8); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbtreekey8_out(gbtreekey8) RETURNS cstring
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbtreekey_out';


ALTER FUNCTION public.gbtreekey8_out(gbtreekey8) OWNER TO postgres;

--
-- TOC entry 2212 (class 1247 OID 5900988)
-- Name: gbtreekey8; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE gbtreekey8 (
    INTERNALLENGTH = 8,
    INPUT = gbtreekey8_in,
    OUTPUT = gbtreekey8_out,
    ALIGNMENT = int4,
    STORAGE = plain
);


ALTER TYPE gbtreekey8 OWNER TO postgres;

--
-- TOC entry 2217 (class 0 OID 0)
-- Name: gbtreekey_var; Type: SHELL TYPE; Schema: public; Owner: postgres
--

CREATE TYPE gbtreekey_var;


--
-- TOC entry 1489 (class 1255 OID 5900993)
-- Name: gbtreekey_var_in(cstring); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbtreekey_var_in(cstring) RETURNS gbtreekey_var
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbtreekey_in';


ALTER FUNCTION public.gbtreekey_var_in(cstring) OWNER TO postgres;

--
-- TOC entry 1490 (class 1255 OID 5900994)
-- Name: gbtreekey_var_out(gbtreekey_var); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbtreekey_var_out(gbtreekey_var) RETURNS cstring
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbtreekey_out';


ALTER FUNCTION public.gbtreekey_var_out(gbtreekey_var) OWNER TO postgres;

--
-- TOC entry 2216 (class 1247 OID 5900992)
-- Name: gbtreekey_var; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE gbtreekey_var (
    INTERNALLENGTH = variable,
    INPUT = gbtreekey_var_in,
    OUTPUT = gbtreekey_var_out,
    ALIGNMENT = int4,
    STORAGE = extended
);


ALTER TYPE gbtreekey_var OWNER TO postgres;

SET search_path = basis, pg_catalog;

--
-- TOC entry 1491 (class 1255 OID 5900996)
-- Name: cascade_deleted(); Type: FUNCTION; Schema: basis; Owner: auikadmin
--

CREATE FUNCTION cascade_deleted() RETURNS trigger
    LANGUAGE plpgsql
    AS $$BEGIN
	IF OLD._deleted = FALSE AND NEW._deleted = TRUE THEN
		RAISE EXCEPTION 'Boohuu';
	END IF;

	RETURN NEW;
END;$$;


ALTER FUNCTION basis.cascade_deleted() OWNER TO auikadmin;

SET search_path = public, pg_catalog;

--
-- TOC entry 1492 (class 1255 OID 5900997)
-- Name: affine(geometry, double precision, double precision, double precision, double precision, double precision, double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION affine(geometry, double precision, double precision, double precision, double precision, double precision, double precision) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT st_affine($1,  $2, $3, 0,  $4, $5, 0,  0, 0, 1,  $6, $7, 0)$_$;


ALTER FUNCTION public.affine(geometry, double precision, double precision, double precision, double precision, double precision, double precision) OWNER TO postgres;

--
-- TOC entry 1493 (class 1255 OID 5900998)
-- Name: asgml(geometry); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION asgml(geometry) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsGML(2, $1, 15, 0, null, null)$_$;


ALTER FUNCTION public.asgml(geometry) OWNER TO postgres;

--
-- TOC entry 1494 (class 1255 OID 5900999)
-- Name: asgml(geometry, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION asgml(geometry, integer) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsGML(2, $1, $2, 0, null, null)$_$;


ALTER FUNCTION public.asgml(geometry, integer) OWNER TO postgres;

--
-- TOC entry 1495 (class 1255 OID 5901000)
-- Name: askml(geometry); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION askml(geometry) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsKML(2, ST_Transform($1,4326), 15, null)$_$;


ALTER FUNCTION public.askml(geometry) OWNER TO postgres;

--
-- TOC entry 1496 (class 1255 OID 5901001)
-- Name: askml(geometry, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION askml(geometry, integer) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsKML(2, ST_transform($1,4326), $2, null)$_$;


ALTER FUNCTION public.askml(geometry, integer) OWNER TO postgres;

--
-- TOC entry 1497 (class 1255 OID 5901002)
-- Name: askml(integer, geometry, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION askml(integer, geometry, integer) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsKML($1, ST_Transform($2,4326), $3, null)$_$;


ALTER FUNCTION public.askml(integer, geometry, integer) OWNER TO postgres;

--
-- TOC entry 1498 (class 1255 OID 5901003)
-- Name: bdmpolyfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION bdmpolyfromtext(text, integer) RETURNS geometry
    LANGUAGE plpgsql IMMUTABLE STRICT
    AS $_$
DECLARE
	geomtext alias for $1;
	srid alias for $2;
	mline geometry;
	geom geometry;
BEGIN
	mline := ST_MultiLineStringFromText(geomtext, srid);

	IF mline IS NULL
	THEN
		RAISE EXCEPTION 'Input is not a MultiLinestring';
	END IF;

	geom := ST_Multi(ST_BuildArea(mline));

	RETURN geom;
END;
$_$;


ALTER FUNCTION public.bdmpolyfromtext(text, integer) OWNER TO postgres;

--
-- TOC entry 1499 (class 1255 OID 5901004)
-- Name: bdpolyfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION bdpolyfromtext(text, integer) RETURNS geometry
    LANGUAGE plpgsql IMMUTABLE STRICT
    AS $_$
DECLARE
	geomtext alias for $1;
	srid alias for $2;
	mline geometry;
	geom geometry;
BEGIN
	mline := ST_MultiLineStringFromText(geomtext, srid);

	IF mline IS NULL
	THEN
		RAISE EXCEPTION 'Input is not a MultiLinestring';
	END IF;

	geom := ST_BuildArea(mline);

	IF GeometryType(geom) != 'POLYGON'
	THEN
		RAISE EXCEPTION 'Input returns more then a single polygon, try using BdMPolyFromText instead';
	END IF;

	RETURN geom;
END;
$_$;


ALTER FUNCTION public.bdpolyfromtext(text, integer) OWNER TO postgres;

--
-- TOC entry 1500 (class 1255 OID 5901005)
-- Name: buffer(geometry, double precision, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION buffer(geometry, double precision, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT ST_Buffer($1, $2, $3)$_$;


ALTER FUNCTION public.buffer(geometry, double precision, integer) OWNER TO postgres;

--
-- TOC entry 1501 (class 1255 OID 5901006)
-- Name: dblink(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink(text) RETURNS SETOF record
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_record';


ALTER FUNCTION public.dblink(text) OWNER TO postgres;

--
-- TOC entry 1502 (class 1255 OID 5901007)
-- Name: dblink(text, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink(text, boolean) RETURNS SETOF record
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_record';


ALTER FUNCTION public.dblink(text, boolean) OWNER TO postgres;

--
-- TOC entry 1503 (class 1255 OID 5901008)
-- Name: dblink(text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink(text, text) RETURNS SETOF record
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_record';


ALTER FUNCTION public.dblink(text, text) OWNER TO postgres;

--
-- TOC entry 1504 (class 1255 OID 5901009)
-- Name: dblink(text, text, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink(text, text, boolean) RETURNS SETOF record
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_record';


ALTER FUNCTION public.dblink(text, text, boolean) OWNER TO postgres;

--
-- TOC entry 1505 (class 1255 OID 5901010)
-- Name: dblink_build_sql_delete(text, int2vector, integer, text[]); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_build_sql_delete(text, int2vector, integer, text[]) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_build_sql_delete';


ALTER FUNCTION public.dblink_build_sql_delete(text, int2vector, integer, text[]) OWNER TO postgres;

--
-- TOC entry 1506 (class 1255 OID 5901011)
-- Name: dblink_build_sql_insert(text, int2vector, integer, text[], text[]); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_build_sql_insert(text, int2vector, integer, text[], text[]) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_build_sql_insert';


ALTER FUNCTION public.dblink_build_sql_insert(text, int2vector, integer, text[], text[]) OWNER TO postgres;

--
-- TOC entry 1507 (class 1255 OID 5901012)
-- Name: dblink_build_sql_update(text, int2vector, integer, text[], text[]); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_build_sql_update(text, int2vector, integer, text[], text[]) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_build_sql_update';


ALTER FUNCTION public.dblink_build_sql_update(text, int2vector, integer, text[], text[]) OWNER TO postgres;

--
-- TOC entry 1508 (class 1255 OID 5901013)
-- Name: dblink_cancel_query(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_cancel_query(text) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_cancel_query';


ALTER FUNCTION public.dblink_cancel_query(text) OWNER TO postgres;

--
-- TOC entry 1509 (class 1255 OID 5901014)
-- Name: dblink_close(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_close(text) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_close';


ALTER FUNCTION public.dblink_close(text) OWNER TO postgres;

--
-- TOC entry 1510 (class 1255 OID 5901015)
-- Name: dblink_close(text, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_close(text, boolean) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_close';


ALTER FUNCTION public.dblink_close(text, boolean) OWNER TO postgres;

--
-- TOC entry 1511 (class 1255 OID 5901016)
-- Name: dblink_close(text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_close(text, text) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_close';


ALTER FUNCTION public.dblink_close(text, text) OWNER TO postgres;

--
-- TOC entry 1512 (class 1255 OID 5901017)
-- Name: dblink_close(text, text, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_close(text, text, boolean) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_close';


ALTER FUNCTION public.dblink_close(text, text, boolean) OWNER TO postgres;

--
-- TOC entry 1513 (class 1255 OID 5901018)
-- Name: dblink_connect(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_connect(text) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_connect';


ALTER FUNCTION public.dblink_connect(text) OWNER TO postgres;

--
-- TOC entry 1514 (class 1255 OID 5901019)
-- Name: dblink_connect(text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_connect(text, text) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_connect';


ALTER FUNCTION public.dblink_connect(text, text) OWNER TO postgres;

--
-- TOC entry 1515 (class 1255 OID 5901020)
-- Name: dblink_connect_u(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_connect_u(text) RETURNS text
    LANGUAGE c STRICT SECURITY DEFINER
    AS '$libdir/dblink', 'dblink_connect';


ALTER FUNCTION public.dblink_connect_u(text) OWNER TO postgres;

--
-- TOC entry 1516 (class 1255 OID 5901021)
-- Name: dblink_connect_u(text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_connect_u(text, text) RETURNS text
    LANGUAGE c STRICT SECURITY DEFINER
    AS '$libdir/dblink', 'dblink_connect';


ALTER FUNCTION public.dblink_connect_u(text, text) OWNER TO postgres;

--
-- TOC entry 1517 (class 1255 OID 5901022)
-- Name: dblink_current_query(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_current_query() RETURNS text
    LANGUAGE c
    AS '$libdir/dblink', 'dblink_current_query';


ALTER FUNCTION public.dblink_current_query() OWNER TO postgres;

--
-- TOC entry 1518 (class 1255 OID 5901023)
-- Name: dblink_disconnect(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_disconnect() RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_disconnect';


ALTER FUNCTION public.dblink_disconnect() OWNER TO postgres;

--
-- TOC entry 1519 (class 1255 OID 5901024)
-- Name: dblink_disconnect(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_disconnect(text) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_disconnect';


ALTER FUNCTION public.dblink_disconnect(text) OWNER TO postgres;

--
-- TOC entry 1520 (class 1255 OID 5901025)
-- Name: dblink_error_message(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_error_message(text) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_error_message';


ALTER FUNCTION public.dblink_error_message(text) OWNER TO postgres;

--
-- TOC entry 1521 (class 1255 OID 5901026)
-- Name: dblink_exec(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_exec(text) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_exec';


ALTER FUNCTION public.dblink_exec(text) OWNER TO postgres;

--
-- TOC entry 1522 (class 1255 OID 5901027)
-- Name: dblink_exec(text, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_exec(text, boolean) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_exec';


ALTER FUNCTION public.dblink_exec(text, boolean) OWNER TO postgres;

--
-- TOC entry 1523 (class 1255 OID 5901028)
-- Name: dblink_exec(text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_exec(text, text) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_exec';


ALTER FUNCTION public.dblink_exec(text, text) OWNER TO postgres;

--
-- TOC entry 1524 (class 1255 OID 5901029)
-- Name: dblink_exec(text, text, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_exec(text, text, boolean) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_exec';


ALTER FUNCTION public.dblink_exec(text, text, boolean) OWNER TO postgres;

--
-- TOC entry 1525 (class 1255 OID 5901030)
-- Name: dblink_fetch(text, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_fetch(text, integer) RETURNS SETOF record
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_fetch';


ALTER FUNCTION public.dblink_fetch(text, integer) OWNER TO postgres;

--
-- TOC entry 1526 (class 1255 OID 5901031)
-- Name: dblink_fetch(text, integer, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_fetch(text, integer, boolean) RETURNS SETOF record
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_fetch';


ALTER FUNCTION public.dblink_fetch(text, integer, boolean) OWNER TO postgres;

--
-- TOC entry 1527 (class 1255 OID 5901032)
-- Name: dblink_fetch(text, text, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_fetch(text, text, integer) RETURNS SETOF record
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_fetch';


ALTER FUNCTION public.dblink_fetch(text, text, integer) OWNER TO postgres;

--
-- TOC entry 1528 (class 1255 OID 5901033)
-- Name: dblink_fetch(text, text, integer, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_fetch(text, text, integer, boolean) RETURNS SETOF record
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_fetch';


ALTER FUNCTION public.dblink_fetch(text, text, integer, boolean) OWNER TO postgres;

--
-- TOC entry 1529 (class 1255 OID 5901034)
-- Name: dblink_get_connections(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_get_connections() RETURNS text[]
    LANGUAGE c
    AS '$libdir/dblink', 'dblink_get_connections';


ALTER FUNCTION public.dblink_get_connections() OWNER TO postgres;

--
-- TOC entry 1530 (class 1255 OID 5901035)
-- Name: dblink_get_pkey(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_get_pkey(text) RETURNS SETOF dblink_pkey_results
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_get_pkey';


ALTER FUNCTION public.dblink_get_pkey(text) OWNER TO postgres;

--
-- TOC entry 1531 (class 1255 OID 5901036)
-- Name: dblink_get_result(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_get_result(text) RETURNS SETOF record
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_get_result';


ALTER FUNCTION public.dblink_get_result(text) OWNER TO postgres;

--
-- TOC entry 1532 (class 1255 OID 5901037)
-- Name: dblink_get_result(text, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_get_result(text, boolean) RETURNS SETOF record
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_get_result';


ALTER FUNCTION public.dblink_get_result(text, boolean) OWNER TO postgres;

--
-- TOC entry 1533 (class 1255 OID 5901038)
-- Name: dblink_is_busy(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_is_busy(text) RETURNS integer
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_is_busy';


ALTER FUNCTION public.dblink_is_busy(text) OWNER TO postgres;

--
-- TOC entry 1534 (class 1255 OID 5901039)
-- Name: dblink_open(text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_open(text, text) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_open';


ALTER FUNCTION public.dblink_open(text, text) OWNER TO postgres;

--
-- TOC entry 1535 (class 1255 OID 5901040)
-- Name: dblink_open(text, text, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_open(text, text, boolean) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_open';


ALTER FUNCTION public.dblink_open(text, text, boolean) OWNER TO postgres;

--
-- TOC entry 1536 (class 1255 OID 5901041)
-- Name: dblink_open(text, text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_open(text, text, text) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_open';


ALTER FUNCTION public.dblink_open(text, text, text) OWNER TO postgres;

--
-- TOC entry 1537 (class 1255 OID 5901042)
-- Name: dblink_open(text, text, text, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_open(text, text, text, boolean) RETURNS text
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_open';


ALTER FUNCTION public.dblink_open(text, text, text, boolean) OWNER TO postgres;

--
-- TOC entry 1538 (class 1255 OID 5901043)
-- Name: dblink_send_query(text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION dblink_send_query(text, text) RETURNS integer
    LANGUAGE c STRICT
    AS '$libdir/dblink', 'dblink_send_query';


ALTER FUNCTION public.dblink_send_query(text, text) OWNER TO postgres;

--
-- TOC entry 1539 (class 1255 OID 5901044)
-- Name: f_collect_custom_cat_layer(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION f_collect_custom_cat_layer(integer) RETURNS text
    LANGUAGE plpgsql STRICT
    AS $_$DECLARE
  i_layer_id ALIAS FOR $1;
  custom_cat_string  TEXT;
  custom_cat_record  RECORD;

BEGIN
custom_cat_string := '';

FOR custom_cat_record IN SELECT layer_custom_category.fkey_custom_category_id from layer_custom_category WHERE layer_custom_category.fkey_layer_id=$1  LOOP
custom_cat_string := custom_cat_string || '{' ||custom_cat_record.fkey_custom_category_id || '}';
END LOOP ;
  
RETURN custom_cat_string;

END;
$_$;


ALTER FUNCTION public.f_collect_custom_cat_layer(integer) OWNER TO postgres;

--
-- TOC entry 1540 (class 1255 OID 5901045)
-- Name: f_collect_custom_cat_wfs_featuretype(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION f_collect_custom_cat_wfs_featuretype(integer) RETURNS text
    LANGUAGE plpgsql STRICT
    AS $_$DECLARE
  i_featuretype_id ALIAS FOR $1;
  custom_cat_string  TEXT;
  custom_cat_record  RECORD;

BEGIN
custom_cat_string := '';

FOR custom_cat_record IN SELECT wfs_featuretype_custom_category.fkey_custom_category_id from wfs_featuretype_custom_category WHERE wfs_featuretype_custom_category.fkey_featuretype_id=$1  LOOP
custom_cat_string := custom_cat_string || '{' ||custom_cat_record.fkey_custom_category_id || '}';
END LOOP ;
  
RETURN custom_cat_string;

END;
$_$;


ALTER FUNCTION public.f_collect_custom_cat_wfs_featuretype(integer) OWNER TO postgres;

--
-- TOC entry 1541 (class 1255 OID 5901046)
-- Name: f_collect_custom_cat_wmc(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION f_collect_custom_cat_wmc(integer) RETURNS text
    LANGUAGE plpgsql STRICT
    AS $_$DECLARE
  i_wmc_serial_id ALIAS FOR $1;
  custom_cat_string  TEXT;
  custom_cat_record  RECORD;
BEGIN
custom_cat_string := '';
FOR custom_cat_record IN SELECT wmc_custom_category.fkey_custom_category_id from wmc_custom_category WHERE wmc_custom_category.fkey_wmc_serial_id=$1  LOOP
custom_cat_string := custom_cat_string || '{' ||custom_cat_record.fkey_custom_category_id || '}';
END LOOP ;
RETURN custom_cat_string;
END;
$_$;


ALTER FUNCTION public.f_collect_custom_cat_wmc(integer) OWNER TO postgres;

--
-- TOC entry 1542 (class 1255 OID 5901047)
-- Name: f_collect_epsg(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION f_collect_epsg(integer) RETURNS text
    LANGUAGE plpgsql STRICT
    AS $_$DECLARE
  i_layer_id ALIAS FOR $1;
  epsg_string  TEXT;
  epsg_record  RECORD;

BEGIN
epsg_string := '';

FOR epsg_record IN SELECT layer_epsg.epsg from layer_epsg WHERE layer_epsg.fkey_layer_id=$1  LOOP
epsg_string := epsg_string ||  epsg_record.epsg || ';';
END LOOP ;
  
RETURN epsg_string;

    --CASE
      --WHEN LEN(epsg) > 0 THEN LEFT(epsg, LEN(epsg) - 1)
     -- ELSE epsg
    

END;
$_$;


ALTER FUNCTION public.f_collect_epsg(integer) OWNER TO postgres;

--
-- TOC entry 1543 (class 1255 OID 5901048)
-- Name: f_collect_inspire_cat_layer(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION f_collect_inspire_cat_layer(integer) RETURNS text
    LANGUAGE plpgsql STRICT
    AS $_$DECLARE
  i_layer_id ALIAS FOR $1;
  inspire_cat_string  TEXT;
  inspire_cat_record  RECORD;

BEGIN
inspire_cat_string := '';

FOR inspire_cat_record IN SELECT layer_inspire_category.fkey_inspire_category_id from layer_inspire_category WHERE layer_inspire_category.fkey_layer_id=$1  LOOP
inspire_cat_string := inspire_cat_string || '{' ||inspire_cat_record.fkey_inspire_category_id || '}';
END LOOP ;
  
RETURN inspire_cat_string;

END;
$_$;


ALTER FUNCTION public.f_collect_inspire_cat_layer(integer) OWNER TO postgres;

--
-- TOC entry 1544 (class 1255 OID 5901049)
-- Name: f_collect_inspire_cat_wfs_featuretype(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION f_collect_inspire_cat_wfs_featuretype(integer) RETURNS text
    LANGUAGE plpgsql STRICT
    AS $_$DECLARE
  i_featuretype_id ALIAS FOR $1;
  inspire_cat_string  TEXT;
  inspire_cat_record  RECORD;

BEGIN
inspire_cat_string := '';

FOR inspire_cat_record IN SELECT wfs_featuretype_inspire_category.fkey_inspire_category_id from wfs_featuretype_inspire_category WHERE wfs_featuretype_inspire_category.fkey_featuretype_id=$1  LOOP
inspire_cat_string := inspire_cat_string || '{' ||inspire_cat_record.fkey_inspire_category_id || '}';
END LOOP ;
  
RETURN inspire_cat_string;

END;
$_$;


ALTER FUNCTION public.f_collect_inspire_cat_wfs_featuretype(integer) OWNER TO postgres;

--
-- TOC entry 1545 (class 1255 OID 5901050)
-- Name: f_collect_inspire_cat_wmc(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION f_collect_inspire_cat_wmc(integer) RETURNS text
    LANGUAGE plpgsql STRICT
    AS $_$DECLARE
  i_wmc_serial_id ALIAS FOR $1;
  inspire_cat_string  TEXT;
  inspire_cat_record  RECORD;
BEGIN
inspire_cat_string := '';
FOR inspire_cat_record IN SELECT wmc_inspire_category.fkey_inspire_category_id from wmc_inspire_category WHERE wmc_inspire_category.fkey_wmc_serial_id=$1  LOOP
inspire_cat_string := inspire_cat_string || '{' ||inspire_cat_record.fkey_inspire_category_id || '}';
END LOOP ;
RETURN inspire_cat_string;
END;
$_$;


ALTER FUNCTION public.f_collect_inspire_cat_wmc(integer) OWNER TO postgres;

--
-- TOC entry 1546 (class 1255 OID 5901051)
-- Name: f_collect_searchtext(integer, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION f_collect_searchtext(integer, integer) RETURNS text
    LANGUAGE plpgsql STRICT
    AS $_$DECLARE
    p_wms_id ALIAS FOR $1;
    p_layer_id ALIAS FOR $2;
    
    r_keywords RECORD;
    l_result TEXT;
BEGIN
    l_result := '';
    l_result := l_result || (SELECT COALESCE(wms_title, '') || ' ' || COALESCE(wms_abstract, '') FROM wms WHERE wms_id = p_wms_id);
    l_result := l_result || (SELECT COALESCE(layer_name, '')|| ' ' || COALESCE(layer_title, '')  || ' ' || COALESCE(layer_abstract, '') FROM layer WHERE layer_id = p_layer_id);
    FOR r_keywords IN SELECT DISTINCT keyword FROM
        (SELECT keyword FROM layer_keyword L JOIN keyword K ON (K.keyword_id = L.fkey_keyword_id AND L.fkey_layer_id = p_layer_id)
        ) AS __keywords__ LOOP
        l_result := l_result || ' ' || COALESCE(r_keywords.keyword, '');
    END LOOP;
    FOR r_keywords IN SELECT DISTINCT md_topic_category_code_de FROM
        (SELECT md_topic_category_code_de FROM md_topic_category T JOIN layer_md_topic_category C ON (C.fkey_md_topic_category_id = T.md_topic_category_id AND C.fkey_layer_id = p_layer_id)
        ) AS __keywords__ LOOP
        l_result := l_result || ' ' || COALESCE(r_keywords.md_topic_category_code_de, '');
    END LOOP;
   l_result := UPPER(l_result);
   l_result := replace(replace(replace(replace(replace(replace(replace(l_result,'Ã„','AE'),'ÃŸ','SS'),'Ã–','OE'),'Ãœ','UE'),'Ã¤','AE'),'Ã¼','UE'),'Ã¶','OE');

    RETURN l_result;
END;
$_$;


ALTER FUNCTION public.f_collect_searchtext(integer, integer) OWNER TO postgres;

--
-- TOC entry 1547 (class 1255 OID 5901052)
-- Name: f_collect_searchtext_wfs(integer, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION f_collect_searchtext_wfs(integer, integer) RETURNS text
    LANGUAGE plpgsql
    AS $_$
DECLARE
   p_wfs_id ALIAS FOR $1;
   p_featuretype_id ALIAS FOR $2;
     r_keywords RECORD;
   l_result TEXT;
BEGIN
   l_result := '';
   l_result := l_result || (SELECT COALESCE(wfs_title, '') || ' ' || COALESCE(wfs_abstract, '') FROM wfs WHERE wfs_id = p_wfs_id);
   l_result := l_result || (SELECT COALESCE(featuretype_name, '')|| ' ' || COALESCE(featuretype_title, '')  || ' ' || COALESCE(featuretype_abstract, '') FROM wfs_featuretype WHERE featuretype_id = p_featuretype_id);
   FOR r_keywords IN SELECT DISTINCT keyword FROM
       (SELECT keyword FROM wfs_featuretype_keyword L JOIN keyword K ON (K.keyword_id = L.fkey_keyword_id AND L.fkey_featuretype_id = p_featuretype_id)
       ) AS __keywords__ LOOP
       l_result := l_result || ' ' || COALESCE(r_keywords.keyword, '');
   END LOOP;
  l_result := UPPER(l_result);
  l_result := replace(replace(replace(replace(replace(replace(replace(l_result,'Ã„','AE'),'ÃŸ','SS'),'Ã–','OE'),'Ãœ','UE'),'Ã¼','UE'),'Ã¶','OE'),'Ã¤','AE');

   RETURN l_result;
END;
$_$;


ALTER FUNCTION public.f_collect_searchtext_wfs(integer, integer) OWNER TO postgres;

--
-- TOC entry 1548 (class 1255 OID 5901053)
-- Name: f_collect_searchtext_wmc(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION f_collect_searchtext_wmc(integer) RETURNS text
    LANGUAGE plpgsql
    AS $_$
DECLARE
    p_wmc_id ALIAS FOR $1;
    
    r_keywords RECORD;
    l_result TEXT;
BEGIN
    l_result := '';
    l_result := l_result || (SELECT COALESCE(wmc_title, '') || ' ' || COALESCE(abstract, '') FROM mb_user_wmc WHERE wmc_serial_id = p_wmc_id);
    FOR r_keywords IN SELECT DISTINCT keyword FROM
        (SELECT keyword FROM wmc_keyword L JOIN keyword K ON (K.keyword_id = L.fkey_keyword_id )
        ) AS __keywords__ LOOP
        l_result := l_result || ' ' || COALESCE(r_keywords.keyword, '');
    END LOOP;
   l_result := UPPER(l_result);
   l_result := replace(replace(replace(replace(replace(replace(replace(l_result,'Ãƒâ€ž','AE'),'ÃƒÅ¸','SS'),'Ãƒâ€“','OE'),'ÃƒÅ“','UE'),'ÃƒÂ¤','AE'),'ÃƒÂ¼','UE'),'ÃƒÂ¶','OE');

    RETURN l_result;
END;
$_$;


ALTER FUNCTION public.f_collect_searchtext_wmc(integer) OWNER TO postgres;

--
-- TOC entry 1549 (class 1255 OID 5901054)
-- Name: f_collect_topic_cat_layer(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION f_collect_topic_cat_layer(integer) RETURNS text
    LANGUAGE plpgsql STRICT
    AS $_$DECLARE
  i_layer_id ALIAS FOR $1;
  topic_cat_string  TEXT;
  topic_cat_record  RECORD;

BEGIN
topic_cat_string := '';

FOR topic_cat_record IN SELECT layer_md_topic_category.fkey_md_topic_category_id from layer_md_topic_category WHERE layer_md_topic_category.fkey_layer_id=$1  LOOP
topic_cat_string := topic_cat_string || '{' ||topic_cat_record.fkey_md_topic_category_id || '}';
END LOOP ;
  
RETURN topic_cat_string;

END;
$_$;


ALTER FUNCTION public.f_collect_topic_cat_layer(integer) OWNER TO postgres;

--
-- TOC entry 1550 (class 1255 OID 5901055)
-- Name: f_collect_topic_cat_wfs_featuretype(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION f_collect_topic_cat_wfs_featuretype(integer) RETURNS text
    LANGUAGE plpgsql STRICT
    AS $_$DECLARE
  i_featuretype_id ALIAS FOR $1;
  topic_cat_string  TEXT;
  topic_cat_record  RECORD;

BEGIN
topic_cat_string := '';

FOR topic_cat_record IN SELECT wfs_featuretype_md_topic_category.fkey_md_topic_category_id from wfs_featuretype_md_topic_category WHERE wfs_featuretype_md_topic_category.fkey_featuretype_id=$1  LOOP
topic_cat_string := topic_cat_string || '{' ||topic_cat_record.fkey_md_topic_category_id || '}';
END LOOP ;
  
RETURN topic_cat_string;

END;
$_$;


ALTER FUNCTION public.f_collect_topic_cat_wfs_featuretype(integer) OWNER TO postgres;

--
-- TOC entry 1551 (class 1255 OID 5901056)
-- Name: f_collect_topic_cat_wmc(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION f_collect_topic_cat_wmc(integer) RETURNS text
    LANGUAGE plpgsql STRICT
    AS $_$DECLARE
  i_wmc_serial_id ALIAS FOR $1;
  topic_cat_string  TEXT;
  topic_cat_record  RECORD;

BEGIN
topic_cat_string := '';

FOR topic_cat_record IN SELECT wmc_md_topic_category.fkey_md_topic_category_id from wmc_md_topic_category WHERE wmc_md_topic_category.fkey_wmc_serial_id=$1  LOOP
topic_cat_string := topic_cat_string || '{' ||topic_cat_record.fkey_md_topic_category_id || '}';
END LOOP ;
  
RETURN topic_cat_string;

END;
$_$;


ALTER FUNCTION public.f_collect_topic_cat_wmc(integer) OWNER TO postgres;

--
-- TOC entry 1552 (class 1255 OID 5901057)
-- Name: f_getwfs_tou(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION f_getwfs_tou(integer) RETURNS integer
    LANGUAGE plpgsql
    AS $_$
DECLARE
   wfs_tou int4;
BEGIN
wfs_tou := fkey_termsofuse_id from wfs_termsofuse where wfs_termsofuse.fkey_wfs_id=$1; 
RETURN wfs_tou;

END;
$_$;


ALTER FUNCTION public.f_getwfs_tou(integer) OWNER TO postgres;

--
-- TOC entry 1553 (class 1255 OID 5901058)
-- Name: f_getwfsmodultype(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION f_getwfsmodultype(integer) RETURNS integer
    LANGUAGE plpgsql
    AS $_$
DECLARE
    i_search INT4;
BEGIN
i_search := count(*) from wfs_conf, wfs_conf_element where wfs_conf.wfs_conf_id=$1 and wfs_conf.wfs_conf_id=wfs_conf_element.fkey_wfs_conf_id and f_search=1;
IF i_search > 0 THEN 
RETURN 1;
else
RETURN 0;
END IF;

END;
$_$;


ALTER FUNCTION public.f_getwfsmodultype(integer) OWNER TO postgres;

--
-- TOC entry 1554 (class 1255 OID 5901059)
-- Name: f_getwms_tou(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION f_getwms_tou(integer) RETURNS integer
    LANGUAGE plpgsql
    AS $_$
DECLARE
   wms_tou int4;
BEGIN
wms_tou := fkey_termsofuse_id from wms_termsofuse where wms_termsofuse.fkey_wms_id=$1; 
RETURN wms_tou;

END;
$_$;


ALTER FUNCTION public.f_getwms_tou(integer) OWNER TO postgres;

--
-- TOC entry 1555 (class 1255 OID 5901060)
-- Name: f_layer_load_count(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION f_layer_load_count(integer) RETURNS integer
    LANGUAGE plpgsql
    AS $_$
DECLARE
   layer_rel int4;
BEGIN
layer_rel := load_count from layer_load_count where layer_load_count.fkey_layer_id=$1; 
RETURN layer_rel;

END;
$_$;


ALTER FUNCTION public.f_layer_load_count(integer) OWNER TO postgres;

--
-- TOC entry 1556 (class 1255 OID 5901061)
-- Name: f_wmc_load_count(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION f_wmc_load_count(integer) RETURNS integer
    LANGUAGE plpgsql
    AS $_$
DECLARE
   wmc_rel int8;
BEGIN
wmc_rel := load_count from wmc_load_count where wmc_load_count.fkey_wmc_serial_id=$1; 
IF wmc_rel IS NULL THEN
	RETURN 0;
ELSE
	RETURN wmc_rel;
END IF;
END;
$_$;


ALTER FUNCTION public.f_wmc_load_count(integer) OWNER TO postgres;

--
-- TOC entry 1557 (class 1255 OID 5901062)
-- Name: find_extent(text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION find_extent(text, text) RETURNS box2d
    LANGUAGE plpgsql IMMUTABLE STRICT
    AS $_$
DECLARE
	tablename alias for $1;
	columnname alias for $2;
	myrec RECORD;

BEGIN
	FOR myrec IN EXECUTE 'SELECT ST_Extent("' || columnname || '") As extent FROM "' || tablename || '"' LOOP
		return myrec.extent;
	END LOOP;
END;
$_$;


ALTER FUNCTION public.find_extent(text, text) OWNER TO postgres;

--
-- TOC entry 1558 (class 1255 OID 5901063)
-- Name: find_extent(text, text, text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION find_extent(text, text, text) RETURNS box2d
    LANGUAGE plpgsql IMMUTABLE STRICT
    AS $_$
DECLARE
	schemaname alias for $1;
	tablename alias for $2;
	columnname alias for $3;
	myrec RECORD;

BEGIN
	FOR myrec IN EXECUTE 'SELECT ST_Extent("' || columnname || '") FROM "' || schemaname || '"."' || tablename || '" As extent ' LOOP
		return myrec.extent;
	END LOOP;
END;
$_$;


ALTER FUNCTION public.find_extent(text, text, text) OWNER TO postgres;

--
-- TOC entry 1559 (class 1255 OID 5901064)
-- Name: fix_geometry_columns(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION fix_geometry_columns() RETURNS text
    LANGUAGE plpgsql
    AS $$
DECLARE
	mislinked record;
	result text;
	linked integer;
	deleted integer;
	foundschema integer;
BEGIN

	-- Since 7.3 schema support has been added.
	-- Previous postgis versions used to put the database name in
	-- the schema column. This needs to be fixed, so we try to
	-- set the correct schema for each geometry_colums record
	-- looking at table, column, type and srid.
	
	return 'This function is obsolete now that geometry_columns is a view';

END;
$$;


ALTER FUNCTION public.fix_geometry_columns() OWNER TO postgres;

--
-- TOC entry 1560 (class 1255 OID 5901065)
-- Name: gbt_bit_compress(internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_bit_compress(internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_bit_compress';


ALTER FUNCTION public.gbt_bit_compress(internal) OWNER TO postgres;

--
-- TOC entry 1561 (class 1255 OID 5901066)
-- Name: gbt_bit_consistent(internal, bit, smallint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_bit_consistent(internal, bit, smallint) RETURNS boolean
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_bit_consistent';


ALTER FUNCTION public.gbt_bit_consistent(internal, bit, smallint) OWNER TO postgres;

--
-- TOC entry 1562 (class 1255 OID 5901067)
-- Name: gbt_bit_penalty(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_bit_penalty(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbt_bit_penalty';


ALTER FUNCTION public.gbt_bit_penalty(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1563 (class 1255 OID 5901068)
-- Name: gbt_bit_picksplit(internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_bit_picksplit(internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_bit_picksplit';


ALTER FUNCTION public.gbt_bit_picksplit(internal, internal) OWNER TO postgres;

--
-- TOC entry 1564 (class 1255 OID 5901069)
-- Name: gbt_bit_same(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_bit_same(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_bit_same';


ALTER FUNCTION public.gbt_bit_same(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1565 (class 1255 OID 5901070)
-- Name: gbt_bit_union(bytea, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_bit_union(bytea, internal) RETURNS gbtreekey_var
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_bit_union';


ALTER FUNCTION public.gbt_bit_union(bytea, internal) OWNER TO postgres;

--
-- TOC entry 1566 (class 1255 OID 5901071)
-- Name: gbt_bpchar_compress(internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_bpchar_compress(internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_bpchar_compress';


ALTER FUNCTION public.gbt_bpchar_compress(internal) OWNER TO postgres;

--
-- TOC entry 1567 (class 1255 OID 5901072)
-- Name: gbt_bpchar_consistent(internal, character, smallint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_bpchar_consistent(internal, character, smallint) RETURNS boolean
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_bpchar_consistent';


ALTER FUNCTION public.gbt_bpchar_consistent(internal, character, smallint) OWNER TO postgres;

--
-- TOC entry 1568 (class 1255 OID 5901073)
-- Name: gbt_bytea_compress(internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_bytea_compress(internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_bytea_compress';


ALTER FUNCTION public.gbt_bytea_compress(internal) OWNER TO postgres;

--
-- TOC entry 1569 (class 1255 OID 5901074)
-- Name: gbt_bytea_consistent(internal, bytea, smallint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_bytea_consistent(internal, bytea, smallint) RETURNS boolean
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_bytea_consistent';


ALTER FUNCTION public.gbt_bytea_consistent(internal, bytea, smallint) OWNER TO postgres;

--
-- TOC entry 1570 (class 1255 OID 5901075)
-- Name: gbt_bytea_penalty(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_bytea_penalty(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbt_bytea_penalty';


ALTER FUNCTION public.gbt_bytea_penalty(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1571 (class 1255 OID 5901076)
-- Name: gbt_bytea_picksplit(internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_bytea_picksplit(internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_bytea_picksplit';


ALTER FUNCTION public.gbt_bytea_picksplit(internal, internal) OWNER TO postgres;

--
-- TOC entry 1572 (class 1255 OID 5901077)
-- Name: gbt_bytea_same(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_bytea_same(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_bytea_same';


ALTER FUNCTION public.gbt_bytea_same(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1573 (class 1255 OID 5901078)
-- Name: gbt_bytea_union(bytea, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_bytea_union(bytea, internal) RETURNS gbtreekey_var
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_bytea_union';


ALTER FUNCTION public.gbt_bytea_union(bytea, internal) OWNER TO postgres;

--
-- TOC entry 1574 (class 1255 OID 5901079)
-- Name: gbt_cash_compress(internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_cash_compress(internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_cash_compress';


ALTER FUNCTION public.gbt_cash_compress(internal) OWNER TO postgres;

--
-- TOC entry 1575 (class 1255 OID 5901080)
-- Name: gbt_cash_consistent(internal, money, smallint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_cash_consistent(internal, money, smallint) RETURNS boolean
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_cash_consistent';


ALTER FUNCTION public.gbt_cash_consistent(internal, money, smallint) OWNER TO postgres;

--
-- TOC entry 1576 (class 1255 OID 5901081)
-- Name: gbt_cash_penalty(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_cash_penalty(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbt_cash_penalty';


ALTER FUNCTION public.gbt_cash_penalty(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1577 (class 1255 OID 5901082)
-- Name: gbt_cash_picksplit(internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_cash_picksplit(internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_cash_picksplit';


ALTER FUNCTION public.gbt_cash_picksplit(internal, internal) OWNER TO postgres;

--
-- TOC entry 1578 (class 1255 OID 5901083)
-- Name: gbt_cash_same(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_cash_same(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_cash_same';


ALTER FUNCTION public.gbt_cash_same(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1579 (class 1255 OID 5901084)
-- Name: gbt_cash_union(bytea, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_cash_union(bytea, internal) RETURNS gbtreekey8
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_cash_union';


ALTER FUNCTION public.gbt_cash_union(bytea, internal) OWNER TO postgres;

--
-- TOC entry 1580 (class 1255 OID 5901085)
-- Name: gbt_date_compress(internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_date_compress(internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_date_compress';


ALTER FUNCTION public.gbt_date_compress(internal) OWNER TO postgres;

--
-- TOC entry 1581 (class 1255 OID 5901086)
-- Name: gbt_date_consistent(internal, date, smallint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_date_consistent(internal, date, smallint) RETURNS boolean
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_date_consistent';


ALTER FUNCTION public.gbt_date_consistent(internal, date, smallint) OWNER TO postgres;

--
-- TOC entry 1582 (class 1255 OID 5901087)
-- Name: gbt_date_penalty(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_date_penalty(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbt_date_penalty';


ALTER FUNCTION public.gbt_date_penalty(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1583 (class 1255 OID 5901088)
-- Name: gbt_date_picksplit(internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_date_picksplit(internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_date_picksplit';


ALTER FUNCTION public.gbt_date_picksplit(internal, internal) OWNER TO postgres;

--
-- TOC entry 1584 (class 1255 OID 5901089)
-- Name: gbt_date_same(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_date_same(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_date_same';


ALTER FUNCTION public.gbt_date_same(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1585 (class 1255 OID 5901090)
-- Name: gbt_date_union(bytea, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_date_union(bytea, internal) RETURNS gbtreekey8
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_date_union';


ALTER FUNCTION public.gbt_date_union(bytea, internal) OWNER TO postgres;

--
-- TOC entry 1586 (class 1255 OID 5901091)
-- Name: gbt_decompress(internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_decompress(internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_decompress';


ALTER FUNCTION public.gbt_decompress(internal) OWNER TO postgres;

--
-- TOC entry 1587 (class 1255 OID 5901092)
-- Name: gbt_float4_compress(internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_float4_compress(internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_float4_compress';


ALTER FUNCTION public.gbt_float4_compress(internal) OWNER TO postgres;

--
-- TOC entry 1588 (class 1255 OID 5901093)
-- Name: gbt_float4_consistent(internal, real, smallint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_float4_consistent(internal, real, smallint) RETURNS boolean
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_float4_consistent';


ALTER FUNCTION public.gbt_float4_consistent(internal, real, smallint) OWNER TO postgres;

--
-- TOC entry 1589 (class 1255 OID 5901094)
-- Name: gbt_float4_penalty(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_float4_penalty(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbt_float4_penalty';


ALTER FUNCTION public.gbt_float4_penalty(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1590 (class 1255 OID 5901095)
-- Name: gbt_float4_picksplit(internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_float4_picksplit(internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_float4_picksplit';


ALTER FUNCTION public.gbt_float4_picksplit(internal, internal) OWNER TO postgres;

--
-- TOC entry 1591 (class 1255 OID 5901096)
-- Name: gbt_float4_same(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_float4_same(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_float4_same';


ALTER FUNCTION public.gbt_float4_same(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1592 (class 1255 OID 5901097)
-- Name: gbt_float4_union(bytea, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_float4_union(bytea, internal) RETURNS gbtreekey8
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_float4_union';


ALTER FUNCTION public.gbt_float4_union(bytea, internal) OWNER TO postgres;

--
-- TOC entry 1593 (class 1255 OID 5901098)
-- Name: gbt_float8_compress(internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_float8_compress(internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_float8_compress';


ALTER FUNCTION public.gbt_float8_compress(internal) OWNER TO postgres;

--
-- TOC entry 1594 (class 1255 OID 5901099)
-- Name: gbt_float8_consistent(internal, double precision, smallint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_float8_consistent(internal, double precision, smallint) RETURNS boolean
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_float8_consistent';


ALTER FUNCTION public.gbt_float8_consistent(internal, double precision, smallint) OWNER TO postgres;

--
-- TOC entry 1595 (class 1255 OID 5901100)
-- Name: gbt_float8_penalty(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_float8_penalty(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbt_float8_penalty';


ALTER FUNCTION public.gbt_float8_penalty(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1596 (class 1255 OID 5901101)
-- Name: gbt_float8_picksplit(internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_float8_picksplit(internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_float8_picksplit';


ALTER FUNCTION public.gbt_float8_picksplit(internal, internal) OWNER TO postgres;

--
-- TOC entry 1597 (class 1255 OID 5901102)
-- Name: gbt_float8_same(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_float8_same(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_float8_same';


ALTER FUNCTION public.gbt_float8_same(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1598 (class 1255 OID 5901103)
-- Name: gbt_float8_union(bytea, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_float8_union(bytea, internal) RETURNS gbtreekey16
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_float8_union';


ALTER FUNCTION public.gbt_float8_union(bytea, internal) OWNER TO postgres;

--
-- TOC entry 1599 (class 1255 OID 5901104)
-- Name: gbt_inet_compress(internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_inet_compress(internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_inet_compress';


ALTER FUNCTION public.gbt_inet_compress(internal) OWNER TO postgres;

--
-- TOC entry 1600 (class 1255 OID 5901105)
-- Name: gbt_inet_consistent(internal, inet, smallint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_inet_consistent(internal, inet, smallint) RETURNS boolean
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_inet_consistent';


ALTER FUNCTION public.gbt_inet_consistent(internal, inet, smallint) OWNER TO postgres;

--
-- TOC entry 1601 (class 1255 OID 5901106)
-- Name: gbt_inet_penalty(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_inet_penalty(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbt_inet_penalty';


ALTER FUNCTION public.gbt_inet_penalty(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1602 (class 1255 OID 5901107)
-- Name: gbt_inet_picksplit(internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_inet_picksplit(internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_inet_picksplit';


ALTER FUNCTION public.gbt_inet_picksplit(internal, internal) OWNER TO postgres;

--
-- TOC entry 1603 (class 1255 OID 5901108)
-- Name: gbt_inet_same(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_inet_same(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_inet_same';


ALTER FUNCTION public.gbt_inet_same(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1604 (class 1255 OID 5901109)
-- Name: gbt_inet_union(bytea, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_inet_union(bytea, internal) RETURNS gbtreekey16
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_inet_union';


ALTER FUNCTION public.gbt_inet_union(bytea, internal) OWNER TO postgres;

--
-- TOC entry 1605 (class 1255 OID 5901110)
-- Name: gbt_int2_compress(internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_int2_compress(internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_int2_compress';


ALTER FUNCTION public.gbt_int2_compress(internal) OWNER TO postgres;

--
-- TOC entry 1606 (class 1255 OID 5901111)
-- Name: gbt_int2_consistent(internal, smallint, smallint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_int2_consistent(internal, smallint, smallint) RETURNS boolean
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_int2_consistent';


ALTER FUNCTION public.gbt_int2_consistent(internal, smallint, smallint) OWNER TO postgres;

--
-- TOC entry 1607 (class 1255 OID 5901112)
-- Name: gbt_int2_penalty(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_int2_penalty(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbt_int2_penalty';


ALTER FUNCTION public.gbt_int2_penalty(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1608 (class 1255 OID 5901113)
-- Name: gbt_int2_picksplit(internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_int2_picksplit(internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_int2_picksplit';


ALTER FUNCTION public.gbt_int2_picksplit(internal, internal) OWNER TO postgres;

--
-- TOC entry 1609 (class 1255 OID 5901114)
-- Name: gbt_int2_same(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_int2_same(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_int2_same';


ALTER FUNCTION public.gbt_int2_same(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1610 (class 1255 OID 5901115)
-- Name: gbt_int2_union(bytea, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_int2_union(bytea, internal) RETURNS gbtreekey4
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_int2_union';


ALTER FUNCTION public.gbt_int2_union(bytea, internal) OWNER TO postgres;

--
-- TOC entry 1611 (class 1255 OID 5901116)
-- Name: gbt_int4_compress(internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_int4_compress(internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_int4_compress';


ALTER FUNCTION public.gbt_int4_compress(internal) OWNER TO postgres;

--
-- TOC entry 1612 (class 1255 OID 5901117)
-- Name: gbt_int4_consistent(internal, integer, smallint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_int4_consistent(internal, integer, smallint) RETURNS boolean
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_int4_consistent';


ALTER FUNCTION public.gbt_int4_consistent(internal, integer, smallint) OWNER TO postgres;

--
-- TOC entry 1613 (class 1255 OID 5901118)
-- Name: gbt_int4_penalty(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_int4_penalty(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbt_int4_penalty';


ALTER FUNCTION public.gbt_int4_penalty(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1614 (class 1255 OID 5901119)
-- Name: gbt_int4_picksplit(internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_int4_picksplit(internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_int4_picksplit';


ALTER FUNCTION public.gbt_int4_picksplit(internal, internal) OWNER TO postgres;

--
-- TOC entry 1615 (class 1255 OID 5901120)
-- Name: gbt_int4_same(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_int4_same(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_int4_same';


ALTER FUNCTION public.gbt_int4_same(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1616 (class 1255 OID 5901121)
-- Name: gbt_int4_union(bytea, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_int4_union(bytea, internal) RETURNS gbtreekey8
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_int4_union';


ALTER FUNCTION public.gbt_int4_union(bytea, internal) OWNER TO postgres;

--
-- TOC entry 1617 (class 1255 OID 5901122)
-- Name: gbt_int8_compress(internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_int8_compress(internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_int8_compress';


ALTER FUNCTION public.gbt_int8_compress(internal) OWNER TO postgres;

--
-- TOC entry 1618 (class 1255 OID 5901123)
-- Name: gbt_int8_consistent(internal, bigint, smallint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_int8_consistent(internal, bigint, smallint) RETURNS boolean
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_int8_consistent';


ALTER FUNCTION public.gbt_int8_consistent(internal, bigint, smallint) OWNER TO postgres;

--
-- TOC entry 1619 (class 1255 OID 5901124)
-- Name: gbt_int8_penalty(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_int8_penalty(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbt_int8_penalty';


ALTER FUNCTION public.gbt_int8_penalty(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1620 (class 1255 OID 5901125)
-- Name: gbt_int8_picksplit(internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_int8_picksplit(internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_int8_picksplit';


ALTER FUNCTION public.gbt_int8_picksplit(internal, internal) OWNER TO postgres;

--
-- TOC entry 1621 (class 1255 OID 5901126)
-- Name: gbt_int8_same(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_int8_same(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_int8_same';


ALTER FUNCTION public.gbt_int8_same(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1622 (class 1255 OID 5901127)
-- Name: gbt_int8_union(bytea, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_int8_union(bytea, internal) RETURNS gbtreekey16
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_int8_union';


ALTER FUNCTION public.gbt_int8_union(bytea, internal) OWNER TO postgres;

--
-- TOC entry 1623 (class 1255 OID 5901128)
-- Name: gbt_intv_compress(internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_intv_compress(internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_intv_compress';


ALTER FUNCTION public.gbt_intv_compress(internal) OWNER TO postgres;

--
-- TOC entry 1624 (class 1255 OID 5901129)
-- Name: gbt_intv_consistent(internal, interval, smallint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_intv_consistent(internal, interval, smallint) RETURNS boolean
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_intv_consistent';


ALTER FUNCTION public.gbt_intv_consistent(internal, interval, smallint) OWNER TO postgres;

--
-- TOC entry 1625 (class 1255 OID 5901130)
-- Name: gbt_intv_decompress(internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_intv_decompress(internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_intv_decompress';


ALTER FUNCTION public.gbt_intv_decompress(internal) OWNER TO postgres;

--
-- TOC entry 1626 (class 1255 OID 5901131)
-- Name: gbt_intv_penalty(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_intv_penalty(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbt_intv_penalty';


ALTER FUNCTION public.gbt_intv_penalty(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1627 (class 1255 OID 5901132)
-- Name: gbt_intv_picksplit(internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_intv_picksplit(internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_intv_picksplit';


ALTER FUNCTION public.gbt_intv_picksplit(internal, internal) OWNER TO postgres;

--
-- TOC entry 1628 (class 1255 OID 5901133)
-- Name: gbt_intv_same(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_intv_same(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_intv_same';


ALTER FUNCTION public.gbt_intv_same(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1629 (class 1255 OID 5901134)
-- Name: gbt_intv_union(bytea, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_intv_union(bytea, internal) RETURNS gbtreekey32
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_intv_union';


ALTER FUNCTION public.gbt_intv_union(bytea, internal) OWNER TO postgres;

--
-- TOC entry 1630 (class 1255 OID 5901135)
-- Name: gbt_macad_compress(internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_macad_compress(internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_macad_compress';


ALTER FUNCTION public.gbt_macad_compress(internal) OWNER TO postgres;

--
-- TOC entry 1631 (class 1255 OID 5901136)
-- Name: gbt_macad_consistent(internal, macaddr, smallint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_macad_consistent(internal, macaddr, smallint) RETURNS boolean
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_macad_consistent';


ALTER FUNCTION public.gbt_macad_consistent(internal, macaddr, smallint) OWNER TO postgres;

--
-- TOC entry 1632 (class 1255 OID 5901137)
-- Name: gbt_macad_penalty(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_macad_penalty(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbt_macad_penalty';


ALTER FUNCTION public.gbt_macad_penalty(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1633 (class 1255 OID 5901138)
-- Name: gbt_macad_picksplit(internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_macad_picksplit(internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_macad_picksplit';


ALTER FUNCTION public.gbt_macad_picksplit(internal, internal) OWNER TO postgres;

--
-- TOC entry 1634 (class 1255 OID 5901139)
-- Name: gbt_macad_same(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_macad_same(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_macad_same';


ALTER FUNCTION public.gbt_macad_same(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1635 (class 1255 OID 5901140)
-- Name: gbt_macad_union(bytea, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_macad_union(bytea, internal) RETURNS gbtreekey16
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_macad_union';


ALTER FUNCTION public.gbt_macad_union(bytea, internal) OWNER TO postgres;

--
-- TOC entry 1636 (class 1255 OID 5901141)
-- Name: gbt_numeric_compress(internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_numeric_compress(internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_numeric_compress';


ALTER FUNCTION public.gbt_numeric_compress(internal) OWNER TO postgres;

--
-- TOC entry 1637 (class 1255 OID 5901142)
-- Name: gbt_numeric_consistent(internal, numeric, smallint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_numeric_consistent(internal, numeric, smallint) RETURNS boolean
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_numeric_consistent';


ALTER FUNCTION public.gbt_numeric_consistent(internal, numeric, smallint) OWNER TO postgres;

--
-- TOC entry 1638 (class 1255 OID 5901143)
-- Name: gbt_numeric_penalty(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_numeric_penalty(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbt_numeric_penalty';


ALTER FUNCTION public.gbt_numeric_penalty(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1639 (class 1255 OID 5901144)
-- Name: gbt_numeric_picksplit(internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_numeric_picksplit(internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_numeric_picksplit';


ALTER FUNCTION public.gbt_numeric_picksplit(internal, internal) OWNER TO postgres;

--
-- TOC entry 1640 (class 1255 OID 5901145)
-- Name: gbt_numeric_same(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_numeric_same(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_numeric_same';


ALTER FUNCTION public.gbt_numeric_same(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1641 (class 1255 OID 5901146)
-- Name: gbt_numeric_union(bytea, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_numeric_union(bytea, internal) RETURNS gbtreekey_var
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_numeric_union';


ALTER FUNCTION public.gbt_numeric_union(bytea, internal) OWNER TO postgres;

--
-- TOC entry 1642 (class 1255 OID 5901147)
-- Name: gbt_oid_compress(internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_oid_compress(internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_oid_compress';


ALTER FUNCTION public.gbt_oid_compress(internal) OWNER TO postgres;

--
-- TOC entry 1643 (class 1255 OID 5901148)
-- Name: gbt_oid_consistent(internal, oid, smallint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_oid_consistent(internal, oid, smallint) RETURNS boolean
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_oid_consistent';


ALTER FUNCTION public.gbt_oid_consistent(internal, oid, smallint) OWNER TO postgres;

--
-- TOC entry 1644 (class 1255 OID 5901149)
-- Name: gbt_oid_penalty(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_oid_penalty(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbt_oid_penalty';


ALTER FUNCTION public.gbt_oid_penalty(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1645 (class 1255 OID 5901150)
-- Name: gbt_oid_picksplit(internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_oid_picksplit(internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_oid_picksplit';


ALTER FUNCTION public.gbt_oid_picksplit(internal, internal) OWNER TO postgres;

--
-- TOC entry 1646 (class 1255 OID 5901151)
-- Name: gbt_oid_same(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_oid_same(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_oid_same';


ALTER FUNCTION public.gbt_oid_same(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1647 (class 1255 OID 5901152)
-- Name: gbt_oid_union(bytea, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_oid_union(bytea, internal) RETURNS gbtreekey8
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_oid_union';


ALTER FUNCTION public.gbt_oid_union(bytea, internal) OWNER TO postgres;

--
-- TOC entry 1648 (class 1255 OID 5901153)
-- Name: gbt_text_compress(internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_text_compress(internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_text_compress';


ALTER FUNCTION public.gbt_text_compress(internal) OWNER TO postgres;

--
-- TOC entry 1649 (class 1255 OID 5901154)
-- Name: gbt_text_consistent(internal, text, smallint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_text_consistent(internal, text, smallint) RETURNS boolean
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_text_consistent';


ALTER FUNCTION public.gbt_text_consistent(internal, text, smallint) OWNER TO postgres;

--
-- TOC entry 1650 (class 1255 OID 5901155)
-- Name: gbt_text_penalty(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_text_penalty(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbt_text_penalty';


ALTER FUNCTION public.gbt_text_penalty(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1651 (class 1255 OID 5901156)
-- Name: gbt_text_picksplit(internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_text_picksplit(internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_text_picksplit';


ALTER FUNCTION public.gbt_text_picksplit(internal, internal) OWNER TO postgres;

--
-- TOC entry 1652 (class 1255 OID 5901157)
-- Name: gbt_text_same(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_text_same(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_text_same';


ALTER FUNCTION public.gbt_text_same(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1653 (class 1255 OID 5901158)
-- Name: gbt_text_union(bytea, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_text_union(bytea, internal) RETURNS gbtreekey_var
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_text_union';


ALTER FUNCTION public.gbt_text_union(bytea, internal) OWNER TO postgres;

--
-- TOC entry 1654 (class 1255 OID 5901159)
-- Name: gbt_time_compress(internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_time_compress(internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_time_compress';


ALTER FUNCTION public.gbt_time_compress(internal) OWNER TO postgres;

--
-- TOC entry 1655 (class 1255 OID 5901160)
-- Name: gbt_time_consistent(internal, time without time zone, smallint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_time_consistent(internal, time without time zone, smallint) RETURNS boolean
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_time_consistent';


ALTER FUNCTION public.gbt_time_consistent(internal, time without time zone, smallint) OWNER TO postgres;

--
-- TOC entry 1656 (class 1255 OID 5901161)
-- Name: gbt_time_penalty(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_time_penalty(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbt_time_penalty';


ALTER FUNCTION public.gbt_time_penalty(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1657 (class 1255 OID 5901162)
-- Name: gbt_time_picksplit(internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_time_picksplit(internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_time_picksplit';


ALTER FUNCTION public.gbt_time_picksplit(internal, internal) OWNER TO postgres;

--
-- TOC entry 1658 (class 1255 OID 5901163)
-- Name: gbt_time_same(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_time_same(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_time_same';


ALTER FUNCTION public.gbt_time_same(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1659 (class 1255 OID 5901164)
-- Name: gbt_time_union(bytea, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_time_union(bytea, internal) RETURNS gbtreekey16
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_time_union';


ALTER FUNCTION public.gbt_time_union(bytea, internal) OWNER TO postgres;

--
-- TOC entry 1660 (class 1255 OID 5901165)
-- Name: gbt_timetz_compress(internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_timetz_compress(internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_timetz_compress';


ALTER FUNCTION public.gbt_timetz_compress(internal) OWNER TO postgres;

--
-- TOC entry 1661 (class 1255 OID 5901166)
-- Name: gbt_timetz_consistent(internal, time with time zone, smallint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_timetz_consistent(internal, time with time zone, smallint) RETURNS boolean
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_timetz_consistent';


ALTER FUNCTION public.gbt_timetz_consistent(internal, time with time zone, smallint) OWNER TO postgres;

--
-- TOC entry 1662 (class 1255 OID 5901167)
-- Name: gbt_ts_compress(internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_ts_compress(internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_ts_compress';


ALTER FUNCTION public.gbt_ts_compress(internal) OWNER TO postgres;

--
-- TOC entry 1663 (class 1255 OID 5901168)
-- Name: gbt_ts_consistent(internal, timestamp without time zone, smallint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_ts_consistent(internal, timestamp without time zone, smallint) RETURNS boolean
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_ts_consistent';


ALTER FUNCTION public.gbt_ts_consistent(internal, timestamp without time zone, smallint) OWNER TO postgres;

--
-- TOC entry 1664 (class 1255 OID 5901169)
-- Name: gbt_ts_penalty(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_ts_penalty(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE STRICT
    AS '$libdir/btree_gist', 'gbt_ts_penalty';


ALTER FUNCTION public.gbt_ts_penalty(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1665 (class 1255 OID 5901170)
-- Name: gbt_ts_picksplit(internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_ts_picksplit(internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_ts_picksplit';


ALTER FUNCTION public.gbt_ts_picksplit(internal, internal) OWNER TO postgres;

--
-- TOC entry 1666 (class 1255 OID 5901171)
-- Name: gbt_ts_same(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_ts_same(internal, internal, internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_ts_same';


ALTER FUNCTION public.gbt_ts_same(internal, internal, internal) OWNER TO postgres;

--
-- TOC entry 1667 (class 1255 OID 5901172)
-- Name: gbt_ts_union(bytea, internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_ts_union(bytea, internal) RETURNS gbtreekey16
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_ts_union';


ALTER FUNCTION public.gbt_ts_union(bytea, internal) OWNER TO postgres;

--
-- TOC entry 1668 (class 1255 OID 5901173)
-- Name: gbt_tstz_compress(internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_tstz_compress(internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_tstz_compress';


ALTER FUNCTION public.gbt_tstz_compress(internal) OWNER TO postgres;

--
-- TOC entry 1669 (class 1255 OID 5901174)
-- Name: gbt_tstz_consistent(internal, timestamp with time zone, smallint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_tstz_consistent(internal, timestamp with time zone, smallint) RETURNS boolean
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_tstz_consistent';


ALTER FUNCTION public.gbt_tstz_consistent(internal, timestamp with time zone, smallint) OWNER TO postgres;

--
-- TOC entry 1670 (class 1255 OID 5901175)
-- Name: gbt_var_decompress(internal); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gbt_var_decompress(internal) RETURNS internal
    LANGUAGE c IMMUTABLE
    AS '$libdir/btree_gist', 'gbt_var_decompress';


ALTER FUNCTION public.gbt_var_decompress(internal) OWNER TO postgres;

--
-- TOC entry 1671 (class 1255 OID 5901176)
-- Name: geomcollfromtext(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION geomcollfromtext(text) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE
	WHEN geometrytype(GeomFromText($1)) = 'GEOMETRYCOLLECTION'
	THEN GeomFromText($1)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.geomcollfromtext(text) OWNER TO postgres;

--
-- TOC entry 1672 (class 1255 OID 5901177)
-- Name: geomcollfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION geomcollfromtext(text, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE
	WHEN geometrytype(GeomFromText($1, $2)) = 'GEOMETRYCOLLECTION'
	THEN GeomFromText($1,$2)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.geomcollfromtext(text, integer) OWNER TO postgres;

--
-- TOC entry 1673 (class 1255 OID 5901178)
-- Name: geomcollfromwkb(bytea); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION geomcollfromwkb(bytea) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE
	WHEN geometrytype(GeomFromWKB($1)) = 'GEOMETRYCOLLECTION'
	THEN GeomFromWKB($1)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.geomcollfromwkb(bytea) OWNER TO postgres;

--
-- TOC entry 1674 (class 1255 OID 5901179)
-- Name: geomcollfromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION geomcollfromwkb(bytea, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE
	WHEN geometrytype(GeomFromWKB($1, $2)) = 'GEOMETRYCOLLECTION'
	THEN GeomFromWKB($1, $2)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.geomcollfromwkb(bytea, integer) OWNER TO postgres;

--
-- TOC entry 1675 (class 1255 OID 5901180)
-- Name: geomfromtext(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION geomfromtext(text) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT ST_GeomFromText($1)$_$;


ALTER FUNCTION public.geomfromtext(text) OWNER TO postgres;

--
-- TOC entry 1676 (class 1255 OID 5901181)
-- Name: geomfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION geomfromtext(text, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT ST_GeomFromText($1, $2)$_$;


ALTER FUNCTION public.geomfromtext(text, integer) OWNER TO postgres;

--
-- TOC entry 1677 (class 1255 OID 5901182)
-- Name: geomfromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION geomfromwkb(bytea, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT ST_SetSRID(ST_GeomFromWKB($1), $2)$_$;


ALTER FUNCTION public.geomfromwkb(bytea, integer) OWNER TO postgres;

--
-- TOC entry 1678 (class 1255 OID 5901183)
-- Name: linefromtext(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION linefromtext(text) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromText($1)) = 'LINESTRING'
	THEN GeomFromText($1)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.linefromtext(text) OWNER TO postgres;

--
-- TOC entry 1679 (class 1255 OID 5901184)
-- Name: linefromtext(text, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION linefromtext(text, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromText($1, $2)) = 'LINESTRING'
	THEN GeomFromText($1,$2)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.linefromtext(text, integer) OWNER TO postgres;

--
-- TOC entry 1680 (class 1255 OID 5901185)
-- Name: linefromwkb(bytea); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION linefromwkb(bytea) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1)) = 'LINESTRING'
	THEN GeomFromWKB($1)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.linefromwkb(bytea) OWNER TO postgres;

--
-- TOC entry 1681 (class 1255 OID 5901186)
-- Name: linefromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION linefromwkb(bytea, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1, $2)) = 'LINESTRING'
	THEN GeomFromWKB($1, $2)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.linefromwkb(bytea, integer) OWNER TO postgres;

--
-- TOC entry 1682 (class 1255 OID 5901187)
-- Name: linestringfromtext(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION linestringfromtext(text) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT LineFromText($1)$_$;


ALTER FUNCTION public.linestringfromtext(text) OWNER TO postgres;

--
-- TOC entry 1683 (class 1255 OID 5901188)
-- Name: linestringfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION linestringfromtext(text, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT LineFromText($1, $2)$_$;


ALTER FUNCTION public.linestringfromtext(text, integer) OWNER TO postgres;

--
-- TOC entry 1684 (class 1255 OID 5901189)
-- Name: linestringfromwkb(bytea); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION linestringfromwkb(bytea) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1)) = 'LINESTRING'
	THEN GeomFromWKB($1)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.linestringfromwkb(bytea) OWNER TO postgres;

--
-- TOC entry 1685 (class 1255 OID 5901190)
-- Name: linestringfromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION linestringfromwkb(bytea, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1, $2)) = 'LINESTRING'
	THEN GeomFromWKB($1, $2)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.linestringfromwkb(bytea, integer) OWNER TO postgres;

--
-- TOC entry 1686 (class 1255 OID 5901191)
-- Name: locate_along_measure(geometry, double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION locate_along_measure(geometry, double precision) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$ SELECT ST_locate_between_measures($1, $2, $2) $_$;


ALTER FUNCTION public.locate_along_measure(geometry, double precision) OWNER TO postgres;

--
-- TOC entry 1687 (class 1255 OID 5901192)
-- Name: mb_monitor_after(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION mb_monitor_after() RETURNS trigger
    LANGUAGE plpgsql
    AS $$DECLARE
   availability_new REAL;
   average_res_cap REAL;
   count_monitors REAL;
    BEGIN
     IF TG_OP = 'UPDATE' THEN
     
     count_monitors := count(fkey_wms_id) from mb_monitor where fkey_wms_id=NEW.fkey_wms_id;
      --the following should be adopted if the duration of storing is changed!!!
      average_res_cap := ((select average_resp_time from mb_wms_availability where fkey_wms_id=NEW.fkey_wms_id)*count_monitors+(NEW.timestamp_end-NEW.timestamp_begin))/(count_monitors+1);

     IF NEW.status > -1 THEN --service gives caps
      availability_new := round(cast(((select availability from mb_wms_availability where fkey_wms_id=NEW.fkey_wms_id)*count_monitors + 100)/(count_monitors+1) as numeric),2);
     ELSE --service has problems with caps
      availability_new := round(cast(((select availability from mb_wms_availability where fkey_wms_id=NEW.fkey_wms_id)*count_monitors)/(count_monitors+1) as numeric),2);
     END IF;

      UPDATE mb_wms_availability SET average_resp_time=average_res_cap,last_status=NEW.status, availability=availability_new, image=NEW.image, status_comment=NEW.status_comment,upload_url=NEW.upload_url,map_url=NEW.map_url, cap_diff=NEW.cap_diff WHERE mb_wms_availability.fkey_wms_id=NEW.fkey_wms_id;
      RETURN NEW;
     END IF;
     IF TG_OP = 'INSERT' THEN

	IF (select count(fkey_wms_id) from mb_wms_availability where fkey_wms_id=NEW.fkey_wms_id) > 0  then -- service is not new
			UPDATE mb_wms_availability set fkey_upload_id=NEW.upload_id,last_status=NEW.status,status_comment=NEW.status_comment,upload_url=NEW.upload_url, cap_diff=NEW.cap_diff where fkey_wms_id=NEW.fkey_wms_id;
		else --service has not yet been monitored
			INSERT INTO mb_wms_availability (fkey_upload_id,fkey_wms_id,last_status,status_comment,upload_url,map_url,cap_diff,average_resp_time,availability) VALUES (NEW.upload_id,NEW.fkey_wms_id,NEW.status,NEW.status_comment,NEW.upload_url::text,NEW.map_url,NEW.cap_diff,0,100);
		end if;

      RETURN NEW;
     END IF;
    END;
$$;


ALTER FUNCTION public.mb_monitor_after() OWNER TO postgres;

--
-- TOC entry 1688 (class 1255 OID 5901193)
-- Name: mlinefromtext(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION mlinefromtext(text) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromText($1)) = 'MULTILINESTRING'
	THEN GeomFromText($1)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.mlinefromtext(text) OWNER TO postgres;

--
-- TOC entry 1689 (class 1255 OID 5901194)
-- Name: mlinefromtext(text, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION mlinefromtext(text, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE
	WHEN geometrytype(GeomFromText($1, $2)) = 'MULTILINESTRING'
	THEN GeomFromText($1,$2)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.mlinefromtext(text, integer) OWNER TO postgres;

--
-- TOC entry 1690 (class 1255 OID 5901195)
-- Name: mlinefromwkb(bytea); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION mlinefromwkb(bytea) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1)) = 'MULTILINESTRING'
	THEN GeomFromWKB($1)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.mlinefromwkb(bytea) OWNER TO postgres;

--
-- TOC entry 1691 (class 1255 OID 5901196)
-- Name: mlinefromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION mlinefromwkb(bytea, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1, $2)) = 'MULTILINESTRING'
	THEN GeomFromWKB($1, $2)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.mlinefromwkb(bytea, integer) OWNER TO postgres;

--
-- TOC entry 1692 (class 1255 OID 5901197)
-- Name: mpointfromtext(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION mpointfromtext(text) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromText($1)) = 'MULTIPOINT'
	THEN GeomFromText($1)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.mpointfromtext(text) OWNER TO postgres;

--
-- TOC entry 1693 (class 1255 OID 5901198)
-- Name: mpointfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION mpointfromtext(text, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromText($1,$2)) = 'MULTIPOINT'
	THEN GeomFromText($1,$2)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.mpointfromtext(text, integer) OWNER TO postgres;

--
-- TOC entry 1694 (class 1255 OID 5901199)
-- Name: mpointfromwkb(bytea); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION mpointfromwkb(bytea) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1)) = 'MULTIPOINT'
	THEN GeomFromWKB($1)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.mpointfromwkb(bytea) OWNER TO postgres;

--
-- TOC entry 1695 (class 1255 OID 5901200)
-- Name: mpointfromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION mpointfromwkb(bytea, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1,$2)) = 'MULTIPOINT'
	THEN GeomFromWKB($1, $2)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.mpointfromwkb(bytea, integer) OWNER TO postgres;

--
-- TOC entry 1696 (class 1255 OID 5901201)
-- Name: mpolyfromtext(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION mpolyfromtext(text) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromText($1)) = 'MULTIPOLYGON'
	THEN GeomFromText($1)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.mpolyfromtext(text) OWNER TO postgres;

--
-- TOC entry 1697 (class 1255 OID 5901202)
-- Name: mpolyfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION mpolyfromtext(text, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromText($1, $2)) = 'MULTIPOLYGON'
	THEN GeomFromText($1,$2)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.mpolyfromtext(text, integer) OWNER TO postgres;

--
-- TOC entry 1698 (class 1255 OID 5901203)
-- Name: mpolyfromwkb(bytea); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION mpolyfromwkb(bytea) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1)) = 'MULTIPOLYGON'
	THEN GeomFromWKB($1)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.mpolyfromwkb(bytea) OWNER TO postgres;

--
-- TOC entry 1699 (class 1255 OID 5901204)
-- Name: mpolyfromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION mpolyfromwkb(bytea, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1, $2)) = 'MULTIPOLYGON'
	THEN GeomFromWKB($1, $2)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.mpolyfromwkb(bytea, integer) OWNER TO postgres;

--
-- TOC entry 1700 (class 1255 OID 5901205)
-- Name: multilinefromwkb(bytea); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION multilinefromwkb(bytea) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1)) = 'MULTILINESTRING'
	THEN GeomFromWKB($1)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.multilinefromwkb(bytea) OWNER TO postgres;

--
-- TOC entry 1701 (class 1255 OID 5901206)
-- Name: multilinefromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION multilinefromwkb(bytea, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1, $2)) = 'MULTILINESTRING'
	THEN GeomFromWKB($1, $2)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.multilinefromwkb(bytea, integer) OWNER TO postgres;

--
-- TOC entry 1702 (class 1255 OID 5901207)
-- Name: multilinestringfromtext(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION multilinestringfromtext(text) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT ST_MLineFromText($1)$_$;


ALTER FUNCTION public.multilinestringfromtext(text) OWNER TO postgres;

--
-- TOC entry 1703 (class 1255 OID 5901208)
-- Name: multilinestringfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION multilinestringfromtext(text, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT MLineFromText($1, $2)$_$;


ALTER FUNCTION public.multilinestringfromtext(text, integer) OWNER TO postgres;

--
-- TOC entry 1704 (class 1255 OID 5901209)
-- Name: multipointfromtext(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION multipointfromtext(text) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT MPointFromText($1)$_$;


ALTER FUNCTION public.multipointfromtext(text) OWNER TO postgres;

--
-- TOC entry 1705 (class 1255 OID 5901210)
-- Name: multipointfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION multipointfromtext(text, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT MPointFromText($1, $2)$_$;


ALTER FUNCTION public.multipointfromtext(text, integer) OWNER TO postgres;

--
-- TOC entry 1706 (class 1255 OID 5901211)
-- Name: multipointfromwkb(bytea); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION multipointfromwkb(bytea) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1)) = 'MULTIPOINT'
	THEN GeomFromWKB($1)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.multipointfromwkb(bytea) OWNER TO postgres;

--
-- TOC entry 1707 (class 1255 OID 5901212)
-- Name: multipointfromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION multipointfromwkb(bytea, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1,$2)) = 'MULTIPOINT'
	THEN GeomFromWKB($1, $2)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.multipointfromwkb(bytea, integer) OWNER TO postgres;

--
-- TOC entry 1708 (class 1255 OID 5901213)
-- Name: multipolyfromwkb(bytea); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION multipolyfromwkb(bytea) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1)) = 'MULTIPOLYGON'
	THEN GeomFromWKB($1)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.multipolyfromwkb(bytea) OWNER TO postgres;

--
-- TOC entry 1709 (class 1255 OID 5901214)
-- Name: multipolyfromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION multipolyfromwkb(bytea, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1, $2)) = 'MULTIPOLYGON'
	THEN GeomFromWKB($1, $2)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.multipolyfromwkb(bytea, integer) OWNER TO postgres;

--
-- TOC entry 1710 (class 1255 OID 5901215)
-- Name: multipolygonfromtext(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION multipolygonfromtext(text) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT MPolyFromText($1)$_$;


ALTER FUNCTION public.multipolygonfromtext(text) OWNER TO postgres;

--
-- TOC entry 1711 (class 1255 OID 5901216)
-- Name: multipolygonfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION multipolygonfromtext(text, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT MPolyFromText($1, $2)$_$;


ALTER FUNCTION public.multipolygonfromtext(text, integer) OWNER TO postgres;

--
-- TOC entry 1712 (class 1255 OID 5901217)
-- Name: pointfromtext(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION pointfromtext(text) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromText($1)) = 'POINT'
	THEN GeomFromText($1)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.pointfromtext(text) OWNER TO postgres;

--
-- TOC entry 1713 (class 1255 OID 5901218)
-- Name: pointfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION pointfromtext(text, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromText($1, $2)) = 'POINT'
	THEN GeomFromText($1,$2)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.pointfromtext(text, integer) OWNER TO postgres;

--
-- TOC entry 1714 (class 1255 OID 5901219)
-- Name: pointfromwkb(bytea); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION pointfromwkb(bytea) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1)) = 'POINT'
	THEN GeomFromWKB($1)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.pointfromwkb(bytea) OWNER TO postgres;

--
-- TOC entry 1715 (class 1255 OID 5901220)
-- Name: pointfromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION pointfromwkb(bytea, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(ST_GeomFromWKB($1, $2)) = 'POINT'
	THEN GeomFromWKB($1, $2)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.pointfromwkb(bytea, integer) OWNER TO postgres;

--
-- TOC entry 1716 (class 1255 OID 5901221)
-- Name: polyfromtext(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION polyfromtext(text) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromText($1)) = 'POLYGON'
	THEN GeomFromText($1)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.polyfromtext(text) OWNER TO postgres;

--
-- TOC entry 1717 (class 1255 OID 5901222)
-- Name: polyfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION polyfromtext(text, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromText($1, $2)) = 'POLYGON'
	THEN GeomFromText($1,$2)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.polyfromtext(text, integer) OWNER TO postgres;

--
-- TOC entry 1718 (class 1255 OID 5901223)
-- Name: polyfromwkb(bytea); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION polyfromwkb(bytea) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1)) = 'POLYGON'
	THEN GeomFromWKB($1)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.polyfromwkb(bytea) OWNER TO postgres;

--
-- TOC entry 1719 (class 1255 OID 5901224)
-- Name: polyfromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION polyfromwkb(bytea, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1, $2)) = 'POLYGON'
	THEN GeomFromWKB($1, $2)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.polyfromwkb(bytea, integer) OWNER TO postgres;

--
-- TOC entry 1720 (class 1255 OID 5901225)
-- Name: polygonfromtext(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION polygonfromtext(text) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT PolyFromText($1)$_$;


ALTER FUNCTION public.polygonfromtext(text) OWNER TO postgres;

--
-- TOC entry 1721 (class 1255 OID 5901226)
-- Name: polygonfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION polygonfromtext(text, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT PolyFromText($1, $2)$_$;


ALTER FUNCTION public.polygonfromtext(text, integer) OWNER TO postgres;

--
-- TOC entry 1722 (class 1255 OID 5901227)
-- Name: polygonfromwkb(bytea); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION polygonfromwkb(bytea) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1)) = 'POLYGON'
	THEN GeomFromWKB($1)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.polygonfromwkb(bytea) OWNER TO postgres;

--
-- TOC entry 1723 (class 1255 OID 5901228)
-- Name: polygonfromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION polygonfromwkb(bytea, integer) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1,$2)) = 'POLYGON'
	THEN GeomFromWKB($1, $2)
	ELSE NULL END
	$_$;


ALTER FUNCTION public.polygonfromwkb(bytea, integer) OWNER TO postgres;

--
-- TOC entry 1724 (class 1255 OID 5901229)
-- Name: populate_geometry_columns(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION populate_geometry_columns() RETURNS text
    LANGUAGE plpgsql
    AS $$
DECLARE
	inserted    integer;
	oldcount    integer;
	probed      integer;
	stale       integer;
	gcs         RECORD;
	gc          RECORD;
	gsrid       integer;
	gndims      integer;
	gtype       text;
	query       text;
	gc_is_valid boolean;

BEGIN
	SELECT count(*) INTO oldcount FROM geometry_columns;
	inserted := 0;

	EXECUTE 'TRUNCATE geometry_columns';

	-- Count the number of geometry columns in all tables and views
	SELECT count(DISTINCT c.oid) INTO probed
	FROM pg_class c,
		 pg_attribute a,
		 pg_type t,
		 pg_namespace n
	WHERE (c.relkind = 'r' OR c.relkind = 'v')
	AND t.typname = 'geometry'
	AND a.attisdropped = false
	AND a.atttypid = t.oid
	AND a.attrelid = c.oid
	AND c.relnamespace = n.oid
	AND n.nspname NOT ILIKE 'pg_temp%';

	-- Iterate through all non-dropped geometry columns
	RAISE DEBUG 'Processing Tables.....';

	FOR gcs IN
	SELECT DISTINCT ON (c.oid) c.oid, n.nspname, c.relname
		FROM pg_class c,
			 pg_attribute a,
			 pg_type t,
			 pg_namespace n
		WHERE c.relkind = 'r'
		AND t.typname = 'geometry'
		AND a.attisdropped = false
		AND a.atttypid = t.oid
		AND a.attrelid = c.oid
		AND c.relnamespace = n.oid
		AND n.nspname NOT ILIKE 'pg_temp%'
	LOOP

	inserted := inserted + populate_geometry_columns(gcs.oid);
	END LOOP;

	-- Add views to geometry columns table
	RAISE DEBUG 'Processing Views.....';
	FOR gcs IN
	SELECT DISTINCT ON (c.oid) c.oid, n.nspname, c.relname
		FROM pg_class c,
			 pg_attribute a,
			 pg_type t,
			 pg_namespace n
		WHERE c.relkind = 'v'
		AND t.typname = 'geometry'
		AND a.attisdropped = false
		AND a.atttypid = t.oid
		AND a.attrelid = c.oid
		AND c.relnamespace = n.oid
	LOOP

	inserted := inserted + populate_geometry_columns(gcs.oid);
	END LOOP;

	IF oldcount > inserted THEN
	stale = oldcount-inserted;
	ELSE
	stale = 0;
	END IF;

	RETURN 'probed:' ||probed|| ' inserted:'||inserted|| ' conflicts:'||probed-inserted|| ' deleted:'||stale;
END

$$;


ALTER FUNCTION public.populate_geometry_columns() OWNER TO postgres;

--
-- TOC entry 4819 (class 0 OID 0)
-- Dependencies: 1724
-- Name: FUNCTION populate_geometry_columns(); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION populate_geometry_columns() IS 'Ensures geometry columns have appropriate spatial constraints and exist in the geometry_columns table.';


--
-- TOC entry 1725 (class 1255 OID 5901230)
-- Name: populate_geometry_columns(oid); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION populate_geometry_columns(tbl_oid oid) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
	gcs         RECORD;
	gc          RECORD;
	gsrid       integer;
	gndims      integer;
	gtype       text;
	query       text;
	gc_is_valid boolean;
	inserted    integer;

BEGIN
	inserted := 0;

	-- Iterate through all geometry columns in this table
	FOR gcs IN
	SELECT n.nspname, c.relname, a.attname
		FROM pg_class c,
			 pg_attribute a,
			 pg_type t,
			 pg_namespace n
		WHERE c.relkind = 'r'
		AND t.typname = 'geometry'
		AND a.attisdropped = false
		AND a.atttypid = t.oid
		AND a.attrelid = c.oid
		AND c.relnamespace = n.oid
		AND n.nspname NOT ILIKE 'pg_temp%'
		AND c.oid = tbl_oid
	LOOP

	RAISE DEBUG 'Processing table %.%.%', gcs.nspname, gcs.relname, gcs.attname;

	DELETE FROM geometry_columns
	  WHERE f_table_schema = quote_ident(gcs.nspname)
	  AND f_table_name = quote_ident(gcs.relname)
	  AND f_geometry_column = quote_ident(gcs.attname);

	gc_is_valid := true;

	-- Try to find srid check from system tables (pg_constraint)
	gsrid :=
		(SELECT replace(replace(split_part(s.consrc, ' = ', 2), ')', ''), '(', '')
		 FROM pg_class c, pg_namespace n, pg_attribute a, pg_constraint s
		 WHERE n.nspname = gcs.nspname
		 AND c.relname = gcs.relname
		 AND a.attname = gcs.attname
		 AND a.attrelid = c.oid
		 AND s.connamespace = n.oid
		 AND s.conrelid = c.oid
		 AND a.attnum = ANY (s.conkey)
		 AND s.consrc LIKE '%srid(% = %');
	IF (gsrid IS NULL) THEN
		-- Try to find srid from the geometry itself
		EXECUTE 'SELECT srid(' || quote_ident(gcs.attname) || ')
				 FROM ONLY ' || quote_ident(gcs.nspname) || '.' || quote_ident(gcs.relname) || '
				 WHERE ' || quote_ident(gcs.attname) || ' IS NOT NULL LIMIT 1'
			INTO gc;
		gsrid := gc.srid;

		-- Try to apply srid check to column
		IF (gsrid IS NOT NULL) THEN
			BEGIN
				EXECUTE 'ALTER TABLE ONLY ' || quote_ident(gcs.nspname) || '.' || quote_ident(gcs.relname) || '
						 ADD CONSTRAINT ' || quote_ident('enforce_srid_' || gcs.attname) || '
						 CHECK (srid(' || quote_ident(gcs.attname) || ') = ' || gsrid || ')';
			EXCEPTION
				WHEN check_violation THEN
					RAISE WARNING 'Not inserting ''%'' in ''%.%'' into geometry_columns: could not apply constraint CHECK (srid(%) = %)', quote_ident(gcs.attname), quote_ident(gcs.nspname), quote_ident(gcs.relname), quote_ident(gcs.attname), gsrid;
					gc_is_valid := false;
			END;
		END IF;
	END IF;

	-- Try to find ndims check from system tables (pg_constraint)
	gndims :=
		(SELECT replace(split_part(s.consrc, ' = ', 2), ')', '')
		 FROM pg_class c, pg_namespace n, pg_attribute a, pg_constraint s
		 WHERE n.nspname = gcs.nspname
		 AND c.relname = gcs.relname
		 AND a.attname = gcs.attname
		 AND a.attrelid = c.oid
		 AND s.connamespace = n.oid
		 AND s.conrelid = c.oid
		 AND a.attnum = ANY (s.conkey)
		 AND s.consrc LIKE '%ndims(% = %');
	IF (gndims IS NULL) THEN
		-- Try to find ndims from the geometry itself
		EXECUTE 'SELECT ndims(' || quote_ident(gcs.attname) || ')
				 FROM ONLY ' || quote_ident(gcs.nspname) || '.' || quote_ident(gcs.relname) || '
				 WHERE ' || quote_ident(gcs.attname) || ' IS NOT NULL LIMIT 1'
			INTO gc;
		gndims := gc.ndims;

		-- Try to apply ndims check to column
		IF (gndims IS NOT NULL) THEN
			BEGIN
				EXECUTE 'ALTER TABLE ONLY ' || quote_ident(gcs.nspname) || '.' || quote_ident(gcs.relname) || '
						 ADD CONSTRAINT ' || quote_ident('enforce_dims_' || gcs.attname) || '
						 CHECK (ndims(' || quote_ident(gcs.attname) || ') = '||gndims||')';
			EXCEPTION
				WHEN check_violation THEN
					RAISE WARNING 'Not inserting ''%'' in ''%.%'' into geometry_columns: could not apply constraint CHECK (ndims(%) = %)', quote_ident(gcs.attname), quote_ident(gcs.nspname), quote_ident(gcs.relname), quote_ident(gcs.attname), gndims;
					gc_is_valid := false;
			END;
		END IF;
	END IF;

	-- Try to find geotype check from system tables (pg_constraint)
	gtype :=
		(SELECT replace(split_part(s.consrc, '''', 2), ')', '')
		 FROM pg_class c, pg_namespace n, pg_attribute a, pg_constraint s
		 WHERE n.nspname = gcs.nspname
		 AND c.relname = gcs.relname
		 AND a.attname = gcs.attname
		 AND a.attrelid = c.oid
		 AND s.connamespace = n.oid
		 AND s.conrelid = c.oid
		 AND a.attnum = ANY (s.conkey)
		 AND s.consrc LIKE '%geometrytype(% = %');
	IF (gtype IS NULL) THEN
		-- Try to find geotype from the geometry itself
		EXECUTE 'SELECT geometrytype(' || quote_ident(gcs.attname) || ')
				 FROM ONLY ' || quote_ident(gcs.nspname) || '.' || quote_ident(gcs.relname) || '
				 WHERE ' || quote_ident(gcs.attname) || ' IS NOT NULL LIMIT 1'
			INTO gc;
		gtype := gc.geometrytype;
		--IF (gtype IS NULL) THEN
		--    gtype := 'GEOMETRY';
		--END IF;

		-- Try to apply geometrytype check to column
		IF (gtype IS NOT NULL) THEN
			BEGIN
				EXECUTE 'ALTER TABLE ONLY ' || quote_ident(gcs.nspname) || '.' || quote_ident(gcs.relname) || '
				ADD CONSTRAINT ' || quote_ident('enforce_geotype_' || gcs.attname) || '
				CHECK ((geometrytype(' || quote_ident(gcs.attname) || ') = ' || quote_literal(gtype) || ') OR (' || quote_ident(gcs.attname) || ' IS NULL))';
			EXCEPTION
				WHEN check_violation THEN
					-- No geometry check can be applied. This column contains a number of geometry types.
					RAISE WARNING 'Could not add geometry type check (%) to table column: %.%.%', gtype, quote_ident(gcs.nspname),quote_ident(gcs.relname),quote_ident(gcs.attname);
			END;
		END IF;
	END IF;

	IF (gsrid IS NULL) THEN
		RAISE WARNING 'Not inserting ''%'' in ''%.%'' into geometry_columns: could not determine the srid', quote_ident(gcs.attname), quote_ident(gcs.nspname), quote_ident(gcs.relname);
	ELSIF (gndims IS NULL) THEN
		RAISE WARNING 'Not inserting ''%'' in ''%.%'' into geometry_columns: could not determine the number of dimensions', quote_ident(gcs.attname), quote_ident(gcs.nspname), quote_ident(gcs.relname);
	ELSIF (gtype IS NULL) THEN
		RAISE WARNING 'Not inserting ''%'' in ''%.%'' into geometry_columns: could not determine the geometry type', quote_ident(gcs.attname), quote_ident(gcs.nspname), quote_ident(gcs.relname);
	ELSE
		-- Only insert into geometry_columns if table constraints could be applied.
		IF (gc_is_valid) THEN
			INSERT INTO geometry_columns (f_table_catalog,f_table_schema, f_table_name, f_geometry_column, coord_dimension, srid, type)
			VALUES ('', gcs.nspname, gcs.relname, gcs.attname, gndims, gsrid, gtype);
			inserted := inserted + 1;
		END IF;
	END IF;
	END LOOP;

	-- Add views to geometry columns table
	FOR gcs IN
	SELECT n.nspname, c.relname, a.attname
		FROM pg_class c,
			 pg_attribute a,
			 pg_type t,
			 pg_namespace n
		WHERE c.relkind = 'v'
		AND t.typname = 'geometry'
		AND a.attisdropped = false
		AND a.atttypid = t.oid
		AND a.attrelid = c.oid
		AND c.relnamespace = n.oid
		AND n.nspname NOT ILIKE 'pg_temp%'
		AND c.oid = tbl_oid
	LOOP
		RAISE DEBUG 'Processing view %.%.%', gcs.nspname, gcs.relname, gcs.attname;

		EXECUTE 'SELECT ndims(' || quote_ident(gcs.attname) || ')
				 FROM ' || quote_ident(gcs.nspname) || '.' || quote_ident(gcs.relname) || '
				 WHERE ' || quote_ident(gcs.attname) || ' IS NOT NULL LIMIT 1'
			INTO gc;
		gndims := gc.ndims;

		EXECUTE 'SELECT srid(' || quote_ident(gcs.attname) || ')
				 FROM ' || quote_ident(gcs.nspname) || '.' || quote_ident(gcs.relname) || '
				 WHERE ' || quote_ident(gcs.attname) || ' IS NOT NULL LIMIT 1'
			INTO gc;
		gsrid := gc.srid;

		EXECUTE 'SELECT geometrytype(' || quote_ident(gcs.attname) || ')
				 FROM ' || quote_ident(gcs.nspname) || '.' || quote_ident(gcs.relname) || '
				 WHERE ' || quote_ident(gcs.attname) || ' IS NOT NULL LIMIT 1'
			INTO gc;
		gtype := gc.geometrytype;

		IF (gndims IS NULL) THEN
			RAISE WARNING 'Not inserting ''%'' in ''%.%'' into geometry_columns: could not determine ndims', quote_ident(gcs.attname), quote_ident(gcs.nspname), quote_ident(gcs.relname);
		ELSIF (gsrid IS NULL) THEN
			RAISE WARNING 'Not inserting ''%'' in ''%.%'' into geometry_columns: could not determine srid', quote_ident(gcs.attname), quote_ident(gcs.nspname), quote_ident(gcs.relname);
		ELSIF (gtype IS NULL) THEN
			RAISE WARNING 'Not inserting ''%'' in ''%.%'' into geometry_columns: could not determine gtype', quote_ident(gcs.attname), quote_ident(gcs.nspname), quote_ident(gcs.relname);
		ELSE
			query := 'INSERT INTO geometry_columns (f_table_catalog,f_table_schema, f_table_name, f_geometry_column, coord_dimension, srid, type) ' ||
					 'VALUES ('''', ' || quote_literal(gcs.nspname) || ',' || quote_literal(gcs.relname) || ',' || quote_literal(gcs.attname) || ',' || gndims || ',' || gsrid || ',' || quote_literal(gtype) || ')';
			EXECUTE query;
			inserted := inserted + 1;
		END IF;
	END LOOP;

	RETURN inserted;
END

$$;


ALTER FUNCTION public.populate_geometry_columns(tbl_oid oid) OWNER TO postgres;

--
-- TOC entry 4820 (class 0 OID 0)
-- Dependencies: 1725
-- Name: FUNCTION populate_geometry_columns(tbl_oid oid); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION populate_geometry_columns(tbl_oid oid) IS 'args: relation_oid - Ensures geometry columns have appropriate spatial constraints and exist in the geometry_columns table.';


--
-- TOC entry 1726 (class 1255 OID 5901233)
-- Name: probe_geometry_columns(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION probe_geometry_columns() RETURNS text
    LANGUAGE plpgsql
    AS $$
DECLARE
	inserted integer;
	oldcount integer;
	probed integer;
	stale integer;
BEGIN




	RETURN 'This function is obsolete now that geometry_columns is a view';
END

$$;


ALTER FUNCTION public.probe_geometry_columns() OWNER TO postgres;

--
-- TOC entry 4821 (class 0 OID 0)
-- Dependencies: 1726
-- Name: FUNCTION probe_geometry_columns(); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION probe_geometry_columns() IS 'Scans all tables with PostGIS geometry constraints and adds them to the geometry_columns table if they are not there.';


--
-- TOC entry 1727 (class 1255 OID 5901234)
-- Name: rename_geometry_table_constraints(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION rename_geometry_table_constraints() RETURNS text
    LANGUAGE sql IMMUTABLE
    AS $$
SELECT 'rename_geometry_table_constraint() is obsoleted'::text
$$;


ALTER FUNCTION public.rename_geometry_table_constraints() OWNER TO postgres;

--
-- TOC entry 1728 (class 1255 OID 5901235)
-- Name: rotate(geometry, double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION rotate(geometry, double precision) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT st_rotateZ($1, $2)$_$;


ALTER FUNCTION public.rotate(geometry, double precision) OWNER TO postgres;

--
-- TOC entry 1729 (class 1255 OID 5901236)
-- Name: rotatex(geometry, double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION rotatex(geometry, double precision) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT st_affine($1, 1, 0, 0, 0, cos($2), -sin($2), 0, sin($2), cos($2), 0, 0, 0)$_$;


ALTER FUNCTION public.rotatex(geometry, double precision) OWNER TO postgres;

--
-- TOC entry 1730 (class 1255 OID 5901237)
-- Name: rotatey(geometry, double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION rotatey(geometry, double precision) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT st_affine($1,  cos($2), 0, sin($2),  0, 1, 0,  -sin($2), 0, cos($2), 0,  0, 0)$_$;


ALTER FUNCTION public.rotatey(geometry, double precision) OWNER TO postgres;

--
-- TOC entry 1731 (class 1255 OID 5901238)
-- Name: rotatez(geometry, double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION rotatez(geometry, double precision) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT st_affine($1,  cos($2), -sin($2), 0,  sin($2), cos($2), 0,  0, 0, 1,  0, 0, 0)$_$;


ALTER FUNCTION public.rotatez(geometry, double precision) OWNER TO postgres;

--
-- TOC entry 1732 (class 1255 OID 5901239)
-- Name: scale(geometry, double precision, double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION scale(geometry, double precision, double precision) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT st_scale($1, $2, $3, 1)$_$;


ALTER FUNCTION public.scale(geometry, double precision, double precision) OWNER TO postgres;

--
-- TOC entry 1733 (class 1255 OID 5901240)
-- Name: scale(geometry, double precision, double precision, double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION scale(geometry, double precision, double precision, double precision) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT st_affine($1,  $2, 0, 0,  0, $3, 0,  0, 0, $4,  0, 0, 0)$_$;


ALTER FUNCTION public.scale(geometry, double precision, double precision, double precision) OWNER TO postgres;

--
-- TOC entry 1734 (class 1255 OID 5901241)
-- Name: se_envelopesintersect(geometry, geometry); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION se_envelopesintersect(geometry, geometry) RETURNS boolean
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$ 
	SELECT $1 && $2
	$_$;


ALTER FUNCTION public.se_envelopesintersect(geometry, geometry) OWNER TO postgres;

--
-- TOC entry 1735 (class 1255 OID 5901242)
-- Name: se_locatealong(geometry, double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION se_locatealong(geometry, double precision) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$ SELECT SE_LocateBetween($1, $2, $2) $_$;


ALTER FUNCTION public.se_locatealong(geometry, double precision) OWNER TO postgres;

--
-- TOC entry 1736 (class 1255 OID 5901243)
-- Name: snaptogrid(geometry, double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION snaptogrid(geometry, double precision) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT ST_SnapToGrid($1, 0, 0, $2, $2)$_$;


ALTER FUNCTION public.snaptogrid(geometry, double precision) OWNER TO postgres;

--
-- TOC entry 1737 (class 1255 OID 5901244)
-- Name: snaptogrid(geometry, double precision, double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION snaptogrid(geometry, double precision, double precision) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT ST_SnapToGrid($1, 0, 0, $2, $3)$_$;


ALTER FUNCTION public.snaptogrid(geometry, double precision, double precision) OWNER TO postgres;

--
-- TOC entry 1738 (class 1255 OID 5901245)
-- Name: st_area(geography); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_area(geography) RETURNS double precision
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT ST_Area($1, true)$_$;


ALTER FUNCTION public.st_area(geography) OWNER TO postgres;

--
-- TOC entry 4822 (class 0 OID 0)
-- Dependencies: 1738
-- Name: FUNCTION st_area(geography); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_area(geography) IS 'args: g1 - Returns the area of the surface if it is a polygon or multi-polygon. For "geometry" type area is in SRID units. For "geography" area is in square meters.';


--
-- TOC entry 1739 (class 1255 OID 5901246)
-- Name: st_asbinary(text); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_asbinary(text) RETURNS bytea
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$ SELECT ST_AsBinary($1::geometry);$_$;


ALTER FUNCTION public.st_asbinary(text) OWNER TO postgres;

--
-- TOC entry 1740 (class 1255 OID 5901247)
-- Name: st_asgeojson(geography); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_asgeojson(geography) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsGeoJson(1, $1, 15, 0)$_$;


ALTER FUNCTION public.st_asgeojson(geography) OWNER TO postgres;

--
-- TOC entry 4823 (class 0 OID 0)
-- Dependencies: 1740
-- Name: FUNCTION st_asgeojson(geography); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_asgeojson(geography) IS 'args: g1 - Return the geometry as a GeoJSON element.';


--
-- TOC entry 1741 (class 1255 OID 5901248)
-- Name: st_asgeojson(geometry); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_asgeojson(geometry) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsGeoJson(1, $1, 15, 0)$_$;


ALTER FUNCTION public.st_asgeojson(geometry) OWNER TO postgres;

--
-- TOC entry 4824 (class 0 OID 0)
-- Dependencies: 1741
-- Name: FUNCTION st_asgeojson(geometry); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_asgeojson(geometry) IS 'args: g1 - Return the geometry as a GeoJSON element.';


--
-- TOC entry 1742 (class 1255 OID 5901249)
-- Name: st_asgeojson(integer, geography); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_asgeojson(integer, geography) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsGeoJson($1, $2, 15, 0)$_$;


ALTER FUNCTION public.st_asgeojson(integer, geography) OWNER TO postgres;

--
-- TOC entry 4825 (class 0 OID 0)
-- Dependencies: 1742
-- Name: FUNCTION st_asgeojson(integer, geography); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_asgeojson(integer, geography) IS 'args: version, g1 - Return the geometry as a GeoJSON element.';


--
-- TOC entry 1743 (class 1255 OID 5901250)
-- Name: st_asgeojson(integer, geometry); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_asgeojson(integer, geometry) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsGeoJson($1, $2, 15, 0)$_$;


ALTER FUNCTION public.st_asgeojson(integer, geometry) OWNER TO postgres;

--
-- TOC entry 4826 (class 0 OID 0)
-- Dependencies: 1743
-- Name: FUNCTION st_asgeojson(integer, geometry); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_asgeojson(integer, geometry) IS 'args: version, g1 - Return the geometry as a GeoJSON element.';


--
-- TOC entry 1744 (class 1255 OID 5901251)
-- Name: st_asgeojson(geography, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_asgeojson(geography, integer) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsGeoJson(1, $1, $2, 0)$_$;


ALTER FUNCTION public.st_asgeojson(geography, integer) OWNER TO postgres;

--
-- TOC entry 4827 (class 0 OID 0)
-- Dependencies: 1744
-- Name: FUNCTION st_asgeojson(geography, integer); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_asgeojson(geography, integer) IS 'args: g1, max_decimal_digits - Return the geometry as a GeoJSON element.';


--
-- TOC entry 1745 (class 1255 OID 5901252)
-- Name: st_asgeojson(geometry, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_asgeojson(geometry, integer) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsGeoJson(1, $1, $2, 0)$_$;


ALTER FUNCTION public.st_asgeojson(geometry, integer) OWNER TO postgres;

--
-- TOC entry 4828 (class 0 OID 0)
-- Dependencies: 1745
-- Name: FUNCTION st_asgeojson(geometry, integer); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_asgeojson(geometry, integer) IS 'args: g1, max_decimal_digits - Return the geometry as a GeoJSON element.';


--
-- TOC entry 1746 (class 1255 OID 5901253)
-- Name: st_asgeojson(integer, geography, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_asgeojson(integer, geography, integer) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsGeoJson($1, $2, $3, 0)$_$;


ALTER FUNCTION public.st_asgeojson(integer, geography, integer) OWNER TO postgres;

--
-- TOC entry 4829 (class 0 OID 0)
-- Dependencies: 1746
-- Name: FUNCTION st_asgeojson(integer, geography, integer); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_asgeojson(integer, geography, integer) IS 'args: version, g1, max_decimal_digits - Return the geometry as a GeoJSON element.';


--
-- TOC entry 1747 (class 1255 OID 5901254)
-- Name: st_asgeojson(integer, geometry, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_asgeojson(integer, geometry, integer) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsGeoJson($1, $2, $3, 0)$_$;


ALTER FUNCTION public.st_asgeojson(integer, geometry, integer) OWNER TO postgres;

--
-- TOC entry 4830 (class 0 OID 0)
-- Dependencies: 1747
-- Name: FUNCTION st_asgeojson(integer, geometry, integer); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_asgeojson(integer, geometry, integer) IS 'args: version, g1, max_decimal_digits - Return the geometry as a GeoJSON element.';


--
-- TOC entry 1748 (class 1255 OID 5901255)
-- Name: st_asgml(geography); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_asgml(geography) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsGML(2, $1, 15, 0)$_$;


ALTER FUNCTION public.st_asgml(geography) OWNER TO postgres;

--
-- TOC entry 4831 (class 0 OID 0)
-- Dependencies: 1748
-- Name: FUNCTION st_asgml(geography); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_asgml(geography) IS 'args: g1 - Return the geometry as a GML version 2 or 3 element.';


--
-- TOC entry 1749 (class 1255 OID 5901256)
-- Name: st_asgml(geometry); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_asgml(geometry) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsGML(2, $1, 15, 0)$_$;


ALTER FUNCTION public.st_asgml(geometry) OWNER TO postgres;

--
-- TOC entry 4832 (class 0 OID 0)
-- Dependencies: 1749
-- Name: FUNCTION st_asgml(geometry); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_asgml(geometry) IS 'args: g1 - Return the geometry as a GML version 2 or 3 element.';


--
-- TOC entry 1750 (class 1255 OID 5901257)
-- Name: st_asgml(integer, geography); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_asgml(integer, geography) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsGML($1, $2, 15, 0)$_$;


ALTER FUNCTION public.st_asgml(integer, geography) OWNER TO postgres;

--
-- TOC entry 4833 (class 0 OID 0)
-- Dependencies: 1750
-- Name: FUNCTION st_asgml(integer, geography); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_asgml(integer, geography) IS 'args: version, g1 - Return the geometry as a GML version 2 or 3 element.';


--
-- TOC entry 1751 (class 1255 OID 5901258)
-- Name: st_asgml(integer, geometry); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_asgml(integer, geometry) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsGML($1, $2, 15, 0)$_$;


ALTER FUNCTION public.st_asgml(integer, geometry) OWNER TO postgres;

--
-- TOC entry 4834 (class 0 OID 0)
-- Dependencies: 1751
-- Name: FUNCTION st_asgml(integer, geometry); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_asgml(integer, geometry) IS 'args: version, g1 - Return the geometry as a GML version 2 or 3 element.';


--
-- TOC entry 1752 (class 1255 OID 5901259)
-- Name: st_asgml(geography, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_asgml(geography, integer) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsGML(2, $1, $2, 0)$_$;


ALTER FUNCTION public.st_asgml(geography, integer) OWNER TO postgres;

--
-- TOC entry 4835 (class 0 OID 0)
-- Dependencies: 1752
-- Name: FUNCTION st_asgml(geography, integer); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_asgml(geography, integer) IS 'args: g1, precision - Return the geometry as a GML version 2 or 3 element.';


--
-- TOC entry 1753 (class 1255 OID 5901260)
-- Name: st_asgml(geometry, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_asgml(geometry, integer) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsGML(2, $1, $2, 0)$_$;


ALTER FUNCTION public.st_asgml(geometry, integer) OWNER TO postgres;

--
-- TOC entry 4836 (class 0 OID 0)
-- Dependencies: 1753
-- Name: FUNCTION st_asgml(geometry, integer); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_asgml(geometry, integer) IS 'args: g1, precision - Return the geometry as a GML version 2 or 3 element.';


--
-- TOC entry 1754 (class 1255 OID 5901261)
-- Name: st_asgml(integer, geography, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_asgml(integer, geography, integer) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsGML($1, $2, $3, 0)$_$;


ALTER FUNCTION public.st_asgml(integer, geography, integer) OWNER TO postgres;

--
-- TOC entry 4837 (class 0 OID 0)
-- Dependencies: 1754
-- Name: FUNCTION st_asgml(integer, geography, integer); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_asgml(integer, geography, integer) IS 'args: version, g1, precision - Return the geometry as a GML version 2 or 3 element.';


--
-- TOC entry 1755 (class 1255 OID 5901262)
-- Name: st_asgml(integer, geometry, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_asgml(integer, geometry, integer) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsGML($1, $2, $3, 0)$_$;


ALTER FUNCTION public.st_asgml(integer, geometry, integer) OWNER TO postgres;

--
-- TOC entry 4838 (class 0 OID 0)
-- Dependencies: 1755
-- Name: FUNCTION st_asgml(integer, geometry, integer); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_asgml(integer, geometry, integer) IS 'args: version, g1, precision - Return the geometry as a GML version 2 or 3 element.';


--
-- TOC entry 1756 (class 1255 OID 5901263)
-- Name: st_asgml(integer, geography, integer, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_asgml(integer, geography, integer, integer) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsGML($1, $2, $3, $4)$_$;


ALTER FUNCTION public.st_asgml(integer, geography, integer, integer) OWNER TO postgres;

--
-- TOC entry 4839 (class 0 OID 0)
-- Dependencies: 1756
-- Name: FUNCTION st_asgml(integer, geography, integer, integer); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_asgml(integer, geography, integer, integer) IS 'args: version, g1, precision, options - Return the geometry as a GML version 2 or 3 element.';


--
-- TOC entry 1757 (class 1255 OID 5901264)
-- Name: st_asgml(integer, geometry, integer, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_asgml(integer, geometry, integer, integer) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsGML($1, $2, $3, $4)$_$;


ALTER FUNCTION public.st_asgml(integer, geometry, integer, integer) OWNER TO postgres;

--
-- TOC entry 4840 (class 0 OID 0)
-- Dependencies: 1757
-- Name: FUNCTION st_asgml(integer, geometry, integer, integer); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_asgml(integer, geometry, integer, integer) IS 'args: version, g1, precision, options - Return the geometry as a GML version 2 or 3 element.';


--
-- TOC entry 1758 (class 1255 OID 5901265)
-- Name: st_askml(geography); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_askml(geography) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsKML(2, $1, 15)$_$;


ALTER FUNCTION public.st_askml(geography) OWNER TO postgres;

--
-- TOC entry 4841 (class 0 OID 0)
-- Dependencies: 1758
-- Name: FUNCTION st_askml(geography); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_askml(geography) IS 'args: g1 - Return the geometry as a KML element. Several variants. Default version=2, default precision=15';


--
-- TOC entry 1759 (class 1255 OID 5901266)
-- Name: st_askml(geometry); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_askml(geometry) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsKML(2, ST_Transform($1,4326), 15)$_$;


ALTER FUNCTION public.st_askml(geometry) OWNER TO postgres;

--
-- TOC entry 4842 (class 0 OID 0)
-- Dependencies: 1759
-- Name: FUNCTION st_askml(geometry); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_askml(geometry) IS 'args: g1 - Return the geometry as a KML element. Several variants. Default version=2, default precision=15';


--
-- TOC entry 1760 (class 1255 OID 5901267)
-- Name: st_askml(integer, geography); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_askml(integer, geography) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsKML($1, $2, 15)$_$;


ALTER FUNCTION public.st_askml(integer, geography) OWNER TO postgres;

--
-- TOC entry 4843 (class 0 OID 0)
-- Dependencies: 1760
-- Name: FUNCTION st_askml(integer, geography); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_askml(integer, geography) IS 'args: version, geom1 - Return the geometry as a KML element. Several variants. Default version=2, default precision=15';


--
-- TOC entry 1761 (class 1255 OID 5901268)
-- Name: st_askml(integer, geometry); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_askml(integer, geometry) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsKML($1, ST_Transform($2,4326), 15)$_$;


ALTER FUNCTION public.st_askml(integer, geometry) OWNER TO postgres;

--
-- TOC entry 4844 (class 0 OID 0)
-- Dependencies: 1761
-- Name: FUNCTION st_askml(integer, geometry); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_askml(integer, geometry) IS 'args: version, geom1 - Return the geometry as a KML element. Several variants. Default version=2, default precision=15';


--
-- TOC entry 1762 (class 1255 OID 5901269)
-- Name: st_askml(integer, geography, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_askml(integer, geography, integer) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsKML($1, $2, $3)$_$;


ALTER FUNCTION public.st_askml(integer, geography, integer) OWNER TO postgres;

--
-- TOC entry 4845 (class 0 OID 0)
-- Dependencies: 1762
-- Name: FUNCTION st_askml(integer, geography, integer); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_askml(integer, geography, integer) IS 'args: version, geom1, precision - Return the geometry as a KML element. Several variants. Default version=2, default precision=15';


--
-- TOC entry 1763 (class 1255 OID 5901270)
-- Name: st_askml(integer, geometry, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_askml(integer, geometry, integer) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT _ST_AsKML($1, ST_Transform($2,4326), $3)$_$;


ALTER FUNCTION public.st_askml(integer, geometry, integer) OWNER TO postgres;

--
-- TOC entry 4846 (class 0 OID 0)
-- Dependencies: 1763
-- Name: FUNCTION st_askml(integer, geometry, integer); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_askml(integer, geometry, integer) IS 'args: version, geom1, precision - Return the geometry as a KML element. Several variants. Default version=2, default precision=15';


--
-- TOC entry 1764 (class 1255 OID 5901271)
-- Name: st_astext(bytea); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_astext(bytea) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$ SELECT ST_AsText($1::geometry);$_$;


ALTER FUNCTION public.st_astext(bytea) OWNER TO postgres;

--
-- TOC entry 1765 (class 1255 OID 5901272)
-- Name: st_geohash(geometry); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_geohash(geometry) RETURNS text
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT ST_GeoHash($1, 0)$_$;


ALTER FUNCTION public.st_geohash(geometry) OWNER TO postgres;

--
-- TOC entry 4847 (class 0 OID 0)
-- Dependencies: 1765
-- Name: FUNCTION st_geohash(geometry); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_geohash(geometry) IS 'args: g1 - Return a GeoHash representation (geohash.org) of the geometry.';


--
-- TOC entry 1766 (class 1255 OID 5901273)
-- Name: st_length(geography); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_length(geography) RETURNS double precision
    LANGUAGE sql IMMUTABLE
    AS $_$SELECT ST_Length($1, true)$_$;


ALTER FUNCTION public.st_length(geography) OWNER TO postgres;

--
-- TOC entry 4848 (class 0 OID 0)
-- Dependencies: 1766
-- Name: FUNCTION st_length(geography); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_length(geography) IS 'args: gg - Returns the 2d length of the geometry if it is a linestring or multilinestring. geometry are in units of spatial reference and geography are in meters (default spheroid)';


--
-- TOC entry 1767 (class 1255 OID 5901274)
-- Name: st_minimumboundingcircle(geometry); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION st_minimumboundingcircle(geometry) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT ST_MinimumBoundingCircle($1, 48)$_$;


ALTER FUNCTION public.st_minimumboundingcircle(geometry) OWNER TO postgres;

--
-- TOC entry 4849 (class 0 OID 0)
-- Dependencies: 1767
-- Name: FUNCTION st_minimumboundingcircle(geometry); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON FUNCTION st_minimumboundingcircle(geometry) IS 'args: geomA - Returns the smallest circle polygon that can fully contain a geometry. Default uses 48 segments per quarter circle.';


--
-- TOC entry 1768 (class 1255 OID 5901275)
-- Name: translate(geometry, double precision, double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION translate(geometry, double precision, double precision) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT st_translate($1, $2, $3, 0)$_$;


ALTER FUNCTION public.translate(geometry, double precision, double precision) OWNER TO postgres;

--
-- TOC entry 1769 (class 1255 OID 5901276)
-- Name: translate(geometry, double precision, double precision, double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION translate(geometry, double precision, double precision, double precision) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT st_affine($1, 1, 0, 0, 0, 1, 0, 0, 0, 1, $2, $3, $4)$_$;


ALTER FUNCTION public.translate(geometry, double precision, double precision, double precision) OWNER TO postgres;

--
-- TOC entry 1770 (class 1255 OID 5901277)
-- Name: transscale(geometry, double precision, double precision, double precision, double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION transscale(geometry, double precision, double precision, double precision, double precision) RETURNS geometry
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT st_affine($1,  $4, 0, 0,  0, $5, 0,
		0, 0, 1,  $2 * $4, $3 * $5, 0)$_$;


ALTER FUNCTION public.transscale(geometry, double precision, double precision, double precision, double precision) OWNER TO postgres;

--
-- TOC entry 1771 (class 1255 OID 5901278)
-- Name: update_lastchanged_column(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION update_lastchanged_column() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
   NEW.lastchanged = now(); 
   RETURN NEW;
END;
$$;


ALTER FUNCTION public.update_lastchanged_column() OWNER TO postgres;

--
-- TOC entry 1772 (class 1255 OID 5901279)
-- Name: update_the_geom(); Type: FUNCTION; Schema: public; Owner: auikadmin
--

CREATE FUNCTION update_the_geom() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
begin
	new.the_geom = st_setsrid(st_makepoint(new.e32,new.n32),25832) ;
	return new ;
end ;

$$;


ALTER FUNCTION public.update_the_geom() OWNER TO auikadmin;

--
-- TOC entry 1773 (class 1255 OID 5901280)
-- Name: within(geometry, geometry); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION within(geometry, geometry) RETURNS boolean
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$SELECT ST_Within($1, $2)$_$;


ALTER FUNCTION public.within(geometry, geometry) OWNER TO postgres;

--
-- TOC entry 2677 (class 1255 OID 5901281)
-- Name: accum(geometry); Type: AGGREGATE; Schema: public; Owner: postgres
--

CREATE AGGREGATE accum(geometry) (
    SFUNC = public.pgis_geometry_accum_transfn,
    STYPE = pgis_abs,
    FINALFUNC = pgis_geometry_accum_finalfn
);


ALTER AGGREGATE public.accum(geometry) OWNER TO postgres;

--
-- TOC entry 2678 (class 1255 OID 5901282)
-- Name: collect(geometry); Type: AGGREGATE; Schema: public; Owner: postgres
--

CREATE AGGREGATE collect(geometry) (
    SFUNC = public.pgis_geometry_accum_transfn,
    STYPE = pgis_abs,
    FINALFUNC = pgis_geometry_collect_finalfn
);


ALTER AGGREGATE public.collect(geometry) OWNER TO postgres;

--
-- TOC entry 2679 (class 1255 OID 5901283)
-- Name: extent(geometry); Type: AGGREGATE; Schema: public; Owner: postgres
--

CREATE AGGREGATE extent(geometry) (
    SFUNC = public.st_combine_bbox,
    STYPE = box3d,
    FINALFUNC = public.box2d
);


ALTER AGGREGATE public.extent(geometry) OWNER TO postgres;

--
-- TOC entry 2680 (class 1255 OID 5901284)
-- Name: makeline(geometry); Type: AGGREGATE; Schema: public; Owner: postgres
--

CREATE AGGREGATE makeline(geometry) (
    SFUNC = public.pgis_geometry_accum_transfn,
    STYPE = pgis_abs,
    FINALFUNC = pgis_geometry_makeline_finalfn
);


ALTER AGGREGATE public.makeline(geometry) OWNER TO postgres;

--
-- TOC entry 2681 (class 1255 OID 5901285)
-- Name: memcollect(geometry); Type: AGGREGATE; Schema: public; Owner: postgres
--

CREATE AGGREGATE memcollect(geometry) (
    SFUNC = public.st_collect,
    STYPE = geometry
);


ALTER AGGREGATE public.memcollect(geometry) OWNER TO postgres;

--
-- TOC entry 2682 (class 1255 OID 5901286)
-- Name: polygonize(geometry); Type: AGGREGATE; Schema: public; Owner: postgres
--

CREATE AGGREGATE polygonize(geometry) (
    SFUNC = public.pgis_geometry_accum_transfn,
    STYPE = pgis_abs,
    FINALFUNC = pgis_geometry_polygonize_finalfn
);


ALTER AGGREGATE public.polygonize(geometry) OWNER TO postgres;

--
-- TOC entry 2683 (class 1255 OID 5901287)
-- Name: st_extent3d(geometry); Type: AGGREGATE; Schema: public; Owner: postgres
--

CREATE AGGREGATE st_extent3d(geometry) (
    SFUNC = public.st_combine_bbox,
    STYPE = box3d
);


ALTER AGGREGATE public.st_extent3d(geometry) OWNER TO postgres;

--
-- TOC entry 4850 (class 0 OID 0)
-- Dependencies: 2683
-- Name: AGGREGATE st_extent3d(geometry); Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON AGGREGATE st_extent3d(geometry) IS 'args: geomfield - an aggregate function that returns the box3D bounding box that bounds rows of geometries.';


--
-- TOC entry 3745 (class 2753 OID 5901288)
-- Name: gist_bit_ops; Type: OPERATOR FAMILY; Schema: public; Owner: auikadmin
--

CREATE OPERATOR FAMILY gist_bit_ops USING gist;


ALTER OPERATOR FAMILY public.gist_bit_ops USING gist OWNER TO auikadmin;

--
-- TOC entry 3631 (class 2616 OID 5901289)
-- Name: gist_bit_ops; Type: OPERATOR CLASS; Schema: public; Owner: postgres
--

CREATE OPERATOR CLASS gist_bit_ops
    DEFAULT FOR TYPE bit USING gist AS
    STORAGE gbtreekey_var ,
    OPERATOR 1 <(bit,bit) ,
    OPERATOR 2 <=(bit,bit) ,
    OPERATOR 3 =(bit,bit) ,
    OPERATOR 4 >=(bit,bit) ,
    OPERATOR 5 >(bit,bit) ,
    FUNCTION 1 (bit, bit) gbt_bit_consistent(internal,bit,smallint) ,
    FUNCTION 2 (bit, bit) gbt_bit_union(bytea,internal) ,
    FUNCTION 3 (bit, bit) gbt_bit_compress(internal) ,
    FUNCTION 4 (bit, bit) gbt_var_decompress(internal) ,
    FUNCTION 5 (bit, bit) gbt_bit_penalty(internal,internal,internal) ,
    FUNCTION 6 (bit, bit) gbt_bit_picksplit(internal,internal) ,
    FUNCTION 7 (bit, bit) gbt_bit_same(internal,internal,internal);


ALTER OPERATOR CLASS public.gist_bit_ops USING gist OWNER TO postgres;

--
-- TOC entry 3746 (class 2753 OID 5901302)
-- Name: gist_bpchar_ops; Type: OPERATOR FAMILY; Schema: public; Owner: auikadmin
--

CREATE OPERATOR FAMILY gist_bpchar_ops USING gist;


ALTER OPERATOR FAMILY public.gist_bpchar_ops USING gist OWNER TO auikadmin;

--
-- TOC entry 3632 (class 2616 OID 5901303)
-- Name: gist_bpchar_ops; Type: OPERATOR CLASS; Schema: public; Owner: postgres
--

CREATE OPERATOR CLASS gist_bpchar_ops
    DEFAULT FOR TYPE character USING gist AS
    STORAGE gbtreekey_var ,
    OPERATOR 1 <(character,character) ,
    OPERATOR 2 <=(character,character) ,
    OPERATOR 3 =(character,character) ,
    OPERATOR 4 >=(character,character) ,
    OPERATOR 5 >(character,character) ,
    FUNCTION 1 (character, character) gbt_bpchar_consistent(internal,character,smallint) ,
    FUNCTION 2 (character, character) gbt_text_union(bytea,internal) ,
    FUNCTION 3 (character, character) gbt_bpchar_compress(internal) ,
    FUNCTION 4 (character, character) gbt_var_decompress(internal) ,
    FUNCTION 5 (character, character) gbt_text_penalty(internal,internal,internal) ,
    FUNCTION 6 (character, character) gbt_text_picksplit(internal,internal) ,
    FUNCTION 7 (character, character) gbt_text_same(internal,internal,internal);


ALTER OPERATOR CLASS public.gist_bpchar_ops USING gist OWNER TO postgres;

--
-- TOC entry 3747 (class 2753 OID 5901316)
-- Name: gist_bytea_ops; Type: OPERATOR FAMILY; Schema: public; Owner: auikadmin
--

CREATE OPERATOR FAMILY gist_bytea_ops USING gist;


ALTER OPERATOR FAMILY public.gist_bytea_ops USING gist OWNER TO auikadmin;

--
-- TOC entry 3633 (class 2616 OID 5901317)
-- Name: gist_bytea_ops; Type: OPERATOR CLASS; Schema: public; Owner: postgres
--

CREATE OPERATOR CLASS gist_bytea_ops
    DEFAULT FOR TYPE bytea USING gist AS
    STORAGE gbtreekey_var ,
    OPERATOR 1 <(bytea,bytea) ,
    OPERATOR 2 <=(bytea,bytea) ,
    OPERATOR 3 =(bytea,bytea) ,
    OPERATOR 4 >=(bytea,bytea) ,
    OPERATOR 5 >(bytea,bytea) ,
    FUNCTION 1 (bytea, bytea) gbt_bytea_consistent(internal,bytea,smallint) ,
    FUNCTION 2 (bytea, bytea) gbt_bytea_union(bytea,internal) ,
    FUNCTION 3 (bytea, bytea) gbt_bytea_compress(internal) ,
    FUNCTION 4 (bytea, bytea) gbt_var_decompress(internal) ,
    FUNCTION 5 (bytea, bytea) gbt_bytea_penalty(internal,internal,internal) ,
    FUNCTION 6 (bytea, bytea) gbt_bytea_picksplit(internal,internal) ,
    FUNCTION 7 (bytea, bytea) gbt_bytea_same(internal,internal,internal);


ALTER OPERATOR CLASS public.gist_bytea_ops USING gist OWNER TO postgres;

--
-- TOC entry 3748 (class 2753 OID 5901330)
-- Name: gist_cash_ops; Type: OPERATOR FAMILY; Schema: public; Owner: auikadmin
--

CREATE OPERATOR FAMILY gist_cash_ops USING gist;


ALTER OPERATOR FAMILY public.gist_cash_ops USING gist OWNER TO auikadmin;

--
-- TOC entry 3634 (class 2616 OID 5901331)
-- Name: gist_cash_ops; Type: OPERATOR CLASS; Schema: public; Owner: postgres
--

CREATE OPERATOR CLASS gist_cash_ops
    DEFAULT FOR TYPE money USING gist AS
    STORAGE gbtreekey16 ,
    OPERATOR 1 <(money,money) ,
    OPERATOR 2 <=(money,money) ,
    OPERATOR 3 =(money,money) ,
    OPERATOR 4 >=(money,money) ,
    OPERATOR 5 >(money,money) ,
    FUNCTION 1 (money, money) gbt_cash_consistent(internal,money,smallint) ,
    FUNCTION 2 (money, money) gbt_cash_union(bytea,internal) ,
    FUNCTION 3 (money, money) gbt_cash_compress(internal) ,
    FUNCTION 4 (money, money) gbt_decompress(internal) ,
    FUNCTION 5 (money, money) gbt_cash_penalty(internal,internal,internal) ,
    FUNCTION 6 (money, money) gbt_cash_picksplit(internal,internal) ,
    FUNCTION 7 (money, money) gbt_cash_same(internal,internal,internal);


ALTER OPERATOR CLASS public.gist_cash_ops USING gist OWNER TO postgres;

--
-- TOC entry 3749 (class 2753 OID 5901344)
-- Name: gist_cidr_ops; Type: OPERATOR FAMILY; Schema: public; Owner: auikadmin
--

CREATE OPERATOR FAMILY gist_cidr_ops USING gist;


ALTER OPERATOR FAMILY public.gist_cidr_ops USING gist OWNER TO auikadmin;

--
-- TOC entry 3635 (class 2616 OID 5901345)
-- Name: gist_cidr_ops; Type: OPERATOR CLASS; Schema: public; Owner: postgres
--

CREATE OPERATOR CLASS gist_cidr_ops
    DEFAULT FOR TYPE cidr USING gist AS
    STORAGE gbtreekey16 ,
    OPERATOR 1 <(inet,inet) ,
    OPERATOR 2 <=(inet,inet) ,
    OPERATOR 3 =(inet,inet) ,
    OPERATOR 4 >=(inet,inet) ,
    OPERATOR 5 >(inet,inet) ,
    FUNCTION 1 (cidr, cidr) gbt_inet_consistent(internal,inet,smallint) ,
    FUNCTION 2 (cidr, cidr) gbt_inet_union(bytea,internal) ,
    FUNCTION 3 (cidr, cidr) gbt_inet_compress(internal) ,
    FUNCTION 4 (cidr, cidr) gbt_decompress(internal) ,
    FUNCTION 5 (cidr, cidr) gbt_inet_penalty(internal,internal,internal) ,
    FUNCTION 6 (cidr, cidr) gbt_inet_picksplit(internal,internal) ,
    FUNCTION 7 (cidr, cidr) gbt_inet_same(internal,internal,internal);


ALTER OPERATOR CLASS public.gist_cidr_ops USING gist OWNER TO postgres;

--
-- TOC entry 3750 (class 2753 OID 5901358)
-- Name: gist_date_ops; Type: OPERATOR FAMILY; Schema: public; Owner: auikadmin
--

CREATE OPERATOR FAMILY gist_date_ops USING gist;


ALTER OPERATOR FAMILY public.gist_date_ops USING gist OWNER TO auikadmin;

--
-- TOC entry 3636 (class 2616 OID 5901359)
-- Name: gist_date_ops; Type: OPERATOR CLASS; Schema: public; Owner: postgres
--

CREATE OPERATOR CLASS gist_date_ops
    DEFAULT FOR TYPE date USING gist AS
    STORAGE gbtreekey8 ,
    OPERATOR 1 <(date,date) ,
    OPERATOR 2 <=(date,date) ,
    OPERATOR 3 =(date,date) ,
    OPERATOR 4 >=(date,date) ,
    OPERATOR 5 >(date,date) ,
    FUNCTION 1 (date, date) gbt_date_consistent(internal,date,smallint) ,
    FUNCTION 2 (date, date) gbt_date_union(bytea,internal) ,
    FUNCTION 3 (date, date) gbt_date_compress(internal) ,
    FUNCTION 4 (date, date) gbt_decompress(internal) ,
    FUNCTION 5 (date, date) gbt_date_penalty(internal,internal,internal) ,
    FUNCTION 6 (date, date) gbt_date_picksplit(internal,internal) ,
    FUNCTION 7 (date, date) gbt_date_same(internal,internal,internal);


ALTER OPERATOR CLASS public.gist_date_ops USING gist OWNER TO postgres;

--
-- TOC entry 3751 (class 2753 OID 5901372)
-- Name: gist_float4_ops; Type: OPERATOR FAMILY; Schema: public; Owner: auikadmin
--

CREATE OPERATOR FAMILY gist_float4_ops USING gist;


ALTER OPERATOR FAMILY public.gist_float4_ops USING gist OWNER TO auikadmin;

--
-- TOC entry 3637 (class 2616 OID 5901373)
-- Name: gist_float4_ops; Type: OPERATOR CLASS; Schema: public; Owner: postgres
--

CREATE OPERATOR CLASS gist_float4_ops
    DEFAULT FOR TYPE real USING gist AS
    STORAGE gbtreekey8 ,
    OPERATOR 1 <(real,real) ,
    OPERATOR 2 <=(real,real) ,
    OPERATOR 3 =(real,real) ,
    OPERATOR 4 >=(real,real) ,
    OPERATOR 5 >(real,real) ,
    FUNCTION 1 (real, real) gbt_float4_consistent(internal,real,smallint) ,
    FUNCTION 2 (real, real) gbt_float4_union(bytea,internal) ,
    FUNCTION 3 (real, real) gbt_float4_compress(internal) ,
    FUNCTION 4 (real, real) gbt_decompress(internal) ,
    FUNCTION 5 (real, real) gbt_float4_penalty(internal,internal,internal) ,
    FUNCTION 6 (real, real) gbt_float4_picksplit(internal,internal) ,
    FUNCTION 7 (real, real) gbt_float4_same(internal,internal,internal);


ALTER OPERATOR CLASS public.gist_float4_ops USING gist OWNER TO postgres;

--
-- TOC entry 3752 (class 2753 OID 5901386)
-- Name: gist_float8_ops; Type: OPERATOR FAMILY; Schema: public; Owner: auikadmin
--

CREATE OPERATOR FAMILY gist_float8_ops USING gist;


ALTER OPERATOR FAMILY public.gist_float8_ops USING gist OWNER TO auikadmin;

--
-- TOC entry 3638 (class 2616 OID 5901387)
-- Name: gist_float8_ops; Type: OPERATOR CLASS; Schema: public; Owner: postgres
--

CREATE OPERATOR CLASS gist_float8_ops
    DEFAULT FOR TYPE double precision USING gist AS
    STORAGE gbtreekey16 ,
    OPERATOR 1 <(double precision,double precision) ,
    OPERATOR 2 <=(double precision,double precision) ,
    OPERATOR 3 =(double precision,double precision) ,
    OPERATOR 4 >=(double precision,double precision) ,
    OPERATOR 5 >(double precision,double precision) ,
    FUNCTION 1 (double precision, double precision) gbt_float8_consistent(internal,double precision,smallint) ,
    FUNCTION 2 (double precision, double precision) gbt_float8_union(bytea,internal) ,
    FUNCTION 3 (double precision, double precision) gbt_float8_compress(internal) ,
    FUNCTION 4 (double precision, double precision) gbt_decompress(internal) ,
    FUNCTION 5 (double precision, double precision) gbt_float8_penalty(internal,internal,internal) ,
    FUNCTION 6 (double precision, double precision) gbt_float8_picksplit(internal,internal) ,
    FUNCTION 7 (double precision, double precision) gbt_float8_same(internal,internal,internal);


ALTER OPERATOR CLASS public.gist_float8_ops USING gist OWNER TO postgres;

--
-- TOC entry 3753 (class 2753 OID 5901400)
-- Name: gist_inet_ops; Type: OPERATOR FAMILY; Schema: public; Owner: auikadmin
--

CREATE OPERATOR FAMILY gist_inet_ops USING gist;


ALTER OPERATOR FAMILY public.gist_inet_ops USING gist OWNER TO auikadmin;

--
-- TOC entry 3639 (class 2616 OID 5901401)
-- Name: gist_inet_ops; Type: OPERATOR CLASS; Schema: public; Owner: postgres
--

CREATE OPERATOR CLASS gist_inet_ops
    DEFAULT FOR TYPE inet USING gist AS
    STORAGE gbtreekey16 ,
    OPERATOR 1 <(inet,inet) ,
    OPERATOR 2 <=(inet,inet) ,
    OPERATOR 3 =(inet,inet) ,
    OPERATOR 4 >=(inet,inet) ,
    OPERATOR 5 >(inet,inet) ,
    FUNCTION 1 (inet, inet) gbt_inet_consistent(internal,inet,smallint) ,
    FUNCTION 2 (inet, inet) gbt_inet_union(bytea,internal) ,
    FUNCTION 3 (inet, inet) gbt_inet_compress(internal) ,
    FUNCTION 4 (inet, inet) gbt_decompress(internal) ,
    FUNCTION 5 (inet, inet) gbt_inet_penalty(internal,internal,internal) ,
    FUNCTION 6 (inet, inet) gbt_inet_picksplit(internal,internal) ,
    FUNCTION 7 (inet, inet) gbt_inet_same(internal,internal,internal);


ALTER OPERATOR CLASS public.gist_inet_ops USING gist OWNER TO postgres;

--
-- TOC entry 3754 (class 2753 OID 5901414)
-- Name: gist_int2_ops; Type: OPERATOR FAMILY; Schema: public; Owner: auikadmin
--

CREATE OPERATOR FAMILY gist_int2_ops USING gist;


ALTER OPERATOR FAMILY public.gist_int2_ops USING gist OWNER TO auikadmin;

--
-- TOC entry 3640 (class 2616 OID 5901415)
-- Name: gist_int2_ops; Type: OPERATOR CLASS; Schema: public; Owner: postgres
--

CREATE OPERATOR CLASS gist_int2_ops
    DEFAULT FOR TYPE smallint USING gist AS
    STORAGE gbtreekey4 ,
    OPERATOR 1 <(smallint,smallint) ,
    OPERATOR 2 <=(smallint,smallint) ,
    OPERATOR 3 =(smallint,smallint) ,
    OPERATOR 4 >=(smallint,smallint) ,
    OPERATOR 5 >(smallint,smallint) ,
    FUNCTION 1 (smallint, smallint) gbt_int2_consistent(internal,smallint,smallint) ,
    FUNCTION 2 (smallint, smallint) gbt_int2_union(bytea,internal) ,
    FUNCTION 3 (smallint, smallint) gbt_int2_compress(internal) ,
    FUNCTION 4 (smallint, smallint) gbt_decompress(internal) ,
    FUNCTION 5 (smallint, smallint) gbt_int2_penalty(internal,internal,internal) ,
    FUNCTION 6 (smallint, smallint) gbt_int2_picksplit(internal,internal) ,
    FUNCTION 7 (smallint, smallint) gbt_int2_same(internal,internal,internal);


ALTER OPERATOR CLASS public.gist_int2_ops USING gist OWNER TO postgres;

--
-- TOC entry 3755 (class 2753 OID 5901428)
-- Name: gist_int4_ops; Type: OPERATOR FAMILY; Schema: public; Owner: auikadmin
--

CREATE OPERATOR FAMILY gist_int4_ops USING gist;


ALTER OPERATOR FAMILY public.gist_int4_ops USING gist OWNER TO auikadmin;

--
-- TOC entry 3641 (class 2616 OID 5901429)
-- Name: gist_int4_ops; Type: OPERATOR CLASS; Schema: public; Owner: postgres
--

CREATE OPERATOR CLASS gist_int4_ops
    DEFAULT FOR TYPE integer USING gist AS
    STORAGE gbtreekey8 ,
    OPERATOR 1 <(integer,integer) ,
    OPERATOR 2 <=(integer,integer) ,
    OPERATOR 3 =(integer,integer) ,
    OPERATOR 4 >=(integer,integer) ,
    OPERATOR 5 >(integer,integer) ,
    FUNCTION 1 (integer, integer) gbt_int4_consistent(internal,integer,smallint) ,
    FUNCTION 2 (integer, integer) gbt_int4_union(bytea,internal) ,
    FUNCTION 3 (integer, integer) gbt_int4_compress(internal) ,
    FUNCTION 4 (integer, integer) gbt_decompress(internal) ,
    FUNCTION 5 (integer, integer) gbt_int4_penalty(internal,internal,internal) ,
    FUNCTION 6 (integer, integer) gbt_int4_picksplit(internal,internal) ,
    FUNCTION 7 (integer, integer) gbt_int4_same(internal,internal,internal);


ALTER OPERATOR CLASS public.gist_int4_ops USING gist OWNER TO postgres;

--
-- TOC entry 3756 (class 2753 OID 5901442)
-- Name: gist_int8_ops; Type: OPERATOR FAMILY; Schema: public; Owner: auikadmin
--

CREATE OPERATOR FAMILY gist_int8_ops USING gist;


ALTER OPERATOR FAMILY public.gist_int8_ops USING gist OWNER TO auikadmin;

--
-- TOC entry 3642 (class 2616 OID 5901443)
-- Name: gist_int8_ops; Type: OPERATOR CLASS; Schema: public; Owner: postgres
--

CREATE OPERATOR CLASS gist_int8_ops
    DEFAULT FOR TYPE bigint USING gist AS
    STORAGE gbtreekey16 ,
    OPERATOR 1 <(bigint,bigint) ,
    OPERATOR 2 <=(bigint,bigint) ,
    OPERATOR 3 =(bigint,bigint) ,
    OPERATOR 4 >=(bigint,bigint) ,
    OPERATOR 5 >(bigint,bigint) ,
    FUNCTION 1 (bigint, bigint) gbt_int8_consistent(internal,bigint,smallint) ,
    FUNCTION 2 (bigint, bigint) gbt_int8_union(bytea,internal) ,
    FUNCTION 3 (bigint, bigint) gbt_int8_compress(internal) ,
    FUNCTION 4 (bigint, bigint) gbt_decompress(internal) ,
    FUNCTION 5 (bigint, bigint) gbt_int8_penalty(internal,internal,internal) ,
    FUNCTION 6 (bigint, bigint) gbt_int8_picksplit(internal,internal) ,
    FUNCTION 7 (bigint, bigint) gbt_int8_same(internal,internal,internal);


ALTER OPERATOR CLASS public.gist_int8_ops USING gist OWNER TO postgres;

--
-- TOC entry 3757 (class 2753 OID 5901456)
-- Name: gist_interval_ops; Type: OPERATOR FAMILY; Schema: public; Owner: auikadmin
--

CREATE OPERATOR FAMILY gist_interval_ops USING gist;


ALTER OPERATOR FAMILY public.gist_interval_ops USING gist OWNER TO auikadmin;

--
-- TOC entry 3643 (class 2616 OID 5901457)
-- Name: gist_interval_ops; Type: OPERATOR CLASS; Schema: public; Owner: postgres
--

CREATE OPERATOR CLASS gist_interval_ops
    DEFAULT FOR TYPE interval USING gist AS
    STORAGE gbtreekey32 ,
    OPERATOR 1 <(interval,interval) ,
    OPERATOR 2 <=(interval,interval) ,
    OPERATOR 3 =(interval,interval) ,
    OPERATOR 4 >=(interval,interval) ,
    OPERATOR 5 >(interval,interval) ,
    FUNCTION 1 (interval, interval) gbt_intv_consistent(internal,interval,smallint) ,
    FUNCTION 2 (interval, interval) gbt_intv_union(bytea,internal) ,
    FUNCTION 3 (interval, interval) gbt_intv_compress(internal) ,
    FUNCTION 4 (interval, interval) gbt_intv_decompress(internal) ,
    FUNCTION 5 (interval, interval) gbt_intv_penalty(internal,internal,internal) ,
    FUNCTION 6 (interval, interval) gbt_intv_picksplit(internal,internal) ,
    FUNCTION 7 (interval, interval) gbt_intv_same(internal,internal,internal);


ALTER OPERATOR CLASS public.gist_interval_ops USING gist OWNER TO postgres;

--
-- TOC entry 3758 (class 2753 OID 5901470)
-- Name: gist_macaddr_ops; Type: OPERATOR FAMILY; Schema: public; Owner: auikadmin
--

CREATE OPERATOR FAMILY gist_macaddr_ops USING gist;


ALTER OPERATOR FAMILY public.gist_macaddr_ops USING gist OWNER TO auikadmin;

--
-- TOC entry 3644 (class 2616 OID 5901471)
-- Name: gist_macaddr_ops; Type: OPERATOR CLASS; Schema: public; Owner: postgres
--

CREATE OPERATOR CLASS gist_macaddr_ops
    DEFAULT FOR TYPE macaddr USING gist AS
    STORAGE gbtreekey16 ,
    OPERATOR 1 <(macaddr,macaddr) ,
    OPERATOR 2 <=(macaddr,macaddr) ,
    OPERATOR 3 =(macaddr,macaddr) ,
    OPERATOR 4 >=(macaddr,macaddr) ,
    OPERATOR 5 >(macaddr,macaddr) ,
    FUNCTION 1 (macaddr, macaddr) gbt_macad_consistent(internal,macaddr,smallint) ,
    FUNCTION 2 (macaddr, macaddr) gbt_macad_union(bytea,internal) ,
    FUNCTION 3 (macaddr, macaddr) gbt_macad_compress(internal) ,
    FUNCTION 4 (macaddr, macaddr) gbt_decompress(internal) ,
    FUNCTION 5 (macaddr, macaddr) gbt_macad_penalty(internal,internal,internal) ,
    FUNCTION 6 (macaddr, macaddr) gbt_macad_picksplit(internal,internal) ,
    FUNCTION 7 (macaddr, macaddr) gbt_macad_same(internal,internal,internal);


ALTER OPERATOR CLASS public.gist_macaddr_ops USING gist OWNER TO postgres;

--
-- TOC entry 3759 (class 2753 OID 5901484)
-- Name: gist_numeric_ops; Type: OPERATOR FAMILY; Schema: public; Owner: auikadmin
--

CREATE OPERATOR FAMILY gist_numeric_ops USING gist;


ALTER OPERATOR FAMILY public.gist_numeric_ops USING gist OWNER TO auikadmin;

--
-- TOC entry 3645 (class 2616 OID 5901485)
-- Name: gist_numeric_ops; Type: OPERATOR CLASS; Schema: public; Owner: postgres
--

CREATE OPERATOR CLASS gist_numeric_ops
    DEFAULT FOR TYPE numeric USING gist AS
    STORAGE gbtreekey_var ,
    OPERATOR 1 <(numeric,numeric) ,
    OPERATOR 2 <=(numeric,numeric) ,
    OPERATOR 3 =(numeric,numeric) ,
    OPERATOR 4 >=(numeric,numeric) ,
    OPERATOR 5 >(numeric,numeric) ,
    FUNCTION 1 (numeric, numeric) gbt_numeric_consistent(internal,numeric,smallint) ,
    FUNCTION 2 (numeric, numeric) gbt_numeric_union(bytea,internal) ,
    FUNCTION 3 (numeric, numeric) gbt_numeric_compress(internal) ,
    FUNCTION 4 (numeric, numeric) gbt_var_decompress(internal) ,
    FUNCTION 5 (numeric, numeric) gbt_numeric_penalty(internal,internal,internal) ,
    FUNCTION 6 (numeric, numeric) gbt_numeric_picksplit(internal,internal) ,
    FUNCTION 7 (numeric, numeric) gbt_numeric_same(internal,internal,internal);


ALTER OPERATOR CLASS public.gist_numeric_ops USING gist OWNER TO postgres;

--
-- TOC entry 3760 (class 2753 OID 5901498)
-- Name: gist_oid_ops; Type: OPERATOR FAMILY; Schema: public; Owner: auikadmin
--

CREATE OPERATOR FAMILY gist_oid_ops USING gist;


ALTER OPERATOR FAMILY public.gist_oid_ops USING gist OWNER TO auikadmin;

--
-- TOC entry 3646 (class 2616 OID 5901499)
-- Name: gist_oid_ops; Type: OPERATOR CLASS; Schema: public; Owner: postgres
--

CREATE OPERATOR CLASS gist_oid_ops
    DEFAULT FOR TYPE oid USING gist AS
    STORAGE gbtreekey8 ,
    OPERATOR 1 <(oid,oid) ,
    OPERATOR 2 <=(oid,oid) ,
    OPERATOR 3 =(oid,oid) ,
    OPERATOR 4 >=(oid,oid) ,
    OPERATOR 5 >(oid,oid) ,
    FUNCTION 1 (oid, oid) gbt_oid_consistent(internal,oid,smallint) ,
    FUNCTION 2 (oid, oid) gbt_oid_union(bytea,internal) ,
    FUNCTION 3 (oid, oid) gbt_oid_compress(internal) ,
    FUNCTION 4 (oid, oid) gbt_decompress(internal) ,
    FUNCTION 5 (oid, oid) gbt_oid_penalty(internal,internal,internal) ,
    FUNCTION 6 (oid, oid) gbt_oid_picksplit(internal,internal) ,
    FUNCTION 7 (oid, oid) gbt_oid_same(internal,internal,internal);


ALTER OPERATOR CLASS public.gist_oid_ops USING gist OWNER TO postgres;

--
-- TOC entry 3761 (class 2753 OID 5901512)
-- Name: gist_text_ops; Type: OPERATOR FAMILY; Schema: public; Owner: auikadmin
--

CREATE OPERATOR FAMILY gist_text_ops USING gist;


ALTER OPERATOR FAMILY public.gist_text_ops USING gist OWNER TO auikadmin;

--
-- TOC entry 3647 (class 2616 OID 5901513)
-- Name: gist_text_ops; Type: OPERATOR CLASS; Schema: public; Owner: postgres
--

CREATE OPERATOR CLASS gist_text_ops
    DEFAULT FOR TYPE text USING gist AS
    STORAGE gbtreekey_var ,
    OPERATOR 1 <(text,text) ,
    OPERATOR 2 <=(text,text) ,
    OPERATOR 3 =(text,text) ,
    OPERATOR 4 >=(text,text) ,
    OPERATOR 5 >(text,text) ,
    FUNCTION 1 (text, text) gbt_text_consistent(internal,text,smallint) ,
    FUNCTION 2 (text, text) gbt_text_union(bytea,internal) ,
    FUNCTION 3 (text, text) gbt_text_compress(internal) ,
    FUNCTION 4 (text, text) gbt_var_decompress(internal) ,
    FUNCTION 5 (text, text) gbt_text_penalty(internal,internal,internal) ,
    FUNCTION 6 (text, text) gbt_text_picksplit(internal,internal) ,
    FUNCTION 7 (text, text) gbt_text_same(internal,internal,internal);


ALTER OPERATOR CLASS public.gist_text_ops USING gist OWNER TO postgres;

--
-- TOC entry 3762 (class 2753 OID 5901526)
-- Name: gist_time_ops; Type: OPERATOR FAMILY; Schema: public; Owner: auikadmin
--

CREATE OPERATOR FAMILY gist_time_ops USING gist;


ALTER OPERATOR FAMILY public.gist_time_ops USING gist OWNER TO auikadmin;

--
-- TOC entry 3648 (class 2616 OID 5901527)
-- Name: gist_time_ops; Type: OPERATOR CLASS; Schema: public; Owner: postgres
--

CREATE OPERATOR CLASS gist_time_ops
    DEFAULT FOR TYPE time without time zone USING gist AS
    STORAGE gbtreekey16 ,
    OPERATOR 1 <(time without time zone,time without time zone) ,
    OPERATOR 2 <=(time without time zone,time without time zone) ,
    OPERATOR 3 =(time without time zone,time without time zone) ,
    OPERATOR 4 >=(time without time zone,time without time zone) ,
    OPERATOR 5 >(time without time zone,time without time zone) ,
    FUNCTION 1 (time without time zone, time without time zone) gbt_time_consistent(internal,time without time zone,smallint) ,
    FUNCTION 2 (time without time zone, time without time zone) gbt_time_union(bytea,internal) ,
    FUNCTION 3 (time without time zone, time without time zone) gbt_time_compress(internal) ,
    FUNCTION 4 (time without time zone, time without time zone) gbt_decompress(internal) ,
    FUNCTION 5 (time without time zone, time without time zone) gbt_time_penalty(internal,internal,internal) ,
    FUNCTION 6 (time without time zone, time without time zone) gbt_time_picksplit(internal,internal) ,
    FUNCTION 7 (time without time zone, time without time zone) gbt_time_same(internal,internal,internal);


ALTER OPERATOR CLASS public.gist_time_ops USING gist OWNER TO postgres;

--
-- TOC entry 3763 (class 2753 OID 5901540)
-- Name: gist_timestamp_ops; Type: OPERATOR FAMILY; Schema: public; Owner: auikadmin
--

CREATE OPERATOR FAMILY gist_timestamp_ops USING gist;


ALTER OPERATOR FAMILY public.gist_timestamp_ops USING gist OWNER TO auikadmin;

--
-- TOC entry 3649 (class 2616 OID 5901541)
-- Name: gist_timestamp_ops; Type: OPERATOR CLASS; Schema: public; Owner: postgres
--

CREATE OPERATOR CLASS gist_timestamp_ops
    DEFAULT FOR TYPE timestamp without time zone USING gist AS
    STORAGE gbtreekey16 ,
    OPERATOR 1 <(timestamp without time zone,timestamp without time zone) ,
    OPERATOR 2 <=(timestamp without time zone,timestamp without time zone) ,
    OPERATOR 3 =(timestamp without time zone,timestamp without time zone) ,
    OPERATOR 4 >=(timestamp without time zone,timestamp without time zone) ,
    OPERATOR 5 >(timestamp without time zone,timestamp without time zone) ,
    FUNCTION 1 (timestamp without time zone, timestamp without time zone) gbt_ts_consistent(internal,timestamp without time zone,smallint) ,
    FUNCTION 2 (timestamp without time zone, timestamp without time zone) gbt_ts_union(bytea,internal) ,
    FUNCTION 3 (timestamp without time zone, timestamp without time zone) gbt_ts_compress(internal) ,
    FUNCTION 4 (timestamp without time zone, timestamp without time zone) gbt_decompress(internal) ,
    FUNCTION 5 (timestamp without time zone, timestamp without time zone) gbt_ts_penalty(internal,internal,internal) ,
    FUNCTION 6 (timestamp without time zone, timestamp without time zone) gbt_ts_picksplit(internal,internal) ,
    FUNCTION 7 (timestamp without time zone, timestamp without time zone) gbt_ts_same(internal,internal,internal);


ALTER OPERATOR CLASS public.gist_timestamp_ops USING gist OWNER TO postgres;

--
-- TOC entry 3764 (class 2753 OID 5901554)
-- Name: gist_timestamptz_ops; Type: OPERATOR FAMILY; Schema: public; Owner: auikadmin
--

CREATE OPERATOR FAMILY gist_timestamptz_ops USING gist;


ALTER OPERATOR FAMILY public.gist_timestamptz_ops USING gist OWNER TO auikadmin;

--
-- TOC entry 3650 (class 2616 OID 5901555)
-- Name: gist_timestamptz_ops; Type: OPERATOR CLASS; Schema: public; Owner: postgres
--

CREATE OPERATOR CLASS gist_timestamptz_ops
    DEFAULT FOR TYPE timestamp with time zone USING gist AS
    STORAGE gbtreekey16 ,
    OPERATOR 1 <(timestamp with time zone,timestamp with time zone) ,
    OPERATOR 2 <=(timestamp with time zone,timestamp with time zone) ,
    OPERATOR 3 =(timestamp with time zone,timestamp with time zone) ,
    OPERATOR 4 >=(timestamp with time zone,timestamp with time zone) ,
    OPERATOR 5 >(timestamp with time zone,timestamp with time zone) ,
    FUNCTION 1 (timestamp with time zone, timestamp with time zone) gbt_tstz_consistent(internal,timestamp with time zone,smallint) ,
    FUNCTION 2 (timestamp with time zone, timestamp with time zone) gbt_ts_union(bytea,internal) ,
    FUNCTION 3 (timestamp with time zone, timestamp with time zone) gbt_tstz_compress(internal) ,
    FUNCTION 4 (timestamp with time zone, timestamp with time zone) gbt_decompress(internal) ,
    FUNCTION 5 (timestamp with time zone, timestamp with time zone) gbt_ts_penalty(internal,internal,internal) ,
    FUNCTION 6 (timestamp with time zone, timestamp with time zone) gbt_ts_picksplit(internal,internal) ,
    FUNCTION 7 (timestamp with time zone, timestamp with time zone) gbt_ts_same(internal,internal,internal);


ALTER OPERATOR CLASS public.gist_timestamptz_ops USING gist OWNER TO postgres;

--
-- TOC entry 3765 (class 2753 OID 5901568)
-- Name: gist_timetz_ops; Type: OPERATOR FAMILY; Schema: public; Owner: auikadmin
--

CREATE OPERATOR FAMILY gist_timetz_ops USING gist;


ALTER OPERATOR FAMILY public.gist_timetz_ops USING gist OWNER TO auikadmin;

--
-- TOC entry 3651 (class 2616 OID 5901569)
-- Name: gist_timetz_ops; Type: OPERATOR CLASS; Schema: public; Owner: postgres
--

CREATE OPERATOR CLASS gist_timetz_ops
    DEFAULT FOR TYPE time with time zone USING gist AS
    STORAGE gbtreekey16 ,
    OPERATOR 1 <(time with time zone,time with time zone) ,
    OPERATOR 2 <=(time with time zone,time with time zone) ,
    OPERATOR 3 =(time with time zone,time with time zone) ,
    OPERATOR 4 >=(time with time zone,time with time zone) ,
    OPERATOR 5 >(time with time zone,time with time zone) ,
    FUNCTION 1 (time with time zone, time with time zone) gbt_timetz_consistent(internal,time with time zone,smallint) ,
    FUNCTION 2 (time with time zone, time with time zone) gbt_time_union(bytea,internal) ,
    FUNCTION 3 (time with time zone, time with time zone) gbt_timetz_compress(internal) ,
    FUNCTION 4 (time with time zone, time with time zone) gbt_decompress(internal) ,
    FUNCTION 5 (time with time zone, time with time zone) gbt_time_penalty(internal,internal,internal) ,
    FUNCTION 6 (time with time zone, time with time zone) gbt_time_picksplit(internal,internal) ,
    FUNCTION 7 (time with time zone, time with time zone) gbt_time_same(internal,internal,internal);


ALTER OPERATOR CLASS public.gist_timetz_ops USING gist OWNER TO postgres;

--
-- TOC entry 3766 (class 2753 OID 5901582)
-- Name: gist_vbit_ops; Type: OPERATOR FAMILY; Schema: public; Owner: auikadmin
--

CREATE OPERATOR FAMILY gist_vbit_ops USING gist;


ALTER OPERATOR FAMILY public.gist_vbit_ops USING gist OWNER TO auikadmin;

--
-- TOC entry 3652 (class 2616 OID 5901583)
-- Name: gist_vbit_ops; Type: OPERATOR CLASS; Schema: public; Owner: postgres
--

CREATE OPERATOR CLASS gist_vbit_ops
    DEFAULT FOR TYPE bit varying USING gist AS
    STORAGE gbtreekey_var ,
    OPERATOR 1 <(bit varying,bit varying) ,
    OPERATOR 2 <=(bit varying,bit varying) ,
    OPERATOR 3 =(bit varying,bit varying) ,
    OPERATOR 4 >=(bit varying,bit varying) ,
    OPERATOR 5 >(bit varying,bit varying) ,
    FUNCTION 1 (bit varying, bit varying) gbt_bit_consistent(internal,bit,smallint) ,
    FUNCTION 2 (bit varying, bit varying) gbt_bit_union(bytea,internal) ,
    FUNCTION 3 (bit varying, bit varying) gbt_bit_compress(internal) ,
    FUNCTION 4 (bit varying, bit varying) gbt_var_decompress(internal) ,
    FUNCTION 5 (bit varying, bit varying) gbt_bit_penalty(internal,internal,internal) ,
    FUNCTION 6 (bit varying, bit varying) gbt_bit_picksplit(internal,internal) ,
    FUNCTION 7 (bit varying, bit varying) gbt_bit_same(internal,internal,internal);


ALTER OPERATOR CLASS public.gist_vbit_ops USING gist OWNER TO postgres;

SET search_path = awsv, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 194 (class 1259 OID 5901596)
-- Name: abfuellflaeche; Type: TABLE; Schema: awsv; Owner: auikadmin; Tablespace: 
--

CREATE TABLE abfuellflaeche (
    behaelterid integer NOT NULL,
    eoh boolean,
    ef boolean,
    abfsaniert boolean,
    abfneuerstellt boolean,
    bodenflaechenausf character varying(255),
    beschbodenfl character varying(255),
    dicke real,
    guete character varying(255),
    fugenmaterial character varying(255),
    beschfugenmat character varying(255),
    niederschlagschutz character varying(255),
    abscheidervorh boolean,
    beschableitung character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer NOT NULL,
    groesse integer
);


ALTER TABLE abfuellflaeche OWNER TO auikadmin;

--
-- TOC entry 195 (class 1259 OID 5901604)
-- Name: abscheider; Type: TABLE; Schema: awsv; Owner: auikadmin; Tablespace: 
--

CREATE TABLE abscheider (
    behaelterid integer NOT NULL,
    sfhersteller character varying(255),
    sftyp character varying(255),
    sfvolumen character varying(255),
    sfmaterial character varying(255),
    sfbeschichtung character varying(255),
    abhersteller character varying(255),
    abtyp character varying(255),
    abpruefz character varying(255),
    abmaterial character varying(255),
    abbeschichtung character varying(255),
    abnenngr character varying(255),
    zuldn character varying(255),
    zulmaterial character varying(255),
    zullaenge character varying(255),
    verbdn character varying(255),
    verbmaterial character varying(255),
    verblaenge character varying(255),
    sondn character varying(255),
    sonmaterial character varying(255),
    sonlaenge character varying(255),
    oelspeichervol character varying(255),
    kompaktanlage boolean,
    sf boolean,
    kkl1 boolean,
    lfkl2 boolean,
    ps boolean,
    ueberhausr boolean,
    waschanlvorh boolean,
    abgabe boolean,
    hlzapfanl boolean,
    belvonlagerbh boolean,
    rueckhalteausr boolean,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer NOT NULL
);


ALTER TABLE abscheider OWNER TO auikadmin;

--
-- TOC entry 196 (class 1259 OID 5901612)
-- Name: anlagenarten; Type: TABLE; Schema: awsv; Owner: auikadmin; Tablespace: 
--

CREATE TABLE anlagenarten (
    id integer NOT NULL,
    anlagenart character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE anlagenarten OWNER TO auikadmin;

--
-- TOC entry 197 (class 1259 OID 5901617)
-- Name: anlagenchrono; Type: TABLE; Schema: awsv; Owner: auikadmin; Tablespace: 
--

CREATE TABLE anlagenchrono (
    id integer NOT NULL,
    datum timestamp without time zone,
    sachverhalt character varying(255),
    wv timestamp without time zone,
    behaelterid integer NOT NULL,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    abgeschlossen boolean DEFAULT false
);


ALTER TABLE anlagenchrono OWNER TO auikadmin;

--
-- TOC entry 198 (class 1259 OID 5901623)
-- Name: awsv_fachdaten_behaelterid_seq; Type: SEQUENCE; Schema: awsv; Owner: auikadmin
--

CREATE SEQUENCE awsv_fachdaten_behaelterid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE awsv_fachdaten_behaelterid_seq OWNER TO auikadmin;

--
-- TOC entry 199 (class 1259 OID 5901625)
-- Name: behaelterart; Type: TABLE; Schema: awsv; Owner: auikadmin; Tablespace: 
--

CREATE TABLE behaelterart (
    id integer NOT NULL,
    behaelterart character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE behaelterart OWNER TO auikadmin;

--
-- TOC entry 200 (class 1259 OID 5901630)
-- Name: fachdaten; Type: TABLE; Schema: awsv; Owner: auikadmin; Tablespace: 
--

CREATE TABLE fachdaten (
    behaelterid integer DEFAULT nextval('awsv_fachdaten_behaelterid_seq'::regclass) NOT NULL,
    objektid integer NOT NULL,
    herstellnr character varying(255),
    hersteller character varying(255),
    datuminbetriebnahme timestamp without time zone,
    datumerfassung timestamp without time zone,
    datumaenderung timestamp without time zone,
    datumgenehmigung timestamp without time zone,
    anlagenart character varying(255),
    behaelterart character varying(255),
    material character varying(255),
    fluessigkeit character varying(255),
    vbfeinstufung character varying(255),
    menge double precision,
    wgk character varying,
    gefaehrdungsstufe character varying(255),
    baujahr integer,
    doppelwandig boolean,
    leckanzeige boolean,
    auffangraum boolean,
    grenzwertgeber boolean,
    leckschutzauskleidung boolean,
    kellerlagerung boolean,
    innenbeschichtung boolean,
    beschreibung_a text,
    beschreibung_s text,
    oberirdisch boolean,
    unterirdisch boolean,
    saugleitung boolean,
    rohr_kathodenschutz boolean,
    aus_kupfer boolean,
    aus_stahl boolean,
    mit_schutzrohr boolean,
    beschreibung_r text,
    pruefturnus double precision,
    angemahntkz boolean,
    mahnfrist timestamp without time zone,
    wiedervorlage timestamp without time zone,
    stillegungsdatum timestamp without time zone,
    bemerkungen text,
    ausfuehrung character varying(255),
    pruefumfang character varying(255),
    verwendung character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    aus_hdpe boolean,
    druckleitung boolean,
    schutz_sensor boolean,
    schutz_folie boolean,
    schutz_antiheber boolean,
    aktenzeichen character varying
);


ALTER TABLE fachdaten OWNER TO auikadmin;

--
-- TOC entry 201 (class 1259 OID 5901639)
-- Name: fluessigkeit; Type: TABLE; Schema: awsv; Owner: auikadmin; Tablespace: 
--

CREATE TABLE fluessigkeit (
    id integer NOT NULL,
    fluessigkeit character varying(255),
    idland integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE fluessigkeit OWNER TO auikadmin;

--
-- TOC entry 202 (class 1259 OID 5901644)
-- Name: gebuehrenarten; Type: TABLE; Schema: awsv; Owner: auikadmin; Tablespace: 
--

CREATE TABLE gebuehrenarten (
    id integer NOT NULL,
    gebuehrenart character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE gebuehrenarten OWNER TO auikadmin;

--
-- TOC entry 203 (class 1259 OID 5901649)
-- Name: gefaehrdungsstufen; Type: TABLE; Schema: awsv; Owner: auikadmin; Tablespace: 
--

CREATE TABLE gefaehrdungsstufen (
    id integer NOT NULL,
    gefaehrdungsstufen character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE gefaehrdungsstufen OWNER TO auikadmin;

SET default_with_oids = true;

--
-- TOC entry 204 (class 1259 OID 5901654)
-- Name: jgs; Type: TABLE; Schema: awsv; Owner: auikadmin; Tablespace: 
--

CREATE TABLE jgs (
    id integer NOT NULL,
    behaelterid integer,
    lagerflaeche integer,
    gewaesser_abstand integer,
    gewaesser_name character varying,
    brunnen_abstand integer,
    tierhaltung character varying,
    seitenwaende boolean,
    wandhoehe integer,
    bodenplatte character varying,
    ueberdachung boolean,
    auffangbeh character varying,
    volumen_auffangbeh double precision,
    rohrleitung character varying,
    dichtheitspruefung timestamp without time zone,
    drainage boolean,
    fuellanzeiger boolean,
    schieber boolean,
    abdeckung boolean,
    leitung_geprueft boolean,
    _enabled boolean,
    _deleted boolean
);


ALTER TABLE jgs OWNER TO auikadmin;

SET default_with_oids = false;

--
-- TOC entry 205 (class 1259 OID 5901660)
-- Name: kontrollen; Type: TABLE; Schema: awsv; Owner: auikadmin; Tablespace: 
--

CREATE TABLE kontrollen (
    id integer NOT NULL,
    grundderpruefung character varying(255),
    pruefturnus real,
    pruefdatum timestamp without time zone,
    pruefer character varying(255),
    pruefungabgeschlossen boolean,
    nachpruefung boolean,
    nachpruefdatum timestamp without time zone,
    nachpruefer character varying(255),
    naechstepruefung timestamp without time zone,
    pruefergebnis character varying(255),
    behaelterid integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE kontrollen OWNER TO auikadmin;

--
-- TOC entry 206 (class 1259 OID 5901668)
-- Name: material; Type: TABLE; Schema: awsv; Owner: auikadmin; Tablespace: 
--

CREATE TABLE material (
    id integer NOT NULL,
    material character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE material OWNER TO auikadmin;

--
-- TOC entry 207 (class 1259 OID 5901673)
-- Name: pruefer; Type: TABLE; Schema: awsv; Owner: auikadmin; Tablespace: 
--

CREATE TABLE pruefer (
    id integer NOT NULL,
    pruefer character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE pruefer OWNER TO auikadmin;

--
-- TOC entry 208 (class 1259 OID 5901678)
-- Name: pruefergebniss; Type: TABLE; Schema: awsv; Owner: auikadmin; Tablespace: 
--

CREATE TABLE pruefergebniss (
    id integer NOT NULL,
    pruefergebnis character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE pruefergebniss OWNER TO auikadmin;

--
-- TOC entry 209 (class 1259 OID 5901683)
-- Name: standortgghwsg; Type: TABLE; Schema: awsv; Owner: auikadmin; Tablespace: 
--

CREATE TABLE standortgghwsg (
    id integer NOT NULL,
    standortgg character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE standortgghwsg OWNER TO auikadmin;

--
-- TOC entry 210 (class 1259 OID 5901688)
-- Name: awsv_abfuellflaeche_id_seq; Type: SEQUENCE; Schema: awsv; Owner: auikadmin
--

CREATE SEQUENCE awsv_abfuellflaeche_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE awsv_abfuellflaeche_id_seq OWNER TO auikadmin;

--
-- TOC entry 4851 (class 0 OID 0)
-- Dependencies: 210
-- Name: awsv_abfuellflaeche_id_seq; Type: SEQUENCE OWNED BY; Schema: awsv; Owner: auikadmin
--

ALTER SEQUENCE awsv_abfuellflaeche_id_seq OWNED BY abfuellflaeche.id;


--
-- TOC entry 211 (class 1259 OID 5901690)
-- Name: awsv_abscheider_id_seq; Type: SEQUENCE; Schema: awsv; Owner: auikadmin
--

CREATE SEQUENCE awsv_abscheider_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE awsv_abscheider_id_seq OWNER TO auikadmin;

--
-- TOC entry 4853 (class 0 OID 0)
-- Dependencies: 211
-- Name: awsv_abscheider_id_seq; Type: SEQUENCE OWNED BY; Schema: awsv; Owner: auikadmin
--

ALTER SEQUENCE awsv_abscheider_id_seq OWNED BY abscheider.id;


--
-- TOC entry 212 (class 1259 OID 5901692)
-- Name: awsv_anlagenchrono_id_seq; Type: SEQUENCE; Schema: awsv; Owner: auikadmin
--

CREATE SEQUENCE awsv_anlagenchrono_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE awsv_anlagenchrono_id_seq OWNER TO auikadmin;

--
-- TOC entry 4855 (class 0 OID 0)
-- Dependencies: 212
-- Name: awsv_anlagenchrono_id_seq; Type: SEQUENCE OWNED BY; Schema: awsv; Owner: auikadmin
--

ALTER SEQUENCE awsv_anlagenchrono_id_seq OWNED BY anlagenchrono.id;


--
-- TOC entry 213 (class 1259 OID 5901694)
-- Name: awsv_jgs_id_seq; Type: SEQUENCE; Schema: awsv; Owner: auikadmin
--

CREATE SEQUENCE awsv_jgs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE awsv_jgs_id_seq OWNER TO auikadmin;

--
-- TOC entry 4856 (class 0 OID 0)
-- Dependencies: 213
-- Name: awsv_jgs_id_seq; Type: SEQUENCE OWNED BY; Schema: awsv; Owner: auikadmin
--

ALTER SEQUENCE awsv_jgs_id_seq OWNED BY jgs.id;


--
-- TOC entry 214 (class 1259 OID 5901696)
-- Name: awsv_kontrollen_id_seq; Type: SEQUENCE; Schema: awsv; Owner: auikadmin
--

CREATE SEQUENCE awsv_kontrollen_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE awsv_kontrollen_id_seq OWNER TO auikadmin;

--
-- TOC entry 4858 (class 0 OID 0)
-- Dependencies: 214
-- Name: awsv_kontrollen_id_seq; Type: SEQUENCE OWNED BY; Schema: awsv; Owner: auikadmin
--

ALTER SEQUENCE awsv_kontrollen_id_seq OWNED BY kontrollen.id;


--
-- TOC entry 215 (class 1259 OID 5901698)
-- Name: verwaltungsgebuehren; Type: TABLE; Schema: awsv; Owner: auikadmin; Tablespace: 
--

CREATE TABLE verwaltungsgebuehren (
    id integer NOT NULL,
    behaelterid integer,
    gebuehrenart integer,
    datum timestamp without time zone,
    betrag real,
    abschnitt character varying(255),
    kassenzeichen character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE verwaltungsgebuehren OWNER TO auikadmin;

--
-- TOC entry 216 (class 1259 OID 5901706)
-- Name: awsv_verwaltungsgebuehren_id_seq; Type: SEQUENCE; Schema: awsv; Owner: auikadmin
--

CREATE SEQUENCE awsv_verwaltungsgebuehren_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE awsv_verwaltungsgebuehren_id_seq OWNER TO auikadmin;

--
-- TOC entry 4859 (class 0 OID 0)
-- Dependencies: 216
-- Name: awsv_verwaltungsgebuehren_id_seq; Type: SEQUENCE OWNED BY; Schema: awsv; Owner: auikadmin
--

ALTER SEQUENCE awsv_verwaltungsgebuehren_id_seq OWNED BY verwaltungsgebuehren.id;


--
-- TOC entry 217 (class 1259 OID 5901708)
-- Name: verwaltungsverf; Type: TABLE; Schema: awsv; Owner: auikadmin; Tablespace: 
--

CREATE TABLE verwaltungsverf (
    id integer NOT NULL,
    datum timestamp without time zone,
    massnahme character varying(255),
    wiedervorlage timestamp without time zone,
    wvverwverf boolean,
    behaelterid integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE verwaltungsverf OWNER TO auikadmin;

--
-- TOC entry 218 (class 1259 OID 5901713)
-- Name: awsv_verwaltungsverf_id_seq; Type: SEQUENCE; Schema: awsv; Owner: auikadmin
--

CREATE SEQUENCE awsv_verwaltungsverf_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE awsv_verwaltungsverf_id_seq OWNER TO auikadmin;

--
-- TOC entry 4860 (class 0 OID 0)
-- Dependencies: 218
-- Name: awsv_verwaltungsverf_id_seq; Type: SEQUENCE OWNED BY; Schema: awsv; Owner: auikadmin
--

ALTER SEQUENCE awsv_verwaltungsverf_id_seq OWNED BY verwaltungsverf.id;


--
-- TOC entry 219 (class 1259 OID 5901715)
-- Name: vbfeinstufung; Type: TABLE; Schema: awsv; Owner: auikadmin; Tablespace: 
--

CREATE TABLE vbfeinstufung (
    id integer NOT NULL,
    vbfeinstufung character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE vbfeinstufung OWNER TO auikadmin;

--
-- TOC entry 220 (class 1259 OID 5901720)
-- Name: verwmassnahmen; Type: TABLE; Schema: awsv; Owner: auikadmin; Tablespace: 
--

CREATE TABLE verwmassnahmen (
    id integer NOT NULL,
    massnahmen character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE verwmassnahmen OWNER TO auikadmin;

--
-- TOC entry 221 (class 1259 OID 5901725)
-- Name: wassereinzugsgebiet; Type: TABLE; Schema: awsv; Owner: auikadmin; Tablespace: 
--

CREATE TABLE wassereinzugsgebiet (
    id integer NOT NULL,
    ezgbname character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE wassereinzugsgebiet OWNER TO auikadmin;

SET search_path = basis, pg_catalog;

--
-- TOC entry 222 (class 1259 OID 5901730)
-- Name: adresse; Type: TABLE; Schema: basis; Owner: auikadmin; Tablespace: 
--

CREATE TABLE adresse (
    id integer NOT NULL,
    strasse character varying(255),
    hausnr integer,
    hausnrzus character varying(255),
    plzzs character varying(255),
    plz character varying(255),
    ort character varying(255),
    bemerkungen character varying(255),
    revidatum timestamp without time zone,
    revihandz character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    erstell_dat date,
    igl_id integer,
    oberflid integer,
    flur character varying(255),
    flurstueck character varying(255),
    gemarkungid integer,
    ueberschgeb boolean,
    entgebid character varying(255),
    strasseeigent character varying(255),
    wassermenge integer,
    sachbe33rav character varying(255),
    sachbe33hee character varying(255),
    standortgegebid integer,
    wassereinzgebid integer
);


ALTER TABLE adresse OWNER TO auikadmin;

--
-- TOC entry 223 (class 1259 OID 5901741)
-- Name: basis_betreiber_id_seq; Type: SEQUENCE; Schema: basis; Owner: auikadmin
--

CREATE SEQUENCE basis_betreiber_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE basis_betreiber_id_seq OWNER TO auikadmin;

--
-- TOC entry 4862 (class 0 OID 0)
-- Dependencies: 223
-- Name: basis_betreiber_id_seq; Type: SEQUENCE OWNED BY; Schema: basis; Owner: auikadmin
--

ALTER SEQUENCE basis_betreiber_id_seq OWNED BY adresse.id;



--
-- TOC entry 226 (class 1259 OID 5901756)
-- Name: objekt; Type: TABLE; Schema: basis; Owner: auikadmin; Tablespace: 
--

CREATE TABLE objekt (
    id integer NOT NULL,
    uschistdid integer,
    beschreibung character varying(255),
    wiedervorlage timestamp without time zone,
    erfassungsdatum timestamp without time zone,
    gueltig_von timestamp without time zone,
    aenderungsdatum timestamp without time zone,
    gueltig_bis timestamp without time zone,
    inaktiv boolean DEFAULT false NOT NULL,
    betreiberid integer,
    objektartid integer,
    prioritaet character varying,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    abwasserfrei boolean DEFAULT true,
    standortid integer,
    sachbearbeiter integer,
    elkarelevant boolean DEFAULT false
);


ALTER TABLE objekt OWNER TO auikadmin;

--
-- TOC entry 227 (class 1259 OID 5901767)
-- Name: basis_objekt_objektid_seq; Type: SEQUENCE; Schema: basis; Owner: auikadmin
--

CREATE SEQUENCE basis_objekt_objektid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE basis_objekt_objektid_seq OWNER TO auikadmin;

--
-- TOC entry 4864 (class 0 OID 0)
-- Dependencies: 227
-- Name: basis_objekt_objektid_seq; Type: SEQUENCE OWNED BY; Schema: basis; Owner: auikadmin
--

ALTER SEQUENCE basis_objekt_objektid_seq OWNED BY objekt.id;


--
-- TOC entry 228 (class 1259 OID 5901769)
-- Name: objektchrono; Type: TABLE; Schema: basis; Owner: auikadmin; Tablespace: 
--

CREATE TABLE objektchrono (
    id integer NOT NULL,
    datum timestamp without time zone,
    sachverhalt character varying(255),
    wv timestamp without time zone,
    sachbearbeiter character varying(255),
    objektid integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    pfad character varying
);


ALTER TABLE objektchrono OWNER TO auikadmin;

--
-- TOC entry 229 (class 1259 OID 5901777)
-- Name: basis_objektchrono_id_seq; Type: SEQUENCE; Schema: basis; Owner: auikadmin
--

CREATE SEQUENCE basis_objektchrono_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE basis_objektchrono_id_seq OWNER TO auikadmin;

--
-- TOC entry 4865 (class 0 OID 0)
-- Dependencies: 229
-- Name: basis_objektchrono_id_seq; Type: SEQUENCE OWNED BY; Schema: basis; Owner: auikadmin
--

ALTER SEQUENCE basis_objektchrono_id_seq OWNED BY objektchrono.id;


--
-- TOC entry 230 (class 1259 OID 5901779)
-- Name: objektverknuepfung; Type: TABLE; Schema: basis; Owner: auikadmin; Tablespace: 
--

CREATE TABLE objektverknuepfung (
    id integer NOT NULL,
    ist_verknuepft_mit integer,
    objekt integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE objektverknuepfung OWNER TO auikadmin;

--
-- TOC entry 231 (class 1259 OID 5901784)
-- Name: basis_objektverknuepfung_id_seq; Type: SEQUENCE; Schema: basis; Owner: auikadmin
--

CREATE SEQUENCE basis_objektverknuepfung_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE basis_objektverknuepfung_id_seq OWNER TO auikadmin;

--
-- TOC entry 4866 (class 0 OID 0)
-- Dependencies: 231
-- Name: basis_objektverknuepfung_id_seq; Type: SEQUENCE OWNED BY; Schema: basis; Owner: auikadmin
--

ALTER SEQUENCE basis_objektverknuepfung_id_seq OWNED BY objektverknuepfung.id;


--
-- TOC entry 232 (class 1259 OID 5901786)
-- Name: sachbearbeiter; Type: TABLE; Schema: basis; Owner: auikadmin; Tablespace: 
--

CREATE TABLE sachbearbeiter (
    kennummer character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    zeichen character varying(255),
    zimmer character varying(255),
    telefon character varying(255),
    email character varying(255),
    gehoertzuarbeitsgr character varying,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer NOT NULL
);


ALTER TABLE sachbearbeiter OWNER TO auikadmin;

--
-- TOC entry 233 (class 1259 OID 5901794)
-- Name: basis_sachbearbeiter_id_seq; Type: SEQUENCE; Schema: basis; Owner: auikadmin
--

CREATE SEQUENCE basis_sachbearbeiter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE basis_sachbearbeiter_id_seq OWNER TO auikadmin;

--
-- TOC entry 4867 (class 0 OID 0)
-- Dependencies: 233
-- Name: basis_sachbearbeiter_id_seq; Type: SEQUENCE OWNED BY; Schema: basis; Owner: auikadmin
--

ALTER SEQUENCE basis_sachbearbeiter_id_seq OWNED BY sachbearbeiter.id;


SET default_with_oids = true;

--
-- TOC entry 234 (class 1259 OID 5901796)
-- Name: standort; Type: TABLE; Schema: basis; Owner: auikadmin; Tablespace: 
--

CREATE TABLE standort (
    id integer NOT NULL,
    inhaberid integer,
    _enabled boolean DEFAULT true,
    _deleted boolean DEFAULT false,
    industrieabwasser_tog boolean,
    niederschlagswasser_tog boolean,
    nur_sb_tog boolean,
    igl_id integer,
    e32 real,
    n32 real,
    bezeichnung character varying,
    revidatum timestamp without time zone,
    revihandz character varying(255),
    the_geom public.geometry
);


ALTER TABLE standort OWNER TO auikadmin;

--
-- TOC entry 235 (class 1259 OID 5901801)
-- Name: basis_standort_id_seq; Type: SEQUENCE; Schema: basis; Owner: auikadmin
--

CREATE SEQUENCE basis_standort_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE basis_standort_id_seq OWNER TO auikadmin;

--
-- TOC entry 4869 (class 0 OID 0)
-- Dependencies: 235
-- Name: basis_standort_id_seq; Type: SEQUENCE OWNED BY; Schema: basis; Owner: auikadmin
--

ALTER SEQUENCE basis_standort_id_seq OWNED BY standort.id;


SET default_with_oids = false;

--
-- TOC entry 236 (class 1259 OID 5901803)
-- Name: bezeichnung; Type: TABLE; Schema: basis; Owner: auikadmin; Tablespace: 
--

CREATE TABLE bezeichnung (
    id integer NOT NULL,
    gruppe character varying(255) NOT NULL,
    bezeichnung character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE bezeichnung OWNER TO auikadmin;

--
-- TOC entry 237 (class 1259 OID 5901811)
-- Name: gemarkung; Type: TABLE; Schema: basis; Owner: auikadmin; Tablespace: 
--

CREATE TABLE gemarkung (
    id integer NOT NULL,
    gemarkung character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE gemarkung OWNER TO auikadmin;

--
-- TOC entry 340 (class 1259 OID 5903752)
-- Name: inhaber; Type: TABLE; Schema: basis; Owner: auikadmin; Tablespace: 
--

CREATE TABLE inhaber (
    id integer NOT NULL,
    adresseid integer,
    anrede character varying(255),
    name character varying(255),
    vorname character varying(255),
    namezus character varying(255),
    namebetrbeauf character varying(255),
    vornamebetrbeauf character varying(255),
    telefon character varying(255),
    telefax character varying(255),
    email character varying(255),
    bemerkungen character varying(255),
    revidatum timestamp without time zone,
    revihandz character varying(255),
    kassenzeichen character varying(255),
    wirtschaftszweigid integer,
    auik_wz_code character varying(255),
    wassermenge integer,
    datenschutz_awsv boolean DEFAULT false,
    datenschutz_esatzung boolean DEFAULT false,
    datenschutz_whg boolean DEFAULT false,
    erstell_dat date,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE inhaber OWNER TO auikadmin;

--
-- TOC entry 339 (class 1259 OID 5903750)
-- Name: inhaber_id_seq; Type: SEQUENCE; Schema: basis; Owner: auikadmin
--

CREATE SEQUENCE inhaber_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE inhaber_id_seq OWNER TO auikadmin;

--
-- TOC entry 4871 (class 0 OID 0)
-- Dependencies: 339
-- Name: inhaber_id_seq; Type: SEQUENCE OWNED BY; Schema: basis; Owner: auikadmin
--

ALTER SEQUENCE inhaber_id_seq OWNED BY inhaber.id;


--
-- TOC entry 238 (class 1259 OID 5901816)
-- Name: objektarten; Type: TABLE; Schema: basis; Owner: auikadmin; Tablespace: 
--

CREATE TABLE objektarten (
    id integer NOT NULL,
    objektart character varying(255),
    abteilung character varying(255),
    kategorie character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE objektarten OWNER TO auikadmin;

--
-- TOC entry 239 (class 1259 OID 5901824)
-- Name: prioritaet; Type: TABLE; Schema: basis; Owner: auikadmin; Tablespace: 
--

CREATE TABLE prioritaet (
    standort_id integer NOT NULL,
    betreiber_id integer NOT NULL,
    prioritaet integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE prioritaet OWNER TO auikadmin;

--
-- TOC entry 241 (class 1259 OID 5901837)
-- Name: view_two_way_objektverknuepfung; Type: VIEW; Schema: basis; Owner: auikadmin
--

CREATE VIEW view_two_way_objektverknuepfung AS
 SELECT basis_objektverknuepfung.id,
    basis_objektverknuepfung.ist_verknuepft_mit,
    basis_objektverknuepfung.objekt,
    basis_objektverknuepfung._enabled,
    basis_objektverknuepfung._deleted
   FROM objektverknuepfung basis_objektverknuepfung
UNION
 SELECT (- basis_objektverknuepfung.id) AS id,
    basis_objektverknuepfung.objekt AS ist_verknuepft_mit,
    basis_objektverknuepfung.ist_verknuepft_mit AS objekt,
    basis_objektverknuepfung._enabled,
    basis_objektverknuepfung._deleted
   FROM objektverknuepfung basis_objektverknuepfung
  ORDER BY 2;


ALTER TABLE view_two_way_objektverknuepfung OWNER TO auikadmin;

--
-- TOC entry 242 (class 1259 OID 5901841)
-- Name: wirtschaftszweig; Type: TABLE; Schema: basis; Owner: auikadmin; Tablespace: 
--

CREATE TABLE wirtschaftszweig (
    id integer NOT NULL,
    wirtschaftszweig character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE wirtschaftszweig OWNER TO auikadmin;

SET search_path = elka, pg_catalog;

--
-- TOC entry 243 (class 1259 OID 5901846)
-- Name: _q_z; Type: VIEW; Schema: elka; Owner: auikadmin
--

CREATE VIEW _q_z AS
 SELECT DISTINCT objektverknuepfung.ist_verknuepft_mit AS quelle,
    objektverknuepfung.objekt AS ziel
   FROM basis.objektverknuepfung
  WHERE (objektverknuepfung._deleted = false)
UNION
 SELECT DISTINCT objektverknuepfung.objekt AS quelle,
    objektverknuepfung.ist_verknuepft_mit AS ziel
   FROM basis.objektverknuepfung
  WHERE (objektverknuepfung._deleted = false);


ALTER TABLE _q_z OWNER TO auikadmin;

--
-- TOC entry 244 (class 1259 OID 5901850)
-- Name: aba; Type: TABLE; Schema: elka; Owner: auikadmin; Tablespace: 
--

CREATE TABLE aba (
    id integer NOT NULL,
    objektid integer,
    anspr_adr_id integer,
    aktual_dat date,
    erstell_dat date,
    herkunft character varying,
    bezeichnung character varying,
    inbetrieb_dat date,
    stillgelegt_dat date,
    genehmpflichtig_toc boolean,
    e32 integer,
    n32 integer,
    sonst_zul_opt integer,
    verfahren_nr integer,
    wartungsvertrag_toc boolean,
    einzelabnahme_toc boolean,
    _enabled boolean,
    _deleted boolean
);


ALTER TABLE aba OWNER TO auikadmin;

SET default_with_oids = true;

--
-- TOC entry 245 (class 1259 OID 5901856)
-- Name: abaverfahren; Type: TABLE; Schema: elka; Owner: auikadmin; Tablespace: 
--

CREATE TABLE abaverfahren (
    nr integer NOT NULL,
    aktual_dat date,
    erstell_dat date,
    bezeichnung character varying
);


ALTER TABLE abaverfahren OWNER TO auikadmin;

SET default_with_oids = false;

--
-- TOC entry 246 (class 1259 OID 5901862)
-- Name: anfallstelle; Type: TABLE; Schema: elka; Owner: auikadmin; Tablespace: 
--

CREATE TABLE anfallstelle (
    id integer NOT NULL,
    objektid integer,
    seq_id integer,
    aktual_dat date,
    erstell_dat date,
    anhang_id character varying,
    herkunft character varying,
    anwendungsbereich character varying,
    bezeichnung character varying,
    stillgelegt_am date,
    abwa_beschaff_opt integer,
    betriebsweise_opt integer,
    _enabled boolean,
    _deleted boolean,
    max_vol_tag numeric(11,2),
    max_vol_stunde numeric(11,2),
    vol_jahr integer,
    external_nr character varying(50),
    anlagenart character varying,
    bemerkungen character varying
);


ALTER TABLE anfallstelle OWNER TO auikadmin;

--
-- TOC entry 355 (class 1259 OID 5903893)
-- Name: anhang; Type: TABLE; Schema: elka; Owner: auikadmin; Tablespace: 
--

CREATE TABLE anhang (
    sl_nr integer NOT NULL,
    anh_ma_sl_nr integer,
    anhang_id character varying,
    anh_gueltig_von date,
    anh_gueltig_bis date,
    anh_regelwerk character varying,
    anh_text character varying,
    herkunft character varying,
    erstell_dat date,
    aktual_dat date,
    zeitstempel timestamp without time zone
);


ALTER TABLE anhang OWNER TO "auikadmin";

--
-- TOC entry 247 (class 1259 OID 5901868)
-- Name: wasserrecht; Type: TABLE; Schema: elka; Owner: auikadmin; Tablespace: 
--

CREATE TABLE wasserrecht (
    objektid integer NOT NULL,
    bemerkungen character varying(255),
    erstellungs_datum date,
    aenderungs_datum date,
    antrag_datum date,
    befristet boolean,
    befristet_bis date,
    anhang integer,
    gen_menge integer,
    gen58 boolean,
    gen59 boolean,
    selbstueberw boolean,
    e_satzung boolean,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    uebergabestelle_e32 integer,
    uebergabestelle_n32 integer,
    id integer NOT NULL,
    recht_behoerden_id character varying(16),
    wr_beschreibung character varying(50),
    recht_art_opt integer,
    aktenzeichen character varying(45),
    bemerkung character varying(1000),
    wasserbuch_id character varying(30),
    beer_wr_nr character varying(10),
    erstell_dat timestamp without time zone,
    aktual_dat timestamp without time zone,
    gen8 boolean
);


ALTER TABLE wasserrecht OWNER TO auikadmin;

--
-- TOC entry 341 (class 1259 OID 5903784)
-- Name: e_adresse; Type: VIEW; Schema: elka; Owner: 

--

CREATE VIEW e_adresse AS
 SELECT DISTINCT inhaber.id AS nr,
    "left"((inhaber.anrede)::text, 30) AS anrede,
    "left"((inhaber.name)::text, 40) AS name1,
    "left"((((inhaber.vorname)::text || ' '::text) || (inhaber.namezus)::text), 40) AS name2,
    adresse.strasse,
    adresse.hausnr,
    adresse.hausnrzus,
    adresse.plz,
    adresse.plzzs AS staatskennung_zst,
    adresse.ort,
    adresse.gemarkungid,
    adresse.flur,
    adresse.flurstueck,
    "left"((inhaber.telefon)::text, 30) AS telefon,
    inhaber.telefax AS fax,
    inhaber.email,
    1 AS typ,
    false AS person_tog,
    '1970-01-01 00:00:00'::timestamp without time zone AS erstell_dat,
        CASE
            WHEN (adresse.revidatum IS NOT NULL) THEN adresse.revidatum
            ELSE '1970-01-01 00:00:00'::timestamp without time zone
        END AS aktual_dat,
    'ELKA_KR711'::text AS herkunft
   FROM basis.adresse,
    basis.inhaber,
    basis.objekt,
    wasserrecht,
    basis.standort
  WHERE (((((((adresse.id = inhaber.adresseid) AND (inhaber.id = standort.inhaberid)) AND (objekt.id = wasserrecht.objektid)) AND (standort.id = objekt.standortid)) AND (wasserrecht.gen59 = true)) AND (objekt._deleted = false)) AND (objekt.inaktiv = false))
UNION
 SELECT DISTINCT inhaber.id AS nr,
    "left"((inhaber.anrede)::text, 30) AS anrede,
    "left"((inhaber.name)::text, 40) AS name1,
    "left"((((inhaber.vorname)::text || ' '::text) || (inhaber.namezus)::text), 40) AS name2,
    adresse.strasse,
    adresse.hausnr,
    adresse.hausnrzus,
    adresse.plz,
    adresse.plzzs AS staatskennung_zst,
    adresse.ort,
    adresse.gemarkungid,
    adresse.flur,
    adresse.flurstueck,
    "left"((inhaber.telefon)::text, 30) AS telefon,
    inhaber.telefax AS fax,
    inhaber.email,
    1 AS typ,
    false AS person_tog,
    '1970-01-01 00:00:00'::timestamp without time zone AS erstell_dat,
        CASE
            WHEN (adresse.revidatum IS NOT NULL) THEN adresse.revidatum
            ELSE '1970-01-01 00:00:00'::timestamp without time zone
        END AS aktual_dat,
    'ELKA_KR711'::text AS herkunft
   FROM basis.adresse,
    basis.inhaber,
    basis.objekt,
    wasserrecht
  WHERE ((((((adresse.id = inhaber.adresseid) AND (inhaber.id = objekt.betreiberid)) AND (objekt.id = wasserrecht.objektid)) AND (wasserrecht.gen59 = true)) AND (objekt._deleted = false)) AND (objekt.inaktiv = false));


ALTER TABLE e_adresse OWNER TO "auikadmin";

--
-- TOC entry 342 (class 1259 OID 5903789)
-- Name: e_standort; Type: VIEW; Schema: elka; Owner: auikadmin
--

CREATE VIEW e_standort AS
 SELECT standort.id AS nr,
    '05711000'::character varying(8) AS gemeindekennzahl,
    gemarkung.gemarkung,
    e_adresse.flur,
    e_adresse.flurstueck AS flurstuecke,
    standort.e32,
    standort.n32,
    standort.industrieabwasser_tog,
    standort.niederschlagswasser_tog,
    false AS kommunale_ka_tog,
    false AS kleika_tog,
    standort.nur_sb_tog,
    e_adresse.erstell_dat,
    e_adresse.aktual_dat,
    'ELKA_KR711'::text AS herkunft,
    e_adresse.nr AS adr_nr
   FROM e_adresse,
    basis.standort,
    basis.gemarkung
  WHERE ((e_adresse.nr = standort.inhaberid) AND (e_adresse.gemarkungid = gemarkung.id));


ALTER TABLE e_standort OWNER TO auikadmin;

--
-- TOC entry 343 (class 1259 OID 5903794)
-- Name: e_abwasserbehandlungsanlage; Type: VIEW; Schema: elka; Owner: auikadmin
--

CREATE VIEW e_abwasserbehandlungsanlage AS
 SELECT aba.objektid AS nr,
    e_standort.nr AS standort_nr,
    e_standort.adr_nr AS sto_adr_nr,
    objekt.betreiberid AS betreib_adr_nr,
        CASE
            WHEN ((objekt.beschreibung IS NULL) OR ((objekt.beschreibung)::text = ''::text)) THEN '>> Bezeichnung folgt später <<'::character varying
            ELSE ("left"((objekt.beschreibung)::text, 64))::character varying
        END AS bezeichnung,
    e_standort.e32,
    e_standort.n32,
    false AS wartungsvertrag_tog,
    aba.genehmpflichtig_toc AS genehmpflichtig_tog,
    false AS einzelabnahme_tog,
    objekt.beschreibung AS bemerkung,
        CASE
            WHEN (objekt.aenderungsdatum IS NOT NULL) THEN objekt.aenderungsdatum
            ELSE '1970-01-01 00:00:00'::timestamp without time zone
        END AS aktual_dat,
        CASE
            WHEN (objekt.erfassungsdatum IS NOT NULL) THEN objekt.erfassungsdatum
            ELSE '1970-01-01 00:00:00'::timestamp without time zone
        END AS erstell_dat,
    'ELKA_KR711'::text AS herkunft
   FROM e_adresse,
    e_standort,
    basis.objekt,
    aba
  WHERE ((((((objekt.standortid = e_standort.nr) AND (objekt.betreiberid = e_adresse.nr)) AND (aba.objektid = objekt.id)) AND (objekt._deleted = false)) AND (objekt.inaktiv = false)) AND (objekt.elkarelevant = true));


ALTER TABLE e_abwasserbehandlungsanlage OWNER TO auikadmin;

--
-- TOC entry 248 (class 1259 OID 5901891)
-- Name: e_adresse_seq; Type: SEQUENCE; Schema: elka; Owner: auikadmin
--

CREATE SEQUENCE e_adresse_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE e_adresse_seq OWNER TO auikadmin;

--
-- TOC entry 354 (class 1259 OID 5903848)
-- Name: e_anfallstelle; Type: VIEW; Schema: elka; Owner: auikadmin
--

CREATE VIEW e_anfallstelle AS
 SELECT DISTINCT anfallstelle.objektid AS nr,
    e_standort.nr AS standort_nr,
    objekt.betreiberid AS adresse,
    anfallstelle.anhang_id,
    1 AS abwa_beschaff_opt,
    anfallstelle.bezeichnung,
    objekt.beschreibung AS bemerkung,
    false AS aufz_betrieb_tog,
    anfallstelle.aktual_dat,
    anfallstelle.erstell_dat,
    'ELKA_KR711'::text AS herkunft
   FROM e_adresse,
    e_standort,
    basis.objekt,
    anfallstelle
  WHERE ((((((objekt.standortid = e_standort.nr) AND (objekt.betreiberid = e_adresse.nr)) AND (anfallstelle.objektid = objekt.id)) AND (objekt._deleted = false)) AND (objekt.inaktiv = false)) AND (objekt.elkarelevant = true));


ALTER TABLE e_anfallstelle OWNER TO auikadmin;

--
-- TOC entry 353 (class 1259 OID 5903843)
-- Name: e_betrieb; Type: VIEW; Schema: elka; Owner: auikadmin
--

CREATE VIEW e_betrieb AS
 SELECT DISTINCT e_standort.nr,
    e_standort.nr AS standort_nr,
    e_adresse.nr AS wr_adr_nr,
    e_adresse.name1 AS bezeichnung,
    false AS suevkan_tog,
    e_standort.e32,
    e_standort.n32,
    e_standort.erstell_dat,
    e_standort.aktual_dat,
    e_standort.herkunft
   FROM e_adresse,
    basis.objekt,
    e_standort
  WHERE (((e_adresse.nr = objekt.betreiberid) AND (e_standort.nr = objekt.standortid)) AND (objekt.objektartid = 42));


ALTER TABLE e_betrieb OWNER TO auikadmin;

--
-- TOC entry 249 (class 1259 OID 5901903)
-- Name: einleitungsstelle; Type: TABLE; Schema: elka; Owner: auikadmin; Tablespace: 
--

CREATE TABLE einleitungsstelle (
    id integer NOT NULL,
    objektid integer,
    aktual_dat date,
    erstell_dat date,
    herkunft character varying,
    bezeichnung character varying,
    gewaessername_alias_3 character varying,
    gewaessername_ns character varying,
    nadia_id character varying,
    stillgelegt_am date,
    typ_indirekt boolean,
    typ_ind_gew_direkt boolean,
    typ_komm_trenn boolean,
    typ_privat_trenn boolean,
    typ_sonstige boolean,
    typ_ausserort_strasseneinl boolean,
    stationierung_ns_3 double precision,
    einzugsgebiet double precision,
    stationierung_st_3 double precision,
    abgaberel_einl smallint,
    e32 integer,
    n32 integer,
    kanal_art_opt smallint,
    stationierung_3_opt smallint,
    schutzzone_opt smallint,
    _deleted boolean,
    _enabled boolean,
    the_geom public.geometry,
    typ_indirekteinleitung_tog boolean DEFAULT false NOT NULL,
    typ_indus_gewerb_direkteinleitung_tog boolean DEFAULT false NOT NULL,
    typ_komm_nw_misch_tog boolean DEFAULT false NOT NULL,
    typ_komm_nw_trenn_tog boolean DEFAULT false NOT NULL,
    typ_nw_privat_trenn_tog boolean DEFAULT false NOT NULL,
    typ_indus_gewerb_nw_misch_tog boolean DEFAULT false NOT NULL,
    typ_indus_gewerb_nw_trenn_tog boolean DEFAULT false NOT NULL,
    typ_grubenwasser_tog boolean DEFAULT false NOT NULL,
    typ_kleinklaeranlage_tog boolean DEFAULT false NOT NULL,
    typ_komm_ka_tog boolean DEFAULT false NOT NULL,
    typ_ausseroertliche_strasseneinleitung_tog boolean DEFAULT false NOT NULL,
    typ_sonstige_tog boolean DEFAULT false NOT NULL,
    abgaberelevante_elt_opt integer,
    gewaesser_3_id character varying(20),
    ofwk_nrw_id character varying(24),
    ofwk_nrw_auflage_id character varying(10),
    ofwk_nrw_opt integer,
    see_ns_3_id character varying(20),
    seename_alias_3 character varying(60),
    see_auflage_3_id character varying(10),
    see_3_id character varying(20),
    einleitungsstellen_id character varying(9),
    gewaesser_auflage_3_id character varying(10),
    fluss_gebiet_3_id character varying(20),
    bemerkung character varying(1000),
    fluss_auflage_3_id character varying(10),
    gewaessername_ns_3 character varying(64),
    gewaesser_ns_3_id character varying(20),
    gwk_id character varying(15),
    ka_nicht_in_nrw_tog boolean DEFAULT false NOT NULL,
    ka_name_ausserhalb_nrw character varying(30),
    external_nr character varying(50),
    nr integer
);


ALTER TABLE einleitungsstelle OWNER TO auikadmin;

--
-- TOC entry 344 (class 1259 OID 5903799)
-- Name: e_einleitungsstelle; Type: VIEW; Schema: elka; Owner: auikadmin
--

CREATE VIEW e_einleitungsstelle AS
 SELECT DISTINCT einleitungsstelle.objektid AS nr,
    objekt.standortid AS standort_nr,
    true AS typ_indirekteinleitung_tog,
    einleitungsstelle.bezeichnung,
    einleitungsstelle.e32,
    einleitungsstelle.n32,
    einleitungsstelle.kanal_art_opt,
    false AS ka_nicht_in_nrw_tog,
    einleitungsstelle.aktual_dat,
    einleitungsstelle.erstell_dat,
    'ELKA_KR711'::text AS herkunft
   FROM e_adresse,
    e_standort,
    basis.objekt,
    einleitungsstelle
  WHERE ((((((objekt.standortid = e_standort.nr) AND (objekt.betreiberid = e_adresse.nr)) AND (einleitungsstelle.objektid = objekt.id)) AND (objekt._deleted = false)) AND (objekt.inaktiv = false)) AND (objekt.elkarelevant = true));


ALTER TABLE e_einleitungsstelle OWNER TO auikadmin;

SET search_path = oberflgw, pg_catalog;

SET default_with_oids = true;

--
-- TOC entry 250 (class 1259 OID 5901927)
-- Name: entwaesserungsgrundstueck; Type: TABLE; Schema: oberflgw; Owner: auikadmin; Tablespace: 
--

CREATE TABLE entwaesserungsgrundstueck (
    nr bigint NOT NULL,
    objekt_id bigint NOT NULL,
    erl_frei_el_tog boolean DEFAULT false NOT NULL,
    regenspende numeric(10,1),
    bemerkung character varying(1000),
    regenhaeufigkeit numeric(10,1),
    regendauer integer,
    gr_entw_gebiet integer,
    dtv_wert numeric(10,1),
    wasserableitungsstrecke_opt integer,
    name_etw_gebiet character varying(80),
    erstell_dat timestamp without time zone NOT NULL,
    einl_bereich_opt integer NOT NULL,
    abwbeskon_nr character varying(30),
    einbauart_opt integer,
    aktual_dat timestamp without time zone NOT NULL,
    adr_nr bigint NOT NULL,
    wasserecht_nr bigint,
    external_nr character varying(50)
);


ALTER TABLE entwaesserungsgrundstueck OWNER TO auikadmin;

SET search_path = elka, pg_catalog;

--
-- TOC entry 352 (class 1259 OID 5903838)
-- Name: e_entwaesserungsgrundstueck; Type: VIEW; Schema: elka; Owner: auikadmin
--

CREATE VIEW e_entwaesserungsgrundstueck AS
 SELECT entwaesserungsgrundstueck.nr,
    entwaesserungsgrundstueck.erl_frei_el_tog,
    entwaesserungsgrundstueck.regenspende,
    entwaesserungsgrundstueck.regenhaeufigkeit,
    objekt.beschreibung AS bemerkung,
    entwaesserungsgrundstueck.regendauer,
    entwaesserungsgrundstueck.gr_entw_gebiet,
    objekt.standortid,
    entwaesserungsgrundstueck.dtv_wert,
    entwaesserungsgrundstueck.wasserableitungsstrecke_opt,
    entwaesserungsgrundstueck.name_etw_gebiet,
    entwaesserungsgrundstueck.erstell_dat,
    entwaesserungsgrundstueck.einl_bereich_opt,
    entwaesserungsgrundstueck.abwbeskon_nr,
    entwaesserungsgrundstueck.einbauart_opt,
    entwaesserungsgrundstueck.aktual_dat,
    objekt.betreiberid,
    entwaesserungsgrundstueck.wasserecht_nr,
    'ELKA_KR711'::text AS herkunft,
    entwaesserungsgrundstueck.external_nr
   FROM e_adresse,
    e_standort,
    basis.objekt,
    oberflgw.entwaesserungsgrundstueck
  WHERE ((((objekt.standortid = e_standort.nr) AND (objekt.betreiberid = e_adresse.nr)) AND (entwaesserungsgrundstueck.objekt_id = objekt.id)) AND (objekt._deleted = false));


ALTER TABLE e_entwaesserungsgrundstueck OWNER TO auikadmin;

SET search_path = labor, pg_catalog;

SET default_with_oids = false;

--
-- TOC entry 251 (class 1259 OID 5901939)
-- Name: messstelle; Type: TABLE; Schema: labor; Owner: auikadmin; Tablespace: 
--

CREATE TABLE messstelle (
    objektid integer NOT NULL,
    beschreibung character varying(255),
    nr_probepkt integer,
    firmen_id integer,
    ka_id integer,
    art_id integer,
    branche character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer NOT NULL,
    sachbearbeiter integer
);


ALTER TABLE messstelle OWNER TO auikadmin;

SET search_path = elka, pg_catalog;

--
-- TOC entry 348 (class 1259 OID 5903818)
-- Name: e_messstelle; Type: VIEW; Schema: elka; Owner: auikadmin
--

CREATE VIEW e_messstelle AS
 SELECT messstelle.id AS nr,
    e_standort.nr AS standort_nr,
    e_standort.e32,
    e_standort.n32,
        CASE
            WHEN ((objekt.beschreibung IS NULL) OR ((objekt.beschreibung)::text = ''::text)) THEN '>> Bezeichnung folgt später <<'::character varying
            ELSE ("left"((objekt.beschreibung)::text, 64))::character varying
        END AS bezeichnung,
    3 AS typ_opt,
    0 AS art_messung_opt,
    messstelle.beschreibung AS bemerkung,
        CASE
            WHEN (objekt.aenderungsdatum IS NOT NULL) THEN objekt.aenderungsdatum
            ELSE '1970-01-01 00:00:00'::timestamp without time zone
        END AS aktual_dat,
        CASE
            WHEN (objekt.erfassungsdatum IS NOT NULL) THEN objekt.erfassungsdatum
            ELSE '1970-01-01 00:00:00'::timestamp without time zone
        END AS erstell_dat,
    'ELKA_KR711'::text AS herkunft
   FROM e_adresse,
    e_standort,
    basis.objekt,
    labor.messstelle
  WHERE (((((((objekt.standortid = e_standort.nr) AND (objekt.betreiberid = e_adresse.nr)) AND (messstelle.objektid = objekt.id)) AND (messstelle.art_id = 2)) AND (objekt._deleted = false)) AND (objekt.inaktiv = false)) AND (objekt.elkarelevant = true));


ALTER TABLE e_messstelle OWNER TO auikadmin;

SET search_path = labor, pg_catalog;

--
-- TOC entry 252 (class 1259 OID 5901952)
-- Name: probenahme; Type: TABLE; Schema: labor; Owner: auikadmin; Tablespace: 
--

CREATE TABLE probenahme (
    id integer NOT NULL,
    kennummer character varying(255),
    art character varying(255),
    datum_der_entnahme timestamp without time zone,
    zeit_anfang time without time zone,
    bis_datum timestamp without time zone,
    zeit_der_entnahmen character varying(255),
    einwaage real,
    v_m3 character varying(255),
    fahrer character varying(255),
    objekt_nr character varying(255),
    datum_icp timestamp without time zone,
    sonderparameter character varying(255),
    bemerkung character varying(255),
    an_360x11 timestamp without time zone,
    ueberschreitung character varying(255),
    anzahlbeteiligte integer,
    uhrzeitbeginn character varying(255),
    uhrzeitende character varying(255),
    fahrtzeit character varying(255),
    bescheid timestamp without time zone,
    kosten double precision,
    massnahmen character varying(255),
    bezeichnung character varying(255),
    messstid integer,
    status integer,
    uschivorg integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    qb_ausschliessen boolean DEFAULT false,
    sachbearbeiter integer
);


ALTER TABLE probenahme OWNER TO auikadmin;

SET search_path = elka, pg_catalog;

--
-- TOC entry 349 (class 1259 OID 5903823)
-- Name: e_probenahme; Type: VIEW; Schema: elka; Owner: auikadmin
--

CREATE VIEW e_probenahme AS
 SELECT probenahme.id AS nr,
    e_messstelle.nr AS msst_nr,
    probenahme.bezeichnung AS beschreibung,
    probenahme.datum_der_entnahme AS probe_dat,
    probenahme.kennummer AS probe_id,
    probenahme.zeit_der_entnahmen AS uhr_stich,
        CASE
            WHEN ((probenahme.kennummer)::text ~~ '2%'::text) THEN true
            ELSE false
        END AS selbstueberw_tog,
    probenahme.bemerkung,
    probenahme.datum_der_entnahme AS aktual_dat,
    probenahme.datum_der_entnahme AS erstell_dat,
    'ELKA_KR711'::text AS herkunft
   FROM labor.probenahme,
    e_messstelle
  WHERE (e_messstelle.nr = probenahme.messstid);


ALTER TABLE e_probenahme OWNER TO auikadmin;

--
-- TOC entry 253 (class 1259 OID 5901966)
-- Name: map_elka_einheit; Type: TABLE; Schema: elka; Owner: auikadmin; Tablespace: 
--

CREATE TABLE map_elka_einheit (
    id integer NOT NULL,
    name character varying,
    id_elka integer,
    id_auik integer
);


ALTER TABLE map_elka_einheit OWNER TO auikadmin;

--
-- TOC entry 254 (class 1259 OID 5901972)
-- Name: map_elka_stoff; Type: TABLE; Schema: elka; Owner: auikadmin; Tablespace: 
--

CREATE TABLE map_elka_stoff (
    id integer NOT NULL,
    name character varying,
    id_elka integer,
    id_auik character varying
);


ALTER TABLE map_elka_stoff OWNER TO auikadmin;

SET search_path = labor, pg_catalog;

--
-- TOC entry 255 (class 1259 OID 5901978)
-- Name: analyseposition; Type: TABLE; Schema: labor; Owner: auikadmin; Tablespace: 
--

CREATE TABLE analyseposition (
    id integer NOT NULL,
    grkl character varying(255),
    wert real DEFAULT 0 NOT NULL,
    analyse_von character varying(255),
    bericht character varying(255),
    normwert double precision,
    einheiten_id integer,
    parameter_id character varying(255) NOT NULL,
    probenahme_id integer NOT NULL,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    normwert_neu double precision
);


ALTER TABLE analyseposition OWNER TO auikadmin;

SET search_path = elka, pg_catalog;

--
-- TOC entry 350 (class 1259 OID 5903828)
-- Name: e_probenahme_ueberwachungsergeb; Type: VIEW; Schema: elka; Owner: auikadmin
--

CREATE VIEW e_probenahme_ueberwachungsergeb AS
 SELECT analyseposition.id AS nr,
    analyseposition.probenahme_id AS probenahme_nr,
    map_elka_stoff.id_elka AS stoff_nr,
    '000'::character varying(3) AS gruppe_dev_id,
    '00'::character varying(2) AS regelwerk_id,
    '0'::character varying(1) AS varianten_id,
    '0'::character varying(3) AS trenn_nr_opt,
        CASE
            WHEN ((analyseposition.grkl)::text ~~ '<'::text) THEN 1
            WHEN ((analyseposition.grkl)::text ~~ '>'::text) THEN 2
            WHEN ((analyseposition.grkl)::text ~~ '='::text) THEN 3
            ELSE NULL::integer
        END AS messergebnis_text_opt,
    round((analyseposition.wert)::numeric, 3) AS wert,
    map_elka_einheit.id_elka
   FROM e_probenahme,
    labor.analyseposition,
    map_elka_stoff,
    map_elka_einheit
  WHERE (((e_probenahme.nr = analyseposition.probenahme_id) AND (analyseposition.einheiten_id = map_elka_einheit.id_auik)) AND ((analyseposition.parameter_id)::text = (map_elka_stoff.id_auik)::text));


ALTER TABLE e_probenahme_ueberwachungsergeb OWNER TO auikadmin;

SET search_path = oberflgw, pg_catalog;

SET default_with_oids = true;

--
-- TOC entry 256 (class 1259 OID 5901992)
-- Name: sonderbauwerk; Type: TABLE; Schema: oberflgw; Owner: auikadmin; Tablespace: 
--

CREATE TABLE sonderbauwerk (
    nr bigint NOT NULL,
    objekt_id bigint NOT NULL,
    gemeinde_id character varying(8) NOT NULL,
    bezeichnung character varying(60),
    kurzbeschreibung character varying(60),
    entw_einzugsgeb_opt integer,
    typ_opt integer NOT NULL,
    inbetriebnahme integer,
    stillgelegt_am date,
    wiederinbetr_dat date,
    anspr_adr_nr bigint,
    bemerkung character varying(1000),
    e32 integer NOT NULL,
    n32 integer NOT NULL,
    in_nrw_tog boolean DEFAULT false NOT NULL,
    industr_tog boolean DEFAULT false NOT NULL,
    name_ausserhalb_nrw character varying(30),
    k_entw_gebiet numeric(11,2),
    bef_flaeche_egebiet numeric(11,2),
    abflussbeiwert numeric(11,2),
    bef_grad numeric(11,2),
    undurch_flaeche numeric(11,2),
    beckentyp_opt integer,
    rohrspeicher_tog boolean DEFAULT false NOT NULL,
    bauweise_opt integer,
    drossel_opt integer,
    so_drossel character varying(80),
    beckensteuerung_opt integer,
    a_rei_einrich_tog boolean DEFAULT false NOT NULL,
    messart_tog boolean DEFAULT false NOT NULL,
    drossel_tog boolean DEFAULT false NOT NULL,
    fuellstand_tog boolean DEFAULT false NOT NULL,
    entlastungswasser_tog boolean DEFAULT false NOT NULL,
    entlastungsdauer_tog boolean DEFAULT false NOT NULL,
    entlastungshaeufig_tog boolean DEFAULT false NOT NULL,
    fern_mess_tog boolean DEFAULT false NOT NULL,
    niederschlag_tog boolean DEFAULT false NOT NULL,
    fern_stoer_tog boolean DEFAULT false NOT NULL,
    hwfrei_tog boolean DEFAULT false NOT NULL,
    einstau_haeufig numeric(11,2),
    hw_einrichtung_tog boolean DEFAULT false NOT NULL,
    hw_rueckstau_tog boolean DEFAULT false NOT NULL,
    hw_schieber_tog boolean DEFAULT false NOT NULL,
    hw_pumpwerk_tog boolean DEFAULT false NOT NULL,
    hw_weitere_tog boolean DEFAULT false NOT NULL,
    hw_sonst_text character varying(80),
    bemes_weiterg_tog boolean DEFAULT false NOT NULL,
    bemessung_text character varying(80),
    anordnung_opt integer,
    wasserrecht_genehmigung_nr bigint,
    beckenart_opt integer,
    beckentiefe numeric(10,1),
    beh_flaeche_1u2 numeric(11,2),
    beh_flaeche_2u3 numeric(11,2),
    betriebsart_opt integer,
    csb integer,
    drosselabfluss numeric(10,1),
    dross_abfluss_opt integer,
    dross_ueber_tog boolean DEFAULT false NOT NULL,
    entlastungsart_opt integer,
    entleerungszeit numeric(10,1),
    flaechenbeschickung numeric(10,1),
    fliesszeit integer,
    fremd_abfluss numeric(10,2),
    funktion_opt integer,
    kanal_vol integer,
    qrkrit numeric(11,2),
    kritisch_misch numeric(10,1),
    max_h_schmutzabfluss numeric(10,2),
    max_h_trocken numeric(10,1),
    min_dr_abfluss numeric(10,1),
    misch_ueberlauf integer,
    n_mindest_v integer,
    n_spez_vol integer,
    neigung numeric(11,2),
    rdrosseldurchfluss numeric(10,1),
    regenabfl_dross numeric(10,1),
    regenabfl_entl numeric(10,1),
    regenabfluss numeric(10,2),
    regenabfluss_dr numeric(10,1),
    regenspende numeric(10,1),
    rfilterflaeche integer,
    rfiltergeschwin numeric(12,3),
    rfiltersubstrat_h numeric(11,2),
    rhyd_wirkungsgrad integer,
    rjahr_ueh numeric(11,2),
    rkrit numeric(10,1),
    rm_filterbelastung numeric(10,1),
    rspez_filtervol numeric(11,2),
    rstauvolumen integer,
    rvol_slamelle integer,
    rw_krit_abfluss numeric(10,1),
    rw_krit_misch numeric(10,1),
    rw_mindest_misch integer,
    schmutz_abfluss numeric(10,2),
    sku_anstroem numeric(10,1),
    sku_mindest_svol integer,
    sku_spez_vol integer,
    spez_beckenvol integer,
    spez_speicher integer,
    trocken_w_abfluss numeric(10,1),
    speichervolumen integer,
    w_oberflaeche integer,
    erstell_dat timestamp without time zone NOT NULL,
    aktual_dat timestamp without time zone NOT NULL,
    external_nr character varying(50)
);


ALTER TABLE sonderbauwerk OWNER TO auikadmin;

SET search_path = elka, pg_catalog;

--
-- TOC entry 351 (class 1259 OID 5903833)
-- Name: e_sonderbauwerk; Type: VIEW; Schema: elka; Owner: auikadmin
--

CREATE VIEW e_sonderbauwerk AS
 SELECT sonderbauwerk.nr,
    sonderbauwerk.gemeinde_id,
    sonderbauwerk.bezeichnung,
    sonderbauwerk.kurzbeschreibung,
    sonderbauwerk.entw_einzugsgeb_opt,
    sonderbauwerk.typ_opt,
    sonderbauwerk.inbetriebnahme,
    sonderbauwerk.stillgelegt_am,
    sonderbauwerk.wiederinbetr_dat,
    objekt.standortid,
    objekt.betreiberid,
    sonderbauwerk.anspr_adr_nr,
    objekt.beschreibung,
    sonderbauwerk.e32,
    sonderbauwerk.n32,
    sonderbauwerk.in_nrw_tog,
    sonderbauwerk.industr_tog,
    sonderbauwerk.name_ausserhalb_nrw,
    sonderbauwerk.k_entw_gebiet,
    sonderbauwerk.bef_flaeche_egebiet,
    sonderbauwerk.abflussbeiwert,
    sonderbauwerk.bef_grad,
    sonderbauwerk.undurch_flaeche,
    sonderbauwerk.beckentyp_opt,
    sonderbauwerk.rohrspeicher_tog,
    sonderbauwerk.bauweise_opt,
    sonderbauwerk.drossel_opt,
    sonderbauwerk.so_drossel,
    sonderbauwerk.beckensteuerung_opt,
    sonderbauwerk.a_rei_einrich_tog,
    sonderbauwerk.messart_tog,
    sonderbauwerk.drossel_tog,
    sonderbauwerk.fuellstand_tog,
    sonderbauwerk.entlastungswasser_tog,
    sonderbauwerk.entlastungsdauer_tog,
    sonderbauwerk.entlastungshaeufig_tog,
    sonderbauwerk.fern_mess_tog,
    sonderbauwerk.niederschlag_tog,
    sonderbauwerk.fern_stoer_tog,
    sonderbauwerk.hwfrei_tog,
    sonderbauwerk.einstau_haeufig,
    sonderbauwerk.hw_einrichtung_tog,
    sonderbauwerk.hw_rueckstau_tog,
    sonderbauwerk.hw_schieber_tog,
    sonderbauwerk.hw_pumpwerk_tog,
    sonderbauwerk.hw_weitere_tog,
    sonderbauwerk.hw_sonst_text,
    sonderbauwerk.bemes_weiterg_tog,
    sonderbauwerk.bemessung_text,
    sonderbauwerk.anordnung_opt,
    sonderbauwerk.wasserrecht_genehmigung_nr,
    sonderbauwerk.beckenart_opt,
    sonderbauwerk.beckentiefe,
    sonderbauwerk.beh_flaeche_1u2,
    sonderbauwerk.beh_flaeche_2u3,
    sonderbauwerk.betriebsart_opt,
    sonderbauwerk.csb,
    sonderbauwerk.drosselabfluss,
    sonderbauwerk.dross_abfluss_opt,
    sonderbauwerk.dross_ueber_tog,
    sonderbauwerk.entlastungsart_opt,
    sonderbauwerk.entleerungszeit,
    sonderbauwerk.flaechenbeschickung,
    sonderbauwerk.fliesszeit,
    sonderbauwerk.fremd_abfluss,
    sonderbauwerk.funktion_opt,
    sonderbauwerk.kanal_vol,
    sonderbauwerk.qrkrit,
    sonderbauwerk.kritisch_misch,
    sonderbauwerk.max_h_schmutzabfluss,
    sonderbauwerk.max_h_trocken,
    sonderbauwerk.min_dr_abfluss,
    sonderbauwerk.misch_ueberlauf,
    sonderbauwerk.n_mindest_v,
    sonderbauwerk.n_spez_vol,
    sonderbauwerk.neigung,
    sonderbauwerk.rdrosseldurchfluss,
    sonderbauwerk.regenabfl_dross,
    sonderbauwerk.regenabfl_entl,
    sonderbauwerk.regenabfluss,
    sonderbauwerk.regenabfluss_dr,
    sonderbauwerk.regenspende,
    sonderbauwerk.rfilterflaeche,
    sonderbauwerk.rfiltergeschwin,
    sonderbauwerk.rfiltersubstrat_h,
    sonderbauwerk.rhyd_wirkungsgrad,
    sonderbauwerk.rjahr_ueh,
    sonderbauwerk.rkrit,
    sonderbauwerk.rm_filterbelastung,
    sonderbauwerk.rspez_filtervol,
    sonderbauwerk.rstauvolumen,
    sonderbauwerk.rvol_slamelle,
    sonderbauwerk.rw_krit_abfluss,
    sonderbauwerk.rw_krit_misch,
    sonderbauwerk.rw_mindest_misch,
    sonderbauwerk.schmutz_abfluss,
    sonderbauwerk.sku_anstroem,
    sonderbauwerk.sku_mindest_svol,
    sonderbauwerk.sku_spez_vol,
    sonderbauwerk.spez_beckenvol,
    sonderbauwerk.spez_speicher,
    sonderbauwerk.trocken_w_abfluss,
    sonderbauwerk.speichervolumen,
    sonderbauwerk.w_oberflaeche,
    sonderbauwerk.erstell_dat,
    sonderbauwerk.aktual_dat,
    'ELKA_KR711'::text AS herkunft,
    sonderbauwerk.external_nr
   FROM e_adresse,
    e_standort,
    basis.objekt,
    oberflgw.sonderbauwerk
  WHERE ((((objekt.standortid = e_standort.nr) AND (objekt.betreiberid = e_adresse.nr)) AND (sonderbauwerk.objekt_id = objekt.id)) AND (objekt._deleted = false));


ALTER TABLE e_sonderbauwerk OWNER TO auikadmin;

--
-- TOC entry 345 (class 1259 OID 5903804)
-- Name: e_wasserrecht; Type: VIEW; Schema: elka; Owner: auikadmin
--

CREATE VIEW e_wasserrecht AS
 SELECT wasserrecht.objektid AS nr,
    objekt.betreiberid AS adresse,
    objekt.id AS ref_nr,
    objekt.id AS aktenzeichen,
        CASE
            WHEN (wasserrecht.gen58 = true) THEN 3
            WHEN (wasserrecht.gen59 = true) THEN 4
            WHEN (wasserrecht.gen8 = true) THEN 1
            ELSE NULL::integer
        END AS recht_art_opt,
    wasserrecht.antrag_datum AS antrag_dat,
    wasserrecht.erstellungs_datum AS erteilung_wr_dat,
        CASE
            WHEN (wasserrecht.befristet IS NOT NULL) THEN wasserrecht.befristet
            ELSE false
        END AS befristung_tog,
    wasserrecht.befristet_bis AS befristung_dat,
    wasserrecht.bemerkungen AS bemerkung,
        CASE
            WHEN (wasserrecht.aenderungs_datum IS NOT NULL) THEN (wasserrecht.aenderungs_datum)::timestamp without time zone
            ELSE ('now'::text)::timestamp without time zone
        END AS aktual_dat,
        CASE
            WHEN (wasserrecht.erstellungs_datum IS NOT NULL) THEN (wasserrecht.erstellungs_datum)::timestamp without time zone
            ELSE ('1970-01-01 00:00:00'::text)::timestamp without time zone
        END AS erstell_dat,
    'ELKA_KR711'::text AS herkunft
   FROM e_adresse,
    e_standort,
    basis.objekt,
    wasserrecht
  WHERE ((((objekt.standortid = e_standort.nr) AND (objekt.betreiberid = e_adresse.nr)) AND (wasserrecht.objektid = objekt.id)) AND (objekt._deleted = false));


ALTER TABLE e_wasserrecht OWNER TO auikadmin;

--
-- TOC entry 257 (class 1259 OID 5902029)
-- Name: z_aba_verfahren; Type: TABLE; Schema: elka; Owner: auikadmin; Tablespace: 
--

CREATE TABLE z_aba_verfahren (
    id integer NOT NULL,
    anlage_nr bigint NOT NULL,
    abwasbehverf_nr bigint NOT NULL
);


ALTER TABLE z_aba_verfahren OWNER TO auikadmin;

--
-- TOC entry 258 (class 1259 OID 5902032)
-- Name: e_z_aba_verfahren; Type: VIEW; Schema: elka; Owner: auikadmin
--

CREATE VIEW e_z_aba_verfahren AS
 SELECT aba.objektid AS anlage_nr,
    z_aba_verfahren.abwasbehverf_nr
   FROM z_aba_verfahren,
    aba
  WHERE (z_aba_verfahren.anlage_nr = aba.id);


ALTER TABLE e_z_aba_verfahren OWNER TO auikadmin;

--
-- TOC entry 347 (class 1259 OID 5903813)
-- Name: e_z_aba_wasserrecht; Type: VIEW; Schema: elka; Owner: auikadmin
--

CREATE VIEW e_z_aba_wasserrecht AS
 SELECT e_abwasserbehandlungsanlage.nr AS anlage_nr,
    e_wasserrecht.nr AS wasserrecht_nr
   FROM ((e_abwasserbehandlungsanlage
     JOIN _q_z ON ((e_abwasserbehandlungsanlage.nr = _q_z.quelle)))
     JOIN e_wasserrecht ON ((_q_z.ziel = e_wasserrecht.nr)));


ALTER TABLE e_z_aba_wasserrecht OWNER TO auikadmin;

--
-- TOC entry 346 (class 1259 OID 5903809)
-- Name: e_z_els_wasserrecht; Type: VIEW; Schema: elka; Owner: auikadmin
--

CREATE VIEW e_z_els_wasserrecht AS
 SELECT e_einleitungsstelle.nr AS els_nr,
    e_wasserrecht.nr AS wasserrecht_nr
   FROM ((e_einleitungsstelle
     JOIN _q_z ON ((e_einleitungsstelle.nr = _q_z.quelle)))
     JOIN e_wasserrecht ON ((_q_z.ziel = e_wasserrecht.nr)));


ALTER TABLE e_z_els_wasserrecht OWNER TO auikadmin;

--
-- TOC entry 259 (class 1259 OID 5902045)
-- Name: elka_aba_id_seq; Type: SEQUENCE; Schema: elka; Owner: auikadmin
--

CREATE SEQUENCE elka_aba_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE elka_aba_id_seq OWNER TO auikadmin;

--
-- TOC entry 4880 (class 0 OID 0)
-- Dependencies: 259
-- Name: elka_aba_id_seq; Type: SEQUENCE OWNED BY; Schema: elka; Owner: auikadmin
--

ALTER SEQUENCE elka_aba_id_seq OWNED BY aba.id;


--
-- TOC entry 260 (class 1259 OID 5902047)
-- Name: elka_anfallstelle_id_seq; Type: SEQUENCE; Schema: elka; Owner: auikadmin
--

CREATE SEQUENCE elka_anfallstelle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE elka_anfallstelle_id_seq OWNER TO auikadmin;

--
-- TOC entry 4881 (class 0 OID 0)
-- Dependencies: 260
-- Name: elka_anfallstelle_id_seq; Type: SEQUENCE OWNED BY; Schema: elka; Owner: auikadmin
--

ALTER SEQUENCE elka_anfallstelle_id_seq OWNED BY anfallstelle.id;


--
-- TOC entry 261 (class 1259 OID 5902049)
-- Name: elka_einleitungsstelle_id_seq; Type: SEQUENCE; Schema: elka; Owner: auikadmin
--

CREATE SEQUENCE elka_einleitungsstelle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE elka_einleitungsstelle_id_seq OWNER TO auikadmin;

--
-- TOC entry 4882 (class 0 OID 0)
-- Dependencies: 261
-- Name: elka_einleitungsstelle_id_seq; Type: SEQUENCE OWNED BY; Schema: elka; Owner: auikadmin
--

ALTER SEQUENCE elka_einleitungsstelle_id_seq OWNED BY einleitungsstelle.id;


--
-- TOC entry 262 (class 1259 OID 5902051)
-- Name: referenz; Type: TABLE; Schema: elka; Owner: auikadmin; Tablespace: 
--

CREATE TABLE referenz (
    nr integer NOT NULL,
    fs_tog boolean,
    standort_nr integer,
    q_aba_nr integer,
    q_els_nr integer,
    q_afs_nr integer,
    q_msst_nr integer,
    q_sb_nr integer,
    q_nw_afs_nr integer,
    q_entl_nr integer,
    q_kka_nr integer,
    q_ka_nr integer,
    z_aba_nr integer,
    z_els_nr integer,
    z_afs_nr integer,
    z_msst_nr integer,
    z_sb_nr integer,
    z_nw_afs_nr integer,
    z_entl_nr integer,
    z_kka_nr integer,
    z_ka_nr integer,
    aktual_dat date,
    erstell_dat date
);


ALTER TABLE referenz OWNER TO auikadmin;

--
-- TOC entry 263 (class 1259 OID 5902054)
-- Name: elka_referenz_nr_seq; Type: SEQUENCE; Schema: elka; Owner: auikadmin
--

CREATE SEQUENCE elka_referenz_nr_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE elka_referenz_nr_seq OWNER TO auikadmin;

--
-- TOC entry 4883 (class 0 OID 0)
-- Dependencies: 263
-- Name: elka_referenz_nr_seq; Type: SEQUENCE OWNED BY; Schema: elka; Owner: auikadmin
--

ALTER SEQUENCE elka_referenz_nr_seq OWNED BY referenz.nr;


--
-- TOC entry 264 (class 1259 OID 5902056)
-- Name: elka_wasserrecht_id_seq; Type: SEQUENCE; Schema: elka; Owner: auikadmin
--

CREATE SEQUENCE elka_wasserrecht_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE elka_wasserrecht_id_seq OWNER TO auikadmin;

--
-- TOC entry 4885 (class 0 OID 0)
-- Dependencies: 264
-- Name: elka_wasserrecht_id_seq; Type: SEQUENCE OWNED BY; Schema: elka; Owner: auikadmin
--

ALTER SEQUENCE elka_wasserrecht_id_seq OWNED BY wasserrecht.id;


--
-- TOC entry 265 (class 1259 OID 5902058)
-- Name: map_elka_anhang; Type: TABLE; Schema: elka; Owner: auikadmin; Tablespace: 
--

CREATE TABLE map_elka_anhang (
    id integer NOT NULL,
    anhang_auik integer,
    id_elka character varying
);


ALTER TABLE map_elka_anhang OWNER TO auikadmin;

--
-- TOC entry 266 (class 1259 OID 5902064)
-- Name: z_aba_verfahren_id_seq; Type: SEQUENCE; Schema: elka; Owner: auikadmin
--

CREATE SEQUENCE z_aba_verfahren_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE z_aba_verfahren_id_seq OWNER TO auikadmin;

--
-- TOC entry 4888 (class 0 OID 0)
-- Dependencies: 266
-- Name: z_aba_verfahren_id_seq; Type: SEQUENCE OWNED BY; Schema: elka; Owner: auikadmin
--

ALTER SEQUENCE z_aba_verfahren_id_seq OWNED BY z_aba_verfahren.id;


SET search_path = indeinl, pg_catalog;

SET default_with_oids = false;

--
-- TOC entry 267 (class 1259 OID 5902304)
-- Name: anh_40_fachdaten; Type: TABLE; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

CREATE TABLE anh_40_fachdaten (
    bemerkungen character varying(255),
    ansprechpartner character varying(255),
    sachbearbeiterrav character varying(255),
    sachbearbeiterheepen character varying(255),
    klaeranlage character varying(255),
    herkunftsbereich character varying(255),
    wsg boolean,
    prioritaet smallint,
    genehmigungspflicht boolean,
    nachtrag boolean,
    bimsch boolean,
    abwmengegenehmigt integer,
    abwmengeprodspez integer,
    abwmengegesamt integer,
    gen58 timestamp without time zone,
    gen59 timestamp without time zone,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer NOT NULL,
    anfallstelleid integer
);


ALTER TABLE anh_40_fachdaten OWNER TO auikadmin;

--
-- TOC entry 268 (class 1259 OID 5902312)
-- Name: anh_40_fachdaten_id_seq; Type: SEQUENCE; Schema: indeinl; Owner: auikadmin
--

CREATE SEQUENCE anh_40_fachdaten_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE anh_40_fachdaten_id_seq OWNER TO auikadmin;

--
-- TOC entry 4889 (class 0 OID 0)
-- Dependencies: 268
-- Name: anh_40_fachdaten_id_seq; Type: SEQUENCE OWNED BY; Schema: indeinl; Owner: auikadmin
--

ALTER SEQUENCE anh_40_fachdaten_id_seq OWNED BY anh_40_fachdaten.id;


--
-- TOC entry 269 (class 1259 OID 5902314)
-- Name: anh_49_abfuhr; Type: TABLE; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

CREATE TABLE anh_49_abfuhr (
    id integer NOT NULL,
    abfuhrdatum date,
    naechsteabfuhr date,
    entsorger character varying,
    anh49id integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    menge double precision
);


ALTER TABLE anh_49_abfuhr OWNER TO auikadmin;

--
-- TOC entry 270 (class 1259 OID 5902322)
-- Name: anh_49_abfuhr_id_seq; Type: SEQUENCE; Schema: indeinl; Owner: auikadmin
--

CREATE SEQUENCE anh_49_abfuhr_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE anh_49_abfuhr_id_seq OWNER TO auikadmin;

--
-- TOC entry 4891 (class 0 OID 0)
-- Dependencies: 270
-- Name: anh_49_abfuhr_id_seq; Type: SEQUENCE OWNED BY; Schema: indeinl; Owner: auikadmin
--

ALTER SEQUENCE anh_49_abfuhr_id_seq OWNED BY anh_49_abfuhr.id;


--
-- TOC entry 271 (class 1259 OID 5902324)
-- Name: anh_49_abscheiderdetails; Type: TABLE; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

CREATE TABLE anh_49_abscheiderdetails (
    id integer NOT NULL,
    abscheidernr integer,
    von integer,
    lage character varying(255),
    nenngroesse integer,
    rueckhalt boolean DEFAULT false,
    schlammfang boolean DEFAULT false,
    benzinabscheider boolean DEFAULT false,
    koaabscheider boolean DEFAULT false,
    kompakt boolean DEFAULT false,
    emulsionsspaltanlage boolean DEFAULT false,
    vsf2 integer,
    schwimmer boolean DEFAULT false,
    warnanlage boolean,
    hersteller character varying(255),
    bemerkung character varying(255),
    anh49id integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    vsf1 integer,
    typ character varying(255),
    kreis boolean DEFAULT false,
    vorschlamm boolean DEFAULT false,
    pn_stelle boolean DEFAULT false
);


ALTER TABLE anh_49_abscheiderdetails OWNER TO auikadmin;

--
-- TOC entry 272 (class 1259 OID 5902340)
-- Name: anh_49_abscheiderdetails_id_seq; Type: SEQUENCE; Schema: indeinl; Owner: auikadmin
--

CREATE SEQUENCE anh_49_abscheiderdetails_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE anh_49_abscheiderdetails_id_seq OWNER TO auikadmin;

--
-- TOC entry 4893 (class 0 OID 0)
-- Dependencies: 272
-- Name: anh_49_abscheiderdetails_id_seq; Type: SEQUENCE OWNED BY; Schema: indeinl; Owner: auikadmin
--

ALTER SEQUENCE anh_49_abscheiderdetails_id_seq OWNED BY anh_49_abscheiderdetails.id;


--
-- TOC entry 273 (class 1259 OID 5902342)
-- Name: anh_49_analysen; Type: TABLE; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

CREATE TABLE anh_49_analysen (
    id integer NOT NULL,
    datum timestamp without time zone,
    institut character varying(255),
    csb_wert character varying(255),
    csb_grenzwert character varying(255),
    aox_wert character varying(255),
    aox_grenzwert character varying(255),
    kw_wert character varying(255),
    kw_grenzwert character varying(255),
    zink_wert character varying(255),
    zink_grenzwert character varying(255),
    bsb5_wert character varying(255),
    bsb5_csb_relation character varying(255),
    bemerkungen character varying(255),
    bik_grenzwert character varying(255),
    bik_wert character varying(255),
    ph_wert character varying(255),
    ph_grenzwert character varying(255),
    anh49id integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE anh_49_analysen OWNER TO auikadmin;

--
-- TOC entry 274 (class 1259 OID 5902350)
-- Name: anh_49_analysen_id_seq; Type: SEQUENCE; Schema: indeinl; Owner: auikadmin
--

CREATE SEQUENCE anh_49_analysen_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE anh_49_analysen_id_seq OWNER TO auikadmin;

--
-- TOC entry 4894 (class 0 OID 0)
-- Dependencies: 274
-- Name: anh_49_analysen_id_seq; Type: SEQUENCE OWNED BY; Schema: indeinl; Owner: auikadmin
--

ALTER SEQUENCE anh_49_analysen_id_seq OWNED BY anh_49_analysen.id;


--
-- TOC entry 275 (class 1259 OID 5902352)
-- Name: anh_49_fachdaten; Type: TABLE; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

CREATE TABLE anh_49_fachdaten (
    klaeranlage character varying(255),
    name character varying(255),
    bemerkungen character varying(255),
    planquadrat character varying(255),
    abgemeldet boolean,
    technik_anh49 character varying(255),
    technik_anh49_nr character varying(255),
    sachkundelfa character varying(255),
    werkstatt boolean,
    bodeneinlaeufe boolean,
    waschanlagen boolean,
    sonstiges character varying(255),
    analysemonat character varying(255),
    abwasserfrei boolean,
    anredeantragst character varying(255),
    nameantragst character varying(255),
    zusantragst character varying(255),
    strasseantragst character varying(255),
    hausnrantragst integer,
    hausnrzusantragst character varying(255),
    plzantragst character varying(255),
    ortantragst character varying(255),
    sachbearbeiter_in character varying(255),
    ansprechpartner_in character varying(255),
    antragvom timestamp without time zone,
    genehmigung timestamp without time zone,
    wiedervorlage timestamp without time zone,
    aenderungsgenehmigung timestamp without time zone,
    letztes_anschreiben timestamp without time zone,
    anschreiben character varying(255),
    waschanlage boolean,
    e_satzung boolean,
    seitwann timestamp without time zone,
    sonstigestechnik character varying(255),
    maengel boolean,
    behoben boolean,
    frist timestamp without time zone,
    durchgefuehrt integer,
    dekra_tuev_datum timestamp without time zone,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer NOT NULL,
    anfallstelleid integer,
    sicherheitsabscheider boolean
);


ALTER TABLE anh_49_fachdaten OWNER TO auikadmin;

--
-- TOC entry 276 (class 1259 OID 5902360)
-- Name: anh_49_fachdaten_id_seq; Type: SEQUENCE; Schema: indeinl; Owner: auikadmin
--

CREATE SEQUENCE anh_49_fachdaten_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE anh_49_fachdaten_id_seq OWNER TO auikadmin;

--
-- TOC entry 4895 (class 0 OID 0)
-- Dependencies: 276
-- Name: anh_49_fachdaten_id_seq; Type: SEQUENCE OWNED BY; Schema: indeinl; Owner: auikadmin
--

ALTER SEQUENCE anh_49_fachdaten_id_seq OWNED BY anh_49_fachdaten.id;


--
-- TOC entry 277 (class 1259 OID 5902362)
-- Name: anh_49_kontrollen; Type: TABLE; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

CREATE TABLE anh_49_kontrollen (
    id integer NOT NULL,
    pruefdatum date,
    naechstepruefung date,
    pruefergebnis character varying,
    anh49id integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE anh_49_kontrollen OWNER TO auikadmin;

--
-- TOC entry 278 (class 1259 OID 5902370)
-- Name: anh_49_kontrollen_id_seq; Type: SEQUENCE; Schema: indeinl; Owner: auikadmin
--

CREATE SEQUENCE anh_49_kontrollen_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE anh_49_kontrollen_id_seq OWNER TO auikadmin;

--
-- TOC entry 4897 (class 0 OID 0)
-- Dependencies: 278
-- Name: anh_49_kontrollen_id_seq; Type: SEQUENCE OWNED BY; Schema: indeinl; Owner: auikadmin
--

ALTER SEQUENCE anh_49_kontrollen_id_seq OWNED BY anh_49_kontrollen.id;


--
-- TOC entry 279 (class 1259 OID 5902392)
-- Name: anh_50_fachdaten; Type: TABLE; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

CREATE TABLE anh_50_fachdaten (
    telefon character varying(255),
    erloschen boolean,
    datumantrag timestamp without time zone,
    bemerkungen character varying(255),
    genehmigung timestamp without time zone,
    wiedervorlage timestamp without time zone,
    gefaehrdungsklasse character varying(255),
    entsorgerid integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer NOT NULL,
    anfallstelleid integer
);


ALTER TABLE anh_50_fachdaten OWNER TO auikadmin;

--
-- TOC entry 280 (class 1259 OID 5902400)
-- Name: anh_50_fachdaten_id_seq; Type: SEQUENCE; Schema: indeinl; Owner: auikadmin
--

CREATE SEQUENCE anh_50_fachdaten_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE anh_50_fachdaten_id_seq OWNER TO auikadmin;

--
-- TOC entry 4899 (class 0 OID 0)
-- Dependencies: 280
-- Name: anh_50_fachdaten_id_seq; Type: SEQUENCE OWNED BY; Schema: indeinl; Owner: auikadmin
--

ALTER SEQUENCE anh_50_fachdaten_id_seq OWNED BY anh_50_fachdaten.id;


--
-- TOC entry 281 (class 1259 OID 5902402)
-- Name: anh_52_fachdaten; Type: TABLE; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

CREATE TABLE anh_52_fachdaten (
    nrbetriebsstaette integer,
    firmenname character varying(255),
    telefon character varying(255),
    telefax character varying(255),
    ansprechpartner character varying(255),
    datumgenehmigung timestamp without time zone,
    bemerkungen character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer NOT NULL,
    anfallstelleid integer
);


ALTER TABLE anh_52_fachdaten OWNER TO auikadmin;

--
-- TOC entry 282 (class 1259 OID 5902410)
-- Name: anh_52_fachdaten_id_seq; Type: SEQUENCE; Schema: indeinl; Owner: auikadmin
--

CREATE SEQUENCE anh_52_fachdaten_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE anh_52_fachdaten_id_seq OWNER TO auikadmin;

--
-- TOC entry 4901 (class 0 OID 0)
-- Dependencies: 282
-- Name: anh_52_fachdaten_id_seq; Type: SEQUENCE OWNED BY; Schema: indeinl; Owner: auikadmin
--

ALTER SEQUENCE anh_52_fachdaten_id_seq OWNED BY anh_52_fachdaten.id;


--
-- TOC entry 283 (class 1259 OID 5902412)
-- Name: anh_53_fachdaten; Type: TABLE; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

CREATE TABLE anh_53_fachdaten (
    branche character varying(255),
    verfahren character varying(255),
    antragsdatum timestamp without time zone,
    bagatell boolean,
    bagatelldatum timestamp without time zone,
    genehmigungsdatum timestamp without time zone,
    genehmigungaufgehoben timestamp without time zone,
    abnahmedatum timestamp without time zone,
    genehmigungstitel character varying(255),
    genehmigung boolean,
    durchsatz integer,
    gesamtmenge_eb integer,
    onlineentsilberung boolean,
    abwasser boolean,
    abwasserfrei timestamp without time zone,
    kleiner200qm timestamp without time zone,
    betrieb_abgemeldet boolean,
    bemerkungen character varying(255),
    betriebstagebuch boolean,
    wasseruhr boolean,
    spuelwassermenge integer,
    spuelwasserverbrauch integer,
    wartungsvertrag boolean,
    grgen boolean,
    genart character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer NOT NULL,
    anfallstelleid integer
);


ALTER TABLE anh_53_fachdaten OWNER TO auikadmin;

--
-- TOC entry 284 (class 1259 OID 5902420)
-- Name: anh_53_fachdaten_id_seq; Type: SEQUENCE; Schema: indeinl; Owner: auikadmin
--

CREATE SEQUENCE anh_53_fachdaten_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE anh_53_fachdaten_id_seq OWNER TO auikadmin;

--
-- TOC entry 4903 (class 0 OID 0)
-- Dependencies: 284
-- Name: anh_53_fachdaten_id_seq; Type: SEQUENCE OWNED BY; Schema: indeinl; Owner: auikadmin
--

ALTER SEQUENCE anh_53_fachdaten_id_seq OWNED BY anh_53_fachdaten.id;


--
-- TOC entry 285 (class 1259 OID 5902422)
-- Name: anh_55_fachdaten; Type: TABLE; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

CREATE TABLE anh_55_fachdaten (
    abgemeldet boolean,
    putztuecher boolean,
    teppich boolean,
    matten boolean,
    haushaltstex boolean,
    berufskl boolean,
    gaststhotel boolean,
    krankenhaus boolean,
    heimwaesche boolean,
    anteilwaschgut integer,
    vlies boolean,
    fischfleisch boolean,
    anteilgesamtgut integer,
    betrwasseraufber boolean,
    chlor boolean,
    aktivchlor boolean,
    sachbearbeiter character varying(255),
    entgeb_id character varying(255),
    bemerkungen character varying(255),
    mengewaesche character varying(255),
    sonsttex character varying(255),
    monatwasserverb character varying(255),
    waschsituation character varying(255),
    ansprechpartner character varying(255),
    branche character varying(255),
    loesungsmittel boolean,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer NOT NULL,
    anfallstelleid integer
);


ALTER TABLE anh_55_fachdaten OWNER TO auikadmin;

--
-- TOC entry 286 (class 1259 OID 5902430)
-- Name: anh_55_fachdaten_id_seq; Type: SEQUENCE; Schema: indeinl; Owner: auikadmin
--

CREATE SEQUENCE anh_55_fachdaten_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE anh_55_fachdaten_id_seq OWNER TO auikadmin;

--
-- TOC entry 4905 (class 0 OID 0)
-- Dependencies: 286
-- Name: anh_55_fachdaten_id_seq; Type: SEQUENCE OWNED BY; Schema: indeinl; Owner: auikadmin
--

ALTER SEQUENCE anh_55_fachdaten_id_seq OWNED BY anh_55_fachdaten.id;


--
-- TOC entry 287 (class 1259 OID 5902432)
-- Name: anh_56_fachdaten; Type: TABLE; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

CREATE TABLE anh_56_fachdaten (
    druckverfahren character varying(255),
    verbrauch character varying(255),
    sachbearbeiterrav character varying(255),
    sachbearbeiterheepen character varying(255),
    entsorgung character varying(255),
    abwasseranfall boolean,
    genpflicht boolean,
    aba boolean,
    gen_58 timestamp without time zone,
    gen_59 timestamp without time zone,
    bemerkungen character varying(255),
    abfallrechtlentsorg boolean,
    spuelwasser boolean,
    leimabwasser boolean,
    erfasstam timestamp without time zone,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer NOT NULL,
    anfallstelleid integer
);


ALTER TABLE anh_56_fachdaten OWNER TO auikadmin;

--
-- TOC entry 288 (class 1259 OID 5902440)
-- Name: anh_56_fachdaten_id_seq; Type: SEQUENCE; Schema: indeinl; Owner: auikadmin
--

CREATE SEQUENCE anh_56_fachdaten_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE anh_56_fachdaten_id_seq OWNER TO auikadmin;

--
-- TOC entry 4907 (class 0 OID 0)
-- Dependencies: 288
-- Name: anh_56_fachdaten_id_seq; Type: SEQUENCE OWNED BY; Schema: indeinl; Owner: auikadmin
--

ALTER SEQUENCE anh_56_fachdaten_id_seq OWNED BY anh_56_fachdaten.id;


--
-- TOC entry 289 (class 1259 OID 5902442)
-- Name: bwk_fachdaten; Type: TABLE; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

CREATE TABLE bwk_fachdaten (
    branche character varying(255),
    k_hersteller character varying(255),
    k_typ character varying(255),
    k_brennmittel character varying(255),
    k_leistung integer,
    datum_g timestamp without time zone,
    aba boolean,
    w_brenner character varying(255),
    w_waermetauscher character varying(255),
    w_abgasleitung character varying(255),
    w_kondensableitung character varying(255),
    abnahme character varying(255),
    bemerkungen character varying(255),
    anschreiben timestamp without time zone,
    erfassung integer,
    genehmigung boolean,
    genehmigungspflicht boolean,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer NOT NULL,
    anfallstelleid integer
);


ALTER TABLE bwk_fachdaten OWNER TO auikadmin;

--
-- TOC entry 290 (class 1259 OID 5902450)
-- Name: anh_bwk_fachdaten_id_seq; Type: SEQUENCE; Schema: indeinl; Owner: auikadmin
--

CREATE SEQUENCE anh_bwk_fachdaten_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE anh_bwk_fachdaten_id_seq OWNER TO auikadmin;

--
-- TOC entry 4909 (class 0 OID 0)
-- Dependencies: 290
-- Name: anh_bwk_fachdaten_id_seq; Type: SEQUENCE OWNED BY; Schema: indeinl; Owner: auikadmin
--

ALTER SEQUENCE anh_bwk_fachdaten_id_seq OWNED BY bwk_fachdaten.id;


--
-- TOC entry 291 (class 1259 OID 5902452)
-- Name: suev_fachdaten; Type: TABLE; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

CREATE TABLE suev_fachdaten (
    objektid integer NOT NULL,
    groesser_3ha boolean,
    vers_flaeche integer,
    suevkan_pflicht boolean,
    indirektsw boolean,
    indirektrw boolean,
    direktsw boolean,
    direktrw boolean,
    anzeige58 integer,
    sanierung_erfolgt boolean,
    sanierungskonzept boolean,
    keine_angaben boolean,
    dat_anzeige58 timestamp without time zone,
    dat_anschreiben timestamp without time zone,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer NOT NULL
);


ALTER TABLE suev_fachdaten OWNER TO auikadmin;

--
-- TOC entry 292 (class 1259 OID 5902457)
-- Name: anh_suev_fachdaten_id_seq; Type: SEQUENCE; Schema: indeinl; Owner: auikadmin
--

CREATE SEQUENCE anh_suev_fachdaten_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE anh_suev_fachdaten_id_seq OWNER TO auikadmin;

--
-- TOC entry 4911 (class 0 OID 0)
-- Dependencies: 292
-- Name: anh_suev_fachdaten_id_seq; Type: SEQUENCE OWNED BY; Schema: indeinl; Owner: auikadmin
--

ALTER SEQUENCE anh_suev_fachdaten_id_seq OWNED BY suev_fachdaten.id;


--
-- TOC entry 293 (class 1259 OID 5902459)
-- Name: entsorger; Type: TABLE; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

CREATE TABLE entsorger (
    id integer NOT NULL,
    entsorger character varying(255),
    strasse character varying(255),
    hausnr integer,
    plz character varying(255),
    ort character varying(255),
    ansprechpartner character varying(255),
    telefon character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE entsorger OWNER TO auikadmin;

SET search_path = labor, pg_catalog;

--
-- TOC entry 294 (class 1259 OID 5902472)
-- Name: einheiten; Type: TABLE; Schema: labor; Owner: auikadmin; Tablespace: 
--

CREATE TABLE einheiten (
    id integer NOT NULL,
    bezeichnung character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    dea_einheiten_masseinheiten_nr integer
);


ALTER TABLE einheiten OWNER TO auikadmin;

--
-- TOC entry 295 (class 1259 OID 5902477)
-- Name: parameter; Type: TABLE; Schema: labor; Owner: auikadmin; Tablespace: 
--

CREATE TABLE parameter (
    ordnungsbegriff character varying(255) NOT NULL,
    analyseverfahren character varying(255),
    bezeichnung character varying(255),
    wirdgemessenineinheit integer,
    grenzwert double precision,
    grenzwertname character varying(255),
    sielhaut_gw double precision,
    klaerschlamm_gw double precision,
    preisfueranalyse double precision,
    einzelnbeauftragbar boolean,
    kennzeichnung character varying(255),
    konservierung character varying(255),
    parametergruppe_id integer,
    reihenfolge integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    dea_stoffe_stoff_nr integer
);


ALTER TABLE parameter OWNER TO auikadmin;

SET search_path = labor, pg_catalog;

--
-- TOC entry 297 (class 1259 OID 5902490)
-- Name: atl_sielhaut_id_seq; Type: SEQUENCE; Schema: labor; Owner: auikadmin
--

CREATE SEQUENCE atl_sielhaut_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE atl_sielhaut_id_seq OWNER TO auikadmin;

--
-- TOC entry 298 (class 1259 OID 5902492)
-- Name: sielhaut; Type: TABLE; Schema: labor; Owner: auikadmin; Tablespace: 
--

CREATE TABLE sielhaut (
    bezeichnung character varying(255),
    haltungsnr character varying(255),
    alarmplannr character varying(255),
    entgeb character varying(255),
    e32 double precision,
    n32 double precision,
    lage character varying(255),
    bemerkungen character varying(255),
    twabfluss character varying(255),
    bsb character varying(255),
    ew character varying(255),
    gebiet character varying(255),
    p_sielhaut boolean,
    p_alarmplan boolean,
    p_nachprobe boolean,
    schlammprobe boolean,
    p_firmenprobe boolean,
    the_geom public.geometry,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer DEFAULT nextval('atl_sielhaut_id_seq'::regclass) NOT NULL,
    probepktid integer,
    CONSTRAINT enforce_dims_the_geom CHECK ((public.st_ndims(the_geom) = 2)),
    CONSTRAINT enforce_geotype_the_geom CHECK (((public.geometrytype(the_geom) = 'POINT'::text) OR (the_geom IS NULL))),
    CONSTRAINT enforce_srid_the_geom CHECK ((public.st_srid(the_geom) = 25832))
);


ALTER TABLE sielhaut OWNER TO auikadmin;

SET search_path = labor, pg_catalog;

--
-- TOC entry 300 (class 1259 OID 5902509)
-- Name: atl_analyseposition_id_seq; Type: SEQUENCE; Schema: labor; Owner: auikadmin
--

CREATE SEQUENCE atl_analyseposition_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE atl_analyseposition_id_seq OWNER TO auikadmin;

--
-- TOC entry 4914 (class 0 OID 0)
-- Dependencies: 300
-- Name: atl_analyseposition_id_seq; Type: SEQUENCE OWNED BY; Schema: labor; Owner: auikadmin
--

ALTER SEQUENCE atl_analyseposition_id_seq OWNED BY analyseposition.id;


--
-- TOC entry 301 (class 1259 OID 5902511)
-- Name: atl_probenahmen_id_seq; Type: SEQUENCE; Schema: labor; Owner: auikadmin
--

CREATE SEQUENCE atl_probenahmen_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE atl_probenahmen_id_seq OWNER TO auikadmin;

--
-- TOC entry 4915 (class 0 OID 0)
-- Dependencies: 301
-- Name: atl_probenahmen_id_seq; Type: SEQUENCE OWNED BY; Schema: labor; Owner: auikadmin
--

ALTER SEQUENCE atl_probenahmen_id_seq OWNED BY probenahme.id;


--
-- TOC entry 302 (class 1259 OID 5902513)
-- Name: atl_probepkt_id_seq; Type: SEQUENCE; Schema: labor; Owner: auikadmin
--

CREATE SEQUENCE atl_probepkt_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE atl_probepkt_id_seq OWNER TO auikadmin;

--
-- TOC entry 4916 (class 0 OID 0)
-- Dependencies: 302
-- Name: atl_probepkt_id_seq; Type: SEQUENCE OWNED BY; Schema: labor; Owner: auikadmin
--

ALTER SEQUENCE atl_probepkt_id_seq OWNED BY messstelle.id;


--
-- TOC entry 303 (class 1259 OID 5902515)
-- Name: klaeranlage; Type: TABLE; Schema: labor; Owner: auikadmin; Tablespace: 
--

CREATE TABLE klaeranlage (
    id integer NOT NULL,
    anlage character varying(255) NOT NULL,
    dea_klaeranlage_nr integer DEFAULT 0 NOT NULL,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE klaeranlage OWNER TO auikadmin;

--
-- TOC entry 4918 (class 0 OID 0)
-- Dependencies: 303
-- Name: COLUMN klaeranlage.id; Type: COMMENT; Schema: labor; Owner: auikadmin
--

COMMENT ON COLUMN klaeranlage.id IS 'Entwässerungsgebiet';


--
-- TOC entry 304 (class 1259 OID 5902521)
-- Name: meta_parameter; Type: TABLE; Schema: labor; Owner: auikadmin; Tablespace: 
--

CREATE TABLE meta_parameter (
    id integer NOT NULL,
    ordnungsbegriff character varying(255),
    einheit_id integer,
    dea_regelwerk_nr character varying(2),
    dea_gruppe_dev character varying(3),
    dea_varianten_nr character varying(1),
    dea_trenn_nr_opt integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    ueberwachungswert real
);


ALTER TABLE meta_parameter OWNER TO auikadmin;

--
-- TOC entry 305 (class 1259 OID 5902526)
-- Name: parametergruppen; Type: TABLE; Schema: labor; Owner: auikadmin; Tablespace: 
--

CREATE TABLE parametergruppen (
    id integer NOT NULL,
    name character varying(255),
    preisfueranalyse double precision,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE parametergruppen OWNER TO auikadmin;

--
-- TOC entry 306 (class 1259 OID 5902531)
-- Name: probeart; Type: TABLE; Schema: labor; Owner: auikadmin; Tablespace: 
--

CREATE TABLE probeart (
    id integer NOT NULL,
    art character varying(255) NOT NULL,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE probeart OWNER TO auikadmin;

--
-- TOC entry 307 (class 1259 OID 5902536)
-- Name: status; Type: TABLE; Schema: labor; Owner: auikadmin; Tablespace: 
--

CREATE TABLE status (
    id integer NOT NULL,
    bezeichnung character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE status OWNER TO auikadmin;

--
-- TOC entry 308 (class 1259 OID 5902541)
-- Name: view_atl_analyseposition_all; Type: VIEW; Schema: labor; Owner: auikadmin
--

CREATE VIEW view_atl_analyseposition_all AS
 SELECT atl_analyseposition.id,
    atl_analyseposition.grkl,
    atl_analyseposition.wert,
    atl_analyseposition.analyse_von,
    atl_analyseposition.bericht,
    atl_analyseposition.normwert,
    atl_analyseposition.einheiten_id,
    atl_analyseposition.parameter_id,
    atl_analyseposition.probenahme_id,
    atl_analyseposition._enabled,
    atl_analyseposition._deleted,
    atl_probenahmen.datum_der_entnahme,
    atl_probenahmen.messstid AS probepkt_id,
    atl_probenahmen.kennummer
   FROM ((analyseposition atl_analyseposition
     LEFT JOIN probenahme atl_probenahmen ON ((atl_analyseposition.probenahme_id = atl_probenahmen.id)))
     LEFT JOIN messstelle atl_probepkt ON ((atl_probenahmen.messstid = atl_probepkt.id)))
UNION
 SELECT (- bsb.id) AS id,
    NULL::character varying(255) AS grkl,
    (bsb.wert / csb.wert) AS wert,
    bsb.analyse_von,
    bsb.bericht,
    NULL::double precision AS normwert,
    0 AS einheiten_id,
    'P00011'::character varying(255) AS parameter_id,
    bsb.probenahme_id,
    bsb._enabled,
    bsb._deleted,
    atl_probenahmen.datum_der_entnahme,
    atl_probenahmen.messstid AS probepkt_id,
    atl_probenahmen.kennummer
   FROM (((analyseposition bsb
     JOIN analyseposition csb ON (((((((bsb.probenahme_id = csb.probenahme_id) AND ((bsb.parameter_id)::text = 'L16250'::text)) AND ((csb.parameter_id)::text = 'L15330'::text)) AND (bsb.wert IS NOT NULL)) AND (csb.wert IS NOT NULL)) AND (csb.wert <> (0)::double precision))))
     LEFT JOIN probenahme atl_probenahmen ON ((csb.probenahme_id = atl_probenahmen.id)))
     LEFT JOIN messstelle atl_probepkt ON ((atl_probenahmen.messstid = atl_probepkt.id)))
UNION
 SELECT (- doc.id) AS id,
    NULL::character varying(255) AS grkl,
    (((doc.wert * menge.wert) / (1000)::double precision))::real AS wert,
    doc.analyse_von,
    doc.bericht,
    NULL::double precision AS normwert,
    22 AS einheiten_id,
    'P00015'::character varying(255) AS parameter_id,
    doc.probenahme_id,
    doc._enabled,
    doc._deleted,
    atl_probenahmen.datum_der_entnahme,
    atl_probenahmen.messstid AS probepkt_id,
    atl_probenahmen.kennummer
   FROM (((analyseposition menge
     JOIN analyseposition doc ON ((((((menge.probenahme_id = doc.probenahme_id) AND ((menge.parameter_id)::text = 'P00013'::text)) AND ((doc.parameter_id)::text = 'L15210'::text)) AND (menge.wert IS NOT NULL)) AND (doc.wert IS NOT NULL))))
     LEFT JOIN probenahme atl_probenahmen ON ((doc.probenahme_id = atl_probenahmen.id)))
     LEFT JOIN messstelle atl_probepkt ON ((atl_probenahmen.messstid = atl_probepkt.id)))
UNION
 SELECT min((- toc.id)) AS id,
    NULL::character varying(255) AS grkl,
    (sum(((toc.wert * menge.wert) / (1000)::double precision)))::real AS wert,
    (max((toc.analyse_von)::text))::character varying(255) AS analyse_von,
    (max((toc.bericht)::text))::character varying(255) AS bericht,
    NULL::double precision AS normwert,
    22 AS einheiten_id,
    'P00014'::character varying(255) AS parameter_id,
    min(toc.probenahme_id) AS probenahme_id,
    every(toc._enabled) AS _enabled,
    every(toc._deleted) AS _deleted,
    p.datum_der_entnahme,
    min(p.objektid) AS probepkt_id,
    (min((p.kennummer)::text))::character varying(255) AS kennummer
   FROM (((analyseposition menge
     JOIN analyseposition toc ON ((((((menge.probenahme_id = toc.probenahme_id) AND ((menge.parameter_id)::text = 'P00013'::text)) AND ((toc.parameter_id)::text = 'L15230'::text)) AND (menge.wert IS NOT NULL)) AND (toc.wert IS NOT NULL))))
     JOIN ( SELECT atl_probenahmen.id,
            atl_probenahmen.kennummer,
            atl_probenahmen.art,
            atl_probenahmen.datum_der_entnahme,
            atl_probenahmen.zeit_anfang,
            atl_probenahmen.bis_datum,
            atl_probenahmen.zeit_der_entnahmen,
            atl_probenahmen.einwaage,
            atl_probenahmen.v_m3,
            atl_probenahmen.fahrer,
            atl_probenahmen.objekt_nr,
            atl_probenahmen.datum_icp,
            atl_probenahmen.sonderparameter,
            atl_probenahmen.bemerkung,
            atl_probenahmen.an_360x11,
            atl_probenahmen.ueberschreitung,
            atl_probenahmen.anzahlbeteiligte,
            atl_probenahmen.uhrzeitbeginn,
            atl_probenahmen.uhrzeitende,
            atl_probenahmen.fahrtzeit,
            atl_probenahmen.bescheid,
            atl_probenahmen.kosten,
            atl_probenahmen.massnahmen,
            atl_probenahmen.bezeichnung,
            atl_probenahmen.messstid AS objektid,
            atl_probenahmen.status,
            atl_probenahmen.uschivorg,
            atl_probenahmen._enabled,
            atl_probenahmen._deleted,
            atl_probenahmen.qb_ausschliessen,
            atl_probenahmen.sachbearbeiter
           FROM probenahme atl_probenahmen
          WHERE (atl_probenahmen._deleted = false)) p ON ((toc.probenahme_id = p.id)))
     JOIN messstelle atl_probepkt ON ((p.objektid = atl_probepkt.id)))
  GROUP BY p.datum_der_entnahme;


ALTER TABLE view_atl_analyseposition_all OWNER TO auikadmin;

--
-- TOC entry 309 (class 1259 OID 5902546)
-- Name: view_atl_analyseposition_alt; Type: VIEW; Schema: labor; Owner: auikadmin
--

CREATE VIEW view_atl_analyseposition_alt AS
 SELECT atl_analyseposition.id,
    atl_analyseposition.grkl,
    atl_analyseposition.wert,
    atl_analyseposition.analyse_von,
    atl_analyseposition.bericht,
    atl_analyseposition.normwert,
    atl_analyseposition.einheiten_id,
    atl_analyseposition.parameter_id,
    atl_analyseposition.probenahme_id,
    atl_analyseposition._enabled,
    atl_analyseposition._deleted,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid AS probepkt_id
   FROM ((analyseposition atl_analyseposition
     LEFT JOIN probenahme atl_probenahmen ON ((atl_analyseposition.probenahme_id = atl_probenahmen.id)))
     LEFT JOIN messstelle atl_probepkt ON ((atl_probenahmen.messstid = atl_probepkt.objektid)))
UNION
 SELECT (- bsb.id) AS id,
    NULL::character varying(255) AS grkl,
    (bsb.wert / csb.wert) AS wert,
    bsb.analyse_von,
    bsb.bericht,
    NULL::double precision AS normwert,
    0 AS einheiten_id,
    'P00011'::character varying(255) AS parameter_id,
    bsb.probenahme_id,
    bsb._enabled,
    bsb._deleted,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid AS probepkt_id
   FROM (((analyseposition bsb
     JOIN analyseposition csb ON (((((((bsb.probenahme_id = csb.probenahme_id) AND ((bsb.parameter_id)::text = 'L16250'::text)) AND ((csb.parameter_id)::text = 'L15330'::text)) AND (bsb.wert IS NOT NULL)) AND (csb.wert IS NOT NULL)) AND (csb.wert <> (0)::double precision))))
     LEFT JOIN probenahme atl_probenahmen ON ((csb.probenahme_id = atl_probenahmen.id)))
     LEFT JOIN messstelle atl_probepkt ON ((atl_probenahmen.messstid = atl_probepkt.objektid)))
UNION
 SELECT (- doc.id) AS id,
    NULL::character varying(255) AS grkl,
    (((doc.wert * menge.wert) / (1000)::double precision))::real AS wert,
    doc.analyse_von,
    doc.bericht,
    NULL::double precision AS normwert,
    22 AS einheiten_id,
    'P00015'::character varying(255) AS parameter_id,
    doc.probenahme_id,
    doc._enabled,
    doc._deleted,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid AS probepkt_id
   FROM (((analyseposition menge
     JOIN analyseposition doc ON ((((((menge.probenahme_id = doc.probenahme_id) AND ((menge.parameter_id)::text = 'P00013'::text)) AND ((doc.parameter_id)::text = 'L15210'::text)) AND (menge.wert IS NOT NULL)) AND (doc.wert IS NOT NULL))))
     LEFT JOIN probenahme atl_probenahmen ON ((doc.probenahme_id = atl_probenahmen.id)))
     LEFT JOIN messstelle atl_probepkt ON ((atl_probenahmen.messstid = atl_probepkt.objektid)))
UNION
 SELECT (- toc.id) AS id,
    NULL::character varying(255) AS grkl,
    (((toc.wert * menge.wert) / (1000)::double precision))::real AS wert,
    toc.analyse_von,
    toc.bericht,
    NULL::double precision AS normwert,
    22 AS einheiten_id,
    'P00014'::character varying(255) AS parameter_id,
    toc.probenahme_id,
    toc._enabled,
    toc._deleted,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid AS probepkt_id
   FROM ((((analyseposition menge
     JOIN analyseposition toc ON ((((((menge.probenahme_id = toc.probenahme_id) AND ((menge.parameter_id)::text = 'P00013'::text)) AND ((toc.parameter_id)::text = 'L15230'::text)) AND (menge.wert IS NOT NULL)) AND (toc.wert IS NOT NULL))))
     LEFT JOIN probenahme atl_probenahmen ON ((toc.probenahme_id = atl_probenahmen.id)))
     LEFT JOIN messstelle atl_probepkt ON ((atl_probenahmen.messstid = atl_probepkt.objektid)))
     JOIN ( SELECT atl_probepkt_1.objektid,
            probe.datum_der_entnahme,
            count(*) AS anzahl_proben
           FROM (((messstelle atl_probepkt_1
             JOIN probenahme probe ON ((((probe.messstid = atl_probepkt_1.objektid) AND (atl_probepkt_1._deleted = false)) AND (probe._deleted = false))))
             JOIN analyseposition menge_1 ON (((((menge_1.probenahme_id = probe.id) AND (menge_1._deleted = false)) AND ((menge_1.parameter_id)::text = 'P00013'::text)) AND (menge_1.wert IS NOT NULL))))
             JOIN analyseposition toc_1 ON (((((toc_1.probenahme_id = probe.id) AND (toc_1._deleted = false)) AND ((toc_1.parameter_id)::text = 'L15230'::text)) AND (toc_1.wert IS NOT NULL))))
          GROUP BY atl_probepkt_1.objektid, probe.datum_der_entnahme) anzahl_proben ON ((((anzahl_proben.objektid = atl_probepkt.objektid) AND (anzahl_proben.datum_der_entnahme = atl_probenahmen.datum_der_entnahme)) AND (anzahl_proben.anzahl_proben = 1))))
UNION
 SELECT (- menge_1.id) AS id,
    NULL::character varying(255) AS grkl,
    (((((toc_1.wert * (menge_1.wert / (menge_1.wert + menge_2.wert))) + (toc_2.wert * (menge_2.wert / (menge_1.wert + menge_2.wert)))) * (menge_1.wert + menge_2.wert)) / (1000)::double precision))::real AS wert,
    menge_1.analyse_von,
    menge_1.bericht,
    NULL::double precision AS normwert,
    22 AS einheiten_id,
    'P00014'::character varying(255) AS parameter_id,
    menge_1.probenahme_id,
    menge_1._enabled,
    menge_1._deleted,
    probe_1.datum_der_entnahme,
    atl_probepkt.objektid AS probepkt_id
   FROM ((((((analyseposition menge_1
     JOIN analyseposition toc_1 ON ((((((((menge_1.probenahme_id = toc_1.probenahme_id) AND ((menge_1.parameter_id)::text = 'P00013'::text)) AND ((toc_1.parameter_id)::text = 'L15230'::text)) AND (menge_1.wert IS NOT NULL)) AND (toc_1.wert IS NOT NULL)) AND (NOT menge_1._deleted)) AND (NOT toc_1._deleted))))
     JOIN probenahme probe_1 ON (((toc_1.probenahme_id = probe_1.id) AND (NOT probe_1._deleted))))
     JOIN probenahme probe_2 ON (((((probe_1.messstid = probe_2.messstid) AND (probe_1.datum_der_entnahme = probe_2.datum_der_entnahme)) AND (probe_1.id <> probe_2.id)) AND (NOT probe_2._deleted))))
     JOIN analyseposition menge_2 ON (((((menge_2.probenahme_id = probe_2.id) AND ((menge_2.parameter_id)::text = 'P00013'::text)) AND (menge_2.wert IS NOT NULL)) AND (NOT menge_2._deleted))))
     JOIN analyseposition toc_2 ON (((((menge_2.probenahme_id = toc_2.probenahme_id) AND ((toc_2.parameter_id)::text = 'L15230'::text)) AND (toc_2.wert IS NOT NULL)) AND (NOT toc_2._deleted))))
     JOIN messstelle atl_probepkt ON ((probe_1.messstid = atl_probepkt.objektid)));


ALTER TABLE view_atl_analyseposition_alt OWNER TO auikadmin;

SET search_path = oberflgw, pg_catalog;

SET default_with_oids = true;

--
-- TOC entry 315 (class 1259 OID 5902625)
-- Name: afs_niederschlagswasser; Type: TABLE; Schema: oberflgw; Owner: auikadmin; Tablespace: 
--

CREATE TABLE afs_niederschlagswasser (
    nr bigint NOT NULL,
    lfd_nr integer,
    bezeichnung character varying(80),
    bef_flaeche integer,
    nw_her_bereich_opt integer,
    abflussmenge numeric(11,2),
    anfallstellen_nr bigint,
    entw_grund_nr bigint
);


ALTER TABLE afs_niederschlagswasser OWNER TO auikadmin;

--
-- TOC entry 316 (class 1259 OID 5902628)
-- Name: afs_stoffe; Type: TABLE; Schema: oberflgw; Owner: auikadmin; Tablespace: 
--

CREATE TABLE afs_stoffe (
    anfallstellen_nr bigint NOT NULL,
    stoff_nr bigint NOT NULL,
    produkt character varying(15)
);


ALTER TABLE afs_stoffe OWNER TO auikadmin;

--
-- TOC entry 317 (class 1259 OID 5902694)
-- Name: igl_p_einl_id_seq; Type: SEQUENCE; Schema: oberflgw; Owner: auikadmin
--

CREATE SEQUENCE igl_p_einl_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE igl_p_einl_id_seq OWNER TO auikadmin;

--
-- TOC entry 318 (class 1259 OID 5902734)
-- Name: massnahme; Type: TABLE; Schema: oberflgw; Owner: auikadmin; Tablespace: 
--

CREATE TABLE massnahme (
    nr bigint NOT NULL,
    stoff_nr bigint,
    gruppe_nr bigint,
    massnahme_stoff character varying(1000),
    stoff_eintrags_dat date,
    ergebnis_massnahme character varying(1000),
    ergebnis_am date,
    erstell_dat timestamp without time zone NOT NULL,
    aktual_dat timestamp without time zone NOT NULL,
    external_nr character varying(50)
);


ALTER TABLE massnahme OWNER TO auikadmin;

--
-- TOC entry 319 (class 1259 OID 5902740)
-- Name: msst_berichtspflicht; Type: TABLE; Schema: oberflgw; Owner: auikadmin; Tablespace: 
--

CREATE TABLE msst_berichtspflicht (
    id integer NOT NULL,
    msst_nr bigint NOT NULL,
    berichtspflicht_opt integer NOT NULL
);


ALTER TABLE msst_berichtspflicht OWNER TO auikadmin;

--
-- TOC entry 320 (class 1259 OID 5902743)
-- Name: msst_berichtspflicht_id_seq; Type: SEQUENCE; Schema: oberflgw; Owner: auikadmin
--

CREATE SEQUENCE msst_berichtspflicht_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE msst_berichtspflicht_id_seq OWNER TO auikadmin;

--
-- TOC entry 4928 (class 0 OID 0)
-- Dependencies: 320
-- Name: msst_berichtspflicht_id_seq; Type: SEQUENCE OWNED BY; Schema: oberflgw; Owner: auikadmin
--

ALTER SEQUENCE msst_berichtspflicht_id_seq OWNED BY msst_berichtspflicht.id;


--
-- TOC entry 321 (class 1259 OID 5902857)
-- Name: sb_entlastung; Type: TABLE; Schema: oberflgw; Owner: auikadmin; Tablespace: 
--

CREATE TABLE sb_entlastung (
    nr bigint NOT NULL,
    lfd_nr integer,
    typ_opt integer,
    sb_nr bigint NOT NULL,
    tauchwand_tog boolean DEFAULT false NOT NULL,
    sonstige_beh_tog boolean DEFAULT false NOT NULL,
    ausb_ueberlauf_opt integer,
    sonstige_behandlung character varying(80),
    sb_nicht_in_nrw_tog boolean DEFAULT false NOT NULL,
    ka_nicht_in_nrw_tog boolean DEFAULT false NOT NULL,
    ausb_ueberlauf_sonstiges character varying(80)
);


ALTER TABLE sb_entlastung OWNER TO auikadmin;

--
-- TOC entry 322 (class 1259 OID 5902880)
-- Name: versickerungsanlage; Type: TABLE; Schema: oberflgw; Owner: auikadmin; Tablespace: 
--

CREATE TABLE versickerungsanlage (
    nr bigint NOT NULL,
    els_nr bigint NOT NULL,
    flurabstand numeric(11,2),
    gelaende_ver_anlage numeric(11,2),
    abst_gr_grenze numeric(11,2),
    abst_unterk_gebaeude numeric(11,2),
    abst_ver_anlage numeric(11,2),
    landesfoerderung_tog boolean DEFAULT false NOT NULL,
    notueberlauf_tog boolean DEFAULT false NOT NULL,
    notueberlauf_ziel character varying(80),
    erstell_dat timestamp without time zone NOT NULL,
    durchlaessigkeit numeric(22,11),
    untergrundart character varying(30),
    ver_anlage_opt integer,
    sonstiges_vers character varying(50),
    bauartzul_id character varying(30),
    aktual_dat timestamp without time zone NOT NULL,
    herkunft character varying(20) NOT NULL,
    external_nr character varying(50)
);


ALTER TABLE versickerungsanlage OWNER TO auikadmin;

--
-- TOC entry 323 (class 1259 OID 5902893)
-- Name: z_betrieb_massnahme; Type: TABLE; Schema: oberflgw; Owner: auikadmin; Tablespace: 
--

CREATE TABLE z_betrieb_massnahme (
    id integer NOT NULL,
    betrieb_nr bigint NOT NULL,
    massnahme_nr bigint NOT NULL
);


ALTER TABLE z_betrieb_massnahme OWNER TO auikadmin;

--
-- TOC entry 324 (class 1259 OID 5902896)
-- Name: z_betrieb_massnahme_id_seq; Type: SEQUENCE; Schema: oberflgw; Owner: auikadmin
--

CREATE SEQUENCE z_betrieb_massnahme_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE z_betrieb_massnahme_id_seq OWNER TO auikadmin;

--
-- TOC entry 4932 (class 0 OID 0)
-- Dependencies: 324
-- Name: z_betrieb_massnahme_id_seq; Type: SEQUENCE OWNED BY; Schema: oberflgw; Owner: auikadmin
--

ALTER SEQUENCE z_betrieb_massnahme_id_seq OWNED BY z_betrieb_massnahme.id;


--
-- TOC entry 325 (class 1259 OID 5902898)
-- Name: z_entwaessgr_abwasbehverf; Type: TABLE; Schema: oberflgw; Owner: auikadmin; Tablespace: 
--

CREATE TABLE z_entwaessgr_abwasbehverf (
    id integer NOT NULL,
    entw_grund_nr bigint NOT NULL,
    abwasbehverf_nr bigint NOT NULL
);


ALTER TABLE z_entwaessgr_abwasbehverf OWNER TO auikadmin;

--
-- TOC entry 326 (class 1259 OID 5902901)
-- Name: z_entwaessgr_abwasbehverf_id_seq; Type: SEQUENCE; Schema: oberflgw; Owner: auikadmin
--

CREATE SEQUENCE z_entwaessgr_abwasbehverf_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE z_entwaessgr_abwasbehverf_id_seq OWNER TO auikadmin;

--
-- TOC entry 4934 (class 0 OID 0)
-- Dependencies: 326
-- Name: z_entwaessgr_abwasbehverf_id_seq; Type: SEQUENCE OWNED BY; Schema: oberflgw; Owner: auikadmin
--

ALTER SEQUENCE z_entwaessgr_abwasbehverf_id_seq OWNED BY z_entwaessgr_abwasbehverf.id;


--
-- TOC entry 327 (class 1259 OID 5902903)
-- Name: z_rbf_schutzgueter; Type: TABLE; Schema: oberflgw; Owner: auikadmin; Tablespace: 
--

CREATE TABLE z_rbf_schutzgueter (
    id integer NOT NULL,
    sb_nr bigint NOT NULL,
    schutzgueter_opt integer NOT NULL
);


ALTER TABLE z_rbf_schutzgueter OWNER TO auikadmin;

--
-- TOC entry 328 (class 1259 OID 5902906)
-- Name: z_rbf_schutzgueter_id_seq; Type: SEQUENCE; Schema: oberflgw; Owner: auikadmin
--

CREATE SEQUENCE z_rbf_schutzgueter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE z_rbf_schutzgueter_id_seq OWNER TO auikadmin;

--
-- TOC entry 4936 (class 0 OID 0)
-- Dependencies: 328
-- Name: z_rbf_schutzgueter_id_seq; Type: SEQUENCE OWNED BY; Schema: oberflgw; Owner: auikadmin
--

ALTER SEQUENCE z_rbf_schutzgueter_id_seq OWNED BY z_rbf_schutzgueter.id;


--
-- TOC entry 329 (class 1259 OID 5902908)
-- Name: z_sb_regeln; Type: TABLE; Schema: oberflgw; Owner: auikadmin; Tablespace: 
--

CREATE TABLE z_sb_regeln (
    id integer NOT NULL,
    sb_nr bigint NOT NULL,
    regeln_tech_opt integer NOT NULL
);


ALTER TABLE z_sb_regeln OWNER TO auikadmin;

--
-- TOC entry 330 (class 1259 OID 5902911)
-- Name: z_sb_regeln_id_seq; Type: SEQUENCE; Schema: oberflgw; Owner: auikadmin
--

CREATE SEQUENCE z_sb_regeln_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE z_sb_regeln_id_seq OWNER TO auikadmin;

--
-- TOC entry 4938 (class 0 OID 0)
-- Dependencies: 330
-- Name: z_sb_regeln_id_seq; Type: SEQUENCE OWNED BY; Schema: oberflgw; Owner: auikadmin
--

ALTER SEQUENCE z_sb_regeln_id_seq OWNED BY z_sb_regeln.id;


--
-- TOC entry 331 (class 1259 OID 5902913)
-- Name: z_sb_verfahren; Type: TABLE; Schema: oberflgw; Owner: auikadmin; Tablespace: 
--

CREATE TABLE z_sb_verfahren (
    id integer NOT NULL,
    sb_nr bigint NOT NULL,
    vorgaben_opt integer NOT NULL
);


ALTER TABLE z_sb_verfahren OWNER TO auikadmin;

--
-- TOC entry 332 (class 1259 OID 5902916)
-- Name: z_sb_verfahren_id_seq; Type: SEQUENCE; Schema: oberflgw; Owner: auikadmin
--

CREATE SEQUENCE z_sb_verfahren_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE z_sb_verfahren_id_seq OWNER TO auikadmin;

--
-- TOC entry 4940 (class 0 OID 0)
-- Dependencies: 332
-- Name: z_sb_verfahren_id_seq; Type: SEQUENCE OWNED BY; Schema: oberflgw; Owner: auikadmin
--

ALTER SEQUENCE z_sb_verfahren_id_seq OWNED BY z_sb_verfahren.id;


SET search_path = public, pg_catalog;

--
-- TOC entry 333 (class 1259 OID 5902918)
-- Name: e_z_klaeranlage_massnahme; Type: TABLE; Schema: public; Owner: auikadmin; Tablespace: 
--

CREATE TABLE e_z_klaeranlage_massnahme (
    id integer NOT NULL,
    massnahme_nr bigint NOT NULL,
    klaeranlage_nr bigint NOT NULL
);


ALTER TABLE e_z_klaeranlage_massnahme OWNER TO auikadmin;

--
-- TOC entry 334 (class 1259 OID 5902921)
-- Name: e_z_klaeranlage_massnahme_id_seq; Type: SEQUENCE; Schema: public; Owner: auikadmin
--

CREATE SEQUENCE e_z_klaeranlage_massnahme_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE e_z_klaeranlage_massnahme_id_seq OWNER TO auikadmin;

--
-- TOC entry 4941 (class 0 OID 0)
-- Dependencies: 334
-- Name: e_z_klaeranlage_massnahme_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: auikadmin
--

ALTER SEQUENCE e_z_klaeranlage_massnahme_id_seq OWNED BY e_z_klaeranlage_massnahme.id;


--
-- TOC entry 335 (class 1259 OID 5902923)
-- Name: e_z_kleika_gebiet; Type: TABLE; Schema: public; Owner: auikadmin; Tablespace: 
--

CREATE TABLE e_z_kleika_gebiet (
    id integer NOT NULL,
    kleika_nr bigint NOT NULL,
    gebietskennung_opt integer NOT NULL
);


ALTER TABLE e_z_kleika_gebiet OWNER TO auikadmin;

--
-- TOC entry 336 (class 1259 OID 5902926)
-- Name: e_z_kleika_gebiet_id_seq; Type: SEQUENCE; Schema: public; Owner: auikadmin
--

CREATE SEQUENCE e_z_kleika_gebiet_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE e_z_kleika_gebiet_id_seq OWNER TO auikadmin;

--
-- TOC entry 4942 (class 0 OID 0)
-- Dependencies: 336
-- Name: e_z_kleika_gebiet_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: auikadmin
--

ALTER SEQUENCE e_z_kleika_gebiet_id_seq OWNED BY e_z_kleika_gebiet.id;


--
-- TOC entry 337 (class 1259 OID 5902928)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: auikadmin
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO auikadmin;

--
-- TOC entry 338 (class 1259 OID 5902930)
-- Name: tab_streets_alkis; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tab_streets_alkis (
    plz character varying(5),
    name character varying(40) NOT NULL,
    nr character varying(4) NOT NULL,
    x double precision,
    y double precision,
    gemeinde character varying(10) NOT NULL,
    schl character varying(5),
    hausnr integer,
    hausnr_zusatz character varying(1),
    the_geom geometry(Point,25832),
    abgleich character varying(9)
);


ALTER TABLE tab_streets_alkis OWNER TO postgres;

--
-- TOC entry 4944 (class 0 OID 0)
-- Dependencies: 338
-- Name: TABLE tab_streets_alkis; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE tab_streets_alkis IS 'Tabelle wurde automatisch geladen am: 16_03_2020';


SET search_path = awsv, pg_catalog;

--
-- TOC entry 4169 (class 2604 OID 5902936)
-- Name: id; Type: DEFAULT; Schema: awsv; Owner: auikadmin
--

ALTER TABLE ONLY abfuellflaeche ALTER COLUMN id SET DEFAULT nextval('awsv_abfuellflaeche_id_seq'::regclass);


--
-- TOC entry 4172 (class 2604 OID 5902937)
-- Name: id; Type: DEFAULT; Schema: awsv; Owner: auikadmin
--

ALTER TABLE ONLY abscheider ALTER COLUMN id SET DEFAULT nextval('awsv_abscheider_id_seq'::regclass);


--
-- TOC entry 4178 (class 2604 OID 5902938)
-- Name: id; Type: DEFAULT; Schema: awsv; Owner: auikadmin
--

ALTER TABLE ONLY anlagenchrono ALTER COLUMN id SET DEFAULT nextval('awsv_anlagenchrono_id_seq'::regclass);


--
-- TOC entry 4190 (class 2604 OID 5902939)
-- Name: id; Type: DEFAULT; Schema: awsv; Owner: auikadmin
--

ALTER TABLE ONLY jgs ALTER COLUMN id SET DEFAULT nextval('awsv_jgs_id_seq'::regclass);


--
-- TOC entry 4193 (class 2604 OID 5902940)
-- Name: id; Type: DEFAULT; Schema: awsv; Owner: auikadmin
--

ALTER TABLE ONLY kontrollen ALTER COLUMN id SET DEFAULT nextval('awsv_kontrollen_id_seq'::regclass);


--
-- TOC entry 4204 (class 2604 OID 5902941)
-- Name: id; Type: DEFAULT; Schema: awsv; Owner: auikadmin
--

ALTER TABLE ONLY verwaltungsgebuehren ALTER COLUMN id SET DEFAULT nextval('awsv_verwaltungsgebuehren_id_seq'::regclass);


--
-- TOC entry 4207 (class 2604 OID 5902942)
-- Name: id; Type: DEFAULT; Schema: awsv; Owner: auikadmin
--

ALTER TABLE ONLY verwaltungsverf ALTER COLUMN id SET DEFAULT nextval('awsv_verwaltungsverf_id_seq'::regclass);


SET search_path = basis, pg_catalog;

--
-- TOC entry 4216 (class 2604 OID 5902943)
-- Name: id; Type: DEFAULT; Schema: basis; Owner: auikadmin
--

ALTER TABLE ONLY adresse ALTER COLUMN id SET DEFAULT nextval('basis_betreiber_id_seq'::regclass);


--
-- TOC entry 4393 (class 2604 OID 5903755)
-- Name: id; Type: DEFAULT; Schema: basis; Owner: auikadmin
--

ALTER TABLE ONLY inhaber ALTER COLUMN id SET DEFAULT nextval('inhaber_id_seq'::regclass);


--
-- TOC entry 4228 (class 2604 OID 5902945)
-- Name: id; Type: DEFAULT; Schema: basis; Owner: auikadmin
--

ALTER TABLE ONLY objekt ALTER COLUMN id SET DEFAULT nextval('basis_objekt_objektid_seq'::regclass);


--
-- TOC entry 4231 (class 2604 OID 5902946)
-- Name: id; Type: DEFAULT; Schema: basis; Owner: auikadmin
--

ALTER TABLE ONLY objektchrono ALTER COLUMN id SET DEFAULT nextval('basis_objektchrono_id_seq'::regclass);


--
-- TOC entry 4234 (class 2604 OID 5902947)
-- Name: id; Type: DEFAULT; Schema: basis; Owner: auikadmin
--

ALTER TABLE ONLY objektverknuepfung ALTER COLUMN id SET DEFAULT nextval('basis_objektverknuepfung_id_seq'::regclass);


--
-- TOC entry 4237 (class 2604 OID 5902948)
-- Name: id; Type: DEFAULT; Schema: basis; Owner: auikadmin
--

ALTER TABLE ONLY sachbearbeiter ALTER COLUMN id SET DEFAULT nextval('basis_sachbearbeiter_id_seq'::regclass);


--
-- TOC entry 4240 (class 2604 OID 5902949)
-- Name: id; Type: DEFAULT; Schema: basis; Owner: auikadmin
--

ALTER TABLE ONLY standort ALTER COLUMN id SET DEFAULT nextval('basis_standort_id_seq'::regclass);


SET search_path = elka, pg_catalog;

--
-- TOC entry 4253 (class 2604 OID 5902950)
-- Name: id; Type: DEFAULT; Schema: elka; Owner: auikadmin
--

ALTER TABLE ONLY aba ALTER COLUMN id SET DEFAULT nextval('elka_aba_id_seq'::regclass);


--
-- TOC entry 4254 (class 2604 OID 5902951)
-- Name: id; Type: DEFAULT; Schema: elka; Owner: auikadmin
--

ALTER TABLE ONLY anfallstelle ALTER COLUMN id SET DEFAULT nextval('elka_anfallstelle_id_seq'::regclass);


--
-- TOC entry 4271 (class 2604 OID 5902952)
-- Name: id; Type: DEFAULT; Schema: elka; Owner: auikadmin
--

ALTER TABLE ONLY einleitungsstelle ALTER COLUMN id SET DEFAULT nextval('elka_einleitungsstelle_id_seq'::regclass);


--
-- TOC entry 4306 (class 2604 OID 5902953)
-- Name: nr; Type: DEFAULT; Schema: elka; Owner: auikadmin
--

ALTER TABLE ONLY referenz ALTER COLUMN nr SET DEFAULT nextval('elka_referenz_nr_seq'::regclass);


--
-- TOC entry 4257 (class 2604 OID 5902954)
-- Name: id; Type: DEFAULT; Schema: elka; Owner: auikadmin
--

ALTER TABLE ONLY wasserrecht ALTER COLUMN id SET DEFAULT nextval('elka_wasserrecht_id_seq'::regclass);


--
-- TOC entry 4305 (class 2604 OID 5902955)
-- Name: id; Type: DEFAULT; Schema: elka; Owner: auikadmin
--

ALTER TABLE ONLY z_aba_verfahren ALTER COLUMN id SET DEFAULT nextval('z_aba_verfahren_id_seq'::regclass);


SET search_path = indeinl, pg_catalog;

--
-- TOC entry 4309 (class 2604 OID 5902981)
-- Name: id; Type: DEFAULT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY anh_40_fachdaten ALTER COLUMN id SET DEFAULT nextval('anh_40_fachdaten_id_seq'::regclass);


--
-- TOC entry 4312 (class 2604 OID 5902982)
-- Name: id; Type: DEFAULT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY anh_49_abfuhr ALTER COLUMN id SET DEFAULT nextval('anh_49_abfuhr_id_seq'::regclass);


--
-- TOC entry 4321 (class 2604 OID 5902983)
-- Name: id; Type: DEFAULT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY anh_49_abscheiderdetails ALTER COLUMN id SET DEFAULT nextval('anh_49_abscheiderdetails_id_seq'::regclass);


--
-- TOC entry 4328 (class 2604 OID 5902984)
-- Name: id; Type: DEFAULT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY anh_49_analysen ALTER COLUMN id SET DEFAULT nextval('anh_49_analysen_id_seq'::regclass);


--
-- TOC entry 4331 (class 2604 OID 5902985)
-- Name: id; Type: DEFAULT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY anh_49_fachdaten ALTER COLUMN id SET DEFAULT nextval('anh_49_fachdaten_id_seq'::regclass);


--
-- TOC entry 4334 (class 2604 OID 5902986)
-- Name: id; Type: DEFAULT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY anh_49_kontrollen ALTER COLUMN id SET DEFAULT nextval('anh_49_kontrollen_id_seq'::regclass);


--
-- TOC entry 4337 (class 2604 OID 5902989)
-- Name: id; Type: DEFAULT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY anh_50_fachdaten ALTER COLUMN id SET DEFAULT nextval('anh_50_fachdaten_id_seq'::regclass);


--
-- TOC entry 4340 (class 2604 OID 5902990)
-- Name: id; Type: DEFAULT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY anh_52_fachdaten ALTER COLUMN id SET DEFAULT nextval('anh_52_fachdaten_id_seq'::regclass);


--
-- TOC entry 4343 (class 2604 OID 5902991)
-- Name: id; Type: DEFAULT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY anh_53_fachdaten ALTER COLUMN id SET DEFAULT nextval('anh_53_fachdaten_id_seq'::regclass);


--
-- TOC entry 4346 (class 2604 OID 5902992)
-- Name: id; Type: DEFAULT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY anh_55_fachdaten ALTER COLUMN id SET DEFAULT nextval('anh_55_fachdaten_id_seq'::regclass);


--
-- TOC entry 4349 (class 2604 OID 5902993)
-- Name: id; Type: DEFAULT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY anh_56_fachdaten ALTER COLUMN id SET DEFAULT nextval('anh_56_fachdaten_id_seq'::regclass);


--
-- TOC entry 4352 (class 2604 OID 5902994)
-- Name: id; Type: DEFAULT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY bwk_fachdaten ALTER COLUMN id SET DEFAULT nextval('anh_bwk_fachdaten_id_seq'::regclass);


--
-- TOC entry 4355 (class 2604 OID 5902995)
-- Name: id; Type: DEFAULT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY suev_fachdaten ALTER COLUMN id SET DEFAULT nextval('anh_suev_fachdaten_id_seq'::regclass);


SET search_path = labor, pg_catalog;

--
-- TOC entry 4283 (class 2604 OID 5902996)
-- Name: id; Type: DEFAULT; Schema: labor; Owner: auikadmin
--

ALTER TABLE ONLY analyseposition ALTER COLUMN id SET DEFAULT nextval('atl_analyseposition_id_seq'::regclass);


--
-- TOC entry 4275 (class 2604 OID 5902997)
-- Name: id; Type: DEFAULT; Schema: labor; Owner: auikadmin
--

ALTER TABLE ONLY messstelle ALTER COLUMN id SET DEFAULT nextval('atl_probepkt_id_seq'::regclass);


--
-- TOC entry 4279 (class 2604 OID 5902998)
-- Name: id; Type: DEFAULT; Schema: labor; Owner: auikadmin
--

ALTER TABLE ONLY probenahme ALTER COLUMN id SET DEFAULT nextval('atl_probenahmen_id_seq'::regclass);


SET search_path = oberflgw, pg_catalog;

--
-- TOC entry 4379 (class 2604 OID 5903008)
-- Name: id; Type: DEFAULT; Schema: oberflgw; Owner: auikadmin
--

ALTER TABLE ONLY msst_berichtspflicht ALTER COLUMN id SET DEFAULT nextval('msst_berichtspflicht_id_seq'::regclass);


--
-- TOC entry 4386 (class 2604 OID 5903016)
-- Name: id; Type: DEFAULT; Schema: oberflgw; Owner: auikadmin
--

ALTER TABLE ONLY z_betrieb_massnahme ALTER COLUMN id SET DEFAULT nextval('z_betrieb_massnahme_id_seq'::regclass);


--
-- TOC entry 4387 (class 2604 OID 5903017)
-- Name: id; Type: DEFAULT; Schema: oberflgw; Owner: auikadmin
--

ALTER TABLE ONLY z_entwaessgr_abwasbehverf ALTER COLUMN id SET DEFAULT nextval('z_entwaessgr_abwasbehverf_id_seq'::regclass);


--
-- TOC entry 4388 (class 2604 OID 5903018)
-- Name: id; Type: DEFAULT; Schema: oberflgw; Owner: auikadmin
--

ALTER TABLE ONLY z_rbf_schutzgueter ALTER COLUMN id SET DEFAULT nextval('z_rbf_schutzgueter_id_seq'::regclass);


--
-- TOC entry 4389 (class 2604 OID 5903019)
-- Name: id; Type: DEFAULT; Schema: oberflgw; Owner: auikadmin
--

ALTER TABLE ONLY z_sb_regeln ALTER COLUMN id SET DEFAULT nextval('z_sb_regeln_id_seq'::regclass);


--
-- TOC entry 4390 (class 2604 OID 5903020)
-- Name: id; Type: DEFAULT; Schema: oberflgw; Owner: auikadmin
--

ALTER TABLE ONLY z_sb_verfahren ALTER COLUMN id SET DEFAULT nextval('z_sb_verfahren_id_seq'::regclass);


SET search_path = public, pg_catalog;

--
-- TOC entry 4391 (class 2604 OID 5903021)
-- Name: id; Type: DEFAULT; Schema: public; Owner: auikadmin
--

ALTER TABLE ONLY e_z_klaeranlage_massnahme ALTER COLUMN id SET DEFAULT nextval('e_z_klaeranlage_massnahme_id_seq'::regclass);


--
-- TOC entry 4392 (class 2604 OID 5903022)
-- Name: id; Type: DEFAULT; Schema: public; Owner: auikadmin
--

ALTER TABLE ONLY e_z_kleika_gebiet ALTER COLUMN id SET DEFAULT nextval('e_z_kleika_gebiet_id_seq'::regclass);


SET search_path = awsv, pg_catalog;

--
-- TOC entry 4400 (class 2606 OID 5903024)
-- Name: awsv_abfuellflaeche_pkey; Type: CONSTRAINT; Schema: awsv; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY abfuellflaeche
    ADD CONSTRAINT awsv_abfuellflaeche_pkey PRIMARY KEY (behaelterid);


--
-- TOC entry 4402 (class 2606 OID 5903026)
-- Name: awsv_abscheider_pkey; Type: CONSTRAINT; Schema: awsv; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY abscheider
    ADD CONSTRAINT awsv_abscheider_pkey PRIMARY KEY (id);


--
-- TOC entry 4404 (class 2606 OID 5903028)
-- Name: awsv_anlagenarten_pkey; Type: CONSTRAINT; Schema: awsv; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY anlagenarten
    ADD CONSTRAINT awsv_anlagenarten_pkey PRIMARY KEY (id);


--
-- TOC entry 4406 (class 2606 OID 5903030)
-- Name: awsv_anlagenchrono_pkey; Type: CONSTRAINT; Schema: awsv; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY anlagenchrono
    ADD CONSTRAINT awsv_anlagenchrono_pkey PRIMARY KEY (id);


--
-- TOC entry 4408 (class 2606 OID 5903032)
-- Name: awsv_behaelterart_pkey; Type: CONSTRAINT; Schema: awsv; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY behaelterart
    ADD CONSTRAINT awsv_behaelterart_pkey PRIMARY KEY (id);


--
-- TOC entry 4410 (class 2606 OID 5903034)
-- Name: awsv_fachdaten_pkey; Type: CONSTRAINT; Schema: awsv; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY fachdaten
    ADD CONSTRAINT awsv_fachdaten_pkey PRIMARY KEY (behaelterid);


--
-- TOC entry 4412 (class 2606 OID 5903036)
-- Name: awsv_fluessigkeit_pkey; Type: CONSTRAINT; Schema: awsv; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY fluessigkeit
    ADD CONSTRAINT awsv_fluessigkeit_pkey PRIMARY KEY (id);


--
-- TOC entry 4414 (class 2606 OID 5903038)
-- Name: awsv_gebuehrenarten_pkey; Type: CONSTRAINT; Schema: awsv; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY gebuehrenarten
    ADD CONSTRAINT awsv_gebuehrenarten_pkey PRIMARY KEY (id);


--
-- TOC entry 4416 (class 2606 OID 5903040)
-- Name: awsv_gefaehrdungsstufen_pkey; Type: CONSTRAINT; Schema: awsv; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY gefaehrdungsstufen
    ADD CONSTRAINT awsv_gefaehrdungsstufen_pkey PRIMARY KEY (id);


--
-- TOC entry 4418 (class 2606 OID 5903042)
-- Name: awsv_jgs_pkey; Type: CONSTRAINT; Schema: awsv; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY jgs
    ADD CONSTRAINT awsv_jgs_pkey PRIMARY KEY (id);


--
-- TOC entry 4420 (class 2606 OID 5903044)
-- Name: awsv_kontrollen_pkey; Type: CONSTRAINT; Schema: awsv; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY kontrollen
    ADD CONSTRAINT awsv_kontrollen_pkey PRIMARY KEY (id);


--
-- TOC entry 4422 (class 2606 OID 5903046)
-- Name: awsv_material_pkey; Type: CONSTRAINT; Schema: awsv; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY material
    ADD CONSTRAINT awsv_material_pkey PRIMARY KEY (id);


--
-- TOC entry 4424 (class 2606 OID 5903048)
-- Name: awsv_pruefer_pkey; Type: CONSTRAINT; Schema: awsv; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY pruefer
    ADD CONSTRAINT awsv_pruefer_pkey PRIMARY KEY (id);


--
-- TOC entry 4426 (class 2606 OID 5903050)
-- Name: awsv_pruefergebnisse_pkey; Type: CONSTRAINT; Schema: awsv; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY pruefergebniss
    ADD CONSTRAINT awsv_pruefergebnisse_pkey PRIMARY KEY (id);


--
-- TOC entry 4428 (class 2606 OID 5903052)
-- Name: awsv_standortgghwsg_pkey; Type: CONSTRAINT; Schema: awsv; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY standortgghwsg
    ADD CONSTRAINT awsv_standortgghwsg_pkey PRIMARY KEY (id);


--
-- TOC entry 4434 (class 2606 OID 5903054)
-- Name: awsv_vbfeinstufung_pkey; Type: CONSTRAINT; Schema: awsv; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY vbfeinstufung
    ADD CONSTRAINT awsv_vbfeinstufung_pkey PRIMARY KEY (id);


--
-- TOC entry 4430 (class 2606 OID 5903056)
-- Name: awsv_verwaltungsgebuehren_pkey; Type: CONSTRAINT; Schema: awsv; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY verwaltungsgebuehren
    ADD CONSTRAINT awsv_verwaltungsgebuehren_pkey PRIMARY KEY (id);


--
-- TOC entry 4432 (class 2606 OID 5903058)
-- Name: awsv_verwaltungsverf_pkey; Type: CONSTRAINT; Schema: awsv; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY verwaltungsverf
    ADD CONSTRAINT awsv_verwaltungsverf_pkey PRIMARY KEY (id);


--
-- TOC entry 4436 (class 2606 OID 5903060)
-- Name: awsv_verwmassnahmen_pkey; Type: CONSTRAINT; Schema: awsv; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY verwmassnahmen
    ADD CONSTRAINT awsv_verwmassnahmen_pkey PRIMARY KEY (id);


--
-- TOC entry 4438 (class 2606 OID 5903062)
-- Name: awsv_wassereinzugsgebiete_pkey; Type: CONSTRAINT; Schema: awsv; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY wassereinzugsgebiet
    ADD CONSTRAINT awsv_wassereinzugsgebiete_pkey PRIMARY KEY (id);


SET search_path = basis, pg_catalog;

--
-- TOC entry 4440 (class 2606 OID 5903064)
-- Name: basis_betreiber_pkey; Type: CONSTRAINT; Schema: basis; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY adresse
    ADD CONSTRAINT basis_betreiber_pkey PRIMARY KEY (id);


--
-- TOC entry 4457 (class 2606 OID 5903066)
-- Name: basis_bezeichnung_pkey; Type: CONSTRAINT; Schema: basis; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY bezeichnung
    ADD CONSTRAINT basis_bezeichnung_pkey PRIMARY KEY (id);


--
-- TOC entry 4459 (class 2606 OID 5903068)
-- Name: basis_gemarkung_pkey; Type: CONSTRAINT; Schema: basis; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY gemarkung
    ADD CONSTRAINT basis_gemarkung_pkey PRIMARY KEY (id);


--
-- TOC entry 4572 (class 2606 OID 5903765)
-- Name: basis_inhaber_pkey; Type: CONSTRAINT; Schema: basis; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY inhaber
    ADD CONSTRAINT basis_inhaber_pkey PRIMARY KEY (id);


--
-- TOC entry 4445 (class 2606 OID 5903072)
-- Name: basis_objekt_pkey; Type: CONSTRAINT; Schema: basis; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY objekt
    ADD CONSTRAINT basis_objekt_pkey PRIMARY KEY (id);


--
-- TOC entry 4461 (class 2606 OID 5903074)
-- Name: basis_objektarten_pkey; Type: CONSTRAINT; Schema: basis; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY objektarten
    ADD CONSTRAINT basis_objektarten_pkey PRIMARY KEY (id);


--
-- TOC entry 4447 (class 2606 OID 5903076)
-- Name: basis_objektchrono_pkey; Type: CONSTRAINT; Schema: basis; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY objektchrono
    ADD CONSTRAINT basis_objektchrono_pkey PRIMARY KEY (id);


--
-- TOC entry 4449 (class 2606 OID 5903078)
-- Name: basis_objektverknuepfung_pkey; Type: CONSTRAINT; Schema: basis; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY objektverknuepfung
    ADD CONSTRAINT basis_objektverknuepfung_pkey PRIMARY KEY (id);


--
-- TOC entry 4463 (class 2606 OID 5903080)
-- Name: basis_prioritaet_pkey; Type: CONSTRAINT; Schema: basis; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY prioritaet
    ADD CONSTRAINT basis_prioritaet_pkey PRIMARY KEY (standort_id, betreiber_id);


--
-- TOC entry 4451 (class 2606 OID 5903082)
-- Name: basis_sachbearbeiter_pkey; Type: CONSTRAINT; Schema: basis; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY sachbearbeiter
    ADD CONSTRAINT basis_sachbearbeiter_pkey PRIMARY KEY (id);


--
-- TOC entry 4455 (class 2606 OID 5903084)
-- Name: basis_standort_pkey; Type: CONSTRAINT; Schema: basis; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY standort
    ADD CONSTRAINT basis_standort_pkey PRIMARY KEY (id);


--
-- TOC entry 4453 (class 2606 OID 5903088)
-- Name: kennummer_unique_constraint; Type: CONSTRAINT; Schema: basis; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY sachbearbeiter
    ADD CONSTRAINT kennummer_unique_constraint UNIQUE (kennummer);


--
-- TOC entry 4467 (class 2606 OID 5903090)
-- Name: awsv_wirtschaftszweige_pkey; Type: CONSTRAINT; Schema: basis; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY wirtschaftszweig
    ADD CONSTRAINT awsv_wirtschaftszweige_pkey PRIMARY KEY (id);


SET search_path = elka, pg_catalog;

--
-- TOC entry 4469 (class 2606 OID 5903092)
-- Name: aba_pkey; Type: CONSTRAINT; Schema: elka; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY aba
    ADD CONSTRAINT aba_pkey PRIMARY KEY (id);


--
-- TOC entry 4471 (class 2606 OID 5903094)
-- Name: aba_unique_id; Type: CONSTRAINT; Schema: elka; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY aba
    ADD CONSTRAINT aba_unique_id UNIQUE (id);


--
-- TOC entry 4473 (class 2606 OID 5903096)
-- Name: aba_unique_objektid; Type: CONSTRAINT; Schema: elka; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY aba
    ADD CONSTRAINT aba_unique_objektid UNIQUE (objektid);


--
-- TOC entry 4477 (class 2606 OID 5903098)
-- Name: basis_anfallstelle_pkey; Type: CONSTRAINT; Schema: elka; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY anfallstelle
    ADD CONSTRAINT basis_anfallstelle_pkey PRIMARY KEY (id);


--
-- TOC entry 4475 (class 2606 OID 5903100)
-- Name: elka_aba_verfahren_pkey; Type: CONSTRAINT; Schema: elka; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY abaverfahren
    ADD CONSTRAINT elka_aba_verfahren_pkey PRIMARY KEY (nr);


--
-- TOC entry 4574 (class 2606 OID 5903900)
-- Name: elka_anhang_pkey; Type: CONSTRAINT; Schema: elka; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY anhang
    ADD CONSTRAINT elka_anhang_pkey PRIMARY KEY (sl_nr);


--
-- TOC entry 4483 (class 2606 OID 5903102)
-- Name: elka_einleitungsstelle_pkey; Type: CONSTRAINT; Schema: elka; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY einleitungsstelle
    ADD CONSTRAINT elka_einleitungsstelle_pkey PRIMARY KEY (id);


--
-- TOC entry 4503 (class 2606 OID 5903104)
-- Name: elka_referenz_pkey; Type: CONSTRAINT; Schema: elka; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY referenz
    ADD CONSTRAINT elka_referenz_pkey PRIMARY KEY (nr);


--
-- TOC entry 4479 (class 2606 OID 5903106)
-- Name: indeinl_genehmigung_pkey; Type: CONSTRAINT; Schema: elka; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY wasserrecht
    ADD CONSTRAINT indeinl_genehmigung_pkey PRIMARY KEY (id);


--
-- TOC entry 4509 (class 2606 OID 5903108)
-- Name: pkey_map_anhang; Type: CONSTRAINT; Schema: elka; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY map_elka_anhang
    ADD CONSTRAINT pkey_map_anhang PRIMARY KEY (id);


--
-- TOC entry 4493 (class 2606 OID 5903110)
-- Name: pkey_map_einheit; Type: CONSTRAINT; Schema: elka; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY map_elka_einheit
    ADD CONSTRAINT pkey_map_einheit PRIMARY KEY (id);


--
-- TOC entry 4495 (class 2606 OID 5903112)
-- Name: pkey_map_stoff; Type: CONSTRAINT; Schema: elka; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY map_elka_stoff
    ADD CONSTRAINT pkey_map_stoff PRIMARY KEY (id);


--
-- TOC entry 4481 (class 2606 OID 5903114)
-- Name: wasserrecht_unique_id; Type: CONSTRAINT; Schema: elka; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY wasserrecht
    ADD CONSTRAINT wasserrecht_unique_id UNIQUE (objektid);


--
-- TOC entry 4501 (class 2606 OID 5903116)
-- Name: z_aba_verfahren_pkey; Type: CONSTRAINT; Schema: elka; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY z_aba_verfahren
    ADD CONSTRAINT z_aba_verfahren_pkey PRIMARY KEY (id);


SET search_path = indeinl, pg_catalog;

--
-- TOC entry 4511 (class 2606 OID 5903168)
-- Name: anh_40_fachdaten_pkey; Type: CONSTRAINT; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY anh_40_fachdaten
    ADD CONSTRAINT anh_40_fachdaten_pkey PRIMARY KEY (id);


--
-- TOC entry 4513 (class 2606 OID 5903170)
-- Name: anh_49_abfuhr_pkey; Type: CONSTRAINT; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY anh_49_abfuhr
    ADD CONSTRAINT anh_49_abfuhr_pkey PRIMARY KEY (id);


--
-- TOC entry 4515 (class 2606 OID 5903172)
-- Name: anh_49_abscheiderdetails_pkey; Type: CONSTRAINT; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY anh_49_abscheiderdetails
    ADD CONSTRAINT anh_49_abscheiderdetails_pkey PRIMARY KEY (id);


--
-- TOC entry 4517 (class 2606 OID 5903174)
-- Name: anh_49_analysen_pkey; Type: CONSTRAINT; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY anh_49_analysen
    ADD CONSTRAINT anh_49_analysen_pkey PRIMARY KEY (id);


--
-- TOC entry 4519 (class 2606 OID 5903176)
-- Name: anh_49_fachdate_objektid_unique; Type: CONSTRAINT; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY anh_49_fachdaten
    ADD CONSTRAINT anh_49_fachdate_objektid_unique UNIQUE (id);


--
-- TOC entry 4521 (class 2606 OID 5903178)
-- Name: anh_49_fachdaten_pkey; Type: CONSTRAINT; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY anh_49_fachdaten
    ADD CONSTRAINT anh_49_fachdaten_pkey PRIMARY KEY (id);


--
-- TOC entry 4523 (class 2606 OID 5903180)
-- Name: anh_49_kontrollen_pkey; Type: CONSTRAINT; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY anh_49_kontrollen
    ADD CONSTRAINT anh_49_kontrollen_pkey PRIMARY KEY (id);


--
-- TOC entry 4526 (class 2606 OID 5903186)
-- Name: anh_50_fachdaten_pkey; Type: CONSTRAINT; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY anh_50_fachdaten
    ADD CONSTRAINT anh_50_fachdaten_pkey PRIMARY KEY (id);


--
-- TOC entry 4528 (class 2606 OID 5903188)
-- Name: anh_52_fachdaten_pkey; Type: CONSTRAINT; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY anh_52_fachdaten
    ADD CONSTRAINT anh_52_fachdaten_pkey PRIMARY KEY (id);


--
-- TOC entry 4530 (class 2606 OID 5903190)
-- Name: anh_53_fachdaten_pkey; Type: CONSTRAINT; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY anh_53_fachdaten
    ADD CONSTRAINT anh_53_fachdaten_pkey PRIMARY KEY (id);


--
-- TOC entry 4532 (class 2606 OID 5903192)
-- Name: anh_55_fachdaten_pkey; Type: CONSTRAINT; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY anh_55_fachdaten
    ADD CONSTRAINT anh_55_fachdaten_pkey PRIMARY KEY (id);


--
-- TOC entry 4534 (class 2606 OID 5903194)
-- Name: anh_56_fachdaten_pkey; Type: CONSTRAINT; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY anh_56_fachdaten
    ADD CONSTRAINT anh_56_fachdaten_pkey PRIMARY KEY (id);


--
-- TOC entry 4536 (class 2606 OID 5903196)
-- Name: anh_bwk_fachdaten_pkey; Type: CONSTRAINT; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY bwk_fachdaten
    ADD CONSTRAINT anh_bwk_fachdaten_pkey PRIMARY KEY (id);


--
-- TOC entry 4540 (class 2606 OID 5903198)
-- Name: anh_entsorger_pkey; Type: CONSTRAINT; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY entsorger
    ADD CONSTRAINT anh_entsorger_pkey PRIMARY KEY (id);


--
-- TOC entry 4538 (class 2606 OID 5903200)
-- Name: anh_suev_fachdaten_pkey; Type: CONSTRAINT; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY suev_fachdaten
    ADD CONSTRAINT anh_suev_fachdaten_pkey PRIMARY KEY (id);


SET search_path = labor, pg_catalog;

--
-- TOC entry 4497 (class 2606 OID 5903202)
-- Name: atl_analyseposition_pkey; Type: CONSTRAINT; Schema: labor; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY analyseposition
    ADD CONSTRAINT atl_analyseposition_pkey PRIMARY KEY (id);


--
-- TOC entry 4542 (class 2606 OID 5903204)
-- Name: atl_einheiten_pkey; Type: CONSTRAINT; Schema: labor; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY einheiten
    ADD CONSTRAINT atl_einheiten_pkey PRIMARY KEY (id);


--
-- TOC entry 4548 (class 2606 OID 5903206)
-- Name: atl_klaeranlagen_pkey; Type: CONSTRAINT; Schema: labor; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY klaeranlage
    ADD CONSTRAINT atl_klaeranlagen_pkey PRIMARY KEY (id);


--
-- TOC entry 4550 (class 2606 OID 5903208)
-- Name: atl_meta_parameter_pkey; Type: CONSTRAINT; Schema: labor; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY meta_parameter
    ADD CONSTRAINT atl_meta_parameter_pkey PRIMARY KEY (id);


--
-- TOC entry 4544 (class 2606 OID 5903210)
-- Name: atl_parameter_pkey; Type: CONSTRAINT; Schema: labor; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY parameter
    ADD CONSTRAINT atl_parameter_pkey PRIMARY KEY (ordnungsbegriff);


--
-- TOC entry 4552 (class 2606 OID 5903212)
-- Name: atl_parametergruppen_pkey; Type: CONSTRAINT; Schema: labor; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY parametergruppen
    ADD CONSTRAINT atl_parametergruppen_pkey PRIMARY KEY (id);


--
-- TOC entry 4554 (class 2606 OID 5903214)
-- Name: atl_probeart_pkey; Type: CONSTRAINT; Schema: labor; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY probeart
    ADD CONSTRAINT atl_probeart_pkey PRIMARY KEY (id);


--
-- TOC entry 4491 (class 2606 OID 5903216)
-- Name: atl_probenahmen_pkey; Type: CONSTRAINT; Schema: labor; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY probenahme
    ADD CONSTRAINT atl_probenahmen_pkey PRIMARY KEY (id);


--
-- TOC entry 4487 (class 2606 OID 5903218)
-- Name: atl_probepkt_objektid_unique; Type: CONSTRAINT; Schema: labor; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY messstelle
    ADD CONSTRAINT atl_probepkt_objektid_unique UNIQUE (objektid);


--
-- TOC entry 4489 (class 2606 OID 5903220)
-- Name: atl_probepkt_pkey; Type: CONSTRAINT; Schema: labor; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY messstelle
    ADD CONSTRAINT atl_probepkt_pkey PRIMARY KEY (id);


--
-- TOC entry 4546 (class 2606 OID 5903222)
-- Name: atl_sielhaut_pkey; Type: CONSTRAINT; Schema: labor; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY sielhaut
    ADD CONSTRAINT atl_sielhaut_pkey PRIMARY KEY (id);


--
-- TOC entry 4556 (class 2606 OID 5903224)
-- Name: atl_status_pkey; Type: CONSTRAINT; Schema: labor; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY status
    ADD CONSTRAINT atl_status_pkey PRIMARY KEY (id);


SET search_path = oberflgw, pg_catalog;

--
-- TOC entry 4558 (class 2606 OID 5903234)
-- Name: afs_niederschlagswasser_pkey; Type: CONSTRAINT; Schema: oberflgw; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY afs_niederschlagswasser
    ADD CONSTRAINT afs_niederschlagswasser_pkey PRIMARY KEY (nr);


--
-- TOC entry 4560 (class 2606 OID 5903236)
-- Name: afs_stoffe_pkey; Type: CONSTRAINT; Schema: oberflgw; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY afs_stoffe
    ADD CONSTRAINT afs_stoffe_pkey PRIMARY KEY (anfallstellen_nr, stoff_nr);


--
-- TOC entry 4485 (class 2606 OID 5903244)
-- Name: entwaesserungsgrundstueck_pkey; Type: CONSTRAINT; Schema: oberflgw; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY entwaesserungsgrundstueck
    ADD CONSTRAINT entwaesserungsgrundstueck_pkey PRIMARY KEY (nr);


--
-- TOC entry 4562 (class 2606 OID 5903256)
-- Name: massnahme_pkey; Type: CONSTRAINT; Schema: oberflgw; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY massnahme
    ADD CONSTRAINT massnahme_pkey PRIMARY KEY (nr);


--
-- TOC entry 4564 (class 2606 OID 5903266)
-- Name: sb_entlastung_pkey; Type: CONSTRAINT; Schema: oberflgw; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY sb_entlastung
    ADD CONSTRAINT sb_entlastung_pkey PRIMARY KEY (nr);


--
-- TOC entry 4499 (class 2606 OID 5903268)
-- Name: sonderbauwerk_pkey; Type: CONSTRAINT; Schema: oberflgw; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY sonderbauwerk
    ADD CONSTRAINT sonderbauwerk_pkey PRIMARY KEY (nr);


--
-- TOC entry 4566 (class 2606 OID 5903274)
-- Name: versickerungsanlage_pkey; Type: CONSTRAINT; Schema: oberflgw; Owner: auikadmin; Tablespace: 
--

ALTER TABLE ONLY versickerungsanlage
    ADD CONSTRAINT versickerungsanlage_pkey PRIMARY KEY (nr);


SET search_path = public, pg_catalog;

--
-- TOC entry 4568 (class 2606 OID 5903278)
-- Name: con_streets_alkis; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tab_streets_alkis
    ADD CONSTRAINT con_streets_alkis PRIMARY KEY (gemeinde, name, nr);


SET search_path = basis, pg_catalog;


SET search_path = elka, pg_catalog;

--
-- TOC entry 4504 (class 1259 OID 5903280)
-- Name: fki_q_einleitungsstelle_fkey; Type: INDEX; Schema: elka; Owner: auikadmin; Tablespace: 
--

CREATE INDEX fki_q_einleitungsstelle_fkey ON referenz USING btree (q_els_nr);


--
-- TOC entry 4505 (class 1259 OID 5903281)
-- Name: fki_q_klaeranlage_fkey; Type: INDEX; Schema: elka; Owner: auikadmin; Tablespace: 
--

CREATE INDEX fki_q_klaeranlage_fkey ON referenz USING btree (q_ka_nr);


--
-- TOC entry 4506 (class 1259 OID 5903282)
-- Name: fki_z_einleitungsstelle_fkey; Type: INDEX; Schema: elka; Owner: auikadmin; Tablespace: 
--

CREATE INDEX fki_z_einleitungsstelle_fkey ON referenz USING btree (z_els_nr);


--
-- TOC entry 4507 (class 1259 OID 5903283)
-- Name: fki_z_klaeranlage_fkey; Type: INDEX; Schema: elka; Owner: auikadmin; Tablespace: 
--

CREATE INDEX fki_z_klaeranlage_fkey ON referenz USING btree (z_ka_nr);


SET search_path = indeinl, pg_catalog;

--
-- TOC entry 4524 (class 1259 OID 5903291)
-- Name: fki_49fachdaten_kontrollen; Type: INDEX; Schema: indeinl; Owner: auikadmin; Tablespace: 
--

CREATE INDEX fki_49fachdaten_kontrollen ON anh_49_kontrollen USING btree (anh49id);


SET search_path = public, pg_catalog;

--
-- TOC entry 4569 (class 1259 OID 5903293)
-- Name: name_alkis_ix; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX name_alkis_ix ON tab_streets_alkis USING btree (name);


--
-- TOC entry 4570 (class 1259 OID 5903294)
-- Name: strassen_alkis_ix; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX strassen_alkis_ix ON tab_streets_alkis USING gist (the_geom);

ALTER TABLE tab_streets_alkis CLUSTER ON strassen_alkis_ix;


SET search_path = labor, pg_catalog;

--
-- TOC entry 4786 (class 2618 OID 5903322)
-- Name: set_normwert; Type: RULE; Schema: labor; Owner: auikadmin
--

CREATE RULE set_normwert AS
    ON INSERT TO analyseposition DO NOTHING;


--
-- TOC entry 4657 (class 2620 OID 5903323)
-- Name: update_the_geom; Type: TRIGGER; Schema: labor; Owner: auikadmin
--

CREATE TRIGGER update_the_geom BEFORE INSERT OR UPDATE ON sielhaut FOR EACH ROW EXECUTE PROCEDURE public.update_the_geom();


SET search_path = awsv, pg_catalog;

--
-- TOC entry 4578 (class 2606 OID 5903324)
-- Name: fk3537b8a88b7385c8; Type: FK CONSTRAINT; Schema: awsv; Owner: auikadmin
--

ALTER TABLE ONLY fachdaten
    ADD CONSTRAINT fk3537b8a88b7385c8 FOREIGN KEY (objektid) REFERENCES basis.objekt(id);


--
-- TOC entry 4577 (class 2606 OID 5903329)
-- Name: fk50bd7a59ec7c6efa; Type: FK CONSTRAINT; Schema: awsv; Owner: auikadmin
--

ALTER TABLE ONLY anlagenchrono
    ADD CONSTRAINT fk50bd7a59ec7c6efa FOREIGN KEY (behaelterid) REFERENCES fachdaten(behaelterid);


--
-- TOC entry 4581 (class 2606 OID 5903334)
-- Name: fk52f46b053e50a20; Type: FK CONSTRAINT; Schema: awsv; Owner: auikadmin
--

ALTER TABLE ONLY verwaltungsgebuehren
    ADD CONSTRAINT fk52f46b053e50a20 FOREIGN KEY (gebuehrenart) REFERENCES gebuehrenarten(id);


--
-- TOC entry 4582 (class 2606 OID 5903339)
-- Name: fk52f46b05ec7c6efa; Type: FK CONSTRAINT; Schema: awsv; Owner: auikadmin
--

ALTER TABLE ONLY verwaltungsgebuehren
    ADD CONSTRAINT fk52f46b05ec7c6efa FOREIGN KEY (behaelterid) REFERENCES fachdaten(behaelterid);


--
-- TOC entry 4580 (class 2606 OID 5903344)
-- Name: fk5d09f108ec7c6efa; Type: FK CONSTRAINT; Schema: awsv; Owner: auikadmin
--

ALTER TABLE ONLY kontrollen
    ADD CONSTRAINT fk5d09f108ec7c6efa FOREIGN KEY (behaelterid) REFERENCES fachdaten(behaelterid);


--
-- TOC entry 4583 (class 2606 OID 5903349)
-- Name: fke8c3ed65ec7c6efa; Type: FK CONSTRAINT; Schema: awsv; Owner: auikadmin
--

ALTER TABLE ONLY verwaltungsverf
    ADD CONSTRAINT fke8c3ed65ec7c6efa FOREIGN KEY (behaelterid) REFERENCES fachdaten(behaelterid);


--
-- TOC entry 4575 (class 2606 OID 5903354)
-- Name: awsv_abfuellflaeche_behaelterid_fkey; Type: FK CONSTRAINT; Schema: awsv; Owner: auikadmin
--

ALTER TABLE ONLY abfuellflaeche
    ADD CONSTRAINT awsv_abfuellflaeche_behaelterid_fkey FOREIGN KEY (behaelterid) REFERENCES fachdaten(behaelterid);


--
-- TOC entry 4576 (class 2606 OID 5903359)
-- Name: awsv_abscheider_behaelterid_fkey; Type: FK CONSTRAINT; Schema: awsv; Owner: auikadmin
--

ALTER TABLE ONLY abscheider
    ADD CONSTRAINT awsv_abscheider_behaelterid_fkey FOREIGN KEY (behaelterid) REFERENCES fachdaten(behaelterid);


--
-- TOC entry 4579 (class 2606 OID 5903364)
-- Name: awsv_jgs_fachdaten_fkey; Type: FK CONSTRAINT; Schema: awsv; Owner: auikadmin
--

ALTER TABLE ONLY jgs
    ADD CONSTRAINT awsv_jgs_fachdaten_fkey FOREIGN KEY (behaelterid) REFERENCES fachdaten(behaelterid);


SET search_path = basis, pg_catalog;

--
-- TOC entry 4587 (class 2606 OID 5903369)
-- Name: basis_objekt_basis_sachbearbeiter_fkey; Type: FK CONSTRAINT; Schema: basis; Owner: auikadmin
--

ALTER TABLE ONLY objekt
    ADD CONSTRAINT basis_objekt_basis_sachbearbeiter_fkey FOREIGN KEY (sachbearbeiter) REFERENCES sachbearbeiter(id);


--
-- TOC entry 4588 (class 2606 OID 5903374)
-- Name: basis_objekt_basis_standort_fkey; Type: FK CONSTRAINT; Schema: basis; Owner: auikadmin
--

ALTER TABLE ONLY objekt
    ADD CONSTRAINT basis_objekt_basis_standort_fkey FOREIGN KEY (standortid) REFERENCES standort(id);


--
-- TOC entry 4590 (class 2606 OID 5903776)
-- Name: basis_objekt_inhaber_fkey; Type: FK CONSTRAINT; Schema: basis; Owner: auikadmin
--

ALTER TABLE ONLY objekt
    ADD CONSTRAINT basis_objekt_inhaber_fkey FOREIGN KEY (betreiberid) REFERENCES inhaber(id);


--
-- TOC entry 4591 (class 2606 OID 5903384)
-- Name: fk6471bfb8b7385c8; Type: FK CONSTRAINT; Schema: basis; Owner: auikadmin
--

ALTER TABLE ONLY objektchrono
    ADD CONSTRAINT fk6471bfb8b7385c8 FOREIGN KEY (objektid) REFERENCES objekt(id);



--
-- TOC entry 4592 (class 2606 OID 5903404)
-- Name: fk8d8baafa490baa6d; Type: FK CONSTRAINT; Schema: basis; Owner: auikadmin
--

ALTER TABLE ONLY objektverknuepfung
    ADD CONSTRAINT fk8d8baafa490baa6d FOREIGN KEY (objekt) REFERENCES objekt(id);


--
-- TOC entry 4593 (class 2606 OID 5903409)
-- Name: fk8d8baafa5f57144c; Type: FK CONSTRAINT; Schema: basis; Owner: auikadmin
--

ALTER TABLE ONLY objektverknuepfung
    ADD CONSTRAINT fk8d8baafa5f57144c FOREIGN KEY (ist_verknuepft_mit) REFERENCES objekt(id);


--
-- TOC entry 4589 (class 2606 OID 5903414)
-- Name: fkf8502b5882c7c6bd; Type: FK CONSTRAINT; Schema: basis; Owner: auikadmin
--

ALTER TABLE ONLY objekt
    ADD CONSTRAINT fkf8502b5882c7c6bd FOREIGN KEY (objektartid) REFERENCES objektarten(id);


--
-- TOC entry 4656 (class 2606 OID 5903771)
-- Name: inhaber_adresse_fkey; Type: FK CONSTRAINT; Schema: basis; Owner: auikadmin
--

ALTER TABLE ONLY inhaber
    ADD CONSTRAINT inhaber_adresse_fkey FOREIGN KEY (adresseid) REFERENCES adresse(id);


--
-- TOC entry 4655 (class 2606 OID 5903766)
-- Name: inhaber_wirtschaftsz_fkey; Type: FK CONSTRAINT; Schema: basis; Owner: auikadmin
--

ALTER TABLE ONLY inhaber
    ADD CONSTRAINT inhaber_wirtschaftsz_fkey FOREIGN KEY (wirtschaftszweigid) REFERENCES wirtschaftszweig(id);


SET search_path = elka, pg_catalog;

--
-- TOC entry 4594 (class 2606 OID 5903424)
-- Name: fkecb352f88b7385c8; Type: FK CONSTRAINT; Schema: elka; Owner: auikadmin
--

ALTER TABLE ONLY wasserrecht
    ADD CONSTRAINT fkecb352f88b7385c8 FOREIGN KEY (objektid) REFERENCES basis.objekt(id);


--
-- TOC entry 4612 (class 2606 OID 5903429)
-- Name: referenz_q_aba_nr_fkey; Type: FK CONSTRAINT; Schema: elka; Owner: auikadmin
--

ALTER TABLE ONLY referenz
    ADD CONSTRAINT referenz_q_aba_nr_fkey FOREIGN KEY (q_aba_nr) REFERENCES aba(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4613 (class 2606 OID 5903434)
-- Name: referenz_q_afs_nr_fkey; Type: FK CONSTRAINT; Schema: elka; Owner: auikadmin
--

ALTER TABLE ONLY referenz
    ADD CONSTRAINT referenz_q_afs_nr_fkey FOREIGN KEY (q_afs_nr) REFERENCES anfallstelle(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4614 (class 2606 OID 5903439)
-- Name: referenz_q_entl_nr_fkey; Type: FK CONSTRAINT; Schema: elka; Owner: auikadmin
--

ALTER TABLE ONLY referenz
    ADD CONSTRAINT referenz_q_entl_nr_fkey FOREIGN KEY (q_entl_nr) REFERENCES oberflgw.sb_entlastung(nr) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4615 (class 2606 OID 5903444)
-- Name: referenz_q_msst_nr_fkey; Type: FK CONSTRAINT; Schema: elka; Owner: auikadmin
--

ALTER TABLE ONLY referenz
    ADD CONSTRAINT referenz_q_msst_nr_fkey FOREIGN KEY (q_msst_nr) REFERENCES labor.messstelle(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4616 (class 2606 OID 5903449)
-- Name: referenz_q_nw_afs_nr_fkey; Type: FK CONSTRAINT; Schema: elka; Owner: auikadmin
--

ALTER TABLE ONLY referenz
    ADD CONSTRAINT referenz_q_nw_afs_nr_fkey FOREIGN KEY (q_nw_afs_nr) REFERENCES oberflgw.afs_niederschlagswasser(nr) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4617 (class 2606 OID 5903454)
-- Name: referenz_q_sb_nr_fkey; Type: FK CONSTRAINT; Schema: elka; Owner: auikadmin
--

ALTER TABLE ONLY referenz
    ADD CONSTRAINT referenz_q_sb_nr_fkey FOREIGN KEY (q_sb_nr) REFERENCES oberflgw.sonderbauwerk(nr) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4618 (class 2606 OID 5903459)
-- Name: referenz_z_aba_nr_fkey; Type: FK CONSTRAINT; Schema: elka; Owner: auikadmin
--

ALTER TABLE ONLY referenz
    ADD CONSTRAINT referenz_z_aba_nr_fkey FOREIGN KEY (z_aba_nr) REFERENCES aba(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4619 (class 2606 OID 5903464)
-- Name: referenz_z_afs_nr_fkey; Type: FK CONSTRAINT; Schema: elka; Owner: auikadmin
--

ALTER TABLE ONLY referenz
    ADD CONSTRAINT referenz_z_afs_nr_fkey FOREIGN KEY (z_afs_nr) REFERENCES anfallstelle(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4620 (class 2606 OID 5903469)
-- Name: referenz_z_entl_nr_fkey; Type: FK CONSTRAINT; Schema: elka; Owner: auikadmin
--

ALTER TABLE ONLY referenz
    ADD CONSTRAINT referenz_z_entl_nr_fkey FOREIGN KEY (z_entl_nr) REFERENCES oberflgw.sb_entlastung(nr) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4621 (class 2606 OID 5903474)
-- Name: referenz_z_msst_nr_fkey; Type: FK CONSTRAINT; Schema: elka; Owner: auikadmin
--

ALTER TABLE ONLY referenz
    ADD CONSTRAINT referenz_z_msst_nr_fkey FOREIGN KEY (z_msst_nr) REFERENCES labor.messstelle(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4622 (class 2606 OID 5903479)
-- Name: referenz_z_nw_afs_nr_fkey; Type: FK CONSTRAINT; Schema: elka; Owner: auikadmin
--

ALTER TABLE ONLY referenz
    ADD CONSTRAINT referenz_z_nw_afs_nr_fkey FOREIGN KEY (z_nw_afs_nr) REFERENCES oberflgw.afs_niederschlagswasser(nr) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4623 (class 2606 OID 5903484)
-- Name: referenz_z_sb_nr_fkey; Type: FK CONSTRAINT; Schema: elka; Owner: auikadmin
--

ALTER TABLE ONLY referenz
    ADD CONSTRAINT referenz_z_sb_nr_fkey FOREIGN KEY (z_sb_nr) REFERENCES oberflgw.sonderbauwerk(nr) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4610 (class 2606 OID 5903489)
-- Name: z_aba_verfahren_abwasbehverf_nr_fkey; Type: FK CONSTRAINT; Schema: elka; Owner: auikadmin
--

ALTER TABLE ONLY z_aba_verfahren
    ADD CONSTRAINT z_aba_verfahren_abwasbehverf_nr_fkey FOREIGN KEY (abwasbehverf_nr) REFERENCES abaverfahren(nr);


--
-- TOC entry 4611 (class 2606 OID 5903494)
-- Name: z_aba_verfahren_anlage_nr_fkey; Type: FK CONSTRAINT; Schema: elka; Owner: auikadmin
--

ALTER TABLE ONLY z_aba_verfahren
    ADD CONSTRAINT z_aba_verfahren_anlage_nr_fkey FOREIGN KEY (anlage_nr) REFERENCES aba(id);


SET search_path = indeinl, pg_catalog;

--
-- TOC entry 4624 (class 2606 OID 5903853)
-- Name: anfallstelle_40_fachdaten_fkey; Type: FK CONSTRAINT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY anh_40_fachdaten
    ADD CONSTRAINT anfallstelle_40_fachdaten_fkey FOREIGN KEY (anfallstelleid) REFERENCES elka.anfallstelle(id);


--
-- TOC entry 4628 (class 2606 OID 5903858)
-- Name: anfallstelle_49_fachdaten_fkey; Type: FK CONSTRAINT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY anh_49_fachdaten
    ADD CONSTRAINT anfallstelle_49_fachdaten_fkey FOREIGN KEY (anfallstelleid) REFERENCES elka.anfallstelle(id);


--
-- TOC entry 4631 (class 2606 OID 5903863)
-- Name: anfallstelle_50_fachdaten_fkey; Type: FK CONSTRAINT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY anh_50_fachdaten
    ADD CONSTRAINT anfallstelle_50_fachdaten_fkey FOREIGN KEY (anfallstelleid) REFERENCES elka.anfallstelle(id);


--
-- TOC entry 4632 (class 2606 OID 5903868)
-- Name: anfallstelle_52_fachdaten_fkey; Type: FK CONSTRAINT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY anh_52_fachdaten
    ADD CONSTRAINT anfallstelle_52_fachdaten_fkey FOREIGN KEY (anfallstelleid) REFERENCES elka.anfallstelle(id);


--
-- TOC entry 4633 (class 2606 OID 5903873)
-- Name: anfallstelle_53_fachdaten_fkey; Type: FK CONSTRAINT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY anh_53_fachdaten
    ADD CONSTRAINT anfallstelle_53_fachdaten_fkey FOREIGN KEY (anfallstelleid) REFERENCES elka.anfallstelle(id);


--
-- TOC entry 4634 (class 2606 OID 5903878)
-- Name: anfallstelle_55_fachdaten_fkey; Type: FK CONSTRAINT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY anh_55_fachdaten
    ADD CONSTRAINT anfallstelle_55_fachdaten_fkey FOREIGN KEY (anfallstelleid) REFERENCES elka.anfallstelle(id);


--
-- TOC entry 4635 (class 2606 OID 5903883)
-- Name: anfallstelle_56_fachdaten_fkey; Type: FK CONSTRAINT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY anh_56_fachdaten
    ADD CONSTRAINT anfallstelle_56_fachdaten_fkey FOREIGN KEY (anfallstelleid) REFERENCES elka.anfallstelle(id);


--
-- TOC entry 4636 (class 2606 OID 5903888)
-- Name: anfallstelle_bwk_fachdaten_fkey; Type: FK CONSTRAINT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY bwk_fachdaten
    ADD CONSTRAINT anfallstelle_bwk_fachdaten_fkey FOREIGN KEY (anfallstelleid) REFERENCES elka.anfallstelle(id);


--
-- TOC entry 4625 (class 2606 OID 5903499)
-- Name: anh49_abfuhr_anh49_fachdaten_fkey; Type: FK CONSTRAINT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY anh_49_abfuhr
    ADD CONSTRAINT anh49_abfuhr_anh49_fachdaten_fkey FOREIGN KEY (anh49id) REFERENCES anh_49_fachdaten(id);


--
-- TOC entry 4626 (class 2606 OID 5903504)
-- Name: anh49_abscheiderdetails_anh49_fachdaten_fkey; Type: FK CONSTRAINT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY anh_49_abscheiderdetails
    ADD CONSTRAINT anh49_abscheiderdetails_anh49_fachdaten_fkey FOREIGN KEY (anh49id) REFERENCES anh_49_fachdaten(id);


--
-- TOC entry 4627 (class 2606 OID 5903509)
-- Name: anh49_analysen_anh49_fachdaten_fkey; Type: FK CONSTRAINT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY anh_49_analysen
    ADD CONSTRAINT anh49_analysen_anh49_fachdaten_fkey FOREIGN KEY (anh49id) REFERENCES anh_49_fachdaten(id);


--
-- TOC entry 4629 (class 2606 OID 5903514)
-- Name: anh49_kontrolle_anh49_fachdaten_fkey; Type: FK CONSTRAINT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY anh_49_kontrollen
    ADD CONSTRAINT anh49_kontrolle_anh49_fachdaten_fkey FOREIGN KEY (anh49id) REFERENCES anh_49_fachdaten(id);


--
-- TOC entry 4630 (class 2606 OID 5903544)
-- Name: fk1ef5f3605afcac2e; Type: FK CONSTRAINT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY anh_50_fachdaten
    ADD CONSTRAINT fk1ef5f3605afcac2e FOREIGN KEY (entsorgerid) REFERENCES entsorger(id);


--
-- TOC entry 4637 (class 2606 OID 5903559)
-- Name: fk85fdf8588b7385c8; Type: FK CONSTRAINT; Schema: indeinl; Owner: auikadmin
--

ALTER TABLE ONLY suev_fachdaten
    ADD CONSTRAINT fk85fdf8588b7385c8 FOREIGN KEY (objektid) REFERENCES basis.objekt(id);


SET search_path = labor, pg_catalog;

--
-- TOC entry 4640 (class 2606 OID 5903579)
-- Name: atl_meta_parameter_einheit_id_fkey; Type: FK CONSTRAINT; Schema: labor; Owner: auikadmin
--

ALTER TABLE ONLY meta_parameter
    ADD CONSTRAINT atl_meta_parameter_einheit_id_fkey FOREIGN KEY (einheit_id) REFERENCES einheiten(id);


--
-- TOC entry 4641 (class 2606 OID 5903584)
-- Name: atl_meta_parameter_ordnungsbegriff_fkey; Type: FK CONSTRAINT; Schema: labor; Owner: auikadmin
--

ALTER TABLE ONLY meta_parameter
    ADD CONSTRAINT atl_meta_parameter_ordnungsbegriff_fkey FOREIGN KEY (ordnungsbegriff) REFERENCES parameter(ordnungsbegriff);


--
-- TOC entry 4601 (class 2606 OID 5903589)
-- Name: atl_probemahmen_basis_sachbearbeiter_fkey; Type: FK CONSTRAINT; Schema: labor; Owner: auikadmin
--

ALTER TABLE ONLY probenahme
    ADD CONSTRAINT atl_probemahmen_basis_sachbearbeiter_fkey FOREIGN KEY (sachbearbeiter) REFERENCES basis.sachbearbeiter(id);


--
-- TOC entry 4602 (class 2606 OID 5903594)
-- Name: atl_probenahmen_atl_probepkt_fkey; Type: FK CONSTRAINT; Schema: labor; Owner: auikadmin
--

ALTER TABLE ONLY probenahme
    ADD CONSTRAINT atl_probenahmen_atl_probepkt_fkey FOREIGN KEY (messstid) REFERENCES messstelle(id);


--
-- TOC entry 4597 (class 2606 OID 5903599)
-- Name: atl_probepkt_basis_sachbearbeiter_fkey; Type: FK CONSTRAINT; Schema: labor; Owner: auikadmin
--

ALTER TABLE ONLY messstelle
    ADD CONSTRAINT atl_probepkt_basis_sachbearbeiter_fkey FOREIGN KEY (sachbearbeiter) REFERENCES basis.sachbearbeiter(id);


--
-- TOC entry 4639 (class 2606 OID 5903604)
-- Name: atl_sielhaut_atl_probepkt_fkey; Type: FK CONSTRAINT; Schema: labor; Owner: auikadmin
--

ALTER TABLE ONLY sielhaut
    ADD CONSTRAINT atl_sielhaut_atl_probepkt_fkey FOREIGN KEY (probepktid) REFERENCES messstelle(id);


--
-- TOC entry 4598 (class 2606 OID 5903609)
-- Name: fk3a5a1f0f5d55491f; Type: FK CONSTRAINT; Schema: labor; Owner: auikadmin
--

ALTER TABLE ONLY messstelle
    ADD CONSTRAINT fk3a5a1f0f5d55491f FOREIGN KEY (art_id) REFERENCES probeart(id);


--
-- TOC entry 4599 (class 2606 OID 5903614)
-- Name: fk3a5a1f0f8b7385c8; Type: FK CONSTRAINT; Schema: labor; Owner: auikadmin
--

ALTER TABLE ONLY messstelle
    ADD CONSTRAINT fk3a5a1f0f8b7385c8 FOREIGN KEY (objektid) REFERENCES basis.objekt(id);


--
-- TOC entry 4600 (class 2606 OID 5903619)
-- Name: fk3a5a1f0fb128db5a; Type: FK CONSTRAINT; Schema: labor; Owner: auikadmin
--

ALTER TABLE ONLY messstelle
    ADD CONSTRAINT fk3a5a1f0fb128db5a FOREIGN KEY (ka_id) REFERENCES klaeranlage(id);


--
-- TOC entry 4604 (class 2606 OID 5903624)
-- Name: fk3d2450f610b7d285; Type: FK CONSTRAINT; Schema: labor; Owner: auikadmin
--

ALTER TABLE ONLY analyseposition
    ADD CONSTRAINT fk3d2450f610b7d285 FOREIGN KEY (einheiten_id) REFERENCES einheiten(id);


--
-- TOC entry 4605 (class 2606 OID 5903629)
-- Name: fk3d2450f6ac5a119; Type: FK CONSTRAINT; Schema: labor; Owner: auikadmin
--

ALTER TABLE ONLY analyseposition
    ADD CONSTRAINT fk3d2450f6ac5a119 FOREIGN KEY (probenahme_id) REFERENCES probenahme(id);


--
-- TOC entry 4606 (class 2606 OID 5903634)
-- Name: fk3d2450f6d4eaa7c5; Type: FK CONSTRAINT; Schema: labor; Owner: auikadmin
--

ALTER TABLE ONLY analyseposition
    ADD CONSTRAINT fk3d2450f6d4eaa7c5 FOREIGN KEY (parameter_id) REFERENCES parameter(ordnungsbegriff);


--
-- TOC entry 4603 (class 2606 OID 5903639)
-- Name: fk7989e7abc0be5519; Type: FK CONSTRAINT; Schema: labor; Owner: auikadmin
--

ALTER TABLE ONLY probenahme
    ADD CONSTRAINT fk7989e7abc0be5519 FOREIGN KEY (status) REFERENCES status(id);


--
-- TOC entry 4638 (class 2606 OID 5903644)
-- Name: fkc85f7d837f84beb5; Type: FK CONSTRAINT; Schema: labor; Owner: auikadmin
--

ALTER TABLE ONLY parameter
    ADD CONSTRAINT fkc85f7d837f84beb5 FOREIGN KEY (parametergruppe_id) REFERENCES parametergruppen(id);


SET search_path = oberflgw, pg_catalog;

--
-- TOC entry 4642 (class 2606 OID 5903649)
-- Name: afs_niederschlagswasser_anfallstellen_nr_fkey; Type: FK CONSTRAINT; Schema: oberflgw; Owner: auikadmin
--

ALTER TABLE ONLY afs_niederschlagswasser
    ADD CONSTRAINT afs_niederschlagswasser_anfallstellen_nr_fkey FOREIGN KEY (anfallstellen_nr) REFERENCES elka.anfallstelle(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4643 (class 2606 OID 5903654)
-- Name: afs_niederschlagswasser_entw_grund_nr_fkey; Type: FK CONSTRAINT; Schema: oberflgw; Owner: auikadmin
--

ALTER TABLE ONLY afs_niederschlagswasser
    ADD CONSTRAINT afs_niederschlagswasser_entw_grund_nr_fkey FOREIGN KEY (entw_grund_nr) REFERENCES entwaesserungsgrundstueck(nr) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4644 (class 2606 OID 5903659)
-- Name: afs_stoffe_anfallstellen_nr_fkey; Type: FK CONSTRAINT; Schema: oberflgw; Owner: auikadmin
--

ALTER TABLE ONLY afs_stoffe
    ADD CONSTRAINT afs_stoffe_anfallstellen_nr_fkey FOREIGN KEY (anfallstellen_nr) REFERENCES elka.anfallstelle(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4595 (class 2606 OID 5903664)
-- Name: entwaesserungsgrundstueck_objekt_id_fkey; Type: FK CONSTRAINT; Schema: oberflgw; Owner: auikadmin
--

ALTER TABLE ONLY entwaesserungsgrundstueck
    ADD CONSTRAINT entwaesserungsgrundstueck_objekt_id_fkey FOREIGN KEY (objekt_id) REFERENCES basis.objekt(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4596 (class 2606 OID 5903669)
-- Name: entwaesserungsgrundstueck_wasserecht_nr_fkey; Type: FK CONSTRAINT; Schema: oberflgw; Owner: auikadmin
--

ALTER TABLE ONLY entwaesserungsgrundstueck
    ADD CONSTRAINT entwaesserungsgrundstueck_wasserecht_nr_fkey FOREIGN KEY (wasserecht_nr) REFERENCES elka.wasserrecht(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 4645 (class 2606 OID 5903674)
-- Name: msst_berichtspflicht_msst_nr_fkey; Type: FK CONSTRAINT; Schema: oberflgw; Owner: auikadmin
--

ALTER TABLE ONLY msst_berichtspflicht
    ADD CONSTRAINT msst_berichtspflicht_msst_nr_fkey FOREIGN KEY (msst_nr) REFERENCES labor.messstelle(objektid);


--
-- TOC entry 4646 (class 2606 OID 5903679)
-- Name: sb_entlastung_sb_nr_fkey; Type: FK CONSTRAINT; Schema: oberflgw; Owner: auikadmin
--

ALTER TABLE ONLY sb_entlastung
    ADD CONSTRAINT sb_entlastung_sb_nr_fkey FOREIGN KEY (sb_nr) REFERENCES sonderbauwerk(nr) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4607 (class 2606 OID 5903684)
-- Name: sonderbauwerk_anspr_adr_nr_fkey; Type: FK CONSTRAINT; Schema: oberflgw; Owner: auikadmin
--

ALTER TABLE ONLY sonderbauwerk
    ADD CONSTRAINT sonderbauwerk_anspr_adr_nr_fkey FOREIGN KEY (anspr_adr_nr) REFERENCES basis.adresse(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 4608 (class 2606 OID 5903689)
-- Name: sonderbauwerk_objekt_id_fkey; Type: FK CONSTRAINT; Schema: oberflgw; Owner: auikadmin
--

ALTER TABLE ONLY sonderbauwerk
    ADD CONSTRAINT sonderbauwerk_objekt_id_fkey FOREIGN KEY (objekt_id) REFERENCES basis.objekt(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 4609 (class 2606 OID 5903694)
-- Name: sonderbauwerk_wasserrecht_genehmigung_nr_fkey; Type: FK CONSTRAINT; Schema: oberflgw; Owner: auikadmin
--

ALTER TABLE ONLY sonderbauwerk
    ADD CONSTRAINT sonderbauwerk_wasserrecht_genehmigung_nr_fkey FOREIGN KEY (wasserrecht_genehmigung_nr) REFERENCES elka.wasserrecht(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 4647 (class 2606 OID 5903699)
-- Name: versickerungsanlage_els_nr_fkey; Type: FK CONSTRAINT; Schema: oberflgw; Owner: auikadmin
--

ALTER TABLE ONLY versickerungsanlage
    ADD CONSTRAINT versickerungsanlage_els_nr_fkey FOREIGN KEY (els_nr) REFERENCES elka.einleitungsstelle(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 4649 (class 2606 OID 5903704)
-- Name: z_betrieb_massnahme_betrieb_nr_fkey; Type: FK CONSTRAINT; Schema: oberflgw; Owner: auikadmin
--

ALTER TABLE ONLY z_betrieb_massnahme
    ADD CONSTRAINT z_betrieb_massnahme_betrieb_nr_fkey FOREIGN KEY (betrieb_nr) REFERENCES basis.adresse(id);


--
-- TOC entry 4648 (class 2606 OID 5903709)
-- Name: z_betrieb_massnahme_massnahme_nr_fkey; Type: FK CONSTRAINT; Schema: oberflgw; Owner: auikadmin
--

ALTER TABLE ONLY z_betrieb_massnahme
    ADD CONSTRAINT z_betrieb_massnahme_massnahme_nr_fkey FOREIGN KEY (massnahme_nr) REFERENCES massnahme(nr);


--
-- TOC entry 4651 (class 2606 OID 5903714)
-- Name: z_entwaessgr_abwasbehverf_abwasbehverf_nr_fkey; Type: FK CONSTRAINT; Schema: oberflgw; Owner: auikadmin
--

ALTER TABLE ONLY z_entwaessgr_abwasbehverf
    ADD CONSTRAINT z_entwaessgr_abwasbehverf_abwasbehverf_nr_fkey FOREIGN KEY (abwasbehverf_nr) REFERENCES elka.abaverfahren(nr);


--
-- TOC entry 4650 (class 2606 OID 5903719)
-- Name: z_entwaessgr_abwasbehverf_entw_grund_nr_fkey; Type: FK CONSTRAINT; Schema: oberflgw; Owner: auikadmin
--

ALTER TABLE ONLY z_entwaessgr_abwasbehverf
    ADD CONSTRAINT z_entwaessgr_abwasbehverf_entw_grund_nr_fkey FOREIGN KEY (entw_grund_nr) REFERENCES entwaesserungsgrundstueck(nr);


--
-- TOC entry 4652 (class 2606 OID 5903724)
-- Name: z_rbf_schutzgueter_sb_nr_fkey; Type: FK CONSTRAINT; Schema: oberflgw; Owner: auikadmin
--

ALTER TABLE ONLY z_rbf_schutzgueter
    ADD CONSTRAINT z_rbf_schutzgueter_sb_nr_fkey FOREIGN KEY (sb_nr) REFERENCES sonderbauwerk(nr);


--
-- TOC entry 4653 (class 2606 OID 5903729)
-- Name: z_sb_regeln_sb_nr_fkey; Type: FK CONSTRAINT; Schema: oberflgw; Owner: auikadmin
--

ALTER TABLE ONLY z_sb_regeln
    ADD CONSTRAINT z_sb_regeln_sb_nr_fkey FOREIGN KEY (sb_nr) REFERENCES sonderbauwerk(nr);


--
-- TOC entry 4654 (class 2606 OID 5903734)
-- Name: z_sb_verfahren_sb_nr_fkey; Type: FK CONSTRAINT; Schema: oberflgw; Owner: auikadmin
--

ALTER TABLE ONLY z_sb_verfahren
    ADD CONSTRAINT z_sb_verfahren_sb_nr_fkey FOREIGN KEY (sb_nr) REFERENCES sonderbauwerk(nr);


--
-- TOC entry 4806 (class 0 OID 0)
-- Dependencies: 6
-- Name: awsv; Type: ACL; Schema: -; Owner: auikadmin
--

REVOKE ALL ON SCHEMA awsv FROM PUBLIC;
REVOKE ALL ON SCHEMA awsv FROM auikadmin;
GRANT ALL ON SCHEMA awsv TO auikadmin;
GRANT ALL ON SCHEMA awsv TO PUBLIC;


--
-- TOC entry 4807 (class 0 OID 0)
-- Dependencies: 7
-- Name: basis; Type: ACL; Schema: -; Owner: auikadmin
--

REVOKE ALL ON SCHEMA basis FROM PUBLIC;
REVOKE ALL ON SCHEMA basis FROM auikadmin;
GRANT ALL ON SCHEMA basis TO auikadmin;
GRANT ALL ON SCHEMA basis TO PUBLIC;


--
-- TOC entry 4808 (class 0 OID 0)
-- Dependencies: 8
-- Name: elka; Type: ACL; Schema: -; Owner: auikadmin
--

REVOKE ALL ON SCHEMA elka FROM PUBLIC;
REVOKE ALL ON SCHEMA elka FROM auikadmin;
GRANT ALL ON SCHEMA elka TO auikadmin;
GRANT ALL ON SCHEMA elka TO PUBLIC;


--
-- TOC entry 4809 (class 0 OID 0)
-- Dependencies: 9
-- Name: indeinl; Type: ACL; Schema: -; Owner: auikadmin
--

REVOKE ALL ON SCHEMA indeinl FROM PUBLIC;
REVOKE ALL ON SCHEMA indeinl FROM auikadmin;
GRANT ALL ON SCHEMA indeinl TO auikadmin;
GRANT ALL ON SCHEMA indeinl TO PUBLIC;


--
-- TOC entry 4810 (class 0 OID 0)
-- Dependencies: 10
-- Name: labor; Type: ACL; Schema: -; Owner: auikadmin
--

REVOKE ALL ON SCHEMA labor FROM PUBLIC;
REVOKE ALL ON SCHEMA labor FROM auikadmin;
GRANT ALL ON SCHEMA labor TO auikadmin;
GRANT ALL ON SCHEMA labor TO PUBLIC;


--
-- TOC entry 4811 (class 0 OID 0)
-- Dependencies: 11
-- Name: oberflgw; Type: ACL; Schema: -; Owner: auikadmin
--

REVOKE ALL ON SCHEMA oberflgw FROM PUBLIC;
REVOKE ALL ON SCHEMA oberflgw FROM auikadmin;
GRANT ALL ON SCHEMA oberflgw TO auikadmin;
GRANT ALL ON SCHEMA oberflgw TO PUBLIC;


--
-- TOC entry 4813 (class 0 OID 0)
-- Dependencies: 12
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
GRANT ALL ON SCHEMA public TO auikadmin;


SET search_path = public, pg_catalog;

--
-- TOC entry 4817 (class 0 OID 0)
-- Dependencies: 1515
-- Name: dblink_connect_u(text); Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON FUNCTION dblink_connect_u(text) FROM PUBLIC;
REVOKE ALL ON FUNCTION dblink_connect_u(text) FROM postgres;
GRANT ALL ON FUNCTION dblink_connect_u(text) TO postgres;


--
-- TOC entry 4818 (class 0 OID 0)
-- Dependencies: 1516
-- Name: dblink_connect_u(text, text); Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON FUNCTION dblink_connect_u(text, text) FROM PUBLIC;
REVOKE ALL ON FUNCTION dblink_connect_u(text, text) FROM postgres;
GRANT ALL ON FUNCTION dblink_connect_u(text, text) TO postgres;


SET search_path = awsv, pg_catalog;

--
-- TOC entry 4852 (class 0 OID 0)
-- Dependencies: 210
-- Name: awsv_abfuellflaeche_id_seq; Type: ACL; Schema: awsv; Owner: auikadmin
--

REVOKE ALL ON SEQUENCE awsv_abfuellflaeche_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE awsv_abfuellflaeche_id_seq FROM auikadmin;
GRANT ALL ON SEQUENCE awsv_abfuellflaeche_id_seq TO auikadmin;
GRANT ALL ON SEQUENCE awsv_abfuellflaeche_id_seq TO PUBLIC;


--
-- TOC entry 4854 (class 0 OID 0)
-- Dependencies: 211
-- Name: awsv_abscheider_id_seq; Type: ACL; Schema: awsv; Owner: auikadmin
--

REVOKE ALL ON SEQUENCE awsv_abscheider_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE awsv_abscheider_id_seq FROM auikadmin;
GRANT ALL ON SEQUENCE awsv_abscheider_id_seq TO auikadmin;
GRANT ALL ON SEQUENCE awsv_abscheider_id_seq TO PUBLIC;


--
-- TOC entry 4857 (class 0 OID 0)
-- Dependencies: 213
-- Name: awsv_jgs_id_seq; Type: ACL; Schema: awsv; Owner: auikadmin
--

REVOKE ALL ON SEQUENCE awsv_jgs_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE awsv_jgs_id_seq FROM auikadmin;
GRANT ALL ON SEQUENCE awsv_jgs_id_seq TO auikadmin;
GRANT ALL ON SEQUENCE awsv_jgs_id_seq TO PUBLIC;


SET search_path = basis, pg_catalog;

--
-- TOC entry 4861 (class 0 OID 0)
-- Dependencies: 222
-- Name: adresse; Type: ACL; Schema: basis; Owner: auikadmin
--

REVOKE ALL ON TABLE adresse FROM PUBLIC;
REVOKE ALL ON TABLE adresse FROM auikadmin;
GRANT ALL ON TABLE adresse TO auikadmin;
GRANT ALL ON TABLE adresse TO PUBLIC;


--
-- TOC entry 4868 (class 0 OID 0)
-- Dependencies: 233
-- Name: basis_sachbearbeiter_id_seq; Type: ACL; Schema: basis; Owner: auikadmin
--

REVOKE ALL ON SEQUENCE basis_sachbearbeiter_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE basis_sachbearbeiter_id_seq FROM auikadmin;
GRANT ALL ON SEQUENCE basis_sachbearbeiter_id_seq TO auikadmin;
GRANT ALL ON SEQUENCE basis_sachbearbeiter_id_seq TO PUBLIC;


--
-- TOC entry 4870 (class 0 OID 0)
-- Dependencies: 235
-- Name: basis_standort_id_seq; Type: ACL; Schema: basis; Owner: auikadmin
--

REVOKE ALL ON SEQUENCE basis_standort_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE basis_standort_id_seq FROM auikadmin;
GRANT ALL ON SEQUENCE basis_standort_id_seq TO auikadmin;
GRANT ALL ON SEQUENCE basis_standort_id_seq TO PUBLIC;


--
-- TOC entry 4872 (class 0 OID 0)
-- Dependencies: 241
-- Name: view_two_way_objektverknuepfung; Type: ACL; Schema: basis; Owner: auikadmin
--

REVOKE ALL ON TABLE view_two_way_objektverknuepfung FROM PUBLIC;
REVOKE ALL ON TABLE view_two_way_objektverknuepfung FROM auikadmin;
GRANT ALL ON TABLE view_two_way_objektverknuepfung TO auikadmin;
GRANT ALL ON TABLE view_two_way_objektverknuepfung TO PUBLIC;


SET search_path = elka, pg_catalog;

--
-- TOC entry 4873 (class 0 OID 0)
-- Dependencies: 243
-- Name: _q_z; Type: ACL; Schema: elka; Owner: auikadmin
--

REVOKE ALL ON TABLE _q_z FROM PUBLIC;
REVOKE ALL ON TABLE _q_z FROM auikadmin;
GRANT ALL ON TABLE _q_z TO auikadmin;
GRANT ALL ON TABLE _q_z TO PUBLIC;


SET search_path = oberflgw, pg_catalog;

--
-- TOC entry 4874 (class 0 OID 0)
-- Dependencies: 250
-- Name: entwaesserungsgrundstueck; Type: ACL; Schema: oberflgw; Owner: auikadmin
--

REVOKE ALL ON TABLE entwaesserungsgrundstueck FROM PUBLIC;
REVOKE ALL ON TABLE entwaesserungsgrundstueck FROM auikadmin;
GRANT ALL ON TABLE entwaesserungsgrundstueck TO auikadmin;
GRANT ALL ON TABLE entwaesserungsgrundstueck TO PUBLIC;


SET search_path = elka, pg_catalog;

--
-- TOC entry 4875 (class 0 OID 0)
-- Dependencies: 253
-- Name: map_elka_einheit; Type: ACL; Schema: elka; Owner: auikadmin
--

REVOKE ALL ON TABLE map_elka_einheit FROM PUBLIC;
REVOKE ALL ON TABLE map_elka_einheit FROM auikadmin;
GRANT ALL ON TABLE map_elka_einheit TO auikadmin;
GRANT SELECT ON TABLE map_elka_einheit TO PUBLIC;


--
-- TOC entry 4876 (class 0 OID 0)
-- Dependencies: 254
-- Name: map_elka_stoff; Type: ACL; Schema: elka; Owner: auikadmin
--

REVOKE ALL ON TABLE map_elka_stoff FROM PUBLIC;
REVOKE ALL ON TABLE map_elka_stoff FROM auikadmin;
GRANT ALL ON TABLE map_elka_stoff TO auikadmin;
GRANT SELECT ON TABLE map_elka_stoff TO PUBLIC;


SET search_path = oberflgw, pg_catalog;

--
-- TOC entry 4877 (class 0 OID 0)
-- Dependencies: 256
-- Name: sonderbauwerk; Type: ACL; Schema: oberflgw; Owner: auikadmin
--

REVOKE ALL ON TABLE sonderbauwerk FROM PUBLIC;
REVOKE ALL ON TABLE sonderbauwerk FROM auikadmin;
GRANT ALL ON TABLE sonderbauwerk TO auikadmin;
GRANT ALL ON TABLE sonderbauwerk TO PUBLIC;


SET search_path = elka, pg_catalog;

--
-- TOC entry 4878 (class 0 OID 0)
-- Dependencies: 257
-- Name: z_aba_verfahren; Type: ACL; Schema: elka; Owner: auikadmin
--

REVOKE ALL ON TABLE z_aba_verfahren FROM PUBLIC;
REVOKE ALL ON TABLE z_aba_verfahren FROM auikadmin;
GRANT ALL ON TABLE z_aba_verfahren TO auikadmin;
GRANT ALL ON TABLE z_aba_verfahren TO PUBLIC;


--
-- TOC entry 4879 (class 0 OID 0)
-- Dependencies: 258
-- Name: e_z_aba_verfahren; Type: ACL; Schema: elka; Owner: auikadmin
--

REVOKE ALL ON TABLE e_z_aba_verfahren FROM PUBLIC;
REVOKE ALL ON TABLE e_z_aba_verfahren FROM auikadmin;
GRANT ALL ON TABLE e_z_aba_verfahren TO auikadmin;
GRANT ALL ON TABLE e_z_aba_verfahren TO PUBLIC;


--
-- TOC entry 4884 (class 0 OID 0)
-- Dependencies: 263
-- Name: elka_referenz_nr_seq; Type: ACL; Schema: elka; Owner: auikadmin
--

REVOKE ALL ON SEQUENCE elka_referenz_nr_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE elka_referenz_nr_seq FROM auikadmin;
GRANT ALL ON SEQUENCE elka_referenz_nr_seq TO auikadmin;
GRANT ALL ON SEQUENCE elka_referenz_nr_seq TO PUBLIC;


--
-- TOC entry 4886 (class 0 OID 0)
-- Dependencies: 264
-- Name: elka_wasserrecht_id_seq; Type: ACL; Schema: elka; Owner: auikadmin
--

REVOKE ALL ON SEQUENCE elka_wasserrecht_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE elka_wasserrecht_id_seq FROM auikadmin;
GRANT ALL ON SEQUENCE elka_wasserrecht_id_seq TO auikadmin;
GRANT ALL ON SEQUENCE elka_wasserrecht_id_seq TO PUBLIC;


--
-- TOC entry 4887 (class 0 OID 0)
-- Dependencies: 265
-- Name: map_elka_anhang; Type: ACL; Schema: elka; Owner: auikadmin
--

REVOKE ALL ON TABLE map_elka_anhang FROM PUBLIC;
REVOKE ALL ON TABLE map_elka_anhang FROM auikadmin;
GRANT ALL ON TABLE map_elka_anhang TO auikadmin;
GRANT SELECT ON TABLE map_elka_anhang TO PUBLIC;


SET search_path = indeinl, pg_catalog;

--
-- TOC entry 4890 (class 0 OID 0)
-- Dependencies: 268
-- Name: anh_40_fachdaten_id_seq; Type: ACL; Schema: indeinl; Owner: auikadmin
--

REVOKE ALL ON SEQUENCE anh_40_fachdaten_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE anh_40_fachdaten_id_seq FROM auikadmin;
GRANT ALL ON SEQUENCE anh_40_fachdaten_id_seq TO auikadmin;
GRANT ALL ON SEQUENCE anh_40_fachdaten_id_seq TO PUBLIC;


--
-- TOC entry 4892 (class 0 OID 0)
-- Dependencies: 270
-- Name: anh_49_abfuhr_id_seq; Type: ACL; Schema: indeinl; Owner: auikadmin
--

REVOKE ALL ON SEQUENCE anh_49_abfuhr_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE anh_49_abfuhr_id_seq FROM auikadmin;
GRANT ALL ON SEQUENCE anh_49_abfuhr_id_seq TO auikadmin;
GRANT ALL ON SEQUENCE anh_49_abfuhr_id_seq TO PUBLIC;


--
-- TOC entry 4896 (class 0 OID 0)
-- Dependencies: 276
-- Name: anh_49_fachdaten_id_seq; Type: ACL; Schema: indeinl; Owner: auikadmin
--

REVOKE ALL ON SEQUENCE anh_49_fachdaten_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE anh_49_fachdaten_id_seq FROM auikadmin;
GRANT ALL ON SEQUENCE anh_49_fachdaten_id_seq TO auikadmin;
GRANT ALL ON SEQUENCE anh_49_fachdaten_id_seq TO PUBLIC;


--
-- TOC entry 4898 (class 0 OID 0)
-- Dependencies: 278
-- Name: anh_49_kontrollen_id_seq; Type: ACL; Schema: indeinl; Owner: auikadmin
--

REVOKE ALL ON SEQUENCE anh_49_kontrollen_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE anh_49_kontrollen_id_seq FROM auikadmin;
GRANT ALL ON SEQUENCE anh_49_kontrollen_id_seq TO auikadmin;
GRANT ALL ON SEQUENCE anh_49_kontrollen_id_seq TO PUBLIC;


--
-- TOC entry 4900 (class 0 OID 0)
-- Dependencies: 280
-- Name: anh_50_fachdaten_id_seq; Type: ACL; Schema: indeinl; Owner: auikadmin
--

REVOKE ALL ON SEQUENCE anh_50_fachdaten_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE anh_50_fachdaten_id_seq FROM auikadmin;
GRANT ALL ON SEQUENCE anh_50_fachdaten_id_seq TO auikadmin;
GRANT ALL ON SEQUENCE anh_50_fachdaten_id_seq TO PUBLIC;


--
-- TOC entry 4902 (class 0 OID 0)
-- Dependencies: 282
-- Name: anh_52_fachdaten_id_seq; Type: ACL; Schema: indeinl; Owner: auikadmin
--

REVOKE ALL ON SEQUENCE anh_52_fachdaten_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE anh_52_fachdaten_id_seq FROM auikadmin;
GRANT ALL ON SEQUENCE anh_52_fachdaten_id_seq TO auikadmin;
GRANT ALL ON SEQUENCE anh_52_fachdaten_id_seq TO PUBLIC;


--
-- TOC entry 4904 (class 0 OID 0)
-- Dependencies: 284
-- Name: anh_53_fachdaten_id_seq; Type: ACL; Schema: indeinl; Owner: auikadmin
--

REVOKE ALL ON SEQUENCE anh_53_fachdaten_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE anh_53_fachdaten_id_seq FROM auikadmin;
GRANT ALL ON SEQUENCE anh_53_fachdaten_id_seq TO auikadmin;
GRANT ALL ON SEQUENCE anh_53_fachdaten_id_seq TO PUBLIC;


--
-- TOC entry 4906 (class 0 OID 0)
-- Dependencies: 286
-- Name: anh_55_fachdaten_id_seq; Type: ACL; Schema: indeinl; Owner: auikadmin
--

REVOKE ALL ON SEQUENCE anh_55_fachdaten_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE anh_55_fachdaten_id_seq FROM auikadmin;
GRANT ALL ON SEQUENCE anh_55_fachdaten_id_seq TO auikadmin;
GRANT ALL ON SEQUENCE anh_55_fachdaten_id_seq TO PUBLIC;


--
-- TOC entry 4908 (class 0 OID 0)
-- Dependencies: 288
-- Name: anh_56_fachdaten_id_seq; Type: ACL; Schema: indeinl; Owner: auikadmin
--

REVOKE ALL ON SEQUENCE anh_56_fachdaten_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE anh_56_fachdaten_id_seq FROM auikadmin;
GRANT ALL ON SEQUENCE anh_56_fachdaten_id_seq TO auikadmin;
GRANT ALL ON SEQUENCE anh_56_fachdaten_id_seq TO PUBLIC;


--
-- TOC entry 4910 (class 0 OID 0)
-- Dependencies: 290
-- Name: anh_bwk_fachdaten_id_seq; Type: ACL; Schema: indeinl; Owner: auikadmin
--

REVOKE ALL ON SEQUENCE anh_bwk_fachdaten_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE anh_bwk_fachdaten_id_seq FROM auikadmin;
GRANT ALL ON SEQUENCE anh_bwk_fachdaten_id_seq TO auikadmin;
GRANT ALL ON SEQUENCE anh_bwk_fachdaten_id_seq TO PUBLIC;


--
-- TOC entry 4912 (class 0 OID 0)
-- Dependencies: 292
-- Name: anh_suev_fachdaten_id_seq; Type: ACL; Schema: indeinl; Owner: auikadmin
--

REVOKE ALL ON SEQUENCE anh_suev_fachdaten_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE anh_suev_fachdaten_id_seq FROM auikadmin;
GRANT ALL ON SEQUENCE anh_suev_fachdaten_id_seq TO auikadmin;
GRANT ALL ON SEQUENCE anh_suev_fachdaten_id_seq TO PUBLIC;


SET search_path = labor, pg_catalog;

--
-- TOC entry 4913 (class 0 OID 0)
-- Dependencies: 297
-- Name: atl_sielhaut_id_seq; Type: ACL; Schema: labor; Owner: auikadmin
--

REVOKE ALL ON SEQUENCE atl_sielhaut_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE atl_sielhaut_id_seq FROM auikadmin;
GRANT ALL ON SEQUENCE atl_sielhaut_id_seq TO auikadmin;
GRANT ALL ON SEQUENCE atl_sielhaut_id_seq TO PUBLIC;


--
-- TOC entry 4917 (class 0 OID 0)
-- Dependencies: 302
-- Name: atl_probepkt_id_seq; Type: ACL; Schema: labor; Owner: auikadmin
--

REVOKE ALL ON SEQUENCE atl_probepkt_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE atl_probepkt_id_seq FROM auikadmin;
GRANT ALL ON SEQUENCE atl_probepkt_id_seq TO auikadmin;
GRANT ALL ON SEQUENCE atl_probepkt_id_seq TO PUBLIC;


--
-- TOC entry 4919 (class 0 OID 0)
-- Dependencies: 308
-- Name: view_atl_analyseposition_all; Type: ACL; Schema: labor; Owner: auikadmin
--

REVOKE ALL ON TABLE view_atl_analyseposition_all FROM PUBLIC;
REVOKE ALL ON TABLE view_atl_analyseposition_all FROM auikadmin;
GRANT ALL ON TABLE view_atl_analyseposition_all TO auikadmin;
GRANT ALL ON TABLE view_atl_analyseposition_all TO PUBLIC;


SET search_path = oberflgw, pg_catalog;

--
-- TOC entry 4923 (class 0 OID 0)
-- Dependencies: 315
-- Name: afs_niederschlagswasser; Type: ACL; Schema: oberflgw; Owner: auikadmin
--

REVOKE ALL ON TABLE afs_niederschlagswasser FROM PUBLIC;
REVOKE ALL ON TABLE afs_niederschlagswasser FROM auikadmin;
GRANT ALL ON TABLE afs_niederschlagswasser TO auikadmin;
GRANT ALL ON TABLE afs_niederschlagswasser TO PUBLIC;


--
-- TOC entry 4924 (class 0 OID 0)
-- Dependencies: 316
-- Name: afs_stoffe; Type: ACL; Schema: oberflgw; Owner: auikadmin
--

REVOKE ALL ON TABLE afs_stoffe FROM PUBLIC;
REVOKE ALL ON TABLE afs_stoffe FROM auikadmin;
GRANT ALL ON TABLE afs_stoffe TO auikadmin;
GRANT ALL ON TABLE afs_stoffe TO PUBLIC;


--
-- TOC entry 4925 (class 0 OID 0)
-- Dependencies: 317
-- Name: igl_p_einl_id_seq; Type: ACL; Schema: oberflgw; Owner: auikadmin
--

REVOKE ALL ON SEQUENCE igl_p_einl_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE igl_p_einl_id_seq FROM auikadmin;
GRANT ALL ON SEQUENCE igl_p_einl_id_seq TO auikadmin;
GRANT ALL ON SEQUENCE igl_p_einl_id_seq TO PUBLIC;


--
-- TOC entry 4926 (class 0 OID 0)
-- Dependencies: 318
-- Name: massnahme; Type: ACL; Schema: oberflgw; Owner: auikadmin
--

REVOKE ALL ON TABLE massnahme FROM PUBLIC;
REVOKE ALL ON TABLE massnahme FROM auikadmin;
GRANT ALL ON TABLE massnahme TO auikadmin;
GRANT ALL ON TABLE massnahme TO PUBLIC;


--
-- TOC entry 4927 (class 0 OID 0)
-- Dependencies: 319
-- Name: msst_berichtspflicht; Type: ACL; Schema: oberflgw; Owner: auikadmin
--

REVOKE ALL ON TABLE msst_berichtspflicht FROM PUBLIC;
REVOKE ALL ON TABLE msst_berichtspflicht FROM auikadmin;
GRANT ALL ON TABLE msst_berichtspflicht TO auikadmin;
GRANT ALL ON TABLE msst_berichtspflicht TO PUBLIC;


--
-- TOC entry 4929 (class 0 OID 0)
-- Dependencies: 321
-- Name: sb_entlastung; Type: ACL; Schema: oberflgw; Owner: auikadmin
--

REVOKE ALL ON TABLE sb_entlastung FROM PUBLIC;
REVOKE ALL ON TABLE sb_entlastung FROM auikadmin;
GRANT ALL ON TABLE sb_entlastung TO auikadmin;
GRANT ALL ON TABLE sb_entlastung TO PUBLIC;


--
-- TOC entry 4930 (class 0 OID 0)
-- Dependencies: 322
-- Name: versickerungsanlage; Type: ACL; Schema: oberflgw; Owner: auikadmin
--

REVOKE ALL ON TABLE versickerungsanlage FROM PUBLIC;
REVOKE ALL ON TABLE versickerungsanlage FROM auikadmin;
GRANT ALL ON TABLE versickerungsanlage TO auikadmin;
GRANT ALL ON TABLE versickerungsanlage TO PUBLIC;


--
-- TOC entry 4931 (class 0 OID 0)
-- Dependencies: 323
-- Name: z_betrieb_massnahme; Type: ACL; Schema: oberflgw; Owner: auikadmin
--

REVOKE ALL ON TABLE z_betrieb_massnahme FROM PUBLIC;
REVOKE ALL ON TABLE z_betrieb_massnahme FROM auikadmin;
GRANT ALL ON TABLE z_betrieb_massnahme TO auikadmin;
GRANT ALL ON TABLE z_betrieb_massnahme TO PUBLIC;


--
-- TOC entry 4933 (class 0 OID 0)
-- Dependencies: 325
-- Name: z_entwaessgr_abwasbehverf; Type: ACL; Schema: oberflgw; Owner: auikadmin
--

REVOKE ALL ON TABLE z_entwaessgr_abwasbehverf FROM PUBLIC;
REVOKE ALL ON TABLE z_entwaessgr_abwasbehverf FROM auikadmin;
GRANT ALL ON TABLE z_entwaessgr_abwasbehverf TO auikadmin;
GRANT ALL ON TABLE z_entwaessgr_abwasbehverf TO PUBLIC;


--
-- TOC entry 4935 (class 0 OID 0)
-- Dependencies: 327
-- Name: z_rbf_schutzgueter; Type: ACL; Schema: oberflgw; Owner: auikadmin
--

REVOKE ALL ON TABLE z_rbf_schutzgueter FROM PUBLIC;
REVOKE ALL ON TABLE z_rbf_schutzgueter FROM auikadmin;
GRANT ALL ON TABLE z_rbf_schutzgueter TO auikadmin;
GRANT ALL ON TABLE z_rbf_schutzgueter TO PUBLIC;


--
-- TOC entry 4937 (class 0 OID 0)
-- Dependencies: 329
-- Name: z_sb_regeln; Type: ACL; Schema: oberflgw; Owner: auikadmin
--

REVOKE ALL ON TABLE z_sb_regeln FROM PUBLIC;
REVOKE ALL ON TABLE z_sb_regeln FROM auikadmin;
GRANT ALL ON TABLE z_sb_regeln TO auikadmin;
GRANT ALL ON TABLE z_sb_regeln TO PUBLIC;


--
-- TOC entry 4939 (class 0 OID 0)
-- Dependencies: 331
-- Name: z_sb_verfahren; Type: ACL; Schema: oberflgw; Owner: auikadmin
--

REVOKE ALL ON TABLE z_sb_verfahren FROM PUBLIC;
REVOKE ALL ON TABLE z_sb_verfahren FROM auikadmin;
GRANT ALL ON TABLE z_sb_verfahren TO auikadmin;
GRANT ALL ON TABLE z_sb_verfahren TO PUBLIC;


SET search_path = public, pg_catalog;

--
-- TOC entry 4943 (class 0 OID 0)
-- Dependencies: 337
-- Name: hibernate_sequence; Type: ACL; Schema: public; Owner: auikadmin
--

REVOKE ALL ON SEQUENCE hibernate_sequence FROM PUBLIC;
REVOKE ALL ON SEQUENCE hibernate_sequence FROM auikadmin;
GRANT ALL ON SEQUENCE hibernate_sequence TO auikadmin;
GRANT ALL ON SEQUENCE hibernate_sequence TO PUBLIC;


--
-- TOC entry 4945 (class 0 OID 0)
-- Dependencies: 338
-- Name: tab_streets_alkis; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE tab_streets_alkis FROM PUBLIC;
REVOKE ALL ON TABLE tab_streets_alkis FROM postgres;
GRANT ALL ON TABLE tab_streets_alkis TO postgres;
GRANT ALL ON TABLE tab_streets_alkis TO auikadmin;
GRANT SELECT ON TABLE tab_streets_alkis TO PUBLIC;

SET search_path = labor, pg_catalog;

--
-- TOC entry 4453 (class 0 OID 5245992)
-- Dependencies: 375
-- Data for Name: parametergruppen; Type: TABLE DATA; Schema: labor; Owner: auikadmin
--

INSERT INTO parametergruppen VALUES (3, 'E-Satzung', NULL, true, false);
INSERT INTO parametergruppen VALUES (1, 'LHKW (leichtfl.halogen.KW)', 0, true, false);
INSERT INTO parametergruppen VALUES (2, 'Benzol und Derivate', 0, true, false);


--
-- TOC entry 4454 (class 0 OID 5245943)
-- Dependencies: 365
-- Data for Name: parameter; Type: TABLE DATA; Schema: labor; Owner: auikadmin
--

INSERT INTO parameter VALUES ('B00107', '', 'Trockensubstanz', 63, NULL, NULL, 1, 0, 0, true, NULL, NULL, NULL, NULL, true, false, 1469);
INSERT INTO parameter VALUES ('B00159', '', 'Formaldehyd (HCHO)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, 3003);
INSERT INTO parameter VALUES ('B00112', '', 'PCB 138', 42, NULL, NULL, NULL, 0.050000000000000003, 0, true, NULL, NULL, NULL, NULL, true, false, 2074);
INSERT INTO parameter VALUES ('B00109', '', 'PCB 28', 42, NULL, NULL, NULL, 0.050000000000000003, 0, true, NULL, NULL, NULL, NULL, true, false, 2071);
INSERT INTO parameter VALUES ('B00110', '', 'PCB 52', 42, NULL, NULL, NULL, 0.050000000000000003, 0, true, NULL, NULL, NULL, NULL, true, false, 2072);
INSERT INTO parameter VALUES ('B00602', '', 'lipophile Stoffe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, 1570);
INSERT INTO parameter VALUES ('B00338', '', 'Absetzbare Stoffe', 46, 10, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, 1452);
INSERT INTO parameter VALUES ('B00030', '', 'Durchsichtigkeit', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, 1035);
INSERT INTO parameter VALUES ('B00018', '', 'Phosphat (PO4) ges.', 42, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, NULL, true, false, 1261);
INSERT INTO parameter VALUES ('B00034', '', 'Phosphat (PO4) ges.', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, 1261);
INSERT INTO parameter VALUES ('L10610', '', 'pH-Wert', 0, 10, NULL, NULL, 0, 0, true, NULL, NULL, NULL, NULL, true, true, 1061);
INSERT INTO parameter VALUES ('L10820', '', 'Leitfähigkeit', 73, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, NULL, true, true, 1082);
INSERT INTO parameter VALUES ('L20020', '', 'Tetrachlormethan (CCl4)', 44, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, 2002);
INSERT INTO parameter VALUES ('Z00001', NULL, 'EDTA', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, 2605);
INSERT INTO parameter VALUES ('L10420', '', 'Geruch', 0, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, 1042);
INSERT INTO parameter VALUES ('L10110', '', 'Temperatur', 61, 25, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, 1011);
INSERT INTO parameter VALUES ('B00130', '', 'Xylole, Summe (C6H4-(CH3)2)', 44, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, 3, NULL, true, false, 2913);
INSERT INTO parameter VALUES ('L20030', '', 'Tribrommethan (HCBr3)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, 2003);
INSERT INTO parameter VALUES ('N00001', NULL, 'DEHP', 42, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, NULL, true, false, 2679);
INSERT INTO parameter VALUES ('L15210', '', 'DOC', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, 1521);
INSERT INTO parameter VALUES ('B30114', '', 'Abgrabung', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00431', NULL, 'MTBE', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00070', '', 'Barium (Ba)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, 3, NULL, true, false, 1124);
INSERT INTO parameter VALUES ('B00041', '', 'Eisen (Fe)', 42, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, NULL, true, false, 1182);
INSERT INTO parameter VALUES ('L11120', '', 'Natrium (Na)', 42, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, NULL, true, false, 1112);
INSERT INTO parameter VALUES ('L11710', '', 'Mangan (Mn)', 42, NULL, NULL, NULL, 2500, 0, true, NULL, NULL, NULL, NULL, true, false, 1171);
INSERT INTO parameter VALUES ('L12490', '', 'Ammonium- Stickstoff (N) NH4-N', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, 1249);
INSERT INTO parameter VALUES ('B00113', '', 'PCB 153', 42, NULL, NULL, NULL, 0.050000000000000003, 0, true, NULL, NULL, NULL, 57, true, false, 2076);
INSERT INTO parameter VALUES ('B00409', '', 'Ausgasung', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00337', '', 'Bacterium pyocyaneum', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00007', '', 'Nitrat (NO3)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, 1244);
INSERT INTO parameter VALUES ('B00127', '', 'ortho-Phosphat (o-PO4)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 78, true, false, 1263);
INSERT INTO parameter VALUES ('B00111', '', 'PCB 101', 42, NULL, NULL, NULL, 0.050000000000000003, 0, true, NULL, NULL, NULL, NULL, true, false, 2073);
INSERT INTO parameter VALUES ('B00114', '', 'PCB 180', 42, NULL, NULL, NULL, 0.050000000000000003, 0, true, NULL, NULL, NULL, 63, true, false, 2077);
INSERT INTO parameter VALUES ('Z00005', NULL, 'Trockenrückstand', 133, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, true, false, NULL);
INSERT INTO parameter VALUES ('Z00006', NULL, 'Gefriertrockenrückstand', 133, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false, false, NULL);
INSERT INTO parameter VALUES ('B00116', '', 'Benzol (C6H6)', 44, 50, NULL, NULL, NULL, 0, true, NULL, NULL, 2, NULL, true, false, 2048);
INSERT INTO parameter VALUES ('B00129', '', 'Ethylbenzol (C6H5-CH2-CH3)', 44, 500, NULL, NULL, NULL, 0, true, NULL, NULL, 3, NULL, true, false, 2415);
INSERT INTO parameter VALUES ('L13310', NULL, 'Chlorid (Cl)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, 3, 55, true, false, 1331);
INSERT INTO parameter VALUES ('P00010', NULL, 'Ammonium (NH4)', 42, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, true, false, 1248);
INSERT INTO parameter VALUES ('B00102', '', 'Bromdichlormethan (HCBrCl2)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, 2006);
INSERT INTO parameter VALUES ('L12450', '', 'Nitrat-Stickstoff (N) NO3-N', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, 1245);
INSERT INTO parameter VALUES ('L11130', '', 'Kalium (K) K2O', 63, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, NULL, true, false, NULL);
INSERT INTO parameter VALUES ('B00380', '', 'Abbaubarkeit (Nr. 407 AbwV)', 63, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, 3, NULL, true, false, 1609);
INSERT INTO parameter VALUES ('N00003', NULL, 'Schwefel', 63, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, NULL, true, false, NULL);
INSERT INTO parameter VALUES ('L11310', '', 'Aluminium (Al)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, 3, 21, true, false, 1131);
INSERT INTO parameter VALUES ('L15610', '', 'Anionische Tenside (MBAS)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, 3, NULL, true, false, 1561);
INSERT INTO parameter VALUES ('B00035', '', 'Chloride (Cl)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L11220', '', 'Calcium (Ca) ges. als CaO', 63, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, 62, true, false, NULL);
INSERT INTO parameter VALUES ('B00020', '', 'Chrom (Cr) ges.', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, 1151);
INSERT INTO parameter VALUES ('B00039', '', 'Chromate (CrO4)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00080', '', 'Farbe', 0, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, 1023);
INSERT INTO parameter VALUES ('B00165', '', 'Geschmack', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L11210', '', 'Magnesium (Mg) MgO', 63, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, 64, true, false, NULL);
INSERT INTO parameter VALUES ('B00383', '', '1.2.4-Trimethylbenzol', 44, 500, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 92, true, false, NULL);
INSERT INTO parameter VALUES ('B00607', '', 'Bismut-Komplexierungsindex', 42, NULL, NULL, NULL, NULL, 34.509999999999998, true, NULL, NULL, 3, 54, true, false, 1562);
INSERT INTO parameter VALUES ('L10350', '', 'Trübung', 0, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, 1035);
INSERT INTO parameter VALUES ('L13130', '', 'Sulfat (SO4)', 42, 600, NULL, NULL, NULL, 9.5199999999999996, true, NULL, NULL, 3, 15, true, false, 1313);
INSERT INTO parameter VALUES ('L13380', NULL, 'Chlor, freies (Cl2)', 42, 0.5, NULL, NULL, NULL, 5.9500000000000002, true, NULL, NULL, 3, NULL, true, false, 1338);
INSERT INTO parameter VALUES ('B00155', '', '1,1-Dichlorethan (HCCl2-CH3)', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00290', '', '1,1,2-Trichlorethan (HCCl2-CClH2)', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00217', '', '1,12-Benzperylen', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00166', '', '2-Chlorphenol', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00142', '', '2,2`,3,4,4`,5,5`-Heptachlorobiphenyl', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00140', '', '2,2`,3,4,4`,5`-Hexachlorobiphenyl', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00141', '', '2,2`,4,4`,5,5`-Hexachlorobiphenyl', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00139', '', '2,2`,4,5,5`-Pentachlorobiphenyl', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00138', '', '2,2`,5,5`-Tetrachlorobiphenyl', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00171', '', '2,3,5,6-Tetrachlorphenol', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00169', '', '2,3,6-Trichlorphenol', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00167', '', '2,4-Dichlorphenol', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00170', '', '2,4,6-Trichlorphenol', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00168', '', '2,6-Dichlorphenol', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00158', '', 'Ameisensäure (HCOOH)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00147', '', 'Ammoniak (NH3)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00025', '', 'Anionenaktive Detergentien', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00023', '', 'Anzahl der Organismen', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00046', '', 'Atrazin', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00154', '', 'Butan (C4H10)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00022', '', 'Chemischer Index', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00036', '', 'Detergentien', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00031', '', 'Entf. d. Methylenblau- Probe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00160', '', 'Erdalkalien, Summe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00152', '', 'Ethan (C2H6)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L10230', '', 'Färbung', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00083', '', 'Gesamt-Phosphat-Phosphor (PO4-P)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00177', '', 'Glührückstand', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00172', '', 'HKW (Halogenierte Kohlenwasserstoffe)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00026', '', 'Hydrogencarbonat (HCO3-)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00069', NULL, 'Ionenbilanzfehler', 63, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00174', '', 'Keimzahl in Gelatine', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00175', '', 'Kohlensäure, kalk-aggr., rechn.', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00144', '', 'Kohlensäure, kalkaggr. n. Heyer', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00157', '', 'Kohlenstoff, gesamt (C)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L16900', '', 'Koloniezahl in Agar 20 Cel', 42, 100, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L16901', '', 'Koloniezahl in Agar 36 Cel', 42, 100, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00088', '', 'Leichtfl.organ.Chlorverb.', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00151', '', 'Lufttemperatur', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00081', '', 'Methylenblauprobe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00164', '', 'n-Hexan-extrahierbare Stoffe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00032', '', 'organischer Stickstoff (N)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00037', '', 'Petroläther extrahierbare Stoffe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00042', '', 'Phenol (C6H5OH)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00149', '', 'Phosphin (PH3)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00148', '', 'Phosphorwasserstoff (PH3)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00153', '', 'Propan (C3H8)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00312', '', 'Pseudomonas aeruginosa', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00085', '', 'Saprobienindex', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00146', '', 'Scheinbare Karbonathärte', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00047', '', 'Simazin', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00349', '', 'Vinylchlorid (CH2=CHCl)', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00086', '', 'Wassergüteklasse', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00220', '', 'Dibrommethan', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, NULL);
INSERT INTO parameter VALUES ('B00603', '', 'CSB/BSB5-Verhältnis', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00236', '', 'n-Propylbenzol (C6H5-C3H7)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00202', '', 'Gesamtmineralstoffgehalt', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00218', '', '11,12-Benzfluoranthen', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00222', '', '2,3,4,6-Tetrachlorphenol', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00238', '', '3,4-Benzfluoranthen', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00221', '', '4-Chlorphenol', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00287', '', 'Aluminium, gelöst', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00286', '', 'Anionen, Summe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00237', '', 'Aromatische Kohlenwasserstoffe, Summe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00288', '', 'Basenkapazität bis pH 8.2', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00206', '', 'Beryllium (Be)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00191', '', 'Bromid (Br-)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00181', '', 'Campylobacter', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00194', '', 'Carbonat (CO3--)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00207', '', 'Cäsium (Cs)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00216', '', 'Chloroform-extrahierbare Stoffe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00187', '', 'Chrom-III', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00143', '', 'CKW (Chlorkohlenwasserstoffe, Summe)', 42, 0, NULL, NULL, 0, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00416', '', 'CSB-Abbautest', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00163', '', 'trans-1,2-Dichlorethen (HCCl=CClH)', 44, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, 2029);
INSERT INTO parameter VALUES ('B00273', '', 'Phosphor (P2O5) ges.', 63, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, NULL, true, false, NULL);
INSERT INTO parameter VALUES ('B00198', '', 'Radioaktivität', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00131', '', 'Cumol (C6H5-CH-(CH3)2)', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00197', '', 'Dichte', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00227', '', 'EOX', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00184', '', 'Fette', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00234', '', 'Freon 113', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00009', '', 'Gesamt-Härte', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00226', '', 'Hydrazin (H2N-NH2)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00193', '', 'Hydrogenphosphat (HPO4--)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00192', '', 'Jodid (J-)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00285', '', 'Kationen, Summe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00239', '', 'Keimzahl bei 20 Grad C', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00240', '', 'Keimzahl bei 37 Grad C', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00214', '', 'Kieselsäure (H2SiO3)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L15310', '', 'KMnO4-Verbrauch', 42, 20, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 73, true, true, NULL);
INSERT INTO parameter VALUES ('B00123', '', 'Kohlendioxid (CO2)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 76, true, true, NULL);
INSERT INTO parameter VALUES ('B00204', '', 'Kohlendioxid, frei (CO2)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00228', '', 'Kohlensäure, kalkangreifend', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00229', '', 'Koloniezahl', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00200', '', 'Lithium (Li)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00196', '', 'Luftdruck', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00201', '', 'meta-Borsäure (HBO2-)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00224', '', 'meta-Kresol', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00283', '', 'Metazachlor', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00282', '', 'Methabenzthiazuron', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00121', '', 'Methanol (H3COH)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 74, true, true, NULL);
INSERT INTO parameter VALUES ('B00281', '', 'Metobromuron', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00284', '', 'Metolachlor', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00208', '', 'Molybdän (Mo)', 42, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00185', '', 'Öle', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00189', '', 'organische Chlorverbindungen', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00223', '', 'ortho-Kresol', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00225', '', 'para-Kresol', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00178', '', 'pH-Wert nach CaCO3-Sättigung', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00183', '', 'Phenole', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L10720', '', 'Redoxpotential', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00199', '', 'Restaktivität', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00209', '', 'Rubidium (Rb)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L12810', '', 'Sauerstoff (O2)', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00289', '', 'Säurekapazität bis pH 8.2', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00230', '', 'Schwebestoffe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00124', '', 'Schwefelwasserstoff (H2S)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 77, true, true, NULL);
INSERT INTO parameter VALUES ('B00231', '', 'Trichlorfluormethan (CFCl3)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00190', '', 'Strontium', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00235', '', 'Styrol (C6H5-CH=CH2)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00232', '', 'Trichlortrifluorethan (C2Cl3F3)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00211', '', 'Uran (U)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00215', '', 'UV-Extinktion bei 436 nm (SAK 436)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00212', '', 'Vanadium (Va)', 42, NULL, NULL, NULL, 100, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00271', '', '2,4,5-Trichlorphenol', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00243', '', 'Benzo-b-Pyrrol', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00263', '', 'Benzo(b)fluoranthen', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00264', '', 'Benzo(ghi)perylen', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00262', '', 'Benzo(k)fluoranthen', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B30001', '', 'Boden', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00274', '', 'Calciumcarbonatsättigung', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00254', '', 'Chlorbenzol', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00267', '', 'Chlorphenole', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00277', '', 'Chlortoluron', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00251', '', 'Citratlösung nach KOSER', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00303', '', 'Decan (H3C-(CH2)8-CH3)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B30113', '', 'Deponie in Betrieb', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00276', '', 'Desethylatrazin', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00255', '', 'Dichlorbenzole', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00280', '', 'Diuron', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00305', '', 'Dodecan (H3C-(CH2)10-CH3)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00272', '', 'Fäulnisfähigkeit', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B30021', '', 'Fläche', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B30111', '', 'Forstwirtschaftliche Nutzfläche', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('BK0001', '', 'Gattung/Art/Sorte', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B30102', '', 'Gewerbegebäude', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00245', '', 'Haloforme, Summe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B30003', '', 'Haushaltsmüll', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00300', '', 'Hexachlorbenzol', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00259', '', 'Hexachlorbutadien', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00258', '', 'Hexachlorethan', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00275', '', 'Hydrolysierbares Phosphat', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00242', '', 'Indol', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B30004', '', 'Industriemüll', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00278', '', 'Isoproturon', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B30105', '', 'Kleingärten', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B30103', '', 'Landwirtschaftliche Gebäude', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B30110', '', 'Landwirtschaftliche Nutzfläche', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00248', '', 'Methylrotprobe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00279', '', 'Metoxuron', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00244', '', 'Natriumhydrogencarbonat (NaHCO3)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B30120', '', 'nicht bebaubar', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00265', '', 'Nichtcarbonathärte', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00302', '', 'Nonan (H3C-(CH2)7-CH3)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00301', '', 'Octan (H3C-(CH2)6-CH3)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B30104', '', 'Öffentliche Gebäude', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('BK0002', '', 'Ortslage, inner-/außerhalb', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B30107', '', 'Park', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B30108', '', 'Park mit Spielplatz', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B30109', '', 'Parkplatz', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00299', '', 'Pentachlorbenzol', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00261', '', 'Pentachlorethan', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('BK0004', '', 'Pflanzebene', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('BK0003', '', 'Pflanzung', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00293', '', 'Phenanthren', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00266', '', 'Polyphosphate', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B30010', '', 'Prioritätengruppe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00250', '', 'Säurebildner', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B30106', '', 'Sportanlagen', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00252', '', 'Sulfit (SO3)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00270', '', 'Summe Calcium (Ca) und Magnesium (Mg)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00257', '', 'Tetrachlorbenzole', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00260', '', 'Tetrachlorethan', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00269', '', 'Toxizitätstest (TTC-Test)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00256', '', 'Trichlorbenzole', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00306', '', 'Tridecan (H3C-(CH2)11-CH3)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('BK0011', '', 'Überdeckung: Vegetationsfläche', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00304', '', 'Undecan (H3C-(CH2)9-CH3)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B30112', '', 'Unland', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00249', '', 'Voges-Proskauer-Reaktion', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B30116', '', 'Wasserschutzgebiet, ausgewiesen', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B30117', '', 'Wasserschutzgebiet, geplant', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B30101', '', 'Wohngebäude', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('BK0022', '', 'Wurzeln', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00342', '', '10821', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00343', '', '2-Methyl-Naphthalin', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00344', '', '2,6-Dimethyl-Naphthalin', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L14310', '', 'Abdampfrückstand', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00381', '', 'Abfiltrierbare Stoffe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00370', '', 'Acenaphthen', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00369', '', 'Acenaphthylen', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00382', '', 'Aliphatische Kohlenwasserstoffe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00404', '', 'Anionenequivalente, Summe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00372', '', 'Anthracen', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B30022', '', 'Volumen', 14, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00375', '', 'Benzo-a-anthracen', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00365', '', 'Bromacil', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00395', '', 'Buturon', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L20070', '', 'Chlordibrommethan (HCClBr2)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00389', '', 'Chloridazon', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00399', '', 'Chloroxuron', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00400', '', 'Chlorpropham', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00374', '', 'Chrysen', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00392', '', 'Cyanazin', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00350', '', 'Cyanwasserstoff (HCN)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00386', '', 'Desisopropylatrazin', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00376', '', 'Dibenzo-(a,h,i)-anthracen', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00407', '', 'Dichlordifluormethan (CF2Cl2)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L20050', '', 'Dichlorethan (H2CCl-CClH2)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00341', '', 'Diphenyl', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00311', '', 'Fäkalstreptokokken', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00388', '', 'Fenuron', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L16710', '', 'Fischgiftigkeit', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L23000', '', 'Fluoranthen', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00371', '', 'Fluoren', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L15000', '', 'Gelöste Stoffe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00314', '', 'Gesamtkoloniezahl (EV K 5, 48h, 20 Grd)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00268', '', 'Harnstoff (O=C-(NH2)2)', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00345', '', 'Heizöl', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00335', '', 'Heptan (C7H16)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00309', '', 'Hexadecan (H3C-(CH2)14-CH3)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00334', '', 'Hexan (C6H14)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00390', '', 'Hexazinon', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L23300', '', 'Indeno-(1,2,3-cd)-pyren', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00333', '', 'IR-aktive Kohlenwasserstoffe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00336', '', 'Isotoluron', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00405', '', 'Kationenequivalente, Summe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00377', '', 'Kohlendioxid, gelöst (CO2)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00346', '', 'Kohlensäure, zugehörige', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L23500', '', 'Kohlenwasserstoffe,polycyclische aromat.', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L20210', '', 'LEER (ehem. Tetra)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L20200', '', 'LEER (ehem. Tri)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00398', '', 'Linuron', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00387', '', 'Metamitron', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00391', '', 'Metribuzin', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00310', '', 'Mineralöl, Summe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00393', '', 'Monolinuron', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00401', '', 'nicht-ionische Tenside', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, 1567);
INSERT INTO parameter VALUES ('B00403', '', 'kationische Tenside', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, 1564);
INSERT INTO parameter VALUES ('B00315', '', 'Tenside', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, 1608);
INSERT INTO parameter VALUES ('L20060', '', 'Dichlorbrommethan (HCBrCl2)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, 2006);
INSERT INTO parameter VALUES ('B00351', '', 'organische Stoffe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00608', '', 'Diethylether', 42, NULL, NULL, NULL, NULL, 53.359999999999999, true, NULL, NULL, 3, NULL, true, false, NULL);
INSERT INTO parameter VALUES ('B00378', '', 'PBSM (Summenparameter)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L21400', '', 'Pentachlorphenol', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00308', '', 'Pentadecan (H3C-(CH2)13-CH3)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00367', '', 'Pentan (C5H12)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00402', '', 'Pestizide, Summe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00348', '', 'Polytest', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00396', '', 'Propazin', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00394', '', 'Propham', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00373', '', 'Pyren', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L14720', '', 'Säurekapazität (m-Wert)', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00313', '', 'sulfitreduz. sporenb. Anaerobier', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00385', '', 'Summe der Ca- und Mg-Ionen', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00397', '', 'Terbutylazin', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00307', '', 'Tetradecan (H3C-(CH2)12-CH3)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00406', '', 'Titan (Ti)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00316', '', 'TOX (Summe toxisch wirkender Gase)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00366', '', 'Waschaktive Substanzen', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L11400', NULL, 'Gold', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, NULL);
INSERT INTO parameter VALUES ('B00421', '', 'Chlopyralid', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00426', NULL, 'Alachlor', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00429', NULL, 'Aldicarb', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('BK0031', '', 'Alter, bezogen auf Pflanzjahr', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('G00011', '', 'Altöl', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('G00002', '', 'Anzahl der Arbeitsschichten', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('G00003', '', 'Anzahl der Beschäftigten', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('G00004', '', 'Arbeitsbeginn Montag bis Freitag', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('G00006', '', 'Arbeitsbeginn Samstag', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('G00008', '', 'Arbeitsbeginn Sonntag', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('G00005', '', 'Arbeitsende Montag bis Freitag', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('G00007', '', 'Arbeitsende Samstag', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('G00009', '', 'Arbeitsende Sonntag', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('BK0025', '', 'Belaubung', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00420', '', 'Bentazon', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L12110', '', 'Bor (B)', 42, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00427', NULL, 'Crimidin', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00428', NULL, 'Desethylterbutylazin', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('BK0021', '', 'Erscheinungsbild', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00247', '', 'Ethyltoluole, gesamt', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('G00010', '', 'Frischöl', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('G00001', '', 'Gewerbegruppenschlüssel', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00424', '', 'Glyphosat', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00412', '', 'Hemellitol', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('BK0033', '', 'Höhe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('G10004', '', 'Konsistenz', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('BK0024', '', 'Krone', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('BK0034', '', 'Kronenbreite', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('G00018', '', 'Kühlwasser', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('G00012', '', 'Lacke', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('G00015', '', 'Laugen', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('G00014', '', 'Lösungsmittel', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00422', '', 'MCPA', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00423', '', 'Mecoprop', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00006', '', 'Ammonium (NH4-N) DIN 38604', 63, 0.5, NULL, NULL, 0, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L12640', '', 'ortho-Phosphat-Phosphor (o-PO4-P)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00430', NULL, 'PAK-Summe nach TVO', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00415', '', 'Pestizide bei pH 7', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00417', '', 'Phenmedipham', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('G00020', '', 'produktionsbelastetes Abwasser', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00418', '', 'Prometryn', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('G00017', '', 'sanitäres Abwasser', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('G00013', '', 'Säuren', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00414', '', 'Sebutylazin', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L12120', '', 'Silicium (Si)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L12130', '', 'Silikat (SiO2)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('G00019', '', 'Spülwasser', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('BK0023', '', 'Stamm', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00419', '', 'Terbutryn', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00413', '', 'Tetralin', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('BK0014', '', 'Überdeckung: Baumscheibenplatten o.ä.', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('BK0012', '', 'Überdeckung: Mulchfläche', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('BK0013', '', 'Überdeckung: offener Boden', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('BK0016', '', 'Überdeckung: überdeckt, befestigt', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('BK0015', '', 'Überdeckung: wassergebundene Decke', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('BK0032', '', 'Umfang in 1m Höhe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L10280', '', 'UV-Extinktion (SAK 254nm)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('BK0026', '', 'Vitalität', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00176', '', 'BTX-Summe (Benzol,Toluol,Xylol)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, 2950);
INSERT INTO parameter VALUES ('G00016', '', 'sonstige wassergefährdende Stoffe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('G00021', '', 'sonstiges Abwasser', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L13131', NULL, 'Sulfat (SO4) bei Probenahme', 42, 600, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 5, true, true, 1313);
INSERT INTO parameter VALUES ('B00135', '', 'Isopropylbenzol (C6H5-CH-(CH3)2)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 82, true, true, NULL);
INSERT INTO parameter VALUES ('P00005', NULL, 'Salmonellen', 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, true, false, NULL);
INSERT INTO parameter VALUES ('P00001', NULL, 'Gesamtgehalt NH4-N (CaCI-löslich)', 63, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('P00006', NULL, 'Salmonella senftenberg W 775', 0, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, true, false, NULL);
INSERT INTO parameter VALUES ('B00203', '', 'Sauerstoff, gelöst (O2)', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 61, true, true, NULL);
INSERT INTO parameter VALUES ('B30002', NULL, 'Bauschutt', 0, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00425', '', 'Schöpfzeitraum', 0, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00368', '', 'Naphthalin', 42, NULL, NULL, NULL, NULL, 24.989999999999998, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('Z00003', NULL, 'PFOA', 44, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 89, true, false, NULL);
INSERT INTO parameter VALUES ('Z00004', NULL, 'PFOS', 44, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 90, true, false, NULL);
INSERT INTO parameter VALUES ('B00182', '', 'Chlor, gesamt (Cl)', 42, NULL, NULL, NULL, 10000, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00137', '', '2,4,4`-Trichlorobiphenyl', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 84, true, true, NULL);
INSERT INTO parameter VALUES ('L14520', '', 'Absetzbare Stoffe ml/l', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 59, true, true, NULL);
INSERT INTO parameter VALUES ('B00001', '', 'Bodensatz', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 85, true, true, NULL);
INSERT INTO parameter VALUES ('B00134', '', 'Dicyclopentadien (C10H12)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 81, true, true, NULL);
INSERT INTO parameter VALUES ('P00003', NULL, 'Fluor', 43, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00173', '', 'Keimzahl in Agar', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 58, true, true, NULL);
INSERT INTO parameter VALUES ('B00119', '', 'meta-Xylol (C6H4-(CH3)2)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 67, true, true, NULL);
INSERT INTO parameter VALUES ('B00115', '', 'Methan (CH4)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 66, true, true, NULL);
INSERT INTO parameter VALUES ('B00038', '', 'Mineralöl- Kohlenwasserstoffe', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 65, true, true, NULL);
INSERT INTO parameter VALUES ('B00120', '', 'para-Xylol (C6H4-(CH3)2)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 72, true, true, NULL);
INSERT INTO parameter VALUES ('P00004', NULL, 'PCP', 43, NULL, NULL, NULL, 5, NULL, NULL, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00133', '', 'tert.-Butyl-Benzol (C6H5-C-(CH3)3)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 80, true, true, NULL);
INSERT INTO parameter VALUES ('B00005', '', 'Basenkapazität (p-Wert)', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00015', NULL, 'Chlor, gebundenes (Cl)', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00012', '', 'Coliforme Bakt.', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00002', '', 'Eisen nach Belüftung (Fe)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00011', '', 'Escherichia coli', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00246', '', 'flüchtige Halogenkohlenwasserstoffe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00010', '', 'Karbonat-Härte', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00004', '', 'Kohlensäure (frei)', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00003', '', 'Kohlensäure (gesamt)', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00122', '', 'Kohlensäure, gebunden', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 75, true, true, NULL);
INSERT INTO parameter VALUES ('B00014', '', 'Koloniezahl in Gelatine 20 Cel', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00132', '', 'Mesitylen (C6H3-(CH3)3)', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00253', '', 'PCB-Isomere, Summe', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00084', '', 'Sauerstoffsättigung (% O2)', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00016', '', 'Wasserspiegel', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00017', '', 'Wassertemperatur', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 60, true, true, NULL);
INSERT INTO parameter VALUES ('B00013', '', 'Zahl E. coli und Coliforme', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('P00007', NULL, 'Kjeldahl-Stickstoff', 42, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, true, false, NULL);
INSERT INTO parameter VALUES ('P00014', NULL, 'TOC-Tagesfracht', 22, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, true, false, NULL);
INSERT INTO parameter VALUES ('L10821', NULL, 'Leitfähigkeit bei Probenahme', 73, NULL, NULL, NULL, 0, 0, true, NULL, NULL, 3, 3, true, false, 1082);
INSERT INTO parameter VALUES ('P00012', NULL, 'Phosphor (P) ges.', 63, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, NULL, true, false, NULL);
INSERT INTO parameter VALUES ('B00606', '', 'Absetzbare Stoffe bei Probenahme', 46, 10, NULL, NULL, NULL, 0, true, NULL, NULL, 3, 4, true, false, 1452);
INSERT INTO parameter VALUES ('L10111', NULL, 'Temperatur bei Probenahme', 61, 35, NULL, NULL, NULL, 0, true, NULL, NULL, 3, 1, true, false, 1011);
INSERT INTO parameter VALUES ('B00150', '', 'Glühverlust', 63, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, NULL, true, false, 1458);
INSERT INTO parameter VALUES ('B00601', '', 'Chromat (CrVI) bei Probenahme', 42, 0.20000000000000001, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 7, true, false, NULL);
INSERT INTO parameter VALUES ('N00002', NULL, 'Basisch wirksame Substanz als CaO', 63, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, NULL, true, false, 1228);
INSERT INTO parameter VALUES ('P00008', NULL, 'AOX-SPE', 42, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, true, false, 1343);
INSERT INTO parameter VALUES ('P00016', NULL, 'TOC-Tagesfracht (2 Tanks)', 22, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L13381', NULL, 'Chlor, freies (Cl2) bei Probenahme', 42, 0.5, NULL, NULL, NULL, 0, true, NULL, NULL, 3, 8, true, false, 1338);
INSERT INTO parameter VALUES ('P00015', NULL, 'DOC-Tagesfracht', 22, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, true, false, NULL);
INSERT INTO parameter VALUES ('B00611', NULL, 'Mercaptanschwefel', 42, 2, NULL, NULL, NULL, 26.18, true, NULL, NULL, NULL, NULL, true, false, 1317);
INSERT INTO parameter VALUES ('L11820', '', 'Eisen (Fe) ges.', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, 1182);
INSERT INTO parameter VALUES ('P00011', NULL, 'BSB5/CSB-Verhältnis', 0, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, true, false, NULL);
INSERT INTO parameter VALUES ('B00033', '', 'Stickstoff (N) ges.', 63, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00600', '', 'pH-Wert bei Probenahme', 0, 10, NULL, NULL, 0, 0, true, NULL, NULL, 3, 2, true, false, 1061);
INSERT INTO parameter VALUES ('L12310', '', 'Cyanide (CN)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, 1231);
INSERT INTO parameter VALUES ('B00125', '', 'Stickstoff (N2) ges.', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, NULL);
INSERT INTO parameter VALUES ('B00162', '', 'cis-1,2-Dichlorethen (HCCl=CClH)', 44, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, 2028);
INSERT INTO parameter VALUES ('B00614', NULL, 'Chlorethen', 44, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, 3, NULL, true, false, NULL);
INSERT INTO parameter VALUES ('Z00002', NULL, 'Tellur', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, NULL);
INSERT INTO parameter VALUES ('L14550', '', 'Absetzbare Stoffe mg/l', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00604', NULL, 'Nitrit (NO2) bei Probenahme', 42, 10, NULL, NULL, NULL, 0, true, NULL, NULL, 3, 6, true, true, 1246);
INSERT INTO parameter VALUES ('L20100', '', '1,1,1-Trichlorethan (CCl3-CH3)', 44, 500, NULL, NULL, NULL, 11.9, true, NULL, NULL, 1, NULL, true, false, 2010);
INSERT INTO parameter VALUES ('P00013', NULL, 'Abwassermenge', 14, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, 901);
INSERT INTO parameter VALUES ('L13430', '', 'AOX', 42, 1, NULL, 0.5, 400, 23.800000000000001, true, NULL, NULL, 3, 38, true, false, 1343);
INSERT INTO parameter VALUES ('B00612', NULL, 'Benzol und Derivate', 44, 1000, NULL, NULL, NULL, 11.9, true, NULL, NULL, 3, 45, true, false, NULL);
INSERT INTO parameter VALUES ('B00219', '', '3,4-Benzpyren', 42, NULL, NULL, NULL, NULL, 24.989999999999998, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00008', '', 'Nitrit (NO2)', 42, 10, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, 1246);
INSERT INTO parameter VALUES ('L15230', '', 'TOC (Organischer Kohlenstoff, ges.)', 42, NULL, NULL, NULL, NULL, 6.5499999999999998, true, NULL, NULL, 3, 86, true, false, 1523);
INSERT INTO parameter VALUES ('B00613', NULL, 'PCDD, PCDF', 131, 2, NULL, NULL, 0, 297.5, true, NULL, NULL, 3, 53, true, false, 2490);
INSERT INTO parameter VALUES ('B00106', '', 'Benzo-(a)-pyren', 42, NULL, NULL, NULL, 0, 17.850000000000001, true, NULL, NULL, NULL, NULL, true, false, 2320);
INSERT INTO parameter VALUES ('B00136', '', '1.3.5-Trimethylbenzol', 44, 500, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 93, true, false, NULL);
INSERT INTO parameter VALUES ('L16250', 'nur wenn CSB > 300 mg/l', 'BSB 5', 42, NULL, NULL, NULL, NULL, 14.279999999999999, true, NULL, NULL, 3, 10, true, false, 1635);
INSERT INTO parameter VALUES ('B00186', '', 'Organische Lösungsmittel', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00411', '', 'LHKW (leichtfl.halogen.KW)', 44, 1000, NULL, NULL, NULL, 23.800000000000001, true, NULL, NULL, 3, 39, true, false, 2045);
INSERT INTO parameter VALUES ('B00044', '', 'Trichlorethen (HCCl=CCl2)', 44, 500, NULL, NULL, NULL, 11.9, true, NULL, NULL, 1, NULL, true, false, 2020);
INSERT INTO parameter VALUES ('B00045', '', 'Tetrachlorethen (CCl2=CCl2)', 44, 500, NULL, NULL, NULL, 11.9, true, NULL, NULL, 1, NULL, true, false, 2021);
INSERT INTO parameter VALUES ('B00105', '', 'Trichlormethan (HCCl3)', 44, 500, NULL, NULL, NULL, 11.9, true, NULL, NULL, 1, NULL, true, false, 2001);
INSERT INTO parameter VALUES ('L20000', '', 'Dichlormethan (H2CCl2)', 44, 500, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, 2000);
INSERT INTO parameter VALUES ('B00117', '', 'Toluol (C6H5-CH3)', 44, 500, NULL, NULL, NULL, 0, true, NULL, NULL, 2, NULL, true, false, 2400);
INSERT INTO parameter VALUES ('B00296', '', '1,2-Dichlorbenzol', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00103', '', '1,2-Dichlorethan (H2CCl-CClH2)', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 71, true, true, NULL);
INSERT INTO parameter VALUES ('B00156', '', '1,2-Dichlorethen (HCCl=CClH)', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 68, true, true, NULL);
INSERT INTO parameter VALUES ('L20250', '', '1,2-Dichlorpropan (H2CCl-HCCl-CH3)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00297', '', '1,2,4-Trichlorbenzol', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00298', '', '1,2,4,5-Tetrachlorbenzol', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00294', '', '1,3-Dichlorbenzol', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00291', '', '1,3-Dichlorpropan (CH2-(CClH2)2)', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00292', '', '1,3-Dichlorpropen, Summe cis- und trans-', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00295', '', '1,4-Dichlorbenzol', 42, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('B00195', '', '1.1-Dichlorethen (Cl2C=CH2)', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 69, true, true, NULL);
INSERT INTO parameter VALUES ('B00233', '', '1.1.2-Trifluor-1.2.2-Trichlorethan', 42, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 70, true, true, NULL);
INSERT INTO parameter VALUES ('B00364', NULL, '1.2.3-Trimethylbenzol', 44, 500, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 91, true, false, NULL);
INSERT INTO parameter VALUES ('B00188', '', 'Chromat (CrVI)', 42, 0.20000000000000001, NULL, NULL, 0, 4.1699999999999999, true, NULL, NULL, 3, 27, true, false, NULL);
INSERT INTO parameter VALUES ('L13210', '', 'Fluorid (F)', 42, 50, NULL, NULL, NULL, 6.3099999999999996, true, NULL, NULL, 3, 20, true, false, 1321);
INSERT INTO parameter VALUES ('B00180', '', 'meta/para-Xylol (Summe)', 44, 500, NULL, NULL, NULL, 0, true, NULL, NULL, 2, NULL, true, false, 2896);
INSERT INTO parameter VALUES ('B00118', '', 'ortho-Xylol (C6H4-(CH3)2)', 44, 500, NULL, NULL, NULL, 0, true, NULL, NULL, 2, NULL, true, false, 2410);
INSERT INTO parameter VALUES ('B00609', '', 'Bakterienleuchthemmung', 130, 4, NULL, NULL, NULL, 29.75, true, NULL, NULL, 3, 52, true, false, 1674);
INSERT INTO parameter VALUES ('L12470', '', 'Nitrit-Stickstoff (N) NO2-N', 42, 10, NULL, NULL, NULL, 4.1699999999999999, true, NULL, NULL, 3, 18, true, false, 1247);
INSERT INTO parameter VALUES ('L11660', '', 'Quecksilber (Hg)', 42, 0.050000000000000003, NULL, 0.69999999999999996, 2, 6.5499999999999998, true, NULL, NULL, 3, 31, true, false, 1166);
INSERT INTO parameter VALUES ('L15470', '', 'Phenolindex', 42, 100, NULL, NULL, NULL, 7.7400000000000002, true, NULL, NULL, 3, 14, true, false, 1547);
INSERT INTO parameter VALUES ('B00145', '', 'PCB, Summe', 42, NULL, NULL, NULL, 0, 17.850000000000001, true, NULL, NULL, NULL, NULL, true, false, 2075);
INSERT INTO parameter VALUES ('B00128', '', 'Sulfid (S2-), leicht freisetzbar', 42, NULL, NULL, NULL, NULL, 11.9, true, NULL, NULL, 3, 79, true, false, 1309);
INSERT INTO parameter VALUES ('P00009', '', 'Sulfid (S2-), ges.', 42, 2, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, 1311);
INSERT INTO parameter VALUES ('B00205', '', 'Antimon (Sb)', 42, 0.5, NULL, NULL, 55, 4.7599999999999998, true, NULL, NULL, 3, 22, true, false, 1145);
INSERT INTO parameter VALUES ('B00408', '', 'Zirkonium (Zr)', 42, 1, NULL, NULL, NULL, 35.700000000000003, true, NULL, NULL, 3, 37, true, false, 1130);
INSERT INTO parameter VALUES ('B00213', '', 'Zinn (Sn)', 42, 1, NULL, NULL, 100, 17.850000000000001, true, NULL, NULL, 3, 36, true, false, 1137);
INSERT INTO parameter VALUES ('L11640', '', 'Zink (Zn)', 42, 2, NULL, 800, 1400, 5.9500000000000002, true, NULL, NULL, 3, 35, true, false, 1164);
INSERT INTO parameter VALUES ('B00384', '', 'Thallium (Tl)', 42, 0.20000000000000001, NULL, NULL, 1, 17.850000000000001, true, NULL, NULL, 3, 34, true, false, 1132);
INSERT INTO parameter VALUES ('P00002', NULL, 'Summe 2 PFT', 42, NULL, NULL, NULL, 100, 119, true, NULL, NULL, 3, 87, true, false, NULL);
INSERT INTO parameter VALUES ('P00017', NULL, 'Summe 10 PFT', 42, NULL, NULL, NULL, 100, 142.80000000000001, true, NULL, NULL, 3, 88, true, false, NULL);
INSERT INTO parameter VALUES ('B00210', '', 'Silber (Ag)', 42, 1, NULL, 5.2999999999999998, NULL, 17.850000000000001, true, NULL, NULL, 3, 33, true, false, 1162);
INSERT INTO parameter VALUES ('L12180', '', 'Selen (Se)', 42, 1, NULL, NULL, 0, 4.7599999999999998, true, NULL, NULL, 3, 32, true, false, 1218);
INSERT INTO parameter VALUES ('B00339', '', 'schwerflüchtige lipophile Stoffe', 42, 300, NULL, NULL, NULL, 11.9, true, NULL, NULL, 3, 13, true, false, 1570);
INSERT INTO parameter VALUES ('B00610', '', 'Palladium (Pd)', 42, 1, NULL, NULL, NULL, 41.600000000000001, true, NULL, NULL, 3, NULL, true, false, 1187);
INSERT INTO parameter VALUES ('B00043', '', 'PAK (Polycykl. Aromat. KW)', 42, NULL, NULL, NULL, 0, 17.850000000000001, true, NULL, NULL, NULL, NULL, true, false, 2350);
INSERT INTO parameter VALUES ('B00379', '', 'PAK-Summe nach EPA', 42, NULL, NULL, NULL, NULL, 17.850000000000001, true, NULL, NULL, NULL, NULL, true, true, NULL);
INSERT INTO parameter VALUES ('L11880', '', 'Nickel (Ni)', 42, 1, NULL, 17, 80, 5.9500000000000002, true, NULL, NULL, 3, 30, true, false, 1188);
INSERT INTO parameter VALUES ('L11610', '', 'Kupfer (Cu)', 42, 1, NULL, 250, 550, 5.9500000000000002, true, NULL, NULL, 3, 29, true, false, 1161);
INSERT INTO parameter VALUES ('L15500', '', 'Kohlenwasserstoff-Index', 42, 20, NULL, NULL, NULL, 12.5, true, NULL, NULL, 3, 12, true, false, 1552);
INSERT INTO parameter VALUES ('L12340', '', 'Cyanid, leicht freisetzbar', 42, 1, NULL, NULL, NULL, 9.1600000000000001, true, NULL, NULL, 3, 16, true, false, 1234);
INSERT INTO parameter VALUES ('B00021', '', 'Cyanid (CN)', 42, 2, NULL, NULL, NULL, 9.1600000000000001, true, NULL, NULL, 3, 17, true, false, 1231);
INSERT INTO parameter VALUES ('L15330', '', 'CSB', 42, NULL, NULL, NULL, NULL, 34.509999999999998, true, NULL, NULL, 3, 9, true, false, 1533);
INSERT INTO parameter VALUES ('B00108', '', 'Cobalt (Co)', 42, 2, NULL, 2.7999999999999998, 20, 4.7599999999999998, true, NULL, NULL, 3, 28, true, false, 1186);
INSERT INTO parameter VALUES ('B00040', '', 'Chrom (Cr) ges.', 42, 1, NULL, 24, NULL, 2.7400000000000002, true, NULL, NULL, NULL, NULL, true, false, 1151);
INSERT INTO parameter VALUES ('L11650', '', 'Cadmium (Cd)', 42, 0.10000000000000001, NULL, 0.59999999999999998, 2.5, 4.7599999999999998, true, NULL, NULL, 3, 25, true, false, 1165);
INSERT INTO parameter VALUES ('L11380', '', 'Blei (Pb)', 42, 1, NULL, 34, 200, 4.7599999999999998, true, NULL, NULL, 3, 24, true, false, 1138);
INSERT INTO parameter VALUES ('L11420', '', 'Arsen (As)', 42, 0.5, NULL, NULL, 10, 4.7599999999999998, true, NULL, NULL, 3, 23, true, false, 1142);
INSERT INTO parameter VALUES ('L11510', '', 'Chrom (Cr)', 42, 1, NULL, 24, 200, 5.9500000000000002, true, NULL, NULL, 3, 26, true, false, 1151);


SET search_path = basis, pg_catalog;

--
-- TOC entry 4453 (class 0 OID 5245282)
-- Dependencies: 239
-- Data for Name: objektarten; Type: TABLE DATA; Schema: basis; Owner: auikadmin
--

INSERT INTO objektarten VALUES (0, 'k. A.', '360.34', NULL, true, false);
INSERT INTO objektarten VALUES (1, 'Tankstelle', '360.34', NULL, true, false);
INSERT INTO objektarten VALUES (2, 'Betriebstankstelle', '360.34', NULL, true, false);
INSERT INTO objektarten VALUES (3, 'Gefahrstofflager', '360.34', NULL, true, false);
INSERT INTO objektarten VALUES (4, 'Altöllager', '360.34', NULL, true, false);
INSERT INTO objektarten VALUES (5, 'Chemische Reinigung', '360.34', NULL, true, false);
INSERT INTO objektarten VALUES (6, 'Schrottplatz (o. Entfallst.)', '360.34', NULL, true, false);
INSERT INTO objektarten VALUES (7, 'Galvanik', '360.34', NULL, true, false);
INSERT INTO objektarten VALUES (8, 'Kfz-Werkstatt', '360.34', NULL, true, false);
INSERT INTO objektarten VALUES (58, 'Abscheider', '360.34', NULL, true, false);
INSERT INTO objektarten VALUES (64, 'BImSch-Kommunal', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (67, 'Schrottplatz', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (9, 'Metallbe- und Verarbeitung', '360.34', NULL, true, false);
INSERT INTO objektarten VALUES (10, 'Energie-u.Hydr.aggregate', '360.34', NULL, true, false);
INSERT INTO objektarten VALUES (11, 'Heizöllager', '360.34', NULL, true, false);
INSERT INTO objektarten VALUES (12, 'BImSchG-Land', '360.34', NULL, true, false);
INSERT INTO objektarten VALUES (21, 'Lackieranlage', '360.34', NULL, true, false);
INSERT INTO objektarten VALUES (22, 'Gewerbeobjekt', '360.34', NULL, true, false);
INSERT INTO objektarten VALUES (23, 'Chemische Industrie', '360.34', NULL, true, false);
INSERT INTO objektarten VALUES (24, 'Altautoabstellflächen', '360.34', NULL, true, false);
INSERT INTO objektarten VALUES (47, 'Biogasanlage', '360.34', NULL, true, false);
INSERT INTO objektarten VALUES (49, 'BImSchG-kommunal', '360.34', NULL, true, false);
INSERT INTO objektarten VALUES (55, 'Kälteanlagen', '360.34', NULL, true, false);
INSERT INTO objektarten VALUES (56, 'Erdwärmeanlagen', '360.34', NULL, true, false);
INSERT INTO objektarten VALUES (57, 'JGS-Anlagen', '360.34', NULL, true, false);
INSERT INTO objektarten VALUES (59, 'BHKW', '360.34', NULL, true, false);
INSERT INTO objektarten VALUES (61, 'BImSchG-BR', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (16, 'Brennwertkessel', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (60, 'Abscheider nach Entwässerungssatzung', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (41, 'Übergabestelle', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (19, 'Abscheider ohne Nutzung', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (36, 'BHKW', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (54, 'Anhang 54', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (53, 'Aufbereitung Medizinprodukte', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (44, 'Stellungnahme zu BA', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (33, 'Gewerbeobjekt', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (52, 'Wärmetauscher', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (51, 'KWK Anlage', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (50, 'Gewerbepark', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (48, 'Betriebserfassung', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (46, 'Anhang 26', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (45, 'RLT Anlagen', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (43, 'Kompressorenanlage', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (42, 'Genehmigung', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (40, 'Sielhautmessstelle', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (39, 'Anhang 41', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (38, 'Anhang 27', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (35, 'Anhang 52', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (32, 'Probenahmepunkt', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (31, 'Anhang 38', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (30, 'Anhang 56', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (29, 'Anhang 55', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (28, 'Anhang 31', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (27, 'Anhang 25', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (26, 'Anhang 22', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (25, 'Anhang 40', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (14, 'Anhang 49', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (15, 'Fettabscheider', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (17, 'Anhang 53 (<3000)', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (18, 'Anhang 53 (>3000)', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (20, 'Anhang 50', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (13, 'SüwVO-Abw-Teil1', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (34, 'Abwasserbehandlungsanlage', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (66, 'Einleitungsstelle', '360.33', NULL, false, false);
INSERT INTO objektarten VALUES (62, 'Labor', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (63, 'Gentechnikanlage', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (65, 'Anfallstelle', '360.33', NULL, true, false);
INSERT INTO objektarten VALUES (68, 'Sonderbauwerk', '360.33 + 360.41', NULL, true, false);
INSERT INTO objektarten VALUES (69, 'Entwässerungsgrundstück', '360.33 + 360.41', NULL, true, false);



SET search_path = labor, pg_catalog;


--
-- TOC entry 4453 (class 0 OID 5245997)
-- Dependencies: 376
-- Data for Name: probeart; Type: TABLE DATA; Schema: labor; Owner: auikadmin
--

INSERT INTO probeart VALUES (4, 'Anlieferung', true, false);
INSERT INTO probeart VALUES (6, 'Rohschlamm', true, false);
INSERT INTO probeart VALUES (7, 'Sielhaut', true, false);
INSERT INTO probeart VALUES (8, 'sonstiges', true, false);
INSERT INTO probeart VALUES (9, 'Zulauf', true, false);
INSERT INTO probeart VALUES (2, 'Abwasser', true, false);
INSERT INTO probeart VALUES (5, 'Faulchlamm', true, false);


--
-- TOC entry 4453 (class 0 OID 5245938)
-- Dependencies: 364
-- Data for Name: einheiten; Type: TABLE DATA; Schema: labor; Owner: auikadmin
--

INSERT INTO einheiten VALUES (0, 'ohne', true, false, 23);
INSERT INTO einheiten VALUES (41, 'g/l', true, false, 27);
INSERT INTO einheiten VALUES (42, 'mg/l', true, false, 7);
INSERT INTO einheiten VALUES (43, 'mg/kg', true, false, 50);
INSERT INTO einheiten VALUES (44, 'µg/l', true, false, 10);
INSERT INTO einheiten VALUES (46, 'ml/l', true, false, 8);
INSERT INTO einheiten VALUES (92, 'ppm', true, true, NULL);
INSERT INTO einheiten VALUES (61, '°C', true, false, 4);
INSERT INTO einheiten VALUES (73, 'µS/cm', true, false, 28);
INSERT INTO einheiten VALUES (131, 'ng/kg TS', true, false, 54);
INSERT INTO einheiten VALUES (47, 'pg/l', true, true, 58);
INSERT INTO einheiten VALUES (93, 'vpm', true, true, NULL);
INSERT INTO einheiten VALUES (63, 'Ma.-%', true, false, 5);
INSERT INTO einheiten VALUES (1, 'km', true, true, NULL);
INSERT INTO einheiten VALUES (99, 'Stück', true, true, NULL);
INSERT INTO einheiten VALUES (2, 'm', true, true, NULL);
INSERT INTO einheiten VALUES (3, 'cm', true, true, NULL);
INSERT INTO einheiten VALUES (4, 'mm', true, true, NULL);
INSERT INTO einheiten VALUES (5, 'm über NN', true, true, NULL);
INSERT INTO einheiten VALUES (6, '1/m', true, true, NULL);
INSERT INTO einheiten VALUES (7, 'm u.Gel.', true, true, NULL);
INSERT INTO einheiten VALUES (8, 'm unter NN', true, true, NULL);
INSERT INTO einheiten VALUES (9, 'Code', true, true, NULL);
INSERT INTO einheiten VALUES (11, 'km2', true, true, NULL);
INSERT INTO einheiten VALUES (12, 'ha', true, true, NULL);
INSERT INTO einheiten VALUES (13, 'm2', true, true, NULL);
INSERT INTO einheiten VALUES (15, 'l', true, true, NULL);
INSERT INTO einheiten VALUES (100, 'µg/m3', true, true, NULL);
INSERT INTO einheiten VALUES (21, 't', true, true, NULL);
INSERT INTO einheiten VALUES (16, 'ml', true, true, NULL);
INSERT INTO einheiten VALUES (23, 'g', true, true, NULL);
INSERT INTO einheiten VALUES (24, 'mg', true, true, NULL);
INSERT INTO einheiten VALUES (28, 'm3/Monat', true, true, NULL);
INSERT INTO einheiten VALUES (29, 'm3/Std.', true, true, NULL);
INSERT INTO einheiten VALUES (30, 'm3/Tag', true, true, NULL);
INSERT INTO einheiten VALUES (31, 'm/s', true, true, NULL);
INSERT INTO einheiten VALUES (32, 'm3/s', true, true, NULL);
INSERT INTO einheiten VALUES (33, 'm3/2h', true, true, NULL);
INSERT INTO einheiten VALUES (34, 'm3/24h', true, true, NULL);
INSERT INTO einheiten VALUES (35, 'm3/Jahr', true, true, NULL);
INSERT INTO einheiten VALUES (36, 'l/min', true, true, NULL);
INSERT INTO einheiten VALUES (37, 'l/s', true, true, NULL);
INSERT INTO einheiten VALUES (38, 'l/s*k', true, true, NULL);
INSERT INTO einheiten VALUES (39, 'l/s*h', true, true, NULL);
INSERT INTO einheiten VALUES (45, 'mval/l', true, true, NULL);
INSERT INTO einheiten VALUES (51, 'g/m3', true, true, NULL);
INSERT INTO einheiten VALUES (52, 'mg/m3', true, true, NULL);
INSERT INTO einheiten VALUES (53, 'mmol/l', true, true, NULL);
INSERT INTO einheiten VALUES (54, 'mmol/m3', true, true, NULL);
INSERT INTO einheiten VALUES (55, 't/Tag', true, true, NULL);
INSERT INTO einheiten VALUES (56, 'kg/Tag', true, true, NULL);
INSERT INTO einheiten VALUES (57, 'kg/h', true, true, NULL);
INSERT INTO einheiten VALUES (58, 't/Jahr', true, true, NULL);
INSERT INTO einheiten VALUES (62, 'Grad dH', true, true, NULL);
INSERT INTO einheiten VALUES (64, 'in h', true, true, NULL);
INSERT INTO einheiten VALUES (65, 'mbar', true, true, NULL);
INSERT INTO einheiten VALUES (66, 'in 1 ml', true, true, NULL);
INSERT INTO einheiten VALUES (67, 'in 100 ml', true, true, NULL);
INSERT INTO einheiten VALUES (68, 'in 1/10 ml', true, true, NULL);
INSERT INTO einheiten VALUES (69, 'in 1/100 ml', true, true, NULL);
INSERT INTO einheiten VALUES (71, 'pS', true, true, NULL);
INSERT INTO einheiten VALUES (72, 'mS/m', true, true, NULL);
INSERT INTO einheiten VALUES (74, 'mV', true, true, NULL);
INSERT INTO einheiten VALUES (90, 'm-1', true, true, NULL);
INSERT INTO einheiten VALUES (91, 'Vol.-%', true, true, NULL);
INSERT INTO einheiten VALUES (102, 'ng/l', true, true, NULL);
INSERT INTO einheiten VALUES (103, 'kN/m3', true, true, NULL);
INSERT INTO einheiten VALUES (104, 'µg/kg', true, true, NULL);
INSERT INTO einheiten VALUES (105, 'nm/s', true, true, NULL);
INSERT INTO einheiten VALUES (106, 'l/Std.', true, true, NULL);
INSERT INTO einheiten VALUES (110, 'm3/120 Std.', true, true, NULL);
INSERT INTO einheiten VALUES (111, 'm3/10 Std.', true, true, NULL);
INSERT INTO einheiten VALUES (112, 'pro Jahr', true, true, NULL);
INSERT INTO einheiten VALUES (113, 'm3/5 Jahre', true, true, NULL);
INSERT INTO einheiten VALUES (114, 'cm2/s', true, true, NULL);
INSERT INTO einheiten VALUES (115, 'm2/s', true, true, NULL);
INSERT INTO einheiten VALUES (116, 'hPa', true, true, NULL);
INSERT INTO einheiten VALUES (117, 'Std.', true, true, NULL);
INSERT INTO einheiten VALUES (118, 'Jahre', true, true, NULL);
INSERT INTO einheiten VALUES (119, 'Bewertungsstufe', true, true, NULL);
INSERT INTO einheiten VALUES (120, 'min', true, true, NULL);
INSERT INTO einheiten VALUES (121, 'in 50 ml', true, true, NULL);
INSERT INTO einheiten VALUES (122, 'in 20 ml', true, true, NULL);
INSERT INTO einheiten VALUES (123, 'in 10 ml', true, true, NULL);
INSERT INTO einheiten VALUES (124, 'g/kg', true, true, NULL);
INSERT INTO einheiten VALUES (125, 'in 250 ml', true, true, NULL);
INSERT INTO einheiten VALUES (126, 'Uhr', true, true, NULL);
INSERT INTO einheiten VALUES (127, 'mm/Monat', true, true, NULL);
INSERT INTO einheiten VALUES (128, 'mm/Jahr', true, true, NULL);
INSERT INTO einheiten VALUES (14, 'm³', true, false, 47);
INSERT INTO einheiten VALUES (22, 'kg/d', true, false, 41);
INSERT INTO einheiten VALUES (10, 'mg/kg TS', true, false, 50);
INSERT INTO einheiten VALUES (132, 'µg/kg TS', true, false, NULL);
INSERT INTO einheiten VALUES (133, '% OS', false, false, NULL);
INSERT INTO einheiten VALUES (130, 'GL', true, false, NULL);


SET search_path = public, pg_catalog;

INSERT INTO tab_streets_alkis VALUES ('33602', 'August-Bebel-Straße', '075', 468437.12900000002, 5764021.8590000002, '05711000', '01380', 75, NULL, '0101000020E86400007593188454971C4123DBF976EDFC5541', '01380075');

SET search_path = basis, pg_catalog;

INSERT INTO gemarkung VALUES (1, 'k. A.', true, false);
INSERT INTO gemarkung VALUES (2, 'Bielefeld', true, false);

INSERT INTO adresse VALUES (1, 'August-Bebel-Straße', 75, '-77', 'D', '33602', 'Bielefeld', NULL, NULL, 'GG', true, false, NULL, NULL, NULL, NULL, NULL, NULL, false, '1.01');

INSERT INTO sachbearbeiter VALUES ('BI007', 'James Bond', 'JB', '100', '1234567', 'james.bond@palace.uk', 'mi5', true, false, 1);

SET search_path = awsv, pg_catalog;

INSERT INTO standortgghwsg VALUES (1, 'sonstiges Geb.', true, false);
INSERT INTO standortgghwsg VALUES (2, 'Wasserschutzgebiet Zone I', true, false);

INSERT INTO wassereinzugsgebiet VALUES (1, 'k. A.', true, false);
INSERT INTO wassereinzugsgebiet VALUES (2, 'Bezirk Süd I', true, false);

SET search_path = labor, pg_catalog;

INSERT INTO klaeranlage VALUES (1, 'Heepen', 222165, true, false);

SET search_path = elka, pg_catalog;


INSERT INTO anhang VALUES (1, 16, '27', '2002-08-01', NULL, 'AbwVerO', 'Behandlung von Abfällen durch chemische und physikalische Verfahren (CP-Anlagen) sowie Altölaufbereitung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (2, 34, '6', '1990-01-01', '1998-12-31', 'RahmenAbwV', 'Herstellung von Erfrischungsgetränken und Getränkeabfüllung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (3, 49, '54', '1999-01-01', NULL, 'AbwVerO', 'Herstellung von Halbleiterbauelementen', 'NAD_BR1', '1980-01-01', '2009-11-30', '2009-11-30 08:50:44');
INSERT INTO anhang VALUES (4, 64, '22', '1992-01-01', '1998-12-31', 'RahmenAbwV', 'Mischabwasser', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (5, 27, '57', '1999-01-01', NULL, 'AbwVerO', 'Wollwäschereien', 'NAD_BR1', '1980-01-01', '2009-11-30', '2009-11-30 08:49:35');
INSERT INTO anhang VALUES (6, 21, '30', '1983-10-01', '1989-12-31', 'AbwVerwVor', 'Sodaherstellung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (7, 2, '50', '1990-01-01', '1998-12-31', 'RahmenAbwV', 'Zahnbehandlung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (8, 37, '9', '1999-01-01', NULL, 'AbwVerO', 'Herstellung von Beschichtungsstoffen und Lackharzen', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (9, 2, '50', '1999-01-01', NULL, 'AbwVerO', 'Zahnbehandlung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (10, 44, '36', '1999-01-01', NULL, 'AbwVerO', 'Herstellung von Kohlenwasserstoffen', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (11, 24, '24 Teil B', '1999-01-01', '2001-07-31', 'AbwVerO', 'Eisen-, Stahl- und Tempergießerei', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (12, 52, '41', '1999-01-01', NULL, 'AbwVerO', 'Herstellung und Verarbeitung von Glas und künstlichen Mineralfasern', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (13, 41, '37', '1984-09-26', '1991-12-31', 'AbwVerwVor', 'Herstellung anorganischer Pigmente', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (14, 34, '6', '1999-01-01', NULL, 'AbwVerO', 'Herstellung von Erfrischungsgetränken und Getränkeabfüllung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (15, 45, '7', '1999-01-01', NULL, 'AbwVerO', 'Fischverarbeitung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (16, 57, '43 Teil II', '2001-08-01', '2002-07-31', 'AbwVerO', 'Verarbeitung von Kautschuk und Latizes, Herstellung und Verarbeitung von Gummi', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (17, 48, '28', '1983-10-01', '2000-06-01', 'AbwVerwVor', 'Melasseverarbeitung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (18, 4, '32', '2002-08-01', NULL, 'AbwVerO', 'Verarbeitung von Kautschuk und Latizes, Herstellung und Verarbeitung von Gummi', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (19, 63, '20', '2007-06-01', NULL, 'AbwVerO', 'Verarbeitung tierischer Nebenprodukte', 'NAD_BR1', '2009-11-30', '2009-11-30', '2009-11-30 08:54:14');
INSERT INTO anhang VALUES (20, 19, '39', '1990-01-01', '1998-12-31', 'RahmenAbwV', 'Nichteisenmetallherstellung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (21, 42, '4', '2002-08-01', NULL, 'AbwVerO', 'Ölsaatenaufbereitung, Speisefett- und Speiseölraffination', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (22, 23, '47', '2002-08-01', NULL, 'AbwVerO', 'Wäsche von Rauchgasen aus Feuerungsanlagen', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (23, 25, '34', '1984-09-26', '1997-04-01', 'AbwVerwVor', 'Herstellung von Bariumverbindungen', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (24, 64, '22', '1982-06-12', '1991-12-31', 'AbwVerwVor', 'Mischabwasser', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (25, 12, '26', '1992-01-01', '1998-12-31', 'RahmenAbwV', 'Steine und Erden', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (26, 20, '17', '1982-02-06', '1991-12-31', 'AbwVerwVor', 'Herstellung keramischer Erzeugnisse', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (27, 36, '38', '2000-06-01', NULL, 'AbwVerO', 'Textilherstellung, Textilveredlung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (28, 19, '39', '1999-01-01', NULL, 'AbwVerO', 'Nichteisenmetallherstellung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (29, 29, '44', '1984-09-26', '2002-07-31', 'AbwVerwVor', 'Herstellung von mineralischen Düngemittel außer Kali', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (30, 8, '2', '1999-01-01', NULL, 'AbwVerO', 'Braunkohle-Brikettfabrikation', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (31, 60, '1', '1997-04-01', NULL, 'AbwVerO', 'Häusliches und kommunales Abwasser', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:10');
INSERT INTO anhang VALUES (32, 45, '7', '1992-01-01', '1998-12-31', 'RahmenAbwV', 'Fischverarbeitung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (33, 51, '56', '2000-06-01', NULL, 'AbwVerO', 'Herstellung von Druckformen, Druckerzeugnissen und grafischen Erzeugnissen', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (34, 4, '32', '1984-09-26', '1998-12-31', 'AbwVerwVor', 'Arzneimittel', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (35, 6, '10', '1990-01-01', '1998-12-31', 'RahmenAbwV', 'Fleischwirtschaft', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:10');
INSERT INTO anhang VALUES (36, 15, '43', '1984-09-26', '1998-12-31', 'AbwVerwVor', 'Chemiefasern', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (37, 31, '21', '1982-06-12', '1989-12-31', 'AbwVerwVor', 'Mälzereien', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (38, 26, '25', '1999-01-01', NULL, 'AbwVerO', 'Lederherstellung, Pelzveredlung, Lederfaserstoffherstellung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (39, 61, '23', '1982-06-12', '1998-12-31', 'AbwVerwVor', 'Herstellung von Calciumcarbid', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (40, 22, '40', '1997-04-01', NULL, 'AbwVerO', 'Metallbearbeitung, Metallverarbeitung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (41, 6, '10', '1999-01-01', NULL, 'AbwVerO', 'Fleischwirtschaft', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:10');
INSERT INTO anhang VALUES (42, 10, '16', '1992-01-01', '1998-12-31', 'RahmenAbwV', 'Steinkohleaufbereitung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (43, 5, '14', '1999-01-01', NULL, 'AbwVerO', 'Trocknung pflanzlicher Produkte für die Futtermittelherstellung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:10');
INSERT INTO anhang VALUES (44, 50, '19 Teil 1', '2001-08-01', '2002-07-31', 'AbwVerO', 'Zellstofferzeugung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (45, 39, '5', '1999-01-01', NULL, 'AbwVerO', 'Herstellung von Obst- und Gemüseprodukten', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (46, 17, '3', '2000-06-01', NULL, 'AbwVerO', 'Milchverarbeitung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (47, 5, '14', '1990-01-01', '1998-12-31', 'RahmenAbwV', 'Trocknung pflanzlicher Produkte für die Futtermittelherstellung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:10');
INSERT INTO anhang VALUES (48, 26, '25', '1983-03-22', '1989-12-31', 'AbwVerwVor', 'Lederherstellung, Pelzveredelung, Lederfaserherstellung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (49, 66, '24 Teil A', '1996-01-01', '2002-07-31', 'RahmenAbwV', 'Eisen- und Stahlerzeugung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (50, 5, '14', '1981-03-26', '1989-12-31', 'AbwVerwVor', 'Trocknung pflanzlicher Produkte für die Futtermittelherstellung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:10');
INSERT INTO anhang VALUES (51, 52, '41', '1984-09-26', '1989-12-31', 'AbwVerwVor', 'Glasherstellung und -verarbeitung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (52, 43, '18', '1999-01-01', NULL, 'AbwVerO', 'Zuckerherstellung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (53, 37, '9', '1981-03-26', '1989-12-31', 'AbwVerwVor', 'Herstellung von Anstrichstoffen', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (54, 32, '49', '1990-01-01', '2000-05-31', 'RahmenAbwV', 'Mineralölhaltiges Abwasser', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (55, 10, '16', '1982-02-06', '1991-12-31', 'AbwVerwVor', 'Steinkohleaufbereitung und Steinkohlen-Brikettfabrikation', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (56, 59, '52', '1990-01-01', '1998-12-31', 'RahmenAbwV', 'Chemischreinigung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (57, 28, '19', '1982-02-06', '1989-12-31', 'AbwVerwVor', 'Zellstofferzeugung,Herstellung von Papier und Pappe', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (58, 53, '99', '2009-11-10', NULL, '-', 'Keine eindeutige Zuordnung möglich', 'DEA_IT.NRW', '2009-11-10', '2009-11-10', '2009-11-10 13:57:46');
INSERT INTO anhang VALUES (59, 54, '8', '1999-01-01', NULL, 'AbwVerO', 'Kartoffelverarbeitung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (60, 20, '17', '1992-01-01', '2000-05-31', 'RahmenAbwV', 'Herstellung keramischer Erzeugnisse', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (61, 40, '24 Teil II', '2001-08-01', '2002-07-31', 'AbwVerO', 'Eisen-, Stahl- und Tempergießerei', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (62, 65, '19 Teil A', '1990-01-01', '2001-07-31', 'AbwVerwVor', 'Zellstofferzeugung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (63, 63, '20', '1999-01-01', '2007-05-31', 'AbwVerO', 'Fleischmehlindustrie', 'NAD_BR1', '1980-01-01', '2009-11-30', '2009-11-30 08:51:49');
INSERT INTO anhang VALUES (64, 36, '38', '1984-09-26', '2000-05-31', 'AbwVerwVor', 'Textilherstellung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (65, 39, '5', '1981-03-26', '1989-12-31', 'AbwVerwVor', 'Herstellung von Obst- und Gemüseprodukten', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (66, 44, '36', '1992-01-01', '1998-12-31', 'RahmenAbwV', 'Herstellung von Kohlenwasserstoffen', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (67, 28, '19', '2002-08-01', NULL, 'AbwVerO', 'Zellstofferzeugung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (68, 11, '33', '1984-09-26', '1997-04-01', 'AbwVerwVor', 'Herstellung von Perboraten', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (69, 3, '45', '1999-01-01', NULL, 'AbwVerO', 'Erdölverarbeitung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (70, 64, '22', '1999-01-01', NULL, 'AbwVerO', 'Chemische Industrie', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (71, 20, '17', '2000-06-01', NULL, 'AbwVerO', 'Herstellung keramischer Erzeugnisse', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (72, 28, '19', '1990-01-01', '1991-12-31', 'AbwVerwVor', 'Herstellung von Papier und Pappe', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (73, 56, '15', '1990-01-01', '1998-12-31', 'RahmenAbwV', 'Herstellung von Hautleim, Gelatine und Knochenleim', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (74, 56, '15', '1981-03-26', '1989-12-31', 'AbwVerwVor', 'Herstellung von Hautleim, Gelatine und Knochenleim', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (75, 46, '13', '1993-07-01', '1998-12-31', 'RahmenAbwV', 'Holzfaserplatten', 'NAD_BR1', '1980-01-01', '2009-11-30', '2009-11-30 08:36:02');
INSERT INTO anhang VALUES (76, 12, '26', '1982-03-22', '1991-12-31', 'AbwVerwVor', 'Steine und Erden', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (77, 54, '8', '1990-01-01', '1998-12-31', 'RahmenAbwV', 'Kartoffelverarbeitung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (78, 44, '36', '1984-09-26', '1991-12-31', 'AbwVerwVor', 'Herstellung von Kohlenwasserstoffen', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (79, 7, '11', '1990-01-01', '1998-12-31', 'RahmenAbwV', 'Brauereien', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:10');
INSERT INTO anhang VALUES (80, 42, '4', '1981-03-26', '2002-07-31', 'AbwVerwVor', 'Ã–lsaatenaufbereitung, Speisefett- und Speiseölraffination', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (81, 17, '3', '1990-01-01', '2000-05-31', 'RahmenAbwV', 'Milchverarbeitung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (82, 8, '2', '1990-01-01', '1998-12-31', 'RahmenAbwV', 'Braunkohle-Brikettfabrikation', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (83, 30, '43 Teil I', '1999-01-01', '2002-07-31', 'AbwVerO', 'Herstellung von Chemiefasern, Folien und Schwammtuch nach dem Viskoseverfahren sowie von Celluloseacetatfasern', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (84, 18, '51', '1999-01-01', NULL, 'AbwVerO', 'Oberirdische Ablagerung von Abfällen', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (85, 15, '43', '1996-01-01', '1998-12-31', 'RahmenAbwV', 'Herstellung von Chemiefasern, Folien und Schwammtuch nach dem Viskoseverfahren sowie Celluloseacetatfasern', 'NAD_BR1', '1980-01-01', '2009-11-30', '2009-11-30 08:48:18');
INSERT INTO anhang VALUES (86, 10, '16', '1999-01-01', NULL, 'AbwVerO', 'Steinkohlenaufbereitung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (87, 22, '40', '1984-09-26', '1989-12-31', 'AbwVerwVor', 'Metallbearbeitung, Metallverarbeitung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (88, 13, '12', '1990-01-01', '1998-12-31', 'RahmenAbwV', 'Herstellung von Alkohol und alkoholischen Getränken', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:10');
INSERT INTO anhang VALUES (89, 19, '39', '1984-09-26', '1989-12-31', 'AbwVerwVor', 'Nichteisenmetalle', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (90, 21, '30', '1990-01-01', '2002-07-31', 'RahmenAbwV', 'Sodaherstellung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (91, 60, '1', '1983-01-01', '1989-12-31', 'AbwVerwVor', 'Gemeinden', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:10');
INSERT INTO anhang VALUES (92, 26, '25', '1990-01-01', '1998-12-31', 'RahmenAbwV', 'Lederherstellung, Pelzveredelung, Lederfaserstoffherstellung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (93, 12, '26', '1999-01-01', NULL, 'AbwVerO', 'Steine und Erden', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (94, 22, '40', '1990-01-01', '1997-03-31', 'RahmenAbwV', 'Metallbearbeitung, Metallverarbeitung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (95, 35, '48', '1997-04-01', NULL, 'AbwVerO', 'Verwendung bestimmter gefährlicher Stoffe', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (96, 56, '15', '1999-01-01', NULL, 'AbwVerO', 'Herstellung von Hautleim, Gelatine und Knochenleim', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (97, 46, '13', '1981-03-26', '1993-06-30', 'AbwVerwVor', 'Herstellung von Holzfaserhartplatten', 'NAD_BR1', '1980-01-01', '2009-11-30', '2009-11-30 08:34:09');
INSERT INTO anhang VALUES (98, 7, '11', '1999-01-01', NULL, 'AbwVerO', 'Brauereien', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:10');
INSERT INTO anhang VALUES (99, 52, '41', '1990-01-01', '1998-12-31', 'RahmenAbwV', 'Herstellung und Verarbeitung von Glas und künstlichen Mineralfasern', 'NAD_BR1', '1980-01-01', '2009-11-30', '2009-11-30 08:47:09');
INSERT INTO anhang VALUES (100, 58, '31', '2002-08-01', NULL, 'AbwVerO', 'Wasseraufbereitung, Kühlsysteme, Dampferzeugung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (101, 41, '37', '1992-01-01', '1998-12-31', 'RahmenAbwV', 'Herstellung anorganischer Pigmente', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (102, 16, '27', '1982-03-22', '1998-12-31', 'AbwVerwVor', 'Erzaufbereitung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (103, 58, '31', '1993-07-01', '2002-07-31', 'RahmenAbwV', 'Wasseraufbereitung, Kühlsysteme, Dampferzeugung', 'NAD_BR1', '1980-01-01', '2009-11-30', '2009-11-30 08:45:18');
INSERT INTO anhang VALUES (104, 3, '45', '1992-01-01', '1998-12-31', 'RahmenAbwV', 'Erdölverarbeitung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (105, 13, '12', '1981-03-26', '1989-12-31', 'AbwVerwVor', 'Herstellung von Alkohol und alkoholischen Getränken', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:10');
INSERT INTO anhang VALUES (106, 34, '6', '1981-03-26', '1989-12-31', 'AbwVerwVor', 'Herstellung von Erfrischungsgetränken und Getränkeabfüllung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (107, 43, '18', '1982-02-06', '1991-12-31', 'AbwVerwVor', 'Zuckerherstellung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (108, 47, '29', '2002-08-01', NULL, 'AbwVerO', 'Eisen- und Stahlerzeugung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (109, 39, '5', '1990-01-01', '1998-12-31', 'RahmenAbwV', 'Herstellung von Obst- und Gemüseprodukten', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (110, 37, '9', '1990-01-01', '1998-12-31', 'RahmenAbwV', 'Herstellung von Beschichtungsstoffen und Lackharzen', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (111, 3, '45', '1984-09-26', '1991-12-31', 'AbwVerwVor', 'Erdölverarbeitung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (112, 54, '8', '1981-03-26', '1989-12-31', 'AbwVerwVor', 'Kartoffelverarbeitung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (113, 13, '12', '1999-01-01', NULL, 'AbwVerO', 'Herstellung von Alkohol und alkoholischen Getränken', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:10');
INSERT INTO anhang VALUES (114, 32, '49', '2000-06-01', NULL, 'AbwVerO', 'Mineralölhaltiges Abwasser', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (115, 31, '21', '1999-01-01', NULL, 'AbwVerO', 'Mälzereien', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (116, 38, '46', '1996-01-01', '1998-12-31', 'RahmenAbwV', 'Steinkohleverkokung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (117, 55, '55', '1999-01-01', NULL, 'AbwVerO', 'Wäschereien', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (118, 15, '43', '2002-08-01', NULL, 'AbwVerO', 'Herstellung von Chemiefasern, Folien und Schwammtuch nach dem Viskoseverfahren sowie von Celluloseacetatfasern', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (119, 47, '29', '1983-10-01', '2002-07-31', 'AbwVerwVor', 'Fischintensivhaltung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (120, 41, '37', '1999-01-01', NULL, 'AbwVerO', 'Herstellung anorganischer Pigmente', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (121, 35, '48', '1989-02-01', '1997-03-31', 'AbwVerwVor', 'Verwendung bestimmter gefährlicher Stoffe', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (122, 7, '11', '1981-03-26', '1989-12-31', 'AbwVerwVor', 'Brauereien', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:10');
INSERT INTO anhang VALUES (123, 14, '35', '1984-09-26', '1997-04-01', 'AbwVerwVor', 'Hochdisperse Oxide', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (124, 24, '24 Teil B', '1996-01-01', '1998-12-31', 'RahmenAbwV', 'Eisen-, Stahl- und Tempergießerei', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (125, 59, '52', '1999-01-01', NULL, 'AbwVerO', 'Chemischreinigung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (126, 61, '23', '2001-08-01', NULL, 'AbwVerO', 'Anlagen zur biologischen Behandlung von Abfällen', 'NAD_BR1', '1980-01-01', '2009-11-30', '2009-11-30 08:37:56');
INSERT INTO anhang VALUES (127, 60, '1', '1990-01-01', '1997-03-31', 'RahmenAbwV', 'Gemeinden', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:10');
INSERT INTO anhang VALUES (128, 31, '21', '1990-01-01', '1998-12-31', 'RahmenAbwV', 'Mälzereien', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (129, 18, '51', '1990-01-01', '1998-12-31', 'RahmenAbwV', 'Ablagerung von Siedlungsabfällen', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (130, 43, '18', '1992-01-01', '1998-12-31', 'RahmenAbwV', 'Zuckerherstellung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (131, 48, '28', '2002-08-01', NULL, 'AbwVerO', 'Herstellung von Papier und Pappe', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (132, 38, '46', '1999-01-01', NULL, 'AbwVerO', 'Steinkohleverkokung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (133, 17, '3', '1981-03-26', '1989-12-31', 'AbwVerwVor', 'Milchverarbeitung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (134, 6, '10', '1981-03-26', '1989-12-31', 'AbwVerwVor', 'Fleischwirtschaft', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:10');
INSERT INTO anhang VALUES (135, 46, '13', '1999-01-01', NULL, 'AbwVerO', 'Holzfaserplatten', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:10');
INSERT INTO anhang VALUES (136, 1, '24', '2002-08-01', NULL, 'AbwVerO', 'Eisen-, Stahl- und Tempergießerei', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (137, 11, '33', '2002-08-01', NULL, 'AbwVerO', 'Wäsche von Abgasen aus der Verbrennung von Abfällen', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (138, 58, '31', '1983-10-01', '1993-06-30', 'AbwVerwVor', 'Wasseraufbereitung, Kühlsysteme', 'NAD_BR1', '1980-01-01', '2009-11-30', '2009-11-30 08:44:38');
INSERT INTO anhang VALUES (139, 63, '20', '1982-06-12', '1998-12-31', 'AbwVerwVor', 'Tierkörperbeseitigung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (140, 38, '46', '1986-09-16', '1995-12-31', 'AbwVerwVor', 'Steinkohleverkokung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (141, 45, '7', '1981-03-26', '1991-12-31', 'AbwVerwVor', 'Fischverarbeitung', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (142, 8, '2', '1980-02-12', '1989-12-31', 'AbwVerwVor', 'Braunkohle, Brikettfabrikation', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (143, 9, '53', '1999-01-01', NULL, 'AbwVerO', 'Fotografische Prozesse (Silberhalogenid-Fotografie)', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (144, 23, '47', '1990-01-01', '2002-07-31', 'RahmenAbwV', 'Wäsche von Rauchgasen aus Feuerungsanlagen', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (145, 62, '19 Teil B', '1992-01-01', '2002-07-31', 'RahmenAbwV', 'Herstellung von Papier und Pappe', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (146, 1, '24', '1982-06-12', '1995-12-31', 'AbwVerwVor', 'Eisen- und Stahlerzeugnisse', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (147, 33, '42', '1997-04-01', NULL, 'AbwVerO', 'Alkalichloridelektrolyse', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');
INSERT INTO anhang VALUES (148, 33, '42', '1984-09-26', '1997-03-31', 'AbwVerwVor', 'Alkalichloridelektrolyse nach dem Amalgamverfahren', 'DEA_IT.NRW', '1980-01-01', '2003-10-08', '2009-04-29 13:21:11');

SELECT setval('basis.basis_betreiber_id_seq', 1, true);


-- Completed on 2020-04-01 15:14:49

--
-- PostgreSQL database dump complete
--


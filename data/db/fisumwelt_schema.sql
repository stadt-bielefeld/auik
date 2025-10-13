-- Dumped from database version 14.9
-- Dumped by pg_dump version 15.3



SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;



CREATE SCHEMA awsv;

ALTER SCHEMA awsv OWNER TO auikadmin;

CREATE SCHEMA basis;

ALTER SCHEMA basis OWNER TO auikadmin;

CREATE SCHEMA elka;

ALTER SCHEMA elka OWNER TO auikadmin;

CREATE SCHEMA gis;

ALTER SCHEMA gis OWNER TO auikadmin;

CREATE SCHEMA indeinl;

ALTER SCHEMA indeinl OWNER TO auikadmin;

CREATE SCHEMA labor;

ALTER SCHEMA labor OWNER TO auikadmin;

CREATE SCHEMA oberflgw;

ALTER SCHEMA oberflgw OWNER TO auikadmin;

ALTER SCHEMA public OWNER TO auikadmin;



CREATE EXTENSION IF NOT EXISTS postgis WITH SCHEMA public;

COMMENT ON EXTENSION postgis IS 'PostGIS geometry and geography spatial types and functions';

CREATE EXTENSION IF NOT EXISTS tablefunc WITH SCHEMA public;

COMMENT ON EXTENSION tablefunc IS 'functions that manipulate whole tables, including crosstab';

CREATE FUNCTION oberflgw.set_geometries() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
--  Definition für Spalte "the_geom"
    NEW.the_geom := ST_GeometryFromText('POINT(' || NEW.e32 || ' ' || NEW.n32 || ')', 25832);
-- Definition für Spalte "the_geom_b1"
    NEW.the_geom_b1 := ST_Buffer(NEW.the_geom, 250);
-- Definition für Spalte "the_geom_b2
    NEW.the_geom_b2 := ST_Transform(ST_GeometryFromText('POINT(' || NEW.e32 || ' ' || NEW.n32 || ')', 25832),4326);
  RETURN NEW;
END;
$$;

ALTER FUNCTION oberflgw.set_geometries() OWNER TO auikadmin;

CREATE FUNCTION oberflgw.set_the_geom() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.the_geom := ST_GeometryFromText('POINT(' || NEW.e32 || ' ' || NEW.n32 || ')', 25832);
  RETURN NEW;
END;
$$;

ALTER FUNCTION oberflgw.set_the_geom() OWNER TO auikadmin;

CREATE FUNCTION public.is_date(s character varying) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
begin
  perform s::date;
  return true;
exception when others then
  return false;
end;
$$;

ALTER FUNCTION public.is_date(s character varying) OWNER TO auikadmin;

SET default_tablespace = '';

SET default_table_access_method = heap;

CREATE TABLE awsv.abfuellflaeche (
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

ALTER TABLE awsv.abfuellflaeche OWNER TO auikadmin;

CREATE TABLE awsv.abscheider (
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

ALTER TABLE awsv.abscheider OWNER TO auikadmin;

CREATE TABLE awsv.anlagenarten (
    id integer NOT NULL,
    anlagenart character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE awsv.anlagenarten OWNER TO auikadmin;

CREATE TABLE awsv.anlagenchrono (
    id integer NOT NULL,
    datum timestamp without time zone,
    sachverhalt character varying(255),
    wv timestamp without time zone,
    behaelterid integer NOT NULL,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    abgeschlossen boolean DEFAULT false
);

ALTER TABLE awsv.anlagenchrono OWNER TO auikadmin;

CREATE SEQUENCE awsv.awsv_fachdaten_behaelterid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE awsv.awsv_fachdaten_behaelterid_seq OWNER TO auikadmin;

CREATE TABLE awsv.behaelterart (
    id integer NOT NULL,
    behaelterart character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE awsv.behaelterart OWNER TO auikadmin;

CREATE TABLE awsv.fachdaten (
    behaelterid integer DEFAULT nextval('awsv.awsv_fachdaten_behaelterid_seq'::regclass) NOT NULL,
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

ALTER TABLE awsv.fachdaten OWNER TO auikadmin;

CREATE TABLE awsv.fluessigkeit (
    id integer NOT NULL,
    fluessigkeit character varying(255),
    idland integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE awsv.fluessigkeit OWNER TO auikadmin;

CREATE TABLE awsv.gebuehrenarten (
    id integer NOT NULL,
    gebuehrenart character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE awsv.gebuehrenarten OWNER TO auikadmin;

CREATE TABLE awsv.gefaehrdungsstufen (
    id integer NOT NULL,
    gefaehrdungsstufen character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE awsv.gefaehrdungsstufen OWNER TO auikadmin;

CREATE TABLE awsv.jgs (
    id integer NOT NULL,
    behaelterid integer,
    lagerflaeche integer,
    gewaesser_abstand integer,
    gewaesser_name character varying,
    brunnen_abstand integer,
    tierhaltung character varying,
    seitenwaende boolean,
    wandhoehe double precision,
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

ALTER TABLE awsv.jgs OWNER TO auikadmin;

CREATE TABLE awsv.kontrollen (
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

ALTER TABLE awsv.kontrollen OWNER TO auikadmin;

CREATE TABLE awsv.wassereinzugsgebiet (
    id integer NOT NULL,
    ezgbname character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE awsv.wassereinzugsgebiet OWNER TO auikadmin;

CREATE TABLE basis.adresse (
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
    ueberschgeb boolean DEFAULT false,
    entgebid character varying(255),
    strasseeigent character varying(255),
    wassermenge integer,
    sachbe33rav character varying(255),
    sachbe33hee character varying(255),
    standortgegebid integer,
    wassereinzgebid integer
);

ALTER TABLE basis.adresse OWNER TO auikadmin;

CREATE TABLE basis.inhaber (
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
    _deleted boolean DEFAULT false NOT NULL,
    oberflid integer
);

ALTER TABLE basis.inhaber OWNER TO auikadmin;

CREATE TABLE basis.objekt (
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
    lageid integer,
    prioritaet character varying,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    abwasserfrei boolean DEFAULT true,
    standortid integer,
    sachbearbeiter integer,
    elkarelevant boolean DEFAULT false
);

ALTER TABLE basis.objekt OWNER TO auikadmin;

CREATE TABLE basis.objektarten (
    id integer NOT NULL,
    objektart character varying(255),
    abteilung character varying(255),
    kategorie character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE basis.objektarten OWNER TO auikadmin;

CREATE TABLE basis.standort (
    id integer NOT NULL,
    inhaberid integer,
    lageid integer,
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
    the_geom public.geometry(Point,25832),
    oberflid integer
);

ALTER TABLE basis.standort OWNER TO auikadmin;

CREATE VIEW awsv.lageranlagen AS
 SELECT fachdaten.behaelterid,
    objekt.id,
    standort.e32,
    standort.n32,
    objektarten.abteilung,
    adresse_1.strasse,
    adresse_1.hausnr,
    adresse_1.hausnrzus,
    inhaber.name,
    inhaber.vorname,
    adresse.strasse AS betr_strasse,
    adresse.hausnr AS betr_hausnr,
    adresse.hausnrzus AS betr_hausnrzus,
    objektarten.objektart,
    objekt.inaktiv,
    fachdaten.unterirdisch,
    wassereinzugsgebiet.ezgbname,
    fachdaten.menge,
    fachdaten.fluessigkeit,
    standort.the_geom
   FROM (awsv.wassereinzugsgebiet
     JOIN (basis.adresse adresse_1
     JOIN (basis.inhaber inhaber_1
     JOIN ((((basis.adresse
     JOIN basis.inhaber ON ((adresse.id = inhaber.adresseid)))
     JOIN (basis.standort
     JOIN basis.objekt ON ((standort.id = objekt.standortid))) ON ((inhaber.id = objekt.betreiberid)))
     JOIN basis.objektarten ON ((objekt.objektartid = objektarten.id)))
     JOIN awsv.fachdaten ON ((objekt.id = fachdaten.objektid))) ON ((inhaber_1.id = standort.inhaberid))) ON ((adresse_1.id = inhaber_1.adresseid))) ON ((wassereinzugsgebiet.id = adresse_1.wassereinzgebid)))
  WHERE (((objektarten.abteilung)::text = 'AwSV'::text) AND ((objektarten.objektart)::text <> 'JGS-Anlagen'::text) AND (objekt.inaktiv = false) AND (fachdaten.stillegungsdatum IS NULL) AND ((fachdaten.anlagenart)::text <> 'Rohrleitung'::text) AND ((fachdaten.anlagenart)::text <> 'AwSV-Abscheider'::text) AND ((fachdaten.anlagenart)::text <> 'Abfüllfläche'::text))
  ORDER BY objektarten.objektart;

ALTER TABLE awsv.lageranlagen OWNER TO auikadmin;

CREATE TABLE awsv.material (
    id integer NOT NULL,
    material character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE awsv.material OWNER TO auikadmin;

CREATE TABLE awsv.pruefer (
    id integer NOT NULL,
    pruefer character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE awsv.pruefer OWNER TO auikadmin;

CREATE TABLE awsv.pruefergebniss (
    id integer NOT NULL,
    pruefergebnis character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE awsv.pruefergebniss OWNER TO auikadmin;

CREATE TABLE awsv.standortgghwsg (
    id integer NOT NULL,
    standortgg character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE awsv.standortgghwsg OWNER TO auikadmin;

CREATE SEQUENCE awsv.vaws_abfuellflaeche_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE awsv.vaws_abfuellflaeche_id_seq OWNER TO auikadmin;

ALTER SEQUENCE awsv.vaws_abfuellflaeche_id_seq OWNED BY awsv.abfuellflaeche.id;

CREATE SEQUENCE awsv.vaws_abscheider_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE awsv.vaws_abscheider_id_seq OWNER TO auikadmin;

ALTER SEQUENCE awsv.vaws_abscheider_id_seq OWNED BY awsv.abscheider.id;

CREATE SEQUENCE awsv.vaws_anlagenchrono_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE awsv.vaws_anlagenchrono_id_seq OWNER TO auikadmin;

ALTER SEQUENCE awsv.vaws_anlagenchrono_id_seq OWNED BY awsv.anlagenchrono.id;

CREATE SEQUENCE awsv.vaws_jgs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE awsv.vaws_jgs_id_seq OWNER TO auikadmin;

ALTER SEQUENCE awsv.vaws_jgs_id_seq OWNED BY awsv.jgs.id;

CREATE SEQUENCE awsv.vaws_kontrollen_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE awsv.vaws_kontrollen_id_seq OWNER TO auikadmin;

ALTER SEQUENCE awsv.vaws_kontrollen_id_seq OWNED BY awsv.kontrollen.id;

CREATE TABLE awsv.verwaltungsgebuehren (
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

ALTER TABLE awsv.verwaltungsgebuehren OWNER TO auikadmin;

CREATE SEQUENCE awsv.vaws_verwaltungsgebuehren_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE awsv.vaws_verwaltungsgebuehren_id_seq OWNER TO auikadmin;

ALTER SEQUENCE awsv.vaws_verwaltungsgebuehren_id_seq OWNED BY awsv.verwaltungsgebuehren.id;

CREATE TABLE awsv.verwaltungsverf (
    id integer NOT NULL,
    datum timestamp without time zone,
    massnahme character varying(255),
    wiedervorlage timestamp without time zone,
    wvverwverf boolean,
    behaelterid integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE awsv.verwaltungsverf OWNER TO auikadmin;

CREATE SEQUENCE awsv.vaws_verwaltungsverf_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE awsv.vaws_verwaltungsverf_id_seq OWNER TO auikadmin;

ALTER SEQUENCE awsv.vaws_verwaltungsverf_id_seq OWNED BY awsv.verwaltungsverf.id;

CREATE TABLE awsv.vbfeinstufung (
    id integer NOT NULL,
    vbfeinstufung character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE awsv.vbfeinstufung OWNER TO auikadmin;

CREATE TABLE awsv.verwmassnahmen (
    id integer NOT NULL,
    massnahmen character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE awsv.verwmassnahmen OWNER TO auikadmin;

CREATE VIEW basis."_FieldsSubset_of_standort" AS
 SELECT row_number() OVER () AS ogc_fid
   FROM basis.standort;

ALTER TABLE basis."_FieldsSubset_of_standort" OWNER TO auikadmin;

CREATE SEQUENCE basis.basis_betreiber_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE basis.basis_betreiber_id_seq OWNER TO auikadmin;

ALTER SEQUENCE basis.basis_betreiber_id_seq OWNED BY basis.adresse.id;

CREATE TABLE basis.lage (
    id integer NOT NULL,
    plz character varying(255),
    e32 real,
    n32 real,
    flur character varying(255),
    flurstueck character varying(255),
    entgebid character varying(255),
    strasseeigent character varying(255),
    revidatum timestamp without time zone,
    revihandz character varying(255),
    wassermenge integer,
    sachbe33rav character varying(255),
    sachbe33hee character varying(255),
    gemarkungid integer,
    standortgegebid integer,
    wassereinzgebid integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    oberflid integer,
    igl_id integer,
    adresseid integer,
    ueberschgeb boolean,
    the_geom public.geometry(Point,25832)
);

ALTER TABLE basis.lage OWNER TO auikadmin;

CREATE SEQUENCE basis.basis_lage_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE basis.basis_lage_id_seq OWNER TO auikadmin;

ALTER SEQUENCE basis.basis_lage_id_seq OWNED BY basis.lage.id;

CREATE SEQUENCE basis.basis_objekt_objektid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE basis.basis_objekt_objektid_seq OWNER TO auikadmin;

ALTER SEQUENCE basis.basis_objekt_objektid_seq OWNED BY basis.objekt.id;

CREATE TABLE basis.objektchrono (
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

ALTER TABLE basis.objektchrono OWNER TO auikadmin;

CREATE SEQUENCE basis.basis_objektchrono_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE basis.basis_objektchrono_id_seq OWNER TO auikadmin;

ALTER SEQUENCE basis.basis_objektchrono_id_seq OWNED BY basis.objektchrono.id;

CREATE TABLE basis.objektverknuepfung (
    id integer NOT NULL,
    ist_verknuepft_mit integer,
    objekt integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE basis.objektverknuepfung OWNER TO auikadmin;

CREATE SEQUENCE basis.basis_objektverknuepfung_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE basis.basis_objektverknuepfung_id_seq OWNER TO auikadmin;

ALTER SEQUENCE basis.basis_objektverknuepfung_id_seq OWNED BY basis.objektverknuepfung.id;

CREATE TABLE basis.sachbearbeiter (
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

ALTER TABLE basis.sachbearbeiter OWNER TO auikadmin;

CREATE SEQUENCE basis.basis_sachbearbeiter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE basis.basis_sachbearbeiter_id_seq OWNER TO auikadmin;

ALTER SEQUENCE basis.basis_sachbearbeiter_id_seq OWNED BY basis.sachbearbeiter.id;

CREATE SEQUENCE basis.basis_standort_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE basis.basis_standort_id_seq OWNER TO auikadmin;

ALTER SEQUENCE basis.basis_standort_id_seq OWNED BY basis.standort.id;

CREATE TABLE basis.bezeichnung (
    id integer NOT NULL,
    gruppe character varying(255) NOT NULL,
    bezeichnung character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE basis.bezeichnung OWNER TO auikadmin;

CREATE TABLE basis.gemarkung (
    id integer NOT NULL,
    gemarkung character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE basis.gemarkung OWNER TO auikadmin;

CREATE SEQUENCE basis.inhaber_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE basis.inhaber_id_seq OWNER TO auikadmin;

ALTER SEQUENCE basis.inhaber_id_seq OWNED BY basis.inhaber.id;

CREATE TABLE basis.prioritaet (
    standort_id integer NOT NULL,
    betreiber_id integer NOT NULL,
    prioritaet integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE basis.prioritaet OWNER TO auikadmin;

CREATE TABLE basis.strassen (
    id integer NOT NULL,
    strasse character varying(255),
    plz character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    ort character varying
);

ALTER TABLE basis.strassen OWNER TO auikadmin;

CREATE VIEW basis.view_two_way_objektverknuepfung AS
 SELECT basis_objektverknuepfung.id,
    basis_objektverknuepfung.ist_verknuepft_mit,
    basis_objektverknuepfung.objekt,
    basis_objektverknuepfung._enabled,
    basis_objektverknuepfung._deleted
   FROM basis.objektverknuepfung basis_objektverknuepfung
UNION
 SELECT (- basis_objektverknuepfung.id) AS id,
    basis_objektverknuepfung.objekt AS ist_verknuepft_mit,
    basis_objektverknuepfung.ist_verknuepft_mit AS objekt,
    basis_objektverknuepfung._enabled,
    basis_objektverknuepfung._deleted
   FROM basis.objektverknuepfung basis_objektverknuepfung
  ORDER BY 2;

ALTER TABLE basis.view_two_way_objektverknuepfung OWNER TO auikadmin;

CREATE TABLE basis.wirtschaftszweig (
    id integer NOT NULL,
    wirtschaftszweig character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE basis.wirtschaftszweig OWNER TO auikadmin;

CREATE VIEW elka._q_z AS
 SELECT DISTINCT objektverknuepfung.ist_verknuepft_mit AS quelle,
    objektverknuepfung.objekt AS ziel
   FROM basis.objektverknuepfung
  WHERE (objektverknuepfung._deleted = false)
UNION
 SELECT DISTINCT objektverknuepfung.objekt AS quelle,
    objektverknuepfung.ist_verknuepft_mit AS ziel
   FROM basis.objektverknuepfung
  WHERE (objektverknuepfung._deleted = false);

ALTER TABLE elka._q_z OWNER TO auikadmin;

CREATE TABLE elka.aba (
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

ALTER TABLE elka.aba OWNER TO auikadmin;

CREATE TABLE elka.abaverfahren (
    nr integer NOT NULL,
    aktual_dat date,
    erstell_dat date,
    bezeichnung character varying
);

ALTER TABLE elka.abaverfahren OWNER TO auikadmin;

CREATE TABLE elka.anfallstelle (
    id integer NOT NULL,
    objektid integer,
    seq_id integer,
    aktual_dat date,
    erstell_dat date,
    anhang_id character varying,
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

ALTER TABLE elka.anfallstelle OWNER TO auikadmin;

CREATE TABLE elka.anhang (
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


ALTER TABLE elka.anhang OWNER TO auikadmin;

CREATE TABLE elka.wasserrecht (
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
    gen8 boolean,
    nebenbest character varying(255)
);

ALTER TABLE elka.wasserrecht OWNER TO auikadmin;

CREATE VIEW elka.e_adresse AS
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
    elka.wasserrecht,
    basis.standort
  WHERE (((adresse.id = inhaber.adresseid) AND (inhaber.id = standort.inhaberid) AND (objekt.id = wasserrecht.objektid) AND (standort.id = objekt.standortid) AND (wasserrecht.gen59 = true) AND (objekt._deleted = false) AND (objekt.inaktiv = false)) OR ((adresse.id = inhaber.adresseid) AND (inhaber.id = standort.inhaberid) AND (objekt.id = wasserrecht.objektid) AND (standort.id = objekt.standortid) AND (wasserrecht.gen8 = true) AND (objekt._deleted = false) AND (objekt.inaktiv = false)))
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
    elka.wasserrecht
  WHERE (((adresse.id = inhaber.adresseid) AND (inhaber.id = objekt.betreiberid) AND (objekt.id = wasserrecht.objektid) AND (wasserrecht.gen59 = true) AND (objekt._deleted = false) AND (objekt.inaktiv = false)) OR ((adresse.id = inhaber.adresseid) AND (inhaber.id = objekt.betreiberid) AND (objekt.id = wasserrecht.objektid) AND (wasserrecht.gen8 = true) AND (objekt._deleted = false) AND (objekt.inaktiv = false)));

ALTER TABLE elka.e_adresse OWNER TO auikadmin;

CREATE VIEW elka.e_standort AS
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
   FROM elka.e_adresse,
    basis.standort,
    basis.gemarkung
  WHERE ((e_adresse.nr = standort.inhaberid) AND (e_adresse.gemarkungid = gemarkung.id) AND (standort.e32 IS NOT NULL) AND (standort.e32 > (100000)::double precision) AND (standort.e32 < (999999)::double precision));

ALTER TABLE elka.e_standort OWNER TO auikadmin;

CREATE VIEW elka.e_abwasserbehandlungsanlage AS
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
   FROM elka.e_adresse,
    elka.e_standort,
    basis.objekt,
    elka.aba
  WHERE ((objekt.standortid = e_standort.nr) AND (objekt.betreiberid = e_adresse.nr) AND (aba.objektid = objekt.id) AND (objekt._deleted = false) AND (objekt.inaktiv = false) AND (objekt.elkarelevant = true));

ALTER TABLE elka.e_abwasserbehandlungsanlage OWNER TO auikadmin;

CREATE SEQUENCE elka.e_adresse_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE elka.e_adresse_seq OWNER TO auikadmin;

CREATE TABLE oberflgw.afs_niederschlagswasser (
    nr integer NOT NULL,
    lfd_nr integer,
    bezeichnung character varying(80),
    bef_flaeche integer,
    nw_her_bereich_opt integer,
    abflussmenge numeric(11,2),
    anfallstellen_nr bigint,
    entw_grund_nr bigint,
    anfallstelleid integer,
    _enabled boolean,
    _deleted boolean
);

ALTER TABLE oberflgw.afs_niederschlagswasser OWNER TO auikadmin;

CREATE VIEW elka.e_afs_niederschlagswasser AS
 SELECT DISTINCT afs_niederschlagswasser.nr,
        CASE
            WHEN (afs_niederschlagswasser.bezeichnung IS NOT NULL) THEN "left"((afs_niederschlagswasser.bezeichnung)::text, 80)
            ELSE '>>Bezeichung folgt später<<'::text
        END AS bezeichnung,
    afs_niederschlagswasser.bef_flaeche,
    afs_niederschlagswasser.nw_her_bereich_opt,
    afs_niederschlagswasser.abflussmenge,
    afs_niederschlagswasser.anfallstellen_nr,
    afs_niederschlagswasser.entw_grund_nr
   FROM oberflgw.afs_niederschlagswasser;

ALTER TABLE elka.e_afs_niederschlagswasser OWNER TO auikadmin;

CREATE VIEW elka.e_anfallstelle AS
 SELECT DISTINCT anfallstelle.objektid AS nr,
    objekt.standortid AS standort_nr,
    objekt.betreiberid AS adresse,
    anfallstelle.anhang_id,
    anfallstelle.abwa_beschaff_opt,
        CASE
            WHEN (anfallstelle.bezeichnung IS NOT NULL) THEN "left"((anfallstelle.bezeichnung)::text, 64)
            ELSE '>>Bezeichung folgt später<<'::text
        END AS bezeichnung,
    objekt.beschreibung AS bemerkung,
    false AS aufz_betrieb_tog,
    anfallstelle.aktual_dat,
        CASE
            WHEN (anfallstelle.erstell_dat IS NOT NULL) THEN (anfallstelle.erstell_dat)::timestamp without time zone
            ELSE '1970-01-01 00:00:00'::timestamp without time zone
        END AS erstell_dat,
    'ELKA_KR711'::text AS herkunft
   FROM elka.e_adresse,
    elka.e_standort,
    basis.objekt,
    elka.anfallstelle
  WHERE ((objekt.standortid = e_standort.nr) AND (objekt.betreiberid = e_adresse.nr) AND (anfallstelle.objektid = objekt.id) AND (objekt._deleted = false) AND (objekt.inaktiv = false) AND (objekt.elkarelevant = true));

ALTER TABLE elka.e_anfallstelle OWNER TO "BI005380";

CREATE VIEW elka.e_betrieb AS
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
   FROM elka.e_adresse,
    basis.objekt,
    elka.e_standort
  WHERE ((e_adresse.nr = objekt.betreiberid) AND (e_standort.nr = objekt.standortid) AND (objekt.objektartid = 42) AND (e_standort.industrieabwasser_tog = true));

ALTER TABLE elka.e_betrieb OWNER TO auikadmin;

CREATE TABLE elka.einleitungsstelle (
    id integer NOT NULL,
    objektid integer,
    aktual_dat date,
    erstell_dat date,
    herkunft character varying,
    bezeichnung character varying,
    gewaessername_alias_3 character varying,
    gewaessername_ns character varying,
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
    stationierung_3_opt integer,
    schutzzone_opt smallint,
    _deleted boolean,
    _enabled boolean,
    the_geom public.geometry(Point,25832),
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
    nr integer,
    entf_einl_stat_gew double precision,
    mwq double precision,
    hq1 double precision,
    bemerkung_gew_daten character varying,
    abw_ag_einl character varying,
    gew_name_stadt character varying,
    gew_kennz_stadt character varying,
    hwr_massnahme character varying,
    gewname_stat character varying,
    gewkennz_stat character varying
);

ALTER TABLE elka.einleitungsstelle OWNER TO auikadmin;

CREATE VIEW elka.e_einleitungsstelle AS
 SELECT DISTINCT einleitungsstelle.objektid AS nr,
    objekt.standortid AS standort_nr,
    einleitungsstelle.typ_indirekteinleitung_tog,
    einleitungsstelle.typ_indus_gewerb_direkteinleitung_tog,
    einleitungsstelle.typ_komm_nw_misch_tog,
    einleitungsstelle.typ_komm_nw_trenn_tog,
    einleitungsstelle.typ_nw_privat_trenn_tog,
    einleitungsstelle.typ_indus_gewerb_nw_misch_tog,
    einleitungsstelle.typ_indus_gewerb_nw_trenn_tog,
    einleitungsstelle.typ_grubenwasser_tog,
    einleitungsstelle.typ_kleinklaeranlage_tog,
    einleitungsstelle.typ_komm_ka_tog,
    einleitungsstelle.typ_ausseroertliche_strasseneinleitung_tog,
    einleitungsstelle.typ_sonstige_tog,
    einleitungsstelle.abgaberelevante_elt_opt,
    einleitungsstelle.bezeichnung,
    einleitungsstelle.gewaessername_alias_3,
    einleitungsstelle.einzugsgebiet,
    einleitungsstelle.fluss_gebiet_3_id,
    einleitungsstelle.stationierung_3_opt,
    einleitungsstelle.stationierung_st_3,
    einleitungsstelle.stationierung_ns_3,
    einleitungsstelle.e32,
    einleitungsstelle.n32,
    einleitungsstelle.kanal_art_opt,
    einleitungsstelle.stillgelegt_am,
    einleitungsstelle.schutzzone_opt,
    einleitungsstelle.gewaesser_3_id,
    einleitungsstelle.see_ns_3_id,
    einleitungsstelle.seename_alias_3,
    einleitungsstelle.see_auflage_3_id,
    einleitungsstelle.see_3_id,
    einleitungsstelle.einleitungsstellen_id,
    einleitungsstelle.gewaesser_auflage_3_id,
    einleitungsstelle.bemerkung,
    einleitungsstelle.fluss_auflage_3_id,
    einleitungsstelle.gewaessername_ns_3,
    einleitungsstelle.gwk_id,
    einleitungsstelle.ka_name_ausserhalb_nrw,
    einleitungsstelle.external_nr,
    false AS ka_nicht_in_nrw_tog,
    einleitungsstelle.aktual_dat,
    einleitungsstelle.erstell_dat,
    'ELKA_KR711'::text AS herkunft
   FROM elka.e_adresse,
    elka.e_standort,
    basis.objekt,
    elka.einleitungsstelle
  WHERE ((objekt.standortid = e_standort.nr) AND (objekt.betreiberid = e_adresse.nr) AND (einleitungsstelle.objektid = objekt.id) AND (objekt._deleted = false) AND (objekt.inaktiv = false) AND (objekt.elkarelevant = true));

ALTER TABLE elka.e_einleitungsstelle OWNER TO "BI005380";

CREATE TABLE oberflgw.entwaesserungsgrundstueck (
    nr integer NOT NULL,
    objekt_id bigint NOT NULL,
    erl_frei_el_tog boolean DEFAULT false NOT NULL,
    regenspende numeric(10,1),
    bemerkung character varying(1000),
    regenhaeufigkeit numeric(10,1),
    regendauer integer,
    gr_entw_gebiet integer,
    dtv_wert numeric(10,1),
    wasserableitungsstrecke_opt integer,
    name_etw_gebiet character varying,
    erstell_dat timestamp without time zone,
    einl_bereich_opt integer,
    abwbeskon_nr character varying(30),
    einbauart_opt integer,
    aktual_dat timestamp without time zone,
    adr_nr bigint,
    wasserrecht_nr bigint,
    external_nr character varying(50),
    _enabled boolean,
    _deleted boolean,
    wo_tog boolean,
    mi_tog boolean,
    ge_tog boolean,
    gi_tog boolean,
    gem_tog boolean,
    str_tog boolean,
    parkplatz_tog boolean
);

ALTER TABLE oberflgw.entwaesserungsgrundstueck OWNER TO auikadmin;

CREATE VIEW elka.e_entwaesserungsgrundstueck AS
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
        CASE
            WHEN ((entwaesserungsgrundstueck.name_etw_gebiet IS NULL) OR ((entwaesserungsgrundstueck.name_etw_gebiet)::text = ''::text)) THEN '>>Name folgt später<<'::character varying
            ELSE entwaesserungsgrundstueck.name_etw_gebiet
        END AS name_etw_gebiet,
        CASE
            WHEN (entwaesserungsgrundstueck.erstell_dat IS NOT NULL) THEN entwaesserungsgrundstueck.erstell_dat
            ELSE '1970-01-01 00:00:00'::timestamp without time zone
        END AS erstell_dat,
    entwaesserungsgrundstueck.einl_bereich_opt,
    entwaesserungsgrundstueck.abwbeskon_nr,
    entwaesserungsgrundstueck.einbauart_opt,
    entwaesserungsgrundstueck.aktual_dat,
    objekt.betreiberid,
    entwaesserungsgrundstueck.wasserrecht_nr AS wasserecht_nr,
    'ELKA_KR711'::text AS herkunft,
    entwaesserungsgrundstueck.external_nr
   FROM elka.e_adresse,
    elka.e_standort,
    basis.objekt,
    oberflgw.entwaesserungsgrundstueck
  WHERE ((objekt.standortid = e_standort.nr) AND (objekt.betreiberid = e_adresse.nr) AND (entwaesserungsgrundstueck.objekt_id = objekt.id) AND (objekt._deleted = false));

ALTER TABLE elka.e_entwaesserungsgrundstueck OWNER TO auikadmin;

CREATE TABLE labor.messstelle (
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

ALTER TABLE labor.messstelle OWNER TO auikadmin;

CREATE VIEW elka.e_messstelle AS
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
   FROM elka.e_adresse,
    elka.e_standort,
    basis.objekt,
    labor.messstelle
  WHERE ((objekt.standortid = e_standort.nr) AND (objekt.betreiberid = e_adresse.nr) AND (messstelle.objektid = objekt.id) AND (messstelle.art_id = 2) AND (objekt._deleted = false) AND (objekt.inaktiv = false) AND (objekt.elkarelevant = true));

ALTER TABLE elka.e_messstelle OWNER TO auikadmin;

CREATE TABLE labor.probenahme (
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

ALTER TABLE labor.probenahme OWNER TO auikadmin;

CREATE VIEW elka.e_probenahme AS
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
    elka.e_messstelle
  WHERE (e_messstelle.nr = probenahme.messstid);

ALTER TABLE elka.e_probenahme OWNER TO auikadmin;

CREATE TABLE elka.map_elka_einheit (
    id integer NOT NULL,
    name character varying,
    id_elka integer,
    id_auik integer
);

ALTER TABLE elka.map_elka_einheit OWNER TO auikadmin;

CREATE TABLE elka.map_elka_stoff (
    id integer NOT NULL,
    name character varying,
    id_elka integer,
    id_auik character varying
);

ALTER TABLE elka.map_elka_stoff OWNER TO auikadmin;

CREATE TABLE labor.analyseposition (
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
    normwert_neu double precision,
    gruppe_dev_id character varying(3),
    regelwerk_id character varying(2),
    varianten_id character varying(1),
    methode_id integer
);

ALTER TABLE labor.analyseposition OWNER TO auikadmin;

CREATE VIEW elka.e_probenahme_ueberwachungsergeb AS
 SELECT analyseposition.id AS nr,
    analyseposition.probenahme_id AS probenahme_nr,
    map_elka_stoff.id_elka AS stoff_nr,
    '000'::character varying(3) AS gruppe_dev_id,
    '00'::character varying(2) AS regelwerk_id,
    '0'::character varying(1) AS varianten_id,
    '1'::character varying(3) AS trenn_nr_opt,
    'A'::character varying(1) AS prob_schluessel_id,
        CASE
            WHEN ((analyseposition.grkl)::text ~~ '<'::text) THEN 1
            WHEN ((analyseposition.grkl)::text ~~ '>'::text) THEN 2
            WHEN ((analyseposition.grkl)::text ~~ '='::text) THEN 3
            ELSE NULL::integer
        END AS messergebnis_text_opt,
    round((analyseposition.wert)::numeric, 3) AS wert,
    map_elka_einheit.id_elka
   FROM elka.e_probenahme,
    labor.analyseposition,
    elka.map_elka_stoff,
    elka.map_elka_einheit
  WHERE ((e_probenahme.nr = analyseposition.probenahme_id) AND (analyseposition.einheiten_id = map_elka_einheit.id_auik) AND ((analyseposition.parameter_id)::text = (map_elka_stoff.id_auik)::text));

ALTER TABLE elka.e_probenahme_ueberwachungsergeb OWNER TO auikadmin;

CREATE TABLE oberflgw.sonderbauwerk (
    objekt_id bigint NOT NULL,
    gemeinde_id character varying(8),
    bezeichnung character varying(60),
    kurzbeschreibung character varying(60),
    entw_einzugsgeb_opt integer,
    typ_opt integer,
    inbetriebnahme integer,
    stillgelegt_am date,
    wiederinbetr_dat date,
    anspr_adr_nr bigint,
    bemerkung character varying(1000),
    e32 integer,
    n32 integer,
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
    erstell_dat timestamp without time zone,
    aktual_dat timestamp without time zone,
    external_nr character varying(50),
    nr integer NOT NULL,
    _enabled boolean,
    _deleted boolean
);

ALTER TABLE oberflgw.sonderbauwerk OWNER TO auikadmin;

CREATE VIEW elka.e_sonderbauwerk AS
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
   FROM elka.e_adresse,
    elka.e_standort,
    basis.objekt,
    oberflgw.sonderbauwerk
  WHERE ((objekt.standortid = e_standort.nr) AND (objekt.betreiberid = e_adresse.nr) AND (sonderbauwerk.objekt_id = objekt.id) AND (objekt._deleted = false));

ALTER TABLE elka.e_sonderbauwerk OWNER TO auikadmin;

CREATE VIEW elka.e_wasserrecht AS
 SELECT wasserrecht.objektid AS nr,
    objekt.betreiberid AS adresse,
    objekt.id AS ref_nr,
    wasserrecht.aktenzeichen,
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
   FROM elka.e_adresse,
    elka.e_standort,
    basis.objekt,
    elka.wasserrecht
  WHERE ((objekt.standortid = e_standort.nr) AND (objekt.betreiberid = e_adresse.nr) AND (wasserrecht.objektid = objekt.id) AND (objekt._deleted = false) AND (wasserrecht.aktenzeichen IS NOT NULL));

ALTER TABLE elka.e_wasserrecht OWNER TO "BI005380";

CREATE TABLE elka.z_aba_verfahren (
    id integer NOT NULL,
    anlage_nr bigint NOT NULL,
    abwasbehverf_nr bigint NOT NULL
);

ALTER TABLE elka.z_aba_verfahren OWNER TO auikadmin;

CREATE VIEW elka.e_z_aba_verfahren AS
 SELECT aba.objektid AS anlage_nr,
    z_aba_verfahren.abwasbehverf_nr
   FROM elka.z_aba_verfahren,
    elka.aba
  WHERE (z_aba_verfahren.anlage_nr = aba.id);

ALTER TABLE elka.e_z_aba_verfahren OWNER TO auikadmin;

CREATE VIEW elka.e_z_aba_wasserrecht AS
 SELECT e_abwasserbehandlungsanlage.nr AS anlage_nr,
    e_wasserrecht.nr AS wasserrecht_nr
   FROM ((elka.e_abwasserbehandlungsanlage
     JOIN elka._q_z ON ((e_abwasserbehandlungsanlage.nr = _q_z.quelle)))
     JOIN elka.e_wasserrecht ON ((_q_z.ziel = e_wasserrecht.nr)));

ALTER TABLE elka.e_z_aba_wasserrecht OWNER TO "BI005380";

CREATE VIEW elka.e_z_els_wasserrecht AS
 SELECT e_einleitungsstelle.nr AS els_nr,
    e_wasserrecht.nr AS wasserrecht_nr
   FROM ((elka.e_einleitungsstelle
     JOIN elka._q_z ON ((e_einleitungsstelle.nr = _q_z.quelle)))
     JOIN elka.e_wasserrecht ON ((_q_z.ziel = e_wasserrecht.nr)));

ALTER TABLE elka.e_z_els_wasserrecht OWNER TO "BI005380";

CREATE SEQUENCE elka.elka_aba_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE elka.elka_aba_id_seq OWNER TO auikadmin;

ALTER SEQUENCE elka.elka_aba_id_seq OWNED BY elka.aba.id;

CREATE SEQUENCE elka.elka_anfallstelle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE elka.elka_anfallstelle_id_seq OWNER TO auikadmin;

ALTER SEQUENCE elka.elka_anfallstelle_id_seq OWNED BY elka.anfallstelle.id;

CREATE SEQUENCE elka.elka_einleitungsstelle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE elka.elka_einleitungsstelle_id_seq OWNER TO auikadmin;

ALTER SEQUENCE elka.elka_einleitungsstelle_id_seq OWNED BY elka.einleitungsstelle.id;

CREATE SEQUENCE elka.elka_wasserrecht_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE elka.elka_wasserrecht_id_seq OWNER TO auikadmin;

ALTER SEQUENCE elka.elka_wasserrecht_id_seq OWNED BY elka.wasserrecht.id;

CREATE TABLE elka.map_elka_analysemethode (
    id integer NOT NULL,
    gruppe_dev_id character varying(3) NOT NULL,
    regelwerk_id character varying(2) NOT NULL,
    varianten_id character(1) NOT NULL,
    methoden_nr character varying
);

ALTER TABLE elka.map_elka_analysemethode OWNER TO auikadmin;

CREATE SEQUENCE elka.map_elka_analysemethode_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE elka.map_elka_analysemethode_id_seq OWNER TO auikadmin;

ALTER SEQUENCE elka.map_elka_analysemethode_id_seq OWNED BY elka.map_elka_analysemethode.id;

CREATE TABLE elka.map_elka_anhang (
    id integer NOT NULL,
    anhang_auik integer,
    id_elka character varying
);

ALTER TABLE elka.map_elka_anhang OWNER TO auikadmin;

CREATE TABLE elka.map_elka_gewkennz (
    gewkz integer,
    gewname character varying
);

ALTER TABLE elka.map_elka_gewkennz OWNER TO auikadmin;

CREATE TABLE elka.referenz (
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

ALTER TABLE elka.referenz OWNER TO auikadmin;

CREATE SEQUENCE elka.referenz_nr_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE elka.referenz_nr_seq OWNER TO auikadmin;

ALTER SEQUENCE elka.referenz_nr_seq OWNED BY elka.referenz.nr;

CREATE SEQUENCE elka.z_aba_verfahren_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE elka.z_aba_verfahren_id_seq OWNER TO auikadmin;

ALTER SEQUENCE elka.z_aba_verfahren_id_seq OWNED BY elka.z_aba_verfahren.id;

CREATE VIEW gis.okd_awsv AS
 SELECT DISTINCT objekt.id,
    inhaber.name AS "Betreiber",
    adresse.strasse,
    concat_ws(''::text, adresse.hausnr, adresse.hausnrzus) AS hausnummer,
    adresse.plz,
    wassereinzugsgebiet.ezgbname AS "AwsV-Bezirk (AUIK)",
    fachdaten.aktenzeichen,
    objektarten.objektart,
    fachdaten.anlagenart,
    fachdaten.fluessigkeit,
    fachdaten.herstellnr AS "Herstellernummer",
    fachdaten.menge AS "Menge in m³",
    fachdaten.wgk AS "Wassergefährdungsklasse",
    fachdaten.stillegungsdatum,
    standort.the_geom
   FROM basis.inhaber,
    basis.adresse,
    basis.objekt,
    basis.standort,
    awsv.fachdaten,
    awsv.wassereinzugsgebiet,
    basis.sachbearbeiter,
    basis.objektarten
  WHERE ((inhaber.adresseid = adresse.id) AND (objekt.standortid = standort.id) AND (objekt.id = fachdaten.objektid) AND (standort.inhaberid = inhaber.id) AND (adresse.wassereinzgebid = wassereinzugsgebiet.id) AND (objekt.objektartid = objektarten.id) AND (objekt._deleted = false) AND (fachdaten._deleted = false) AND ((objektarten.objektart)::text !~~ 'JGS-Anlagen'::text) AND (fachdaten.menge > (0.22)::double precision))
UNION
 SELECT DISTINCT objekt.id,
    inhaber.name AS "Betreiber",
    adresse.strasse,
    concat_ws(''::text, adresse.hausnr, adresse.hausnrzus) AS hausnummer,
    adresse.plz,
    wassereinzugsgebiet.ezgbname AS "AwsV-Bezirk (AUIK)",
    fachdaten.aktenzeichen,
    objektarten.objektart,
    fachdaten.anlagenart,
    fachdaten.fluessigkeit,
    fachdaten.herstellnr AS "Herstellernummer",
    fachdaten.menge AS "Menge in m³",
    fachdaten.wgk AS "Wassergefährdungsklasse",
    fachdaten.stillegungsdatum,
    standort.the_geom
   FROM basis.inhaber,
    basis.adresse,
    basis.objekt,
    basis.standort,
    awsv.fachdaten,
    awsv.wassereinzugsgebiet,
    basis.sachbearbeiter,
    basis.objektarten
  WHERE ((inhaber.adresseid = adresse.id) AND (objekt.standortid = standort.id) AND (objekt.id = fachdaten.objektid) AND (standort.inhaberid = inhaber.id) AND (adresse.wassereinzgebid = wassereinzugsgebiet.id) AND (objekt.objektartid = objektarten.id) AND (objekt._deleted = false) AND (fachdaten._deleted = false) AND ((objektarten.objektart)::text = 'JGS-Anlagen'::text));

ALTER TABLE gis.okd_awsv OWNER TO auikadmin;

COMMENT ON VIEW gis.okd_awsv IS '';

CREATE TABLE indeinl.bwk_fachdaten (
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

ALTER TABLE indeinl.bwk_fachdaten OWNER TO auikadmin;

CREATE VIEW gis.okd_indeinl AS
 SELECT DISTINCT concat_ws(', '::text, inhaber.name, inhaber.vorname) AS betreiber,
    concat_ws(' '::text, adresse.strasse, adresse.hausnr, adresse.hausnrzus, ',', adresse.plz, 'Bielefeld') AS adresse,
    adresse.entgebid,
    concat_ws(' '::text, anfallstelle.anhang_id, anhang.anh_text) AS anhang,
    standort.e32,
    standort.n32,
    standort.the_geom
   FROM basis.adresse,
    basis.inhaber,
    basis.objekt,
    basis.objektarten,
    elka.anfallstelle,
    basis.standort,
    elka.anhang
  WHERE ((adresse.id = inhaber.adresseid) AND (inhaber.id = standort.inhaberid) AND (objekt.id = anfallstelle.objektid) AND (objekt.objektartid = objektarten.id) AND ((anfallstelle.anhang_id)::text = (anhang.anhang_id)::text) AND (standort.id = objekt.standortid) AND ((anfallstelle.anhang_id)::text <> '99'::text) AND ((anfallstelle.anhang_id)::text <> '53'::text) AND (objekt._deleted = false) AND (objekt.inaktiv = false) AND (objekt.abwasserfrei = false))
UNION
 SELECT DISTINCT concat_ws(', '::text, inhaber.name, inhaber.vorname) AS betreiber,
    concat_ws(' '::text, adresse.strasse, adresse.hausnr, adresse.hausnrzus, ',', adresse.plz, 'Bielefeld') AS adresse,
    adresse.entgebid,
    concat_ws(' '::text, anfallstelle.anhang_id, anfallstelle.anlagenart) AS anhang,
    standort.e32,
    standort.n32,
    standort.the_geom
   FROM basis.adresse,
    basis.inhaber,
    basis.objekt,
    basis.objektarten,
    elka.anfallstelle,
    basis.standort,
    elka.anhang
  WHERE ((adresse.id = inhaber.adresseid) AND (inhaber.id = standort.inhaberid) AND (objekt.id = anfallstelle.objektid) AND (objekt.objektartid = objektarten.id) AND ((anfallstelle.anhang_id)::text = (anhang.anhang_id)::text) AND (standort.id = objekt.standortid) AND ((anfallstelle.anhang_id)::text = '99'::text) AND ((anfallstelle.anlagenart)::text <> 'Brennwertkessel'::text) AND ((anfallstelle.anlagenart)::text <> ''::text) AND ((anfallstelle.anlagenart)::text <> 'Blockheizkraftwerk'::text) AND ((anfallstelle.anlagenart)::text <> 'Abscheider'::text) AND (objekt._deleted = false) AND (objekt.inaktiv = false) AND (objekt.abwasserfrei = false))
UNION
 SELECT DISTINCT concat_ws(', '::text, inhaber.name, inhaber.vorname) AS betreiber,
    concat_ws(' '::text, adresse.strasse, adresse.hausnr, adresse.hausnrzus, ',', adresse.plz, 'Bielefeld') AS adresse,
    adresse.entgebid,
    concat_ws(' '::text, anfallstelle.anhang_id, anfallstelle.anlagenart) AS anhang,
    standort.e32,
    standort.n32,
    standort.the_geom
   FROM basis.adresse,
    basis.inhaber,
    basis.objekt,
    basis.objektarten,
    elka.anfallstelle,
    basis.standort,
    elka.anhang,
    indeinl.bwk_fachdaten
  WHERE ((adresse.id = inhaber.adresseid) AND (inhaber.id = standort.inhaberid) AND (objekt.id = anfallstelle.objektid) AND (objekt.objektartid = objektarten.id) AND ((anfallstelle.anhang_id)::text = (anhang.anhang_id)::text) AND (standort.id = objekt.standortid) AND (anfallstelle.id = bwk_fachdaten.anfallstelleid) AND (bwk_fachdaten.genehmigungspflicht = true) AND ((anfallstelle.anhang_id)::text = '99'::text) AND (objekt._deleted = false) AND (objekt.inaktiv = false) AND (objekt.abwasserfrei = false));

ALTER TABLE gis.okd_indeinl OWNER TO auikadmin;

CREATE SEQUENCE labor.atl_sielhaut_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE labor.atl_sielhaut_id_seq OWNER TO auikadmin;

CREATE TABLE labor.sielhaut (
    bezeichnung character varying(255) NOT NULL,
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
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer DEFAULT nextval('labor.atl_sielhaut_id_seq'::regclass) NOT NULL,
    probepktid integer,
    the_geom public.geometry(Point,25832)
);

ALTER TABLE labor.sielhaut OWNER TO auikadmin;

CREATE VIEW labor.view_sielhaut AS
 SELECT sielhaut.bezeichnung,
    sielhaut.haltungsnr,
    sielhaut.lage,
    sielhaut.alarmplannr,
    sielhaut.entgeb,
    sielhaut.bemerkungen,
    sielhaut.gebiet,
    sielhaut.p_sielhaut,
    sielhaut.p_alarmplan,
    sielhaut.p_nachprobe,
    sielhaut.schlammprobe,
    sielhaut.p_firmenprobe,
    sielhaut._enabled,
    sielhaut._deleted,
    sielhaut.id,
    sielhaut.probepktid,
    standort.e32,
    standort.n32,
    standort.the_geom
   FROM labor.sielhaut,
    labor.messstelle,
    basis.objekt,
    basis.standort
  WHERE ((sielhaut.probepktid = messstelle.id) AND (messstelle.objektid = objekt.id) AND (objekt.standortid = standort.id));

ALTER TABLE labor.view_sielhaut OWNER TO auikadmin;

CREATE VIEW gis.okd_sielhaut AS
 SELECT view_sielhaut.bezeichnung,
    view_sielhaut.alarmplannr,
    view_sielhaut.haltungsnr,
    view_sielhaut.entgeb,
    view_sielhaut.gebiet,
    view_sielhaut.lage,
    view_sielhaut.bemerkungen,
    view_sielhaut.p_sielhaut,
    view_sielhaut.p_firmenprobe,
    view_sielhaut.p_nachprobe,
    view_sielhaut.the_geom
   FROM labor.view_sielhaut
  WHERE ((view_sielhaut.p_sielhaut = true) OR (view_sielhaut.p_nachprobe = true) OR (view_sielhaut.p_firmenprobe = true));

ALTER TABLE gis.okd_sielhaut OWNER TO auikadmin;

CREATE TABLE indeinl.anh_40_fachdaten (
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

ALTER TABLE indeinl.anh_40_fachdaten OWNER TO auikadmin;

CREATE SEQUENCE indeinl.anh_40_fachdaten_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE indeinl.anh_40_fachdaten_id_seq OWNER TO auikadmin;

ALTER SEQUENCE indeinl.anh_40_fachdaten_id_seq OWNED BY indeinl.anh_40_fachdaten.id;

CREATE TABLE indeinl.anh_49_abfuhr (
    id integer NOT NULL,
    abfuhrdatum date,
    naechsteabfuhr date,
    entsorger character varying,
    anh49id integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    menge double precision
);

ALTER TABLE indeinl.anh_49_abfuhr OWNER TO auikadmin;

CREATE SEQUENCE indeinl.anh_49_abfuhr_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE indeinl.anh_49_abfuhr_id_seq OWNER TO auikadmin;

ALTER SEQUENCE indeinl.anh_49_abfuhr_id_seq OWNED BY indeinl.anh_49_abfuhr.id;

CREATE TABLE indeinl.anh_49_abscheiderdetails (
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

ALTER TABLE indeinl.anh_49_abscheiderdetails OWNER TO auikadmin;

CREATE SEQUENCE indeinl.anh_49_abscheiderdetails_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE indeinl.anh_49_abscheiderdetails_id_seq OWNER TO auikadmin;

ALTER SEQUENCE indeinl.anh_49_abscheiderdetails_id_seq OWNED BY indeinl.anh_49_abscheiderdetails.id;

CREATE TABLE indeinl.anh_49_analysen (
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

ALTER TABLE indeinl.anh_49_analysen OWNER TO auikadmin;

CREATE SEQUENCE indeinl.anh_49_analysen_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE indeinl.anh_49_analysen_id_seq OWNER TO auikadmin;

ALTER SEQUENCE indeinl.anh_49_analysen_id_seq OWNED BY indeinl.anh_49_analysen.id;

CREATE TABLE indeinl.anh_49_fachdaten (
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

ALTER TABLE indeinl.anh_49_fachdaten OWNER TO auikadmin;

CREATE SEQUENCE indeinl.anh_49_fachdaten_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE indeinl.anh_49_fachdaten_id_seq OWNER TO auikadmin;

ALTER SEQUENCE indeinl.anh_49_fachdaten_id_seq OWNED BY indeinl.anh_49_fachdaten.id;

CREATE TABLE indeinl.anh_49_kontrollen (
    id integer NOT NULL,
    pruefdatum date,
    naechstepruefung date,
    pruefergebnis character varying,
    anh49id integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE indeinl.anh_49_kontrollen OWNER TO auikadmin;

CREATE SEQUENCE indeinl.anh_49_kontrollen_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE indeinl.anh_49_kontrollen_id_seq OWNER TO auikadmin;

ALTER SEQUENCE indeinl.anh_49_kontrollen_id_seq OWNED BY indeinl.anh_49_kontrollen.id;

CREATE TABLE indeinl.anh_50_fachdaten (
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

ALTER TABLE indeinl.anh_50_fachdaten OWNER TO auikadmin;

CREATE SEQUENCE indeinl.anh_50_fachdaten_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE indeinl.anh_50_fachdaten_id_seq OWNER TO auikadmin;

ALTER SEQUENCE indeinl.anh_50_fachdaten_id_seq OWNED BY indeinl.anh_50_fachdaten.id;

CREATE TABLE indeinl.anh_52_fachdaten (
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

ALTER TABLE indeinl.anh_52_fachdaten OWNER TO auikadmin;

CREATE SEQUENCE indeinl.anh_52_fachdaten_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE indeinl.anh_52_fachdaten_id_seq OWNER TO auikadmin;

ALTER SEQUENCE indeinl.anh_52_fachdaten_id_seq OWNED BY indeinl.anh_52_fachdaten.id;

CREATE TABLE indeinl.anh_53_fachdaten (
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

ALTER TABLE indeinl.anh_53_fachdaten OWNER TO auikadmin;

CREATE SEQUENCE indeinl.anh_53_fachdaten_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE indeinl.anh_53_fachdaten_id_seq OWNER TO auikadmin;

ALTER SEQUENCE indeinl.anh_53_fachdaten_id_seq OWNED BY indeinl.anh_53_fachdaten.id;

CREATE TABLE indeinl.anh_55_fachdaten (
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

ALTER TABLE indeinl.anh_55_fachdaten OWNER TO auikadmin;

CREATE SEQUENCE indeinl.anh_55_fachdaten_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE indeinl.anh_55_fachdaten_id_seq OWNER TO auikadmin;

ALTER SEQUENCE indeinl.anh_55_fachdaten_id_seq OWNED BY indeinl.anh_55_fachdaten.id;

CREATE TABLE indeinl.anh_56_fachdaten (
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

ALTER TABLE indeinl.anh_56_fachdaten OWNER TO auikadmin;

CREATE SEQUENCE indeinl.anh_56_fachdaten_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE indeinl.anh_56_fachdaten_id_seq OWNER TO auikadmin;

ALTER SEQUENCE indeinl.anh_56_fachdaten_id_seq OWNED BY indeinl.anh_56_fachdaten.id;

CREATE SEQUENCE indeinl.anh_bwk_fachdaten_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE indeinl.anh_bwk_fachdaten_id_seq OWNER TO auikadmin;

ALTER SEQUENCE indeinl.anh_bwk_fachdaten_id_seq OWNED BY indeinl.bwk_fachdaten.id;

CREATE TABLE indeinl.suev_fachdaten (
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

ALTER TABLE indeinl.suev_fachdaten OWNER TO auikadmin;

CREATE SEQUENCE indeinl.anh_suev_fachdaten_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE indeinl.anh_suev_fachdaten_id_seq OWNER TO auikadmin;

ALTER SEQUENCE indeinl.anh_suev_fachdaten_id_seq OWNED BY indeinl.suev_fachdaten.id;

CREATE TABLE indeinl.entsorger (
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

ALTER TABLE indeinl.entsorger OWNER TO auikadmin;

CREATE TABLE labor.einheiten (
    id integer NOT NULL,
    bezeichnung character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    dea_einheiten_masseinheiten_nr integer
);

ALTER TABLE labor.einheiten OWNER TO auikadmin;

CREATE TABLE labor.parameter (
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
    dea_stoffe_stoff_nr integer,
    analysemethode_id integer
);

ALTER TABLE labor.parameter OWNER TO auikadmin;

CREATE SEQUENCE labor.atl_analyseposition_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE labor.atl_analyseposition_id_seq OWNER TO auikadmin;

ALTER SEQUENCE labor.atl_analyseposition_id_seq OWNED BY labor.analyseposition.id;

CREATE SEQUENCE labor.atl_probenahmen_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE labor.atl_probenahmen_id_seq OWNER TO auikadmin;

ALTER SEQUENCE labor.atl_probenahmen_id_seq OWNED BY labor.probenahme.id;

CREATE SEQUENCE labor.atl_probepkt_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE labor.atl_probepkt_id_seq OWNER TO auikadmin;

ALTER SEQUENCE labor.atl_probepkt_id_seq OWNED BY labor.messstelle.id;

CREATE TABLE labor.gebuehren (
    id integer NOT NULL,
    bezeichnung character varying,
    wert integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE labor.gebuehren OWNER TO auikadmin;

CREATE SEQUENCE labor.gebuehren_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE labor.gebuehren_id_seq OWNER TO auikadmin;

ALTER SEQUENCE labor.gebuehren_id_seq OWNED BY labor.gebuehren.id;

CREATE TABLE labor.klaeranlage (
    id integer NOT NULL,
    anlage character varying(255) NOT NULL,
    dea_klaeranlage_nr integer DEFAULT 0 NOT NULL,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE labor.klaeranlage OWNER TO auikadmin;

CREATE TABLE labor.meta_parameter (
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

ALTER TABLE labor.meta_parameter OWNER TO auikadmin;

CREATE TABLE labor.parametergruppen (
    id integer NOT NULL,
    name character varying(255),
    preisfueranalyse double precision,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE labor.parametergruppen OWNER TO auikadmin;


CREATE TABLE labor.probeart (
    id integer NOT NULL,
    art character varying(255) NOT NULL,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE labor.probeart OWNER TO auikadmin;

CREATE TABLE labor.status (
    id integer NOT NULL,
    bezeichnung character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);

ALTER TABLE labor.status OWNER TO auikadmin;

CREATE VIEW labor.view_atl_analyseposition_all AS
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
   FROM ((labor.analyseposition atl_analyseposition
     LEFT JOIN labor.probenahme atl_probenahmen ON ((atl_analyseposition.probenahme_id = atl_probenahmen.id)))
     LEFT JOIN labor.messstelle atl_probepkt ON ((atl_probenahmen.messstid = atl_probepkt.id)))
  WHERE (atl_probenahmen._deleted = false)
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
   FROM (((labor.analyseposition bsb
     JOIN labor.analyseposition csb ON (((bsb.probenahme_id = csb.probenahme_id) AND ((bsb.parameter_id)::text = 'L16250'::text) AND ((csb.parameter_id)::text = 'L15330'::text) AND (bsb.wert IS NOT NULL) AND (csb.wert IS NOT NULL) AND (csb.wert <> (0)::double precision))))
     LEFT JOIN labor.probenahme atl_probenahmen ON ((csb.probenahme_id = atl_probenahmen.id)))
     LEFT JOIN labor.messstelle atl_probepkt ON ((atl_probenahmen.messstid = atl_probepkt.id)))
  WHERE (atl_probenahmen._deleted = false)
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
   FROM (((labor.analyseposition menge
     JOIN labor.analyseposition doc ON (((menge.probenahme_id = doc.probenahme_id) AND ((menge.parameter_id)::text = 'P00013'::text) AND ((doc.parameter_id)::text = 'L15210'::text) AND (menge.wert IS NOT NULL) AND (doc.wert IS NOT NULL))))
     LEFT JOIN labor.probenahme atl_probenahmen ON ((doc.probenahme_id = atl_probenahmen.id)))
     LEFT JOIN labor.messstelle atl_probepkt ON ((atl_probenahmen.messstid = atl_probepkt.id)))
  WHERE (atl_probenahmen._deleted = false)
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
   FROM (((labor.analyseposition menge
     JOIN labor.analyseposition toc ON (((menge.probenahme_id = toc.probenahme_id) AND ((menge.parameter_id)::text = 'P00013'::text) AND ((toc.parameter_id)::text = 'L15230'::text) AND (menge.wert IS NOT NULL) AND (toc.wert IS NOT NULL))))
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
           FROM labor.probenahme atl_probenahmen
          WHERE (atl_probenahmen._deleted = false)) p ON ((toc.probenahme_id = p.id)))
     JOIN labor.messstelle atl_probepkt ON ((p.objektid = atl_probepkt.id)))
  GROUP BY p.datum_der_entnahme;

ALTER TABLE labor.view_atl_analyseposition_all OWNER TO auikadmin;

CREATE VIEW labor.view_atl_analyseposition_alt AS
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
   FROM ((labor.analyseposition atl_analyseposition
     LEFT JOIN labor.probenahme atl_probenahmen ON ((atl_analyseposition.probenahme_id = atl_probenahmen.id)))
     LEFT JOIN labor.messstelle atl_probepkt ON ((atl_probenahmen.messstid = atl_probepkt.objektid)))
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
   FROM (((labor.analyseposition bsb
     JOIN labor.analyseposition csb ON (((bsb.probenahme_id = csb.probenahme_id) AND ((bsb.parameter_id)::text = 'L16250'::text) AND ((csb.parameter_id)::text = 'L15330'::text) AND (bsb.wert IS NOT NULL) AND (csb.wert IS NOT NULL) AND (csb.wert <> (0)::double precision))))
     LEFT JOIN labor.probenahme atl_probenahmen ON ((csb.probenahme_id = atl_probenahmen.id)))
     LEFT JOIN labor.messstelle atl_probepkt ON ((atl_probenahmen.messstid = atl_probepkt.objektid)))
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
   FROM (((labor.analyseposition menge
     JOIN labor.analyseposition doc ON (((menge.probenahme_id = doc.probenahme_id) AND ((menge.parameter_id)::text = 'P00013'::text) AND ((doc.parameter_id)::text = 'L15210'::text) AND (menge.wert IS NOT NULL) AND (doc.wert IS NOT NULL))))
     LEFT JOIN labor.probenahme atl_probenahmen ON ((doc.probenahme_id = atl_probenahmen.id)))
     LEFT JOIN labor.messstelle atl_probepkt ON ((atl_probenahmen.messstid = atl_probepkt.objektid)))
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
   FROM ((((labor.analyseposition menge
     JOIN labor.analyseposition toc ON (((menge.probenahme_id = toc.probenahme_id) AND ((menge.parameter_id)::text = 'P00013'::text) AND ((toc.parameter_id)::text = 'L15230'::text) AND (menge.wert IS NOT NULL) AND (toc.wert IS NOT NULL))))
     LEFT JOIN labor.probenahme atl_probenahmen ON ((toc.probenahme_id = atl_probenahmen.id)))
     LEFT JOIN labor.messstelle atl_probepkt ON ((atl_probenahmen.messstid = atl_probepkt.objektid)))
     JOIN ( SELECT atl_probepkt_1.objektid,
            probe.datum_der_entnahme,
            count(*) AS anzahl_proben
           FROM (((labor.messstelle atl_probepkt_1
             JOIN labor.probenahme probe ON (((probe.messstid = atl_probepkt_1.objektid) AND (atl_probepkt_1._deleted = false) AND (probe._deleted = false))))
             JOIN labor.analyseposition menge_1 ON (((menge_1.probenahme_id = probe.id) AND (menge_1._deleted = false) AND ((menge_1.parameter_id)::text = 'P00013'::text) AND (menge_1.wert IS NOT NULL))))
             JOIN labor.analyseposition toc_1 ON (((toc_1.probenahme_id = probe.id) AND (toc_1._deleted = false) AND ((toc_1.parameter_id)::text = 'L15230'::text) AND (toc_1.wert IS NOT NULL))))
          GROUP BY atl_probepkt_1.objektid, probe.datum_der_entnahme) anzahl_proben ON (((anzahl_proben.objektid = atl_probepkt.objektid) AND (anzahl_proben.datum_der_entnahme = atl_probenahmen.datum_der_entnahme) AND (anzahl_proben.anzahl_proben = 1))))
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
   FROM ((((((labor.analyseposition menge_1
     JOIN labor.analyseposition toc_1 ON (((menge_1.probenahme_id = toc_1.probenahme_id) AND ((menge_1.parameter_id)::text = 'P00013'::text) AND ((toc_1.parameter_id)::text = 'L15230'::text) AND (menge_1.wert IS NOT NULL) AND (toc_1.wert IS NOT NULL) AND (NOT menge_1._deleted) AND (NOT toc_1._deleted))))
     JOIN labor.probenahme probe_1 ON (((toc_1.probenahme_id = probe_1.id) AND (NOT probe_1._deleted))))
     JOIN labor.probenahme probe_2 ON (((probe_1.messstid = probe_2.messstid) AND (probe_1.datum_der_entnahme = probe_2.datum_der_entnahme) AND (probe_1.id <> probe_2.id) AND (NOT probe_2._deleted))))
     JOIN labor.analyseposition menge_2 ON (((menge_2.probenahme_id = probe_2.id) AND ((menge_2.parameter_id)::text = 'P00013'::text) AND (menge_2.wert IS NOT NULL) AND (NOT menge_2._deleted))))
     JOIN labor.analyseposition toc_2 ON (((menge_2.probenahme_id = toc_2.probenahme_id) AND ((toc_2.parameter_id)::text = 'L15230'::text) AND (toc_2.wert IS NOT NULL) AND (NOT toc_2._deleted))))
     JOIN labor.messstelle atl_probepkt ON ((probe_1.messstid = atl_probepkt.objektid)));

ALTER TABLE labor.view_atl_analyseposition_alt OWNER TO auikadmin;

CREATE VIEW labor.view_sielhaut_hg AS
 SELECT atl_analyseposition.id,
    atl_analyseposition.wert,
    atl_probenahmen.kennummer,
    atl_probenahmen.datum_der_entnahme,
    atl_sielhaut.p_sielhaut,
    atl_sielhaut.p_nachprobe,
    atl_sielhaut.p_firmenprobe,
    atl_sielhaut._deleted,
    atl_sielhaut.e32,
    atl_sielhaut.n32,
    atl_sielhaut.the_geom
   FROM labor.sielhaut atl_sielhaut,
    labor.probenahme atl_probenahmen,
    labor.analyseposition atl_analyseposition
  WHERE ((atl_sielhaut.id = atl_probenahmen.messstid) AND (atl_probenahmen.id = atl_analyseposition.probenahme_id) AND ((atl_analyseposition.parameter_id)::text = 'L11660'::text) AND (atl_sielhaut._deleted = false) AND (atl_sielhaut.e32 <> (0)::double precision))
  ORDER BY atl_probenahmen.datum_der_entnahme;

ALTER TABLE labor.view_sielhaut_hg OWNER TO auikadmin;

CREATE VIEW labor.view_sielhautqb_probenahmen_firmen AS
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '101BaxA'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '101EVKBGI1'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '101EVKBGI3'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '101WaeBe'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '102FRHO1'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '102FRHO3'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '102KHM'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '103nElb'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '103vElb'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '103WarG'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '104nbsb'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '104vbsb'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '412nBeh'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '412nSt'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '412vBehSt'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '413UNI'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '414EvKBJ'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '414Lue'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '414MIE'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '414nAl'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '414vAl'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '418Graf79H'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '419Gal3'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '419nTK'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '421IWNOH'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '422IPAde'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '423Duer'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '424MBG'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '424Schoen'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '425hDrew'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '425Hes'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '425Thee'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '426Kis'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '426Kis1'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '426Kis2'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '528BR-NM'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '528BR-VM'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '528BRGT-BW'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '528G-KTL'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '528G-Tor2'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '528GBra'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '529JVA'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '530KHRos'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '530ZFn'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '732PaHa'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '835Med'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '835UK'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '937Oct'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = 'z101vBax'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = 'z103WarS'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = 'z104nIWN'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = 'z104Stock2'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = 'z530nBau'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = 'z530nWot'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = 'z530vWot'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '103nWar'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '103vWar'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '425nHes'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND ((atl_sielhaut.bezeichnung)::text = '425vHes'::text) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
  ORDER BY 3, 5;

ALTER TABLE labor.view_sielhautqb_probenahmen_firmen OWNER TO auikadmin;

CREATE VIEW labor.view_sielhautqb_kreuztabelle_firmen AS
 SELECT view_sielhautqb_probenahmen_firmen.bezeichnung,
    view_sielhautqb_probenahmen_firmen.datum_der_entnahme,
    sum(
        CASE atl_analyseposition.parameter_id
            WHEN 'L11650'::text THEN atl_analyseposition.normwert
            ELSE (0)::double precision
        END) AS "Cadmium",
    sum(
        CASE atl_analyseposition.parameter_id
            WHEN 'L11510'::text THEN atl_analyseposition.normwert
            ELSE (0)::double precision
        END) AS "Chrom",
    sum(
        CASE atl_analyseposition.parameter_id
            WHEN 'L11610'::text THEN atl_analyseposition.normwert
            ELSE (0)::double precision
        END) AS "Kupfer",
    sum(
        CASE atl_analyseposition.parameter_id
            WHEN 'L11660'::text THEN atl_analyseposition.normwert
            ELSE (0)::double precision
        END) AS "Quecksilber",
    sum(
        CASE atl_analyseposition.parameter_id
            WHEN 'L11880'::text THEN atl_analyseposition.normwert
            ELSE (0)::double precision
        END) AS "Nickel",
    sum(
        CASE atl_analyseposition.parameter_id
            WHEN 'L11380'::text THEN atl_analyseposition.normwert
            ELSE (0)::double precision
        END) AS "Blei",
    sum(
        CASE atl_analyseposition.parameter_id
            WHEN 'L11640'::text THEN atl_analyseposition.normwert
            ELSE (0)::double precision
        END) AS "Zink"
   FROM labor.view_sielhautqb_probenahmen_firmen,
    labor.analyseposition atl_analyseposition
  WHERE ((view_sielhautqb_probenahmen_firmen.id = atl_analyseposition.probenahme_id) AND (atl_analyseposition._deleted = false))
  GROUP BY view_sielhautqb_probenahmen_firmen.bezeichnung, view_sielhautqb_probenahmen_firmen.datum_der_entnahme
  ORDER BY view_sielhautqb_probenahmen_firmen.bezeichnung, view_sielhautqb_probenahmen_firmen.datum_der_entnahme;

ALTER TABLE labor.view_sielhautqb_kreuztabelle_firmen OWNER TO auikadmin;

CREATE VIEW labor.view_sielhautqb_probenahmen_routine AS
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24856) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24857) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24858) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24859) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24860) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24863) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24864) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24865) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24867) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24868) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24869) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24871) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24872) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24873) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24874) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24875) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24876) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24877) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24880) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24881) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24882) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24884) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24885) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24887) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24889) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24890) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24891) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24892) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24893) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24894) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24895) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24896) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24897) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24898) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24899) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 24900) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 47997) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 26356) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 26357) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 26358) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 28352) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
UNION
( SELECT row_number() OVER (ORDER BY atl_probenahmen.datum_der_entnahme DESC) AS row_number,
    atl_probenahmen.id,
    atl_probenahmen.kennummer,
    atl_sielhaut.bezeichnung,
    atl_sielhaut.lage,
    atl_probenahmen.datum_der_entnahme,
    atl_probepkt.objektid
   FROM labor.probenahme atl_probenahmen,
    labor.messstelle atl_probepkt,
    labor.sielhaut atl_sielhaut
  WHERE ((atl_probepkt.id = atl_probenahmen.messstid) AND (atl_sielhaut.id = atl_probepkt.objektid) AND (atl_probepkt.objektid = 36760) AND (atl_probenahmen._deleted = false) AND (atl_probenahmen.qb_ausschliessen = false))
  ORDER BY atl_probenahmen.datum_der_entnahme DESC
 LIMIT 5)
  ORDER BY 7, 6;

ALTER TABLE labor.view_sielhautqb_probenahmen_routine OWNER TO auikadmin;

CREATE VIEW labor.view_sielhautqb_kreuztabelle_routine AS
 SELECT view_sielhautqb_probenahmen_routine.kennummer,
    view_sielhautqb_probenahmen_routine.id,
    view_sielhautqb_probenahmen_routine.lage,
    view_sielhautqb_probenahmen_routine.bezeichnung,
    view_sielhautqb_probenahmen_routine.datum_der_entnahme,
    sum(
        CASE atl_analyseposition.parameter_id
            WHEN 'L11650'::text THEN atl_analyseposition.normwert
            ELSE (0)::double precision
        END) AS "Cadmium",
    sum(
        CASE atl_analyseposition.parameter_id
            WHEN 'L11510'::text THEN atl_analyseposition.normwert
            ELSE (0)::double precision
        END) AS "Chrom",
    sum(
        CASE atl_analyseposition.parameter_id
            WHEN 'L11610'::text THEN atl_analyseposition.normwert
            ELSE (0)::double precision
        END) AS "Kupfer",
    sum(
        CASE atl_analyseposition.parameter_id
            WHEN 'L11660'::text THEN atl_analyseposition.normwert
            ELSE (0)::double precision
        END) AS "Quecksilber",
    sum(
        CASE atl_analyseposition.parameter_id
            WHEN 'L11880'::text THEN atl_analyseposition.normwert
            ELSE (0)::double precision
        END) AS "Nickel",
    sum(
        CASE atl_analyseposition.parameter_id
            WHEN 'L11380'::text THEN atl_analyseposition.normwert
            ELSE (0)::double precision
        END) AS "Blei",
    sum(
        CASE atl_analyseposition.parameter_id
            WHEN 'L11640'::text THEN atl_analyseposition.normwert
            ELSE (0)::double precision
        END) AS "Zink"
   FROM labor.view_sielhautqb_probenahmen_routine,
    labor.analyseposition atl_analyseposition
  WHERE ((view_sielhautqb_probenahmen_routine.id = atl_analyseposition.probenahme_id) AND (atl_analyseposition._deleted = false))
  GROUP BY view_sielhautqb_probenahmen_routine.bezeichnung, view_sielhautqb_probenahmen_routine.datum_der_entnahme, view_sielhautqb_probenahmen_routine.kennummer, view_sielhautqb_probenahmen_routine.id, view_sielhautqb_probenahmen_routine.lage
  ORDER BY view_sielhautqb_probenahmen_routine.bezeichnung, view_sielhautqb_probenahmen_routine.datum_der_entnahme;

ALTER TABLE labor.view_sielhautqb_kreuztabelle_routine OWNER TO auikadmin;


CREATE SEQUENCE oberflgw.afs_niederschlagswasser_nr_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE oberflgw.afs_niederschlagswasser_nr_seq OWNER TO auikadmin;

ALTER SEQUENCE oberflgw.afs_niederschlagswasser_nr_seq OWNED BY oberflgw.afs_niederschlagswasser.nr;

CREATE SEQUENCE oberflgw.afs_nw_nr_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE oberflgw.afs_nw_nr_seq OWNER TO auikadmin;

CREATE TABLE oberflgw.afs_stoffe (
    anfallstellen_nr bigint NOT NULL,
    stoff_nr bigint NOT NULL,
    produkt character varying(15)
);

ALTER TABLE oberflgw.afs_stoffe OWNER TO auikadmin;

CREATE SEQUENCE oberflgw.entwaesserungsgrundstueck_nr_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE oberflgw.entwaesserungsgrundstueck_nr_seq OWNER TO auikadmin;

ALTER SEQUENCE oberflgw.entwaesserungsgrundstueck_nr_seq OWNED BY oberflgw.entwaesserungsgrundstueck.nr;

CREATE SEQUENCE oberflgw.igl_p_einl_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE oberflgw.igl_p_einl_id_seq OWNER TO auikadmin;

CREATE TABLE oberflgw.massnahme (
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

ALTER TABLE oberflgw.massnahme OWNER TO auikadmin;

CREATE TABLE oberflgw.sb_entlastung (
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

ALTER TABLE oberflgw.sb_entlastung OWNER TO auikadmin;

CREATE SEQUENCE oberflgw.sonderbauwerk_nr_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE oberflgw.sonderbauwerk_nr_seq OWNER TO auikadmin;

ALTER SEQUENCE oberflgw.sonderbauwerk_nr_seq OWNED BY oberflgw.sonderbauwerk.nr;

CREATE VIEW oberflgw.view_einleitstellen AS
 SELECT inhaber.name,
    inhaber.vorname,
    adresse.strasse,
    adresse.hausnr,
    adresse.hausnrzus,
    adresse.plz,
    adresse.ort,
    wasserrecht.aktenzeichen,
    einleitungsstelle.gewaessername_alias_3,
    einleitungsstelle.gew_name_stadt,
    entwaesserungsgrundstueck.gr_entw_gebiet,
    afs_niederschlagswasser.bef_flaeche,
    afs_niederschlagswasser.abflussmenge,
    standort.the_geom
   FROM (((basis.adresse
     JOIN basis.inhaber ON ((adresse.id = inhaber.adresseid)))
     JOIN basis.standort ON ((inhaber.id = standort.inhaberid)))
     JOIN ((((((basis.objekt
     JOIN elka.wasserrecht ON ((objekt.id = wasserrecht.objektid)))
     JOIN basis.view_two_way_objektverknuepfung ON ((wasserrecht.objektid = view_two_way_objektverknuepfung.ist_verknuepft_mit)))
     JOIN elka.einleitungsstelle ON ((view_two_way_objektverknuepfung.objekt = einleitungsstelle.objektid)))
     JOIN basis.view_two_way_objektverknuepfung twoway ON ((wasserrecht.objektid = twoway.ist_verknuepft_mit)))
     JOIN oberflgw.entwaesserungsgrundstueck ON ((twoway.objekt = entwaesserungsgrundstueck.objekt_id)))
     JOIN oberflgw.afs_niederschlagswasser ON ((entwaesserungsgrundstueck.nr = afs_niederschlagswasser.entw_grund_nr))) ON ((standort.id = objekt.standortid)));

ALTER TABLE oberflgw.view_einleitstellen OWNER TO "BI005380";

CREATE TABLE oberflgw.z_betrieb_massnahme (
    id integer NOT NULL,
    betrieb_nr bigint NOT NULL,
    massnahme_nr bigint NOT NULL
);

ALTER TABLE oberflgw.z_betrieb_massnahme OWNER TO auikadmin;

CREATE SEQUENCE oberflgw.z_betrieb_massnahme_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE oberflgw.z_betrieb_massnahme_id_seq OWNER TO auikadmin;

ALTER SEQUENCE oberflgw.z_betrieb_massnahme_id_seq OWNED BY oberflgw.z_betrieb_massnahme.id;

CREATE TABLE oberflgw.z_entwaessgr_abwasbehverf (
    id integer NOT NULL,
    entw_grund_nr bigint NOT NULL,
    abwasbehverf_nr bigint NOT NULL
);

ALTER TABLE oberflgw.z_entwaessgr_abwasbehverf OWNER TO auikadmin;

CREATE SEQUENCE oberflgw.z_entwaessgr_abwasbehverf_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE oberflgw.z_entwaessgr_abwasbehverf_id_seq OWNER TO auikadmin;

ALTER SEQUENCE oberflgw.z_entwaessgr_abwasbehverf_id_seq OWNED BY oberflgw.z_entwaessgr_abwasbehverf.id;

CREATE TABLE oberflgw.z_rbf_schutzgueter (
    id integer NOT NULL,
    sb_nr bigint NOT NULL,
    schutzgueter_opt integer NOT NULL
);

ALTER TABLE oberflgw.z_rbf_schutzgueter OWNER TO auikadmin;

CREATE SEQUENCE oberflgw.z_rbf_schutzgueter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE oberflgw.z_rbf_schutzgueter_id_seq OWNER TO auikadmin;

ALTER SEQUENCE oberflgw.z_rbf_schutzgueter_id_seq OWNED BY oberflgw.z_rbf_schutzgueter.id;

CREATE TABLE oberflgw.z_sb_regeln (
    id integer NOT NULL,
    sb_nr bigint NOT NULL,
    regeln_tech_opt integer NOT NULL
);

ALTER TABLE oberflgw.z_sb_regeln OWNER TO auikadmin;

CREATE SEQUENCE oberflgw.z_sb_regeln_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE oberflgw.z_sb_regeln_id_seq OWNER TO auikadmin;

ALTER SEQUENCE oberflgw.z_sb_regeln_id_seq OWNED BY oberflgw.z_sb_regeln.id;

CREATE TABLE oberflgw.z_sb_verfahren (
    id integer NOT NULL,
    sb_nr bigint NOT NULL,
    vorgaben_opt integer NOT NULL
);

ALTER TABLE oberflgw.z_sb_verfahren OWNER TO auikadmin;

CREATE SEQUENCE oberflgw.z_sb_verfahren_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE oberflgw.z_sb_verfahren_id_seq OWNER TO auikadmin;

ALTER SEQUENCE oberflgw.z_sb_verfahren_id_seq OWNED BY oberflgw.z_sb_verfahren.id;

CREATE TABLE public.e_z_klaeranlage_massnahme (
    id integer NOT NULL,
    massnahme_nr bigint NOT NULL,
    klaeranlage_nr bigint NOT NULL
);

ALTER TABLE public.e_z_klaeranlage_massnahme OWNER TO auikadmin;

CREATE SEQUENCE public.e_z_klaeranlage_massnahme_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.e_z_klaeranlage_massnahme_id_seq OWNER TO auikadmin;

ALTER SEQUENCE public.e_z_klaeranlage_massnahme_id_seq OWNED BY public.e_z_klaeranlage_massnahme.id;

CREATE TABLE public.e_z_kleika_gebiet (
    id integer NOT NULL,
    kleika_nr bigint NOT NULL,
    gebietskennung_opt integer NOT NULL
);

ALTER TABLE public.e_z_kleika_gebiet OWNER TO auikadmin;

CREATE SEQUENCE public.e_z_kleika_gebiet_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.e_z_kleika_gebiet_id_seq OWNER TO auikadmin;

ALTER SEQUENCE public.e_z_kleika_gebiet_id_seq OWNED BY public.e_z_kleika_gebiet.id;

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.hibernate_sequence OWNER TO auikadmin;

CREATE TABLE public.street (
    bezeichnung text NOT NULL,
    schluessel character varying(5) NOT NULL
);

ALTER TABLE public.street OWNER TO "BI005380";

COMMENT ON TABLE public.street IS 'Die Daten wurden am 2025-01-22 um 06:21:41 Uhr aktualisiert.';

CREATE TABLE public.tab_streets_alkis (
    plz character varying(5),
    name character varying(40) NOT NULL,
    nr character varying(4) NOT NULL,
    x double precision,
    y double precision,
    gemeinde character varying(10) NOT NULL,
    schl character varying(5),
    hausnr integer,
    hausnr_zusatz character varying(1),
    the_geom public.geometry(Point,25832),
    abgleich character varying(9)
);

ALTER TABLE public.tab_streets_alkis OWNER TO auikadmin;




COMMENT ON TABLE public.tab_streets_alkis IS 'Daten wurden am 2025-02-11 um 06:20:59 Uhr geladen.';

ALTER TABLE ONLY awsv.abfuellflaeche ALTER COLUMN id SET DEFAULT nextval('awsv.vaws_abfuellflaeche_id_seq'::regclass);

ALTER TABLE ONLY awsv.abscheider ALTER COLUMN id SET DEFAULT nextval('awsv.vaws_abscheider_id_seq'::regclass);

ALTER TABLE ONLY awsv.anlagenchrono ALTER COLUMN id SET DEFAULT nextval('awsv.vaws_anlagenchrono_id_seq'::regclass);

ALTER TABLE ONLY awsv.jgs ALTER COLUMN id SET DEFAULT nextval('awsv.vaws_jgs_id_seq'::regclass);

ALTER TABLE ONLY awsv.kontrollen ALTER COLUMN id SET DEFAULT nextval('awsv.vaws_kontrollen_id_seq'::regclass);

ALTER TABLE ONLY awsv.verwaltungsgebuehren ALTER COLUMN id SET DEFAULT nextval('awsv.vaws_verwaltungsgebuehren_id_seq'::regclass);

ALTER TABLE ONLY awsv.verwaltungsverf ALTER COLUMN id SET DEFAULT nextval('awsv.vaws_verwaltungsverf_id_seq'::regclass);

ALTER TABLE ONLY basis.adresse ALTER COLUMN id SET DEFAULT nextval('basis.basis_betreiber_id_seq'::regclass);

ALTER TABLE ONLY basis.inhaber ALTER COLUMN id SET DEFAULT nextval('basis.inhaber_id_seq'::regclass);

ALTER TABLE ONLY basis.lage ALTER COLUMN id SET DEFAULT nextval('basis.basis_lage_id_seq'::regclass);

ALTER TABLE ONLY basis.objekt ALTER COLUMN id SET DEFAULT nextval('basis.basis_objekt_objektid_seq'::regclass);

ALTER TABLE ONLY basis.objektchrono ALTER COLUMN id SET DEFAULT nextval('basis.basis_objektchrono_id_seq'::regclass);

ALTER TABLE ONLY basis.objektverknuepfung ALTER COLUMN id SET DEFAULT nextval('basis.basis_objektverknuepfung_id_seq'::regclass);

ALTER TABLE ONLY basis.sachbearbeiter ALTER COLUMN id SET DEFAULT nextval('basis.basis_sachbearbeiter_id_seq'::regclass);

ALTER TABLE ONLY basis.standort ALTER COLUMN id SET DEFAULT nextval('basis.basis_standort_id_seq'::regclass);

ALTER TABLE ONLY elka.aba ALTER COLUMN id SET DEFAULT nextval('elka.elka_aba_id_seq'::regclass);

ALTER TABLE ONLY elka.anfallstelle ALTER COLUMN id SET DEFAULT nextval('elka.elka_anfallstelle_id_seq'::regclass);

ALTER TABLE ONLY elka.einleitungsstelle ALTER COLUMN id SET DEFAULT nextval('elka.elka_einleitungsstelle_id_seq'::regclass);

ALTER TABLE ONLY elka.map_elka_analysemethode ALTER COLUMN id SET DEFAULT nextval('elka.map_elka_analysemethode_id_seq'::regclass);

ALTER TABLE ONLY elka.referenz ALTER COLUMN nr SET DEFAULT nextval('elka.referenz_nr_seq'::regclass);

ALTER TABLE ONLY elka.wasserrecht ALTER COLUMN id SET DEFAULT nextval('elka.elka_wasserrecht_id_seq'::regclass);

ALTER TABLE ONLY elka.z_aba_verfahren ALTER COLUMN id SET DEFAULT nextval('elka.z_aba_verfahren_id_seq'::regclass);

ALTER TABLE ONLY indeinl.anh_40_fachdaten ALTER COLUMN id SET DEFAULT nextval('indeinl.anh_40_fachdaten_id_seq'::regclass);

ALTER TABLE ONLY indeinl.anh_49_abfuhr ALTER COLUMN id SET DEFAULT nextval('indeinl.anh_49_abfuhr_id_seq'::regclass);

ALTER TABLE ONLY indeinl.anh_49_abscheiderdetails ALTER COLUMN id SET DEFAULT nextval('indeinl.anh_49_abscheiderdetails_id_seq'::regclass);

ALTER TABLE ONLY indeinl.anh_49_analysen ALTER COLUMN id SET DEFAULT nextval('indeinl.anh_49_analysen_id_seq'::regclass);

ALTER TABLE ONLY indeinl.anh_49_fachdaten ALTER COLUMN id SET DEFAULT nextval('indeinl.anh_49_fachdaten_id_seq'::regclass);

ALTER TABLE ONLY indeinl.anh_49_kontrollen ALTER COLUMN id SET DEFAULT nextval('indeinl.anh_49_kontrollen_id_seq'::regclass);

ALTER TABLE ONLY indeinl.anh_50_fachdaten ALTER COLUMN id SET DEFAULT nextval('indeinl.anh_50_fachdaten_id_seq'::regclass);

ALTER TABLE ONLY indeinl.anh_52_fachdaten ALTER COLUMN id SET DEFAULT nextval('indeinl.anh_52_fachdaten_id_seq'::regclass);

ALTER TABLE ONLY indeinl.anh_53_fachdaten ALTER COLUMN id SET DEFAULT nextval('indeinl.anh_53_fachdaten_id_seq'::regclass);

ALTER TABLE ONLY indeinl.anh_55_fachdaten ALTER COLUMN id SET DEFAULT nextval('indeinl.anh_55_fachdaten_id_seq'::regclass);

ALTER TABLE ONLY indeinl.anh_56_fachdaten ALTER COLUMN id SET DEFAULT nextval('indeinl.anh_56_fachdaten_id_seq'::regclass);

ALTER TABLE ONLY indeinl.bwk_fachdaten ALTER COLUMN id SET DEFAULT nextval('indeinl.anh_bwk_fachdaten_id_seq'::regclass);

ALTER TABLE ONLY indeinl.suev_fachdaten ALTER COLUMN id SET DEFAULT nextval('indeinl.anh_suev_fachdaten_id_seq'::regclass);

ALTER TABLE ONLY labor.analyseposition ALTER COLUMN id SET DEFAULT nextval('labor.atl_analyseposition_id_seq'::regclass);

ALTER TABLE ONLY labor.gebuehren ALTER COLUMN id SET DEFAULT nextval('labor.gebuehren_id_seq'::regclass);

ALTER TABLE ONLY labor.messstelle ALTER COLUMN id SET DEFAULT nextval('labor.atl_probepkt_id_seq'::regclass);

ALTER TABLE ONLY labor.probenahme ALTER COLUMN id SET DEFAULT nextval('labor.atl_probenahmen_id_seq'::regclass);

ALTER TABLE ONLY oberflgw.afs_niederschlagswasser ALTER COLUMN nr SET DEFAULT nextval('oberflgw.afs_niederschlagswasser_nr_seq'::regclass);

ALTER TABLE ONLY oberflgw.entwaesserungsgrundstueck ALTER COLUMN nr SET DEFAULT nextval('oberflgw.entwaesserungsgrundstueck_nr_seq'::regclass);

ALTER TABLE ONLY oberflgw.sonderbauwerk ALTER COLUMN nr SET DEFAULT nextval('oberflgw.sonderbauwerk_nr_seq'::regclass);

ALTER TABLE ONLY oberflgw.z_betrieb_massnahme ALTER COLUMN id SET DEFAULT nextval('oberflgw.z_betrieb_massnahme_id_seq'::regclass);

ALTER TABLE ONLY oberflgw.z_entwaessgr_abwasbehverf ALTER COLUMN id SET DEFAULT nextval('oberflgw.z_entwaessgr_abwasbehverf_id_seq'::regclass);

ALTER TABLE ONLY oberflgw.z_rbf_schutzgueter ALTER COLUMN id SET DEFAULT nextval('oberflgw.z_rbf_schutzgueter_id_seq'::regclass);

ALTER TABLE ONLY oberflgw.z_sb_regeln ALTER COLUMN id SET DEFAULT nextval('oberflgw.z_sb_regeln_id_seq'::regclass);

ALTER TABLE ONLY oberflgw.z_sb_verfahren ALTER COLUMN id SET DEFAULT nextval('oberflgw.z_sb_verfahren_id_seq'::regclass);

ALTER TABLE ONLY public.e_z_klaeranlage_massnahme ALTER COLUMN id SET DEFAULT nextval('public.e_z_klaeranlage_massnahme_id_seq'::regclass);

ALTER TABLE ONLY public.e_z_kleika_gebiet ALTER COLUMN id SET DEFAULT nextval('public.e_z_kleika_gebiet_id_seq'::regclass);


SELECT pg_catalog.setval('awsv.awsv_fachdaten_behaelterid_seq', 1, true);

SELECT pg_catalog.setval('awsv.vaws_abfuellflaeche_id_seq', 1, true);

SELECT pg_catalog.setval('awsv.vaws_abscheider_id_seq', 1, true);

SELECT pg_catalog.setval('awsv.vaws_anlagenchrono_id_seq', 1, true);

SELECT pg_catalog.setval('awsv.vaws_jgs_id_seq', 1, true);

SELECT pg_catalog.setval('awsv.vaws_kontrollen_id_seq', 1, true);

SELECT pg_catalog.setval('awsv.vaws_verwaltungsgebuehren_id_seq', 1, true);

SELECT pg_catalog.setval('awsv.vaws_verwaltungsverf_id_seq', 21199, true);

SELECT pg_catalog.setval('basis.basis_betreiber_id_seq', 1, true);

SELECT pg_catalog.setval('basis.basis_lage_id_seq', 1, true);

SELECT pg_catalog.setval('basis.basis_objekt_objektid_seq', 1, true);

SELECT pg_catalog.setval('basis.basis_objektchrono_id_seq', 1, true);

SELECT pg_catalog.setval('basis.basis_objektverknuepfung_id_seq', 1, true);

SELECT pg_catalog.setval('basis.basis_sachbearbeiter_id_seq', 1, true);

SELECT pg_catalog.setval('basis.basis_standort_id_seq', 1, true);

SELECT pg_catalog.setval('basis.inhaber_id_seq', 1, true);

SELECT pg_catalog.setval('elka.e_adresse_seq', 1, true);

SELECT pg_catalog.setval('elka.elka_aba_id_seq', 1, true);

SELECT pg_catalog.setval('elka.elka_anfallstelle_id_seq', 1, true);

SELECT pg_catalog.setval('elka.elka_einleitungsstelle_id_seq', 1, true);

SELECT pg_catalog.setval('elka.elka_wasserrecht_id_seq', 1, true);

SELECT pg_catalog.setval('elka.map_elka_analysemethode_id_seq', 1, true);

SELECT pg_catalog.setval('elka.referenz_nr_seq', 1, true);

SELECT pg_catalog.setval('elka.z_aba_verfahren_id_seq', 1, true);

SELECT pg_catalog.setval('indeinl.anh_40_fachdaten_id_seq', 1, true);

SELECT pg_catalog.setval('indeinl.anh_49_abfuhr_id_seq', 1, true);

SELECT pg_catalog.setval('indeinl.anh_49_abscheiderdetails_id_seq', 1, true);

SELECT pg_catalog.setval('indeinl.anh_49_analysen_id_seq', 1, true);

SELECT pg_catalog.setval('indeinl.anh_49_fachdaten_id_seq', 1, true);

SELECT pg_catalog.setval('indeinl.anh_49_kontrollen_id_seq', 1, true);

SELECT pg_catalog.setval('indeinl.anh_50_fachdaten_id_seq', 1, true);

SELECT pg_catalog.setval('indeinl.anh_52_fachdaten_id_seq', 1, true);

SELECT pg_catalog.setval('indeinl.anh_53_fachdaten_id_seq', 1, true);

SELECT pg_catalog.setval('indeinl.anh_55_fachdaten_id_seq', 1, true);

SELECT pg_catalog.setval('indeinl.anh_56_fachdaten_id_seq', 1, true);

SELECT pg_catalog.setval('indeinl.anh_bwk_fachdaten_id_seq', 1, true);

SELECT pg_catalog.setval('indeinl.anh_suev_fachdaten_id_seq', 1, true);

SELECT pg_catalog.setval('labor.atl_analyseposition_id_seq', 1, true);

SELECT pg_catalog.setval('labor.atl_probenahmen_id_seq', 1, true);

SELECT pg_catalog.setval('labor.atl_probepkt_id_seq', 1, true);

SELECT pg_catalog.setval('labor.atl_sielhaut_id_seq', 1, true);

SELECT pg_catalog.setval('labor.gebuehren_id_seq', 1, true);

SELECT pg_catalog.setval('oberflgw.afs_niederschlagswasser_nr_seq', 1, true);

SELECT pg_catalog.setval('oberflgw.afs_nw_nr_seq', 1, false);

SELECT pg_catalog.setval('oberflgw.entwaesserungsgrundstueck_nr_seq', 1, true);

SELECT pg_catalog.setval('oberflgw.igl_p_einl_id_seq', 1, true);

SELECT pg_catalog.setval('oberflgw.sonderbauwerk_nr_seq', 1, false);

SELECT pg_catalog.setval('oberflgw.z_betrieb_massnahme_id_seq', 1, false);

SELECT pg_catalog.setval('oberflgw.z_entwaessgr_abwasbehverf_id_seq', 1, false);

SELECT pg_catalog.setval('oberflgw.z_rbf_schutzgueter_id_seq', 1, false);

SELECT pg_catalog.setval('oberflgw.z_sb_regeln_id_seq', 1, false);

SELECT pg_catalog.setval('oberflgw.z_sb_verfahren_id_seq', 1, false);

SELECT pg_catalog.setval('public.e_z_klaeranlage_massnahme_id_seq', 1, false);

SELECT pg_catalog.setval('public.e_z_kleika_gebiet_id_seq', 1, false);

SELECT pg_catalog.setval('public.hibernate_sequence', 1, true);



ALTER TABLE ONLY awsv.abfuellflaeche
    ADD CONSTRAINT vaws_abfuellflaeche_pkey PRIMARY KEY (behaelterid);

ALTER TABLE ONLY awsv.abscheider
    ADD CONSTRAINT vaws_abscheider_pkey PRIMARY KEY (id);

ALTER TABLE ONLY awsv.anlagenarten
    ADD CONSTRAINT vaws_anlagenarten_pkey PRIMARY KEY (id);

ALTER TABLE ONLY awsv.anlagenchrono
    ADD CONSTRAINT vaws_anlagenchrono_pkey PRIMARY KEY (id);

ALTER TABLE ONLY awsv.behaelterart
    ADD CONSTRAINT vaws_behaelterart_pkey PRIMARY KEY (id);

ALTER TABLE ONLY awsv.fachdaten
    ADD CONSTRAINT vaws_fachdaten_pkey PRIMARY KEY (behaelterid);

ALTER TABLE ONLY awsv.fluessigkeit
    ADD CONSTRAINT vaws_fluessigkeit_pkey PRIMARY KEY (id);

ALTER TABLE ONLY awsv.gebuehrenarten
    ADD CONSTRAINT vaws_gebuehrenarten_pkey PRIMARY KEY (id);

ALTER TABLE ONLY awsv.gefaehrdungsstufen
    ADD CONSTRAINT vaws_gefaehrdungsstufen_pkey PRIMARY KEY (id);

ALTER TABLE ONLY awsv.jgs
    ADD CONSTRAINT vaws_jgs_pkey PRIMARY KEY (id);

ALTER TABLE ONLY awsv.kontrollen
    ADD CONSTRAINT vaws_kontrollen_pkey PRIMARY KEY (id);

ALTER TABLE ONLY awsv.material
    ADD CONSTRAINT vaws_material_pkey PRIMARY KEY (id);

ALTER TABLE ONLY awsv.pruefer
    ADD CONSTRAINT vaws_pruefer_pkey PRIMARY KEY (id);

ALTER TABLE ONLY awsv.pruefergebniss
    ADD CONSTRAINT vaws_pruefergebnisse_pkey PRIMARY KEY (id);

ALTER TABLE ONLY awsv.standortgghwsg
    ADD CONSTRAINT vaws_standortgghwsg_pkey PRIMARY KEY (id);

ALTER TABLE ONLY awsv.vbfeinstufung
    ADD CONSTRAINT vaws_vbfeinstufung_pkey PRIMARY KEY (id);

ALTER TABLE ONLY awsv.verwaltungsgebuehren
    ADD CONSTRAINT vaws_verwaltungsgebuehren_pkey PRIMARY KEY (id);

ALTER TABLE ONLY awsv.verwaltungsverf
    ADD CONSTRAINT vaws_verwaltungsverf_pkey PRIMARY KEY (id);

ALTER TABLE ONLY awsv.verwmassnahmen
    ADD CONSTRAINT vaws_verwmassnahmen_pkey PRIMARY KEY (id);

ALTER TABLE ONLY awsv.wassereinzugsgebiet
    ADD CONSTRAINT vaws_wassereinzugsgebiete_pkey PRIMARY KEY (id);

ALTER TABLE ONLY basis.adresse
    ADD CONSTRAINT basis_betreiber_pkey PRIMARY KEY (id);

ALTER TABLE ONLY basis.bezeichnung
    ADD CONSTRAINT basis_bezeichnung_pkey PRIMARY KEY (id);

ALTER TABLE ONLY basis.gemarkung
    ADD CONSTRAINT basis_gemarkung_pkey PRIMARY KEY (id);

ALTER TABLE ONLY basis.inhaber
    ADD CONSTRAINT basis_inhaber_pkey PRIMARY KEY (id);

ALTER TABLE ONLY basis.lage
    ADD CONSTRAINT basis_lage_pkey PRIMARY KEY (id);

ALTER TABLE ONLY basis.objekt
    ADD CONSTRAINT basis_objekt_pkey PRIMARY KEY (id);

ALTER TABLE ONLY basis.objektarten
    ADD CONSTRAINT basis_objektarten_pkey PRIMARY KEY (id);

ALTER TABLE ONLY basis.objektchrono
    ADD CONSTRAINT basis_objektchrono_pkey PRIMARY KEY (id);

ALTER TABLE ONLY basis.objektverknuepfung
    ADD CONSTRAINT basis_objektverknuepfung_pkey PRIMARY KEY (id);

ALTER TABLE ONLY basis.prioritaet
    ADD CONSTRAINT basis_prioritaet_pkey PRIMARY KEY (standort_id, betreiber_id);

ALTER TABLE ONLY basis.sachbearbeiter
    ADD CONSTRAINT basis_sachbearbeiter_pkey PRIMARY KEY (id);

ALTER TABLE ONLY basis.standort
    ADD CONSTRAINT basis_standort_pkey PRIMARY KEY (id);

ALTER TABLE ONLY basis.strassen
    ADD CONSTRAINT basis_strassen_pkey PRIMARY KEY (id);

ALTER TABLE ONLY basis.sachbearbeiter
    ADD CONSTRAINT kennummer_unique_constraint UNIQUE (kennummer);

ALTER TABLE ONLY basis.wirtschaftszweig
    ADD CONSTRAINT vaws_wirtschaftszweige_pkey PRIMARY KEY (id);

ALTER TABLE ONLY elka.aba
    ADD CONSTRAINT aba_pkey PRIMARY KEY (id);

ALTER TABLE ONLY elka.aba
    ADD CONSTRAINT aba_unique_id UNIQUE (id);

ALTER TABLE ONLY elka.aba
    ADD CONSTRAINT aba_unique_objektid UNIQUE (objektid);

ALTER TABLE ONLY elka.map_elka_analysemethode
    ADD CONSTRAINT analysemethode_pk PRIMARY KEY (id);

ALTER TABLE ONLY elka.anfallstelle
    ADD CONSTRAINT basis_anfallstelle_pkey PRIMARY KEY (id);

ALTER TABLE ONLY elka.abaverfahren
    ADD CONSTRAINT elka_aba_verfahren_pkey PRIMARY KEY (nr);

ALTER TABLE ONLY elka.anhang
    ADD CONSTRAINT elka_anhang_pkey PRIMARY KEY (sl_nr);

ALTER TABLE ONLY elka.einleitungsstelle
    ADD CONSTRAINT elka_einleitungsstelle_pkey PRIMARY KEY (id);

ALTER TABLE ONLY elka.referenz
    ADD CONSTRAINT elka_referenz_pkey PRIMARY KEY (nr);

ALTER TABLE ONLY elka.wasserrecht
    ADD CONSTRAINT indeinl_genehmigung_pkey PRIMARY KEY (id);

ALTER TABLE ONLY elka.map_elka_anhang
    ADD CONSTRAINT pkey_map_anhang PRIMARY KEY (id);

ALTER TABLE ONLY elka.map_elka_einheit
    ADD CONSTRAINT pkey_map_einheit PRIMARY KEY (id);

ALTER TABLE ONLY elka.map_elka_stoff
    ADD CONSTRAINT pkey_map_stoff PRIMARY KEY (id);

ALTER TABLE ONLY elka.wasserrecht
    ADD CONSTRAINT wasserrecht_unique_id UNIQUE (objektid);

ALTER TABLE ONLY elka.z_aba_verfahren
    ADD CONSTRAINT z_aba_verfahren_pkey PRIMARY KEY (id);

ALTER TABLE ONLY indeinl.anh_40_fachdaten
    ADD CONSTRAINT anh_40_fachdaten_pkey PRIMARY KEY (id);

ALTER TABLE ONLY indeinl.anh_49_abfuhr
    ADD CONSTRAINT anh_49_abfuhr_pkey PRIMARY KEY (id);

ALTER TABLE ONLY indeinl.anh_49_abscheiderdetails
    ADD CONSTRAINT anh_49_abscheiderdetails_pkey PRIMARY KEY (id);

ALTER TABLE ONLY indeinl.anh_49_analysen
    ADD CONSTRAINT anh_49_analysen_pkey PRIMARY KEY (id);

ALTER TABLE ONLY indeinl.anh_49_fachdaten
    ADD CONSTRAINT anh_49_fachdate_objektid_unique UNIQUE (id);

ALTER TABLE ONLY indeinl.anh_49_fachdaten
    ADD CONSTRAINT anh_49_fachdaten_pkey PRIMARY KEY (id);

ALTER TABLE ONLY indeinl.anh_49_kontrollen
    ADD CONSTRAINT anh_49_kontrollen_pkey PRIMARY KEY (id);

ALTER TABLE ONLY indeinl.anh_50_fachdaten
    ADD CONSTRAINT anh_50_fachdaten_pkey PRIMARY KEY (id);

ALTER TABLE ONLY indeinl.anh_52_fachdaten
    ADD CONSTRAINT anh_52_fachdaten_pkey PRIMARY KEY (id);

ALTER TABLE ONLY indeinl.anh_53_fachdaten
    ADD CONSTRAINT anh_53_fachdaten_pkey PRIMARY KEY (id);

ALTER TABLE ONLY indeinl.anh_55_fachdaten
    ADD CONSTRAINT anh_55_fachdaten_pkey PRIMARY KEY (id);

ALTER TABLE ONLY indeinl.anh_56_fachdaten
    ADD CONSTRAINT anh_56_fachdaten_pkey PRIMARY KEY (id);

ALTER TABLE ONLY indeinl.bwk_fachdaten
    ADD CONSTRAINT anh_bwk_fachdaten_pkey PRIMARY KEY (id);

ALTER TABLE ONLY indeinl.entsorger
    ADD CONSTRAINT anh_entsorger_pkey PRIMARY KEY (id);

ALTER TABLE ONLY indeinl.suev_fachdaten
    ADD CONSTRAINT anh_suev_fachdaten_pkey PRIMARY KEY (id);

ALTER TABLE ONLY labor.analyseposition
    ADD CONSTRAINT atl_analyseposition_pkey PRIMARY KEY (id);

ALTER TABLE ONLY labor.einheiten
    ADD CONSTRAINT atl_einheiten_pkey PRIMARY KEY (id);

ALTER TABLE ONLY labor.klaeranlage
    ADD CONSTRAINT atl_klaeranlagen_pkey PRIMARY KEY (id);

ALTER TABLE ONLY labor.meta_parameter
    ADD CONSTRAINT atl_meta_parameter_pkey PRIMARY KEY (id);

ALTER TABLE ONLY labor.parameter
    ADD CONSTRAINT atl_parameter_pkey PRIMARY KEY (ordnungsbegriff);

ALTER TABLE ONLY labor.parametergruppen
    ADD CONSTRAINT atl_parametergruppen_pkey PRIMARY KEY (id);

ALTER TABLE ONLY labor.probeart
    ADD CONSTRAINT atl_probeart_pkey PRIMARY KEY (id);

ALTER TABLE ONLY labor.probenahme
    ADD CONSTRAINT atl_probenahmen_pkey PRIMARY KEY (id);

ALTER TABLE ONLY labor.messstelle
    ADD CONSTRAINT atl_probepkt_objektid_unique UNIQUE (objektid);

ALTER TABLE ONLY labor.messstelle
    ADD CONSTRAINT atl_probepkt_pkey PRIMARY KEY (id);

ALTER TABLE ONLY labor.sielhaut
    ADD CONSTRAINT atl_sielhaut_pkey PRIMARY KEY (id);

ALTER TABLE ONLY labor.status
    ADD CONSTRAINT atl_status_pkey PRIMARY KEY (id);

ALTER TABLE ONLY labor.gebuehren
    ADD CONSTRAINT gebuehren_pkey PRIMARY KEY (id);

ALTER TABLE ONLY oberflgw.afs_niederschlagswasser
    ADD CONSTRAINT afs_niederschlagswasser_pkey PRIMARY KEY (nr);

ALTER TABLE ONLY oberflgw.afs_stoffe
    ADD CONSTRAINT afs_stoffe_pkey PRIMARY KEY (anfallstellen_nr, stoff_nr);

ALTER TABLE ONLY oberflgw.entwaesserungsgrundstueck
    ADD CONSTRAINT entwaesserungsgrundstueck_pkey PRIMARY KEY (nr);

ALTER TABLE ONLY oberflgw.massnahme
    ADD CONSTRAINT massnahme_pkey PRIMARY KEY (nr);

ALTER TABLE ONLY oberflgw.sb_entlastung
    ADD CONSTRAINT sb_entlastung_pkey PRIMARY KEY (nr);

ALTER TABLE ONLY oberflgw.sonderbauwerk
    ADD CONSTRAINT sonderbauwerk_pkey PRIMARY KEY (nr);

ALTER TABLE ONLY public.tab_streets_alkis
    ADD CONSTRAINT con_streets_alkis PRIMARY KEY (gemeinde, name, nr);

ALTER TABLE ONLY public.street
    ADD CONSTRAINT street_pkey PRIMARY KEY (schluessel);
    
    

CREATE INDEX fki_standort_inhaber_id_fkey ON basis.standort USING btree (inhaberid);

CREATE INDEX idx_adresse ON basis.adresse USING btree (id) INCLUDE (id) WITH (deduplicate_items='true');

CREATE INDEX idx_inhaber ON basis.inhaber USING btree (id) INCLUDE (id, adresseid) WITH (deduplicate_items='true');

CREATE INDEX idx_standort ON basis.standort USING btree (id) INCLUDE (id, inhaberid) WITH (deduplicate_items='true');

CREATE INDEX sidx_basis_standort_the_geom ON basis.lage USING gist (the_geom);

CREATE INDEX fki_anfallstelle_objekt_id_fkey ON elka.anfallstelle USING btree (objektid);

CREATE INDEX fki_einleitstelle_objekt_id_fkey ON elka.einleitungsstelle USING btree (objektid);

CREATE INDEX fki_49fachdaten_kontrollen ON indeinl.anh_49_kontrollen USING btree (anh49id);

CREATE INDEX fki_position_methode_fkey ON labor.analyseposition USING btree (methode_id);

CREATE INDEX name_alkis_ix ON public.tab_streets_alkis USING btree (name);

CREATE INDEX strassen_alkis_ix ON public.tab_streets_alkis USING gist (the_geom);

ALTER TABLE public.tab_streets_alkis CLUSTER ON strassen_alkis_ix;



ALTER TABLE ONLY awsv.fachdaten
    ADD CONSTRAINT fk3537b8a88b7385c8 FOREIGN KEY (objektid) REFERENCES basis.objekt(id);

ALTER TABLE ONLY awsv.anlagenchrono
    ADD CONSTRAINT fk50bd7a59ec7c6efa FOREIGN KEY (behaelterid) REFERENCES awsv.fachdaten(behaelterid);

ALTER TABLE ONLY awsv.verwaltungsgebuehren
    ADD CONSTRAINT fk52f46b053e50a20 FOREIGN KEY (gebuehrenart) REFERENCES awsv.gebuehrenarten(id);

ALTER TABLE ONLY awsv.verwaltungsgebuehren
    ADD CONSTRAINT fk52f46b05ec7c6efa FOREIGN KEY (behaelterid) REFERENCES awsv.fachdaten(behaelterid);

ALTER TABLE ONLY awsv.kontrollen
    ADD CONSTRAINT fk5d09f108ec7c6efa FOREIGN KEY (behaelterid) REFERENCES awsv.fachdaten(behaelterid);

ALTER TABLE ONLY awsv.verwaltungsverf
    ADD CONSTRAINT fke8c3ed65ec7c6efa FOREIGN KEY (behaelterid) REFERENCES awsv.fachdaten(behaelterid);

ALTER TABLE ONLY awsv.abfuellflaeche
    ADD CONSTRAINT vaws_abfuellflaeche_behaelterid_fkey FOREIGN KEY (behaelterid) REFERENCES awsv.fachdaten(behaelterid);

ALTER TABLE ONLY awsv.abscheider
    ADD CONSTRAINT vaws_abscheider_behaelterid_fkey FOREIGN KEY (behaelterid) REFERENCES awsv.fachdaten(behaelterid);

ALTER TABLE ONLY awsv.jgs
    ADD CONSTRAINT vaws_jgs_fachdaten_fkey FOREIGN KEY (behaelterid) REFERENCES awsv.fachdaten(behaelterid);

ALTER TABLE ONLY basis.objekt
    ADD CONSTRAINT basis_objekt_inhaber_fkey FOREIGN KEY (betreiberid) REFERENCES basis.inhaber(id);

ALTER TABLE ONLY basis.objekt
    ADD CONSTRAINT basis_objekt_sachbearbeiter_fkey FOREIGN KEY (sachbearbeiter) REFERENCES basis.sachbearbeiter(id);

ALTER TABLE ONLY basis.objekt
    ADD CONSTRAINT basis_objekt_standort_fkey FOREIGN KEY (standortid) REFERENCES basis.standort(id);

ALTER TABLE ONLY basis.objektchrono
    ADD CONSTRAINT fk6471bfb8b7385c8 FOREIGN KEY (objektid) REFERENCES basis.objekt(id);

ALTER TABLE ONLY basis.lage
    ADD CONSTRAINT fk6dc7963cce44dba3 FOREIGN KEY (standortgegebid) REFERENCES awsv.standortgghwsg(id);

ALTER TABLE ONLY basis.lage
    ADD CONSTRAINT fk6dc7963ce50b8cce FOREIGN KEY (wassereinzgebid) REFERENCES awsv.wassereinzugsgebiet(id);

ALTER TABLE ONLY basis.lage
    ADD CONSTRAINT fk6dc7963cfcdb82c2 FOREIGN KEY (gemarkungid) REFERENCES basis.gemarkung(id);

ALTER TABLE ONLY basis.objektverknuepfung
    ADD CONSTRAINT fk8d8baafa490baa6d FOREIGN KEY (objekt) REFERENCES basis.objekt(id);

ALTER TABLE ONLY basis.objektverknuepfung
    ADD CONSTRAINT fk8d8baafa5f57144c FOREIGN KEY (ist_verknuepft_mit) REFERENCES basis.objekt(id);

ALTER TABLE ONLY basis.objekt
    ADD CONSTRAINT fkf8502b5882c7c6bd FOREIGN KEY (objektartid) REFERENCES basis.objektarten(id);

ALTER TABLE ONLY basis.inhaber
    ADD CONSTRAINT inhaber_adresse_fkey FOREIGN KEY (adresseid) REFERENCES basis.adresse(id);

ALTER TABLE ONLY basis.inhaber
    ADD CONSTRAINT inhaber_wirtschaftsz_fkey FOREIGN KEY (wirtschaftszweigid) REFERENCES basis.wirtschaftszweig(id);

ALTER TABLE ONLY elka.anfallstelle
    ADD CONSTRAINT anfallstelle_objekt_id_fkey FOREIGN KEY (objektid) REFERENCES basis.objekt(id) NOT VALID;

ALTER TABLE ONLY elka.einleitungsstelle
    ADD CONSTRAINT einleitstelle_objekt_id_fkey FOREIGN KEY (objektid) REFERENCES basis.objekt(id) NOT VALID;

ALTER TABLE ONLY elka.wasserrecht
    ADD CONSTRAINT fkecb352f88b7385c8 FOREIGN KEY (objektid) REFERENCES basis.objekt(id);

ALTER TABLE ONLY elka.referenz
    ADD CONSTRAINT referenz_q_aba_nr_fkey FOREIGN KEY (q_aba_nr) REFERENCES elka.aba(id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY elka.referenz
    ADD CONSTRAINT referenz_q_afs_nr_fkey FOREIGN KEY (q_afs_nr) REFERENCES elka.anfallstelle(id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY elka.referenz
    ADD CONSTRAINT referenz_q_entl_nr_fkey FOREIGN KEY (q_entl_nr) REFERENCES oberflgw.sb_entlastung(nr) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY elka.referenz
    ADD CONSTRAINT referenz_q_msst_nr_fkey FOREIGN KEY (q_msst_nr) REFERENCES labor.messstelle(id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY elka.referenz
    ADD CONSTRAINT referenz_q_nw_afs_nr_fkey FOREIGN KEY (q_nw_afs_nr) REFERENCES oberflgw.afs_niederschlagswasser(nr) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY elka.referenz
    ADD CONSTRAINT referenz_q_sb_nr_fkey FOREIGN KEY (q_sb_nr) REFERENCES oberflgw.sonderbauwerk(nr) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY elka.referenz
    ADD CONSTRAINT referenz_z_aba_nr_fkey FOREIGN KEY (z_aba_nr) REFERENCES elka.aba(id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY elka.referenz
    ADD CONSTRAINT referenz_z_afs_nr_fkey FOREIGN KEY (z_afs_nr) REFERENCES elka.anfallstelle(id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY elka.referenz
    ADD CONSTRAINT referenz_z_entl_nr_fkey FOREIGN KEY (z_entl_nr) REFERENCES oberflgw.sb_entlastung(nr) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY elka.referenz
    ADD CONSTRAINT referenz_z_msst_nr_fkey FOREIGN KEY (z_msst_nr) REFERENCES labor.messstelle(id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY elka.referenz
    ADD CONSTRAINT referenz_z_nw_afs_nr_fkey FOREIGN KEY (z_nw_afs_nr) REFERENCES oberflgw.afs_niederschlagswasser(nr) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY elka.referenz
    ADD CONSTRAINT referenz_z_sb_nr_fkey FOREIGN KEY (z_sb_nr) REFERENCES oberflgw.sonderbauwerk(nr) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY elka.z_aba_verfahren
    ADD CONSTRAINT z_aba_verfahren_abwasbehverf_nr_fkey FOREIGN KEY (abwasbehverf_nr) REFERENCES elka.abaverfahren(nr);

ALTER TABLE ONLY elka.z_aba_verfahren
    ADD CONSTRAINT z_aba_verfahren_anlage_nr_fkey FOREIGN KEY (anlage_nr) REFERENCES elka.aba(id);

ALTER TABLE ONLY indeinl.anh_40_fachdaten
    ADD CONSTRAINT anfallstelle_40_fachdaten_fkey FOREIGN KEY (anfallstelleid) REFERENCES elka.anfallstelle(id);

ALTER TABLE ONLY indeinl.anh_49_fachdaten
    ADD CONSTRAINT anfallstelle_49_fachdaten_fkey FOREIGN KEY (anfallstelleid) REFERENCES elka.anfallstelle(id);

ALTER TABLE ONLY indeinl.anh_50_fachdaten
    ADD CONSTRAINT anfallstelle_50_fachdaten_fkey FOREIGN KEY (anfallstelleid) REFERENCES elka.anfallstelle(id);

ALTER TABLE ONLY indeinl.anh_52_fachdaten
    ADD CONSTRAINT anfallstelle_52_fachdaten_fkey FOREIGN KEY (anfallstelleid) REFERENCES elka.anfallstelle(id);

ALTER TABLE ONLY indeinl.anh_53_fachdaten
    ADD CONSTRAINT anfallstelle_53_fachdaten_fkey FOREIGN KEY (anfallstelleid) REFERENCES elka.anfallstelle(id);

ALTER TABLE ONLY indeinl.anh_55_fachdaten
    ADD CONSTRAINT anfallstelle_55_fachdaten_fkey FOREIGN KEY (anfallstelleid) REFERENCES elka.anfallstelle(id);

ALTER TABLE ONLY indeinl.anh_56_fachdaten
    ADD CONSTRAINT anfallstelle_56_fachdaten_fkey FOREIGN KEY (anfallstelleid) REFERENCES elka.anfallstelle(id);

ALTER TABLE ONLY indeinl.bwk_fachdaten
    ADD CONSTRAINT anfallstelle_bwk_fachdaten_fkey FOREIGN KEY (anfallstelleid) REFERENCES elka.anfallstelle(id);

ALTER TABLE ONLY indeinl.anh_49_abfuhr
    ADD CONSTRAINT anh49_abfuhr_anh49_fachdaten_fkey FOREIGN KEY (anh49id) REFERENCES indeinl.anh_49_fachdaten(id);

ALTER TABLE ONLY indeinl.anh_49_abscheiderdetails
    ADD CONSTRAINT anh49_abscheiderdetails_anh49_fachdaten_fkey FOREIGN KEY (anh49id) REFERENCES indeinl.anh_49_fachdaten(id);

ALTER TABLE ONLY indeinl.anh_49_analysen
    ADD CONSTRAINT anh49_analysen_anh49_fachdaten_fkey FOREIGN KEY (anh49id) REFERENCES indeinl.anh_49_fachdaten(id);

ALTER TABLE ONLY indeinl.anh_49_kontrollen
    ADD CONSTRAINT anh49_kontrolle_anh49_fachdaten_fkey FOREIGN KEY (anh49id) REFERENCES indeinl.anh_49_fachdaten(id);

ALTER TABLE ONLY indeinl.anh_50_fachdaten
    ADD CONSTRAINT fk1ef5f3605afcac2e FOREIGN KEY (entsorgerid) REFERENCES indeinl.entsorger(id);

ALTER TABLE ONLY indeinl.suev_fachdaten
    ADD CONSTRAINT fk85fdf8588b7385c8 FOREIGN KEY (objektid) REFERENCES basis.objekt(id);

ALTER TABLE ONLY labor.meta_parameter
    ADD CONSTRAINT atl_meta_parameter_einheit_id_fkey FOREIGN KEY (einheit_id) REFERENCES labor.einheiten(id);

ALTER TABLE ONLY labor.meta_parameter
    ADD CONSTRAINT atl_meta_parameter_ordnungsbegriff_fkey FOREIGN KEY (ordnungsbegriff) REFERENCES labor.parameter(ordnungsbegriff);

ALTER TABLE ONLY labor.probenahme
    ADD CONSTRAINT atl_probemahmen_basis_sachbearbeiter_fkey FOREIGN KEY (sachbearbeiter) REFERENCES basis.sachbearbeiter(id);

ALTER TABLE ONLY labor.probenahme
    ADD CONSTRAINT atl_probenahmen_atl_probepkt_fkey FOREIGN KEY (messstid) REFERENCES labor.messstelle(id);

ALTER TABLE ONLY labor.messstelle
    ADD CONSTRAINT atl_probepkt_basis_sachbearbeiter_fkey FOREIGN KEY (sachbearbeiter) REFERENCES basis.sachbearbeiter(id);

ALTER TABLE ONLY labor.sielhaut
    ADD CONSTRAINT atl_sielhaut_atl_probepkt_fkey FOREIGN KEY (probepktid) REFERENCES labor.messstelle(id);

ALTER TABLE ONLY labor.messstelle
    ADD CONSTRAINT fk3a5a1f0f5d55491f FOREIGN KEY (art_id) REFERENCES labor.probeart(id);

ALTER TABLE ONLY labor.messstelle
    ADD CONSTRAINT fk3a5a1f0f8b7385c8 FOREIGN KEY (objektid) REFERENCES basis.objekt(id);

ALTER TABLE ONLY labor.messstelle
    ADD CONSTRAINT fk3a5a1f0fb128db5a FOREIGN KEY (ka_id) REFERENCES labor.klaeranlage(id);

ALTER TABLE ONLY labor.analyseposition
    ADD CONSTRAINT fk3d2450f610b7d285 FOREIGN KEY (einheiten_id) REFERENCES labor.einheiten(id);

ALTER TABLE ONLY labor.analyseposition
    ADD CONSTRAINT fk3d2450f6ac5a119 FOREIGN KEY (probenahme_id) REFERENCES labor.probenahme(id);

ALTER TABLE ONLY labor.analyseposition
    ADD CONSTRAINT fk3d2450f6d4eaa7c5 FOREIGN KEY (parameter_id) REFERENCES labor.parameter(ordnungsbegriff);

ALTER TABLE ONLY labor.probenahme
    ADD CONSTRAINT fk7989e7abc0be5519 FOREIGN KEY (status) REFERENCES labor.status(id);

ALTER TABLE ONLY labor.parameter
    ADD CONSTRAINT fkc85f7d837f84beb5 FOREIGN KEY (parametergruppe_id) REFERENCES labor.parametergruppen(id);

ALTER TABLE ONLY labor.analyseposition
    ADD CONSTRAINT position_methode_fkey FOREIGN KEY (methode_id) REFERENCES elka.map_elka_analysemethode(id);

ALTER TABLE ONLY oberflgw.afs_niederschlagswasser
    ADD CONSTRAINT afs_niederschlagswasser_anfallstellen_nr_fkey FOREIGN KEY (anfallstellen_nr) REFERENCES elka.anfallstelle(id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY oberflgw.afs_niederschlagswasser
    ADD CONSTRAINT afs_niederschlagswasser_entw_grund_nr_fkey FOREIGN KEY (entw_grund_nr) REFERENCES oberflgw.entwaesserungsgrundstueck(nr) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY oberflgw.afs_stoffe
    ADD CONSTRAINT afs_stoffe_anfallstellen_nr_fkey FOREIGN KEY (anfallstellen_nr) REFERENCES elka.anfallstelle(id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY oberflgw.entwaesserungsgrundstueck
    ADD CONSTRAINT entwaesserungsgrundstueck_objekt_id_fkey FOREIGN KEY (objekt_id) REFERENCES basis.objekt(id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY oberflgw.entwaesserungsgrundstueck
    ADD CONSTRAINT entwaesserungsgrundstueck_wasserecht_nr_fkey FOREIGN KEY (wasserrecht_nr) REFERENCES elka.wasserrecht(id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY oberflgw.sb_entlastung
    ADD CONSTRAINT sb_entlastung_sb_nr_fkey FOREIGN KEY (sb_nr) REFERENCES oberflgw.sonderbauwerk(nr) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY oberflgw.sonderbauwerk
    ADD CONSTRAINT sonderbauwerk_anspr_adr_nr_fkey FOREIGN KEY (anspr_adr_nr) REFERENCES basis.adresse(id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY oberflgw.sonderbauwerk
    ADD CONSTRAINT sonderbauwerk_objekt_id_fkey FOREIGN KEY (objekt_id) REFERENCES basis.objekt(id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY oberflgw.sonderbauwerk
    ADD CONSTRAINT sonderbauwerk_wasserrecht_genehmigung_nr_fkey FOREIGN KEY (wasserrecht_genehmigung_nr) REFERENCES elka.wasserrecht(id) ON UPDATE CASCADE ON DELETE RESTRICT;

ALTER TABLE ONLY oberflgw.z_betrieb_massnahme
    ADD CONSTRAINT z_betrieb_massnahme_betrieb_nr_fkey FOREIGN KEY (betrieb_nr) REFERENCES basis.adresse(id);

ALTER TABLE ONLY oberflgw.z_betrieb_massnahme
    ADD CONSTRAINT z_betrieb_massnahme_massnahme_nr_fkey FOREIGN KEY (massnahme_nr) REFERENCES oberflgw.massnahme(nr);

ALTER TABLE ONLY oberflgw.z_entwaessgr_abwasbehverf
    ADD CONSTRAINT z_entwaessgr_abwasbehverf_abwasbehverf_nr_fkey FOREIGN KEY (abwasbehverf_nr) REFERENCES elka.abaverfahren(nr);

ALTER TABLE ONLY oberflgw.z_entwaessgr_abwasbehverf
    ADD CONSTRAINT z_entwaessgr_abwasbehverf_entw_grund_nr_fkey FOREIGN KEY (entw_grund_nr) REFERENCES oberflgw.entwaesserungsgrundstueck(nr);

ALTER TABLE ONLY oberflgw.z_rbf_schutzgueter
    ADD CONSTRAINT z_rbf_schutzgueter_sb_nr_fkey FOREIGN KEY (sb_nr) REFERENCES oberflgw.sonderbauwerk(nr);

ALTER TABLE ONLY oberflgw.z_sb_regeln
    ADD CONSTRAINT z_sb_regeln_sb_nr_fkey FOREIGN KEY (sb_nr) REFERENCES oberflgw.sonderbauwerk(nr);

ALTER TABLE ONLY oberflgw.z_sb_verfahren
    ADD CONSTRAINT z_sb_verfahren_sb_nr_fkey FOREIGN KEY (sb_nr) REFERENCES oberflgw.sonderbauwerk(nr);



GRANT ALL ON SCHEMA awsv TO PUBLIC;

GRANT ALL ON SCHEMA basis TO PUBLIC;

GRANT ALL ON SCHEMA elka TO PUBLIC;

GRANT ALL ON SCHEMA gis TO PUBLIC;

GRANT ALL ON SCHEMA indeinl TO PUBLIC;

GRANT ALL ON SCHEMA labor TO PUBLIC;

GRANT ALL ON SCHEMA oberflgw TO PUBLIC;

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;
GRANT ALL ON SCHEMA public TO auikadmin;

GRANT ALL ON TABLE basis.inhaber TO PUBLIC;

GRANT ALL ON TABLE awsv.lageranlagen TO PUBLIC;

GRANT ALL ON SEQUENCE awsv.vaws_abfuellflaeche_id_seq TO PUBLIC;

GRANT ALL ON SEQUENCE awsv.vaws_abscheider_id_seq TO PUBLIC;

GRANT ALL ON SEQUENCE awsv.vaws_jgs_id_seq TO PUBLIC;

GRANT ALL ON SEQUENCE basis.basis_sachbearbeiter_id_seq TO PUBLIC;

GRANT ALL ON SEQUENCE basis.basis_standort_id_seq TO PUBLIC;

GRANT ALL ON SEQUENCE basis.inhaber_id_seq TO PUBLIC;

GRANT ALL ON TABLE basis.view_two_way_objektverknuepfung TO PUBLIC;

GRANT ALL ON TABLE elka._q_z TO PUBLIC;

GRANT ALL ON TABLE elka.anhang TO PUBLIC;

GRANT SELECT,INSERT,REFERENCES,DELETE,TRIGGER,UPDATE ON TABLE elka.e_adresse TO PUBLIC;

GRANT SELECT,INSERT,REFERENCES,DELETE,TRIGGER,UPDATE ON TABLE elka.e_standort TO PUBLIC;

GRANT SELECT,INSERT,REFERENCES,DELETE,TRIGGER,UPDATE ON TABLE elka.e_abwasserbehandlungsanlage TO PUBLIC;

GRANT ALL ON TABLE oberflgw.afs_niederschlagswasser TO PUBLIC;

GRANT ALL ON TABLE elka.e_afs_niederschlagswasser TO PUBLIC;

GRANT ALL ON TABLE elka.e_anfallstelle TO PUBLIC;

GRANT SELECT,INSERT,REFERENCES,DELETE,TRIGGER,UPDATE ON TABLE elka.e_betrieb TO PUBLIC;

GRANT ALL ON TABLE elka.e_einleitungsstelle TO PUBLIC;

GRANT ALL ON TABLE oberflgw.entwaesserungsgrundstueck TO PUBLIC;

GRANT ALL ON TABLE elka.e_entwaesserungsgrundstueck TO PUBLIC;

GRANT SELECT,INSERT,REFERENCES,DELETE,TRIGGER,UPDATE ON TABLE elka.e_messstelle TO PUBLIC;

GRANT SELECT,INSERT,REFERENCES,DELETE,TRIGGER,UPDATE ON TABLE elka.e_probenahme TO PUBLIC;

GRANT SELECT ON TABLE elka.map_elka_einheit TO PUBLIC;

GRANT SELECT ON TABLE elka.map_elka_stoff TO PUBLIC;

GRANT SELECT,INSERT,REFERENCES,DELETE,TRIGGER,UPDATE ON TABLE elka.e_probenahme_ueberwachungsergeb TO PUBLIC;

GRANT ALL ON TABLE oberflgw.sonderbauwerk TO PUBLIC;

GRANT ALL ON TABLE elka.e_sonderbauwerk TO PUBLIC;

GRANT ALL ON TABLE elka.e_wasserrecht TO PUBLIC;

GRANT ALL ON TABLE elka.z_aba_verfahren TO PUBLIC;

GRANT ALL ON TABLE elka.e_z_aba_verfahren TO PUBLIC;

GRANT ALL ON TABLE elka.e_z_aba_wasserrecht TO PUBLIC;

GRANT ALL ON TABLE elka.e_z_els_wasserrecht TO PUBLIC;

GRANT ALL ON SEQUENCE elka.elka_wasserrecht_id_seq TO PUBLIC;

GRANT SELECT ON TABLE elka.map_elka_anhang TO PUBLIC;

GRANT ALL ON TABLE elka.map_elka_gewkennz TO PUBLIC;

GRANT ALL ON TABLE elka.referenz TO PUBLIC;

GRANT ALL ON SEQUENCE elka.referenz_nr_seq TO PUBLIC;

GRANT ALL ON SEQUENCE elka.z_aba_verfahren_id_seq TO PUBLIC;

GRANT ALL ON SEQUENCE labor.atl_sielhaut_id_seq TO PUBLIC;

GRANT ALL ON TABLE labor.view_sielhaut TO PUBLIC;

GRANT ALL ON SEQUENCE indeinl.anh_40_fachdaten_id_seq TO PUBLIC;

GRANT ALL ON SEQUENCE indeinl.anh_49_abfuhr_id_seq TO PUBLIC;

GRANT ALL ON SEQUENCE indeinl.anh_49_fachdaten_id_seq TO PUBLIC;

GRANT ALL ON SEQUENCE indeinl.anh_49_kontrollen_id_seq TO PUBLIC;

GRANT ALL ON SEQUENCE indeinl.anh_50_fachdaten_id_seq TO PUBLIC;

GRANT ALL ON SEQUENCE indeinl.anh_52_fachdaten_id_seq TO PUBLIC;

GRANT ALL ON SEQUENCE indeinl.anh_53_fachdaten_id_seq TO PUBLIC;

GRANT ALL ON SEQUENCE indeinl.anh_55_fachdaten_id_seq TO PUBLIC;

GRANT ALL ON SEQUENCE indeinl.anh_56_fachdaten_id_seq TO PUBLIC;

GRANT ALL ON SEQUENCE indeinl.anh_bwk_fachdaten_id_seq TO PUBLIC;

GRANT ALL ON SEQUENCE indeinl.anh_suev_fachdaten_id_seq TO PUBLIC;

GRANT ALL ON SEQUENCE labor.atl_probepkt_id_seq TO PUBLIC;

GRANT ALL ON TABLE labor.gebuehren TO PUBLIC;

GRANT ALL ON TABLE labor.view_atl_analyseposition_all TO PUBLIC;

GRANT SELECT,INSERT,REFERENCES,DELETE,TRIGGER,UPDATE ON TABLE labor.view_sielhautqb_kreuztabelle_firmen TO PUBLIC;

GRANT ALL ON TABLE labor.view_sielhautqb_probenahmen_routine TO PUBLIC;

GRANT SELECT ON TABLE labor.view_sielhautqb_kreuztabelle_routine TO PUBLIC;

GRANT ALL ON SEQUENCE oberflgw.afs_nw_nr_seq TO PUBLIC;

GRANT ALL ON TABLE oberflgw.afs_stoffe TO PUBLIC;

GRANT ALL ON SEQUENCE oberflgw.igl_p_einl_id_seq TO PUBLIC;

GRANT ALL ON TABLE oberflgw.massnahme TO PUBLIC;

GRANT ALL ON TABLE oberflgw.sb_entlastung TO PUBLIC;

GRANT ALL ON TABLE oberflgw.view_einleitstellen TO PUBLIC;

GRANT ALL ON TABLE oberflgw.z_betrieb_massnahme TO PUBLIC;

GRANT ALL ON TABLE oberflgw.z_entwaessgr_abwasbehverf TO PUBLIC;

GRANT ALL ON TABLE oberflgw.z_rbf_schutzgueter TO PUBLIC;

GRANT ALL ON TABLE oberflgw.z_sb_regeln TO PUBLIC;

GRANT ALL ON TABLE oberflgw.z_sb_verfahren TO PUBLIC;

GRANT ALL ON SEQUENCE public.hibernate_sequence TO PUBLIC;

GRANT SELECT ON TABLE public.tab_streets_alkis TO PUBLIC;




INSERT INTO labor.parameter VALUES ('B00001', '', 'Bodensatz', NULL, 0, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 85, true, true, NULL, NULL);
INSERT INTO labor.parameter VALUES ('B00106', '', 'Benzo-(a)-pyren', NULL, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, NULL, true, false, 2320, NULL);
INSERT INTO labor.parameter VALUES ('B00107', '', 'Trockensubstanz', NULL, NULL, NULL, 1, 0, 0, true, NULL, NULL, NULL, NULL, true, false, 1469, NULL);
INSERT INTO labor.parameter VALUES ('B00108', '', 'Cobalt (Co)', NULL, 2, NULL, 2.8, 20, 7.14, true, NULL, NULL, NULL, 28, true, false, 1186, NULL);
INSERT INTO labor.parameter VALUES ('B00109', '', 'PCB 28', NULL, NULL, NULL, NULL, 0.05, 0, true, NULL, NULL, NULL, NULL, true, false, 2071, NULL);
INSERT INTO labor.parameter VALUES ('B00110', '', 'PCB 52', NULL, NULL, NULL, NULL, 0.05, 0, true, NULL, NULL, NULL, NULL, true, false, 2072, NULL);
INSERT INTO labor.parameter VALUES ('B00111', '', 'PCB 101', NULL, NULL, NULL, NULL, 0.05, 0, true, NULL, NULL, NULL, NULL, true, false, 2073, NULL);
INSERT INTO labor.parameter VALUES ('B00112', '', 'PCB 138', NULL, NULL, NULL, NULL, 0.05, 0, true, NULL, NULL, NULL, NULL, true, false, 2074, NULL);
INSERT INTO labor.parameter VALUES ('B00113', '', 'PCB 153', NULL, NULL, NULL, NULL, 0.05, 0, true, NULL, NULL, NULL, 57, true, false, 2076, NULL);
INSERT INTO labor.parameter VALUES ('B00114', '', 'PCB 180', NULL, NULL, NULL, NULL, 0.05, 0, true, NULL, NULL, NULL, 63, true, false, 2077, NULL);
INSERT INTO labor.parameter VALUES ('B00125', '', 'Stickstoff (N2) ges.', NULL, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, NULL, NULL);
INSERT INTO labor.parameter VALUES ('B00145', '', 'PCB, Summe', NULL, NULL, NULL, NULL, 0, 38.08, true, NULL, NULL, NULL, NULL, true, false, 2075, NULL);
INSERT INTO labor.parameter VALUES ('B00150', '', 'Glühverlust', NULL, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, NULL, true, false, 1458, NULL);
INSERT INTO labor.parameter VALUES ('B00188', '', 'Chromat (CrVI)', NULL, 0.2, NULL, NULL, 0, 5.95, true, NULL, NULL, NULL, 27, true, false, NULL, NULL);
INSERT INTO labor.parameter VALUES ('B00273', '', 'Phosphor (P2O5) ges.', NULL, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, NULL, true, false, NULL, NULL);
INSERT INTO labor.parameter VALUES ('B00384', '', 'Thallium (Tl)', NULL, 0.2, NULL, NULL, 1, 35.7, true, NULL, NULL, NULL, 34, true, false, 1132, NULL);
INSERT INTO labor.parameter VALUES ('B00600', '', 'pH-Wert bei Probenahme', NULL, 10, NULL, NULL, 0, 0, true, NULL, NULL, NULL, 2, true, false, 1061, NULL);
INSERT INTO labor.parameter VALUES ('B00613', NULL, 'PCDD, PCDF', NULL, 2, NULL, NULL, 0, 297.5, true, NULL, NULL, NULL, 53, true, false, 2490, NULL);
INSERT INTO labor.parameter VALUES ('L10111', NULL, 'Temperatur bei Probenahme', NULL, 35, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, 1, true, false, 1011, NULL);
INSERT INTO labor.parameter VALUES ('L10821', NULL, 'Leitfähigkeit bei Probenahme', NULL, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, 3, true, false, 1082, NULL);
INSERT INTO labor.parameter VALUES ('L11130', '', 'Kalium (K) K2O', NULL, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, NULL, true, false, NULL, NULL);
INSERT INTO labor.parameter VALUES ('L11210', '', 'Magnesium (Mg) MgO', NULL, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, 64, true, false, NULL, NULL);
INSERT INTO labor.parameter VALUES ('L11380', '', 'Blei (Pb)', NULL, 1, NULL, 34, 200, 7.14, true, NULL, NULL, NULL, 24, true, false, 1138, NULL);
INSERT INTO labor.parameter VALUES ('L11420', '', 'Arsen (As)', NULL, 0.5, NULL, NULL, 10, 7.14, true, NULL, NULL, NULL, 23, true, false, 1142, NULL);
INSERT INTO labor.parameter VALUES ('L11510', '', 'Chrom (Cr)', NULL, 1, NULL, 24, 200, 7.14, true, NULL, NULL, NULL, 26, true, false, 1151, NULL);
INSERT INTO labor.parameter VALUES ('L11610', '', 'Kupfer (Cu)', NULL, 1, NULL, 250, 550, 7.14, true, NULL, NULL, NULL, 29, true, false, 1161, NULL);
INSERT INTO labor.parameter VALUES ('L11640', '', 'Zink (Zn)', NULL, 2, NULL, 800, 1400, 7.14, true, NULL, NULL, NULL, 35, true, false, 1164, NULL);
INSERT INTO labor.parameter VALUES ('L11650', '', 'Cadmium (Cd)', NULL, 0.1, NULL, 0.6, 2.5, 7.14, true, NULL, NULL, NULL, 25, true, false, 1165, NULL);
INSERT INTO labor.parameter VALUES ('L11660', '', 'Quecksilber (Hg)', NULL, 0.05, NULL, 0.7, 2, 13.09, true, NULL, NULL, NULL, 31, true, false, 1166, NULL);
INSERT INTO labor.parameter VALUES ('L11880', '', 'Nickel (Ni)', NULL, 1, NULL, 17, 80, 7.14, true, NULL, NULL, NULL, 30, true, false, 1188, NULL);
INSERT INTO labor.parameter VALUES ('L12490', '', 'Ammonium- Stickstoff (N) NH4-N', NULL, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, 1249, NULL);
INSERT INTO labor.parameter VALUES ('L13430', '', 'AOX', NULL, 1, NULL, 0.5, 400, 33.32, true, NULL, NULL, NULL, 38, true, false, 1343, NULL);
INSERT INTO labor.parameter VALUES ('L15230', '', 'TOC (Organischer Kohlenstoff, ges.)', NULL, NULL, NULL, NULL, NULL, 14.28, true, NULL, NULL, NULL, 86, true, false, 1523, NULL);
INSERT INTO labor.parameter VALUES ('N00001', NULL, 'DEHP', NULL, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, NULL, true, false, 2679, NULL);
INSERT INTO labor.parameter VALUES ('N00002', NULL, 'Basisch wirksame Substanz als CaO', NULL, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, NULL, true, false, 1228, NULL);
INSERT INTO labor.parameter VALUES ('N00003', NULL, 'Schwefel', NULL, NULL, NULL, NULL, 0, 0, true, NULL, NULL, NULL, NULL, true, false, NULL, NULL);
INSERT INTO labor.parameter VALUES ('P00002', NULL, 'Summe 2 PFT', NULL, NULL, NULL, NULL, 100, 124.95, true, NULL, NULL, NULL, 87, true, false, NULL, NULL);
INSERT INTO labor.parameter VALUES ('P00013', NULL, 'Abwassermenge', NULL, NULL, NULL, NULL, NULL, 0, true, NULL, NULL, NULL, NULL, true, false, 901, NULL);
--
-- PostgreSQL database dump complete
--


SET search_path = elka, pg_catalog;

--Add basis.objekt.id to view
CREATE OR REPLACE VIEW e_betrieb AS
    SELECT DISTINCT e_standort.nr,
        e_standort.nr AS standort_nr,
        e_adresse.nr AS wr_adr_nr,
        e_adresse.name1 AS bezeichnung,
        false AS suevkan_tog,
        e_standort.e32,
        e_standort.n32,
        e_standort.erstell_dat,
        e_standort.aktual_dat,
        e_standort.herkunft,
        objekt.id as objekt_id
    FROM e_adresse,
        basis.objekt as objekt,
        e_standort
    WHERE (((e_adresse.nr = objekt.betreiberid) AND (e_standort.nr = objekt.standortid)) AND (objekt.objektartid = 42));

CREATE OR REPLACE VIEW e_messstelle AS
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
    'ELKA_KR711'::text AS herkunft,
    objekt.id as objekt_id,
    e_adresse.nr as adresse
   FROM e_adresse,
    e_standort,
    basis.objekt,
    labor.messstelle
  WHERE (((((((objekt.standortid = e_standort.nr) AND (objekt.betreiberid = e_adresse.nr)) AND (messstelle.objektid = objekt.id)) AND (messstelle.art_id = 2)) AND (objekt._deleted = false)) AND (objekt.inaktiv = false)) AND (objekt.elkarelevant = true));

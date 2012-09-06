SELECT DISTINCT
  -- Primary Key
  indeinl_genehmigung.objektid::integer	AS betriebseinrichtung_nr,
  1::integer				AS betriebseinrichtung_ver, 

  -- Historisierung
  '1970-01-01'::date					AS gueltig_von,     -- NOT NULL
  NULL::date						AS gueltig_bis,
  NULL::timestamp without time zone			AS aenderungs_datum,
  '1970-01-01 00:00:00'::timestamp without time zone	AS erfassungs_datum,    -- NOT NULL
  1::integer						AS historien_nr,    -- NOT NULL
  true::boolean						AS ist_aktuell_jn,  -- NOT NULL

  -- Daten
  -- Foreign Key: inka_betrieb
  ((basis_objekt.betreiberid << 16) | basis_objekt.standortid)::integer
				AS betrieb_nr,      -- NOT NULL
  1::integer			AS betrieb_ver,     -- NOT NULL
  -- Foreign Key: dea_adresse
  (basis_objekt.betreiberid << 16)::integer	AS adresse_betreib_nr,  -- NOT NULL
  1::integer					AS adresse_betreib_ver, -- NOT NULL
  -- Foreign Key: dea_adresse
  NULL::integer			AS adresse_anspr_nr,
  NULL::integer			AS adresse_anspr_ver,
  -- Foreign Key: inka_genehmigung
  indeinl_genehmigung.objektid::integer		AS genehmigung_nr,  -- NOT NULL
  1::integer					AS genehmigung_ver, -- NOT NULL
  -- Foreign Key: dea_arbeitsstaetten
--  NULL::character varying(2)  AS gaa_nr,
--  NULL::character varying(30) AS astnr,
--  NULL::character varying(1)  AS zusatz1,
--  NULL::character varying(3)  AS zusatz2,
  NULL::integer			AS arbeitsstaette_seq_nr,
  NULL::integer			AS arbeitsstaette_ver,
  -- Foreign Key: dea_wz_code
  auik_wz_code.wzcd_id::character varying(7)	AS wz_code,
  auik_wz_code.wzcd_version::integer		AS wz_code_ver,
  false::boolean		AS stilllegung_jn,  -- NOT NULL
  NULL::date			AS stilllegung_datum

FROM auik.indeinl_genehmigung
  LEFT OUTER JOIN auik.basis_objekt
    ON indeinl_genehmigung.objektid = basis_objekt.objektid
  LEFT OUTER JOIN auik.basis_betreiber
    ON basis_betreiber.id = basis_objekt.betreiberid
  LEFT OUTER JOIN tipi.auik_wz_code
    ON auik_wz_code.bezeichnung = basis_betreiber.auik_wz_code
    
WHERE
  indeinl_genehmigung.anhang IS NOT NULL AND
  indeinl_genehmigung.gen59 AND 
  basis_objekt.inaktiv = FALSE AND
  indeinl_genehmigung._deleted = false AND
  basis_objekt._deleted = false AND
  basis_betreiber._deleted = false;
  
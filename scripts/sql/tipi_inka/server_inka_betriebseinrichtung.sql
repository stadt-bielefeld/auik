SELECT DISTINCT
  -- Primary Key
  indeinl_genehmigung.objektid::integer		AS betriebseinrichtung_nr,
  1::integer					AS be_version, 
  '~~There be dragons~~'::character varying(20)	AS herkunft,

  -- Historisierung
  '1970-01-01'::date	AS inka_gueltig_von,	-- NOT NULL
  NULL::date		AS inka_gueltig_bis,
  1::integer		AS ist_aktuell_tog, 	-- NOT NULL
  '1970-01-01'::date	AS erfassungs_datum,	-- NOT NULL
  NULL::date		AS aenderungs_datum,

  -- Daten
  -- Foreign Key: inka_betrieb
  ((basis_objekt.betreiberid << 16) | basis_objekt.standortid)::integer
						AS betrieb_nr,			-- NOT NULL
  1::integer					AS betrieb_version, 		-- NOT NULL
  '~~There be dragons~~'::character varying(20)	AS bt_herkunft,			-- NOT NULL
  -- Foreign Key: inka_genehmigung
  indeinl_genehmigung.objektid::integer		AS genehmigung_nr,		-- NOT NULL
  1::integer					AS genehmigung_version,		-- NOT NULL
  '~~There be dragons~~'::character varying(20)	AS gn_herkunft,			-- NOT NULL
  -- Foreign Key: dea_adresse
  (basis_objekt.betreiberid << 16)::integer	AS adresse_betreib_nr,		-- NOT NULL
  1::integer					AS adresse_betreib_version,	-- NOT NULL
  '~~There be dragons~~'::character varying(20)	AS adresse_betreib_herkunft,	-- NOT NULL
  NULL::integer			AS ast_seq_nr,
  NULL::integer			AS ast_version,
  NULL::character varying(7)	AS wzcd_id,
  NULL::integer			AS wzcd_version,
  -- Foreign Key: dea_adresse
  NULL::integer					AS adresse_anspr_nr,
  NULL::integer					AS adresse_anspr_version,
  '~~There be dragons~~'::character varying(20)	AS adresse_anspr_herkunft,
  0::integer		AS stilllegung_tog,	-- NOT NULL
  NULL::date		AS stilllegung_datum,

  -- (some more...) Historisierung
  NULL::integer 	AS zustands_nr,		-- NULL
  '1970-01-01'::date 	AS gueltig_von, 	-- NOT NULL
  NULL::date 		AS ig_stichtag,
  NULL::date 		AS gueltig_bis,
  '1970-01-01'::date 	AS erstell_dat,		-- NOT NULL
  '1970-01-01'::date 	AS aktual_dat,		-- NOT NULL
  1::integer 		AS versionsnr,		-- NOT NULL
  NULL::timestamp 	AS zeitstempel

FROM auik.indeinl_genehmigung
  LEFT OUTER JOIN auik.basis_objekt
    ON indeinl_genehmigung.objektid = basis_objekt.objektid
  LEFT OUTER JOIN auik.basis_standort 
    ON basis_standort.id = basis_objekt.standortid
  LEFT OUTER JOIN auik.basis_betreiber
    ON basis_betreiber.id = basis_objekt.betreiberid
    
WHERE
  indeinl_genehmigung.anhang IS NOT NULL AND
  indeinl_genehmigung._deleted = false AND
  basis_objekt._deleted = false AND
  basis_standort._deleted = false AND
  basis_betreiber._deleted = false;
  
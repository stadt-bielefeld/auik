SELECT DISTINCT
  -- Primary Key
  ((basis_objekt.betreiberid << 16) | basis_objekt.standortid)::integer
						AS betrieb_nr,
  1::integer					AS betrieb_version,
  '~~There be dragons~~'::character varying(20)	AS herkunft,
 
  -- Historisierung
  '1970-01-01'::date	AS inka_gueltig_von,	-- NOT NULL
  NULL::date		AS inka_gueltig_bis,
  1::integer		AS ist_aktuell_tog, 	-- NOT NULL
  '1970-01-01'::date	AS erfassungs_datum,	-- NOT NULL
  NULL::date		AS aenderungs_datum,

  -- Daten
  -- Foreign Key: dea_gemeinde
  '05711000'::character varying(8)	AS gemeinde_id,		-- NOT NULL
  1::integer				AS gemeinde_version,	-- NOT NULL
  -- Foreign Key: dea_adresse
  CASE 
    WHEN (basis_standort.strasse = basis_betreiber.strasse AND
          basis_standort.hausnr = basis_betreiber.hausnr AND
          basis_standort.hausnrzus IS NOT DISTINCT FROM basis_betreiber.hausnrzus AND
          basis_standort.plz = basis_betreiber.plz)
    THEN (basis_objekt.betreiberid << 16)::integer
    ELSE basis_objekt.standortid::integer
  END						AS adresse_stand_nr,		-- NOT NULL
  1::integer					AS adresse_stand_version,	-- NOT NULL
  '~~There be dragons~~'::character varying(20)	AS adresse_stand_herkunft,	-- NOT NULL
  -- Foreign Key: dea_adresse
  NULL::integer					AS adresse_anspr_nr,
  NULL::integer					AS adresse_anspr_version,
  NULL::character varying(20)			AS adresse_anspr_herkunft,
  -- Foreign Key: dea_adresse
  (basis_objekt.betreiberid << 16)::integer 	AS adresse_einleit_nr,		-- NOT NULL
  1::integer				 	AS adresse_einleit_version,	-- NOT NULL
  '~~There be dragons~~'::character varying(20)	AS adresse_einleit_herkunft,	-- NOT NULL

  -- (some more...) Historisierung
  1::integer 		AS zustands_nr,		-- NOT NULL
  '1970-01-01'::date 	AS gueltig_von, 	-- NOT NULL
  NULL::date 		AS ig_stichtag,
  NULL::date 		AS gueltig_bis,
  '1970-01-01'::date 	AS erstell_dat,		-- NOT NULL
  '1970-01-01'::date 	AS aktual_dat,		-- NOT NULL
  1::integer 		AS versionsnr,		-- NOT NULL
  NULL::timestamp 	AS zeitstempel

FROM auik.indeinl_genehmigung
  JOIN auik.basis_objekt
    ON indeinl_genehmigung.objektid = basis_objekt.objektid
  JOIN auik.basis_standort 
    ON basis_standort.id = basis_objekt.standortid
  JOIN auik.basis_betreiber
    ON basis_betreiber.id = basis_objekt.betreiberid

WHERE indeinl_genehmigung._deleted = false AND
  basis_objekt._deleted = false AND
  basis_standort._deleted = false AND
  basis_betreiber._deleted = false AND
  indeinl_genehmigung.anhang IS NOT NULL;
  
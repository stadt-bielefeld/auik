SELECT
  -- Primary Key
  indeinl_genehmigung.objektid::integer		AS genehmigung_nr,
  1::integer					AS genehmigung_version,
  '~~There be dragons~~'::character varying(20)	AS herkunft,

  -- Historisierung
  '1970-01-01'::date	AS inka_gueltig_von,	-- NOT NULL
  NULL::date		AS inka_gueltig_bis,
  1::integer		AS ist_aktuell_tog, 	-- NOT NULL
  '1970-01-01'::date	AS erfassungs_datum,	-- NOT NULL
  NULL::date		AS aenderungs_datum,

  -- Daten
  (basis_objekt.betreiberid << 16 | basis_objekt.standortid)::integer
						AS betrieb_nr,		-- NOT NULL
  1::integer					AS betrieb_version,	-- NOT NULL
  '~~There be dragons~~'::character varying(20)	AS bt_herkunft,		-- NOT NULL
  'KR711'::character varying(16)		AS behoerden_id,	-- NOT NULL
  1::integer					AS behoerden_version,	-- NOT NULL
  indeinl_genehmigung.erstellungs_datum::date	AS genehmigung_datum,	-- NOT NULL
-- TODO: <field>_tog is the new <field>_jn. What does "tog" mean?
  indeinl_genehmigung.befristet::integer	AS befristet_tog,	-- NOT NULL
  indeinl_genehmigung.befristet_bis::date	AS befristet_bis,
  1::integer 					AS zustands_nr,		-- NOT NULL
  '1970-01-01'::date 				AS gueltig_von, 	-- NOT NULL
  NULL::date 					AS ig_stichtag,
  NULL::date 					AS gueltig_bis,
  '1970-01-01'::date 				AS erstell_dat,		-- NOT NULL
  '1970-01-01'::date 				AS aktual_dat,		-- NOT NULL
  1::integer 					AS versionsnr,		-- NOT NULL
  NULL::timestamp 				AS zeitstempel

FROM 
  auik.indeinl_genehmigung LEFT OUTER JOIN auik.basis_objekt
  ON indeinl_genehmigung.objektid = basis_objekt.objektid
  
WHERE 
  indeinl_genehmigung._deleted = FALSE AND 
  basis_objekt._deleted = FALSE AND 
  indeinl_genehmigung.anhang IS NOT NULL;

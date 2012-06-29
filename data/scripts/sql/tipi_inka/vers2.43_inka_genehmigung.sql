SELECT distinct
  -- Primary Key
  indeinl_genehmigung.objektid::integer	AS genehmigung_nr,
  1::integer				AS genehmigung_ver,

  -- Historisierung
  '1970-01-01'::date					AS gueltig_von, 	-- NOT NULL
  NULL::date 						AS gueltig_bis,
  NULL::timestamp without time zone			AS aenderungs_datum,
  '1970-01-01 00:00:00'::timestamp without time zone	AS erfassungs_datum,	-- NOT NULL
  1::integer 						AS historien_nr,	-- NOT NULL
  true::boolean						AS ist_aktuell_jn, 	-- NOT NULL

  -- Daten
  (basis_objekt.betreiberid << 16 | basis_objekt.standortid)::integer
						AS betrieb_nr,		-- NOT NULL
  1::integer					AS betrieb_ver,		-- NOT NULL
  'KR711'::character varying(5)			AS behoerden_id,	-- NOT NULL
  1::integer					AS behoerden_ver,	-- NOT NULL
  indeinl_genehmigung.erstellungs_datum::date	AS genehmigung_datum,	-- NOT NULL
  indeinl_genehmigung.befristet::boolean	AS befristet_jn,	-- NOT NULL
  indeinl_genehmigung.befristet_bis::date	AS befristet_bis
  
FROM auik.indeinl_genehmigung
  LEFT OUTER JOIN auik.basis_objekt
    ON indeinl_genehmigung.objektid = basis_objekt.objektid
  
WHERE 
  indeinl_genehmigung.anhang IS NOT NULL AND 
  indeinl_genehmigung.gen59 AND 
  basis_objekt._deleted = FALSE AND 
  indeinl_genehmigung._deleted = FALSE AND
  basis_objekt.inaktiv = FALSE;

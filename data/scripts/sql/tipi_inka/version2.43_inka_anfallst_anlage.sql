SELECT
  -- Primary / Foreign Key: inka_anfallstelle
  indeinl_genehmigung.objektid::integer	AS anfallstelle_nr,
  1::integer				AS anfallstelle_ver,
  -- Primary / Foreign Key: inka_anlage
  indeinl_genehmigung.objektid::integer AS anlage_nr,
  1::integer				AS anlage_ver,

  1::integer				AS anfallst_anlage_ver,

  -- Historisierung
  '1970-01-01'::date					AS gueltig_von, 	-- NOT NULL
  NULL::date 						AS gueltig_bis,
  NULL::timestamp without time zone			AS aenderungs_datum,
  '1970-01-01 00:00:00'::timestamp without time zone	AS erfassungs_datum,	-- NOT NULL
  1::integer 						AS historien_nr,	-- NOT NULL
  true::boolean						AS ist_aktuell_jn 	-- NOT NULL

FROM auik.indeinl_genehmigung
  LEFT OUTER JOIN auik.basis_objekt
    ON indeinl_genehmigung.objektid = basis_objekt.objektid

WHERE 
  indeinl_genehmigung.anhang IS NOT NULL AND 
  indeinl_genehmigung.gen59 AND 
  indeinl_genehmigung._deleted = FALSE AND
  basis_objekt._deleted = FALSE AND
  basis_objekt.inaktiv = FALSE;

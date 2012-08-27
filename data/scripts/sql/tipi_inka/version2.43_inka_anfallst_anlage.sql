SELECT
  -- Primary / Foreign Key: inka_anfallstelle
  inka_anfallstelle.anfallstelle_nr::integer	AS anfallstelle_nr,
  1::integer					AS anfallstelle_ver,
  -- Primary / Foreign Key: inka_anlage
  inka_anlage.anlage_nr::integer 		AS anlage_nr,
  1::integer					AS anlage_ver,

  1::integer					AS anfallst_anlage_ver,

  -- Historisierung
  '1970-01-01'::date					AS gueltig_von, 	-- NOT NULL
  NULL::date 						AS gueltig_bis,
  NULL::timestamp without time zone			AS aenderungs_datum,
  '1970-01-01 00:00:00'::timestamp without time zone	AS erfassungs_datum,	-- NOT NULL
  1::integer 						AS historien_nr,	-- NOT NULL
  true::boolean						AS ist_aktuell_jn 	-- NOT NULL

FROM tipi.inka_anfallstelle
  JOIN tipi.inka_anlage
    ON inka_anfallstelle.anfallstelle_nr = inka_anlage.anlage_nr;
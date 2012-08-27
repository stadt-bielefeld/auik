SELECT
  -- Primary / Foreign Key: inka_anfallstelle
  inka_anfallstelle.anfallstelle_nr::integer	AS anfallstelle_nr,
  1::integer					AS anfallstelle_ver,
  -- Primary / Foreign Key: dea_stoffe
  dea_stoffe.stoff_nr::integer			AS stoff_nr,
  1::integer					AS stoff_ver,

  1::integer					AS anfallst_stoffe_ver,

  -- Historisierung
  '1970-01-01'::date					AS gueltig_von, 	-- NOT NULL
  NULL::date 						AS gueltig_bis,
  NULL::timestamp without time zone			AS aenderungs_datum,
  '1970-01-01 00:00:00'::timestamp without time zone	AS erfassungs_datum,	-- NOT NULL
  1::integer 						AS historien_nr,	-- NOT NULL
  true::boolean						AS ist_aktuell_jn 	-- NOT NULL

FROM tipi.inka_anfallstelle
  JOIN tipi.dea_stoffe
    ON (true)
  JOIN auik.atl_parameter
    ON dea_stoffe.stoff_nr = atl_parameter.dea_stoffe_stoff_nr;
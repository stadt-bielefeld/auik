SELECT distinct
  -- Primary / Foreign Key: dea_parameter
  dea_parameter.parameter_nr::integer	AS parameter_nr,
  1::integer				AS parameter_ver,
  -- Primary / Foreign Key: inka_messstelle
  '05711000'::character varying(8)	AS gemeindekennzahl,
  1::integer				AS gemeinde_ver,
  indeinl_genehmigung.objektid::integer	AS uebergabestelle_lfd_nr,
  1::integer				AS uebergabestelle_ver,
  atl_probepkt.objektid::integer	AS messstelle_lfd_nr,
  1::integer				AS messstelle_ver,
  
  FALSE::boolean	AS selbst_amtl_jn,
  1::integer		AS ueberwachungswert_ver,

  -- Historisierung
  '1970-01-01'::date					AS gueltig_von, 	-- NOT NULL
  NULL::date 						AS gueltig_bis,
  NULL::timestamp without time zone			AS aenderungs_datum,
  '1970-01-01 00:00:00'::timestamp without time zone	AS erfassungs_datum,	-- NOT NULL
  1::integer 						AS historien_nr,	-- NOT NULL
  true::boolean						AS ist_aktuell_jn, 	-- NOT NULL

  -- Daten
  -- Foreign Key: inka_genehmigung
  indeinl_genehmigung.objektid::integer	AS genehmigung_nr,
  1::integer				AS genehmigung_ver,

  NULL::integer					AS anz_jahr,
  atl_meta_parameter.ueberwachungswert::float	AS uewert,
  CASE
    WHEN (atl_meta_parameter.ordnungsbegriff = 'L10610' OR
          atl_meta_parameter.ordnungsbegriff = 'B00600')
    THEN 6.5::float
    ELSE NULL::float
  END 						AS uewert_obergr,
  CASE
    WHEN (atl_meta_parameter.ordnungsbegriff = 'L10610' OR
          atl_meta_parameter.ordnungsbegriff = 'B00600')
    THEN 10::float
    ELSE NULL::float
  END						AS uewert_untergr,
  null::float					AS jahresfracht

FROM auik.indeinl_genehmigung
  JOIN auik.view_two_way_objektverknuepfung
    ON indeinl_genehmigung.objektid = view_two_way_objektverknuepfung.ist_verknuepft_mit
  JOIN auik.basis_objekt
    ON indeinl_genehmigung.objektid = basis_objekt.objektid
  JOIN auik.atl_probepkt
    ON atl_probepkt.objektid = view_two_way_objektverknuepfung.objekt
  JOIN auik.atl_probenahmen
    ON atl_probepkt.objektid = atl_probenahmen.objektid
  JOIN auik.atl_analyseposition
    ON atl_probenahmen.id = atl_analyseposition.probenahme_id
  JOIN auik.atl_parameter
    ON atl_analyseposition.parameter_id = atl_parameter.ordnungsbegriff
  JOIN auik.atl_einheiten
    ON atl_analyseposition.einheiten_id = atl_einheiten.id
  JOIN auik.atl_meta_parameter
    ON (atl_parameter.ordnungsbegriff = atl_meta_parameter.ordnungsbegriff AND
        atl_einheiten.id = atl_meta_parameter.einheit_id)
  JOIN tipi.dea_parameter
    ON (atl_parameter.dea_stoffe_stoff_nr = dea_parameter.stoff_nr AND
        atl_einheiten.dea_einheiten_masseinheiten_nr = dea_parameter.masseinheiten_nr AND
        atl_meta_parameter.dea_regelwerk_nr = dea_parameter.regelwerk_id AND
        atl_meta_parameter.dea_gruppe_dev = dea_parameter.gruppe_dev_id AND
        atl_meta_parameter.dea_varianten_nr = dea_parameter.varianten_id AND
        atl_meta_parameter.dea_trenn_nr_opt = dea_parameter.trenn_nr_opt)
        
WHERE 
  indeinl_genehmigung.anhang IS NOT NULL AND 
  indeinl_genehmigung.gen59 AND 
  basis_objekt.inaktiv = FALSE AND
  atl_probenahmen.datum_der_entnahme >= '2001-01-01 00:00:00' AND
  atl_meta_parameter.ueberwachungswert IS NOT NULL AND
  indeinl_genehmigung._deleted = FALSE AND
  basis_objekt._deleted = FALSE AND
  view_two_way_objektverknuepfung._deleted = FALSE AND
  atl_probepkt._deleted = FALSE AND
  atl_probenahmen._deleted = FALSE AND
  atl_analyseposition._deleted = FALSE AND
  atl_parameter._deleted = FALSE AND
  atl_einheiten._deleted = FALSE AND
  atl_meta_parameter._deleted = FALSE
;

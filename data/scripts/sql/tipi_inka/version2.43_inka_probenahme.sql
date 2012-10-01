SELECT distinct -- to be honest: I have no idea why we get double entries here :(
  -- Primary Key: inka_probenahme
  atl_probenahmen.id::integer	AS probenahme_nr,
  1::integer			AS probenahme_ver,

  -- Historisierung
  '1970-01-01'::date					AS gueltig_von, 	-- NOT NULL
  NULL::date 						AS gueltig_bis,
  NULL::timestamp without time zone			AS aenderungs_datum,
  '1970-01-01 00:00:00'::timestamp without time zone	AS erfassungs_datum,	-- NOT NULL
  1::integer 						AS historien_nr,	-- NOT NULL
  true::boolean						AS ist_aktuell_jn, 	-- NOT NULL

  -- Daten
  -- Foreign Key: inka_messstelle
  '05711000'::character varying(8)	AS gemeindekennzahl,		-- NOT NULL
  1::integer				AS gemeinde_ver,		-- NOT NULL
  indeinl_genehmigung.objektid::integer	AS uebergabestelle_lfd_nr,	-- NOT NULL
  1::integer				AS uebergabestelle_ver,		-- NOT NULL
  atl_probepkt.objektid::integer	AS messstelle_lfd_nr,		-- NOT NULL
  1::integer				AS messstelle_ver,		-- NOT NULL
  -- Foreign Key: dea_probedauer
  'A'::character varying(1)	AS prob_schluessel,	-- NOT NULL
  1::integer			AS prob_ver,		-- NOT NULL

  atl_probenahmen.datum_der_entnahme::date		AS datum_analyse,
  CASE WHEN (substring(atl_probenahmen.kennummer from 1 for 1) = '8'
         AND substring(atl_probenahmen.kennummer from 5 for 1) = '/')
       THEN true::boolean
       ELSE false::boolean
  END							AS selbstueberw_jn,	-- NOT NULL
  atl_probenahmen.kennummer::character varying(15)	AS probe_nr,
  false::boolean					AS durchflussmessung_jn,
  null::boolean						AS registrierung_jn,
  null::float						AS q_05h,
  null::float						AS q_2h

FROM auik.indeinl_genehmigung
  JOIN auik.view_two_way_objektverknuepfung
    ON indeinl_genehmigung.objektid = view_two_way_objektverknuepfung.ist_verknuepft_mit
  JOIN auik.basis_objekt
    ON indeinl_genehmigung.objektid = basis_objekt.objektid
  JOIN auik.atl_probepkt
    ON atl_probepkt.objektid = view_two_way_objektverknuepfung.objekt
  JOIN auik.atl_probenahmen
    ON atl_probepkt.objektid = atl_probenahmen.objektid

WHERE 
  indeinl_genehmigung.anhang IS NOT NULL AND 
  indeinl_genehmigung.gen59 AND 
  basis_objekt.inaktiv = FALSE AND
  atl_probenahmen.datum_der_entnahme >= '2001-01-01 00:00:00' AND
  indeinl_genehmigung._deleted = FALSE AND
  basis_objekt._deleted = FALSE AND
  view_two_way_objektverknuepfung._deleted = FALSE AND
  atl_probepkt._deleted = FALSE AND
  atl_probenahmen._deleted = FALSE;

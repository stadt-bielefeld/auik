SELECT distinct
  indeinl_genehmigung.anhang::character varying(20)	AS anh_id,
  1::integer						AS anh_ver, 
  atl_parameter.dea_stoffe_stoff_nr::integer		AS stoff_nr,
  1::integer						AS stoff_ver

FROM 
  auik.indeinl_genehmigung
  JOIN auik.basis_objekt
    ON basis_objekt.objektid = indeinl_genehmigung.objektid
  JOIN auik.view_two_way_objektverknuepfung
    ON indeinl_genehmigung.objektid = view_two_way_objektverknuepfung.ist_verknuepft_mit
  JOIN auik.atl_probepkt
    ON atl_probepkt.objektid = view_two_way_objektverknuepfung.objekt
  JOIN auik.atl_probenahmen
    ON atl_probenahmen.objektid = atl_probepkt.objektid
  JOIN auik.atl_analyseposition
    ON atl_analyseposition.probenahme_id = atl_probenahmen.id
  JOIN auik.atl_parameter
    ON atl_analyseposition.parameter_id = atl_parameter.ordnungsbegriff

WHERE 
  indeinl_genehmigung.anhang IS NOT NULL AND 
  indeinl_genehmigung.gen59 AND 
  basis_objekt.inaktiv = FALSE AND 
  atl_parameter.dea_stoffe_stoff_nr IS NOT NULL AND
  atl_probenahmen.datum_der_entnahme >= '2012-01-01 00:00:00' AND
  indeinl_genehmigung._deleted = FALSE AND 
  basis_objekt._deleted = FALSE AND 
  view_two_way_objektverknuepfung._deleted = FALSE AND 
  atl_probepkt._deleted = FALSE AND 
  atl_probenahmen._deleted = FALSE AND 
  atl_analyseposition._deleted = FALSE AND 
  atl_parameter._deleted = FALSE;

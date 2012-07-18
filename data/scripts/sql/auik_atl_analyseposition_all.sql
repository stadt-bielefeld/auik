SELECT
  atl_analyseposition.id,
  atl_analyseposition.grkl,
  atl_analyseposition.wert,
  atl_analyseposition.analyse_von,
  atl_analyseposition.bericht,
  atl_analyseposition.normwert,
  atl_analyseposition.einheiten_id,
  atl_analyseposition.parameter_id,
  atl_analyseposition.probenahme_id,
  atl_analyseposition._enabled,
  atl_analyseposition._deleted,
  atl_probenahmen.datum_der_entnahme,
  atl_probepkt.objektid AS probepkt_id
FROM
  auik.atl_analyseposition
  LEFT JOIN auik.atl_probenahmen
    ON atl_analyseposition.probenahme_id = atl_probenahmen.id
  LEFT JOIN auik.atl_probepkt
    ON atl_probenahmen.objektid = atl_probepkt.objektid

UNION

SELECT 
  -bsb.id AS id, 
  NULL::character varying(255) AS grkl, 
  bsb.wert / csb.wert AS wert, 
  bsb.analyse_von, 
  bsb.bericht, 
  NULL::double precision AS normwert, 
  0::integer AS einheiten_id, 
  'P00011'::character varying(255) AS parameter_id, 
  bsb.probenahme_id, 
  bsb._enabled, 
  bsb._deleted,
  atl_probenahmen.datum_der_entnahme,
  atl_probepkt.objektid AS probepkt_id
FROM 
  auik.atl_analyseposition bsb, 
  auik.atl_analyseposition csb
  LEFT JOIN auik.atl_probenahmen
    ON csb.probenahme_id = atl_probenahmen.id
  LEFT JOIN auik.atl_probepkt
    ON atl_probenahmen.objektid = atl_probepkt.objektid
WHERE 
  bsb.probenahme_id = csb.probenahme_id AND
  bsb.parameter_id = 'L16250' AND 
  csb.parameter_id = 'L15330' AND 
  csb.wert IS NOT NULL  AND 
  csb.wert != 0;

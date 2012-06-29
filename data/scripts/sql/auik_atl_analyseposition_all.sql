SELECT
  *
FROM
  auik.atl_analyseposition

UNION

SELECT 
  -bsb.id AS id, 
  NULL::character varying(255) AS grkl, 
  bsb.wert / csb.wert AS wert, 
  NULL::character varying(255) AS analyse_von, 
  NULL::character varying(255) AS bericht, 
  NULL::double precision AS normwert, 
  0::integer AS einheiten_id, 
  'P00011'::character varying(255) AS parameter_id, 
  bsb.probenahme_id, 
  bsb._enabled, 
  bsb._deleted
FROM 
  auik.atl_analyseposition bsb, 
  auik.atl_analyseposition csb
WHERE 
  bsb.probenahme_id = csb.probenahme_id AND
  bsb.parameter_id = 'L16250' AND 
  csb.parameter_id = 'L15330' AND 
  csb.wert IS NOT NULL  AND 
  csb.wert != 0;

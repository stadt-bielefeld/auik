SELECT 
  dea_wz_code.wzcd_id, 
  dea_wz_code.ebene, 
  dea_wz_code.abschnitt_id, 
  dea_wz_code.u_abschnitt_id, 
  dea_wz_code.abteilung_id, 
  dea_wz_code.grp_id, 
  dea_wz_code.kla_id, 
  dea_wz_code.u_kla_id, 
  dea_wz_code.bezeichnung
FROM 
  tipi.dea_wz_code
WHERE 
  dea_wz_code.inka_gueltig_bis IS NULL AND
  (dea_wz_code.abteilung_id IS NULL OR
   dea_wz_code.abteilung_id IN ('15', '17', '18', '19', '21', '22', '24', '25', '26', '27', '28', '29', '37', '50', '55', '60', '63', '85', '90', '93'))
ORDER BY
  dea_wz_code.abschnitt_id ASC, 
  CASE WHEN dea_wz_code.u_abschnitt_id IS NULL THEN 0 ELSE 1 END,
  dea_wz_code.u_abschnitt_id ASC, 
  CASE WHEN dea_wz_code.abteilung_id IS NULL THEN 0 ELSE 1 END,
  dea_wz_code.abteilung_id ASC, 
  CASE WHEN dea_wz_code.grp_id IS NULL THEN 0 ELSE 1 END,
  dea_wz_code.grp_id ASC, 
  CASE WHEN dea_wz_code.kla_id IS NULL THEN 0 ELSE 1 END,
  dea_wz_code.kla_id ASC, 
  CASE WHEN dea_wz_code.u_kla_id IS NULL THEN 0 ELSE 1 END,
  dea_wz_code.u_kla_id ASC;

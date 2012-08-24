-- DELETE FROM tipi.auik_wz_code;
-- INSERT INTO tipi.auik_wz_code
SELECT
  CASE
    WHEN dea_wz_code.ebene = 1 THEN (dea_wz_code.abschnitt_id || '. ' || dea_wz_code.bezeichnung)
    WHEN dea_wz_code.ebene = 2 THEN (dea_wz_code.abschnitt_id || dea_wz_code.u_abschnitt_id || '. ' || dea_wz_code.bezeichnung)
    WHEN dea_wz_code.ebene = 3 THEN (dea_wz_code.abschnitt_id || dea_wz_code.u_abschnitt_id || '.' || dea_wz_code.abteilung_id || ' ' || dea_wz_code.bezeichnung)
    WHEN dea_wz_code.ebene = 4 THEN (dea_wz_code.abschnitt_id || dea_wz_code.u_abschnitt_id || '.' || dea_wz_code.abteilung_id || '.' || dea_wz_code.grp_id || ' ' || dea_wz_code.bezeichnung)
    WHEN dea_wz_code.ebene = 5 THEN (dea_wz_code.abschnitt_id || dea_wz_code.u_abschnitt_id || '.' || dea_wz_code.abteilung_id || '.' || dea_wz_code.grp_id || dea_wz_code.kla_id || ' ' || dea_wz_code.bezeichnung)
    WHEN dea_wz_code.ebene = 6 THEN (dea_wz_code.abschnitt_id || dea_wz_code.u_abschnitt_id || '.' || dea_wz_code.abteilung_id || '.' || dea_wz_code.grp_id || dea_wz_code.kla_id || '.' || dea_wz_code.u_kla_id || ' ' || dea_wz_code.bezeichnung)
  END as bezeichnung,
  false as in_kurz_auswahl,
  dea_wz_code.ebene,
  dea_wz_code.wzcd_id,
  dea_wz_code.wzcd_version
FROM tipi.dea_wz_code
WHERE dea_wz_code.inka_gueltig_bis IS NULL
ORDER BY bezeichnung;

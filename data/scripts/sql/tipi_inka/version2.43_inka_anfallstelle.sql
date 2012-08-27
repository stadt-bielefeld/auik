SELECT
  -- Primary Key
  indeinl_genehmigung.objektid::integer	AS anfallstelle_nr,
  1::integer				AS anfallstelle_ver,

  -- Historisierung
  '1970-01-01'::date					AS gueltig_von, 	-- NOT NULL
  NULL::date 						AS gueltig_bis,
  NULL::timestamp without time zone			AS aenderungs_datum,
  '1970-01-01 00:00:00'::timestamp without time zone	AS erfassungs_datum,	-- NOT NULL
  1::integer 						AS historien_nr,	-- NOT NULL
  true::boolean						AS ist_aktuell_jn, 	-- NOT NULL

  -- Daten
  -- Foreign Key: inka_betriebseinrichtung
  indeinl_genehmigung.objektid::integer	AS betriebseinrichtung_nr,	-- NOT NULL
  1::integer				AS betriebseinrichtung_ver,	-- NOT NULL
  -- Foreign Key: inka_uebergabestelle
  '05711000'::character varying(8)	AS gemeindekennzahl,
  1::integer				AS gemeinde_ver,
  indeinl_genehmigung.objektid::integer	AS uebergabestelle_lfd_nr,
  1::integer				AS uebergabestelle_ver,
  -- Foreign Key: inka_genehmigung
  indeinl_genehmigung.objektid::integer	AS genehmigung_nr,	-- NOT NULL
  1::integer				AS genehmigung_ver,	-- NOT NULL
  -- Foreign Key: dea_anhang
  indeinl_genehmigung.anhang::character varying(20)	AS anh_id,	-- NOT NULL
  dea_anhang.anh_version::integer			AS anh_ver,	-- NOT NULL
  'There be dragons'::character varying(30)		AS beschreibung,
  NULL::float				AS max_vol_tag,
  NULL::integer				AS vol_jahr,
  NULL::boolean				AS dauerbetrieb_jn,
  NULL::boolean				AS chargenbetrieb_jn

FROM auik.indeinl_genehmigung
  LEFT OUTER JOIN auik.basis_objekt
    ON indeinl_genehmigung.objektid = basis_objekt.objektid
  LEFT OUTER JOIN tipi.dea_anhang
    ON dea_anhang.anh_id = (indeinl_genehmigung.anhang || '')

WHERE 
  indeinl_genehmigung.anhang IS NOT NULL AND 
  indeinl_genehmigung.gen59 AND 
  dea_anhang.inka_gueltig_bis IS NULL AND
  indeinl_genehmigung._deleted = FALSE AND
  basis_objekt._deleted = FALSE AND
  basis_objekt.inaktiv = FALSE;

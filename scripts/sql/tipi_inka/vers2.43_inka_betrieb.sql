SELECT DISTINCT
  -- Primary Key
  ((basis_objekt.betreiberid << 16) | basis_objekt.standortid)::integer
						AS betrieb_nr,
  1::integer					AS betrieb_ver,
 
  -- Historisierung
  '1970-01-01'::date					AS gueltig_von, 	-- NOT NULL
  NULL::date 						AS gueltig_bis,
  NULL::timestamp without time zone			AS aenderungs_datum,
  '1970-01-01 00:00:00'::timestamp without time zone	AS erfassungs_datum,	-- NOT NULL
  1::integer 						AS historien_nr,	-- NOT NULL
  true::boolean						AS ist_aktuell_jn, 	-- NOT NULL

  -- Daten
  -- Foreign Key: dea_adresse
  CASE 
    WHEN (basis_standort.strasse = basis_betreiber.strasse AND
          basis_standort.hausnr = basis_betreiber.hausnr AND
          basis_standort.hausnrzus IS NOT DISTINCT FROM basis_betreiber.hausnrzus AND
          basis_standort.plz = basis_betreiber.plz)
    THEN (basis_objekt.betreiberid << 16)::integer
    ELSE basis_objekt.standortid::integer
  END						AS adresse_stand_nr,	-- NOT NULL
  1::integer					AS adresse_stand_ver,	-- NOT NULL
  -- Foreign Key: dea_adresse
  (basis_objekt.betreiberid << 16)::integer 	AS adresse_einleit_nr,	-- NOT NULL
  1::integer				 	AS adresse_einleit_ver,	-- NOT NULL
  -- Foreign Key: dea_adresse
  NULL::integer					AS adresse_anspr_nr,
  NULL::integer					AS adresse_anspr_ver,
  -- Foreign Key: dea_gemeinde
  '05711000'::character varying(8)		AS gemeindekennzahl,	-- NOT NULL
  1::integer					AS gemeinde_ver		-- NOT NULL

FROM auik.indeinl_genehmigung
  LEFT OUTER JOIN auik.basis_objekt
    ON indeinl_genehmigung.objektid = basis_objekt.objektid
  LEFT OUTER JOIN auik.basis_standort 
    ON basis_standort.id = basis_objekt.standortid
  LEFT OUTER JOIN auik.basis_betreiber
    ON basis_betreiber.id = basis_objekt.betreiberid

WHERE 
  indeinl_genehmigung.anhang IS NOT NULL AND
  basis_objekt._deleted = false AND
  basis_standort._deleted = false AND
  basis_betreiber._deleted = false AND
  indeinl_genehmigung._deleted = false;
  
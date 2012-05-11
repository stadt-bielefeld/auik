SELECT DISTINCT
  -- Primary Key
  basis_standort.id::integer	AS adresse_nr,
  1::integer			AS adresse_ver,

  -- Historisierung
  '1970-01-01'::date					AS gueltig_von, 	-- NOT NULL
  NULL::date 						AS gueltig_bis,
  NULL::timestamp without time zone			AS aenderungs_datum,
  '1970-01-01 00:00:00'::timestamp without time zone	AS erfassungs_datum,	-- NOT NULL
  1::integer 						AS historien_nr,	-- NOT NULL
  true::boolean						AS ist_aktuell_jn, 	-- NOT NULL

  -- Daten
  '<< nur Standort >>'::character varying(40)	AS name1,	-- NOT NULL
  NULL::character varying(40)			AS name2,
  basis_standort.strasse::character varying(40)	AS strasse, 
  CASE
    WHEN basis_standort.hausnrzus IS NULL THEN basis_standort.hausnr::character varying(15)
    ELSE (basis_standort.hausnr || basis_standort.hausnrzus)::character varying(15)
  END						AS hausnr,
  basis_standort.plz::character varying(10)	AS plz,
  'Bielefeld'::character varying(40)		AS ort,
  'D'::character varying(3)			AS staatskennung,
  NULL::character varying(30)			AS telefon,
  NULL::character varying(30)			AS telefon_mobil,
  NULL::character varying(30)			AS fax,
  NULL::character varying(255)			AS email

FROM 
  auik.indeinl_genehmigung
    LEFT OUTER JOIN auik.basis_objekt
      ON indeinl_genehmigung.objektid = basis_objekt.objektid
    LEFT OUTER JOIN auik.basis_standort
      ON basis_objekt.standortid = basis_standort.id
    LEFT OUTER JOIN auik.basis_betreiber
      ON basis_objekt.betreiberid = basis_betreiber.id
      
WHERE 
  indeinl_genehmigung.anhang IS NOT NULL AND 
  indeinl_genehmigung._deleted = FALSE AND 
  basis_objekt._deleted = FALSE AND 
  basis_standort._deleted = FALSE AND
  basis_betreiber._deleted = FALSE AND
  NOT (basis_standort.strasse = basis_betreiber.strasse AND
       basis_standort.hausnr = basis_betreiber.hausnr AND
       basis_standort.hausnrzus IS NOT DISTINCT FROM basis_betreiber.hausnrzus AND
       basis_standort.plz = basis_betreiber.plz)
  
UNION

SELECT DISTINCT
  -- Primary Key
  (basis_betreiber.id << 16)::integer	AS adresse_nr, 
  1::integer				AS adresse_ver, 

  -- Historisierung
  '1970-01-01'::date					AS gueltig_von, 	-- NOT NULL
  NULL::date 						AS gueltig_bis,
  NULL::timestamp without time zone			AS aenderungs_datum,
  '1970-01-01 00:00:00'::timestamp without time zone	AS erfassungs_datum,	-- NOT NULL
  1::integer 						AS historien_nr,	-- NOT NULL
  true::boolean						AS ist_aktuell_jn, 	-- NOT NULL

  -- Daten
  basis_betreiber.betrname::character varying(40)	AS name1,	-- NOT NULL
  basis_betreiber.betrvorname::character varying(40)	AS name2,
  basis_betreiber.strasse::character varying(40)	AS strasse, 
  CASE
    WHEN basis_betreiber.hausnrzus IS NULL THEN basis_betreiber.hausnr::character varying(15)
    ELSE (basis_betreiber.hausnr || basis_betreiber.hausnrzus)::character varying(15)
  END							AS hausnr,
  basis_betreiber.plz::character varying(10)		AS plz,
  basis_betreiber.ort::character varying(40)		AS ort,
  basis_betreiber.plzzs::character varying(3)		AS staatskennung,
  NULL::character varying(30)				AS telefon,
  NULL::character varying(30)				AS telefon_mobil,
  NULL::character varying(30)				AS fax,
  NULL::character varying(255)				AS email

FROM 
  auik.indeinl_genehmigung
    LEFT OUTER JOIN auik.basis_objekt
      ON indeinl_genehmigung.objektid = basis_objekt.objektid
    LEFT OUTER JOIN auik.basis_betreiber
      ON basis_objekt.betreiberid = basis_betreiber.id
      
WHERE 
  indeinl_genehmigung.anhang IS NOT NULL AND 
  indeinl_genehmigung._deleted = FALSE AND 
  basis_objekt._deleted = FALSE AND 
  basis_betreiber._deleted = FALSE;
  

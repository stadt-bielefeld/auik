SELECT
  -- Primary / Foreign Key: dea_gemeinde
  '05711000'::character varying(8)		AS gemeindekennzahl,
  1::integer					AS gemeinde_ver,
  -- Primary Key
  indeinl_uebergabestelle.objektid::integer	AS uebergabestelle_lfd_nr,
  1::integer					AS uebergabestelle_ver,

  -- Historisierung
  '1970-01-01'::date					AS gueltig_von, 	-- NOT NULL
  NULL::date 						AS gueltig_bis,
  NULL::timestamp without time zone			AS aenderungs_datum,
  '1970-01-01 00:00:00'::timestamp without time zone	AS erfassungs_datum,	-- NOT NULL
  1::integer 						AS historien_nr,	-- NOT NULL
  true::boolean						AS ist_aktuell_jn, 	-- NOT NULL

  -- Daten
  -- Foreign Key: inka_betrieb
  (basis_objekt.betreiberid << 16 | basis_objekt.standortid)::integer
						AS betrieb_nr,		-- NOT NULL
  1::integer					AS betrieb_ver,		-- NOT NULL
  -- Foreign Key: inka_genehmigung
  indeinl_uebergabestelle.objektid::integer	AS genehmigung_nr,	-- NOT NULL
  1::integer					AS genehmigung_ver,	-- NOT NULL
  -- Foreign Key: dea_klaeranlage
  -- This is the official definition which seems to be rather wrong...
--  'Therebedragons!'::character varying(15)			AS anlagen_nr,		-- NOT NULL
--  1::integer							AS klaeranlagen_ver,	-- NOT NULL
--  'NIKK_BR7'::character varying(20)		AS anl_herkunft,	-- NOT NULL
  atl_klaeranlagen.dea_klaeranlage_klaeranlage_nr::integer	AS anlagen_nr,		-- NOT NULL
  1::integer							AS klaeranlagen_ver,	-- NOT NULL
--  'NIKK_BR7'::character varying(20)		AS anl_herkunft,	-- NOT NULL
  -- Foreign Key: dea_tk25
  NULL::integer					AS kartennummer,
  NULL::integer					AS tk25_ver,
-- I think this is our map as it is the only one with the name 'Bielefeld' - varify!
--  3917::integer					AS kartennummer,
--  1::integer					AS tk25_ver,
  indeinl_uebergabestelle.kanalart::integer	AS kanal_art,		-- NOT NULL
  indeinl_uebergabestelle.rechtswert::integer	AS rechtswert,
  indeinl_uebergabestelle.hochwert::integer	AS hochwert

FROM auik.indeinl_uebergabestelle
  LEFT OUTER JOIN auik.basis_objekt
    ON indeinl_uebergabestelle.objektid = basis_objekt.objektid
  LEFT OUTER JOIN auik.atl_klaeranlagen
    ON indeinl_uebergabestelle.klaeranlage = atl_klaeranlagen.id

WHERE 
  indeinl_uebergabestelle._deleted = FALSE AND
  basis_objekt._deleted = FALSE;

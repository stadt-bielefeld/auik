SELECT
  -- Primary / Foreign Key: inka_messstelle
  '05711000'::character varying(8)			AS gemeindekennzahl,
  1::integer						AS gemeinde_ver,
  inka_messstelle.uebergabestelle_lfd_nr::integer	AS uebergabestelle_lfd_nr,
  1::integer						AS uebergabestelle_ver,
  inka_messstelle.messstelle_lfd_nr::integer		AS messstelle_lfd_nr,
  1::integer						AS messstelle_ver,
  -- Primary / Foreign Key: inka_anlage
  inka_anlage.anlage_nr::integer			AS anlage_nr,
  1::integer						AS anlage_ver,

  1::integer						AS messst_anlage_ver,

  -- Historisierung
  '1970-01-01'::date					AS gueltig_von, 	-- NOT NULL
  NULL::date 						AS gueltig_bis,
  NULL::timestamp without time zone			AS aenderungs_datum,
  '1970-01-01 00:00:00'::timestamp without time zone	AS erfassungs_datum,	-- NOT NULL
  1::integer 						AS historien_nr,	-- NOT NULL
  true::boolean						AS ist_aktuell_jn 	-- NOT NULL

FROM tipi.inka_messstelle
  JOIN tipi.inka_anlage
    ON inka_messstelle.uebergabestelle_lfd_nr = inka_anlage.anlage_nr;
SELECT
  -- Primary Key
  indeinl_genehmigung.objektid::integer AS anlage_nr,
  1::integer                AS anlage_ver,

  -- Historisierung
  '1970-01-01'::date                    AS gueltig_von,     -- NOT NULL
  NULL::date                        	AS gueltig_bis,
  NULL::timestamp without time zone	AS aenderungs_datum,
  '1970-01-01 00:00:00'::timestamp without time zone    AS erfassungs_datum,    -- NOT NULL
  1::integer                        	AS historien_nr,    -- NOT NULL
  true::boolean                     	AS ist_aktuell_jn,  -- NOT NULL

  -- Daten
  -- Foreign Key: inka_betriebseinrichtung
  indeinl_genehmigung.objektid::integer AS betriebseinrichtung_nr,  -- NOT NULL
  1::integer                		AS betriebseinrichtung_ver, -- NOT NULL
  -- Foreign Key: inka_uebergabestelle
  '05711000'::character varying(8)	AS gemeindekennzahl,
  1::integer                		AS gemeinde_ver,
  indeinl_genehmigung.objektid::integer AS uebergabestelle_lfd_nr,
  1::integer                		AS uebergabestelle_ver,
  -- Foreign Key: inka_genehmigung
  indeinl_genehmigung.objektid::integer AS genehmigung_nr,  -- NOT NULL
  1::integer                		AS genehmigung_ver, -- NOT NULL
  -- Foreign Key: dea_stua
  -- TODO: verify
  2::integer				AS stua_bezirk,
  1::integer				AS stua_ver,
  -- Foreign Key: dea_wasserbehoerde
  'KR711'::character varying(5)		AS behoerden_id,	-- NOT NULL
  1::integer				AS behoerden_ver,	-- NOT NULL
  -- Foreign Key: dea_wasserbehoerde
  'KR711'::character varying(5)		AS was_behoerden_id,
  1::integer				AS was_behoerden_ver,
  -- Foreign Key: dea_adresse
  NULL::integer				AS adresse_anspr_nr,
  NULL::integer				AS adresse_anspr_ver,

  TRUE::boolean				AS gen_pflichtig_jn,	-- NOT NULL
  3::integer				AS gen_art,		-- NOT NULL
  NULL::boolean				AS anl_bauaufs_zul_jn,
  NULL::boolean				AS anl_din_de_jn,
  NULL::boolean				AS anl_ohne_zul_jn,
  NULL::boolean				AS anl_baurecht_pr_jn,
  NULL::boolean				AS anl_einzelabn_jn,
  NULL::date				AS dat_genehmigung,	-- NOT NULL
  NULL::boolean				AS befristung_jn,	-- NOT NULL
  NULL::date				AS befristung_bis,	-- NOT NULL
  'There be dragons'::character varying(30)		AS aktenzeichen,
  NULL::date				AS dat_inbetrieb,
  NULL::boolean				AS schlammfang_jn,
  NULL::boolean				AS abscheider_jn,
  NULL::boolean				AS neutralisation_jn,
  NULL::boolean				AS flockung_jn,
  NULL::boolean				AS flotation_jn,
  NULL::boolean				AS emulsionsspalt_jn,
  NULL::boolean				AS absorption_jn,
  NULL::boolean				AS sonst_abscheider_jn,
  NULL::boolean				AS ionenaustausch_jn,
  NULL::boolean				AS stripp_anlage_jn,
  NULL::boolean				AS extraktion_jn,
  NULL::boolean				AS filtration_jn,
  NULL::boolean				AS membranfiltration_jn,
  NULL::boolean				AS sieb_rechen_jn,
  NULL::boolean				AS ausgleichsbecken_jn,
  NULL::boolean				AS vorklaerung_absetz_jn,
  'There be dragons'::character varying(50)		AS sonstige_mech_verf,
  NULL::boolean				AS anaerobe_vorb_jn,
  NULL::boolean				AS tropfkoerper_jn,
  NULL::boolean				AS bel_c_eli_jn,
  NULL::boolean				AS bel_n_eli_jn,
  NULL::boolean				AS biolog_p_eli_jn,
  NULL::boolean				AS nachklaerung_jn,
  'There be dragons'::character varying(50)		AS sonstige_biol_verf,
  NULL::boolean				AS abfuhr_unbeh_jn,
  NULL::boolean				AS masch_entw_jn,
  NULL::boolean				AS stat_entw_jn,
  NULL::boolean				AS vakuumfilter_jn,
  NULL::boolean				AS kammerfilterp_jn,
  NULL::boolean				AS siebbandp_jn,
  NULL::boolean				AS zentrifuge_jn,
  NULL::boolean				AS nat_entw_jn,
  'There be dragons'::character varying(50)		AS sonstige_schlamm_beh

FROM auik.indeinl_genehmigung
  LEFT OUTER JOIN auik.basis_objekt
    ON indeinl_genehmigung.objektid = basis_objekt.objektid

WHERE 
  indeinl_genehmigung.anhang IS NOT NULL AND 
  indeinl_genehmigung.gen59 AND 
  indeinl_genehmigung._deleted = FALSE AND
  basis_objekt._deleted = FALSE AND
  basis_objekt.inaktiv = FALSE;

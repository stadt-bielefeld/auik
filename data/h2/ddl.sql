

CREATE SCHEMA awsv;


CREATE SCHEMA basis;


CREATE SCHEMA elka;


CREATE SCHEMA gis;


CREATE SCHEMA indeinl;


CREATE SCHEMA labor;


CREATE SCHEMA oberflgw;


CREATE TABLE awsv.abfuellflaeche (
    id integer auto_increment NOT NULL,
    behaelterid integer NOT NULL,
    eoh boolean,
    ef boolean,
    abfsaniert boolean,
    abfneuerstellt boolean,
    bodenflaechenausf character varying(255),
    beschbodenfl character varying(255),
    dicke real,
    guete character varying(255),
    fugenmaterial character varying(255),
    beschfugenmat character varying(255),
    niederschlagschutz character varying(255),
    abscheidervorh boolean,
    beschableitung character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    groesse integer
);



CREATE TABLE awsv.abscheider (
    id integer auto_increment NOT NULL,
    behaelterid integer NOT NULL,
    sfhersteller character varying(255),
    sftyp character varying(255),
    sfvolumen character varying(255),
    sfmaterial character varying(255),
    sfbeschichtung character varying(255),
    abhersteller character varying(255),
    abtyp character varying(255),
    abpruefz character varying(255),
    abmaterial character varying(255),
    abbeschichtung character varying(255),
    abnenngr character varying(255),
    zuldn character varying(255),
    zulmaterial character varying(255),
    zullaenge character varying(255),
    verbdn character varying(255),
    verbmaterial character varying(255),
    verblaenge character varying(255),
    sondn character varying(255),
    sonmaterial character varying(255),
    sonlaenge character varying(255),
    oelspeichervol character varying(255),
    kompaktanlage boolean,
    sf boolean,
    kkl1 boolean,
    lfkl2 boolean,
    ps boolean,
    ueberhausr boolean,
    waschanlvorh boolean,
    abgabe boolean,
    hlzapfanl boolean,
    belvonlagerbh boolean,
    rueckhalteausr boolean,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE awsv.anlagenarten (
    id integer auto_increment  NOT NULL,
    anlagenart character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE awsv.anlagenchrono (
    id integer auto_increment NOT NULL,
    datum timestamp,
    sachverhalt character varying(255),
    wv timestamp,
    behaelterid integer NOT NULL,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    abgeschlossen boolean DEFAULT false
);



CREATE SEQUENCE awsv.awsv_fachdaten_behaelterid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



CREATE TABLE awsv.behaelterart (
    id integer auto_increment NOT NULL,
    behaelterart character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE awsv.fachdaten (
    behaelterid integer auto_increment NOT NULL,
    objektid integer NOT NULL,
    herstellnr character varying(255),
    hersteller character varying(255),
    datuminbetriebnahme timestamp,
    datumerfassung timestamp,
    datumaenderung timestamp,
    datumgenehmigung timestamp,
    anlagenart character varying(255),
    behaelterart character varying(255),
    material character varying(255),
    fluessigkeit character varying(255),
    vbfeinstufung character varying(255),
    menge double precision,
    wgk character varying,
    gefaehrdungsstufe character varying(255),
    baujahr integer,
    doppelwandig boolean,
    leckanzeige boolean,
    auffangraum boolean,
    grenzwertgeber boolean,
    leckschutzauskleidung boolean,
    kellerlagerung boolean,
    innenbeschichtung boolean,
    beschreibung_a text,
    beschreibung_s text,
    oberirdisch boolean,
    unterirdisch boolean,
    saugleitung boolean,
    rohr_kathodenschutz boolean,
    aus_kupfer boolean,
    aus_stahl boolean,
    mit_schutzrohr boolean,
    beschreibung_r text,
    pruefturnus double precision,
    angemahntkz boolean,
    mahnfrist timestamp,
    wiedervorlage timestamp,
    stillegungsdatum timestamp,
    bemerkungen text,
    ausfuehrung character varying(255),
    pruefumfang character varying(255),
    verwendung character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    aus_hdpe boolean,
    druckleitung boolean,
    schutz_sensor boolean,
    schutz_folie boolean,
    schutz_antiheber boolean,
    aktenzeichen character varying
);



CREATE TABLE awsv.fluessigkeit (
    id integer auto_increment NOT NULL,
    fluessigkeit character varying(255),
    idland integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE awsv.gebuehrenarten (
    id integer auto_increment NOT NULL,
    gebuehrenart character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE awsv.gefaehrdungsstufen (
    id integer auto_increment NOT NULL,
    gefaehrdungsstufen character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE awsv.jgs (
    id integer auto_increment NOT NULL,
    behaelterid integer,
    lagerflaeche integer,
    gewaesser_abstand integer,
    gewaesser_name character varying,
    brunnen_abstand integer,
    tierhaltung character varying,
    seitenwaende boolean,
    wandhoehe double precision,
    bodenplatte character varying,
    ueberdachung boolean,
    auffangbeh character varying,
    volumen_auffangbeh double precision,
    rohrleitung character varying,
    dichtheitspruefung timestamp,
    drainage boolean,
    fuellanzeiger boolean,
    schieber boolean,
    abdeckung boolean,
    leitung_geprueft boolean,
    _enabled boolean,
    _deleted boolean
);



CREATE TABLE awsv.kontrollen (
    id integer auto_increment NOT NULL,
    grundderpruefung character varying(255),
    pruefturnus real,
    pruefdatum timestamp,
    pruefer character varying(255),
    pruefungabgeschlossen boolean,
    nachpruefung boolean,
    nachpruefdatum timestamp,
    nachpruefer character varying(255),
    naechstepruefung timestamp,
    pruefergebnis character varying(255),
    behaelterid integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE awsv.material (
    id integer auto_increment NOT NULL,
    material character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);




CREATE TABLE awsv.pruefer (
    id integer auto_increment NOT NULL,
    pruefer character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE awsv.pruefergebniss (
    id integer auto_increment NOT NULL,
    pruefergebnis character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE awsv.standortgghwsg (
    id integer auto_increment NOT NULL,
    standortgg character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE awsv.verwaltungsgebuehren (
    id integer auto_increment NOT NULL,
    behaelterid integer,
    gebuehrenart integer,
    datum timestamp,
    betrag real,
    abschnitt character varying(255),
    kassenzeichen character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE awsv.verwaltungsverf (
    id integer auto_increment NOT NULL,
    datum timestamp,
    massnahme character varying(255),
    wiedervorlage timestamp,
    wvverwverf boolean,
    behaelterid integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE awsv.vbfeinstufung (
    id integer auto_increment NOT NULL,
    vbfeinstufung character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE awsv.verwmassnahmen (
    id integer auto_increment NOT NULL,
    massnahmen character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE awsv.wassereinzugsgebiet (
    id integer auto_increment NOT NULL,
    ezgbname character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE basis.standort (
    id integer auto_increment NOT NULL,
    inhaberid integer,
    lageid integer,
    _enabled boolean DEFAULT true,
    _deleted boolean DEFAULT false,
    industrieabwasser_tog boolean,
    niederschlagswasser_tog boolean,
    nur_sb_tog boolean,
    igl_id integer,
    e32 real,
    n32 real,
    bezeichnung character varying,
    revidatum timestamp,
    revihandz character varying(255),
    oberflid integer
);



CREATE TABLE basis.adresse (
    id integer auto_increment NOT NULL,
    strasse character varying(255),
    hausnr integer,
    hausnrzus character varying(255),
    plzzs character varying(255),
    plz character varying(255),
    ort character varying(255),
    bemerkungen character varying(255),
    revidatum timestamp,
    revihandz character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    erstell_dat date,
    igl_id integer,
    oberflid integer,
    flur character varying(255),
    flurstueck character varying(255),
    gemarkungid integer,
    ueberschgeb boolean DEFAULT false,
    entgebid character varying(255),
    strasseeigent character varying(255),
    wassermenge integer,
    sachbe33rav character varying(255),
    sachbe33hee character varying(255),
    standortgegebid integer,
    wassereinzgebid integer
);



CREATE TABLE basis.objekt (
    id integer auto_increment NOT NULL,
    uschistdid integer,
    beschreibung character varying(255),
    wiedervorlage timestamp,
    erfassungsdatum timestamp,
    gueltig_von timestamp,
    aenderungsdatum timestamp,
    gueltig_bis timestamp,
    inaktiv boolean DEFAULT false NOT NULL,
    betreiberid integer,
    objektartid integer,
    lageid integer,
    prioritaet character varying,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    abwasserfrei boolean DEFAULT true,
    standortid integer,
    sachbearbeiter integer,
    elkarelevant boolean DEFAULT false
);



CREATE TABLE basis.objektchrono (
    id integer auto_increment NOT NULL,
    datum timestamp,
    sachverhalt character varying(255),
    wv timestamp,
    sachbearbeiter character varying(255),
    objektid integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    pfad character varying
);



CREATE TABLE basis.objektverknuepfung (
    id integer auto_increment NOT NULL,
    ist_verknuepft_mit integer,
    objekt integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE basis.sachbearbeiter (
    kennummer character varying(255) NOT NULL,
    name character varying(255),
    zeichen character varying(255),
    zimmer character varying(255),
    telefon character varying(255),
    email character varying(255),
    gehoertzuarbeitsgr character varying,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer auto_increment NOT NULL
);



CREATE TABLE basis.bezeichnung (
    id integer auto_increment NOT NULL,
    gruppe character varying(255) NOT NULL,
    bezeichnung character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE basis.gemarkung (
    id integer auto_increment NOT NULL,
    gemarkung character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE basis.inhaber (
    id integer auto_increment NOT NULL,
    adresseid integer,
    anrede character varying(255),
    name character varying(255),
    vorname character varying(255),
    namezus character varying(255),
    namebetrbeauf character varying(255),
    vornamebetrbeauf character varying(255),
    telefon character varying(255),
    telefax character varying(255),
    email character varying(255),
    bemerkungen character varying(255),
    revidatum timestamp,
    revihandz character varying(255),
    kassenzeichen character varying(255),
    wirtschaftszweigid integer,
    auik_wz_code character varying(255),
    wassermenge integer,
    datenschutz_awsv boolean DEFAULT false,
    datenschutz_esatzung boolean DEFAULT false,
    datenschutz_whg boolean DEFAULT false,
    erstell_dat date,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    oberflid integer
);



CREATE TABLE basis.objektarten (
    id integer auto_increment NOT NULL,
    objektart character varying(255),
    abteilung character varying(255),
    kategorie character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE basis.prioritaet (
    standort_id integer NOT NULL,
    betreiber_id integer NOT NULL,
    prioritaet integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE basis.strassen (
    id integer auto_increment NOT NULL,
    strasse character varying(255),
    plz character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    ort character varying
);



CREATE VIEW basis.view_two_way_objektverknuepfung AS
 SELECT basis_objektverknuepfung.id,
    basis_objektverknuepfung.ist_verknuepft_mit,
    basis_objektverknuepfung.objekt,
    basis_objektverknuepfung._enabled,
    basis_objektverknuepfung._deleted
   FROM basis.objektverknuepfung basis_objektverknuepfung
UNION
 SELECT (- basis_objektverknuepfung.id) AS id,
    basis_objektverknuepfung.objekt AS ist_verknuepft_mit,
    basis_objektverknuepfung.ist_verknuepft_mit AS objekt,
    basis_objektverknuepfung._enabled,
    basis_objektverknuepfung._deleted
   FROM basis.objektverknuepfung basis_objektverknuepfung
  ORDER BY 2;



CREATE TABLE basis.wirtschaftszweig (
    id integer auto_increment NOT NULL,
    wirtschaftszweig character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE VIEW elka._q_z AS
 SELECT DISTINCT objektverknuepfung.ist_verknuepft_mit AS quelle,
    objektverknuepfung.objekt AS ziel
   FROM basis.objektverknuepfung
  WHERE (objektverknuepfung._deleted = false)
UNION
 SELECT DISTINCT objektverknuepfung.objekt AS quelle,
    objektverknuepfung.ist_verknuepft_mit AS ziel
   FROM basis.objektverknuepfung
  WHERE (objektverknuepfung._deleted = false);



CREATE TABLE elka.aba (
    id integer auto_increment NOT NULL,
    objektid integer,
    anspr_adr_id integer,
    aktual_dat date,
    erstell_dat date,
    herkunft character varying,
    bezeichnung character varying,
    inbetrieb_dat date,
    stillgelegt_dat date,
    genehmpflichtig_toc boolean,
    e32 integer,
    n32 integer,
    sonst_zul_opt integer,
    verfahren_nr integer,
    wartungsvertrag_toc boolean,
    einzelabnahme_toc boolean,
    _enabled boolean,
    _deleted boolean
);



CREATE TABLE elka.abaverfahren (
    nr integer NOT NULL,
    aktual_dat date,
    erstell_dat date,
    bezeichnung character varying
);



CREATE TABLE elka.anfallstelle (
    id integer auto_increment NOT NULL,
    objektid integer,
    seq_id integer,
    aktual_dat date,
    erstell_dat date,
    anhang_id character varying,
    anwendungsbereich character varying,
    bezeichnung character varying,
    stillgelegt_am date,
    abwa_beschaff_opt integer,
    betriebsweise_opt integer,
    _enabled boolean,
    _deleted boolean,
    max_vol_tag numeric(11,2),
    max_vol_stunde numeric(11,2),
    vol_jahr integer,
    external_nr character varying(50),
    anlagenart character varying,
    bemerkungen character varying,
    abflussmenge numeric(11,2),
    bef_flaeche integer,
    nw_her_bereich_opt integer
);



CREATE TABLE elka.anhang (
    sl_nr integer NOT NULL,
    anh_ma_sl_nr integer,
    anhang_id character varying,
    anh_gueltig_von date,
    anh_gueltig_bis date,
    anh_regelwerk character varying,
    anh_text character varying,
    herkunft character varying,
    erstell_dat date,
    aktual_dat date,
    zeitstempel timestamp
);



CREATE TABLE elka.wasserrecht (
    objektid integer NOT NULL,
    bemerkungen character varying(255),
    erstellungs_datum date,
    aenderungs_datum date,
    antrag_datum date,
    befristet boolean,
    befristet_bis date,
    anhang integer,
    gen_menge integer,
    gen58 boolean,
    gen59 boolean,
    selbstueberw boolean,
    e_satzung boolean,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    uebergabestelle_e32 integer,
    uebergabestelle_n32 integer,
    id integer auto_increment NOT NULL,
    recht_behoerden_id character varying(16),
    wr_beschreibung character varying(50),
    recht_art_opt integer,
    aktenzeichen character varying(45),
    bemerkung character varying(1000),
    wasserbuch_id character varying(30),
    beer_wr_nr character varying(10),
    erstell_dat timestamp,
    aktual_dat timestamp,
    gen8 boolean
);



CREATE VIEW elka.e_adresse AS
 SELECT DISTINCT inhaber.id AS nr,
    LEFT((inhaber.anrede)::text, 30) AS anrede,
    LEFT((inhaber.name)::text, 40) AS name1,
    LEFT((((inhaber.vorname)::text || ' '::text) || (inhaber.namezus)::text), 40) AS name2,
    adresse.strasse,
    adresse.hausnr,
    adresse.hausnrzus,
    adresse.plz,
    adresse.plzzs AS staatskennung_zst,
    adresse.ort,
    adresse.gemarkungid,
    adresse.flur,
    adresse.flurstueck,
    LEFT((inhaber.telefon)::text, 30) AS telefon,
    inhaber.telefax AS fax,
    inhaber.email,
    1 AS typ,
    false AS person_tog,
    '1970-01-01 00:00:00'::timestamp AS erstell_dat,
        CASE
            WHEN (adresse.revidatum IS NOT NULL) THEN adresse.revidatum
            ELSE '1970-01-01 00:00:00'::timestamp
        END AS aktual_dat,
    'ELKA_KR711'::text AS herkunft
   FROM basis.adresse,
    basis.inhaber,
    basis.objekt,
    elka.wasserrecht,
    basis.standort
  WHERE ((adresse.id = inhaber.adresseid) AND (inhaber.id = standort.inhaberid) AND (objekt.id = wasserrecht.objektid) AND (standort.id = objekt.standortid) AND (wasserrecht.gen59 = true) AND (objekt._deleted = false) AND (objekt.inaktiv = false))
UNION
 SELECT DISTINCT inhaber.id AS nr,
    LEFT((inhaber.anrede)::text, 30) AS anrede,
    LEFT((inhaber.name)::text, 40) AS name1,
    LEFT((((inhaber.vorname)::text || ' '::text) || (inhaber.namezus)::text), 40) AS name2,
    adresse.strasse,
    adresse.hausnr,
    adresse.hausnrzus,
    adresse.plz,
    adresse.plzzs AS staatskennung_zst,
    adresse.ort,
    adresse.gemarkungid,
    adresse.flur,
    adresse.flurstueck,
    LEFT((inhaber.telefon)::text, 30) AS telefon,
    inhaber.telefax AS fax,
    inhaber.email,
    1 AS typ,
    false AS person_tog,
    '1970-01-01 00:00:00'::timestamp AS erstell_dat,
        CASE
            WHEN (adresse.revidatum IS NOT NULL) THEN adresse.revidatum
            ELSE '1970-01-01 00:00:00'::timestamp
        END AS aktual_dat,
    'ELKA_KR711'::text AS herkunft
   FROM basis.adresse,
    basis.inhaber,
    basis.objekt,
    elka.wasserrecht
  WHERE ((adresse.id = inhaber.adresseid) AND (inhaber.id = objekt.betreiberid) AND (objekt.id = wasserrecht.objektid) AND (wasserrecht.gen59 = true) AND (objekt._deleted = false) AND (objekt.inaktiv = false));



CREATE VIEW elka.e_standort AS
 SELECT standort.id AS nr,
    '05711000'::character varying(8) AS gemeindekennzahl,
    gemarkung.gemarkung,
    e_adresse.flur,
    e_adresse.flurstueck AS flurstuecke,
    standort.e32,
    standort.n32,
    standort.industrieabwasser_tog,
    standort.niederschlagswasser_tog,
    false AS kommunale_ka_tog,
    false AS kleika_tog,
    standort.nur_sb_tog,
    e_adresse.erstell_dat,
    e_adresse.aktual_dat,
    'ELKA_KR711'::text AS herkunft,
    e_adresse.nr AS adr_nr
   FROM elka.e_adresse,
    basis.standort,
    basis.gemarkung
  WHERE ((e_adresse.nr = standort.inhaberid) AND (e_adresse.gemarkungid = gemarkung.id));



CREATE VIEW elka.e_abwasserbehandlungsanlage AS
 SELECT aba.objektid AS nr,
    e_standort.nr AS standort_nr,
    e_standort.adr_nr AS sto_adr_nr,
    objekt.betreiberid AS betreib_adr_nr,
        CASE
            WHEN ((objekt.beschreibung IS NULL) OR ((objekt.beschreibung)::text = ''::text)) THEN '>> Bezeichnung folgt später <<'::character varying
            ELSE (LEFT((objekt.beschreibung)::text, 64))::character varying
        END AS bezeichnung,
    e_standort.e32,
    e_standort.n32,
    false AS wartungsvertrag_tog,
    aba.genehmpflichtig_toc AS genehmpflichtig_tog,
    false AS einzelabnahme_tog,
    objekt.beschreibung AS bemerkung,
        CASE
            WHEN (objekt.aenderungsdatum IS NOT NULL) THEN objekt.aenderungsdatum
            ELSE '1970-01-01 00:00:00'::timestamp
        END AS aktual_dat,
        CASE
            WHEN (objekt.erfassungsdatum IS NOT NULL) THEN objekt.erfassungsdatum
            ELSE '1970-01-01 00:00:00'::timestamp
        END AS erstell_dat,
    'ELKA_KR711'::text AS herkunft
   FROM elka.e_adresse,
    elka.e_standort,
    basis.objekt,
    elka.aba
  WHERE ((objekt.standortid = e_standort.nr) AND (objekt.betreiberid = e_adresse.nr) AND (aba.objektid = objekt.id) AND (objekt._deleted = false) AND (objekt.inaktiv = false) AND (objekt.elkarelevant = true));



CREATE TABLE elka.e_anfallstelle (
    nr integer,
    standort_nr integer,
    adresse integer,
    anhang_id character varying,
    abwa_beschaff_opt integer,
    bezeichnung character varying,
    bemerkung character varying(255),
    aufz_betrieb_tog boolean,
    aktual_dat date,
    erstell_dat date,
    herkunft text
);



CREATE VIEW elka.e_betrieb AS
 SELECT DISTINCT e_standort.nr,
    e_standort.nr AS standort_nr,
    e_adresse.nr AS wr_adr_nr,
    e_adresse.name1 AS bezeichnung,
    false AS suevkan_tog,
    e_standort.e32,
    e_standort.n32,
    e_standort.erstell_dat,
    e_standort.aktual_dat,
    e_standort.herkunft
   FROM elka.e_adresse,
    basis.objekt,
    elka.e_standort
  WHERE ((e_adresse.nr = objekt.betreiberid) AND (e_standort.nr = objekt.standortid) AND (objekt.objektartid = 42));



CREATE TABLE elka.einleitungsstelle (
    id integer auto_increment NOT NULL,
    objektid integer,
    aktual_dat date,
    erstell_dat date,
    herkunft character varying,
    bezeichnung character varying,
    gewaessername_alias_3 character varying,
    gewaessername_ns character varying,
    stillgelegt_am date,
    typ_indirekt boolean,
    typ_ind_gew_direkt boolean,
    typ_komm_trenn boolean,
    typ_privat_trenn boolean,
    typ_sonstige boolean,
    typ_ausserort_strasseneinl boolean,
    stationierung_ns_3 double precision,
    einzugsgebiet double precision,
    stationierung_st_3 double precision,
    abgaberel_einl smallint,
    e32 integer,
    n32 integer,
    kanal_art_opt smallint,
    stationierung_3_opt smallint,
    schutzzone_opt smallint,
    _deleted boolean,
    _enabled boolean,
    typ_indirekteinleitung_tog boolean DEFAULT false NOT NULL,
    typ_indus_gewerb_direkteinleitung_tog boolean DEFAULT false NOT NULL,
    typ_komm_nw_misch_tog boolean DEFAULT false NOT NULL,
    typ_komm_nw_trenn_tog boolean DEFAULT false NOT NULL,
    typ_nw_privat_trenn_tog boolean DEFAULT false NOT NULL,
    typ_indus_gewerb_nw_misch_tog boolean DEFAULT false NOT NULL,
    typ_indus_gewerb_nw_trenn_tog boolean DEFAULT false NOT NULL,
    typ_grubenwasser_tog boolean DEFAULT false NOT NULL,
    typ_kleinklaeranlage_tog boolean DEFAULT false NOT NULL,
    typ_komm_ka_tog boolean DEFAULT false NOT NULL,
    typ_ausseroertliche_strasseneinleitung_tog boolean DEFAULT false NOT NULL,
    typ_sonstige_tog boolean DEFAULT false NOT NULL,
    abgaberelevante_elt_opt integer,
    gewaesser_3_id character varying(20),
    ofwk_nrw_id character varying(24),
    ofwk_nrw_auflage_id character varying(10),
    ofwk_nrw_opt integer,
    see_ns_3_id character varying(20),
    seename_alias_3 character varying(60),
    see_auflage_3_id character varying(10),
    see_3_id character varying(20),
    einleitungsstellen_id character varying(9),
    gewaesser_auflage_3_id character varying(10),
    fluss_gebiet_3_id character varying(20),
    bemerkung character varying(1000),
    fluss_auflage_3_id character varying(10),
    gewaessername_ns_3 character varying(64),
    gewaesser_ns_3_id character varying(20),
    gwk_id character varying(15),
    ka_nicht_in_nrw_tog boolean DEFAULT false NOT NULL,
    ka_name_ausserhalb_nrw character varying(30),
    external_nr character varying(50),
    nr integer,
    entf_einl_stat_gew double precision,
    mwq double precision,
    hq1 double precision,
    bemerkung_gew_daten character varying,
    abw_ag_einl character varying,
    gew_name_stadt character varying,
    gew_kennz_stadt character varying,
    hwr_massnahme character varying,
    gewname_stat character varying,
    gewkennz_stat character varying
);



CREATE VIEW elka.e_einleitungsstelle AS
 SELECT DISTINCT einleitungsstelle.objektid AS nr,
    objekt.standortid AS standort_nr,
    true AS typ_indirekteinleitung_tog,
    einleitungsstelle.bezeichnung,
    einleitungsstelle.e32,
    einleitungsstelle.n32,
    einleitungsstelle.kanal_art_opt,
    false AS ka_nicht_in_nrw_tog,
    einleitungsstelle.aktual_dat,
    einleitungsstelle.erstell_dat,
    'ELKA_KR711'::text AS herkunft
   FROM elka.e_adresse,
    elka.e_standort,
    basis.objekt,
    elka.einleitungsstelle
  WHERE ((objekt.standortid = e_standort.nr) AND (objekt.betreiberid = e_adresse.nr) AND (einleitungsstelle.objektid = objekt.id) AND (objekt._deleted = false) AND (objekt.inaktiv = false) AND (objekt.elkarelevant = true));



CREATE TABLE oberflgw.entwaesserungsgrundstueck (
    nr integer NOT NULL,
    objekt_id bigint NOT NULL,
    erl_frei_el_tog boolean DEFAULT false NOT NULL,
    regenspende numeric(10,1),
    bemerkung character varying(1000),
    regenhaeufigkeit numeric(10,1),
    regendauer integer,
    gr_entw_gebiet integer,
    dtv_wert numeric(10,1),
    wasserableitungsstrecke_opt integer,
    name_etw_gebiet character varying,
    erstell_dat timestamp,
    einl_bereich_opt integer,
    abwbeskon_nr character varying(30),
    einbauart_opt integer,
    aktual_dat timestamp,
    adr_nr bigint,
    wasserecht_nr bigint,
    external_nr character varying(50),
    _enabled boolean,
    _deleted boolean,
    wo_tog boolean,
    mi_tog boolean,
    ge_tog boolean,
    gi_tog boolean,
    gem_tog boolean,
    str_tog boolean,
    parkplatz_tog boolean
);



CREATE VIEW elka.e_entwaesserungsgrundstueck AS
 SELECT entwaesserungsgrundstueck.nr,
    entwaesserungsgrundstueck.erl_frei_el_tog,
    entwaesserungsgrundstueck.regenspende,
    entwaesserungsgrundstueck.regenhaeufigkeit,
    objekt.beschreibung AS bemerkung,
    entwaesserungsgrundstueck.regendauer,
    entwaesserungsgrundstueck.gr_entw_gebiet,
    objekt.standortid,
    entwaesserungsgrundstueck.dtv_wert,
    entwaesserungsgrundstueck.wasserableitungsstrecke_opt,
    entwaesserungsgrundstueck.name_etw_gebiet,
    entwaesserungsgrundstueck.erstell_dat,
    entwaesserungsgrundstueck.einl_bereich_opt,
    entwaesserungsgrundstueck.abwbeskon_nr,
    entwaesserungsgrundstueck.einbauart_opt,
    entwaesserungsgrundstueck.aktual_dat,
    objekt.betreiberid,
    entwaesserungsgrundstueck.wasserecht_nr,
    'ELKA_KR711'::text AS herkunft,
    entwaesserungsgrundstueck.external_nr
   FROM elka.e_adresse,
    elka.e_standort,
    basis.objekt,
    oberflgw.entwaesserungsgrundstueck
  WHERE ((objekt.standortid = e_standort.nr) AND (objekt.betreiberid = e_adresse.nr) AND (entwaesserungsgrundstueck.objekt_id = objekt.id) AND (objekt._deleted = false));



CREATE TABLE labor.messstelle (
    objektid integer NOT NULL,
    beschreibung character varying(255),
    nr_probepkt integer,
    firmen_id integer,
    ka_id integer,
    art_id integer,
    branche character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer auto_increment NOT NULL,
    sachbearbeiter integer
);



CREATE VIEW elka.e_messstelle AS
 SELECT messstelle.id AS nr,
    e_standort.nr AS standort_nr,
    e_standort.e32,
    e_standort.n32,
        CASE
            WHEN ((objekt.beschreibung IS NULL) OR ((objekt.beschreibung)::text = ''::text)) THEN '>> Bezeichnung folgt später <<'::character varying
            ELSE (LEFT((objekt.beschreibung)::text, 64))::character varying
        END AS bezeichnung,
    3 AS typ_opt,
    0 AS art_messung_opt,
    messstelle.beschreibung AS bemerkung,
        CASE
            WHEN (objekt.aenderungsdatum IS NOT NULL) THEN objekt.aenderungsdatum
            ELSE '1970-01-01 00:00:00'::timestamp
        END AS aktual_dat,
        CASE
            WHEN (objekt.erfassungsdatum IS NOT NULL) THEN objekt.erfassungsdatum
            ELSE '1970-01-01 00:00:00'::timestamp
        END AS erstell_dat,
    'ELKA_KR711'::text AS herkunft
   FROM elka.e_adresse,
    elka.e_standort,
    basis.objekt,
    labor.messstelle
  WHERE ((objekt.standortid = e_standort.nr) AND (objekt.betreiberid = e_adresse.nr) AND (messstelle.objektid = objekt.id) AND (messstelle.art_id = 2) AND (objekt._deleted = false) AND (objekt.inaktiv = false) AND (objekt.elkarelevant = true));



CREATE TABLE labor.probenahme (
    id integer auto_increment NOT NULL,
    kennummer character varying(255),
    art character varying(255),
    datum_der_entnahme timestamp,
    zeit_anfang time,
    bis_datum timestamp,
    zeit_der_entnahmen character varying(255),
    einwaage real,
    v_m3 character varying(255),
    fahrer character varying(255),
    objekt_nr character varying(255),
    datum_icp timestamp,
    sonderparameter character varying(255),
    bemerkung character varying(255),
    an_360x11 timestamp,
    ueberschreitung character varying(255),
    anzahlbeteiligte integer,
    uhrzeitbeginn character varying(255),
    uhrzeitende character varying(255),
    fahrtzeit character varying(255),
    bescheid timestamp,
    kosten double precision,
    massnahmen character varying(255),
    bezeichnung character varying(255),
    messstid integer,
    status integer,
    uschivorg integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    qb_ausschliessen boolean DEFAULT false,
    sachbearbeiter integer
);



CREATE VIEW elka.e_probenahme AS
 SELECT probenahme.id AS nr,
    e_messstelle.nr AS msst_nr,
    probenahme.bezeichnung AS beschreibung,
    probenahme.datum_der_entnahme AS probe_dat,
    probenahme.kennummer AS probe_id,
    probenahme.zeit_der_entnahmen AS uhr_stich,
        CASE
            WHEN ((probenahme.kennummer)::text = '2%'::text) THEN true
            ELSE false
        END AS selbstueberw_tog,
    probenahme.bemerkung,
    probenahme.datum_der_entnahme AS aktual_dat,
    probenahme.datum_der_entnahme AS erstell_dat,
    'ELKA_KR711'::text AS herkunft
   FROM labor.probenahme,
    elka.e_messstelle
  WHERE (e_messstelle.nr = probenahme.messstid);



CREATE TABLE elka.map_elka_einheit (
    id integer auto_increment NOT NULL,
    name character varying,
    id_elka integer,
    id_auik integer
);



CREATE TABLE elka.map_elka_stoff (
    id integer auto_increment NOT NULL,
    name character varying,
    id_elka integer,
    id_auik character varying
);



CREATE TABLE labor.analyseposition (
    id integer auto_increment NOT NULL,
    grkl character varying(255),
    wert real DEFAULT 0 NOT NULL,
    analyse_von character varying(255),
    bericht character varying(255),
    normwert double precision,
    einheiten_id integer,
    parameter_id character varying(255) NOT NULL,
    probenahme_id integer NOT NULL,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    normwert_neu double precision,
    gruppe_dev_id character varying(3),
    regelwerk_id character varying(2),
    varianten_id character varying(1),
    methode_id integer
);



CREATE VIEW elka.e_probenahme_ueberwachungsergeb AS
 SELECT analyseposition.id AS nr,
    analyseposition.probenahme_id AS probenahme_nr,
    map_elka_stoff.id_elka AS stoff_nr,
    '000'::character varying(3) AS gruppe_dev_id,
    '00'::character varying(2) AS regelwerk_id,
    '0'::character varying(1) AS varianten_id,
    '1'::character varying(3) AS trenn_nr_opt,
    'A'::character varying(1) AS prob_schluessel_id,
        CASE
            WHEN ((analyseposition.grkl)::text = '<'::text) THEN 1
            WHEN ((analyseposition.grkl)::text = '>'::text) THEN 2
            WHEN ((analyseposition.grkl)::text = '='::text) THEN 3
            ELSE NULL::integer
        END AS messergebnis_text_opt,
    round((analyseposition.wert)::numeric, 3) AS wert,
    map_elka_einheit.id_elka
   FROM elka.e_probenahme,
    labor.analyseposition,
    elka.map_elka_stoff,
    elka.map_elka_einheit
  WHERE ((e_probenahme.nr = analyseposition.probenahme_id) AND (analyseposition.einheiten_id = map_elka_einheit.id_auik) AND ((analyseposition.parameter_id)::text = (map_elka_stoff.id_auik)::text));



CREATE TABLE oberflgw.sonderbauwerk (
    objekt_id bigint NOT NULL,
    gemeinde_id character varying(8),
    bezeichnung character varying(60),
    kurzbeschreibung character varying(60),
    entw_einzugsgeb_opt integer,
    typ_opt integer,
    inbetriebnahme integer,
    stillgelegt_am date,
    wiederinbetr_dat date,
    anspr_adr_nr bigint,
    bemerkung character varying(1000),
    e32 integer,
    n32 integer,
    in_nrw_tog boolean DEFAULT false NOT NULL,
    industr_tog boolean DEFAULT false NOT NULL,
    name_ausserhalb_nrw character varying(30),
    k_entw_gebiet numeric(11,2),
    bef_flaeche_egebiet numeric(11,2),
    abflussbeiwert numeric(11,2),
    bef_grad numeric(11,2),
    undurch_flaeche numeric(11,2),
    beckentyp_opt integer,
    rohrspeicher_tog boolean DEFAULT false NOT NULL,
    bauweise_opt integer,
    drossel_opt integer,
    so_drossel character varying(80),
    beckensteuerung_opt integer,
    a_rei_einrich_tog boolean DEFAULT false NOT NULL,
    messart_tog boolean DEFAULT false NOT NULL,
    drossel_tog boolean DEFAULT false NOT NULL,
    fuellstand_tog boolean DEFAULT false NOT NULL,
    entlastungswasser_tog boolean DEFAULT false NOT NULL,
    entlastungsdauer_tog boolean DEFAULT false NOT NULL,
    entlastungshaeufig_tog boolean DEFAULT false NOT NULL,
    fern_mess_tog boolean DEFAULT false NOT NULL,
    niederschlag_tog boolean DEFAULT false NOT NULL,
    fern_stoer_tog boolean DEFAULT false NOT NULL,
    hwfrei_tog boolean DEFAULT false NOT NULL,
    einstau_haeufig numeric(11,2),
    hw_einrichtung_tog boolean DEFAULT false NOT NULL,
    hw_rueckstau_tog boolean DEFAULT false NOT NULL,
    hw_schieber_tog boolean DEFAULT false NOT NULL,
    hw_pumpwerk_tog boolean DEFAULT false NOT NULL,
    hw_weitere_tog boolean DEFAULT false NOT NULL,
    hw_sonst_text character varying(80),
    bemes_weiterg_tog boolean DEFAULT false NOT NULL,
    bemessung_text character varying(80),
    anordnung_opt integer,
    wasserrecht_genehmigung_nr bigint,
    beckenart_opt integer,
    beckentiefe numeric(10,1),
    beh_flaeche_1u2 numeric(11,2),
    beh_flaeche_2u3 numeric(11,2),
    betriebsart_opt integer,
    csb integer,
    drosselabfluss numeric(10,1),
    dross_abfluss_opt integer,
    dross_ueber_tog boolean DEFAULT false NOT NULL,
    entlastungsart_opt integer,
    entleerungszeit numeric(10,1),
    flaechenbeschickung numeric(10,1),
    fliesszeit integer,
    fremd_abfluss numeric(10,2),
    funktion_opt integer,
    kanal_vol integer,
    qrkrit numeric(11,2),
    kritisch_misch numeric(10,1),
    max_h_schmutzabfluss numeric(10,2),
    max_h_trocken numeric(10,1),
    min_dr_abfluss numeric(10,1),
    misch_ueberlauf integer,
    n_mindest_v integer,
    n_spez_vol integer,
    neigung numeric(11,2),
    rdrosseldurchfluss numeric(10,1),
    regenabfl_dross numeric(10,1),
    regenabfl_entl numeric(10,1),
    regenabfluss numeric(10,2),
    regenabfluss_dr numeric(10,1),
    regenspende numeric(10,1),
    rfilterflaeche integer,
    rfiltergeschwin numeric(12,3),
    rfiltersubstrat_h numeric(11,2),
    rhyd_wirkungsgrad integer,
    rjahr_ueh numeric(11,2),
    rkrit numeric(10,1),
    rm_filterbelastung numeric(10,1),
    rspez_filtervol numeric(11,2),
    rstauvolumen integer,
    rvol_slamelle integer,
    rw_krit_abfluss numeric(10,1),
    rw_krit_misch numeric(10,1),
    rw_mindest_misch integer,
    schmutz_abfluss numeric(10,2),
    sku_anstroem numeric(10,1),
    sku_mindest_svol integer,
    sku_spez_vol integer,
    spez_beckenvol integer,
    spez_speicher integer,
    trocken_w_abfluss numeric(10,1),
    speichervolumen integer,
    w_oberflaeche integer,
    erstell_dat timestamp,
    aktual_dat timestamp,
    external_nr character varying(50),
    nr integer NOT NULL,
    _enabled boolean,
    _deleted boolean
);



CREATE VIEW elka.e_sonderbauwerk AS
 SELECT sonderbauwerk.nr,
    sonderbauwerk.gemeinde_id,
    sonderbauwerk.bezeichnung,
    sonderbauwerk.kurzbeschreibung,
    sonderbauwerk.entw_einzugsgeb_opt,
    sonderbauwerk.typ_opt,
    sonderbauwerk.inbetriebnahme,
    sonderbauwerk.stillgelegt_am,
    sonderbauwerk.wiederinbetr_dat,
    objekt.standortid,
    objekt.betreiberid,
    sonderbauwerk.anspr_adr_nr,
    objekt.beschreibung,
    sonderbauwerk.e32,
    sonderbauwerk.n32,
    sonderbauwerk.in_nrw_tog,
    sonderbauwerk.industr_tog,
    sonderbauwerk.name_ausserhalb_nrw,
    sonderbauwerk.k_entw_gebiet,
    sonderbauwerk.bef_flaeche_egebiet,
    sonderbauwerk.abflussbeiwert,
    sonderbauwerk.bef_grad,
    sonderbauwerk.undurch_flaeche,
    sonderbauwerk.beckentyp_opt,
    sonderbauwerk.rohrspeicher_tog,
    sonderbauwerk.bauweise_opt,
    sonderbauwerk.drossel_opt,
    sonderbauwerk.so_drossel,
    sonderbauwerk.beckensteuerung_opt,
    sonderbauwerk.a_rei_einrich_tog,
    sonderbauwerk.messart_tog,
    sonderbauwerk.drossel_tog,
    sonderbauwerk.fuellstand_tog,
    sonderbauwerk.entlastungswasser_tog,
    sonderbauwerk.entlastungsdauer_tog,
    sonderbauwerk.entlastungshaeufig_tog,
    sonderbauwerk.fern_mess_tog,
    sonderbauwerk.niederschlag_tog,
    sonderbauwerk.fern_stoer_tog,
    sonderbauwerk.hwfrei_tog,
    sonderbauwerk.einstau_haeufig,
    sonderbauwerk.hw_einrichtung_tog,
    sonderbauwerk.hw_rueckstau_tog,
    sonderbauwerk.hw_schieber_tog,
    sonderbauwerk.hw_pumpwerk_tog,
    sonderbauwerk.hw_weitere_tog,
    sonderbauwerk.hw_sonst_text,
    sonderbauwerk.bemes_weiterg_tog,
    sonderbauwerk.bemessung_text,
    sonderbauwerk.anordnung_opt,
    sonderbauwerk.wasserrecht_genehmigung_nr,
    sonderbauwerk.beckenart_opt,
    sonderbauwerk.beckentiefe,
    sonderbauwerk.beh_flaeche_1u2,
    sonderbauwerk.beh_flaeche_2u3,
    sonderbauwerk.betriebsart_opt,
    sonderbauwerk.csb,
    sonderbauwerk.drosselabfluss,
    sonderbauwerk.dross_abfluss_opt,
    sonderbauwerk.dross_ueber_tog,
    sonderbauwerk.entlastungsart_opt,
    sonderbauwerk.entleerungszeit,
    sonderbauwerk.flaechenbeschickung,
    sonderbauwerk.fliesszeit,
    sonderbauwerk.fremd_abfluss,
    sonderbauwerk.funktion_opt,
    sonderbauwerk.kanal_vol,
    sonderbauwerk.qrkrit,
    sonderbauwerk.kritisch_misch,
    sonderbauwerk.max_h_schmutzabfluss,
    sonderbauwerk.max_h_trocken,
    sonderbauwerk.min_dr_abfluss,
    sonderbauwerk.misch_ueberlauf,
    sonderbauwerk.n_mindest_v,
    sonderbauwerk.n_spez_vol,
    sonderbauwerk.neigung,
    sonderbauwerk.rdrosseldurchfluss,
    sonderbauwerk.regenabfl_dross,
    sonderbauwerk.regenabfl_entl,
    sonderbauwerk.regenabfluss,
    sonderbauwerk.regenabfluss_dr,
    sonderbauwerk.regenspende,
    sonderbauwerk.rfilterflaeche,
    sonderbauwerk.rfiltergeschwin,
    sonderbauwerk.rfiltersubstrat_h,
    sonderbauwerk.rhyd_wirkungsgrad,
    sonderbauwerk.rjahr_ueh,
    sonderbauwerk.rkrit,
    sonderbauwerk.rm_filterbelastung,
    sonderbauwerk.rspez_filtervol,
    sonderbauwerk.rstauvolumen,
    sonderbauwerk.rvol_slamelle,
    sonderbauwerk.rw_krit_abfluss,
    sonderbauwerk.rw_krit_misch,
    sonderbauwerk.rw_mindest_misch,
    sonderbauwerk.schmutz_abfluss,
    sonderbauwerk.sku_anstroem,
    sonderbauwerk.sku_mindest_svol,
    sonderbauwerk.sku_spez_vol,
    sonderbauwerk.spez_beckenvol,
    sonderbauwerk.spez_speicher,
    sonderbauwerk.trocken_w_abfluss,
    sonderbauwerk.speichervolumen,
    sonderbauwerk.w_oberflaeche,
    sonderbauwerk.erstell_dat,
    sonderbauwerk.aktual_dat,
    'ELKA_KR711'::text AS herkunft,
    sonderbauwerk.external_nr
   FROM elka.e_adresse,
    elka.e_standort,
    basis.objekt,
    oberflgw.sonderbauwerk
  WHERE ((objekt.standortid = e_standort.nr) AND (objekt.betreiberid = e_adresse.nr) AND (sonderbauwerk.objekt_id = objekt.id) AND (objekt._deleted = false));



CREATE VIEW elka.e_wasserrecht AS
 SELECT wasserrecht.objektid AS nr,
    objekt.betreiberid AS adresse,
    objekt.id AS ref_nr,
    objekt.id AS aktenzeichen,
        CASE
            WHEN (wasserrecht.gen58 = true) THEN 3
            WHEN (wasserrecht.gen59 = true) THEN 4
            WHEN (wasserrecht.gen8 = true) THEN 1
            ELSE NULL::integer
        END AS recht_art_opt,
    wasserrecht.antrag_datum AS antrag_dat,
    wasserrecht.erstellungs_datum AS erteilung_wr_dat,
        CASE
            WHEN (wasserrecht.befristet IS NOT NULL) THEN wasserrecht.befristet
            ELSE false
        END AS befristung_tog,
    wasserrecht.befristet_bis AS befristung_dat,
    wasserrecht.bemerkungen AS bemerkung,
        CASE
            WHEN (wasserrecht.aenderungs_datum IS NOT NULL) THEN (wasserrecht.aenderungs_datum)::timestamp
            ELSE (CURRENT_TIMESTAMP())::timestamp
        END AS aktual_dat,
        CASE
            WHEN (wasserrecht.erstellungs_datum IS NOT NULL) THEN (wasserrecht.erstellungs_datum)::timestamp
            ELSE ('1970-01-01 00:00:00'::text)::timestamp
        END AS erstell_dat,
    'ELKA_KR711'::text AS herkunft
   FROM elka.e_adresse,
    elka.e_standort,
    basis.objekt,
    elka.wasserrecht
  WHERE ((objekt.standortid = e_standort.nr) AND (objekt.betreiberid = e_adresse.nr) AND (wasserrecht.objektid = objekt.id) AND (objekt._deleted = false));



CREATE TABLE elka.z_aba_verfahren (
    id integer auto_increment NOT NULL,
    anlage_nr bigint NOT NULL,
    abwasbehverf_nr bigint NOT NULL
);


CREATE VIEW elka.e_z_aba_verfahren AS
 SELECT aba.objektid AS anlage_nr,
    z_aba_verfahren.abwasbehverf_nr
   FROM elka.z_aba_verfahren,
    elka.aba
  WHERE (z_aba_verfahren.anlage_nr = aba.id);



CREATE VIEW elka.e_z_aba_wasserrecht AS
 SELECT e_abwasserbehandlungsanlage.nr AS anlage_nr,
    e_wasserrecht.nr AS wasserrecht_nr
   FROM ((elka.e_abwasserbehandlungsanlage
     JOIN elka._q_z ON ((e_abwasserbehandlungsanlage.nr = _q_z.quelle)))
     JOIN elka.e_wasserrecht ON ((_q_z.ziel = e_wasserrecht.nr)));



CREATE VIEW elka.e_z_els_wasserrecht AS
 SELECT e_einleitungsstelle.nr AS els_nr,
    e_wasserrecht.nr AS wasserrecht_nr
   FROM ((elka.e_einleitungsstelle
     JOIN elka._q_z ON ((e_einleitungsstelle.nr = _q_z.quelle)))
     JOIN elka.e_wasserrecht ON ((_q_z.ziel = e_wasserrecht.nr)));



CREATE TABLE elka.map_elka_analysemethode (
    id integer auto_increment NOT NULL,
    gruppe_dev_id character varying(3) NOT NULL,
    regelwerk_id character varying(2) NOT NULL,
    varianten_id character(1) NOT NULL,
    methoden_nr character varying
);



CREATE TABLE elka.map_elka_anhang (
    id integer auto_increment NOT NULL,
    anhang_auik integer,
    id_elka character varying
);



CREATE TABLE elka.map_elka_gewkennz (
    gewkz integer,
    gewname character varying
);



CREATE TABLE elka.referenz (
    nr integer NOT NULL,
    fs_tog boolean,
    standort_nr integer,
    q_aba_nr integer,
    q_els_nr integer,
    q_afs_nr integer,
    q_msst_nr integer,
    q_sb_nr integer,
    q_nw_afs_nr integer,
    q_entl_nr integer,
    q_kka_nr integer,
    q_ka_nr integer,
    z_aba_nr integer,
    z_els_nr integer,
    z_afs_nr integer,
    z_msst_nr integer,
    z_sb_nr integer,
    z_nw_afs_nr integer,
    z_entl_nr integer,
    z_kka_nr integer,
    z_ka_nr integer,
    aktual_dat date,
    erstell_dat date
);



CREATE TABLE indeinl.anh_40_fachdaten (
    bemerkungen character varying(255),
    ansprechpartner character varying(255),
    sachbearbeiterrav character varying(255),
    sachbearbeiterheepen character varying(255),
    klaeranlage character varying(255),
    herkunftsbereich character varying(255),
    wsg boolean,
    prioritaet smallint,
    genehmigungspflicht boolean,
    nachtrag boolean,
    bimsch boolean,
    abwmengegenehmigt integer,
    abwmengeprodspez integer,
    abwmengegesamt integer,
    gen58 timestamp,
    gen59 timestamp,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer auto_increment NOT NULL,
    anfallstelleid integer
);



CREATE TABLE indeinl.anh_49_abfuhr (
    id integer auto_increment NOT NULL,
    abfuhrdatum date,
    naechsteabfuhr date,
    entsorger character varying,
    anh49id integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    menge double precision
);



CREATE TABLE indeinl.anh_49_abscheiderdetails (
    id integer auto_increment NOT NULL,
    abscheidernr integer,
    von integer,
    lage character varying(255),
    nenngroesse integer,
    rueckhalt boolean DEFAULT false,
    schlammfang boolean DEFAULT false,
    benzinabscheider boolean DEFAULT false,
    koaabscheider boolean DEFAULT false,
    kompakt boolean DEFAULT false,
    emulsionsspaltanlage boolean DEFAULT false,
    vsf2 integer,
    schwimmer boolean DEFAULT false,
    warnanlage boolean,
    hersteller character varying(255),
    bemerkung character varying(255),
    anh49id integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    vsf1 integer,
    typ character varying(255),
    kreis boolean DEFAULT false,
    vorschlamm boolean DEFAULT false,
    pn_stelle boolean DEFAULT false
);



CREATE TABLE indeinl.anh_49_analysen (
    id integer auto_increment NOT NULL,
    datum timestamp,
    institut character varying(255),
    csb_wert character varying(255),
    csb_grenzwert character varying(255),
    aox_wert character varying(255),
    aox_grenzwert character varying(255),
    kw_wert character varying(255),
    kw_grenzwert character varying(255),
    zink_wert character varying(255),
    zink_grenzwert character varying(255),
    bsb5_wert character varying(255),
    bsb5_csb_relation character varying(255),
    bemerkungen character varying(255),
    bik_grenzwert character varying(255),
    bik_wert character varying(255),
    ph_wert character varying(255),
    ph_grenzwert character varying(255),
    anh49id integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE indeinl.anh_49_fachdaten (
    klaeranlage character varying(255),
    name character varying(255),
    bemerkungen character varying(255),
    planquadrat character varying(255),
    abgemeldet boolean,
    technik_anh49 character varying(255),
    technik_anh49_nr character varying(255),
    sachkundelfa character varying(255),
    werkstatt boolean,
    bodeneinlaeufe boolean,
    waschanlagen boolean,
    sonstiges character varying(255),
    analysemonat character varying(255),
    abwasserfrei boolean,
    anredeantragst character varying(255),
    nameantragst character varying(255),
    zusantragst character varying(255),
    strasseantragst character varying(255),
    hausnrantragst integer,
    hausnrzusantragst character varying(255),
    plzantragst character varying(255),
    ortantragst character varying(255),
    sachbearbeiter_in character varying(255),
    ansprechpartner_in character varying(255),
    antragvom timestamp,
    genehmigung timestamp,
    wiedervorlage timestamp,
    aenderungsgenehmigung timestamp,
    letztes_anschreiben timestamp,
    anschreiben character varying(255),
    waschanlage boolean,
    e_satzung boolean,
    seitwann timestamp,
    sonstigestechnik character varying(255),
    maengel boolean,
    behoben boolean,
    frist timestamp,
    durchgefuehrt integer,
    dekra_tuev_datum timestamp,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer auto_increment NOT NULL,
    anfallstelleid integer,
    sicherheitsabscheider boolean
);



CREATE TABLE indeinl.anh_49_kontrollen (
    id integer auto_increment NOT NULL,
    pruefdatum date,
    naechstepruefung date,
    pruefergebnis character varying,
    anh49id integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE indeinl.anh_50_fachdaten (
    telefon character varying(255),
    erloschen boolean,
    datumantrag timestamp,
    bemerkungen character varying(255),
    genehmigung timestamp,
    wiedervorlage timestamp,
    gefaehrdungsklasse character varying(255),
    entsorgerid integer auto_increment,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer auto_increment NOT NULL,
    anfallstelleid integer
);



CREATE TABLE indeinl.anh_52_fachdaten (
    nrbetriebsstaette integer,
    firmenname character varying(255),
    telefon character varying(255),
    telefax character varying(255),
    ansprechpartner character varying(255),
    datumgenehmigung timestamp,
    bemerkungen character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer auto_increment NOT NULL,
    anfallstelleid integer
);



CREATE TABLE indeinl.anh_53_fachdaten (
    branche character varying(255),
    verfahren character varying(255),
    antragsdatum timestamp,
    bagatell boolean,
    bagatelldatum timestamp,
    genehmigungsdatum timestamp,
    genehmigungaufgehoben timestamp,
    abnahmedatum timestamp,
    genehmigungstitel character varying(255),
    genehmigung boolean,
    durchsatz integer,
    gesamtmenge_eb integer,
    onlineentsilberung boolean,
    abwasser boolean,
    abwasserfrei timestamp,
    kleiner200qm timestamp,
    betrieb_abgemeldet boolean,
    bemerkungen character varying(255),
    betriebstagebuch boolean,
    wasseruhr boolean,
    spuelwassermenge integer,
    spuelwasserverbrauch integer,
    wartungsvertrag boolean,
    grgen boolean,
    genart character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer auto_increment NOT NULL,
    anfallstelleid integer
);



CREATE TABLE indeinl.anh_55_fachdaten (
    abgemeldet boolean,
    putztuecher boolean,
    teppich boolean,
    matten boolean,
    haushaltstex boolean,
    berufskl boolean,
    gaststhotel boolean,
    krankenhaus boolean,
    heimwaesche boolean,
    anteilwaschgut integer,
    vlies boolean,
    fischfleisch boolean,
    anteilgesamtgut integer,
    betrwasseraufber boolean,
    chlor boolean,
    aktivchlor boolean,
    sachbearbeiter character varying(255),
    entgeb_id character varying(255),
    bemerkungen character varying(255),
    mengewaesche character varying(255),
    sonsttex character varying(255),
    monatwasserverb character varying(255),
    waschsituation character varying(255),
    ansprechpartner character varying(255),
    branche character varying(255),
    loesungsmittel boolean,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer auto_increment NOT NULL,
    anfallstelleid integer
);



CREATE TABLE indeinl.anh_56_fachdaten (
    druckverfahren character varying(255),
    verbrauch character varying(255),
    sachbearbeiterrav character varying(255),
    sachbearbeiterheepen character varying(255),
    entsorgung character varying(255),
    abwasseranfall boolean,
    genpflicht boolean,
    aba boolean,
    gen_58 timestamp,
    gen_59 timestamp,
    bemerkungen character varying(255),
    abfallrechtlentsorg boolean,
    spuelwasser boolean,
    leimabwasser boolean,
    erfasstam timestamp,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer auto_increment NOT NULL,
    anfallstelleid integer
);



CREATE TABLE indeinl.bwk_fachdaten (
    branche character varying(255),
    k_hersteller character varying(255),
    k_typ character varying(255),
    k_brennmittel character varying(255),
    k_leistung integer,
    datum_g timestamp,
    aba boolean,
    w_brenner character varying(255),
    w_waermetauscher character varying(255),
    w_abgasleitung character varying(255),
    w_kondensableitung character varying(255),
    abnahme character varying(255),
    bemerkungen character varying(255),
    anschreiben timestamp,
    erfassung integer,
    genehmigung boolean,
    genehmigungspflicht boolean,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer auto_increment NOT NULL,
    anfallstelleid integer
);



CREATE TABLE indeinl.suev_fachdaten (
    objektid integer NOT NULL,
    groesser_3ha boolean,
    vers_flaeche integer,
    suevkan_pflicht boolean,
    indirektsw boolean,
    indirektrw boolean,
    direktsw boolean,
    direktrw boolean,
    anzeige58 integer,
    sanierung_erfolgt boolean,
    sanierungskonzept boolean,
    keine_angaben boolean,
    dat_anzeige58 timestamp,
    dat_anschreiben timestamp,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer auto_increment NOT NULL
);



CREATE TABLE indeinl.entsorger (
    id integer auto_increment NOT NULL,
    entsorger character varying(255),
    strasse character varying(255),
    hausnr integer,
    plz character varying(255),
    ort character varying(255),
    ansprechpartner character varying(255),
    telefon character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE labor.einheiten (
    id integer auto_increment NOT NULL,
    bezeichnung character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    dea_einheiten_masseinheiten_nr integer
);



CREATE TABLE labor.parameter (
    ordnungsbegriff character varying(255) NOT NULL,
    analyseverfahren character varying(255),
    bezeichnung character varying(255),
    wirdgemessenineinheit integer,
    grenzwert double precision,
    grenzwertname character varying(255),
    sielhaut_gw double precision,
    klaerschlamm_gw double precision,
    preisfueranalyse double precision,
    einzelnbeauftragbar boolean,
    kennzeichnung character varying(255),
    konservierung character varying(255),
    parametergruppe_id integer,
    reihenfolge integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    dea_stoffe_stoff_nr integer,
    analysemethode_id integer
);

ALTER TABLE LABOR.PARAMETER ADD CONSTRAINT PK_PARAMETER PRIMARY KEY ("ORDNUNGSBEGRIFF");



CREATE TABLE labor.sielhaut (
    bezeichnung character varying(255),
    haltungsnr character varying(255),
    alarmplannr character varying(255),
    entgeb character varying(255),
    e32 double precision,
    n32 double precision,
    lage character varying(255),
    bemerkungen character varying(255),
    twabfluss character varying(255),
    bsb character varying(255),
    ew character varying(255),
    gebiet character varying(255),
    p_sielhaut boolean,
    p_alarmplan boolean,
    p_nachprobe boolean,
    schlammprobe boolean,
    p_firmenprobe boolean,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    id integer auto_increment NOT NULL,
    probepktid integer
);



CREATE VIEW indeinl.view_sielhautqb_probenahmen_vs AS
 SELECT pn1.id,
    pn1.kennummer,
    s1.bezeichnung,
    s1.lage,
    pn1.datum_der_entnahme,
    pp1.objektid
   FROM labor.probenahme pn1,
    labor.sielhaut s1,
    labor.messstelle pp1
  WHERE ((pn1.messstid = pp1.objektid) AND (pp1.objektid = s1.id) AND (( SELECT count(*) AS count
           FROM labor.probenahme pn2,
            labor.messstelle pp2
          WHERE ((pn2.messstid = pp2.objektid) AND (pp2.objektid = pp1.objektid) AND (pn2.datum_der_entnahme > pn1.datum_der_entnahme))) < 5))
  ORDER BY pp1.objektid, pn1.datum_der_entnahme DESC;



CREATE TABLE labor.gebuehren (
    id integer auto_increment NOT NULL,
    bezeichnung character varying,
    wert integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE labor.klaeranlage (
    id integer auto_increment NOT NULL,
    anlage character varying(255),
    dea_klaeranlage_nr integer DEFAULT 0 NOT NULL,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



COMMENT ON COLUMN labor.klaeranlage.id IS 'Entwässerungsgebiet';



CREATE TABLE labor.meta_parameter (
    id integer auto_increment NOT NULL,
    ordnungsbegriff character varying(255),
    einheit_id integer,
    dea_regelwerk_nr character varying(2),
    dea_gruppe_dev character varying(3),
    dea_varianten_nr character varying(1),
    dea_trenn_nr_opt integer,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL,
    ueberwachungswert real
);



CREATE TABLE labor.parametergruppen (
    id integer auto_increment NOT NULL,
    name character varying(255),
    preisfueranalyse double precision,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE labor.probeart (
    id integer auto_increment NOT NULL,
    art character varying(255) NOT NULL,
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE labor.status (
    id integer auto_increment NOT NULL,
    bezeichnung character varying(255),
    _enabled boolean DEFAULT true NOT NULL,
    _deleted boolean DEFAULT false NOT NULL
);



CREATE TABLE oberflgw.gew_massnahmen (
    id integer auto_increment NOT NULL,
    massn_nr character varying(100),
    massnahme character varying(255),
    rechtswert double precision,
    hochwert real,
    status character varying(255),
    gewaesser character varying(255),
    gewkz_3c double precision,
    stat_gsk3c character varying(255),
    gewkz_bi double precision,
    stat_bi double precision,
    nstat_entf_m double precision,
    kosten_euro numeric(19,4),
    prioritaet double precision,
    schadlos_ist character varying(255),
    schaeden_ist character varying(255),
    station_von_bis character varying(255),
    baubeginn character varying(255),
    inbetriebnahme_jahr character varying(50),
    speichervolumen integer DEFAULT 0,
    "q_dr_l/s" integer DEFAULT 0,
    einzugsgebiet_qkm integer DEFAULT 0,
    massnahme_knef character varying(50),
    bemerkungen character varying(255),
    aussergew_hwrr double precision,
    gew_hw_rueckhalteraum double precision,
    massn_art character varying,
    nr_gen character varying(50)
);



CREATE TABLE oberflgw.afs_niederschlagswasser (
    nr integer NOT NULL,
    lfd_nr integer,
    bezeichnung character varying(80),
    bef_flaeche integer,
    nw_her_bereich_opt integer,
    abflussmenge numeric(11,2),
    anfallstellen_nr bigint,
    entw_grund_nr bigint
);



CREATE TABLE oberflgw.afs_stoffe (
    anfallstellen_nr bigint NOT NULL,
    stoff_nr bigint NOT NULL,
    produkt character varying(15)
);



CREATE TABLE oberflgw.kom_einleitungen (
    id integer auto_increment NOT NULL,
    e_nr_zahl character varying(255),
    utm_east double precision,
    utm_north double precision,
    az_br_alt character varying(255),
    wb_blatt_nr character varying(255),
    az_360_alt character varying(255),
    az_360_neu character varying(255),
    stadt character varying(255),
    kom_igl_p character varying(255),
    name_erl_inhaber character varying(255),
    strasse_erl_inhaber character varying(255),
    hausnr_erl_inhaber double precision,
    plz_erl_inhaber double precision,
    ort_erl_inhaber character varying(255),
    staatskennung character varying(255),
    name_firma character varying(255),
    strasse_firma character varying(255),
    haus_nr_firma character varying(255),
    plz_firma character varying(255),
    ort_firma character varying(255),
    ansprechp character varying(255),
    telefon_ap character varying(255),
    e_mail_ap character varying(255),
    gew_sch_b character varying(255),
    stadtbezirk character varying(255),
    ts_ms character varying(255),
    e_v character varying(255),
    status character varying(255),
    e_nr character varying(255),
    gemarkung character varying(255),
    flur double precision,
    flurstueck double precision,
    e_in_stat_gew character varying(255),
    gew_nr_stadt character varying(255),
    gew_name_stadt character varying(255),
    entf_e_stat_km character varying(255),
    name_stat_gew character varying(255),
    gew_kz character varying(255),
    gebiets_kz double precision,
    station character varying(255),
    gemeinde_kz character varying(255),
    ermittlung_punkt character varying(255),
    kenn_ofwk double precision,
    aeo_qkm double precision,
    mnq_l_s double precision,
    mwq_l_s double precision,
    hq1_p_nat double precision,
    gew_guete_bem character varying(255),
    vn_360 timestamp,
    hwr_massnahmen character varying(255),
    sonder_bw character varying(255),
    gebiet_bez character varying(255),
    gebiet_nutz character varying(255),
    kat_flaeche character varying(255),
    ew_netz character varying(255),
    a_ek_ha character varying(255),
    a_eb_ha character varying(255),
    ae_b_b_ha character varying(255),
    psi character varying(255),
    au_ha character varying(255),
    e_menge_l_s double precision,
    e_menge_qbm_2h double precision,
    bei_flaechenvers_qm character varying(255),
    bei_muldenvers_cbm character varying(255),
    bei_rigolenvers_m character varying(255),
    bei_schachtvers_tiefe_m character varying(255),
    bodenart character varying(255),
    k_f_wert_boden character varying(255),
    grundwasserflurabstand_m character varying(255),
    gelaendehoehe_vers_anlage character varying(255),
    abstand_vers_nachbar character varying(255),
    abstand_vers_anlage_geb character varying(255),
    abstand_vers_drainage character varying(255),
    notueberlauf character varying(255),
    antrag_vom timestamp,
    wr_art character varying(255),
    datum_wr timestamp,
    datum_aenderung character varying(255),
    befristung timestamp,
    zustaendigkeit character varying(255),
    teilstroeme character varying(255),
    "58_gen" character varying(255),
    wvl_termin timestamp,
    inhalt_nb_1 character varying(255),
    frist_nb_1 character varying(255),
    inhalt_nb_2 character varying(255),
    bericht_vorgelegt_am character varying(255),
    abnahme_am character varying(255),
    suewv_kan_bem character varying(255),
    suewv_kan_wvl character varying(255),
    hwrm_frist character varying(255),
    bemerkungen character varying(255),
    link_1 character varying(255),
    link_2 character varying(255)
);



CREATE TABLE oberflgw.massnahme (
    nr bigint NOT NULL,
    stoff_nr bigint,
    gruppe_nr bigint,
    massnahme_stoff character varying(1000),
    stoff_eintrags_dat date,
    ergebnis_massnahme character varying(1000),
    ergebnis_am date,
    erstell_dat timestamp NOT NULL,
    aktual_dat timestamp NOT NULL,
    external_nr character varying(50)
);



CREATE TABLE oberflgw.msst_berichtspflicht (
    id integer auto_increment NOT NULL,
    msst_nr bigint NOT NULL,
    berichtspflicht_opt integer NOT NULL
);



CREATE TABLE oberflgw.sb_entlastung (
    nr bigint NOT NULL,
    lfd_nr integer,
    typ_opt integer,
    sb_nr bigint NOT NULL,
    tauchwand_tog boolean DEFAULT false NOT NULL,
    sonstige_beh_tog boolean DEFAULT false NOT NULL,
    ausb_ueberlauf_opt integer,
    sonstige_behandlung character varying(80),
    sb_nicht_in_nrw_tog boolean DEFAULT false NOT NULL,
    ka_nicht_in_nrw_tog boolean DEFAULT false NOT NULL,
    ausb_ueberlauf_sonstiges character varying(80)
);



CREATE TABLE oberflgw.versickerungsanlage (
    nr integer NOT NULL,
    els_nr bigint NOT NULL,
    flurabstand numeric(11,2),
    gelaende_ver_anlage numeric(11,2),
    abst_gr_grenze numeric(11,2),
    abst_unterk_gebaeude numeric(11,2),
    abst_ver_anlage numeric(11,2),
    landesfoerderung_tog boolean DEFAULT false NOT NULL,
    notueberlauf_tog boolean DEFAULT false NOT NULL,
    notueberlauf_ziel character varying(80),
    erstell_dat timestamp NOT NULL,
    durchlaessigkeit numeric(22,11),
    untergrundart character varying(30),
    ver_anlage_opt integer,
    sonstiges_vers character varying(50),
    bauartzul_id character varying(30),
    aktual_dat timestamp NOT NULL,
    herkunft character varying(20),
    external_nr character varying(50)
);



CREATE TABLE oberflgw.z_betrieb_massnahme (
    id integer auto_increment NOT NULL,
    betrieb_nr bigint NOT NULL,
    massnahme_nr bigint NOT NULL
);



CREATE TABLE oberflgw.z_entwaessgr_abwasbehverf (
    id integer auto_increment NOT NULL,
    entw_grund_nr bigint NOT NULL,
    abwasbehverf_nr bigint NOT NULL
);



CREATE TABLE oberflgw.z_rbf_schutzgueter (
    id integer auto_increment NOT NULL,
    sb_nr bigint NOT NULL,
    schutzgueter_opt integer NOT NULL
);



CREATE TABLE oberflgw.z_sb_regeln (
    id integer auto_increment NOT NULL,
    sb_nr bigint NOT NULL,
    regeln_tech_opt integer NOT NULL
);



CREATE TABLE oberflgw.z_sb_verfahren (
    id integer auto_increment NOT NULL,
    sb_nr bigint NOT NULL,
    vorgaben_opt integer NOT NULL
);



CREATE TABLE public.e_z_klaeranlage_massnahme (
    id integer auto_increment NOT NULL,
    massnahme_nr bigint NOT NULL,
    klaeranlage_nr bigint NOT NULL
);



CREATE TABLE public.e_z_kleika_gebiet (
    id integer auto_increment NOT NULL,
    kleika_nr bigint NOT NULL,
    gebietskennung_opt integer NOT NULL
);



CREATE TABLE public.tab_streets_alkis (
    plz character varying(5),
    name character varying(40),
    nr character varying(4),
    x double precision,
    y double precision,
    gemeinde character varying(10),
    schl character varying(5),
    hausnr integer,
    hausnr_zusatz character varying(1),
    abgleich character varying(9),
    id integer auto_increment NOT NULL
);



CREATE TABLE public.tbl_einl_gleich_std (
    igl_id double precision,
    name character varying(255),
    strasse_einleiter character varying(255),
    hausnr_einl double precision,
    zus_einl character varying(255),
    strasse_std character varying(255),
    hausnr_std double precision,
    zus_std character varying(255),
    name_std character varying(255),
    einl_gleich_std character(1)
);




INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00001','','Bodensatz',42,0.0,null,null,null,0.0,1,null,null,null,85,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00002','','Eisen nach Belüftung (Fe)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00003','','Kohlensäure (gesamt)',42,0.0,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00004','','Kohlensäure (frei)',42,0.0,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00005','','Basenkapazität (p-Wert)',42,0.0,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00006','','Ammonium (NH4-N) DIN 38604',63,0.5,null,null,0.0,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00007','','Nitrat (NO3)',42,null,null,null,null,0.0,1,null,null,null,null,1,0,1244,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00008','','Nitrit (NO2)',42,10.0,null,null,null,0.0,1,null,null,null,null,1,0,1246,7);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00009','','Gesamt-Härte',42,0.0,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00010','','Karbonat-Härte',42,0.0,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00011','','Escherichia coli',42,0.0,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00012','','Coliforme Bakt.',42,0.0,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00013','','Zahl E. coli und Coliforme',42,0.0,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00014','','Koloniezahl in Gelatine 20 Cel',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00015',null,'Chlor, gebundenes (Cl)',42,0.0,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00016','','Wasserspiegel',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00017','','Wassertemperatur',42,0.0,null,null,null,0.0,1,null,null,null,60,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00018','','Phosphat (PO4) ges.',42,null,null,null,0.0,0.0,1,null,null,null,null,1,0,1261,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00020','','Chrom (Cr) ges.',42,null,null,null,null,0.0,1,null,null,null,null,1,1,1151,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00021','','Cyanid (CN)',42,2.0,null,null,null,8.93,1,null,null,3,17,1,0,1231,4);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00022','','Chemischer Index',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00023','','Anzahl der Organismen',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00025','','Anionenaktive Detergentien',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00026','','Hydrogencarbonat (HCO3-)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00030','','Durchsichtigkeit',42,null,null,null,null,0.0,1,null,null,null,null,1,1,1035,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00031','','Entf. d. Methylenblau- Probe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00032','','organischer Stickstoff (N)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00033','','Stickstoff (N) ges.',63,null,null,null,0.0,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00034','','Phosphat (PO4) ges.',42,null,null,null,null,0.0,1,null,null,null,null,1,1,1261,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00035','','Chloride (Cl)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00036','','Detergentien',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00037','','Petroläther extrahierbare Stoffe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00038','','Mineralöl- Kohlenwasserstoffe',42,0.0,null,null,null,0.0,1,null,null,null,65,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00039','','Chromate (CrO4)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00040','','Chrom (Cr) ges.',42,1.0,null,24.0,null,2.67,1,null,null,null,null,1,0,1151,3);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00041','','Eisen (Fe)',42,null,null,null,0.0,0.0,1,null,null,null,null,1,0,1182,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00042','','Phenol (C6H5OH)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00043','','PAK (Polycykl. Aromat. KW)',42,null,null,null,0.0,17.4,1,null,null,3,null,1,0,2350,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00044','','Trichlorethen (HCCl=CCl2)',44,500.0,null,null,null,11.6,1,null,null,1,null,1,0,2020,17);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00045','','Tetrachlorethen (CCl2=CCl2)',44,500.0,null,null,null,11.6,1,null,null,1,null,1,0,2021,17);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00046','','Atrazin',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00047','','Simazin',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00069',null,'Ionenbilanzfehler',63,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00070','','Barium (Ba)',42,null,null,null,null,0.0,1,null,null,3,null,1,0,1124,3);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00080','','Farbe',0,null,null,null,null,0.0,1,null,null,null,null,1,1,1023,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00081','','Methylenblauprobe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00083','','Gesamt-Phosphat-Phosphor (PO4-P)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00084','','Sauerstoffsättigung (% O2)',42,0.0,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00085','','Saprobienindex',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00086','','Wassergüteklasse',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00088','','Leichtfl.organ.Chlorverb.',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00102','','Bromdichlormethan (HCBrCl2)',42,null,null,null,null,0.0,1,null,null,null,null,1,0,2006,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00103','','1,2-Dichlorethan (H2CCl-CClH2)',42,0.0,null,null,null,0.0,1,null,null,null,71,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00105','','Trichlormethan (HCCl3)',44,500.0,null,null,null,11.6,1,null,null,1,null,1,0,2001,17);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00106','','Benzo-(a)-pyren',42,null,null,null,0.0,17.4,1,null,null,null,null,1,0,2320,19);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00107','','Trockensubstanz',63,null,null,1.0,0.0,0.0,1,null,null,null,null,1,0,1469,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00108','','Cobalt (Co)',42,2.0,null,2.8,20.0,4.64,1,null,null,3,28,1,0,1186,3);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00109','','PCB 28',42,null,null,null,0.05,0.0,1,null,null,null,null,1,0,2071,16);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00110','','PCB 52',42,null,null,null,0.05,0.0,1,null,null,null,null,1,0,2072,16);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00111','','PCB 101',42,null,null,null,0.05,0.0,1,null,null,null,null,1,0,2073,16);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00112','','PCB 138',42,null,null,null,0.05,0.0,1,null,null,null,null,1,0,2074,16);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00113','','PCB 153',42,null,null,null,0.05,0.0,1,null,null,null,57,1,0,2076,16);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00114','','PCB 180',42,null,null,null,0.05,0.0,1,null,null,null,63,1,0,2077,16);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00115','','Methan (CH4)',42,null,null,null,null,0.0,1,null,null,null,66,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00116','','Benzol (C6H6)',44,50.0,null,null,null,0.0,1,null,null,2,null,1,0,2048,18);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00117','','Toluol (C6H5-CH3)',44,500.0,null,null,null,0.0,1,null,null,2,null,1,0,2400,18);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00118','','ortho-Xylol (C6H4-(CH3)2)',44,500.0,null,null,null,0.0,1,null,null,2,null,1,0,2410,18);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00119','','meta-Xylol (C6H4-(CH3)2)',42,null,null,null,null,0.0,1,null,null,null,67,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00120','','para-Xylol (C6H4-(CH3)2)',42,null,null,null,null,0.0,1,null,null,null,72,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00121','','Methanol (H3COH)',42,null,null,null,null,0.0,1,null,null,null,74,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00122','','Kohlensäure, gebunden',42,null,null,null,null,0.0,1,null,null,null,75,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00123','','Kohlendioxid (CO2)',42,null,null,null,null,0.0,1,null,null,null,76,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00124','','Schwefelwasserstoff (H2S)',42,null,null,null,null,0.0,1,null,null,null,77,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00125','','Stickstoff (N2) ges.',42,null,null,null,null,0.0,1,null,null,null,null,1,0,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00127','','ortho-Phosphat (o-PO4)',42,null,null,null,null,0.0,1,null,null,null,78,1,0,1263,8);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00128','','Sulfid (S2-), leicht freisetzbar',42,null,null,null,null,11.6,1,null,null,3,79,1,0,1309,12);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00129','','Ethylbenzol (C6H5-CH2-CH3)',44,500.0,null,null,null,0.0,1,null,null,3,null,1,0,2415,12);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00130','','Xylole, Summe (C6H4-(CH3)2)',44,null,null,null,null,0.0,1,null,null,3,null,1,0,2913,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00131','','Cumol (C6H5-CH-(CH3)2)',42,0.0,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00132','','Mesitylen (C6H3-(CH3)3)',42,0.0,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00133','','tert.-Butyl-Benzol (C6H5-C-(CH3)3)',42,null,null,null,null,0.0,1,null,null,null,80,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00134','','Dicyclopentadien (C10H12)',42,null,null,null,null,0.0,1,null,null,null,81,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00135','','Isopropylbenzol (C6H5-CH-(CH3)2)',42,null,null,null,null,0.0,1,null,null,null,82,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00136','','1.3.5-Trimethylbenzol',44,500.0,null,null,null,0.0,1,null,null,null,93,1,0,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00137','','2,4,4`-Trichlorobiphenyl',42,null,null,null,null,0.0,1,null,null,null,84,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00138','','2,2`,5,5`-Tetrachlorobiphenyl',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00139','','2,2`,4,5,5`-Pentachlorobiphenyl',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00140','','2,2`,3,4,4`,5`-Hexachlorobiphenyl',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00141','','2,2`,4,4`,5,5`-Hexachlorobiphenyl',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00142','','2,2`,3,4,4`,5,5`-Heptachlorobiphenyl',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00143','','CKW (Chlorkohlenwasserstoffe, Summe)',42,0.0,null,null,0.0,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00144','','Kohlensäure, kalkaggr. n. Heyer',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00145','','PCB, Summe',42,null,null,null,0.0,17.4,1,null,null,null,null,1,0,2075,16);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00146','','Scheinbare Karbonathärte',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00147','','Ammoniak (NH3)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00148','','Phosphorwasserstoff (PH3)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00149','','Phosphin (PH3)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00150','','Glühverlust',63,null,null,null,0.0,0.0,1,null,null,null,null,1,0,1458,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00151','','Lufttemperatur',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00152','','Ethan (C2H6)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00153','','Propan (C3H8)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00154','','Butan (C4H10)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00155','','1,1-Dichlorethan (HCCl2-CH3)',42,0.0,null,null,null,0.0,1,null,null,null,null,1,1,null,17);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00156','','1,2-Dichlorethen (HCCl=CClH)',42,0.0,null,null,null,0.0,1,null,null,null,68,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00157','','Kohlenstoff, gesamt (C)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00158','','Ameisensäure (HCOOH)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00159','','Formaldehyd (HCHO)',42,null,null,null,null,0.0,1,null,null,null,null,1,0,3003,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00160','','Erdalkalien, Summe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00162','','cis-1,2-Dichlorethen (HCCl=CClH)',44,null,null,null,null,0.0,1,null,null,null,null,1,0,2028,17);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00163','','trans-1,2-Dichlorethen (HCCl=CClH)',44,null,null,null,null,0.0,1,null,null,null,null,1,0,2029,17);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00164','','n-Hexan-extrahierbare Stoffe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00165','','Geschmack',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00166','','2-Chlorphenol',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00167','','2,4-Dichlorphenol',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00168','','2,6-Dichlorphenol',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00169','','2,3,6-Trichlorphenol',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00170','','2,4,6-Trichlorphenol',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00171','','2,3,5,6-Tetrachlorphenol',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00172','','HKW (Halogenierte Kohlenwasserstoffe)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00173','','Keimzahl in Agar',42,null,null,null,null,0.0,1,null,null,null,58,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00174','','Keimzahl in Gelatine',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00175','','Kohlensäure, kalk-aggr., rechn.',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00176','','BTX-Summe (Benzol,Toluol,Xylol)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,2950,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00177','','Glührückstand',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00178','','pH-Wert nach CaCO3-Sättigung',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00180','','meta/para-Xylol (Summe)',44,500.0,null,null,null,0.0,1,null,null,2,null,1,0,2896,18);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00181','','Campylobacter',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00182','','Chlor, gesamt (Cl)',42,null,null,null,10000.0,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00183','','Phenole',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00184','','Fette',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00185','','Öle',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00186','','Organische Lösungsmittel',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00187','','Chrom-III',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00188','','Chromat (CrVI)',42,0.2,null,null,0.0,4.06,1,null,null,3,27,1,0,null,11);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00189','','organische Chlorverbindungen',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00190','','Strontium',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00191','','Bromid (Br-)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00192','','Jodid (J-)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00193','','Hydrogenphosphat (HPO4--)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00194','','Carbonat (CO3--)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00195','','1.1-Dichlorethen (Cl2C=CH2)',42,0.0,null,null,null,0.0,1,null,null,null,69,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00196','','Luftdruck',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00197','','Dichte',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00198','','Radioaktivität',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00199','','Restaktivität',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00200','','Lithium (Li)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00201','','meta-Borsäure (HBO2-)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00202','','Gesamtmineralstoffgehalt',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00203','','Sauerstoff, gelöst (O2)',42,0.0,null,null,null,0.0,1,null,null,null,61,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00204','','Kohlendioxid, frei (CO2)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00205','','Antimon (Sb)',42,0.5,null,null,55.0,4.64,1,null,null,3,22,1,0,1145,3);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00206','','Beryllium (Be)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00207','','Cäsium (Cs)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00208','','Molybdän (Mo)',42,null,null,null,0.0,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00209','','Rubidium (Rb)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00210','','Silber (Ag)',42,1.0,null,5.3,null,17.4,1,null,null,3,33,1,0,1162,3);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00211','','Uran (U)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00212','','Vanadium (Va)',42,null,null,null,100.0,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00213','','Zinn (Sn)',42,1.0,null,null,100.0,17.4,1,null,null,3,36,1,0,1137,3);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00214','','Kieselsäure (H2SiO3)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00215','','UV-Extinktion bei 436 nm (SAK 436)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00216','','Chloroform-extrahierbare Stoffe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00217','','1,12-Benzperylen',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00218','','11,12-Benzfluoranthen',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00219','','3,4-Benzpyren',42,null,null,null,null,24.36,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00220','','Dibrommethan',42,null,null,null,null,0.0,1,null,null,null,null,1,0,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00221','','4-Chlorphenol',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00222','','2,3,4,6-Tetrachlorphenol',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00223','','ortho-Kresol',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00224','','meta-Kresol',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00225','','para-Kresol',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00226','','Hydrazin (H2N-NH2)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00227','','EOX',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00228','','Kohlensäure, kalkangreifend',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00229','','Koloniezahl',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00230','','Schwebestoffe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00231','','Trichlorfluormethan (CFCl3)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00232','','Trichlortrifluorethan (C2Cl3F3)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00233','','1.1.2-Trifluor-1.2.2-Trichlorethan',42,0.0,null,null,null,0.0,1,null,null,null,70,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00234','','Freon 113',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00235','','Styrol (C6H5-CH=CH2)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00236','','n-Propylbenzol (C6H5-C3H7)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00237','','Aromatische Kohlenwasserstoffe, Summe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00238','','3,4-Benzfluoranthen',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00239','','Keimzahl bei 20 Grad C',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00240','','Keimzahl bei 37 Grad C',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00242','','Indol',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00243','','Benzo-b-Pyrrol',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00244','','Natriumhydrogencarbonat (NaHCO3)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00245','','Haloforme, Summe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00246','','flüchtige Halogenkohlenwasserstoffe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00247','','Ethyltoluole, gesamt',42,0.0,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00248','','Methylrotprobe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00249','','Voges-Proskauer-Reaktion',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00250','','Säurebildner',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00251','','Citratlösung nach KOSER',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00252','','Sulfit (SO3)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00253','','PCB-Isomere, Summe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00254','','Chlorbenzol',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00255','','Dichlorbenzole',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00256','','Trichlorbenzole',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00257','','Tetrachlorbenzole',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00258','','Hexachlorethan',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00259','','Hexachlorbutadien',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00260','','Tetrachlorethan',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00261','','Pentachlorethan',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00262','','Benzo(k)fluoranthen',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,19);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00263','','Benzo(b)fluoranthen',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,19);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00264','','Benzo(ghi)perylen',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,19);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00265','','Nichtcarbonathärte',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00266','','Polyphosphate',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00267','','Chlorphenole',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00268','','Harnstoff (O=C-(NH2)2)',42,0.0,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00269','','Toxizitätstest (TTC-Test)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00270','','Summe Calcium (Ca) und Magnesium (Mg)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00271','','2,4,5-Trichlorphenol',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00272','','Fäulnisfähigkeit',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00273','','Phosphor (P2O5) ges.',63,null,null,null,0.0,0.0,1,null,null,null,null,1,0,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00274','','Calciumcarbonatsättigung',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00275','','Hydrolysierbares Phosphat',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00276','','Desethylatrazin',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00277','','Chlortoluron',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00278','','Isoproturon',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00279','','Metoxuron',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00280','','Diuron',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00281','','Metobromuron',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00282','','Methabenzthiazuron',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00283','','Metazachlor',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00284','','Metolachlor',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00285','','Kationen, Summe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00286','','Anionen, Summe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00287','','Aluminium, gelöst',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00288','','Basenkapazität bis pH 8.2',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00289','','Säurekapazität bis pH 8.2',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00290','','1,1,2-Trichlorethan (HCCl2-CClH2)',42,0.0,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00291','','1,3-Dichlorpropan (CH2-(CClH2)2)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00292','','1,3-Dichlorpropen, Summe cis- und trans-',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00293','','Phenanthren',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00294','','1,3-Dichlorbenzol',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00295','','1,4-Dichlorbenzol',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00296','','1,2-Dichlorbenzol',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00297','','1,2,4-Trichlorbenzol',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00298','','1,2,4,5-Tetrachlorbenzol',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00299','','Pentachlorbenzol',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00300','','Hexachlorbenzol',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00301','','Octan (H3C-(CH2)6-CH3)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00302','','Nonan (H3C-(CH2)7-CH3)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00303','','Decan (H3C-(CH2)8-CH3)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00304','','Undecan (H3C-(CH2)9-CH3)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00305','','Dodecan (H3C-(CH2)10-CH3)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00306','','Tridecan (H3C-(CH2)11-CH3)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00307','','Tetradecan (H3C-(CH2)12-CH3)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00308','','Pentadecan (H3C-(CH2)13-CH3)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00309','','Hexadecan (H3C-(CH2)14-CH3)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00310','','Mineralöl, Summe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00311','','Fäkalstreptokokken',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00312','','Pseudomonas aeruginosa',42,0.0,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00313','','sulfitreduz. sporenb. Anaerobier',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00314','','Gesamtkoloniezahl (EV K 5, 48h, 20 Grd)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00315','','Tenside',42,null,null,null,null,0.0,1,null,null,null,null,1,0,1608,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00316','','TOX (Summe toxisch wirkender Gase)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00333','','IR-aktive Kohlenwasserstoffe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00334','','Hexan (C6H14)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00335','','Heptan (C7H16)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00336','','Isotoluron',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00337','','Bacterium pyocyaneum',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00338','','Absetzbare Stoffe',46,10.0,null,null,null,0.0,1,null,null,null,null,1,1,1452,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00339','','schwerflüchtige lipophile Stoffe',42,300.0,null,null,null,11.6,1,null,null,3,13,1,0,1570,27);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00341','','Diphenyl',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00342','','10821',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00343','','2-Methyl-Naphthalin',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00344','','2,6-Dimethyl-Naphthalin',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00345','','Heizöl',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00346','','Kohlensäure, zugehörige',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00348','','Polytest',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00349','','Vinylchlorid (CH2=CHCl)',42,0.0,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00350','','Cyanwasserstoff (HCN)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00351','','organische Stoffe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00364',null,'1.2.3-Trimethylbenzol',44,500.0,null,null,null,0.0,1,null,null,null,91,1,0,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00365','','Bromacil',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00366','','Waschaktive Substanzen',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00367','','Pentan (C5H12)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00368','','Naphthalin',42,null,null,null,null,24.36,1,null,null,null,null,1,1,null,19);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00369','','Acenaphthylen',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,19);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00370','','Acenaphthen',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,19);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00371','','Fluoren',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,19);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00372','','Anthracen',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,19);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00373','','Pyren',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,19);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00374','','Chrysen',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,19);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00375','','Benzo-a-anthracen',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,19);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00376','','Dibenzo-(a,h,i)-anthracen',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,19);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00377','','Kohlendioxid, gelöst (CO2)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00378','','PBSM (Summenparameter)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00379','','PAK-Summe nach EPA',42,null,null,null,null,17.4,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00380','','Abbaubarkeit (Nr. 407 AbwV)',63,null,null,null,null,0.0,1,null,null,3,null,1,0,1609,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00381','','Abfiltrierbare Stoffe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00382','','Aliphatische Kohlenwasserstoffe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00383','','1.2.4-Trimethylbenzol',44,500.0,null,null,null,0.0,1,null,null,null,92,1,0,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00384','','Thallium (Tl)',42,0.2,null,null,1.0,17.4,1,null,null,3,34,1,0,1132,14);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00385','','Summe der Ca- und Mg-Ionen',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00386','','Desisopropylatrazin',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00387','','Metamitron',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00388','','Fenuron',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00389','','Chloridazon',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00390','','Hexazinon',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00391','','Metribuzin',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00392','','Cyanazin',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00393','','Monolinuron',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00394','','Propham',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00395','','Buturon',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00396','','Propazin',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00397','','Terbutylazin',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00398','','Linuron',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00399','','Chloroxuron',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00400','','Chlorpropham',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00401','','nicht-ionische Tenside',42,null,null,null,null,0.0,1,null,null,null,null,1,0,1567,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00402','','Pestizide, Summe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00403','','kationische Tenside',42,null,null,null,null,0.0,1,null,null,null,null,1,0,1564,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00404','','Anionenequivalente, Summe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00405','','Kationenequivalente, Summe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00406','','Titan (Ti)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00407','','Dichlordifluormethan (CF2Cl2)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00408','','Zirkonium (Zr)',42,1.0,null,null,null,34.8,1,null,null,3,37,1,0,1130,3);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00409','','Ausgasung',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00411','','LHKW (leichtfl.halogen.KW)',44,1000.0,null,null,null,23.2,1,null,null,3,39,1,0,2045,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00412','','Hemellitol',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00413','','Tetralin',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00414','','Sebutylazin',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00415','','Pestizide bei pH 7',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00416','','CSB-Abbautest',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00417','','Phenmedipham',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00418','','Prometryn',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00419','','Terbutryn',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00420','','Bentazon',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00421','','Chlopyralid',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00422','','MCPA',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00423','','Mecoprop',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00424','','Glyphosat',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00425','','Schöpfzeitraum',0,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00426',null,'Alachlor',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00427',null,'Crimidin',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00428',null,'Desethylterbutylazin',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00429',null,'Aldicarb',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00430',null,'PAK-Summe nach TVO',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00431',null,'MTBE',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00600','','pH-Wert bei Probenahme',0,10.0,null,null,0.0,0.0,1,null,null,3,2,1,0,1061,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00601','','Chromat (CrVI) bei Probenahme',42,0.2,null,null,null,0.0,1,null,null,null,7,1,0,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00602','','lipophile Stoffe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,1570,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00603','','CSB/BSB5-Verhältnis',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00604',null,'Nitrit (NO2) bei Probenahme',42,10.0,null,null,null,0.0,1,null,null,3,6,1,1,1246,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00606','','Absetzbare Stoffe bei Probenahme',46,10.0,null,null,null,0.0,1,null,null,3,4,1,0,1452,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00607','','Bismut-Komplexierungsindex',42,null,null,null,null,33.64,1,null,null,3,54,1,0,1562,22);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00608','','Diethylether',42,null,null,null,null,52.01,1,null,null,3,null,1,0,null,28);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00609','','Bakterienleuchthemmung',130,4.0,null,null,null,29.0,1,null,null,3,52,1,0,1674,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00610','','Palladium (Pd)',42,1.0,null,null,null,40.55,1,null,null,3,null,1,0,1187,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00611',null,'Mercaptanschwefel',42,2.0,null,null,null,25.52,1,null,null,null,null,1,0,1317,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00612',null,'Benzol und Derivate',44,1000.0,null,null,null,11.6,1,null,null,3,45,1,0,null,18);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00613',null,'PCDD, PCDF',131,2.0,null,null,0.0,290.0,1,null,null,3,53,1,0,2490,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B00614',null,'Chlorethen',44,null,null,null,null,0.0,1,null,null,3,null,1,0,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B30001','','Boden',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B30002',null,'Bauschutt',0,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B30003','','Haushaltsmüll',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B30004','','Industriemüll',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B30010','','Prioritätengruppe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B30021','','Fläche',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B30022','','Volumen',14,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B30101','','Wohngebäude',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B30102','','Gewerbegebäude',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B30103','','Landwirtschaftliche Gebäude',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B30104','','Öffentliche Gebäude',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B30105','','Kleingärten',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B30106','','Sportanlagen',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B30107','','Park',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B30108','','Park mit Spielplatz',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B30109','','Parkplatz',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B30110','','Landwirtschaftliche Nutzfläche',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B30111','','Forstwirtschaftliche Nutzfläche',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B30112','','Unland',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B30113','','Deponie in Betrieb',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B30114','','Abgrabung',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B30116','','Wasserschutzgebiet, ausgewiesen',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B30117','','Wasserschutzgebiet, geplant',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('B30120','','nicht bebaubar',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('BK0001','','Gattung/Art/Sorte',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('BK0002','','Ortslage, inner-/außerhalb',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('BK0003','','Pflanzung',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('BK0004','','Pflanzebene',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('BK0011','','Überdeckung: Vegetationsfläche',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('BK0012','','Überdeckung: Mulchfläche',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('BK0013','','Überdeckung: offener Boden',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('BK0014','','Überdeckung: Baumscheibenplatten o.ä.',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('BK0015','','Überdeckung: wassergebundene Decke',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('BK0016','','Überdeckung: überdeckt, befestigt',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('BK0021','','Erscheinungsbild',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('BK0022','','Wurzeln',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('BK0023','','Stamm',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('BK0024','','Krone',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('BK0025','','Belaubung',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('BK0026','','Vitalität',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('BK0031','','Alter, bezogen auf Pflanzjahr',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('BK0032','','Umfang in 1m Höhe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('BK0033','','Höhe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('BK0034','','Kronenbreite',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('G00001','','Gewerbegruppenschlüssel',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('G00002','','Anzahl der Arbeitsschichten',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('G00003','','Anzahl der Beschäftigten',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('G00004','','Arbeitsbeginn Montag bis Freitag',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('G00005','','Arbeitsende Montag bis Freitag',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('G00006','','Arbeitsbeginn Samstag',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('G00007','','Arbeitsende Samstag',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('G00008','','Arbeitsbeginn Sonntag',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('G00009','','Arbeitsende Sonntag',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('G00010','','Frischöl',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('G00011','','Altöl',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('G00012','','Lacke',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('G00013','','Säuren',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('G00014','','Lösungsmittel',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('G00015','','Laugen',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('G00016','','sonstige wassergefährdende Stoffe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('G00017','','sanitäres Abwasser',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('G00018','','Kühlwasser',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('G00019','','Spülwasser',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('G00020','','produktionsbelastetes Abwasser',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('G00021','','sonstiges Abwasser',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('G10004','','Konsistenz',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L10110','','Temperatur',61,25.0,null,null,null,0.0,1,null,null,null,null,1,1,1011,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L10111',null,'Temperatur bei Probenahme',61,35.0,null,null,null,0.0,1,null,null,3,1,1,0,1011,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L10230','','Färbung',42,0.0,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L10280','','UV-Extinktion (SAK 254nm)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L10350','','Trübung',0,null,null,null,null,0.0,1,null,null,null,null,1,1,1035,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L10420','','Geruch',0,null,null,null,null,0.0,1,null,null,null,null,1,1,1042,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L10610','','pH-Wert',0,10.0,null,null,0.0,0.0,1,null,null,null,null,1,1,1061,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L10720','','Redoxpotential',42,0.0,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L10820','','Leitfähigkeit',73,null,null,null,0.0,0.0,1,null,null,null,null,1,1,1082,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L10821',null,'Leitfähigkeit bei Probenahme',73,null,null,null,0.0,0.0,1,null,null,3,3,1,0,1082,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L11120','','Natrium (Na)',42,null,null,null,0.0,0.0,1,null,null,null,null,1,0,1112,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L11130','','Kalium (K) K2O',63,null,null,null,0.0,0.0,1,null,null,null,null,1,0,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L11210','','Magnesium (Mg) MgO',63,null,null,null,0.0,0.0,1,null,null,null,64,1,0,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L11220','','Calcium (Ca) ges. als CaO',63,null,null,null,0.0,0.0,1,null,null,null,62,1,0,null,1);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L11310','','Aluminium (Al)',42,null,null,null,null,0.0,1,null,null,3,21,1,0,1131,3);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L11380','','Blei (Pb)',42,1.0,null,34.0,200.0,4.64,1,null,null,3,24,1,0,1138,3);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L11400',null,'Gold',42,null,null,null,null,0.0,1,null,null,null,null,1,0,null,3);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L11420','','Arsen (As)',42,0.5,null,null,10.0,4.64,1,null,null,3,23,1,0,1142,3);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L11510','','Chrom (Cr)',42,1.0,null,24.0,200.0,5.8,1,null,null,3,26,1,0,1151,3);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L11610','','Kupfer (Cu)',42,1.0,null,250.0,550.0,5.8,1,null,null,3,29,1,0,1161,3);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L11640','','Zink (Zn)',42,2.0,null,800.0,1400.0,5.8,1,null,null,3,35,1,0,1164,3);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L11650','','Cadmium (Cd)',42,0.1,null,0.6,2.5,4.64,1,null,null,3,25,1,0,1165,3);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L11660','','Quecksilber (Hg)',42,0.05,null,0.7,2.0,6.38,1,null,null,3,31,1,0,1166,13);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L11710','','Mangan (Mn)',42,null,null,null,2500.0,0.0,1,null,null,null,null,1,0,1171,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L11820','','Eisen (Fe) ges.',42,null,null,null,null,0.0,1,null,null,null,null,1,1,1182,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L11880','','Nickel (Ni)',42,1.0,null,17.0,80.0,5.8,1,null,null,3,30,1,0,1188,3);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L12110','','Bor (B)',42,null,null,null,0.0,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L12120','','Silicium (Si)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L12130','','Silikat (SiO2)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L12180','','Selen (Se)',42,1.0,null,null,0.0,4.64,1,null,null,3,32,1,0,1218,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L12310','','Cyanide (CN)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,1231,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L12340','','Cyanid, leicht freisetzbar',42,1.0,null,null,null,8.93,1,null,null,3,16,1,0,1234,6);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L12450','','Nitrat-Stickstoff (N) NO3-N',42,null,null,null,null,0.0,1,null,null,null,null,1,0,1245,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L12470','','Nitrit-Stickstoff (N) NO2-N',42,10.0,null,null,null,4.06,1,null,null,3,18,1,0,1247,7);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L12490','','Ammonium- Stickstoff (N) NH4-N',42,null,null,null,null,0.0,1,null,null,null,null,1,0,1249,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L12640','','ortho-Phosphat-Phosphor (o-PO4-P)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L12810','','Sauerstoff (O2)',42,0.0,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L13130','','Sulfat (SO4)',42,600.0,null,null,null,9.28,1,null,null,3,15,1,0,1313,9);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L13131',null,'Sulfat (SO4) bei Probenahme',42,600.0,null,null,null,0.0,1,null,null,null,5,1,1,1313,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L13210','','Fluorid (F)',42,50.0,null,null,null,6.15,1,null,null,3,20,1,0,1321,10);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L13310',null,'Chlorid (Cl)',42,null,null,null,null,0.0,1,null,null,3,55,1,0,1331,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L13380',null,'Chlor, freies (Cl2)',42,0.5,null,null,null,5.8,1,null,null,3,null,1,0,1338,28);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L13381',null,'Chlor, freies (Cl2) bei Probenahme',42,0.5,null,null,null,0.0,1,null,null,3,8,1,0,1338,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L13430','','AOX',42,1.0,null,0.5,400.0,23.2,1,null,null,3,38,1,0,1343,21);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L14310','','Abdampfrückstand',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L14520','','Absetzbare Stoffe ml/l',42,0.0,null,null,null,0.0,1,null,null,null,59,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L14550','','Absetzbare Stoffe mg/l',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L14720','','Säurekapazität (m-Wert)',42,0.0,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L15000','','Gelöste Stoffe',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L15210','','DOC',42,null,null,null,null,0.0,1,null,null,null,null,1,0,1521,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L15230','','TOC (Organischer Kohlenstoff, ges.)',42,null,null,null,null,6.38,1,null,null,3,86,1,0,1523,20);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L15310','','KMnO4-Verbrauch',42,20.0,null,null,null,0.0,1,null,null,null,73,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L15330','','CSB',42,null,null,null,null,33.64,1,null,null,3,9,1,0,1533,24);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L15470','','Phenolindex',42,100.0,null,null,null,7.54,1,null,null,3,14,1,0,1547,23);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L15500','','Kohlenwasserstoff-Index',42,20.0,null,null,null,12.18,1,null,null,3,12,1,0,1552,26);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L15610','','Anionische Tenside (MBAS)',42,null,null,null,null,0.0,1,null,null,3,null,1,0,1561,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L16250','nur wenn CSB > 300 mg/l','BSB 5',42,null,null,null,null,13.92,1,null,null,3,10,1,0,1635,25);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L16710','','Fischgiftigkeit',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L16900','','Koloniezahl in Agar 20 Cel',42,100.0,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L16901','','Koloniezahl in Agar 36 Cel',42,100.0,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L20000','','Dichlormethan (H2CCl2)',44,500.0,null,null,null,0.0,1,null,null,null,null,1,0,2000,17);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L20020','','Tetrachlormethan (CCl4)',44,null,null,null,null,0.0,1,null,null,null,null,1,0,2002,17);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L20030','','Tribrommethan (HCBr3)',42,null,null,null,null,0.0,1,null,null,null,null,1,0,2003,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L20050','','Dichlorethan (H2CCl-CClH2)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L20060','','Dichlorbrommethan (HCBrCl2)',42,null,null,null,null,0.0,1,null,null,null,null,1,0,2006,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L20070','','Chlordibrommethan (HCClBr2)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L20100','','1,1,1-Trichlorethan (CCl3-CH3)',44,500.0,null,null,null,11.6,1,null,null,1,null,1,0,2010,17);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L20200','','LEER (ehem. Tri)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L20210','','LEER (ehem. Tetra)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L20250','','1,2-Dichlorpropan (H2CCl-HCCl-CH3)',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L21400','','Pentachlorphenol',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L23000','','Fluoranthen',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L23300','','Indeno-(1,2,3-cd)-pyren',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,19);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('L23500','','Kohlenwasserstoffe,polycyclische aromat.',42,null,null,null,null,0.0,1,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('N00001',null,'DEHP',42,null,null,null,0.0,0.0,1,null,null,null,null,1,0,2679,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('N00002',null,'Basisch wirksame Substanz als CaO',63,null,null,null,0.0,0.0,1,null,null,null,null,1,0,1228,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('N00003',null,'Schwefel',63,null,null,null,0.0,0.0,1,null,null,null,null,1,0,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('P00001',null,'Gesamtgehalt NH4-N (CaCI-löslich)',63,null,null,null,0.0,null,null,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('P00002',null,'Summe 2 PFT',42,null,null,null,100.0,116.0,1,null,null,3,87,1,0,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('P00003',null,'Fluor',43,null,null,null,0.0,null,null,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('P00004',null,'PCP',43,null,null,null,5.0,null,null,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('P00005',null,'Salmonellen',0,null,null,null,0.0,null,null,null,null,null,null,1,0,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('P00006',null,'Salmonella senftenberg W 775',0,null,null,null,0.0,null,null,null,null,null,null,1,0,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('P00007',null,'Kjeldahl-Stickstoff',42,null,null,null,null,null,null,null,null,null,null,1,0,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('P00008',null,'AOX-SPE',42,null,null,null,null,null,null,null,null,null,null,1,0,1343,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('P00009','','Sulfid (S2-), ges.',42,2.0,null,null,null,0.0,1,null,null,null,null,1,0,1311,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('P00010',null,'Ammonium (NH4)',42,null,null,null,null,null,null,null,null,null,null,1,0,1248,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('P00011',null,'BSB5/CSB-Verhältnis',0,null,null,null,null,0.0,null,null,null,null,null,1,0,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('P00012',null,'Phosphor (P) ges.',63,null,null,null,0.0,0.0,1,null,null,null,null,1,0,null,15);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('P00013',null,'Abwassermenge',14,null,null,null,null,0.0,1,null,null,null,null,1,0,901,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('P00014',null,'TOC-Tagesfracht',22,null,null,null,null,0.0,null,null,null,null,null,1,0,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('P00015',null,'DOC-Tagesfracht',22,null,null,null,null,0.0,null,null,null,null,null,1,0,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('P00016',null,'TOC-Tagesfracht (2 Tanks)',22,null,null,null,null,null,null,null,null,null,null,1,1,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('P00017',null,'Summe 10 PFT',42,null,null,null,100.0,139.2,1,null,null,3,88,1,0,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('Z00001',null,'EDTA',42,null,null,null,null,0.0,1,null,null,null,null,1,0,2605,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('Z00002',null,'Tellur',42,null,null,null,null,0.0,1,null,null,null,null,1,0,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('Z00003',null,'PFOA',44,null,null,null,null,null,null,null,null,null,89,1,0,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('Z00004',null,'PFOS',44,null,null,null,null,null,null,null,null,null,90,1,0,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('Z00005',null,'Trockenrückstand',133,null,null,1.0,null,null,null,null,null,null,null,1,0,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('Z00006',null,'Gefriertrockenrückstand',133,null,null,1.0,null,null,null,null,null,null,null,0,0,null,null);
INSERT INTO "LABOR"."PARAMETER" (ORDNUNGSBEGRIFF,ANALYSEVERFAHREN,BEZEICHNUNG,WIRDGEMESSENINEINHEIT,GRENZWERT,GRENZWERTNAME,SIELHAUT_GW,KLAERSCHLAMM_GW,PREISFUERANALYSE,EINZELNBEAUFTRAGBAR,KENNZEICHNUNG,KONSERVIERUNG,PARAMETERGRUPPE_ID,REIHENFOLGE,_ENABLED,_DELETED,DEA_STOFFE_STOFF_NR,ANALYSEMETHODE_ID) VALUES ('Z00007','','Jahresabwassermenge',35,null,null,null,null,0.0,1,null,null,3,94,1,0,913,null);



INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (1,'---','--','-','nicht zugeordnet');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (2,'E22','DO','1','DIN EN ISO 11885 April 1998');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (3,'E22','DO','2','DIN EN ISO 11885 Sep 2009');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (4,'D06','DO','1','DIN EN ISO 14403, Abschnitt 5.1');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (6,'D06','DO','2','DIN EN ISO 14403, Abschnitt 5.2');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (7,'D10','DE','1','DIN EN 26777');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (8,'D11','DO','1','DIN EN ISO 6878, Abschnitt 4');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (9,'D20','DO','1','DIN EN ISO 10304-2');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (10,'D20','DO','2','DIN EN ISO 10304-1 Juli 2009');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (11,'D24','DN','1','DIN 38405-D24');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (12,'D27','DN','1','DIN 38405-D27');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (13,'E12','DE','5','DIN EN 1483, Absch 4 Juli 2007');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (14,'E22','DO','2','DIN EN ISO 11885 Sep 2009');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (15,'E29','DO','1','DIN EN ISO 17294-2');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (16,'F03','DN','3','DIN 38407-F3-3');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (17,'F04','DO','1','DIN EN ISO 10301, Abschnitt 2');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (18,'F09','DN','1','DIN 38407-F9-1');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (19,'F39','DN','1','DIN 38407-39');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (20,'H03','DE','1','DIN EN 1484');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (21,'H14','DO','1','DIN EN ISO 9562 Abschnitt 9.3.2');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (22,'H26','DN','1','DIN 38409-H26');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (23,'H37','DO','1','DIN EN ISO 14402, Abschnitt 3, FIA');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (24,'H41','DN','1','DIN 38409-H41-1');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (25,'H51','DE','1','DIN EN 1899-1, Abschnitt 8.4.1');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (26,'H53','DO','1','DIN EN ISO 9377-2');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (27,'H56','DV','1','DEV V H56');
INSERT INTO "ELKA"."MAP_ELKA_ANALYSEMETHODE" (ID,GRUPPE_DEV_ID,REGELWERK_ID,VARIANTEN_ID,METHODEN_NR) VALUES (28,'000','00','0','nach Laborjournal');


INSERT INTO "LABOR"."PARAMETERGRUPPEN" (ID,NAME,PREISFUERANALYSE,_ENABLED,_DELETED) VALUES (1,'LHKW (leichtfl.halogen.KW)',0.0,1,0);
INSERT INTO "LABOR"."PARAMETERGRUPPEN" (ID,NAME,PREISFUERANALYSE,_ENABLED,_DELETED) VALUES (2,'Benzol und Derivate',0.0,1,0);
INSERT INTO "LABOR"."PARAMETERGRUPPEN" (ID,NAME,PREISFUERANALYSE,_ENABLED,_DELETED) VALUES (3,'E-Satzung',null,1,0);

INSERT INTO "BASIS"."SACHBEARBEITER" (KENNUMMER,NAME,ZEICHEN,ZIMMER,TELEFON,EMAIL,GEHOERTZUARBEITSGR,_ENABLED,_DELETED,ID) VALUES ('007' /*not nullable*/,'s','s','s','s','s','s',1 /*not nullable*/,0 /*not nullable*/,1 /*not nullable*/);

INSERT INTO "PUBLIC"."TAB_STREETS_ALKIS" (PLZ,NAME,NR,X,Y,GEMEINDE,SCHL,HAUSNR,HAUSNR_ZUSATZ,ABGLEICH, ID) VALUES ('12345','Musterstraße','1a',0,0,'s','s',1,'a','1',1);



INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (0,'k. A.','AwSV',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (1,'Tankstelle','AwSV',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (2,'Betriebstankstelle','AwSV',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (3,'Gefahrstofflager','AwSV',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (4,'Altöllager','AwSV',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (5,'Chemische Reinigung','AwSV',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (6,'Schrottplatz (o. Entfallst.)','AwSV',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (7,'Galvanik','AwSV',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (8,'Kfz-Werkstatt','AwSV',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (9,'Metallbe- und Verarbeitung','AwSV',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (10,'Energie-u.Hydr.aggregate','AwSV',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (11,'Heizöllager','AwSV',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (12,'BImSchG-Land','AwSV',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (13,'SüwVO-Abw-Teil1','Indirekt-Einl.',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (21,'Lackieranlage','AwSV',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (22,'Gewerbeobjekt','AwSV',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (23,'Chemische Industrie','AwSV',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (24,'Altautoabstellflächen','AwSV',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (32,'Probenahmepunkt','Indirekt-Einl.',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (33,'Gewerbeobjekt','Indirekt-Einl.',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (34,'Abwasserbehandlungsanlage','Indirekt-Einl.',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (40,'Sielhautmessstelle','Indirekt-Einl.',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (42,'Genehmigung','Einleiter',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (44,'Stellungnahme zu BA','Indirekt-Einl.',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (47,'Biogasanlage','AwSV',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (48,'Betriebserfassung','Indirekt-Einl.',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (49,'BImSchG-kommunal','AwSV',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (50,'Gewerbepark','Indirekt-Einl.',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (55,'Kälteanlagen','AwSV',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (56,'Erdwärmeanlagen','AwSV',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (57,'JGS-Anlagen','AwSV',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (59,'BHKW','AwSV',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (61,'BImSchG-BR','Indirekt-Einl.',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (64,'BImSch-Kommunal','Indirekt-Einl.',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (65,'Anfallstelle','Einleiter',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (66,'Einleitungsstelle','Einleiter',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (68,'Sonderbauwerk','Direkt-Einl.',null,true,false);
INSERT INTO "BASIS"."OBJEKTARTEN" (id,objektart,abteilung,kategorie,_enabled,_deleted) VALUES (69,'Entwässerungsgrundstück','Direkt-Einl.',null,true,false);




INSERT INTO "BASIS"."SACHBEARBEITER" (kennummer,name,zeichen,zimmer,telefon,email,gehoertzuarbeitsgr,_enabled,_deleted) VALUES ('u633x','Müller, Lieschen','MUE','WH#6','9012','lieschen.mueller@musterstadt.eu','360.33',true,false);
INSERT INTO "BASIS"."SACHBEARBEITER" (kennummer,name,zeichen,zimmer,telefon,email,gehoertzuarbeitsgr,_enabled,_deleted) VALUES ('u633y','Modaal, Jan','JM','Z105','1234','jan.modaal@musterstadt.eu','360.33',true,false);
INSERT INTO "BASIS"."SACHBEARBEITER" (kennummer,name,zeichen,zimmer,telefon,email,gehoertzuarbeitsgr,_enabled,_deleted) VALUES ('u633z','Yilmaz, Marie','MY','Z112','5678','marie.yilmaz@musterstadt.eu','360.33',true,false);




INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (0,'ohne',true,false,23);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (1,'km',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (2,'m',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (3,'cm',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (4,'mm',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (5,'m über NN',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (6,'1/m',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (7,'m u.Gel.',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (8,'m unter NN',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (9,'Code',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (10,'mg/kg TS',true,false,50);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (11,'km2',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (12,'ha',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (13,'m2',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (14,'m³',true,false,47);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (15,'l',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (16,'ml',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (21,'t',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (22,'kg/d',true,false,41);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (23,'g',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (24,'mg',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (28,'m3/Monat',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (29,'m3/Std.',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (30,'m3/Tag',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (31,'m/s',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (32,'m3/s',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (33,'m3/2h',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (34,'m3/24h',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (35,'m3/Jahr',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (36,'l/min',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (37,'l/s',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (38,'l/s*k',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (39,'l/s*h',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (41,'g/l',true,false,27);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (42,'mg/l',true,false,7);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (43,'mg/kg',true,false,50);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (44,'µg/l',true,false,10);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (45,'mval/l',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (46,'ml/l',true,false,8);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (47,'pg/l',true,true,58);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (51,'g/m3',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (52,'mg/m3',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (53,'mmol/l',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (54,'mmol/m3',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (55,'t/Tag',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (56,'kg/Tag',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (57,'kg/h',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (58,'t/Jahr',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (61,'°C',true,false,4);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (62,'Grad dH',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (63,'%',true,false,5);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (64,'in h',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (65,'mbar',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (66,'in 1 ml',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (67,'in 100 ml',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (68,'in 1/10 ml',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (69,'in 1/100 ml',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (71,'pS',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (72,'mS/m',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (73,'µS/cm',true,false,28);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (74,'mV',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (90,'m-1',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (91,'Vol.-%',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (92,'ppm',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (93,'vpm',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (99,'Stück',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (100,'µg/m3',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (102,'ng/l',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (103,'kN/m3',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (104,'µg/kg',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (105,'nm/s',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (106,'l/Std.',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (110,'m3/120 Std.',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (111,'m3/10 Std.',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (112,'pro Jahr',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (113,'m3/5 Jahre',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (114,'cm2/s',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (115,'m2/s',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (116,'hPa',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (117,'Std.',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (118,'Jahre',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (119,'Bewertungsstufe',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (120,'min',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (121,'in 50 ml',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (122,'in 20 ml',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (123,'in 10 ml',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (124,'g/kg',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (125,'in 250 ml',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (126,'Uhr',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (127,'mm/Monat',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (128,'mm/Jahr',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (130,'GL',true,true,null);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (131,'ng/kg TS',true,false,54);
INSERT INTO "LABOR"."EINHEITEN" (id,bezeichnung,_enabled,_deleted,dea_einheiten_masseinheiten_nr) VALUES (132,'neue Einheit',false,true,null);




INSERT INTO "LABOR"."KLAERANLAGE" (id,anlage,dea_klaeranlage_nr,_enabled,_deleted) VALUES (1,'Zentrale Kläranlage',301,true,false);




INSERT INTO "AWSV"."STANDORTGGHWSG" (id,standortgg,_enabled,_deleted) VALUES (0,'sonstiges Geb.',true,false);
INSERT INTO "AWSV"."STANDORTGGHWSG" (id,standortgg,_enabled,_deleted) VALUES (1,'Wasserschutzgebiet Zone I',true,false);
INSERT INTO "AWSV"."STANDORTGGHWSG" (id,standortgg,_enabled,_deleted) VALUES (2,'Wasserschutzgebiet Zone II',true,false);
INSERT INTO "AWSV"."STANDORTGGHWSG" (id,standortgg,_enabled,_deleted) VALUES (3,'Wasserschutzgebiet Zone III/III A',true,false);
INSERT INTO "AWSV"."STANDORTGGHWSG" (id,standortgg,_enabled,_deleted) VALUES (4,'Wasserschutzgebiet Zone III B',true,false);
INSERT INTO "AWSV"."STANDORTGGHWSG" (id,standortgg,_enabled,_deleted) VALUES (5,'Heilquellenschutzgebiet',true,false);




INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (1,16,'27',{d '2002-08-01'},null,'AbwVerO','Behandlung von Abfällen durch chemische und physikalische Verfahren (CP-Anlagen) sowie Altölaufbereitung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (2,34,'6',{d '1990-01-01'},{d '1998-12-31'},'RahmenAbwV','Herstellung von Erfrischungsgetränken und Getränkeabfüllung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (3,49,'54',{d '1999-01-01'},null,'AbwVerO','Herstellung von Halbleiterbauelementen','NAD_BR1',{d '1980-01-01'},{d '2009-11-30'},{ts '2009-11-30 08:50:44.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (4,64,'22',{d '1992-01-01'},{d '1998-12-31'},'RahmenAbwV','Mischabwasser','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (5,27,'57',{d '1999-01-01'},null,'AbwVerO','Wollwäschereien','NAD_BR1',{d '1980-01-01'},{d '2009-11-30'},{ts '2009-11-30 08:49:35.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (6,21,'30',{d '1983-10-01'},{d '1989-12-31'},'AbwVerwVor','Sodaherstellung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (7,2,'50',{d '1990-01-01'},{d '1998-12-31'},'RahmenAbwV','Zahnbehandlung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (8,37,'9',{d '1999-01-01'},null,'AbwVerO','Herstellung von Beschichtungsstoffen und Lackharzen','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (9,2,'50',{d '1999-01-01'},null,'AbwVerO','Zahnbehandlung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (10,44,'36',{d '1999-01-01'},null,'AbwVerO','Herstellung von Kohlenwasserstoffen','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (11,24,'24 Teil B',{d '1999-01-01'},{d '2001-07-31'},'AbwVerO','Eisen-, Stahl- und Tempergießerei','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (12,52,'41',{d '1999-01-01'},null,'AbwVerO','Herstellung und Verarbeitung von Glas und künstlichen Mineralfasern','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (13,41,'37',{d '1984-09-26'},{d '1991-12-31'},'AbwVerwVor','Herstellung anorganischer Pigmente','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (14,34,'6',{d '1999-01-01'},null,'AbwVerO','Herstellung von Erfrischungsgetränken und Getränkeabfüllung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (15,45,'7',{d '1999-01-01'},null,'AbwVerO','Fischverarbeitung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (16,57,'43 Teil II',{d '2001-08-01'},{d '2002-07-31'},'AbwVerO','Verarbeitung von Kautschuk und Latizes, Herstellung und Verarbeitung von Gummi','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (17,48,'28',{d '1983-10-01'},{d '2000-06-01'},'AbwVerwVor','Melasseverarbeitung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (18,4,'32',{d '2002-08-01'},null,'AbwVerO','Verarbeitung von Kautschuk und Latizes, Herstellung und Verarbeitung von Gummi','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (19,63,'20',{d '2007-06-01'},null,'AbwVerO','Verarbeitung tierischer Nebenprodukte','NAD_BR1',{d '2009-11-30'},{d '2009-11-30'},{ts '2009-11-30 08:54:14.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (20,19,'39',{d '1990-01-01'},{d '1998-12-31'},'RahmenAbwV','Nichteisenmetallherstellung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (21,42,'4',{d '2002-08-01'},null,'AbwVerO','Ölsaatenaufbereitung, Speisefett- und Speiseölraffination','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (22,23,'47',{d '2002-08-01'},null,'AbwVerO','Wäsche von Rauchgasen aus Feuerungsanlagen','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (23,25,'34',{d '1984-09-26'},{d '1997-04-01'},'AbwVerwVor','Herstellung von Bariumverbindungen','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (24,64,'22',{d '1982-06-12'},{d '1991-12-31'},'AbwVerwVor','Mischabwasser','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (25,12,'26',{d '1992-01-01'},{d '1998-12-31'},'RahmenAbwV','Steine und Erden','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (26,20,'17',{d '1982-02-06'},{d '1991-12-31'},'AbwVerwVor','Herstellung keramischer Erzeugnisse','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (27,36,'38',{d '2000-06-01'},null,'AbwVerO','Textilherstellung, Textilveredlung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (28,19,'39',{d '1999-01-01'},null,'AbwVerO','Nichteisenmetallherstellung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (29,29,'44',{d '1984-09-26'},{d '2002-07-31'},'AbwVerwVor','Herstellung von mineralischen Düngemittel außer Kali','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (30,8,'2',{d '1999-01-01'},null,'AbwVerO','Braunkohle-Brikettfabrikation','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (31,60,'1',{d '1997-04-01'},null,'AbwVerO','Häusliches und kommunales Abwasser','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:10.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (32,45,'7',{d '1992-01-01'},{d '1998-12-31'},'RahmenAbwV','Fischverarbeitung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (33,51,'56',{d '2000-06-01'},null,'AbwVerO','Herstellung von Druckformen, Druckerzeugnissen und grafischen Erzeugnissen','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (34,4,'32',{d '1984-09-26'},{d '1998-12-31'},'AbwVerwVor','Arzneimittel','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (35,6,'10',{d '1990-01-01'},{d '1998-12-31'},'RahmenAbwV','Fleischwirtschaft','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:10.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (36,15,'43',{d '1984-09-26'},{d '1998-12-31'},'AbwVerwVor','Chemiefasern','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (37,31,'21',{d '1982-06-12'},{d '1989-12-31'},'AbwVerwVor','Mälzereien','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (38,26,'25',{d '1999-01-01'},null,'AbwVerO','Lederherstellung, Pelzveredlung, Lederfaserstoffherstellung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (39,61,'23',{d '1982-06-12'},{d '1998-12-31'},'AbwVerwVor','Herstellung von Calciumcarbid','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (40,22,'40',{d '1997-04-01'},null,'AbwVerO','Metallbearbeitung, Metallverarbeitung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (41,6,'10',{d '1999-01-01'},null,'AbwVerO','Fleischwirtschaft','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:10.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (42,10,'16',{d '1992-01-01'},{d '1998-12-31'},'RahmenAbwV','Steinkohleaufbereitung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (43,5,'14',{d '1999-01-01'},null,'AbwVerO','Trocknung pflanzlicher Produkte für die Futtermittelherstellung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:10.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (44,50,'19 Teil 1',{d '2001-08-01'},{d '2002-07-31'},'AbwVerO','Zellstofferzeugung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (45,39,'5',{d '1999-01-01'},null,'AbwVerO','Herstellung von Obst- und Gemüseprodukten','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (46,17,'3',{d '2000-06-01'},null,'AbwVerO','Milchverarbeitung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (47,5,'14',{d '1990-01-01'},{d '1998-12-31'},'RahmenAbwV','Trocknung pflanzlicher Produkte für die Futtermittelherstellung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:10.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (48,26,'25',{d '1983-03-22'},{d '1989-12-31'},'AbwVerwVor','Lederherstellung, Pelzveredelung, Lederfaserherstellung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (49,66,'24 Teil A',{d '1996-01-01'},{d '2002-07-31'},'RahmenAbwV','Eisen- und Stahlerzeugung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (50,5,'14',{d '1981-03-26'},{d '1989-12-31'},'AbwVerwVor','Trocknung pflanzlicher Produkte für die Futtermittelherstellung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:10.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (51,52,'41',{d '1984-09-26'},{d '1989-12-31'},'AbwVerwVor','Glasherstellung und -verarbeitung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (52,43,'18',{d '1999-01-01'},null,'AbwVerO','Zuckerherstellung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (53,37,'9',{d '1981-03-26'},{d '1989-12-31'},'AbwVerwVor','Herstellung von Anstrichstoffen','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (54,32,'49',{d '1990-01-01'},{d '2000-05-31'},'RahmenAbwV','Mineralölhaltiges Abwasser','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (55,10,'16',{d '1982-02-06'},{d '1991-12-31'},'AbwVerwVor','Steinkohleaufbereitung und Steinkohlen-Brikettfabrikation','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (56,59,'52',{d '1990-01-01'},{d '1998-12-31'},'RahmenAbwV','Chemischreinigung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (57,28,'19',{d '1982-02-06'},{d '1989-12-31'},'AbwVerwVor','Zellstofferzeugung,Herstellung von Papier und Pappe','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (58,53,'99',{d '2009-11-10'},null,'-','Keine eindeutige Zuordnung möglich','DEA_IT.NRW',{d '2009-11-10'},{d '2009-11-10'},{ts '2009-11-10 13:57:46.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (59,54,'8',{d '1999-01-01'},null,'AbwVerO','Kartoffelverarbeitung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (60,20,'17',{d '1992-01-01'},{d '2000-05-31'},'RahmenAbwV','Herstellung keramischer Erzeugnisse','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (61,40,'24 Teil II',{d '2001-08-01'},{d '2002-07-31'},'AbwVerO','Eisen-, Stahl- und Tempergießerei','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (62,65,'19 Teil A',{d '1990-01-01'},{d '2001-07-31'},'AbwVerwVor','Zellstofferzeugung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (63,63,'20',{d '1999-01-01'},{d '2007-05-31'},'AbwVerO','Fleischmehlindustrie','NAD_BR1',{d '1980-01-01'},{d '2009-11-30'},{ts '2009-11-30 08:51:49.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (64,36,'38',{d '1984-09-26'},{d '2000-05-31'},'AbwVerwVor','Textilherstellung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (65,39,'5',{d '1981-03-26'},{d '1989-12-31'},'AbwVerwVor','Herstellung von Obst- und Gemüseprodukten','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (66,44,'36',{d '1992-01-01'},{d '1998-12-31'},'RahmenAbwV','Herstellung von Kohlenwasserstoffen','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (67,28,'19',{d '2002-08-01'},null,'AbwVerO','Zellstofferzeugung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (68,11,'33',{d '1984-09-26'},{d '1997-04-01'},'AbwVerwVor','Herstellung von Perboraten','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (69,3,'45',{d '1999-01-01'},null,'AbwVerO','Erdölverarbeitung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (70,64,'22',{d '1999-01-01'},null,'AbwVerO','Chemische Industrie','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (71,20,'17',{d '2000-06-01'},null,'AbwVerO','Herstellung keramischer Erzeugnisse','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (72,28,'19',{d '1990-01-01'},{d '1991-12-31'},'AbwVerwVor','Herstellung von Papier und Pappe','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (73,56,'15',{d '1990-01-01'},{d '1998-12-31'},'RahmenAbwV','Herstellung von Hautleim, Gelatine und Knochenleim','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (74,56,'15',{d '1981-03-26'},{d '1989-12-31'},'AbwVerwVor','Herstellung von Hautleim, Gelatine und Knochenleim','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (75,46,'13',{d '1993-07-01'},{d '1998-12-31'},'RahmenAbwV','Holzfaserplatten','NAD_BR1',{d '1980-01-01'},{d '2009-11-30'},{ts '2009-11-30 08:36:02.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (76,12,'26',{d '1982-03-22'},{d '1991-12-31'},'AbwVerwVor','Steine und Erden','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (77,54,'8',{d '1990-01-01'},{d '1998-12-31'},'RahmenAbwV','Kartoffelverarbeitung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (78,44,'36',{d '1984-09-26'},{d '1991-12-31'},'AbwVerwVor','Herstellung von Kohlenwasserstoffen','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (79,7,'11',{d '1990-01-01'},{d '1998-12-31'},'RahmenAbwV','Brauereien','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:10.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (80,42,'4',{d '1981-03-26'},{d '2002-07-31'},'AbwVerwVor','Ã–lsaatenaufbereitung, Speisefett- und Speiseölraffination','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (81,17,'3',{d '1990-01-01'},{d '2000-05-31'},'RahmenAbwV','Milchverarbeitung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (82,8,'2',{d '1990-01-01'},{d '1998-12-31'},'RahmenAbwV','Braunkohle-Brikettfabrikation','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (83,30,'43 Teil I',{d '1999-01-01'},{d '2002-07-31'},'AbwVerO','Herstellung von Chemiefasern, Folien und Schwammtuch nach dem Viskoseverfahren sowie von Celluloseacetatfasern','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (84,18,'51',{d '1999-01-01'},null,'AbwVerO','Oberirdische Ablagerung von Abfällen','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (85,15,'43',{d '1996-01-01'},{d '1998-12-31'},'RahmenAbwV','Herstellung von Chemiefasern, Folien und Schwammtuch nach dem Viskoseverfahren sowie Celluloseacetatfasern','NAD_BR1',{d '1980-01-01'},{d '2009-11-30'},{ts '2009-11-30 08:48:18.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (86,10,'16',{d '1999-01-01'},null,'AbwVerO','Steinkohlenaufbereitung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (87,22,'40',{d '1984-09-26'},{d '1989-12-31'},'AbwVerwVor','Metallbearbeitung, Metallverarbeitung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (88,13,'12',{d '1990-01-01'},{d '1998-12-31'},'RahmenAbwV','Herstellung von Alkohol und alkoholischen Getränken','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:10.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (89,19,'39',{d '1984-09-26'},{d '1989-12-31'},'AbwVerwVor','Nichteisenmetalle','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (90,21,'30',{d '1990-01-01'},{d '2002-07-31'},'RahmenAbwV','Sodaherstellung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (91,60,'1',{d '1983-01-01'},{d '1989-12-31'},'AbwVerwVor','Gemeinden','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:10.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (92,26,'25',{d '1990-01-01'},{d '1998-12-31'},'RahmenAbwV','Lederherstellung, Pelzveredelung, Lederfaserstoffherstellung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (93,12,'26',{d '1999-01-01'},null,'AbwVerO','Steine und Erden','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (94,22,'40',{d '1990-01-01'},{d '1997-03-31'},'RahmenAbwV','Metallbearbeitung, Metallverarbeitung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (95,35,'48',{d '1997-04-01'},null,'AbwVerO','Verwendung bestimmter gefährlicher Stoffe','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (96,56,'15',{d '1999-01-01'},null,'AbwVerO','Herstellung von Hautleim, Gelatine und Knochenleim','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (97,46,'13',{d '1981-03-26'},{d '1993-06-30'},'AbwVerwVor','Herstellung von Holzfaserhartplatten','NAD_BR1',{d '1980-01-01'},{d '2009-11-30'},{ts '2009-11-30 08:34:09.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (98,7,'11',{d '1999-01-01'},null,'AbwVerO','Brauereien','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:10.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (99,52,'41',{d '1990-01-01'},{d '1998-12-31'},'RahmenAbwV','Herstellung und Verarbeitung von Glas und künstlichen Mineralfasern','NAD_BR1',{d '1980-01-01'},{d '2009-11-30'},{ts '2009-11-30 08:47:09.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (100,58,'31',{d '2002-08-01'},null,'AbwVerO','Wasseraufbereitung, Kühlsysteme, Dampferzeugung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (101,41,'37',{d '1992-01-01'},{d '1998-12-31'},'RahmenAbwV','Herstellung anorganischer Pigmente','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (102,16,'27',{d '1982-03-22'},{d '1998-12-31'},'AbwVerwVor','Erzaufbereitung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (103,58,'31',{d '1993-07-01'},{d '2002-07-31'},'RahmenAbwV','Wasseraufbereitung, Kühlsysteme, Dampferzeugung','NAD_BR1',{d '1980-01-01'},{d '2009-11-30'},{ts '2009-11-30 08:45:18.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (104,3,'45',{d '1992-01-01'},{d '1998-12-31'},'RahmenAbwV','Erdölverarbeitung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (105,13,'12',{d '1981-03-26'},{d '1989-12-31'},'AbwVerwVor','Herstellung von Alkohol und alkoholischen Getränken','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:10.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (106,34,'6',{d '1981-03-26'},{d '1989-12-31'},'AbwVerwVor','Herstellung von Erfrischungsgetränken und Getränkeabfüllung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (107,43,'18',{d '1982-02-06'},{d '1991-12-31'},'AbwVerwVor','Zuckerherstellung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (108,47,'29',{d '2002-08-01'},null,'AbwVerO','Eisen- und Stahlerzeugung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (109,39,'5',{d '1990-01-01'},{d '1998-12-31'},'RahmenAbwV','Herstellung von Obst- und Gemüseprodukten','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (110,37,'9',{d '1990-01-01'},{d '1998-12-31'},'RahmenAbwV','Herstellung von Beschichtungsstoffen und Lackharzen','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (111,3,'45',{d '1984-09-26'},{d '1991-12-31'},'AbwVerwVor','Erdölverarbeitung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (112,54,'8',{d '1981-03-26'},{d '1989-12-31'},'AbwVerwVor','Kartoffelverarbeitung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (113,13,'12',{d '1999-01-01'},null,'AbwVerO','Herstellung von Alkohol und alkoholischen Getränken','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:10.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (114,32,'49',{d '2000-06-01'},null,'AbwVerO','Mineralölhaltiges Abwasser','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (115,31,'21',{d '1999-01-01'},null,'AbwVerO','Mälzereien','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (116,38,'46',{d '1996-01-01'},{d '1998-12-31'},'RahmenAbwV','Steinkohleverkokung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (117,55,'55',{d '1999-01-01'},null,'AbwVerO','Wäschereien','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (118,15,'43',{d '2002-08-01'},null,'AbwVerO','Herstellung von Chemiefasern, Folien und Schwammtuch nach dem Viskoseverfahren sowie von Celluloseacetatfasern','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (119,47,'29',{d '1983-10-01'},{d '2002-07-31'},'AbwVerwVor','Fischintensivhaltung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (120,41,'37',{d '1999-01-01'},null,'AbwVerO','Herstellung anorganischer Pigmente','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (121,35,'48',{d '1989-02-01'},{d '1997-03-31'},'AbwVerwVor','Verwendung bestimmter gefährlicher Stoffe','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (122,7,'11',{d '1981-03-26'},{d '1989-12-31'},'AbwVerwVor','Brauereien','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:10.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (123,14,'35',{d '1984-09-26'},{d '1997-04-01'},'AbwVerwVor','Hochdisperse Oxide','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (124,24,'24 Teil B',{d '1996-01-01'},{d '1998-12-31'},'RahmenAbwV','Eisen-, Stahl- und Tempergießerei','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (125,59,'52',{d '1999-01-01'},null,'AbwVerO','Chemischreinigung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (126,61,'23',{d '2001-08-01'},null,'AbwVerO','Anlagen zur biologischen Behandlung von Abfällen','NAD_BR1',{d '1980-01-01'},{d '2009-11-30'},{ts '2009-11-30 08:37:56.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (127,60,'1',{d '1990-01-01'},{d '1997-03-31'},'RahmenAbwV','Gemeinden','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:10.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (128,31,'21',{d '1990-01-01'},{d '1998-12-31'},'RahmenAbwV','Mälzereien','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (129,18,'51',{d '1990-01-01'},{d '1998-12-31'},'RahmenAbwV','Ablagerung von Siedlungsabfällen','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (130,43,'18',{d '1992-01-01'},{d '1998-12-31'},'RahmenAbwV','Zuckerherstellung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (131,48,'28',{d '2002-08-01'},null,'AbwVerO','Herstellung von Papier und Pappe','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (132,38,'46',{d '1999-01-01'},null,'AbwVerO','Steinkohleverkokung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (133,17,'3',{d '1981-03-26'},{d '1989-12-31'},'AbwVerwVor','Milchverarbeitung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (134,6,'10',{d '1981-03-26'},{d '1989-12-31'},'AbwVerwVor','Fleischwirtschaft','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:10.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (135,46,'13',{d '1999-01-01'},null,'AbwVerO','Holzfaserplatten','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:10.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (136,1,'24',{d '2002-08-01'},null,'AbwVerO','Eisen-, Stahl- und Tempergießerei','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (137,11,'33',{d '2002-08-01'},null,'AbwVerO','Wäsche von Abgasen aus der Verbrennung von Abfällen','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (138,58,'31',{d '1983-10-01'},{d '1993-06-30'},'AbwVerwVor','Wasseraufbereitung, Kühlsysteme','NAD_BR1',{d '1980-01-01'},{d '2009-11-30'},{ts '2009-11-30 08:44:38.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (139,63,'20',{d '1982-06-12'},{d '1998-12-31'},'AbwVerwVor','Tierkörperbeseitigung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (140,38,'46',{d '1986-09-16'},{d '1995-12-31'},'AbwVerwVor','Steinkohleverkokung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (141,45,'7',{d '1981-03-26'},{d '1991-12-31'},'AbwVerwVor','Fischverarbeitung','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (142,8,'2',{d '1980-02-12'},{d '1989-12-31'},'AbwVerwVor','Braunkohle, Brikettfabrikation','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (143,9,'53',{d '1999-01-01'},null,'AbwVerO','Fotografische Prozesse (Silberhalogenid-Fotografie)','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (144,23,'47',{d '1990-01-01'},{d '2002-07-31'},'RahmenAbwV','Wäsche von Rauchgasen aus Feuerungsanlagen','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (145,62,'19 Teil B',{d '1992-01-01'},{d '2002-07-31'},'RahmenAbwV','Herstellung von Papier und Pappe','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (146,1,'24',{d '1982-06-12'},{d '1995-12-31'},'AbwVerwVor','Eisen- und Stahlerzeugnisse','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (147,33,'42',{d '1997-04-01'},null,'AbwVerO','Alkalichloridelektrolyse','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});
INSERT INTO "ELKA"."ANHANG" (SL_NR,ANH_MA_SL_NR,ANHANG_ID,ANH_GUELTIG_VON,ANH_GUELTIG_BIS,ANH_REGELWERK,ANH_TEXT,HERKUNFT,ERSTELL_DAT,AKTUAL_DAT,ZEITSTEMPEL) VALUES (148,33,'42',{d '1984-09-26'},{d '1997-03-31'},'AbwVerwVor','Alkalichloridelektrolyse nach dem Amalgamverfahren','DEA_IT.NRW',{d '1980-01-01'},{d '2003-10-08'},{ts '2009-04-29 13:21:11.0'});




INSERT INTO "AWSV"."ANLAGENARTEN" (ID,ANLAGENART,_ENABLED,_DELETED) VALUES (1,'Lageranlage',1,0);
INSERT INTO "AWSV"."ANLAGENARTEN" (ID,ANLAGENART,_ENABLED,_DELETED) VALUES (2,'Abfüllanlage',1,0);
INSERT INTO "AWSV"."ANLAGENARTEN" (ID,ANLAGENART,_ENABLED,_DELETED) VALUES (3,'Umschlaganlage',1,0);
INSERT INTO "AWSV"."ANLAGENARTEN" (ID,ANLAGENART,_ENABLED,_DELETED) VALUES (4,'HBV-Anlage',1,0);
INSERT INTO "AWSV"."ANLAGENARTEN" (ID,ANLAGENART,_ENABLED,_DELETED) VALUES (5,'Rohrleitung',1,0);
INSERT INTO "AWSV"."ANLAGENARTEN" (ID,ANLAGENART,_ENABLED,_DELETED) VALUES (6,'LAU-Anlage',1,0);
INSERT INTO "AWSV"."ANLAGENARTEN" (ID,ANLAGENART,_ENABLED,_DELETED) VALUES (7,'AwSV-Abscheider',1,0);
INSERT INTO "AWSV"."ANLAGENARTEN" (ID,ANLAGENART,_ENABLED,_DELETED) VALUES (8,'Gebindelageranlage',1,0);
INSERT INTO "AWSV"."ANLAGENARTEN" (ID,ANLAGENART,_ENABLED,_DELETED) VALUES (9,'Abfüllfläche',1,0);
INSERT INTO "AWSV"."ANLAGENARTEN" (ID,ANLAGENART,_ENABLED,_DELETED) VALUES (10,'Fahrsilo',1,0);
INSERT INTO "AWSV"."ANLAGENARTEN" (ID,ANLAGENART,_ENABLED,_DELETED) VALUES (11,'Güllehochbehälter',1,0);
INSERT INTO "AWSV"."ANLAGENARTEN" (ID,ANLAGENART,_ENABLED,_DELETED) VALUES (12,'Güllekeller',1,0);




INSERT INTO "AWSV"."BEHAELTERART" (ID,BEHAELTERART,_ENABLED,_DELETED) VALUES (0,'k. A.',1,0);
INSERT INTO "AWSV"."BEHAELTERART" (ID,BEHAELTERART,_ENABLED,_DELETED) VALUES (1,'oberirdisch',1,0);
INSERT INTO "AWSV"."BEHAELTERART" (ID,BEHAELTERART,_ENABLED,_DELETED) VALUES (2,'unterirdisch',1,0);


INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (89,'Altöl',1,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (90,'Altöl/Heizöl',1,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (91,'Benzin',1,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (92,'Diesel',1,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (93,'Diesel/Altöl',1,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (94,'Flugbenzin',1,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (95,'Frischöl',1,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (96,'Gemisch',1,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (97,'Heizöl',1,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (98,'Hydrauliköl',1,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (99,'Kerosin',1,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (100,'Öle',1,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (101,'Öle',1,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (102,'Petroleum',1,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (103,'Super',1,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (104,'Super+',1,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (105,'Testbenzin',1,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (106,'Trafoöl',1,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (107,'VK',1,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (108,'VK/Biodiesel',1,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (109,'VK/DK',1,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (110,'Waschbenzin',1,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (111,'Athylacetat',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (112,'Biodiesel',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (113,'Chromsäure',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (114,'div., Farben',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (115,'diverses',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (116,'Emulsion',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (117,'Ethanol',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (118,'Isopropanol',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (119,'KKS',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (120,'KLEA 407 C',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (121,'Lacke',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (122,'Lacke etc.',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (123,'Lösungsmittelrückstände',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (124,'Natronlauge',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (125,'Pekasol L',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (126,'Reinigungsmittel',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (127,'Spiritus',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (128,'Toner / Tinte',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (129,'Waschmittel',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (130,'Wasser',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (131,'Xylol',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (132,'Lösungsmittel',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (133,'Silagesickersaft',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (134,'Substrat',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (135,'Gülle',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (136,'Jauche',2,1,0);
INSERT INTO "AWSV"."FLUESSIGKEIT" (ID,FLUESSIGKEIT,IDLAND,_ENABLED,_DELETED) VALUES (137,'Glykol',2,1,0);


INSERT INTO "AWSV"."GEBUEHRENARTEN" (ID,GEBUEHRENART,_ENABLED,_DELETED) VALUES (1,'Auswertung Prüfbericht',1,0);
INSERT INTO "AWSV"."GEBUEHRENARTEN" (ID,GEBUEHRENART,_ENABLED,_DELETED) VALUES (2,'Eignungsfeststellung',1,0);
INSERT INTO "AWSV"."GEBUEHRENARTEN" (ID,GEBUEHRENART,_ENABLED,_DELETED) VALUES (3,'Bußgeld',1,0);
INSERT INTO "AWSV"."GEBUEHRENARTEN" (ID,GEBUEHRENART,_ENABLED,_DELETED) VALUES (4,'Zwangsgeld',1,0);
INSERT INTO "AWSV"."GEBUEHRENARTEN" (ID,GEBUEHRENART,_ENABLED,_DELETED) VALUES (5,'Kostenerstattung (§ 118 LWG)',1,0);
INSERT INTO "AWSV"."GEBUEHRENARTEN" (ID,GEBUEHRENART,_ENABLED,_DELETED) VALUES (6,'Überwachung (§ 116 LWG)',1,0);
INSERT INTO "AWSV"."GEBUEHRENARTEN" (ID,GEBUEHRENART,_ENABLED,_DELETED) VALUES (7,'Gebühr für Ordnungsverfügung',1,0);


INSERT INTO "AWSV"."GEFAEHRDUNGSSTUFEN" (ID,GEFAEHRDUNGSSTUFEN,_ENABLED,_DELETED) VALUES (9,'A',1,0);
INSERT INTO "AWSV"."GEFAEHRDUNGSSTUFEN" (ID,GEFAEHRDUNGSSTUFEN,_ENABLED,_DELETED) VALUES (10,'B',1,0);
INSERT INTO "AWSV"."GEFAEHRDUNGSSTUFEN" (ID,GEFAEHRDUNGSSTUFEN,_ENABLED,_DELETED) VALUES (11,'C',1,0);
INSERT INTO "AWSV"."GEFAEHRDUNGSSTUFEN" (ID,GEFAEHRDUNGSSTUFEN,_ENABLED,_DELETED) VALUES (12,'D',1,0);


INSERT INTO "AWSV"."MATERIAL" (ID,MATERIAL,_ENABLED,_DELETED) VALUES (13,'Stahl',1,0);
INSERT INTO "AWSV"."MATERIAL" (ID,MATERIAL,_ENABLED,_DELETED) VALUES (14,'Kunststoff',1,0);
INSERT INTO "AWSV"."MATERIAL" (ID,MATERIAL,_ENABLED,_DELETED) VALUES (15,'sonstiges',1,0);
INSERT INTO "AWSV"."MATERIAL" (ID,MATERIAL,_ENABLED,_DELETED) VALUES (16,'Beton/Kunststoff',1,0);
INSERT INTO "AWSV"."MATERIAL" (ID,MATERIAL,_ENABLED,_DELETED) VALUES (17,'k. A.',1,0);
INSERT INTO "AWSV"."MATERIAL" (ID,MATERIAL,_ENABLED,_DELETED) VALUES (18,'unbekannt',1,0);


INSERT INTO "AWSV"."PRUEFER" (ID,PRUEFER,_ENABLED,_DELETED) VALUES (13,'TÜV-Nord',1,0);
INSERT INTO "AWSV"."PRUEFER" (ID,PRUEFER,_ENABLED,_DELETED) VALUES (14,'DEKRA',1,0);
INSERT INTO "AWSV"."PRUEFER" (ID,PRUEFER,_ENABLED,_DELETED) VALUES (15,'GEOPOHL',1,0);
INSERT INTO "AWSV"."PRUEFER" (ID,PRUEFER,_ENABLED,_DELETED) VALUES (16,'anderer SV',1,0);
INSERT INTO "AWSV"."PRUEFER" (ID,PRUEFER,_ENABLED,_DELETED) VALUES (17,'Fachbetrieb',1,0);
INSERT INTO "AWSV"."PRUEFER" (ID,PRUEFER,_ENABLED,_DELETED) VALUES (18,'Betreiber',1,0);
INSERT INTO "AWSV"."PRUEFER" (ID,PRUEFER,_ENABLED,_DELETED) VALUES (19,'TÜV-Süd',1,0);
INSERT INTO "AWSV"."PRUEFER" (ID,PRUEFER,_ENABLED,_DELETED) VALUES (20,'TÜV-Rheinland',1,0);


INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (39,'WKP - keine Mängel',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (40,'WKP - geringfügige Mängel',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (41,'WKP - erhebliche Mängel',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (42,'WKP - gefährliche Mängel',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (43,'NP    - keine Mängel',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (44,'NP    - geringfügige Mängel',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (45,'NP    - erhebliche Mängel',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (46,'NP    - gefährliche Mängel',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (47,'AP    - keine Mängel',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (48,'AP    - geringfügige Mängel',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (49,'AP    - erhebliche Mängel',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (50,'AP    - gefährliche Mängel',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (51,'Betreibererklärung - Mängelfrei',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (52,'IP     - keine Mängel',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (53,'IP     - geringfügige Mängel',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (54,'IP     - erhebliche Mängel',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (55,'IP     - gefährliche Mängel',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (56,'Stilllegungsprüfung',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (57,'Fachbetriebserklärung - Mängelfrei',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (59,'Generalinspektion - ohne Mängel',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (60,'Generalinspektion - mit Mängel',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (61,'Bescheinigung gemäß § 12 AwSV',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (62,'PnwÄ - keine Mängel',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (63,'PnwÄ - geringfügige Mängel',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (64,'PnwÄ - erhebliche Mängel',1,0);
INSERT INTO "AWSV"."PRUEFERGEBNISS" (ID,PRUEFERGEBNIS,_ENABLED,_DELETED) VALUES (65,'PnwÄ - gefährliche Mängel',1,0);


INSERT INTO "AWSV"."VBFEINSTUFUNG" (ID,VBFEINSTUFUNG,_ENABLED,_DELETED) VALUES (11,'k. A.',1,0);
INSERT INTO "AWSV"."VBFEINSTUFUNG" (ID,VBFEINSTUFUNG,_ENABLED,_DELETED) VALUES (12,'A I',1,0);
INSERT INTO "AWSV"."VBFEINSTUFUNG" (ID,VBFEINSTUFUNG,_ENABLED,_DELETED) VALUES (13,'A II',1,0);
INSERT INTO "AWSV"."VBFEINSTUFUNG" (ID,VBFEINSTUFUNG,_ENABLED,_DELETED) VALUES (14,'A III',1,0);
INSERT INTO "AWSV"."VBFEINSTUFUNG" (ID,VBFEINSTUFUNG,_ENABLED,_DELETED) VALUES (15,'B',1,0);


INSERT INTO "AWSV"."VERWMASSNAHMEN" (ID,MASSNAHMEN,_ENABLED,_DELETED) VALUES (13,'Anhörung',1,0);
INSERT INTO "AWSV"."VERWMASSNAHMEN" (ID,MASSNAHMEN,_ENABLED,_DELETED) VALUES (14,'Anschreiben',1,0);
INSERT INTO "AWSV"."VERWMASSNAHMEN" (ID,MASSNAHMEN,_ENABLED,_DELETED) VALUES (15,'Zwangsgeldfestsetzung',1,0);
INSERT INTO "AWSV"."VERWMASSNAHMEN" (ID,MASSNAHMEN,_ENABLED,_DELETED) VALUES (16,'Fristverlängerung',1,0);
INSERT INTO "AWSV"."VERWMASSNAHMEN" (ID,MASSNAHMEN,_ENABLED,_DELETED) VALUES (17,'Ordnungsverfügung',1,0);
INSERT INTO "AWSV"."VERWMASSNAHMEN" (ID,MASSNAHMEN,_ENABLED,_DELETED) VALUES (18,'Abgabe an Sachbearbeiter',1,0);
INSERT INTO "AWSV"."VERWMASSNAHMEN" (ID,MASSNAHMEN,_ENABLED,_DELETED) VALUES (19,'Erinnerung',1,0);
INSERT INTO "AWSV"."VERWMASSNAHMEN" (ID,MASSNAHMEN,_ENABLED,_DELETED) VALUES (20,'Telefonat',1,0);
INSERT INTO "AWSV"."VERWMASSNAHMEN" (ID,MASSNAHMEN,_ENABLED,_DELETED) VALUES (21,'Information geringe Mängel',1,0);

INSERT INTO "BASIS"."GEMARKUNG" (ID,GEMARKUNG,_ENABLED,_DELETED) VALUES (0,'k. A.',1,0);



INSERT INTO "BASIS"."WIRTSCHAFTSZWEIG" (ID,WIRTSCHAFTSZWEIG,_ENABLED,_DELETED) VALUES (0,'k. A.',1,0);
INSERT INTO "BASIS"."WIRTSCHAFTSZWEIG" (ID,WIRTSCHAFTSZWEIG,_ENABLED,_DELETED) VALUES (1,'Land- und Forstwirtschaft, Fischerei, Fischzucht',1,0);
INSERT INTO "BASIS"."WIRTSCHAFTSZWEIG" (ID,WIRTSCHAFTSZWEIG,_ENABLED,_DELETED) VALUES (2,'Produzierendes Gewerbe(z.B. Bergbau, Gewinnung von Steinen und Erden, Mineralölverarbeitung, Chemische Industrie)',1,0);
INSERT INTO "BASIS"."WIRTSCHAFTSZWEIG" (ID,WIRTSCHAFTSZWEIG,_ENABLED,_DELETED) VALUES (3,'Handel (einschl. Tankstellen)',1,0);
INSERT INTO "BASIS"."WIRTSCHAFTSZWEIG" (ID,WIRTSCHAFTSZWEIG,_ENABLED,_DELETED) VALUES (4,'sonstige (z. B. private Haushalte, öffentliche Einrichtungen)',1,0);




INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (1,'unbekannt',null,null,null,null,null,null,1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (2,'Medentex GmbH','Pideritz Bleiche',11,'33689','Bielefeld','Hr. Busemann','(05205) 7516-0',1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (3,'Maoro Dental Service GmbH','Detmolder Straße',230,'33698','Bielefeld','Hr. Müller','(0521) 2 10 22',1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (4,'Hager-Dental','Avenwedder Straße',210,'33335','Gütersloh','Hr. Laumann',null,1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (5,'RW TÜV Anlagentechnik GmbH','Südring',15,'59386','Werne',null,'(02389) 53 57 71',1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (6,'Nordwest Dental Schricker & Saphörster GmbH & Co. KG','Walter-Rathenau-Straße',59,'33602','Bielefeld','Hr. Lindemann',null,1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (7,'RW TÜV Anlagentechnik GmbH','Steubenstraße',53,'45138','Essen','Hr. Stumpf','(0201) 82 50',1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (8,'Gisbert Grabosch GmbH','Sunderkamp',9,'48165','Münster-Hiltrup',null,'(02501) 2 57 88',1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (9,'Siemens Dental-Depot','Ahrensburger Straße',1,'30659','Hannover','Hr. Quint','(0511) 61 52 10',1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (10,'Metasys','Ahornstraße',19,'85614','Kirchseeon','Hr. Leitner','(089) 613 87 40',1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (11,'Jerzy Dental-Gerätebau','Südring',3,'21465','Wentorf',null,'(040) 720 97 91',1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (12,'Mitzscherlich Dental Depot GmbH & Co.','Oststraße',91,'32051','Herford','Hr. Bolz','(05221) 30 91',1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (13,'Schwarz Dental Vertriebs GmbH','Marienberger Straße',2,'65936','Frankfurt',null,null,1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (14,'ECODEPOT Dental Vertriebs GmbH','Raiffeisenstraße',1,'37124','Rosdorf',null,'(0551) 500 61 78',1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (15,'Anatom Dental GmbH','Breidenbrucher Straße',10,'51674','Wiehl-Bomig','Hr. Dieper','(02261) 7 70 83',1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (16,'Biofuture Entsorgungsges. mbH','Industriestraße',3,'33184','Altenbeken-Buke','Hr. Dr. Sobanski','(05255) 93 06 00',1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (17,'Meyer Superdenta GmbH','Im Grohenstück',1,'65396','Walluf',null,'(06123) 977 20',1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (22,'Nordische Quecksilber-Rückgewinnung','An der Gasanstalt',9,'23560','Lübeck',null,'(0451) 58 30 00',1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (23,'Ultradent Kutzner & Ostner','Stahlgruberring',26,'81829','München','Hr. Holland','(089) 42 09 92-60',1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (24,'Möller Dentaltechnik','Dorfstraße',118,'21720','Mittelkirchen','Hr. Möller',null,1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (25,'G. Buscha Entsorgung','Am Sülzegraben',19,'38820','Halberstedt','Hr. Buscha','(03941) 60 08 58',1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (26,'REMONDIS','Brunnenstraße',138,'44536','Lünen',null,null,1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (27,'Dental-Geräte Technik Dr. Ropers GmbH','Veerenkamp',4,'21739','Dollern',null,'(0 41 63) 40 18',1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (29,'DEKRA',null,null,null,'Bielefeld','Dipl.Ing. Peter Höfs',null,1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (31,'Maoro',null,null,null,null,null,null,1,0);
INSERT INTO "INDEINL"."ENTSORGER" (ID,ENTSORGER,STRASSE,HAUSNR,PLZ,ORT,ANSPRECHPARTNER,TELEFON,_ENABLED,_DELETED) VALUES (32,'Dental Eco Service GmbH','Bonnerstraße',530,'50968','Köln',null,'(0221)9372618',1,0);


INSERT INTO "AWSV"."WASSEREINZUGSGEBIET" (ID,EZGBNAME,_ENABLED,_DELETED) VALUES (1,'k. A.',1,0);



INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (100,{d '2014-10-30'},{d '2014-10-30'},'1 = (belüfteter) Sandfang');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (200,{d '2014-10-30'},{d '2014-10-30'},'2 = Absetzbecken');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (210,{d '2014-10-30'},{d '2014-10-30'},'2.1 = Schrägplattenklärer');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (220,{d '2014-10-30'},{d '2014-10-30'},'2.2 = Lamellenklärer');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (230,{d '2014-10-30'},{d '2014-10-30'},'2.3 = Dortmundbecken');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (240,{d '2014-10-30'},{d '2014-10-30'},'2.4 = Schlammfang');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (250,{d '2014-10-30'},{d '2014-10-30'},'2.5 = 3-Kammer-Grube');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (300,{d '2014-10-30'},{d '2014-10-30'},'3 = Flotation');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (400,{d '2014-10-30'},{d '2014-10-30'},'4 = Abscheider');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (410,{d '2014-10-30'},{d '2014-10-30'},'4.1 = Koaleszenzabscheider');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (420,{d '2014-10-30'},{d '2014-10-30'},'4.2 = Stärkeabscheider');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (430,{d '2014-10-30'},{d '2014-10-30'},'4.3 = Fettabscheider');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (440,{d '2014-10-30'},{d '2014-10-30'},'4.4 = Hydrodynamische Abscheider');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (441,{d '2014-10-30'},{d '2014-10-30'},'4.4.1 = Wirbelabscheider');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (442,{d '2014-10-30'},{d '2014-10-30'},'4.4.2 = Zyklonabscheider');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (500,{d '2014-10-30'},{d '2014-10-30'},'5 = Zentrifugalabscheider');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (510,{d '2014-10-30'},{d '2014-10-30'},'5.1 = Zentrifuge');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (520,{d '2014-10-30'},{d '2014-10-30'},'5.2 = Dekanter');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (530,{d '2014-10-30'},{d '2014-10-30'},'5.3 = Amalgamabscheider');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (600,{d '2014-10-30'},{d '2014-10-30'},'6 = Sieb/Rechen');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (700,{d '2014-10-30'},{d '2014-10-30'},'7 = Schwerflüssigkeitsabscheider');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (800,{d '2014-10-30'},{d '2014-10-30'},'8 = Eindampfen (Destillation)');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (900,{d '2014-10-30'},{d '2014-10-30'},'9 = Stripp-Anlage');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (1000,{d '2014-10-30'},{d '2014-10-30'},'10 = Filtration');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (1010,{d '2014-10-30'},{d '2014-10-30'},'10.1 = Sandfilter');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (1020,{d '2014-10-30'},{d '2014-10-30'},'10.2 = Kiesfilter');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (1030,{d '2014-10-30'},{d '2014-10-30'},'10.3 = Anthrazit-Filter');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (1040,{d '2014-10-30'},{d '2014-10-30'},'10.4 = Geotextilfilter');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (1100,{d '2014-10-30'},{d '2014-10-30'},'11 = Membranfiltration');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (1110,{d '2014-10-30'},{d '2014-10-30'},'11.1 = Ultrafiltration');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (1120,{d '2014-10-30'},{d '2014-10-30'},'11.2 = Mikrofiltration');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (1130,{d '2014-10-30'},{d '2014-10-30'},'11.3 = Nanofiltration');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (1200,{d '2014-10-30'},{d '2014-10-30'},'12 = Kammerfilterpresse');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (1300,{d '2014-10-30'},{d '2014-10-30'},'13 = Umkehrosmose');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (1400,{d '2014-10-30'},{d '2014-10-30'},'14 = Misch-/Ausgleichs-/Rückhaltebecken');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (1500,{d '2014-10-30'},{d '2014-10-30'},'15 = Elektroflotation');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (1600,{d '2014-10-30'},{d '2014-10-30'},'16 = Adsorption');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (1610,{d '2014-10-30'},{d '2014-10-30'},'16.1 = Aktivkohle');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (1700,{d '2014-10-30'},{d '2014-10-30'},'17 = Absorption');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (1800,{d '2014-10-30'},{d '2014-10-30'},'18 = Neutralisation');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (1900,{d '2014-10-30'},{d '2014-10-30'},'19 = Fällung/Flockung');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2000,{d '2014-10-30'},{d '2014-10-30'},'20 = Emulsionsspaltanlage');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2010,{d '2014-10-30'},{d '2014-10-30'},'20.1 = pH-Verschiebung');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2020,{d '2014-10-30'},{d '2014-10-30'},'20.2 = Bentonit');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2100,{d '2014-10-30'},{d '2014-10-30'},'21 = Ionentauscher');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2110,{d '2014-10-30'},{d '2014-10-30'},'21.1 = selektiv');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2120,{d '2014-10-30'},{d '2014-10-30'},'21.2 = nicht selektiv');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2200,{d '2014-10-30'},{d '2014-10-30'},'22 = Extraktion = Adsorption');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2210,{d '2014-10-30'},{d '2014-10-30'},'22.1 = flüssig-flüssig');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2220,{d '2014-10-30'},{d '2014-10-30'},'22.2 = flüssig-fest');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2300,{d '2014-10-30'},{d '2014-10-30'},'23 = Oxidation');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2310,{d '2014-10-30'},{d '2014-10-30'},'23.1 = Ozonierung');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2320,{d '2014-10-30'},{d '2014-10-30'},'23.2 = Sauerstoff');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2330,{d '2014-10-30'},{d '2014-10-30'},'23.3 = Wasserstoffperoxid');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2340,{d '2014-10-30'},{d '2014-10-30'},'23.4 = chlorierende Oxidation');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2400,{d '2014-10-30'},{d '2014-10-30'},'24 = P-Elimination');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2410,{d '2014-10-30'},{d '2014-10-30'},'24.1 = chemische P-Elimination');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2411,{d '2014-10-30'},{d '2014-10-30'},'24.1.1 = Simultanfällung');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2412,{d '2014-10-30'},{d '2014-10-30'},'24.1.2 = Vorfällung');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2413,{d '2014-10-30'},{d '2014-10-30'},'24.1.3 = Nachfällung');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2420,{d '2014-10-30'},{d '2014-10-30'},'24.2 = biologische P-Elimination');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2500,{d '2014-10-30'},{d '2014-10-30'},'25 = Elektrolyse');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2600,{d '2014-10-30'},{d '2014-10-30'},'26 = Elektrochemische Abscheidung');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2700,{d '2014-10-30'},{d '2014-10-30'},'27 = Tropfkörper');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2800,{d '2014-10-30'},{d '2014-10-30'},'28 = Tauchkörper');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2810,{d '2014-10-30'},{d '2014-10-30'},'28.1 = Scheibentauchkörper');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (2900,{d '2014-10-30'},{d '2014-10-30'},'29 = Schönungsteich');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3000,{d '2014-10-30'},{d '2014-10-30'},'30 = Bodenfilter');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3100,{d '2014-10-30'},{d '2014-10-30'},'31 = Pflanzenkläranlage');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3200,{d '2014-10-30'},{d '2014-10-30'},'32 = Anaerobreaktor');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3210,{d '2014-10-30'},{d '2014-10-30'},'32.1 = mesophil');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3220,{d '2014-10-30'},{d '2014-10-30'},'32.2 = thermophil');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3230,{d '2014-10-30'},{d '2014-10-30'},'32.3 = Vorbehandlung');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3300,{d '2014-10-30'},{d '2014-10-30'},'33 = Belebtschlammverfahren');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3310,{d '2014-10-30'},{d '2014-10-30'},'33.1 = nur C-Abbau');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3320,{d '2014-10-30'},{d '2014-10-30'},'33.2 = Nitrifikation');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3330,{d '2014-10-30'},{d '2014-10-30'},'33.3 = Denitrifikation');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3340,{d '2014-10-30'},{d '2014-10-30'},'33.4 = SBR');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3400,{d '2014-10-30'},{d '2014-10-30'},'34 = Dezentrale NW-Behandlung');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3410,{d '2014-10-30'},{d '2014-10-30'},'34.1 = Sedimentation');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3420,{d '2014-10-30'},{d '2014-10-30'},'34.2 = Siebung');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3430,{d '2014-10-30'},{d '2014-10-30'},'34.3 = Filtration');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3440,{d '2014-10-30'},{d '2014-10-30'},'34.4 = Fällung');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3450,{d '2014-10-30'},{d '2014-10-30'},'34.5 = Sorption');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3460,{d '2014-10-30'},{d '2014-10-30'},'34.6 = Ionenaustausch');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3470,{d '2014-10-30'},{d '2014-10-30'},'34.7 = Leichtstoffabscheidung');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3500,{d '2014-10-30'},{d '2014-10-30'},'35 = Mechanische Verfahren (Kleinkläranlagen)');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3510,{d '2014-10-30'},{d '2014-10-30'},'35.1 = Mehrkammerausfaulgrube');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3520,{d '2014-10-30'},{d '2014-10-30'},'35.2 = Mehrkammerabsetzgrube');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3530,{d '2014-10-30'},{d '2014-10-30'},'35.3 = Fett-/Stärkeabscheider');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3600,{d '2014-10-30'},{d '2014-10-30'},'36 = Biologische Verfahren (Kleinkläranlagen)');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3610,{d '2014-10-30'},{d '2014-10-30'},'36.1 = Tropfkörperanlage');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3620,{d '2014-10-30'},{d '2014-10-30'},'36.2 = Rotations-Tauchkörperanlage');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3630,{d '2014-10-30'},{d '2014-10-30'},'36.3 = Belebungsanlage');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3640,{d '2014-10-30'},{d '2014-10-30'},'36.4 = SBR-Anlage');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3650,{d '2014-10-30'},{d '2014-10-30'},'36.5 = Festbettanlage');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3660,{d '2014-10-30'},{d '2014-10-30'},'36.6 = Wirbelbettanlage');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3670,{d '2014-10-30'},{d '2014-10-30'},'36.7 = Membranbelebung');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3680,{d '2014-10-30'},{d '2014-10-30'},'36.8 = Pflanzenkläranlage vertikal durchströmt');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (3690,{d '2014-10-30'},{d '2014-10-30'},'36.9 = Pflanzenkläranlage horizontal durchströmt');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (36100,{d '2014-10-30'},{d '2014-10-30'},'36.10 = Untergrundverrieselung');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (36110,{d '2014-10-30'},{d '2014-10-30'},'36.11 = Filterkörper');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (36120,{d '2014-10-30'},{d '2014-10-30'},'36.12 = belüftete Teichanlage');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (36130,{d '2014-10-30'},{d '2014-10-30'},'36.13 = unbelüftete Teichanlage');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (36140,{d '2014-10-30'},{d '2014-10-30'},'36.14 = Filtergraben');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (37000,{d '2014-10-30'},{d '2014-10-30'},'37 = Weitergehende Behandlung (Kleinkläranlagen)');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (37100,{d '2014-10-30'},{d '2014-10-30'},'37.1 = Nitrifikation und zusätzliche Denitrifikati');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (37200,{d '2014-10-30'},{d '2014-10-30'},'37.2 = zusätzliche Nitrifikation (Klasse N)');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (37300,{d '2014-10-30'},{d '2014-10-30'},'37.3 = zusätzliche Phosphorelimination (Klasse C/D');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (37400,{d '2014-10-30'},{d '2014-10-30'},'37.4 = zusätzliche Hygienisierung (Klasse C/D/N+H)');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (37500,{d '2014-10-30'},{d '2014-10-30'},'37.5 = zusätzliche Filtration');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (37600,{d '2014-10-30'},{d '2014-10-30'},'37.6 = Schönungsteich');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (37700,{d '2014-10-30'},{d '2014-10-30'},'37.7 = Sonstige weitergehende Behandlungen');
INSERT INTO "ELKA"."ABAVERFAHREN" (NR,AKTUAL_DAT,ERSTELL_DAT,BEZEICHNUNG) VALUES (38000,{d '2015-04-21'},{d '2015-04-21'},'38 = Chargenbetrieb');



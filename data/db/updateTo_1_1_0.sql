ALTER TABLE labor.klaeranlage
   ALTER COLUMN dea_klaeranlage_nr DROP NOT NULL;
   
ALTER TABLE basis.sachbearbeiter
   ALTER COLUMN name DROP NOT NULL;


--Entwässerungsgrundstück

ALTER TABLE oberflgw.entwaesserungsgrundstueck ADD COLUMN wo_tog boolean;
ALTER TABLE oberflgw.entwaesserungsgrundstueck ADD Column mi_tog boolean;
ALTER TABLE oberflgw.entwaesserungsgrundstueck ADD Column ge_tog boolean;
ALTER TABLE oberflgw.entwaesserungsgrundstueck ADD Column gi_tog boolean;
ALTER TABLE oberflgw.entwaesserungsgrundstueck ADD Column gem_tog boolean;
ALTER TABLE oberflgw.entwaesserungsgrundstueck ADD Column str_tog boolean;
ALTER TABLE oberflgw.entwaesserungsgrundstueck ADD COLUMN parkplatz_tog boolean;
ALTER TABLE oberflgw.entwaesserungsgrundstueck ALTER COLUMN erstell_dat DROP NOT NULL;
ALTER TABLE oberflgw.entwaesserungsgrundstueck ALTER COLUMN aktual_dat DROP NOT NULL;

--Einleitungsstelle

ALTER TABLE elka.einleitungsstelle DROP COLUMN nadia_id;

ALTER TABLE elka.einleitungsstelle ADD Column gew_name_stadt character varying;
ALTER TABLE elka.einleitungsstelle ADD Column gew_kennz_stadt character varying;
ALTER TABLE elka.einleitungsstelle ADD Column hwr_massnahme character varying;
ALTER TABLE elka.einleitungsstelle ADD COLUMN gewname_stat character varying;
ALTER TABLE elka.einleitungsstelle ADD COLUMN gewkennz_stat character varying;

CREATE TABLE elka.map_elka_gewkennz (
    gewkz integer,
    gewname character varying
);

--Anfallstelle

ALTER TABLE elka.anfallstelle ADD COLUMN nw_her_bereich_opt integer;
ALTER TABLE elka.anfallstelle DROP COLUMN herkunft;

--Objektarten aktualisieren

UPDATE basis.objektarten SET abteilung = 'Einleiter'
WHERE id = 65 OR id = 66 OR id = 42;


DROP VIEW elka.e_entwaesserungsgrundstueck;

ALTER TABLE oberflgw.entwaesserungsgrundstueck
   ALTER COLUMN name_etw_gebiet TYPE character varying;


CREATE OR REPLACE VIEW elka.e_entwaesserungsgrundstueck AS 
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
  WHERE objekt.standortid = e_standort.nr AND objekt.betreiberid = e_adresse.nr AND entwaesserungsgrundstueck.objekt_id = objekt.id AND objekt._deleted = false;

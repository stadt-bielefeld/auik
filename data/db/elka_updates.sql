SET search_path = elka, pg_catalog;

--Add basis.objekt.id to view
CREATE OR REPLACE VIEW e_betrieb AS
    SELECT DISTINCT e_standort.nr,
        e_standort.nr AS standort_nr,
        e_adresse.nr AS wr_adr_nr,
        e_adresse.name1 AS bezeichnung,
        false AS suevkan_tog,
        e_standort.e32,
        e_standort.n32,
        e_standort.erstell_dat,
        e_standort.aktual_dat,
        e_standort.herkunft,
        objekt.id as objekt_id
    FROM e_adresse,
        basis.objekt as objekt,
        e_standort
    WHERE (((e_adresse.nr = objekt.betreiberid) AND (e_standort.nr = objekt.standortid)) AND (objekt.objektartid = 42));

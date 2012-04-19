CREATE OR REPLACE RULE cascade_enabled_basis_objektchrono AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.basis_objektchrono
    SET _enabled = new._enabled
    WHERE basis_objektchrono.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_enabled_indeinl_genehmigung AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.indeinl_genehmigung
    SET _enabled = new._enabled
    WHERE indeinl_genehmigung.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_enabled_indeinl_uebergabestelle AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.indeinl_uebergabestelle
    SET _enabled = new._enabled
    WHERE indeinl_uebergabestelle.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_enabled_atl_probepkt AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.atl_probepkt
    SET _enabled = new._enabled
    WHERE atl_probepkt.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_enabled_atl_sielhaut AS
  ON UPDATE TO auik.atl_probepkt
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.atl_sielhaut
    SET _enabled = new._enabled
    WHERE atl_sielhaut.id = new.sielhaut_id;

CREATE OR REPLACE RULE cascade_enabled_atl_probenahmen AS
  ON UPDATE TO auik.atl_probepkt
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.atl_probenahmen
    SET _enabled = new._enabled
    WHERE atl_probenahmen.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_enabled_atl_analyseposition AS
  ON UPDATE TO auik.atl_probenahmen
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.atl_analyseposition
    SET _enabled = new._enabled
    WHERE atl_analyseposition.probenahme_id = new.id;

CREATE OR REPLACE RULE cascade_enabled_vaws_fachdaten AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.vaws_fachdaten
    SET _enabled = new._enabled
    WHERE vaws_fachdaten.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_enabled_vaws_anlagenchrono AS
  ON UPDATE TO auik.vaws_fachdaten
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.vaws_anlagenchrono
    SET _enabled = new._enabled
    WHERE vaws_anlagenchrono.behaelterid = new.behaelterid;

CREATE OR REPLACE RULE cascade_enabled_vaws_kontrollen AS
  ON UPDATE TO auik.vaws_fachdaten
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.vaws_kontrollen
    SET _enabled = new._enabled
    WHERE vaws_kontrollen.behaelterid = new.behaelterid;

CREATE OR REPLACE RULE cascade_enabled_vaws_verwaltungsverf AS
  ON UPDATE TO auik.vaws_fachdaten
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.vaws_verwaltungsverf
    SET _enabled = new._enabled
    WHERE vaws_verwaltungsverf.behaelterid = new.behaelterid;

CREATE OR REPLACE RULE cascade_enabled_vaws_verwaltungsgebuehren AS
  ON UPDATE TO auik.vaws_fachdaten
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.vaws_verwaltungsgebuehren
    SET _enabled = new._enabled
    WHERE vaws_verwaltungsgebuehren.behaelterid = new.behaelterid;

CREATE OR REPLACE RULE cascade_enabled_vaws_abfuellflaeche AS
  ON UPDATE TO auik.vaws_fachdaten
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.vaws_abfuellflaeche
    SET _enabled = new._enabled
    WHERE vaws_abfuellflaeche.behaelterid = new.behaelterid;

CREATE OR REPLACE RULE cascade_enabled_vaws_abscheider AS
  ON UPDATE TO auik.vaws_fachdaten
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.vaws_abscheider
    SET _enabled = new._enabled
    WHERE vaws_abscheider.behaelterid = new.behaelterid;

CREATE OR REPLACE RULE cascade_enabled_anh_40_fachdaten AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.anh_40_fachdaten
    SET _enabled = new._enabled
    WHERE anh_40_fachdaten.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_enabled_anh_50_fachdaten AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.anh_50_fachdaten
    SET _enabled = new._enabled
    WHERE anh_50_fachdaten.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_enabled_anh_52_fachdaten AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.anh_52_fachdaten
    SET _enabled = new._enabled
    WHERE anh_52_fachdaten.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_enabled_anh_53_fachdaten AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.anh_53_fachdaten
    SET _enabled = new._enabled
    WHERE anh_53_fachdaten.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_enabled_anh_55_fachdaten AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.anh_55_fachdaten
    SET _enabled = new._enabled
    WHERE anh_55_fachdaten.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_enabled_anh_56_fachdaten AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.anh_56_fachdaten
    SET _enabled = new._enabled
    WHERE anh_56_fachdaten.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_enabled_anh_suev_fachdaten AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.anh_suev_fachdaten
    SET _enabled = new._enabled
    WHERE anh_suev_fachdaten.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_enabled_anh_49_fachdaten AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.anh_49_fachdaten
    SET _enabled = new._enabled
    WHERE anh_49_fachdaten.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_enabled_anh_49_analysen AS
  ON UPDATE TO auik.anh_49_fachdaten
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.anh_49_analysen
    SET _enabled = new._enabled
    WHERE anh_49_analysen.anh49id = new.objektid;

CREATE OR REPLACE RULE cascade_enabled_anh_49_kontrollen AS
  ON UPDATE TO auik.anh_49_fachdaten
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.anh_49_kontrollen
    SET _enabled = new._enabled
    WHERE anh_49_kontrollen.anh49id = new.objektid;

CREATE OR REPLACE RULE cascade_enabled_anh_49_ortstermine AS
  ON UPDATE TO auik.anh_49_fachdaten
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.anh_49_ortstermine
    SET _enabled = new._enabled
    WHERE anh_49_ortstermine.anh49id = new.objektid;

CREATE OR REPLACE RULE cascade_enabled_anh_49_verwaltungsverf AS
  ON UPDATE TO auik.anh_49_fachdaten
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.anh_49_verwaltungsverf
    SET _enabled = new._enabled
    WHERE anh_49_verwaltungsverf.anh49id = new.objektid;

CREATE OR REPLACE RULE cascade_enabled_anh_49_abscheiderdetails AS
  ON UPDATE TO auik.anh_49_fachdaten
  WHERE new._enabled <> old._enabled DO
  UPDATE auik.anh_49_abscheiderdetails
    SET _enabled = new._enabled
    WHERE anh_49_abscheiderdetails.anh49id = new.objektid;

CREATE OR REPLACE RULE cascade_deleted_basis_objektchrono AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.basis_objektchrono
    SET _deleted = new._deleted
    WHERE basis_objektchrono.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_deleted_indeinl_genehmigung AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.indeinl_genehmigung
    SET _deleted = new._deleted
    WHERE indeinl_genehmigung.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_deleted_indeinl_uebergabestelle AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.indeinl_uebergabestelle
    SET _deleted = new._deleted
    WHERE indeinl_uebergabestelle.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_deleted_atl_probepkt AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.atl_probepkt
    SET _deleted = new._deleted
    WHERE atl_probepkt.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_deleted_atl_sielhaut AS
  ON UPDATE TO auik.atl_probepkt
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.atl_sielhaut
    SET _deleted = new._deleted
    WHERE atl_sielhaut.id = new.sielhaut_id;

CREATE OR REPLACE RULE cascade_deleted_atl_probenahmen AS
  ON UPDATE TO auik.atl_probepkt
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.atl_probenahmen
    SET _deleted = new._deleted
    WHERE atl_probenahmen.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_deleted_atl_analyseposition AS
  ON UPDATE TO auik.atl_probenahmen
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.atl_analyseposition
    SET _deleted = new._deleted
    WHERE atl_analyseposition.probenahme_id = new.id;

CREATE OR REPLACE RULE cascade_deleted_vaws_fachdaten AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.vaws_fachdaten
    SET _deleted = new._deleted
    WHERE vaws_fachdaten.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_deleted_vaws_anlagenchrono AS
  ON UPDATE TO auik.vaws_fachdaten
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.vaws_anlagenchrono
    SET _deleted = new._deleted
    WHERE vaws_anlagenchrono.behaelterid = new.behaelterid;

CREATE OR REPLACE RULE cascade_deleted_vaws_kontrollen AS
  ON UPDATE TO auik.vaws_fachdaten
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.vaws_kontrollen
    SET _deleted = new._deleted
    WHERE vaws_kontrollen.behaelterid = new.behaelterid;

CREATE OR REPLACE RULE cascade_deleted_vaws_verwaltungsverf AS
  ON UPDATE TO auik.vaws_fachdaten
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.vaws_verwaltungsverf
    SET _deleted = new._deleted
    WHERE vaws_verwaltungsverf.behaelterid = new.behaelterid;

CREATE OR REPLACE RULE cascade_deleted_vaws_verwaltungsgebuehren AS
  ON UPDATE TO auik.vaws_fachdaten
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.vaws_verwaltungsgebuehren
    SET _deleted = new._deleted
    WHERE vaws_verwaltungsgebuehren.behaelterid = new.behaelterid;

CREATE OR REPLACE RULE cascade_deleted_vaws_abfuellflaeche AS
  ON UPDATE TO auik.vaws_fachdaten
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.vaws_abfuellflaeche
    SET _deleted = new._deleted
    WHERE vaws_abfuellflaeche.behaelterid = new.behaelterid;

CREATE OR REPLACE RULE cascade_deleted_vaws_abscheider AS
  ON UPDATE TO auik.vaws_fachdaten
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.vaws_abscheider
    SET _deleted = new._deleted
    WHERE vaws_abscheider.behaelterid = new.behaelterid;

CREATE OR REPLACE RULE cascade_deleted_anh_40_fachdaten AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.anh_40_fachdaten
    SET _deleted = new._deleted
    WHERE anh_40_fachdaten.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_deleted_anh_50_fachdaten AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.anh_50_fachdaten
    SET _deleted = new._deleted
    WHERE anh_50_fachdaten.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_deleted_anh_52_fachdaten AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.anh_52_fachdaten
    SET _deleted = new._deleted
    WHERE anh_52_fachdaten.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_deleted_anh_53_fachdaten AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.anh_53_fachdaten
    SET _deleted = new._deleted
    WHERE anh_53_fachdaten.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_deleted_anh_55_fachdaten AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.anh_55_fachdaten
    SET _deleted = new._deleted
    WHERE anh_55_fachdaten.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_deleted_anh_56_fachdaten AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.anh_56_fachdaten
    SET _deleted = new._deleted
    WHERE anh_56_fachdaten.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_deleted_anh_suev_fachdaten AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.anh_suev_fachdaten
    SET _deleted = new._deleted
    WHERE anh_suev_fachdaten.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_deleted_anh_49_fachdaten AS
  ON UPDATE TO auik.basis_objekt
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.anh_49_fachdaten
    SET _deleted = new._deleted
    WHERE anh_49_fachdaten.objektid = new.objektid;

CREATE OR REPLACE RULE cascade_deleted_anh_49_analysen AS
  ON UPDATE TO auik.anh_49_fachdaten
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.anh_49_analysen
    SET _deleted = new._deleted
    WHERE anh_49_analysen.anh49id = new.objektid;

CREATE OR REPLACE RULE cascade_deleted_anh_49_kontrollen AS
  ON UPDATE TO auik.anh_49_fachdaten
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.anh_49_kontrollen
    SET _deleted = new._deleted
    WHERE anh_49_kontrollen.anh49id = new.objektid;

CREATE OR REPLACE RULE cascade_deleted_anh_49_ortstermine AS
  ON UPDATE TO auik.anh_49_fachdaten
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.anh_49_ortstermine
    SET _deleted = new._deleted
    WHERE anh_49_ortstermine.anh49id = new.objektid;

CREATE OR REPLACE RULE cascade_deleted_anh_49_verwaltungsverf AS
  ON UPDATE TO auik.anh_49_fachdaten
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.anh_49_verwaltungsverf
    SET _deleted = new._deleted
    WHERE anh_49_verwaltungsverf.anh49id = new.objektid;

CREATE OR REPLACE RULE cascade_deleted_anh_49_abscheiderdetails AS
  ON UPDATE TO auik.anh_49_fachdaten
  WHERE new._deleted <> old._deleted DO
  UPDATE auik.anh_49_abscheiderdetails
    SET _deleted = new._deleted
    WHERE anh_49_abscheiderdetails.anh49id = new.objektid;


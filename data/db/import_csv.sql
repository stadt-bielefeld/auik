--Script to import data from a csv input into the auik database
--Expected filename is: import.csv
--Expected format is:
--Klassifizierung,Wirtschaftszweig,Firmenname,Name,Vorname,E-Mail,Telefon,Mobiltelefon,Plz,Ort,Straße,Hausnr.,Zusatz,Bermerkung
--(Header is suported, Expected delimiter: ,)

BEGIN;
-- Create temporary import table
CREATE TABLE IF NOT EXISTS temp_import (
    id SERIAL PRIMARY KEY,
    klassifizierung character varying(255),
    wirtschaftszweig character varying(255),
    firmenname character varying(255),
    name character varying(255),
    vorname character varying(255),
    email character varying(255),
    telefon character varying(255),
    mobil character varying(255),
    plz character varying(255),
    ort character varying(255),
    strasse character varying(255),
    hausnr integer,
    zusatz character varying(255),
    bemerkung character varying(255)
);

CREATE OR REPLACE FUNCTION insert_row(
    klassifizierung character varying(255),
    wirtschaftszweig_name character varying(255),
    firmenname character varying(255),
    name character varying(255),
    vorname character varying(255),
    email character varying(255),
    telefon character varying(255),
    mobil character varying(255),
    plz character varying(255),
    ort character varying(255),
    strasse character varying(255),
    hausnr integer,
    zusatz character varying(255),
    bemerkung character varying(255)
)
RETURNS void
AS $$
DECLARE
    address_id integer;
    wirtschaftszweig_id integer;
BEGIN
    -- Insert basis.adresse part and return id
    INSERT INTO basis.adresse (strasse, hausnr, hausnrzus, plz, ort)
    VALUES (strasse, hausnr, zusatz, plz, ort)
    RETURNING id INTO address_id;
    -- Get or insert wirtschaftszweig
    IF EXISTS (SELECT 1 FROM basis.wirtschaftszweig WHERE wirtschaftszweig = wirtschaftszweig_name) THEN
        SELECT id INTO wirtschaftszweig_id
        FROM basis.wirtschaftszweig
        WHERE wirtschaftszweig = wirtschaftszweig_name;
    ELSE
        INSERT INTO basis.wirtschaftszweig (id, wirtschaftszweig)
        VALUES (
            (SELECT COALESCE(MAX(id) + 1, 0) FROM basis.wirtschaftszweig),
            wirtschaftszweig_name
        );
        SELECT MAX(id) FROM basis.wirtschaftszweig INTO wirtschaftszweig_id;
    END IF;

    -- Insert inhaber
    INSERT INTO basis.inhaber (adresseid, name, namebetrbeauf, vornamebetrbeauf, telefon, telefax, email,
        bemerkungen, wirtschaftszweigid)
    VALUES (address_id, firmenname, name, vorname, telefon, mobil, email, bemerkung, wirtschaftszweig_id);
END;
$$
LANGUAGE plpgsql;

-- Copy import data to temporary table
COPY temp_import(
    klassifizierung, wirtschaftszweig, firmenname,
    name, vorname, email, telefon, mobil, plz, ort, strasse,
    hausnr, zusatz, bemerkung)
FROM '/opt/auik_db/import.csv'
DELIMITER ','
CSV HEADER;

-- Copy data to their respective tables
DO
$$
DECLARE resultRow RECORD;
BEGIN
    FOR resultRow IN
        SELECT * FROM temp_import
    LOOP
        PERFORM insert_row(
            resultRow.klassifizierung ,
            resultRow.wirtschaftszweig,
            resultRow.firmenname,
            resultRow.name,
            resultRow.vorname,
            resultRow.email,
            resultRow.telefon,
            resultRow.mobil,
            resultRow.plz,
            resultRow.ort,
            resultRow.strasse,
            resultRow.hausnr,
            resultRow.zusatz,
            resultRow.bemerkung
        );
    END LOOP;
END
$$;

-- Clear temporary table
DROP TABLE temp_import;
COMMIT;

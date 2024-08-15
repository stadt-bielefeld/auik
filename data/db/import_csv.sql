-- Script to import data from a CSV input into the AUI-K database.
-- Expected format is a CSV file with header matching the temporary
-- table created in this script.

BEGIN;
-- Create temporary import table
CREATE TEMP TABLE temp_import (
    namezus character varying(255),
    wirtschaftszweig character varying(255),
    name character varying(255),
    namebetrbeauf character varying(255),
    vornamebetrbeauf character varying(255),
    email character varying(255),
    telefon character varying(255),
    telefax character varying(255),
    plz character varying(255),
    ort character varying(255),
    strasse character varying(255),
    hausnr integer,
    hausnrzus character varying(255),
    bemerkungen character varying(255)
);

-- Copy import data to temporary table
\copy temp_import FROM pstdin (FORMAT csv, HEADER true)

-- Copy data to their respective tables
DO
$$
DECLARE resultRow RECORD;
    address_id integer;
    wirtschaftszweig_id integer;
BEGIN
    FOR resultRow IN
        SELECT * FROM temp_import
    LOOP
        -- Insert basis.adresse part and return id
        INSERT INTO basis.adresse (strasse, hausnr, hausnrzus, plz, ort)
            VALUES (resultRow.strasse, resultRow.hausnr,
                resultRow.hausnrzus, resultRow.plz, resultRow.ort)
            RETURNING id INTO address_id;

        -- Get or insert wirtschaftszweig
        IF EXISTS (SELECT 1 FROM basis.wirtschaftszweig
                WHERE wirtschaftszweig = resultRow.wirtschaftszweig) THEN
            SELECT id INTO wirtschaftszweig_id
                FROM basis.wirtschaftszweig
                WHERE wirtschaftszweig = resultRow.wirtschaftszweig;
        ELSE
            INSERT INTO basis.wirtschaftszweig (id, wirtschaftszweig)
                VALUES (
                    (SELECT COALESCE(MAX(id) + 1, 0) FROM basis.wirtschaftszweig),
                    resultRow.wirtschaftszweig
                );
            SELECT MAX(id) FROM basis.wirtschaftszweig INTO wirtschaftszweig_id;
        END IF;

        -- Insert inhaber
        INSERT INTO basis.inhaber (
                adresseid, name, namebetrbeauf, vornamebetrbeauf,
                telefon, telefax, email,
                bemerkungen, wirtschaftszweigid, namezus)
            VALUES (address_id, resultRow.name,
                resultRow.namebetrbeauf, resultRow.vornamebetrbeauf,
                resultRow.telefon, resultRow.telefax, resultRow.email,
                resultRow.bemerkungen, wirtschaftszweig_id,
                resultRow.namezus);
    END LOOP;
END
$$;

COMMIT;

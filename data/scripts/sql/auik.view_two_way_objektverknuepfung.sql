SELECT * FROM auik.basis_objektverknuepfung
UNION
SELECT
  -id,
  objekt,
  ist_verknuepft_mit,
  _enabled,
  _deleted
FROM
  auik.basis_objektverknuepfung
ORDER BY
  ist_verknuepft_mit;

#!/bin/bash

i=0

# Update to basis_objekt => update to basis_objektchrono
tab_parent[$i]="basis_objekt"
 tab_child[$i]="basis_objektchrono"
tab_parent_key[$i]="objektid"
 tab_child_key[$i]="objektid"

i=$(expr $i + 1)

# Update to basis_objekt => update to basis_objektverknuepfung
#tab_parent[$i]="basis_objekt"
# tab_child[$i]="basis_objektverknuepfung"
#tab_parent_key[$i]="objektid"
# tab_child_key[$i]="objekt"

#i=$(expr $i + 1)

# Update to basis_objekt => update to indeinl_genehmigung
tab_parent[$i]="basis_objekt"
 tab_child[$i]="indeinl_genehmigung"
tab_parent_key[$i]="objektid"
 tab_child_key[$i]="objektid"

i=$(expr $i + 1)

# Update to basis_objekt => update to indeinl_uebergabestelle
tab_parent[$i]="basis_objekt"
 tab_child[$i]="indeinl_uebergabestelle"
tab_parent_key[$i]="objektid"
 tab_child_key[$i]="objektid"

i=$(expr $i + 1)

# Update to basis_objekt => update to atl_probepkt
tab_parent[$i]="basis_objekt"
 tab_child[$i]="atl_probepkt"
tab_parent_key[$i]="objektid"
 tab_child_key[$i]="objektid"

i=$(expr $i + 1)

# Update to atl_probepkt => update to atl_sielhaut
tab_parent[$i]="atl_probepkt"
 tab_child[$i]="atl_sielhaut"
tab_parent_key[$i]="sielhaut_id"
 tab_child_key[$i]="id"

i=$(expr $i + 1)

# Update to atl_probepkt => update to atl_probenahmen
tab_parent[$i]="atl_probepkt"
 tab_child[$i]="atl_probenahmen"
tab_parent_key[$i]="objektid"
 tab_child_key[$i]="objektid"

i=$(expr $i + 1)

# Update to atl_probenahmen => update to atl_analyseposition
tab_parent[$i]="atl_probenahmen"
 tab_child[$i]="atl_analyseposition"
tab_parent_key[$i]="id"
 tab_child_key[$i]="probenahme_id"

i=$(expr $i + 1)

# Update to basis_objekt => update to vaws_fachdaten
tab_parent[$i]="basis_objekt"
 tab_child[$i]="vaws_fachdaten"
tab_parent_key[$i]="objektid"
 tab_child_key[$i]="objektid"

i=$(expr $i + 1)

# Update to vaws_fachdaten => update to vaws_anlagenchrono
tab_parent[$i]="vaws_fachdaten"
 tab_child[$i]="vaws_anlagenchrono"
tab_parent_key[$i]="behaelterid"
 tab_child_key[$i]="behaelterid"

i=$(expr $i + 1)

# Update to vaws_fachdaten => update to vaws_kontrollen
tab_parent[$i]="vaws_fachdaten"
 tab_child[$i]="vaws_kontrollen"
tab_parent_key[$i]="behaelterid"
 tab_child_key[$i]="behaelterid"

i=$(expr $i + 1)

# Update to vaws_fachdaten => update to vaws_verwaltungsverf
tab_parent[$i]="vaws_fachdaten"
 tab_child[$i]="vaws_verwaltungsverf"
tab_parent_key[$i]="behaelterid"
 tab_child_key[$i]="behaelterid"

i=$(expr $i + 1)

# Update to vaws_fachdaten => update to vaws_verwaltungsgebuehren
tab_parent[$i]="vaws_fachdaten"
 tab_child[$i]="vaws_verwaltungsgebuehren"
tab_parent_key[$i]="behaelterid"
 tab_child_key[$i]="behaelterid"

i=$(expr $i + 1)

# Update to vaws_fachdaten => update to vaws_abfuellflaeche
tab_parent[$i]="vaws_fachdaten"
 tab_child[$i]="vaws_abfuellflaeche"
tab_parent_key[$i]="behaelterid"
 tab_child_key[$i]="behaelterid"

i=$(expr $i + 1)

# Update to vaws_fachdaten => update to vaws_abscheider
tab_parent[$i]="vaws_fachdaten"
 tab_child[$i]="vaws_abscheider"
tab_parent_key[$i]="behaelterid"
 tab_child_key[$i]="behaelterid"

i=$(expr $i + 1)

# Update to basis_objekt => update to anh_40_fachdaten
tab_parent[$i]="basis_objekt"
 tab_child[$i]="anh_40_fachdaten"
tab_parent_key[$i]="objektid"
 tab_child_key[$i]="objektid"

i=$(expr $i + 1)

# Update to basis_objekt => update to anh_50_fachdaten
tab_parent[$i]="basis_objekt"
 tab_child[$i]="anh_50_fachdaten"
tab_parent_key[$i]="objektid"
 tab_child_key[$i]="objektid"

i=$(expr $i + 1)

# Update to basis_objekt => update to anh_52_fachdaten
tab_parent[$i]="basis_objekt"
 tab_child[$i]="anh_52_fachdaten"
tab_parent_key[$i]="objektid"
 tab_child_key[$i]="objektid"

i=$(expr $i + 1)

# Update to basis_objekt => update to anh_53_fachdaten
tab_parent[$i]="basis_objekt"
 tab_child[$i]="anh_53_fachdaten"
tab_parent_key[$i]="objektid"
 tab_child_key[$i]="objektid"

i=$(expr $i + 1)

# Update to basis_objekt => update to anh_55_fachdaten
tab_parent[$i]="basis_objekt"
 tab_child[$i]="anh_55_fachdaten"
tab_parent_key[$i]="objektid"
 tab_child_key[$i]="objektid"

i=$(expr $i + 1)

# Update to basis_objekt => update to anh_56_fachdaten
tab_parent[$i]="basis_objekt"
 tab_child[$i]="anh_56_fachdaten"
tab_parent_key[$i]="objektid"
 tab_child_key[$i]="objektid"

i=$(expr $i + 1)

# Update to basis_objekt => update to anh_suev_fachdaten
tab_parent[$i]="basis_objekt"
 tab_child[$i]="anh_suev_fachdaten"
tab_parent_key[$i]="objektid"
 tab_child_key[$i]="objektid"

i=$(expr $i + 1)

# Update to basis_objekt => update to anh_49_fachdaten
tab_parent[$i]="basis_objekt"
 tab_child[$i]="anh_49_fachdaten"
tab_parent_key[$i]="objektid"
 tab_child_key[$i]="objektid"

i=$(expr $i + 1)

# Update to anh_49_fachdaten => update to anh_49_analysen
tab_parent[$i]="anh_49_fachdaten"
 tab_child[$i]="anh_49_analysen"
tab_parent_key[$i]="objektid"
 tab_child_key[$i]="anh49id"

i=$(expr $i + 1)

# Update to anh_49_fachdaten => update to anh_49_kontrollen
tab_parent[$i]="anh_49_fachdaten"
 tab_child[$i]="anh_49_kontrollen"
tab_parent_key[$i]="objektid"
 tab_child_key[$i]="anh49id"

i=$(expr $i + 1)

# Update to anh_49_fachdaten => update to anh_49_ortstermine
tab_parent[$i]="anh_49_fachdaten"
 tab_child[$i]="anh_49_ortstermine"
tab_parent_key[$i]="objektid"
 tab_child_key[$i]="anh49id"

i=$(expr $i + 1)

# Update to anh_49_fachdaten => update to anh_49_verwaltungsverf
tab_parent[$i]="anh_49_fachdaten"
 tab_child[$i]="anh_49_verwaltungsverf"
tab_parent_key[$i]="objektid"
 tab_child_key[$i]="anh49id"

i=$(expr $i + 1)

# Update to anh_49_fachdaten => update to anh_49_abscheiderdetails
tab_parent[$i]="anh_49_fachdaten"
 tab_child[$i]="anh_49_abscheiderdetails"
tab_parent_key[$i]="objektid"
 tab_child_key[$i]="anh49id"

for field in _enabled _deleted; do
    for i in $(seq 0 $i); do
#	echo $i
	echo "CREATE OR REPLACE RULE cascade${field}_${tab_child[$i]} AS"
	echo "  ON UPDATE TO auik.${tab_parent[$i]}"
	echo "  WHERE new.${field} <> old.${field} DO"
	echo "  UPDATE auik.${tab_child[$i]}"
	echo "    SET ${field} = new.${field}"
	echo "    WHERE ${tab_child[$i]}.${tab_child_key[$i]} = new.${tab_parent_key[$i]};"
	echo ""
    done
done

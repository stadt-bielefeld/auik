/**
 * Copyright 2005-2011, Stadt Bielefeld
 *
 * This file is part of AUIK (Anlagen- und Indirekteinleiter-Kataster).
 *
 * AUIK is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 2 of the License, or (at your
 * option) any later version.
 *
 * AUIK is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with AUIK. If not, see <http://www.gnu.org/licenses/>.
 *
 * AUIK has been developed by Stadt Bielefeld and Intevation GmbH.
 */
package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import de.bielefeld.umweltamt.aui.mappings.elka_sync.ESonderbauwerk;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

public class ESonderbauwerkModel extends ListTableModel {

    public ESonderbauwerkModel() {
        super(new String[]{
                "Nr",
                "Gemeinde",
                "Bezeichnung",
                "Kurzbeschreibung",
                "entw_einzugsgeb_opt",
                "typ_opt",
                "Inbetriebnahme",
                "Stillgelegt am",
                "Wiederinbetriebnahme am",
                "Standort",
                "Betreiber",
                "anspr_adr_nr",
                "Beschreibung",
                "E32",
                "N32",
                "in_nrw_tog",
                "industr_tog",
                "name_ausserhalb_nrw",
                "k_entw_gebiet",
                "bef_flaeche_egebiet",
                "Abflussbeiwert",
                "undurch_flaeche",
                "beckentyp_opt",
                "rohrspeicher_tog",
                "bauweise_tog",
                "drossel_opt",
                "so_drossel",
                "beckensteuerung_opt",
                "a_rei_einrich_tog",
                "messart_tog",
                "drossel_tog",
                "fuellstand_tog",
                "entlastungswasser_tog",
                "entlastungsdauer_tog",
                "entlastungshaeufig_tog",
                "fern_mess_tog",
                "niederschlag_tog",
                "fern_stoer_tog",
                "hwfrei_tog",
                "einstau_haeufig",
                "hw_einrichtung_tog",
                "hw_rueckstau_tog",
                "hw_schieber_tog",
                "hw_pumpwerk_tog",
                "hw_weitere_tog",
                "hw_sonst_text",
                "bemes_weiterg_tog",
                "bemessung_text",
                "anordnung_opt",
                "wasserrecht_genehmigung_nr",
                "beckenart_opt",
                "beckentiefe",
                "beh_flaeche_1u2",
                "beh_flaeche_2u3",
                "betriebsart_opt",
                "csb",
                "drosselabfluss",
                "dross_abfluss_opt",
                "dross_ueber_tog",
                "entlastungsart_opt",
                "Entleerungszeit",
                "Flächenbeschickung",
                "fliesszeit",
                "fremd_abfluss",
                "funktion_opt",
                "kanal_vol",
                "qkrit",
                "kritisch_misch",
                "max_h_schmutzabfluss",
                "max_h_trocken",
                "min_dr_abfluss",
                "misch_ueberlauf",
                "n_mindest_v",
                "n_spez_vol",
                "Neigung",
                "rdrosseldurchfluss",
                "regenabfl_dross",
                "regenabfl_entl",
                "regenabfluss",
                "regenabfluss_dr",
                "regenspende",
                "rfilterflaeche",
                "rfiltergeschwin",
                "rfiltersubstrat_h",
                "rhyd_wirkungsgrad",
                "rjahr_ueh",
                "rkrit",
                "rm_filterbelastung",
                "rspez_filtervol",
                "rstauvolumen",
                "rvo_slamelle",
                "rw_krit_abluss",
                "rw_krit_misch",
                "rw_mindest_misch",
                "schmutz_abfluss",
                "sku_anstroem",
                "sku_mindest_svol",
                "sku_sku_spez_vol",
                "spez_beckenvol",
                "spez_speicher",
                "trocken_w_ablfuss",
                "speichervolumen",
                "w_oberflaechen",
                "erstellt_dat",
                "aktual_dat",
                "herkunft",
                "external_nr"
                },
                false,
                true);
    }

	@Override
	public void updateList() throws Exception {
	}

	@Override
	public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Object value;

        ESonderbauwerk sb = (ESonderbauwerk) objectAtRow;
        switch(columnIndex) {
            default:
                value = null;
        }

        return value;
	}

}

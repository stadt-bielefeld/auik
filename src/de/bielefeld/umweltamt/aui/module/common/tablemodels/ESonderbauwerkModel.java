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
            case 0: return sb.getNr();
            case 1: return sb.getGemeindeId();
            case 2: return sb.getBezeichnung();
            case 3: return sb.getKurzbeschreibung();
            case 4: return sb.getEntwEinzugsgebOpt();
            case 5: return sb.getTypOpt();
            case 6: return sb.getInbetriebnahme();
            case 7: return sb.getStillgelegtAm();
            case 8: return sb.getWiederinbetrDat();
            case 9: return sb.getStandort().getNr();
            case 10: return sb.getAdresse().getName1();
            case 11: return sb.getAnsprAdrNr();
            case 12: return sb.getBeschreibung();
            case 13: return sb.getE32();
            case 14: return sb.getN32();
            case 15: return sb.getInNrwTog();
            case 16: return sb.getNameAusserhalbNrw();
            case 17: return sb.getKEntwGebiet();
            case 18: return sb.getBefFlaecheEgebiet();
            case 19: return sb.getAbflussbeiwert();
            case 20: return sb.getBefGrad();
            case 21: return sb.getUndurchFlaeche();
            case 22: return sb.getBeckentypOpt();
            case 23: return sb.getRohrspeicherTog();
            case 24: return sb.getBauweiseOpt();
            case 25: return sb.getDrosselOpt();
            case 26: return sb.getSoDrossel();
            case 27: return sb.getBeckensteuerungOpt();
            case 28: return sb.getAReiEinrichTog();
            case 29: return sb.getMessartTog();
            case 30: return sb.getDrosselTog();
            case 31: return sb.getFuellstandTog();
            case 32: return sb.getEntlastungswasserTog();
            case 33: return sb.getEntlastungsdauerTog();
            case 34: return sb.getEntlastungshaeufigTog();
            case 35: return sb.getFernMessTog();
            case 36: return sb.getNiederschlagTog();
            case 37: return sb.getFernStoerTog();
            case 38: return sb.getHwfreiTog();
            case 39: return sb.getHwRueckstauTog();
            case 40: return sb.getHwSchieberTog();
            case 41: return sb.getHwPumpwerkTog();
            case 42: return sb.getHwWeitereTog();
            case 43: return sb.getHwSonstText();
            case 44: return sb.getBemesWeitergTog();
            case 45: return sb.getBemessungText();
            case 46: return sb.getAnordnungOpt();
            case 47: return sb.getWasserrechtGenehmigungNr();
            case 48: return sb.getBeckenartOpt();
            case 49: return sb.getBeckentiefe();
            case 50: return sb.getBehFlaeche1u2();
            case 51: return sb.getBehFlaeche2u3();
            case 52: return sb.getBetriebsartOpt();
            case 53: return sb.getCsb();
            case 54: return sb.getDrosselabfluss();
            case 55: return sb.getDrossUeberTog();
            case 56: return sb.getEntlastungsartOpt();
            case 57: return sb.getEntleerungszeit();
            case 58: return sb.getFlaechenbeschickung();
            case 59: return sb.getFliesszeit();
            case 60: return sb.getFremdAbfluss();
            case 61: return sb.getFunktionOpt();
            case 62: return sb.getKanalVol();
            case 63: return sb.getQrkrit();
            case 64: return sb.getKritischMisch();
            case 65: return sb.getMaxHSchmutzabfluss();
            case 66: return sb.getMaxHTrocken();
            case 67: return sb.getMinDrAbfluss();
            case 68: return sb.getMischUeberlauf();
            case 69: return sb.getNMindestV();
            case 70: return sb.getNSpezVol();
            case 71: return sb.getNeigung();
            case 72: return sb.getRdrosseldurchfluss();
            case 73: return sb.getRegenabflDross();
            case 74: return sb.getRegenabflEntl();
            case 75: return sb.getRegenabfluss();
            case 76: return sb.getRegenabflussDr();
            case 77: return sb.getRegenspende();
            case 78: return sb.getRfilterflaeche();
            case 79: return sb.getRfilterflaeche();
            case 80: return sb.getRfiltersubstratH();
            case 81: return sb.getRhydWirkungsgrad();
            case 82: return sb.getRjahrUeh();
            case 83: return sb.getRkrit();
            case 84: return sb.getRmFilterbelastung();
            case 85: return sb.getRspezFiltervol();
            case 86: return sb.getRvolSlamelle();
            case 87: return sb.getRwKritAbfluss();
            case 88: return sb.getRwKritMisch();
            case 89: return sb.getRwMindestMisch();
            case 90: return sb.getSchmutzAbfluss();
            case 91: return sb.getSkuAnstroem();
            case 92: return sb.getSkuSpezVol();
            case 93: return sb.getSpezBeckenvol();
            case 94: return sb.getSpezSpeicher();
            case 95: return sb.getTrockenWAbfluss();
            case 96: return sb.getSpeichervolumen();
            case 97: return sb.getWOberflaeche();
            case 98: return sb.getErstellDat();
            case 99: return sb.getAktualDat();
            case 100: return sb.getHerkunft();
            case 101: return sb.getExternalNr();
            default:
                value = null;
        }

        return value;
	}

}

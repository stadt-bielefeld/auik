/*
 * Datei:
 * $Id: Anh50Model.java,v 1.3 2010-01-20 12:55:43 u633d Exp $
 *
 * Erstellt am 15.08.2005 von David Klotz
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2009/03/24 12:35:23  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.1  2008/06/05 11:38:40  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.1  2005/08/24 08:42:52  u633d
 * - Auswertungen und anderes
 *
 *
 */
package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh50Fachdaten;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;

/**
 * Ein einfaches TableModel für Anhang 50-Fachdaten.
 * @author David Klotz
 */
public class Anh50Model extends ListTableModel {
    public Anh50Model() {
        super(new String[]{
                "Betreiber",
                "Standort",
                "Wiedervorl.",
                "Bemerkungen"
        },
        false);
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        Anh50Fachdaten fd = (Anh50Fachdaten) objectAtRow;
        Object tmp;

        switch (columnIndex) {
        case 0:
            tmp = fd.getBasisObjekt().getBasisBetreiber();
            break;
        case 1:
            tmp = fd.getBasisObjekt().getBasisStandort();
            break;
        case 2:
            tmp = AuikUtils.getStringFromDate(fd.getWiedervorlage());
            break;
        case 3:
            tmp = fd.getBemerkungen();
            break;

        default:
            tmp = "ERROR";
            break;
        }
        if (fd.getBasisObjekt().getInaktiv() == true)
        {
            tmp = "<html><strike>" + tmp + "</strike></html>";
        }
        return tmp;
    }

    /*
     * Leer, da kein Updaten der Liste nötig/möglich.
     */
    public void updateList() {
    }
}

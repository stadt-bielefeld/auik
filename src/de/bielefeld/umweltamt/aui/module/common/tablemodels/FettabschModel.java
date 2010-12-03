package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Abscheiderdetails;
import de.bielefeld.umweltamt.aui.mappings.indeinl.Anh49Fachdaten;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;
/**
 * Ein einfaches TableModel für Fettabscheider.
 * @author Sebastian Geller
 */
public class FettabschModel extends ListTableModel {
    public FettabschModel() {
        super(new String[]{
                "Betreiber",
                "Standort",
                "Nenngröße",
                "Bemerkungen"
        },
        false);
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
         Anh49Abscheiderdetails ad = null;
         Anh49Fachdaten fd = null;
        String objectString = objectAtRow.toString();
        String kennung;
        if (objectString.contains("Anh49"))
        {
            fd = (Anh49Fachdaten) objectAtRow;
            kennung = "Fachdaten";
        }
        else
        {
          ad = (Anh49Abscheiderdetails) objectAtRow;
          kennung = "Details";
        }

        Object tmp;



        switch (columnIndex) {
        case 0:
            if (kennung == "Fachdaten")
            {
                tmp = fd.getBasisObjekt().getBasisBetreiber();
            }
            else
            {
                tmp = ad.getAnh49Fachdaten().getBasisObjekt().getBasisBetreiber();
            }
            break;

        case 1:
            if (kennung == "Fachdaten")
            {
                tmp = fd.getBasisObjekt().getBasisStandort();
            }
            else
            {
                tmp = ad.getAnh49Fachdaten().getBasisObjekt().getBasisStandort();
            }
            break;

        case 2:

            if (kennung == "Fachdaten")
            {
                tmp = "Keine Details vorhanden";
            }
            else
            {
                tmp = ad.getNenngroesse();

                if (tmp == null)
                {
                    tmp = "Keine Nenngröße angegeben";
                }
            }
            break;

        case 3:
            if (kennung == "Fachdaten")
            {
                tmp = fd.getBemerkungen();
            }
            else
            {
                tmp = ad.getAnh49Fachdaten().getBemerkungen();
            }
            break;

        default:
            tmp = "ERROR";
            break;



        }
        if (kennung == "Fachdaten")
        {
            if (fd.getBasisObjekt().getInaktiv() == true)
            {
                tmp = "<html><strike>" + tmp + "</strike></html>";
            }
        }
        else
        {
            if (ad.getAnh49Fachdaten().getBasisObjekt().getInaktiv() == true)
            {
                tmp = "<html><strike>" + tmp + "</strike></html>";
            }
        }
        return tmp;
    }

    /*
     * Leer, da kein Updaten der Liste nötig/möglich.
     */
    public void updateList()
    {

    }


}

package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import java.util.Arrays;

import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;


public class SielhautModel extends ListTableModel {
    private static final long serialVersionUID = -5313844117284881446L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    public SielhautModel() {
        super(new String[] {"Bezeichnung", "Lage", "R", "F", "N", "I"}, false);
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
    	Object[] obj = (Object[]) objectAtRow;
        Object tmp;

        switch (columnIndex) {
            case 0:
                tmp = obj[1];
                break;
            case 1:
                tmp = obj[2];
                break;
            case 2:
                if (obj[3] == null) {
                    tmp = new Boolean(false);
                } else {
                    tmp = new Boolean((Boolean) obj[3]);
                }
                break;
            case 3:
                if (obj[4] == null) {
                    tmp = new Boolean(false);
                } else {
                    tmp = new Boolean((Boolean) obj[4]);
                }
                break;
            case 4:
                if (obj[5] == null) {
                    tmp = new Boolean(false);
                } else {
                    tmp = new Boolean((Boolean) obj[5]);
                }
                break;
            case 5:
                tmp = new Boolean((Boolean) obj[6]);
                break;
            default:
                tmp = "FEHLER!";
                break;
        }

        // This is too slooow... :(
//        if (tmp instanceof String && spunkt.getBasisObjekt().getInaktiv()) {
//            tmp = StringUtils.setStrike((String) tmp);
//        }

        return tmp;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex > 1) {
            return Boolean.class;
        } else {
            return super.getColumnClass(columnIndex);
        }
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#updateList()
     */
    @Override
    public void updateList() {
    }

    public void filterList(String suche) {
    	Object[] array = DatabaseQuery.findSielhaut(suche);
        setList(Arrays.asList(array));
        log.debug("Suche nach '" + suche + "' (" + getList().size()
            + " Ergebnisse)");
    }
}

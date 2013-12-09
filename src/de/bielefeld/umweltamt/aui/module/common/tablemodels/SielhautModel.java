package de.bielefeld.umweltamt.aui.module.common.tablemodels;

import de.bielefeld.umweltamt.aui.mappings.DatabaseQuery;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlSielhaut;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel;


public class SielhautModel extends ListTableModel {
    private static final long serialVersionUID = -5313844117284881446L;
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    public SielhautModel() {
        super(new String[] {"Bezeichnung", "Lage", "R", "F", "N"}, false);
    }

    /* (non-Javadoc)
     * @see de.bielefeld.umweltamt.aui.utils.tablemodelbase.ListTableModel#getColumnValue(java.lang.Object, int)
     */
    @Override
    public Object getColumnValue(Object objectAtRow, int columnIndex) {
        AtlSielhaut spunkt = (AtlSielhaut) objectAtRow;
        Object tmp;

        switch (columnIndex) {
            case 0:
                tmp = spunkt.getBezeichnung();
                break;
            case 1:
                tmp = spunkt.getLage();
                break;
            case 2:
                if (spunkt.getPSielhaut() == null) {
                    tmp = new Boolean(false);
                } else {
                    tmp = new Boolean(spunkt.getPSielhaut());
                }
                break;
            case 3:
                if (spunkt.getPFirmenprobe() == null) {
                    tmp = new Boolean(false);
                } else {
                    tmp = new Boolean(spunkt.getPFirmenprobe());
                }
                break;
            case 4:
                if (spunkt.getPNachprobe() == null) {
                    tmp = new Boolean(false);
                } else {
                    tmp = new Boolean(spunkt.getPNachprobe());
                }
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
        setList(DatabaseQuery.findSielhaut(suche));
        log.debug("Suche nach '" + suche + "' (" + getList().size()
            + " Ergebnisse)");
    }
}

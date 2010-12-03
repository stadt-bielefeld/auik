/*
 * Created on 04.02.2005
 */
package de.bielefeld.umweltamt.aui.utils;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox.KeySelectionManager;

/**
 * Ein KeySelectionManager, der das Datenmodell basierend auf den
 * eingegebenen Buchstaben durchsucht.
 * Wenn die Zeit zwischen zwei Tastendrücken größer als maxTimeDif
 * ist, wird mit der Suche von vorne angefangen.
 * @author David Klotz
 * @see <a href="http://emoxie.com/whitepapers/ExtendSwing-v2.html">Extending Swing By Example</a>
 */
public class MyKeySelectionManager implements KeySelectionManager {
    private String keys = null;
    private int curSelected = 0;
    private long lastTime = 0;
    private long maxTimeDif = 700;

    /**
     * Durchsucht das Datenmodell basierend auf dem eingegebenen
     * Buchstaben.
     * Wenn die Zeit zwischen zwei Tastendrücken größer als maxTimeDif
     * ist, wird mit der Suche von vorne angefangen.
     *
     * @param aKey Der getippte Buchstabe
     * @param aModel Das Model der ComboBox
     * @return Den Index des ausgewählten Items
     */
    public int selectionForKey(char aKey, ComboBoxModel aModel) {
        int iCount = aModel.getSize();
        int iPatternLen = 0;
        int iSelected = 0;
        String sItem = null;

        long curTime = System.currentTimeMillis();
        long timeDif = curTime - lastTime;

        if (timeDif > maxTimeDif) {
            keys = null;
        }

        if (Character.getNumericValue(aKey) == -1) {
            keys = null;
        } else {
            if (keys != null) {
                keys = keys + (new Character(aKey)).toString();
            } else {
                keys = (new Character(aKey)).toString();
            }

            if (keys != null) {
                iPatternLen = keys.length();
            }

            for (int pos = 0; pos < iCount; pos++) {
                sItem = aModel.getElementAt(pos).toString();

                if (sItem.length() >= iPatternLen) {
                    if (sItem.substring(0,iPatternLen).toLowerCase().equals(keys.toLowerCase())) {
                        iSelected = pos;
                        curSelected = iSelected;
                        break;
                    }
                } else {
                    iSelected = curSelected;
                }
            }
        }

        //System.out.println("KEYMANAGER - char: '" + aKey + "', keys: \"" + keys + "\", curSel: " + curSelected + ", timeDif: " + timeDif);
        lastTime = curTime;
        return curSelected;
    }

    /**
     * Liefert die maximale Zeit zwischen zwei Tastendrücken, über
     * der angenommen wird, dass eine neue Suche begonnen werden soll.
     * @return Die maximale Zeit zwischen zwei Tastendrücken.
     */
    public long getMaxTimeDif() {
        return maxTimeDif;
    }

    /**
     * Setzt die maximale Zeit zwischen zwei Tastendrücken, über
     * der angenommen wird, dass eine neue Suche begonnen werden soll.
     * @param maxTimeDif Die neue maximale Zeit zwischen zwei Tastendrücken.
     */
    public void setMaxTimeDif(long maxTimeDif) {
        this.maxTimeDif = maxTimeDif;
    }
}

/*
 * Created on 28.01.2005
 */
package de.bielefeld.umweltamt.aui.utils;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 * Eine Action um mit Tab zwischen Komponenten hin- und her zu 
 * springen, auch wenn bei ihnen Tab standardmäßig eine andere
 * Funktion hat. 
 * @author David Klotz
 */
public class TabAction extends AbstractAction {
	private Vector components = new Vector();
	private int currentComp = 0;
	private KeyStroke tabKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0, false);
	
	/**
	 * Erzeugt eine Action um mit Tab zwischen Komponenten hin- und 
	 * her zu springen, auch wenn bei ihnen Tab standardmäßig 
	 * eine andere Funktion hat. 
	 * Komponenten müssen mit addComp(c) hinzugefügt werden.
	 */
	public TabAction() {
		super();
	}
	
	/**
	 * Ein Konstruktor um es einfacher zu machen, eine Action zu
	 * erzeugen, um mit Tab zwischen zwei Komponenten hin- und herzuschalten.
	 * @param c1 Die erste Komponente
	 * @param c2 Die zweite Komponente
	 */
	public TabAction(JComponent c1, JComponent c2) {
		super();
		addComp(c1);
		addComp(c2);
	}
	
	/**
	 * Springt zur nächsten Komponente in der Liste.
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		// Natürlich können wir nur wechseln, wenn wir mindestens zwei Komponenten haben
		if (components.size() > 1) {
			// Erstmal herausfinden, bei welcher Komponente wir gerade sind
			JComponent src = (JComponent) e.getSource();
			if (components.contains(src)) {
				for (int i = 0; i < components.size(); i++) {
					if (src == components.get(i)) {
						currentComp = i;
					}
				}
			}
			
			// Wenn wir schon am Ende der Liste sind...
			if (currentComp < components.size() - 1) {
				currentComp += 1;
			} else {
				// ... fangen wir von vorne an
				currentComp = 0;
			}
			
			// Den Fokus zur jetzt aktuellen Komponente wechseln.
			JComponent cur = (JComponent) components.get(currentComp);
			//AUIKataster.debugOutput("Component " + cur + "has Focus!", "TabAction");
			// Textfelder machen das schon von selber richtig
			if (!(src instanceof JTextField)) {
				cur.requestFocus();
			}
		}
	}

	/**
	 * Fügt eine neue Komponente in die Liste derer ein, zwischen
	 * denen mit Tab hin- und hergewechselt werden kann.
	 * @param newComp Die neue Komponente
	 */
	public void addComp(JComponent newComp) {
		components.add(newComp);
		newComp.getInputMap().put(tabKeyStroke, "TAB");
		newComp.getActionMap().put("TAB", this);
	}
}

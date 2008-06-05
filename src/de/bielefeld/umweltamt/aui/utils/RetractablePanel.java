/*
 * Created on 15.02.2005 by u633z
 */
package de.bielefeld.umweltamt.aui.utils;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Ein zusammenklappbares Panel.
 * @author David Klotz
 */
public class RetractablePanel extends JPanel {
	private JLabel iconLabel = null;
	private JPanel topPanel = null;
	private JPanel panel = null;
	private JComponent topComp = null;
	
	private Icon boxOpen = AuikUtils.getIcon("box_minus.png", "Open");
	private Icon boxClosed = AuikUtils.getIcon("box_plus.png", "Closed");
	
	private boolean open;
	
	/**
	 * Erzeugt ein neues Panel im zusammengeklappten Zustand.
	 * @param name Die Überschrift
	 * @param content Das Panel mit dem Inhalt
	 */
	public RetractablePanel(String name, JPanel content) {
		this(name, content, false, null);
	}
	
	/**
	 * Erzeugt ein neues Panel im zusammengeklappten Zustand 
	 * mit einem Tooltip am Label.
	 * @param name Die Überschrift
	 * @param content Das Panel mit dem Inhalt
	 * @param toolTip Der Tooltip für das Label
	 */
	public RetractablePanel(String name, JPanel content, String toolTip) {
		this(name, content, false, toolTip);
	}
	
	/**
	 * Erzeugt ein neues zusammenklappbares Panel
	 * mit einem Tooltip am Label.
	 * @param name Die Überschrift
	 * @param content Das Panel mit dem Inhalt
	 * @param isOpen Soll das Panel anfangs aufgeklappt sein? 
	 * @param toolTip Der Tooltip für das Label
	 */
	public RetractablePanel(String name, JPanel content, boolean isOpen, String toolTip) {
		this(new JLabel(name, JLabel.LEADING), content, isOpen, toolTip);
	}
	
	/**
	 * Erzeugt ein neues zusammenklappbares Panel
	 * mit einem Tooltip am Label.
	 * @param top Die Überschrifts-Komponente
	 * @param content Das Panel mit dem Inhalt
	 * @param isOpen Soll das Panel anfangs aufgeklappt sein? 
	 * @param toolTip Der Tooltip für das Label
	 */
	public RetractablePanel(JComponent top, JPanel content, boolean isOpen, String toolTip) {
		super(new BorderLayout());
		 
		iconLabel = new JLabel(boxOpen, JLabel.LEADING);
		iconLabel.setToolTipText(toolTip);
		iconLabel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
		
		topComp = top;
		topComp.setAlignmentX(JPanel.LEFT_ALIGNMENT);
		panel = content;
		
		topPanel = new JPanel();
		BoxLayout box = new BoxLayout(topPanel, BoxLayout.LINE_AXIS);
		topPanel.setLayout(box);
		
		Cursor handCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
		MouseListener ml = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) {
					toggleOpen();
				}
			}
		};
		
		topPanel.add(iconLabel);
		topPanel.add(Box.createHorizontalStrut(5));
		topPanel.add(topComp);
		
		topPanel.setCursor(handCursor);
		iconLabel.addMouseListener(ml);
		topPanel.addMouseListener(ml);
		
		this.add(topPanel, BorderLayout.NORTH);
		
		setOpen(isOpen);
	}
	
	/**
	 * Fragt ab, ob das Panel aufgeklappt ist.
	 * @return <code>true</code>, wenn das Panel aufgeklappt ist, sonst <code>false</code> 
	 */
	public boolean isOpen() {
		return open;
	}
	
	/**
	 * Klappt das Panel auf oder zu.
	 * @param open <code>true</code>, wenn das Panel aufgeklappt werden soll, sonst <code>false</code>
	 */
	public void setOpen(boolean open) {
		this.open = open;
		if (open) {
			opening();
		} else {
			closing();
		}
		
		Dimension newSize;
		Dimension topSize = topPanel.getPreferredSize();
		Dimension contentSize = panel.getPreferredSize();
		
		if (open) {
			iconLabel.setIcon(boxOpen);
			this.add(panel, BorderLayout.CENTER);
			int newHeight = topSize.height + contentSize.height;
			newSize = new Dimension(Math.max(topSize.width, contentSize.width), newHeight);
		} else {
			iconLabel.setIcon(boxClosed);
			this.remove(panel);
			newSize = topSize;//new Dimension(topSize.width, topSize.height);
		}
		
		this.setMinimumSize(newSize);
		this.setPreferredSize(newSize);
		//this.setMaximumSize(newSize);
		
		Container parent = this.getParent();
		
		if (parent != null) {
			if (parent instanceof JComponent) {
				((JComponent) parent).revalidate();
			} else {
				parent.validate();
			}
			parent.repaint();
		}
	}
	
	/**
	 * Klappt das Panel auf oder zu, je nachdem, in welchem
	 * Zustand es sich im Moment befindet.
	 */
	public void toggleOpen() {
		setOpen(!isOpen());
	}
	
	/**
	 * This method is called just before the panel opens.
	 * This is just here to simplify creating subclasses
	 * that react to the panel opening.
	 */
	public void opening() {
	}
	
	/**
	 * This method is called just before the panel closes.
	 * This is just here to simplify creating subclasses
	 * that react to the panel closing.
	 */
	public void closing() {
	}
}

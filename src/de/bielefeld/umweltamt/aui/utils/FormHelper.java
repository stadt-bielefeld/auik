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
package de.bielefeld.umweltamt.aui.utils;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;

/**
 * Kleine Hilfsklasse für GridBagLayout-Formulare.
 * Ersetzt die früher verwendeten JGoodies-Klassen
 * (FormLayout, DefaultFormBuilder, PanelBuilder, CellConstraints, Paddings).
 *
 * Grundmuster (wie in den bereits migrierten Panels):
 * <pre>
 *   JPanel inner = FormHelper.createFormPanel();
 *   int row = 0;
 *   FormHelper.addSeparator(inner, row++, "Fachdaten");
 *   FormHelper.addLabelField(inner, row++, 0, "Name", nameFeld);
 *   ...
 *   this.setLayout(new BorderLayout());
 *   this.add(FormHelper.wrapNorthWest(inner), BorderLayout.NORTH);
 * </pre>
 *
 * Spalten: Ein Label/Feld-Paar belegt zwei GridBag-Spalten.
 * Paar 0 = Spalten 0+1, Paar 1 = Spalten 2+3 usw.
 */
public final class FormHelper {

    /** Standardbreite für Textfelder, Comboboxen und Datumsfelder (px). */
    public static final int FIELD_WIDTH = 200;

    /** Standardabstand zwischen den Zellen. */
    public static final Insets INSETS = new Insets(3, 5, 3, 5);

    /** Rand um ein Formular / einen Dialoginhalt (ersetzt Paddings.DIALOG). */
    public static final int DIALOG_PADDING = 12;

    private FormHelper() {
    }

    // ── Panels ─────────────────────────────────────────────────────

    /** Neues Panel mit GridBagLayout. */
    public static JPanel createFormPanel() {
        return new JPanel(new GridBagLayout());
    }

    /** Neues Panel mit GridBagLayout und Dialog-Rand. */
    public static JPanel createDialogPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        setDialogBorder(panel);
        return panel;
    }

    /** Setzt den Standard-Dialogrand (ersetzt Paddings.DIALOG). */
    public static void setDialogBorder(JComponent comp) {
        comp.setBorder(BorderFactory.createEmptyBorder(DIALOG_PADDING,
            DIALOG_PADDING, DIALOG_PADDING, DIALOG_PADDING));
    }

    /**
     * Packt das Formular links oben in einen Wrapper,
     * damit rechts und unten kein Platz aufgezogen wird.
     * Ergebnis gehört in BorderLayout.NORTH des Eltern-Panels.
     */
    public static JPanel wrapNorthWest(JComponent inner) {
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(inner, BorderLayout.WEST);
        return wrapperPanel;
    }

    /** Wie {@link #wrapNorthWest}, aber das Formular nutzt die volle Breite. */
    public static JPanel wrapNorth(JComponent inner) {
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(inner, BorderLayout.NORTH);
        return wrapperPanel;
    }

    // ── Separator ──────────────────────────────────────────────────

    /** Fette Überschrift mit Linie dahinter (ersetzt appendSeparator / addSeparator). */
    public static JPanel createSeparator(String title) {
        JPanel sepPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        if (title != null && !title.isEmpty()) {
            JLabel lbl = new JLabel(title);
            lbl.setFont(lbl.getFont().deriveFont(Font.BOLD));
            sepPanel.add(lbl, gbc);
            gbc.insets = new Insets(0, 5, 0, 0);
        }
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        sepPanel.add(new JSeparator(SwingConstants.HORIZONTAL), gbc);
        return sepPanel;
    }

    /** Separator über die gesamte Breite in Zeile {@code row}. */
    public static void addSeparator(JPanel panel, int row, String title) {
        GridBagConstraints gbc = constraints(0, row, GridBagConstraints.REMAINDER);
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        if (row > 0) {
            gbc.insets = new Insets(10, 5, 3, 5);
        }
        panel.add(createSeparator(title), gbc);
    }

    // ── Label + Feld ───────────────────────────────────────────────

    /**
     * Label (rechtsbündig) + Feld in Zeile {@code row}, Paar-Spalte {@code col}.
     * @param label String oder fertige Komponente (z.B. JLabel), darf null sein
     */
    public static void addLabelField(JPanel panel, int row, int col,
        Object label, Component field) {
        addLabelField(panel, row, col, label, field, 1);
    }

    /**
     * Label + Feld, wobei das Feld über {@code fieldSpan} GridBag-Spalten
     * reicht (z.B. 3 = Feld + nächstes Label + nächstes Feld).
     * fieldSpan = GridBagConstraints.REMAINDER: Feld bis zum Zeilenende.
     */
    public static void addLabelField(JPanel panel, int row, int col,
        Object label, Component field, int fieldSpan) {
        int x = col * 2;
        if (label != null) {
            addLabel(panel, x, row, label);
        }
        addField(panel, x + 1, row, fieldSpan, field);
    }

    /** Label rechtsbündig in Zelle x/y. */
    public static void addLabel(JPanel panel, int x, int y, Object label) {
        GridBagConstraints gbc = constraints(x, y, 1);
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(toComponent(label), gbc);
    }

    /**
     * Feld in Zelle x/y. Text-, Combo- und Datumsfelder bekommen die
     * Standardbreite (außer es wurde vorher eine eigene Breite mit
     * {@link #fixWidth(Component, int)} gesetzt). Felder über mehrere
     * Spalten sowie Scrollpanes/Panels füllen die Zelle horizontal.
     */
    public static void addField(JPanel panel, int x, int y, int span,
        Component field) {
        GridBagConstraints gbc = constraints(x, y, span);
        gbc.anchor = GridBagConstraints.WEST;
        if (span != 1 || field instanceof JScrollPane
            || isPlainPanel(field)) {
            gbc.fill = GridBagConstraints.HORIZONTAL;
            if (span != 1 && !field.isPreferredSizeSet()) {
                fixWidth(field);
            }
        } else if (!field.isPreferredSizeSet()) {
            fixWidth(field);
        }
        panel.add(field, gbc);
    }

    /** Komponente über die gesamte Breite (ersetzt append(comp, colSpan)). */
    public static void addFull(JPanel panel, int row, Component comp) {
        GridBagConstraints gbc = constraints(0, row, GridBagConstraints.REMAINDER);
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(comp, gbc);
    }

    /**
     * Tabelle / Textbereich über die volle Breite mit fester Höhe
     * (kein Mitwachsen, damit nicht das ganze Fenster scrollt).
     */
    public static void addFull(JPanel panel, int row, Component comp,
        int height) {
        comp.setPreferredSize(new Dimension(
            Math.max(comp.getPreferredSize().width, 400), height));
        addFull(panel, row, comp);
    }

    /** Freie Platzierung wie früher cc.xyw(x, y, w). */
    public static void add(JPanel panel, Component comp, int x, int y,
        int width, int fill, int anchor) {
        GridBagConstraints gbc = constraints(x, y, width);
        gbc.fill = fill;
        gbc.anchor = anchor;
        if (fill == GridBagConstraints.HORIZONTAL
            || fill == GridBagConstraints.BOTH) {
            gbc.weightx = 1.0;
        }
        if (fill == GridBagConstraints.VERTICAL
            || fill == GridBagConstraints.BOTH) {
            gbc.weighty = 1.0;
        }
        panel.add(comp, gbc);
    }

    /** Freie Platzierung, links ausgerichtet, ohne Füllen. */
    public static void add(JPanel panel, Component comp, int x, int y,
        int width) {
        add(panel, comp, x, y, width, GridBagConstraints.NONE,
            GridBagConstraints.WEST);
    }

    /**
     * Komponente über mehrere Spalten und Zeilen (ersetzt cc.xywh).
     */
    public static void addSpan(JPanel panel, Component comp, int x, int y,
        int width, int height) {
        GridBagConstraints gbc = constraints(x, y, width);
        gbc.gridheight = height;
        if (comp instanceof JScrollPane || isPlainPanel(comp)) {
            // Listen, Tabellen, Unterpanels füllen den ganzen Bereich
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.NORTHWEST;
        } else {
            // Buttons o.ä. behalten ihre Höhe und werden vertikal zentriert
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.CENTER;
        }
        panel.add(comp, gbc);
    }

    /** Separator ab Spalte x über {@code width} Spalten. */
    public static void addSeparator(JPanel panel, int x, int y, int width,
        String title) {
        GridBagConstraints gbc = constraints(x, y, width);
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        if (y > 0) {
            gbc.insets = new Insets(10, 5, 3, 5);
        }
        panel.add(createSeparator(title), gbc);
    }

    /** Leere Zeile, die den restlichen vertikalen Platz schluckt. */
    public static void addFiller(JPanel panel, int row) {
        GridBagConstraints gbc = constraints(0, row, GridBagConstraints.REMAINDER);
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(new JPanel(), gbc);
    }

    // ── Buttons ────────────────────────────────────────────────────

    /** Buttonleiste rechtsbündig. */
    public static JPanel buttonBarRight(Component... buttons) {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        for (Component b : buttons) {
            bar.add(b);
        }
        return bar;
    }

    /** Buttonleiste linksbündig. */
    public static JPanel buttonBarLeft(Component... buttons) {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        for (Component b : buttons) {
            bar.add(b);
        }
        return bar;
    }

    // ── Intern ─────────────────────────────────────────────────────

    /** Frische Constraints mit Standard-Insets. */
    public static GridBagConstraints constraints(int x, int y, int width) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(INSETS.top, INSETS.left, INSETS.bottom,
            INSETS.right);
        return gbc;
    }

    /** Setzt die Standardbreite für Text-/Combo-/Datumsfelder. */
    public static void fixWidth(Component field) {
        fixWidth(field, FIELD_WIDTH);
    }

    /** Setzt eine feste Breite, Höhe bleibt die bevorzugte Höhe. */
    public static void fixWidth(Component field, int width) {
        if (isSizedField(field)) {
            Dimension pref = field.getPreferredSize();
            Dimension size = new Dimension(width, pref.height);
            field.setPreferredSize(size);
            field.setMinimumSize(size);
        }
    }

    private static boolean isSizedField(Component field) {
        return field instanceof JTextComponent
            || field instanceof JComboBox
            || field instanceof TextFieldDateChooser;
    }

    private static boolean isPlainPanel(Component field) {
        return field instanceof JPanel
            && !(field instanceof TextFieldDateChooser);
    }

    private static Component toComponent(Object label) {
        if (label instanceof Component) {
            return (Component) label;
        }
        return new JLabel(label == null ? "" : String.valueOf(label));
    }
}

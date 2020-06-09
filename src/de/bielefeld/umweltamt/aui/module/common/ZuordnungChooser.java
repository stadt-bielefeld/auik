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

package de.bielefeld.umweltamt.aui.module.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Widget containing a list of items to choose and a list of chosen items.
 * @param <T> Entry type
 */
public class ZuordnungChooser<T> extends JPanel{

    private static final long serialVersionUID = 4243444546474849L;

    private JList<T> leftList;
    private JList<T> rightList;

    private DefaultListModel<T> leftListModel;
    private DefaultListModel<T> rightListModel;

    private List<T> data;
    private List<T> leftData;
    private List<T> rightData;

    private JButton addButton;
    private JButton removeButton;

    private boolean sort;
    private Comparator<T> sortComparator;

    private String title;

    public ZuordnungChooser() {
        this(null);
    }

    public ZuordnungChooser(String title) {
        this.title = title;

        this.sort = false;

        leftData = new ArrayList<T>();
        rightData = new ArrayList<T>();
        leftListModel = new DefaultListModel<T>();
        rightListModel = new DefaultListModel<T>();
        leftList = new JList<T>(leftListModel);
        rightList = new JList<T>(rightListModel);

        addButton = new JButton("Hinzufügen >");
        removeButton = new JButton("< Entfernen");

        FormLayout chooserLayout = new FormLayout("150dlu, 5dlu, 65dlu, 5dlu, 150dlu", // Spalten
                "20dlu, 3dlu, 20dlu, 3dlu, 20dlu, 3dlu, 20dlu, 3dlu, 50dlu, 3dlu, 20dlu"); // zeilen

        @SuppressWarnings("deprecation")
        DefaultFormBuilder builder = new DefaultFormBuilder(chooserLayout, this);

        CellConstraints cc = new CellConstraints();
        if (this.title != null) {
            builder.appendSeparator(title);
        }
        builder.add(new JScrollPane(leftList), cc.xywh(1, 1, 1,9));
        builder.add(new JScrollPane(rightList), cc.xywh(5, 1, 1, 9));
        builder.add(addButton, cc.xy(3, 3));
        builder.add(removeButton, cc.xy(3, 5));
        builder.nextLine();


        addButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                applyEntries(leftList.getSelectedValuesList());
            }
        });
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeEntries(rightList.getSelectedValuesList());
            }
        });
    }

    /**
     * Set data for this widget
     * @param data New data
     */
    public void setData(List<T> data) {
        this.data = data;
        this.leftData.clear();
        this.rightData.clear();
        this.leftData.addAll(this.data);
        this.updateLists();
    }

    /**
     * Applies an element and its headers by moving it to the right table
     * @param selection Selection to apply
     */
    public void applyEntries(List<T> selection) {
        selection.forEach((item) -> {
            this.rightData.add(item);
            this.leftData.remove(item);
        });
        this.updateLists();
        leftList.clearSelection();
    }

    /**
     * Removes an element and moves it to the left tabel
     * @param selection Selection to remove
     */
    public void removeEntries(List<T> selection) {
        selection.forEach((item) -> {
            this.leftData.add(item);
            this.rightData.remove(item);
        });
        this.updateLists();
        rightList.clearSelection();
    }

    /**
     * Updates both list views using the underlying lists.
     */
    private void updateLists() {
        if (this.sort == true) {
            if (rightData.size() > 1) {
                rightData.sort(this.sortComparator);
            }
            leftData.sort(this.sortComparator);
        }

        this.leftListModel.clear();
        this.leftData.forEach(element -> this.leftListModel.addElement(element));
        this.rightListModel.clear();
        this.rightData.forEach(element -> this.rightListModel.addElement(element));
    }

    public List<T> getSelected() {
        return this.rightData;
    }

    public List<T> getUnselected() {
        return this.leftData;
    }

    public boolean getSort() {
        return this.sort;
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }

    public Comparator<T> getSortCompator() {
        return this.sortComparator;
    }

    public void setSortComparator(Comparator<T> sortComparator) {
        this.sortComparator = sortComparator;
    }

}
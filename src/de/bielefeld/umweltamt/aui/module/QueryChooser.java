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
package de.bielefeld.umweltamt.aui.module;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.bielefeld.umweltamt.aui.AbstractModul;
import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.ModulManager;
import de.bielefeld.umweltamt.aui.module.common.AbstractQueryModul;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;

public class QueryChooser extends AbstractModul {

    private static final AuikLogger log = AuikLogger.getLogger();

    private JPanel modulePanel;
    private JPanel topBar;
    private JScrollPane contentPanel;

    private JLabel queryChooserLabel;
    private JComboBox<String> queryChooserBox;
    private JButton exportButton;

    private Map<String, AbstractQueryModul> modules;

    private String outputPath = "./auswertungen/auik-export.csv";
    private String defaultModule = "E-Satzung";

    public QueryChooser() {
        modules = getModules();

        modulePanel = new JPanel();
        modulePanel.setLayout(new BoxLayout(modulePanel, BoxLayout.Y_AXIS));
        contentPanel = new JScrollPane();
        exportButton = new JButton("Exportieren");
        exportButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                JDialog dialog = createExportDialog();
                dialog.setVisible(true);
            }
        });

        queryChooserLabel = new JLabel("Auswertung:");
        String[] queries = modules.keySet().stream()
            .sorted((s1, s2) -> s1.compareTo(s2))
            .collect(Collectors.toList())
            .toArray(new String[modules.size()]);
        queryChooserBox = new JComboBox<String>(queries);
        queryChooserBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                switchModule((String) queryChooserBox.getSelectedItem());
            }
        });
        queryChooserBox.setSelectedItem(defaultModule);

        FormLayout layout = new FormLayout("pref, 3dlu, pref, 3dlu, pref, 3dlu, pref");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout);
        builder.append(queryChooserLabel);
        builder.append(queryChooserBox);
        builder.append(exportButton);
        topBar = builder.getPanel();
        topBar.setMaximumSize(new Dimension(500, 150));
        modulePanel.add(topBar);
        modulePanel.add(contentPanel);
    }

    @Override
    public String getName() {
        return "Abfragen";
    }

    @Override
    public String getCategory() {
        return "Auswertung";
    }

    @Override
    public JPanel getPanel() {
        return modulePanel;
    }

    private void switchModule(String moduleName) {
        contentPanel.setViewportView(this.modules.get(moduleName).getPanel());
    }

    @SuppressWarnings("unchecked")
    private Map<String, AbstractQueryModul> getModules() {
        List<Class<? extends AbstractQueryModul>> classes;
        Map<String, AbstractQueryModul> modules = new HashMap<String, AbstractQueryModul>();

        try (ScanResult scanResult = new ClassGraph().enableAllInfo().scan()) {
            List<Class<?>> result = scanResult.getAllClasses()
                .filter(info -> info.extendsSuperclass(AbstractQueryModul.class))
                .loadClasses();
            classes = result.stream()
                .map(clazz -> (Class<? extends AbstractQueryModul>) clazz)
                .collect(Collectors.toList());
            classes.forEach(clazz -> {
                try {
                    AbstractQueryModul instance = clazz.getDeclaredConstructor().newInstance();
                    modules.put(instance.getName(), instance);
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                    log.error("Error creating module " + clazz.toString(), e);
                }
            });
        }
        return modules;
    }

    @SuppressWarnings("deprecation" )
    private JDialog createExportDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Auswertung exportieren");
        dialog.setModal(true);
        dialog.setSize(600, 150);


        JLabel pathLabel = new JLabel("Dateipfad:");
        JTextArea path = new JTextArea(this.outputPath);
        JButton pathButton = new JButton("Pfad wählen");
        pathButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser f = new JFileChooser();
                f.setFileSelectionMode(JFileChooser.FILES_ONLY);
                f.showSaveDialog(null);
                path.setText(f.getSelectedFile().getAbsolutePath());
            }
        });

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!export(new File(path.getText()))) {
                    GUIManager.getInstance().showErrorMessage(
                        "Der Export war nicht erfolgreich",
                        "Export-Fehler");
                } else {
                    dialog.setVisible(false);
                }
            }
        });
        JButton cancelButton = new JButton("Abbrechen");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dialog.setVisible(false);
            }
        });


        FormLayout layout = new FormLayout(
            "5dlu, 100dlu, 75dlu:g, 25dlu, 5dlu, 25dlu, 3dlu, 25dlu, 5dlu",
            "5dlu, 35dlu, 3dlu:g, 35dlu, 5dlu");
        PanelBuilder builder = new PanelBuilder(layout);
        CellConstraints cc = new CellConstraints();
        builder.add(pathLabel, cc.xy(2, 2));
        builder.add(path, cc.xyw(3, 2, 2));
        builder.add(pathButton, cc.xyw(6, 2, 3));
        builder.add(cancelButton, cc.xyw(4, 4, 3));
        builder.add(okButton, cc.xy(8, 4));

        JPanel dialogContent = builder.build();
        dialog.add(dialogContent);

        return dialog;
    }

    private boolean export(File f) {
        AbstractQueryModul currentModule = modules.get(queryChooserBox.getSelectedItem());
        return AuikUtils.exportTableDataToCVS(currentModule.getResultTable(), f);
    }

    @Override
    public void setFrame(HauptFrame f) {
        this.frame = f;
        modules.forEach((key, value) -> value.setFrame(f));
    }

    @Override
    public void setManager(ModulManager m) {
        this.manager = m;
        modules.forEach((key, value) -> value.setManager(m));
    }
}

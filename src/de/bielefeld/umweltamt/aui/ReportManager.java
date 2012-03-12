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

/*
 * Datei:
 * $Id: ReportManager.java,v 1.6.2.1 2010-11-23 10:25:58 u633d Exp $
 *
 * Erstellt am 18.10.2005 von David Klotz
 *
 * CVS-Log:
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2009/12/02 06:30:17  u633d
 * Verbesserung Aufruf designs
 *
 * Revision 1.5  2009/11/23 06:53:50  u633d
 * VAwS-StandortListe
 *
 * Revision 1.3  2009/03/24 12:35:19  u633d
 * Umstellung auf UTF8
 *
 * Revision 1.2  2008/06/24 11:24:07  u633d
 * Version 0.3
 *
 * Revision 1.1  2008/06/05 11:38:40  u633d
 * Start AUIK auf Informix und Postgresql
 *
 * Revision 1.3  2006/11/08 10:10:05  u633d
 * Die Bilder von SielhautBearbeiten PDFs werden nun über die Haltungsnummer aufgeloest
 *
 * Revision 1.2  2006/09/11 06:40:50  u633d
 * Objektchronologie PDF ist erstellbar
 *
 * Revision 1.1.4.5  2006/09/06 09:49:48  u633d
 * *** empty log message ***
 *
 * Revision 1.1.4.4  2006/09/06 08:11:43  u633d
 * VAwS-Anlage hinzugekommen, allerdings noch nicht ganz fertig
 *
 * Revision 1.1.4.3  2006/09/06 06:45:07  u633d
 * Sielhautpunkte funktionieren als PDF
 * VAwS-Listen auch
 * für VAwS-Anlagen muss ich nur noch das Java schreiben
 *
 * Revision 1.1.4.2  2006/08/29 08:27:54  u633d
 * Der Kataster unterstützt jetzt bei den SielhautBearbeiten Punkten den Aufruf eines Reports ohne HashMap,
 * allerdings muss noch die unterstützung für die VaWS Anlagen und Reports für die Objektchronologie erstellt werden!
 *
 * Revision 1.1.4.1  2006/08/16 10:06:48  u633d
 * neues sielhaut rptdesign
 *
 * Revision 1.1  2005/11/02 13:45:44  u633d
 * - Version vom 2.11.
 *
 *
 */
package de.bielefeld.umweltamt.aui;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;

import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;

/**
 * Eine Klasse um die Erzeugung von PDF-Reports mit BIRT zu steuern. Diese
 * Klasse ist ein Singleton, d.h. von ihr kann maximal eine einzige Instanz pro
 * Programm erzeugt werden.
 * @author Colin Atkins based on David Klotz' ReportManager
 */
public class ReportManager {
    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();
    private static ReportManager _instance;
    private EngineConfig config = null;
    private IReportEngine engine = null;
    private HTMLRenderOption options = null;
    private String engineHome;
    private String reportHome;
    private String fotoPath;
    private String mapPath;

    private ReportManager(String engineHome, String reportHome,
        String fotoPath, String mapPath) {
        this.engineHome = engineHome;
        this.reportHome = reportHome;
        this.fotoPath = fotoPath;
        this.mapPath = mapPath;
        initBirt();
    }

    private File runReport(String name) {
        if (name == null) {
            log.debug("DEBUG::runReport Error: Id Name oder HaltungsNr nicht gesetzt!\n");
            log.debug("\nName: " + name);
        }

        // Init Birt
        initBirt();

        // Get a file
        File pdfFile = this.getPDFFile(name);

        // Create task to run and render the report,
        IReportRunnable design = null;
        try {
            design = engine.openReportDesign(reportHome + name + ".rptdesign");
        } catch (EngineException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        IRunAndRenderTask task = engine.createRunAndRenderTask(design);

        return this.runReport(pdfFile, task);
    }

    private File runReport(String name, Integer id, String bezeichnung) {
        if (id == null || name == null || bezeichnung == null) {
            log.debug("DEBUG::runReport Error: Id Name oder HaltungsNr nicht gesetzt!\n");
            log.debug("Id: " + id);
            log.debug("\nName: " + name);
            log.debug("\nHaltungsNr: " + bezeichnung);
        }

        // Init Birt
        initBirt();

        // Get a file
        File pdfFile = this.getPDFFile(name + id);

        // Create task to run and render the report,
        IReportRunnable design = null;
        try {
            design = engine.openReportDesign(reportHome + name + ".rptdesign");
        } catch (EngineException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        IRunAndRenderTask task = engine.createRunAndRenderTask(design);

        // Setup the task
        task.setParameterValue("id", id);
        if (bezeichnung != null
            && new File(fotoPath + bezeichnung + ".jpg").canRead()) {
            task.setParameterValue("foto", new String(fotoPath + bezeichnung
                + ".jpg"));
        } else {
            task.setParameterValue("foto", new String(fotoPath
                + "kein_foto.jpg"));
        }

        if (bezeichnung != null
            && new File(mapPath + bezeichnung + ".jpg").canRead()) {
            task.setParameterValue("karte", new String(mapPath + bezeichnung
                + ".jpg"));
        } else {
            task.setParameterValue("karte", new String(mapPath
                + "keine_karte.jpg"));
        }

        return this.runReport(pdfFile, task);
    }

    private File runReport(Integer standortId, String standort,
        String name) {

        // Init Birt
        initBirt();

        // Get a file
        File pdfFile = this.getPDFFile(name + standortId);

        // Create task to run and render the report,
        IReportRunnable design = null;
        try {
            design = engine.openReportDesign(reportHome + name + ".rptdesign");
        } catch (EngineException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        IRunAndRenderTask task = engine.createRunAndRenderTask(design);

        // Setup the task
        task.setParameterValue("StandortID", standortId);
        task.setParameterValue("Standort", standort);

        return this.runReport(pdfFile, task);
    }

    private File runReport(String name, Integer objektId,
        String betreiber, String standort, String art) {

        // Init Birt
        initBirt();

        // Get a file
        File pdfFile = this.getPDFFile(name + objektId);

        // Create task to run and render the report,
        IReportRunnable design = null;
        try {
            design = engine.openReportDesign(reportHome + name + ".rptdesign");
        } catch (EngineException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        IRunAndRenderTask task = engine.createRunAndRenderTask(design);

        // Setup the task
        task.setParameterValue("ObjektId", objektId);
        task.setParameterValue("Betreiber", betreiber);
        task.setParameterValue("Standort", standort);
        task.setParameterValue("Art", art);

        return this.runReport(pdfFile, task);
    }

    private File runReport(String art, String name,
        Integer behaelterId, String betreiber, String standort) {

        // Init Birt
        initBirt();

        // Get a file
        File pdfFile = this.getPDFFile(name + behaelterId);

        // Create task to run and render the report,
        IReportRunnable design = null;
        try {
            design = engine.openReportDesign(reportHome + name + ".rptdesign");
        } catch (EngineException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        IRunAndRenderTask task = engine.createRunAndRenderTask(design);

        // Setup the task
        task.setParameterValue("BehaelterId", behaelterId);
        task.setParameterValue("Betreiber", betreiber);
        task.setParameterValue("Standort", standort);
        task.setParameterValue("Objektart", art);

        return this.runReport(pdfFile, task);
    }

    /**
     * Helper method for the different runReport methods
     * @param pdfFile
     * @param task
     * @return File
     */
    private File runReport(File pdfFile, IRunAndRenderTask task) {

        task.validateParameters();

        HTMLRenderOption options = new HTMLRenderOption();

        // Remove HTML and Body tags
        options.setEmbeddable(true);

        // Set ouptut location
        options.setOutputFileName(pdfFile.getAbsolutePath());

        // Set output format
        options.setOutputFormat("pdf");
        task.setRenderOption(options);

        // run the report and destroy the engine
        // Note - If the program stays resident do not shutdown the Platform or
        // the Engine
        // Den Report endgültig erzeugen
        try {
            task.run();

            shutdownBirt();
        } catch (EngineException e1) {
            throw new RuntimeException(
                "Fehler beim Durchführen des BIRT-Reports!", e1);
        }

        return pdfFile;
    }

    /**
     * Create a tempory file
     * @param name
     * @return File
     */
    private File getPDFFile(String name) {
        File pdfFile = null;
        if (name == null) {
            log.debug("Kein Dateiname angegeben.");
            name = "BirtReport";
        }
        try {
            pdfFile = File.createTempFile(name, ".pdf");
        } catch (IOException e) {
            throw new RuntimeException(
                "Konnte temporäre PDF-Datei nicht speichern!", e);
        }
        pdfFile.deleteOnExit();
        return pdfFile;
    }

    private void initBirt() {
        if (config != null && engine != null && options != null) {
            return;
        }

        config = null;
        engine = null;
        try {
            // Configure the Engine and start the Platform
            config = new EngineConfig();
            config.setEngineHome(engineHome);
            config.setLogConfig(null, Level.OFF);
        } catch (Exception ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }

        try {
            Platform.startup(config);
        } catch (BirtException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        IReportEngineFactory factory = (IReportEngineFactory) Platform
            .createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
        engine = factory.createReportEngine(config);

        engine.changeLogLevel(Level.OFF);
    }

    public void startReportWorker(final String name, Component focusComp) {
        SwingWorkerVariant worker = new SwingWorkerVariant(focusComp) {
            File pdfFile;

            @Override
            protected void doNonUILogic() throws RuntimeException {
                pdfFile = runReport(name);
            }

            @Override
            protected void doUIUpdateLogic() throws RuntimeException {
                AuikUtils.spawnFileProg(pdfFile);
            }
        };

        worker.start();
    }

    public void startReportWorker(final String name, final Integer id,
        final String haltungsNr, Component focusComp) {
        SwingWorkerVariant worker = new SwingWorkerVariant(focusComp) {
            File pdfFile;

            @Override
            protected void doNonUILogic() throws RuntimeException {
                pdfFile = runReport(name, id, haltungsNr);
            }

            @Override
            protected void doUIUpdateLogic() throws RuntimeException {
                AuikUtils.spawnFileProg(pdfFile);
            }
        };

        worker.start();
//        prepareReport(Name, Id, EntGeb);
    }

    public void startReportWorker(final String name, final Integer objektId,
        final String betreiber, final String standort, final String art,
        Component focusComp) {
        SwingWorkerVariant worker = new SwingWorkerVariant(focusComp) {
            File pdfFile;

            @Override
            protected void doNonUILogic() throws RuntimeException {
                pdfFile = runReport(name, objektId, betreiber, standort, art);
            }

            @Override
            protected void doUIUpdateLogic() throws RuntimeException {
                AuikUtils.spawnFileProg(pdfFile);
            }
        };

        worker.start();
    }

    public void startReportWorker(final String name, final String standort,
        final Integer standortId, Component focusComp) {
        SwingWorkerVariant worker = new SwingWorkerVariant(focusComp) {
            File pdfFile;

            @Override
            protected void doNonUILogic() throws RuntimeException {
                pdfFile = runReport(standortId, standort, name);
            }

            @Override
            protected void doUIUpdateLogic() throws RuntimeException {
                AuikUtils.spawnFileProg(pdfFile);
            }
        };

        worker.start();
    }

    public void startReportWorker(final String name, final Integer behaelterId,
        final String betreiber, final String standort, Component focusComp,
        final String art) {
        SwingWorkerVariant worker = new SwingWorkerVariant(focusComp) {
            File pdfFile;

            @Override
            protected void doNonUILogic() throws RuntimeException {
                pdfFile = runReport(
                		art, name, behaelterId, betreiber, standort);
            }

            @Override
            protected void doUIUpdateLogic() throws RuntimeException {
                AuikUtils.spawnFileProg(pdfFile);
            }
        };

        worker.start();
    }

    public static synchronized ReportManager getInstance() {
        if (_instance == null) {
            SettingsManager sm = SettingsManager.getInstance();
            String engineHome = sm.getSetting("auik.birt.enginepath");
            String reportHome = sm.getSetting("auik.birt.reportpath");
            String fotoPath = sm.getSetting("auik.system.spath_fotos");
            String mapPath = sm.getSetting("auik.system.spath_karten");

            _instance = new ReportManager(
                engineHome, reportHome, fotoPath, mapPath);
        }
        return _instance;
    }

    public void shutdownBirt() {
        // Wenn die Engine noch existiert, wird sie geschlossen.
        if (engine != null) {
            engine.destroy();
            log.debug("Engine zerstört!");
        }

        // Alle Referenzen auf null, damit der GC seine Arbeit machen kann.
        engine = null;
        config = null;
        options = null;

        // Den Garbage Collector laufen lassen, damit Acrobat
        // auf das PDF zugreifen darf *grrr*
        Runtime.getRuntime().gc();
    }

    @Override
    protected void finalize() {
        shutdownBirt();
    }
}

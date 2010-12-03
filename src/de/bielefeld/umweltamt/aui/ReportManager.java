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
import org.eclipse.birt.report.engine.api.ReportEngine;

import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;


/**
 * Eine Klasse um die Erzeugung von PDF-Reports mit BIRT zu steuern.
 * Diese Klasse ist ein Singleton, d.h. von ihr kann maximal eine einzige
 * Instanz pro Programm erzeugt werden.
 * @author Colin Atkins
 * based on David Klotz' ReportManager
 */
public class ReportManager {
    private static ReportManager _instance;
    private EngineConfig config;
    private IReportEngine engine;
    private HTMLRenderOption options;
    private String engineHome;
    private String reportHome;
    private String fotoPath;
    private String mapPath;


    private ReportManager(String engineHome, String reportHome, String fotoPath, String mapPath) {
        this.engineHome = engineHome;
        this.reportHome = reportHome;
        this.fotoPath   = fotoPath;
        this.mapPath    = mapPath;
//        this.Name       = Name;
//        this.Id         = Id;
//        if (EntGeb != null)
//            this.EntGeb = EntGeb;
        initBirt();
    }

    public File runReport(String Name) throws EngineException {
        File pdfFile;
        try {
            pdfFile = File.createTempFile(Name, ".pdf");
        } catch (IOException e) {
            throw new RuntimeException("Konnte temporäre PDF-Datei nicht speichern!", e);
        }
        pdfFile.deleteOnExit();

        runReport(pdfFile, Name);

        return pdfFile;
    }

    public File runReport(String Name, Integer Id, String Bezeichnung) throws EngineException {
        File pdfFile;
        try {
            pdfFile = File.createTempFile(Name + Id, ".pdf");
        } catch (IOException e) {
            throw new RuntimeException("Konnte temporäre PDF-Datei nicht speichern!", e);
        }
        pdfFile.deleteOnExit();

        runReport(pdfFile, Name, Id, Bezeichnung);

        return pdfFile;
    }

    public File runReport(String Name, Integer ObjektId, String Betreiber, String Standort, String Art) throws EngineException {
        File pdfFile;
        try {
            pdfFile = File.createTempFile(Name + ObjektId, ".pdf");
        } catch (IOException e) {
            throw new RuntimeException("Konnte temporäre PDF-Datei nicht speichern!", e);
        }
        pdfFile.deleteOnExit();

        runReport(pdfFile, Name, ObjektId, Betreiber, Standort, Art);

        return pdfFile;
    }

    public File runReport(String Name,String art, Integer BehaelterId, String Betreiber, String Standort) throws EngineException {
        File pdfFile;
        try {
            pdfFile = File.createTempFile(Name + BehaelterId, ".pdf");
        } catch (IOException e) {
            throw new RuntimeException("Konnte temporäre PDF-Datei nicht speichern!", e);
        }
        pdfFile.deleteOnExit();

        runReport(pdfFile, art, Name, BehaelterId, Betreiber, Standort);

        return pdfFile;
    }





    public File runReport( Integer StandortId, String Standort, String Name) throws EngineException {
        File pdfFile;
        try {

            pdfFile = File.createTempFile(Name + StandortId, ".pdf");

        } catch (IOException e) {
            throw new RuntimeException("Konnte temporäre PDF-Datei nicht speichern!", e);
        }
        pdfFile.deleteOnExit();

        runReport(pdfFile, StandortId, Standort,Name);

        return pdfFile;
    }




    protected void initBirt()
    {
        config = null;
        engine = null;
        try {

            //Configure the Engine and start the Platform
            config = new EngineConfig();
            config.setEngineHome(engineHome);
            config.setLogConfig(null, Level.OFF);


        } catch( Exception ex) {
            ex.printStackTrace();
        }

        try {
            Platform.startup( config );
        } catch (BirtException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();


        }
            IReportEngineFactory factory = (IReportEngineFactory) Platform
            .createFactoryObject( IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY );
        engine = factory.createReportEngine( config );



        engine.changeLogLevel( Level.OFF );
    }

    public void startReportWorker(final String Name, Component focusComp) throws EngineException {
        SwingWorkerVariant worker = new SwingWorkerVariant(focusComp) {
            File pdfFile;
            protected void doNonUILogic() throws RuntimeException {
                //File report = new File(reportHome + reportname + ".rptdesign");
                try {
                    pdfFile = runReport(Name);
                } catch (EngineException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            protected void doUIUpdateLogic() throws RuntimeException {
                AuikUtils.spawnFileProg(pdfFile);
            }
        };

        worker.start();
    }

    public void startReportWorker(final String Name, final Integer Id, final String HaltungsNr, Component focusComp) throws EngineException {
        SwingWorkerVariant worker = new SwingWorkerVariant(focusComp) {
            File pdfFile;
            protected void doNonUILogic() throws RuntimeException {
                //File report = new File(reportHome + reportname + ".rptdesign");
                try {
                    pdfFile = runReport(Name, Id, HaltungsNr);
                } catch (EngineException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            protected void doUIUpdateLogic() throws RuntimeException {
                AuikUtils.spawnFileProg(pdfFile);
            }
        };

        worker.start();
//        try {
//            prepareReport(Name, Id, EntGeb);
//        } catch (EngineException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }

    public void startReportWorker(final String Name, final Integer ObjektId, final String Betreiber, final String Standort, final String Art, Component focusComp) {
        SwingWorkerVariant worker = new SwingWorkerVariant(focusComp) {
            File pdfFile;
            protected void doNonUILogic() throws RuntimeException {
                //File report = new File(reportHome + reportname + ".rptdesign");
                try {
                    pdfFile = runReport(Name, ObjektId, Betreiber, Standort, Art);
                } catch (EngineException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            protected void doUIUpdateLogic() throws RuntimeException {
                AuikUtils.spawnFileProg(pdfFile);
            }
        };

        worker.start();
    }



    public void startReportWorker(final String Name, final String Standort, final Integer StandortId,  Component focusComp ) {
        SwingWorkerVariant worker = new SwingWorkerVariant(focusComp) {
            File pdfFile;
            protected void doNonUILogic() throws RuntimeException {
                //File report = new File(reportHome + reportname + ".rptdesign");
                try {

                    pdfFile = runReport (StandortId, Standort,Name);
                } catch (EngineException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            protected void doUIUpdateLogic() throws RuntimeException {
                AuikUtils.spawnFileProg(pdfFile);
            }
        };

        worker.start();
    }







    public void startReportWorker(final String Name, final Integer BehaelterId, final String Betreiber, final String Standort, Component focusComp, final String art) {
        SwingWorkerVariant worker = new SwingWorkerVariant(focusComp) {
            File pdfFile;
            protected void doNonUILogic() throws RuntimeException {
                //File report = new File(reportHome + reportname + ".rptdesign");
                try {
                    pdfFile = runReport(Name, art, BehaelterId, Betreiber, Standort);
                } catch (EngineException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            protected void doUIUpdateLogic() throws RuntimeException {
                AuikUtils.spawnFileProg(pdfFile);
            }
        };

        worker.start();
    }

    public void runReport(File pdffile, String Name) throws EngineException {
        if (Name == null)
        {
            System.out.println("DEBUG::runReport Error: Id Name oder HaltungsNr nicht gesetzt!\n");
            System.out.println("\nName: " + Name);
        }


        if (config == null || engine == null || options == null)
            initBirt();


        IReportRunnable design = null;
        design = engine.openReportDesign(reportHome + Name + ".rptdesign"); //reportHome + Name + ".rptdesign"

        //Create task to run and render the report,
        IRunAndRenderTask task = engine.createRunAndRenderTask(design);

        task.validateParameters();
        options = new HTMLRenderOption();

        //Remove HTML and Body tags
        options.setEmbeddable(true);

        //Set ouptut location
        options.setOutputFileName(pdffile.getAbsolutePath());

        //Set output format
        options.setOutputFormat("pdf");
        task.setRenderOption(options);

        //run the report and destroy the engine
        //Note - If the program stays resident do not shutdown the Platform or the Engine
        //Den Report endgültig erzeugen
        try {
            task.run();

            shutdownBirt();
        } catch (EngineException e1) {
            throw new RuntimeException("Fehler beim Durchführen des BIRT-Reports!", e1);
        }
    }

    public void runReport(File pdffile, String Name, Integer Id, String Bezeichnung) throws EngineException {
        if (Id == null || Name == null || Bezeichnung == null)
        {
            System.out.println("DEBUG::runReport Error: Id Name oder HaltungsNr nicht gesetzt!\n");
            System.out.println("Id: " + Id);
            System.out.println("\nName: " + Name);
            System.out.println("\nHaltungsNr: " + Bezeichnung);
        }


        if (config == null || engine == null || options == null)
            initBirt();


        IReportRunnable design = null;
        design = engine.openReportDesign(reportHome + Name + ".rptdesign"); //reportHome + Name + ".rptdesign"

        //Create task to run and render the report,
        IRunAndRenderTask task = engine.createRunAndRenderTask(design);

        task.setParameterValue("id", Id);
        if (Bezeichnung != null && new File(fotoPath + Bezeichnung + ".jpg").canRead()) {
            task.setParameterValue("foto", new String(fotoPath + Bezeichnung + ".jpg"));
        }
        else
        {
            task.setParameterValue("foto", new String(fotoPath + "kein_foto.jpg"));
        }

        if (Bezeichnung != null && new File(mapPath + Bezeichnung + ".jpg").canRead()) {
            task.setParameterValue("karte", new String(mapPath + Bezeichnung + ".jpg"));
        }
        else
        {
            task.setParameterValue("karte", new String(mapPath + "keine_karte.jpg"));
        }
        task.validateParameters();
        options = new HTMLRenderOption();

        //Remove HTML and Body tags
        options.setEmbeddable(true);

        //Set ouptut location
        options.setOutputFileName(pdffile.getAbsolutePath());

        //Set output format
        options.setOutputFormat("pdf");
        task.setRenderOption(options);

        //run the report and destroy the engine
        //Note - If the program stays resident do not shutdown the Platform or the Engine
        //Den Report endgültig erzeugen
        try {
            task.run();

            shutdownBirt();
        } catch (EngineException e1) {
            throw new RuntimeException("Fehler beim Durchführen des BIRT-Reports!", e1);
        }
    }







    public void runReport(File pdffile, Integer StandortId, String Standort, String Name)  throws EngineException {


        if (config == null || engine == null || options == null)
            initBirt();

        IReportRunnable design = null;


        try{
            design = engine.openReportDesign(reportHome + Name + ".rptdesign"); //reportHome + Name + ".rptdesign"

        }
        catch (EngineException e1) {
                AUIKataster
                .debugOutput("Fehler: " +  e1);
        }


        //Create task to run and render the report,
        IRunAndRenderTask task = engine.createRunAndRenderTask(design);

        task.setParameterValue("StandortID", StandortId);
        task.setParameterValue("Standort", Standort);
        task.validateParameters();
        HTMLRenderOption options = new HTMLRenderOption();

        //Remove HTML and Body tags
        options.setEmbeddable(true);

        //Set ouptut location
        options.setOutputFileName(pdffile.getAbsolutePath());


        //Set output format
        options.setOutputFormat("pdf");
        task.setRenderOption(options);


        try {
            task.run();

            shutdownBirt();
        } catch (EngineException e1) {
            throw new RuntimeException("Fehler beim Durchführen des BIRT-Reports!", e1);
        }
    }













    public void runReport(File pdffile, String Name, Integer ObjektId, String Betreiber, String Standort, String Art) throws EngineException {
//        EngineConfig config = null;
//        IReportEngine engine = null;
//        try {
//
//            //Configure the Engine and start the Platform
//            config = new EngineConfig();
//            config.setEngineHome(engineHome);
//            config.setLogConfig(null, Level.FINE);
//
//        } catch( Exception ex) {
//            ex.printStackTrace();
//        }
//
//        try {
//            Platform.startup( config );
//        } catch (BirtException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        IReportEngineFactory factory = (IReportEngineFactory) Platform
//            .createFactoryObject( IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY );
//        engine = factory.createReportEngine( config );
//        engine.changeLogLevel( Level.WARNING );

        if (config == null || engine == null || options == null)
            initBirt();

        IReportRunnable design = null;



        design = engine.openReportDesign(reportHome + Name + ".rptdesign"); //reportHome + Name + ".rptdesign"




        //Create task to run and render the report,
        IRunAndRenderTask task = engine.createRunAndRenderTask(design);

        task.setParameterValue("ObjektId", ObjektId);
        task.setParameterValue("Betreiber", Betreiber);
        task.setParameterValue("Standort", Standort);
        task.setParameterValue("Art", Art);
        task.validateParameters();
        HTMLRenderOption options = new HTMLRenderOption();

        //Remove HTML and Body tags
        options.setEmbeddable(true);

        //Set ouptut location
        options.setOutputFileName(pdffile.getAbsolutePath());

        //Set output format
        options.setOutputFormat("pdf");
        task.setRenderOption(options);

        //run the report and destroy the engine
        //Note - If the program stays resident do not shutdown the Platform or the Engine
//        task.run();
//        task.close();
//        engine.shutdown();
//        engine.destroy();
//        Platform.shutdown();
//        engine = null;
//        config = null;
//        options = null;
//        Runtime.getRuntime().gc();
        //run the report and destroy the engine
        //Note - If the program stays resident do not shutdown the Platform or the Engine
        //Den Report endgültig erzeugen
        try {
            task.run();

            shutdownBirt();
        } catch (EngineException e1) {
            throw new RuntimeException("Fehler beim Durchführen des BIRT-Reports!", e1);
        }
    }

    public void runReport(File pdffile, String art, String Name, Integer BehaelterId, String Betreiber, String Standort) throws EngineException {
//        EngineConfig config = null;
//        IReportEngine engine = null;
//        try {
//
//            //Configure the Engine and start the Platform
//            config = new EngineConfig();
//            config.setEngineHome(engineHome);
//            config.setLogConfig(null, Level.FINE);
//
//        } catch( Exception ex) {
//            ex.printStackTrace();
//        }
//
//        try {
//            Platform.startup( config );
//        } catch (BirtException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        IReportEngineFactory factory = (IReportEngineFactory) Platform
//            .createFactoryObject( IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY );
//        engine = factory.createReportEngine( config );
//        engine.changeLogLevel( Level.WARNING );

        if (config == null || engine == null || options == null)
            initBirt();

        IReportRunnable design = null;
        design = engine.openReportDesign(reportHome + Name + ".rptdesign"); //reportHome + Name + ".rptdesign"

        //Create task to run and render the report,
        IRunAndRenderTask task = engine.createRunAndRenderTask(design);

        task.setParameterValue("BehaelterId", BehaelterId);
        task.setParameterValue("Betreiber", Betreiber);
        task.setParameterValue("Standort", Standort);
        task.setParameterValue("Objektart",art);
        task.validateParameters();
        HTMLRenderOption options = new HTMLRenderOption();

        //Remove HTML and Body tags
        options.setEmbeddable(true);

        //Set ouptut location
        options.setOutputFileName(pdffile.getAbsolutePath());

        //Set output format
        options.setOutputFormat("pdf");
        task.setRenderOption(options);

        //run the report and destroy the engine
        //Note - If the program stays resident do not shutdown the Platform or the Engine
//        task.run();
//        task.close();
//        engine.shutdown();
//        engine.destroy();
//        Platform.shutdown();
//        engine = null;
//        config = null;
//        options = null;
//        Runtime.getRuntime().gc();
        //run the report and destroy the engine
        //Note - If the program stays resident do not shutdown the Platform or the Engine
        //Den Report endgültig erzeugen
        try {
            task.run();

            shutdownBirt();
        } catch (EngineException e1) {
            throw new RuntimeException("Fehler beim Durchführen des BIRT-Reports!", e1);
        }
    }

    public static synchronized ReportManager getInstance() {
        if (_instance == null) {
            String engineHome = SettingsManager.getInstance().getSetting("auik.birt.enginepath");
            String reportHome = SettingsManager.getInstance().getSetting("auik.birt.reportpath");
            String fotoPath   = SettingsManager.getInstance().getSetting("auik.system.spath_fotos");
            String mapPath    = SettingsManager.getInstance().getSetting("auik.system.spath_karten");

            _instance = new ReportManager(engineHome, reportHome, fotoPath, mapPath);
        }
        return _instance;
    }

//    public void startReportWorker(final String Name, final Integer Id, final String EntGeb) {
//    }

//    public void startReportWorker(final String Name, final Integer Id) {
//    }

//    public void startReportWorker(final String reportname, final Integer id, final String fotopath, final String mappath, Component focusComp, boolean withoutparams, final String entgeb) {
//        withoutparams = true;
//        SwingWorkerVariant worker = new SwingWorkerVariant(focusComp) {
//            File pdfFile;
//            protected void doNonUILogic() throws RuntimeException {
//                File report = new File(reportHome + reportname + ".rptdesign");
//            }
//
//            protected void doUIUpdateLogic() throws RuntimeException {
//                AuikUtils.spawnFileProg(pdfFile);
//            }
//        };
//
//        worker.start();
//    }

    public void shutdownBirt() {
        // Wenn die Engine noch existiert, wird sie geschlossen.
        if (engine != null) {
//            AUIKataster.debugOutput("Engine zerstören:");
            engine.destroy();
            AUIKataster.debugOutput("Engine zerstört!");
        }

        // Alle Referenzen auf null, damit der GC seine Arbeit machen kann.
        engine = null;
        config = null;
        options = null;

        // Den Garbage Collector laufen lassen, damit Acrobat
        // auf das PDF zugreifen darf *grrr*
        Runtime.getRuntime().gc();
    }

    protected void finalize() {
        shutdownBirt();
    }
}

/*
Copyright (c) 2005-2007, Robert F. Beeger & Arno Haase & Stefan Roock & Sebastian Sanitz
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

    * Redistributions of source code must retain the above copyright notice,
      this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright notice,
      this list of conditions and the following disclaimer in the documentation and/or
      other materials provided with the distribution.
    * Neither the name of the the authors nor the names of its contributors may be
      used to endorse or promote products derived from this software without specific
      prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.
 */
package de.bielefeld.umweltamt.aui;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.Timer;
import javax.swing.UIManager;

import de.bielefeld.umweltamt.aui.AUIKSplash;
import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.ModulManager;
import de.bielefeld.umweltamt.aui.SettingsManager;
import de.bielefeld.umweltamt.aui.SplashWindow;
import de.bielefeld.umweltamt.aui.mappings.basis.*;
import de.bielefeld.umweltamt.aui.mappings.indeinl.*;
import de.bielefeld.umweltamt.aui.mappings.atl.*;
import de.bielefeld.umweltamt.aui.mappings.vaws.*;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.jgoodies.plaf.FontSizeHints;
import com.jgoodies.plaf.Options;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;

import org.hibernate.HibernateException;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.plaf.FontSizeHints;
import com.jgoodies.plaf.HeaderStyle;
import com.jgoodies.plaf.Options;
import com.jgoodies.uif_lite.component.Factory;
import com.jgoodies.uif_lite.panel.SimpleInternalFrame;

import de.bielefeld.umweltamt.aui.mappings.atl.AtlProbepkt;
import de.bielefeld.umweltamt.aui.mappings.atl.AtlSielhaut;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;
import de.bielefeld.umweltamt.aui.utils.GradientPanel;
import de.bielefeld.umweltamt.aui.utils.SwingWorkerVariant;

public class GUITest extends TestCase {
    /**
     * Starten einer SessionFactory
     */
    protected void setUp() throws Exception {
        super.setUp();
        Configuration configuration =
                new Configuration().configure();
 
      
        
	   _sessionFactory =
                configuration.buildSessionFactory();

      
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        _sessionFactory.close();
    }
   

    /**
     * Testen die Funktionen des Modul Managers
     */
	private ModulManager manager;
   public void testModulManager() {
        Session session = null;
        try {
            session = _sessionFactory.openSession();

            //Aufruf des Login-Fensters
            SettingsManager settings = SettingsManager.getInstance();
    		runningFrame = new HauptFrame(settings);
    		manager = new ModulManager(runningFrame, settings);
			// ... füge Module hinzu
			manager.loadModule();
			// Zeige dieses Fenster an
			runningFrame.setVisible(true);
						
			//modul wechslen
			manager.switchModul("m_sielhaut1");
			Modul modul = manager.getCurrentModul();
			// wurde das Modul gewechselt?
			assertEquals("m_sielhaut1",modul.getIdentifier());
			// ist es der richtige Kategorie zugeordnet?
			assertEquals("Sielhaut", modul.getCategory());
			
			manager.switchModul("m_auswertung_anh40");
			modul = manager.getCurrentModul();
			assertEquals("m_auswertung_anh40", modul.getIdentifier());
			assertEquals("Auswertung", modul.getCategory());
			 
			manager.switchModul("m_auswertung_anh49");
			modul = manager.getCurrentModul();
			assertEquals("m_auswertung_anh49", modul.getIdentifier());
			assertEquals("Auswertung", modul.getCategory());
			
			manager.switchModul("m_auswertung_anh52");
			modul = manager.getCurrentModul();
			assertEquals("m_auswertung_anh52", modul.getIdentifier());
			assertEquals("Auswertung", modul.getCategory());
			
			manager.switchModul("m_auswertung_anh53");
			modul = manager.getCurrentModul();
			assertEquals("m_auswertung_anh53", modul.getIdentifier());
			assertEquals("Auswertung", modul.getCategory());
			
			manager.switchModul("m_auswertung_anh55");
			modul = manager.getCurrentModul();
			assertEquals("m_auswertung_anh55", modul.getIdentifier());
			assertEquals("Auswertung", modul.getCategory());
			
			manager.switchModul("m_auswertung_anh56");
			modul = manager.getCurrentModul();
			assertEquals("m_auswertung_anh56", modul.getIdentifier());
			assertEquals("Auswertung", modul.getCategory());
			
			manager.switchModul("m_schlaemme_faul");
			modul = manager.getCurrentModul();
			assertEquals("m_schlaemme_faul", modul.getIdentifier());
			assertEquals("Analysen", modul.getCategory());
			
			manager.switchModul("m_schlaemme_roh");
			modul = manager.getCurrentModul();
			assertEquals("m_schlaemme_roh", modul.getIdentifier());
			assertEquals("Analysen", modul.getCategory());
			
			manager.switchModul("m_betreiber_neu");
			modul = manager.getCurrentModul();
			assertEquals("m_betreiber_neu", modul.getIdentifier());
			assertEquals("Betreiber", modul.getCategory());
			
			manager.switchModul("m_betreiber_suchen");
			modul = manager.getCurrentModul();
			assertEquals("m_betreiber_suchen", modul.getIdentifier());
			assertEquals("Betreiber", modul.getCategory());
			
			manager.switchModul("m_fettabscheider_auswertung");
			modul = manager.getCurrentModul();
			assertEquals("m_fettabscheider_auswertung", modul.getIdentifier());
			assertEquals("Auswertung", modul.getCategory());
			
			manager.switchModul("m_auswertung_genehmigung");
			modul = manager.getCurrentModul();
			assertEquals("m_auswertung_genehmigung", modul.getIdentifier());
			assertEquals("Auswertung", modul.getCategory());
			
			manager.switchModul("atl_icp_import");
			modul = manager.getCurrentModul();
			assertEquals("atl_icp_import", modul.getIdentifier());
			assertEquals("Analysen", modul.getCategory());
			
			manager.switchModul("m_schlaemme_auswertung");
			modul = manager.getCurrentModul();
			assertEquals("m_schlaemme_auswertung",modul.getIdentifier());
			assertEquals("Analysen", modul.getCategory());
			
			manager.switchModul("m_objekt_bearbeiten");
			modul = manager.getCurrentModul();
			assertEquals("m_objekt_bearbeiten",modul.getIdentifier());
			assertEquals("Objekte", modul.getCategory());
						
			manager.switchModul("m_probe_suchen");
			modul = manager.getCurrentModul();
			assertEquals("m_probe_suchen", modul.getIdentifier());
			assertEquals("Analysen", modul.getCategory());
			
			manager.switchModul("m_sielhaut_import");
			modul = manager.getCurrentModul();
			assertEquals("m_sielhaut_import", modul.getIdentifier());
			assertEquals("Sielhaut",modul.getCategory());
			
			manager.switchModul("m_standort_neu");
			modul = manager.getCurrentModul();
			assertEquals("m_standort_neu", modul.getIdentifier());
			assertEquals("Standorte",modul.getCategory());
			
			manager.switchModul("m_standort_suchen");
			modul = manager.getCurrentModul();
			assertEquals("m_standort_suchen", modul.getIdentifier());
			assertEquals("Standorte", modul.getCategory());
			
			manager.switchModul("m_auswertung_uebergabe");
			modul = manager.getCurrentModul();
			assertEquals("m_auswertung_uebergabe",modul.getIdentifier());
			assertEquals("Auswertung",modul.getCategory());
			
			manager.back();
			modul = manager.getCurrentModul();
			assertEquals("m_standort_suchen", modul.getIdentifier());
			assertEquals("Standorte", modul.getCategory());
			
			manager.forward();
			modul = manager.getCurrentModul();
			assertEquals("m_auswertung_uebergabe",modul.getIdentifier());
			assertEquals("Auswertung",modul.getCategory());

						
        }
        finally {
            if (session != null &&
                    session.isConnected()) {
                session.close();
            }
        }
    }
   private static HauptFrame runningFrame = null;
   private SessionFactory _sessionFactory;

}

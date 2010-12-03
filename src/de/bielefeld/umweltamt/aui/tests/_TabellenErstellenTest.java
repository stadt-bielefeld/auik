package de.bielefeld.umweltamt.aui.tests;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import de.bielefeld.umweltamt.aui.mappings.basis.BasisBetreiber;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjektarten;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisStandort;

public class _TabellenErstellenTest extends TestCase {

    private static final String Strasse = "Teststraße";
    private static final int Hausnr = 10;
    private int _idS;
    private static final String Name = "Testbetreiber";
    private static final String Handz = "Junit";
    private int _idB;
    private static final int ObjID = 1;
    private static final String ObjArt = "Testart";
    private int _idO;

    protected void setUp() throws Exception {
        super.setUp();

        Configuration configuration = new Configuration().configure();
        SchemaExport export = new SchemaExport(configuration);
        export.create(false, true);
        testErzeugeDaten();
        tearDown();

    }

    protected void tearDown() throws Exception {
        super.tearDown();

    }

    public void testErzeugeDaten() {
        String idS = "leer";
        _idS = erzeugeStandort(Strasse, Hausnr);
        if (_idS != 0) {
            idS = "vorhanden";
        }
        assertEquals(idS, "vorhanden");

        String idB = "leer";
        _idB = erzeugeBetreiber(Name, Handz);
        if (_idB != 0) {
            idB = "vorhanden";
        }
        assertEquals(idB, "vorhanden");

        String idO = "leer";
        _idO = erzeugeObjektart(ObjID, ObjArt);
        if (_idO != 0) {
            idO = "vorhanden";
        }
        assertEquals(idO, "vorhanden");

        BasisBetreiber.removeBetreiber(BasisBetreiber.getBetreiber(_idB));
        BasisStandort.removeStandort(BasisStandort.getStandort(_idS));
        BasisObjektarten.removeObjektart(BasisObjektarten.getObjektart(_idO));


    }

    /**
     * Kleine Hilfsmethode, mit der ein Standort erzeugt und in der Datenbank
     * gesichert wird.
     *
     * @param Strasse
     * @param Hausnr
     *            Die Hausnummer
     * @return Gibt die ID des erzeugten Standorts zurück.
     */
    private int erzeugeStandort(String Strasse, int Hausnr) {
        BasisStandort standort = new BasisStandort();
        standort.setStrasse(Strasse);
        standort.setHausnr(Hausnr);

        try {

            standort = BasisStandort.saveStandort(standort);

        } catch (HibernateException e) {

            throw e;

        }

        return standort.getStandortid();
    }

    /**
     * Kleine Hilfsmethode, mit der ein Betreiber erzeugt und in der Datenbank
     * gesichert wird.
     *
     * @param name
     *            Der Name des zu erzeugenden Betreibers.
     * @param handz
     *            Das Handzeichen
     * @return Gibt die ID des erzeugten Betreibers zurück.
     */
    private int erzeugeBetreiber(String name, String handz) {
        BasisBetreiber betreiber = new BasisBetreiber();
        betreiber.setBetrname(name);
        betreiber.setRevihandz(handz);

        try {

            betreiber = BasisBetreiber.saveBetreiber(betreiber);

        } catch (HibernateException e) {

                throw e;

        }


        return betreiber.getBetreiberid();
    }

    /**
     * Kleine Hilfsmethode, mit der eine Objektart erzeugt und in der Datenbank
     * gesichert wird.
     *
     * @param name
     *            Der Name des zu erzeugenden Betreibers.
     * @param handz
     *            Das Handzeichen
     * @return Gibt die ID des erzeugten Betreibers zurück.
     */
    private int erzeugeObjektart(int ID, String ObjArt) {
        BasisObjektarten art = new BasisObjektarten();
        art.setObjektartid(ID);
        art.setObjektart(ObjArt);

        try {

            art = BasisObjektarten.saveObjektart(art);

        } catch (HibernateException e) {

                throw e;

        }


        return art.getObjektartid();
    }

}

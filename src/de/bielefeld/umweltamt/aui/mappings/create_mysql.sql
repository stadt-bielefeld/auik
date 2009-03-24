-- table `anh_40_fachdaten`
CREATE TABLE `anh_40_fachdaten` (
        objektid int(10) NOT NULL ,
        bemerkungen varchar(255),
        ansprechpartner varchar(50),
        sachbearbeiterrav varchar(50),
        sachbearbeiterheepen varchar(50),
        klaeranlage varchar(50),
        herkunftsbereich varchar(50),
        wsg BIT,
        prioritaet smallint(5),
        genehmigungspflicht BIT,
        nachtrag BIT,
        bimsch BIT,
        abwmengegenehmigt int(10),
        abwmengeprodspez int(10),
        abwmengegesamt int(10),
        gen58 date,
        gen59 date
);
-- table `anh_49_abscheiderdetails`
CREATE TABLE `anh_49_abscheiderdetails` (
        id int(10) NOT NULL ,
        anh49id int(10) NOT NULL ,
        abscheidernr int(10),
        von int(10),
        lage varchar(255),
        nenngroesse int(10),
        tankstelle BIT,
        waschplatz_halle BIT,
        kfz_betrieb BIT,
        lebensmittelbetrieb BIT,
        wohnhaus BIT,
        oberflaechenentwaesserung BIT,
        produktionsabwasser BIT,
        schlammfang BIT,
        benzin_oelabscheider BIT,
        koaleszenzfilter BIT,
        integriert BIT,
        emulsionsspaltanlage BIT,
        fettabscheider BIT,
        baujahr varchar(50),
        din1999 BIT,
        bauartzulassungsnummer varchar(50),
        ng_sf int(10),
        ng_ba int(10),
        ng_ka int(10),
        schwimmer BIT,
        warnsignal BIT,
        wartungsvertrag BIT,
        vertragspartner varchar(50),
        letzte_wartung varchar(50),
        letzte_leerung varchar(50),
        hersteller varchar(255),
        schmutzwasserkanal BIT,
        regenwasserkanal BIT,
        mischwasserkanal BIT,
        direkteinleiter BIT,
        entsorgungnachweis varchar(255),
        entsorgungsvertrag varchar(255),
        entsorgungsintervalle varchar(255),
        flaeche int(10),
        bemerkung varchar(255),
        entsorgungsnachweis date,
        entsorgungsnachweis_durch varchar(25)
);
-- table `anh_49_analysen`
CREATE TABLE `anh_49_analysen` (
        id int(10) NOT NULL ,
        anh49id int(10) NOT NULL ,
        datum date,
        institut varchar(50),
        csb_wert varchar(50),
        csb_grenzwert varchar(50),
        aox_wert varchar(50),
        aox_grenzwert varchar(50),
        kw_wert varchar(50),
        kw_grenzwert varchar(50),
        zink_wert varchar(50),
        zink_grenzwert varchar(50),
        bsb5_wert varchar(50),
        bsb5_csb_relation varchar(50),
        bemerkungen varchar(50),
        bik_grenzwert varchar(50),
        bik_wert varchar(50),
        ph_wert varchar(50),
        ph_grenzwert varchar(50)
);
-- table `anh_49_fachdaten`
CREATE TABLE `anh_49_fachdaten` (
        objektid int(10) NOT NULL ,
        klaeranlage varchar(50),
        name varchar(50),
        bemerkungen varchar(255),
        planquadrat varchar(50),
        abgemeldet BIT,
        technik_anh49 varchar(50),
        technik_anh49_nr varchar(50),
        sachkundelfa varchar(50),
        werkstatt BIT,
        bodeneinlaeufe BIT,
        waschanlagen BIT,
        sonstiges varchar(255),
        analysemonat varchar(50),
        abwasserfrei BIT,
        dekra_tuev_termin int(10),
        anredeantragst varchar(100),
        nameantragst varchar(100),
        zusantragst varchar(50),
        strasseantragst varchar(50),
        hausnrantragst varchar(10),
        hausnrzusantragst varchar(10),
        plzantragst varchar(10),
        ortantragst varchar(50),
        sachbearbeiter_in varchar(50),
        ansprechpartner_in varchar(50),
        antragvom date,
        genehmigung date,
        wiedervorlage date,
        aenderungsgenehmigung date,
        letztes_anschreiben date,
        anschreiben varchar(50),
        waschanlage BIT,
        e_satzung BIT,
        seitwann date,
        sonstigestechnik varchar(255),
        maengel BIT,
        behoben BIT,
        frist date,
        durchgefuehrt int(10)
);
-- table `anh_49_ortstermine`
CREATE TABLE `anh_49_ortstermine` (
        id int(10) NOT NULL ,
        anh49id int(10) NOT NULL ,
        datum date,
        sachbearbeiter_in varchar(50),
        bemerkungen varchar(255)
);
-- table `anh_50_entsorger`
CREATE TABLE `anh_50_entsorger` (
        id int(10) NOT NULL ,
        entsorger varchar(100),
        strasse varchar(100),
        hausnr varchar(10),
        plz varchar(6),
        ort varchar(50),
        ansprechpartner varchar(50),
        telefon varchar(25)
);
-- table `anh_50_fachdaten`
CREATE TABLE `anh_50_fachdaten` (
        objektid int(10) NOT NULL ,
        gemeinschaft varchar(50),
        anrede varchar(50),
        name varchar(50),
        vorname varchar(50),
        strassenid int(10),
        hausnr varchar(10),
        hausnrzus varchar(10),
        telefon varchar(25),
        existenz BIT,
        erloschen BIT,
        m_liste BIT,
        amalgam BIT,
        abscheidervor BIT,
        datumantrag date,
        drucken BIT,
        bemerkungen text(65535),
        antrag_gueltig BIT,
        genehmigung date,
        angaben_korrekt BIT,
        antrag_stellen BIT,
        genehmigung_erforderlich BIT,
        wiedervorlage date,
        gefaehrdungsklasse varchar(50),
        entsorgerid int(10) NOT NULL 
);
-- table `anh_52_fachdaten`
CREATE TABLE `anh_52_fachdaten` (
        objektid int(10) NOT NULL ,
        id int(10) NOT NULL ,
        nrbetriebsstaette int(10),
        firmenname varchar(150),
        telefon varchar(25),
        telefax varchar(25),
        ansprechpartner varchar(150),
        datumgenehmigung date,
        bemerkungen varchar(255)
);
-- table `anh_53_entsorgungsbetriebe`
CREATE TABLE `anh_53_entsorgungsbetriebe` (
        id int(10) NOT NULL ,
        firmenname varchar(50),
        strasse varchar(50),
        hausnr varchar(5),
        plz varchar(5),
        ort varchar(50),
        telefon varchar(50),
        taetigkeitsfeld varchar(255)
);
-- table `anh_53_fachdaten`
CREATE TABLE `anh_53_fachdaten` (
        objektid int(10) NOT NULL ,
        branche varchar(255),
        verfahren varchar(50),
        antragsdatum date,
        bagatell BIT,
        bagatelldatum date,
        genehmigungsdatum date,
        genehmigungaufgehoben date,
        abnahmedatum date,
        genehmigungstitel varchar(255),
        genehmigung BIT,
        durchsatz int(10),
        gesamtmenge_eb int(10),
        onlineentsilberung BIT,
        abwasser BIT,
        abwasserfrei date,
        kleiner200qm date,
        betrieb_abgemeldet BIT,
        bemerkungen varchar(255),
        betriebstagebuch BIT,
        wasseruhr BIT,
        spuelwassermenge int(10),
        spuelwasserverbrauch int(10),
        wartungsvertrag BIT,
        grgen BIT,
        genart varchar(50)
);
-- table `anh_55_fachdaten`
CREATE TABLE `anh_55_fachdaten` (
        objektid int(10) NOT NULL ,
        sachbearbeiter varchar(10),
        entgeb_id varchar(10),
        bemerkungen varchar(255),
        abgemeldet BIT,
        mengewaesche varchar(30),
        putztuecher BIT,
        teppich BIT,
        matten BIT,
        haushaltstex BIT,
        berufskl BIT,
        gaststhotel BIT,
        sonsttex varchar(50),
        krankenhaus BIT,
        heimwaesche BIT,
        anteilwaschgut int(10),
        vlies BIT,
        fischfleisch BIT,
        anteilgesamtgut int(10),
        monatwasserverb varchar(50),
        betrwasseraufber BIT,
        chlor BIT,
        waschsituation varchar(255),
        ansprechpartner varchar(100),
        branche varchar(50),
        aktivchlor BIT,
        loesungsmittel BIT
);
-- table `anh_56_fachdaten`
CREATE TABLE `anh_56_fachdaten` (
        objektid int(10) NOT NULL ,
        druckverfahren varchar(150),
        sachbearbeiterrav varchar(50),
        sachbearbeiterheepen varchar(50),
        verbrauch varchar(150),
        entsorgung varchar(150),
        abwasseranfall BIT,
        genpflicht BIT,
        aba BIT,
        gen_58 date,
        gen_59 date,
        bemerkungen varchar(255),
        abfallrechtlentsorg BIT,
        spuelwasser BIT,
        leimabwasser BIT,
        erfasstam date
);
-- table `anh_bwk_fachdaten`
CREATE TABLE `anh_bwk_fachdaten` (
        objektid int(10) NOT NULL ,
        branche varchar(50),
        k_hersteller varchar(50),
        k_typ varchar(50),
        k_brennmittel varchar(50),
        k_leistung int(10),
        datum_g date,
        aba BIT,
        w_brenner varchar(50),
        w_waermetauscher varchar(50),
        w_abgasleitung varchar(50),
        w_kondensableitung varchar(50),
        abnahme varchar(50),
        bemerkungen varchar(150),
        anschreiben date,
        erfassung int(10),
        genehmigung BIT
);
-- table `anh_suev_fachdaten`
CREATE TABLE `anh_suev_fachdaten` (
        objektid int(10) NOT NULL ,
        groesser_3ha BIT,
        vers_flaeche int(10),
        suevkan_pflicht BIT,
        indirektsw BIT,
        indirektrw BIT,
        direktsw BIT,
        direktrw BIT,
        anzeige58 BIT,
        sanierung_erfolgt BIT,
        sanierungskonzept BIT,
        keine_angaben BIT,
        dat_anzeige58 date,
        dat_anschreiben date
);
-- table `atl_analyseposition`
CREATE TABLE `atl_analyseposition` (
        id int(10) NOT NULL ,
        probe_id varchar(50),
        parameter_id varchar(6),
        grkl varchar(3),
        wert float(12),
        einheiten_id int(10) NOT NULL ,
        analyse_von varchar(50),
        bericht varchar(255),
        probenahme_id int(10),
        normwert float(12)
);
-- table `atl_einheiten`
CREATE TABLE `atl_einheiten` (
        id int(10) NOT NULL ,
        bezeichnung varchar(40)
);
-- table `atl_klaeranlagen`
CREATE TABLE `atl_klaeranlagen` (
        id int(10) NOT NULL ,
        anlage varchar(32) NOT NULL 
);
-- table `atl_parameter`
CREATE TABLE `atl_parameter` (
        ordnungsbegriff varchar(6) NOT NULL ,
        analyseverfahren varchar(40),
        bezeichnung varchar(40),
        wirdgemessenineinheit int(10),
        grenzwert float(12),
        grenzwertname varchar(50),
        sielhaut_gw float(12),
        klaerschlamm_gw float(12)
);
-- table `atl_probeart`
CREATE TABLE `atl_probeart` (
        id int(10) NOT NULL ,
        art varchar(32) NOT NULL 
);
-- table `atl_probenahmen`
CREATE TABLE `atl_probenahmen` (
        id int(10) NOT NULL ,
        objektid int(10),
        kennummer varchar(50),
        sielhaut_id int(10),
        firmen_id int(10),
        probepkt_id int(10),
        art varchar(255),
        betrieb varchar(255),
        probenahmepkt varchar(255),
        nr_probenahmepkt int(10),
        datum_der_entnahme date,
        bis_datum date,
        zeit_der_entnahmen varchar(50),
        einwaage float(12),
        v_m3 varchar(255),
        fahrer varchar(255),
        objekt_nr varchar(255),
        datum_icp date,
        sonderparameter varchar(255),
        bemerkung varchar(255),
        an_360x11 date,
        ueberschreitung varchar(255),
        bescheid date,
        kosten float(12),
        massnahmen varchar(255),
        bezeichnung varchar(255),
        quartal varchar(10)
);
-- table `atl_probepkt`
CREATE TABLE `atl_probepkt` (
        objektid int(10) NOT NULL ,
        sielhaut_id int(10),
        firmen_id int(10),
        nr_probepkt int(10),
        art varchar(255),
        betrieb varchar(255),
        probenahmepkt varchar(255),
        ka_id int(10),
        art_id int(10),
        beschreibung varchar(255),
        id_alt int(10)
);
-- table `atl_sielhaut`
CREATE TABLE `atl_sielhaut` (
        id int(10) NOT NULL ,
        bezeichnung varchar(50),
        haltungsnr varchar(50),
        alarmplannr varchar(50),
        entgeb varchar(50),
        rechtswert decimal(16,2),
        hochwert decimal(16,2),
        lage varchar(50),
        bemerkungen varchar(255),
        twabfluss varchar(50),
        bsb varchar(50),
        ew varchar(50),
        gebiet varchar(50),
        p_sielhaut BIT,
        p_alarmplan BIT,
        p_nachprobe BIT,
        p_firmenprobe BIT,
        schlammprobe BIT
);
-- table `basis_betreiber`
CREATE TABLE `basis_betreiber` (
        id int(10) NOT NULL ,
        betranrede varchar(100),
        betrname varchar(100),
        betrnamezus varchar(50),
        namebetrbeauf varchar(50),
        vornamebetrbeauf varchar(50),
        strasse varchar(50),
        hausnr varchar(10),
        hausnrzus varchar(5),
        plzzs char(3),
        plz varchar(6),
        ort varchar(50),
        telefon varchar(50),
        telefax varchar(50),
        email varchar(50),
        wirtschaftszweigid int(10),
        bemerkungen text(65535),
        revidatum date,
        revihandz varchar(10)
);
-- table `basis_gemarkung`
CREATE TABLE `basis_gemarkung` (
        id int(10) NOT NULL ,
        gemarkung varchar(255)
);
-- table `basis_objekt`
CREATE TABLE `basis_objekt` (
        objektid int(10) NOT NULL ,
        standortid int(10) NOT NULL ,
        betreiberid int(10) NOT NULL ,
        objektartid int(10) NOT NULL ,
        uschistdid int(10),
        beschreibung varchar(150),
        sachbearbeiter varchar(25),
        wiedervorlage date
);
-- table `basis_objektarten`
CREATE TABLE `basis_objektarten` (
        id int(10) NOT NULL ,
        objektart varchar(50),
        abteilung varchar(10),
        kategorie varchar(25)
);
-- table `basis_objektchrono`
CREATE TABLE `basis_objektchrono` (
        id bigint unsigned(19) NOT NULL ,
        objektid int(10) NOT NULL ,
        datum date,
        sachverhalt varchar(255),
        wv date
);
-- table `basis_objektverknuepfung`
CREATE TABLE `basis_objektverknuepfung` (
        id bigint unsigned(19) NOT NULL ,
        objekt int(10),
        ist_verknuepft_mit int(10)
);
-- table `basis_standort`
CREATE TABLE `basis_standort` (
        id int(10) NOT NULL ,
        strasse varchar(50),
        hausnr int(10),
        hausnrzus varchar(5),
        plz varchar(6),
        rechtswert decimal(16,2),
        hochwert decimal(16,2),
        gemarkungid int(10),
        flur varchar(10),
        flurstueck varchar(50),
        entgebid varchar(10),
        standortgegebid int(10),
        wassereinzgebid int(10),
        vornameeigent varchar(50),
        nameeigent varchar(50),
        strasseeigent varchar(50),
        hausnreigent int(10),
        hausnrzuseigent char(3),
        revidatum date,
        revihandz varchar(10)
);
-- table `basis_strassen`
CREATE TABLE `basis_strassen` (
        id int(10) NOT NULL ,
        strasse varchar(50),
        plz varchar(6)
);
-- table `indeinl_genehmigung`
CREATE TABLE `indeinl_genehmigung` (
        objektid int(10) NOT NULL ,
        erstellungs_datum date,
        aenderungs_datum date,
        antrag_datum date,
        befristet BIT,
        befristet_bis date,
        anhang int(10),
        gen_menge int(10),
        gen58 BIT,
        gen59 BIT,
        selbstueberw BIT,
        e_satzung BIT,
        bemerkungen varchar(255)
);
-- table `indeinl_uebergabestelle`
CREATE TABLE `indeinl_uebergabestelle` (
        objektid int(10) NOT NULL ,
        klaeranlage int(10),
        aenderungs_datum date,
        erfassungs_datum date,
        kanalart int(10),
        rechtswert int(10),
        hochwert int(10),
        bemerkungen varchar(255)
);
-- table `vaws_abfuellflaeche`
CREATE TABLE `vaws_abfuellflaeche` (
        behaelterid int(10) NOT NULL ,
        eoh BIT,
        ef BIT,
        abfsaniert BIT,
        abfneuerstellt BIT,
        bodenflaechenausf varchar(50),
        beschbodenfl varchar(255),
        dicke float(12),
        guete varchar(50),
        fugenmaterial varchar(50),
        beschfugenmat varchar(255),
        niederschlagschutz varchar(50),
        abscheidervorh BIT,
        beschableitung varchar(255)
);
-- table `vaws_abscheider`
CREATE TABLE `vaws_abscheider` (
        behaelterid int(10) NOT NULL ,
        medium varchar(50),
        kompaktanlage BIT,
        sf BIT,
        kkl1 BIT,
        lfkl2 BIT,
        ps BIT,
        sfhersteller varchar(50),
        sftyp varchar(50),
        sfvolumen varchar(50),
        sfmaterial varchar(50),
        sfbeschichtung varchar(50),
        abhersteller varchar(50),
        abtyp varchar(50),
        abpruefz varchar(50),
        abmaterial varchar(50),
        abbeschichtung varchar(50),
        abnenngr varchar(50),
        oelspeichervol varchar(50),
        zuldn varchar(50),
        zulmaterial varchar(50),
        zullaenge varchar(50),
        verbdn varchar(50),
        verbmaterial varchar(50),
        verblaenge varchar(50),
        sondn varchar(50),
        sonmaterial varchar(50),
        sonlaenge varchar(50),
        ueberhausr BIT,
        waschanlvorh BIT,
        abgabe BIT,
        hlzapfanl BIT,
        belvonlagerbh BIT,
        rueckhalteausr BIT
);
-- table `vaws_anlagenarten`
CREATE TABLE `vaws_anlagenarten` (
        id bigint unsigned(19) NOT NULL ,
        anlagenart varchar(60)
);
-- table `vaws_anlagenchrono`
CREATE TABLE `vaws_anlagenchrono` (
        id int(10) NOT NULL ,
        behaelterid int(10) NOT NULL ,
        datum date,
        sachverhalt varchar(255),
        wv date
);
-- table `vaws_behaelterart`
CREATE TABLE `vaws_behaelterart` (
        id int(10) NOT NULL ,
        behaelterart varchar(50)
);
-- table `vaws_fachdaten`
CREATE TABLE `vaws_fachdaten` (
        behaelterid int(10) NOT NULL ,
        objektid int(10) NOT NULL ,
        herstellnr varchar(25),
        hersteller varchar(50),
        datuminbetriebnahme date,
        datumerfassung date,
        datumaenderung date,
        datumgenehmigung date,
        anlagenart varchar(50),
        behaelterart varchar(50),
        material varchar(50),
        fluessigkeit varchar(50),
        vbfeinstufung varchar(5),
        menge float(12),
        wgk int(10),
        gefaehrdungsstufe varchar(5),
        baujahr int(10),
        doppelwandig BIT,
        leckanzeige BIT,
        auffangraum BIT,
        grenzwertgeber BIT,
        leckschutzauskleidung BIT,
        kellerlagerung BIT,
        innenbeschichtung BIT,
        beschreibung_a varchar(255),
        beschreibung_s varchar(255),
        oberirdisch BIT,
        unterirdisch BIT,
        saugleitung BIT,
        rohr_kathodenschutz BIT,
        aus_kupfer BIT,
        aus_stahl BIT,
        mit_schutzrohr BIT,
        beschreibung_r varchar(255),
        pruefturnus float(12),
        angemahntkz BIT,
        mahnfrist date,
        wiedervorlage date,
        stillegungsdatum date,
        bemerkungen varchar(255),
        ausfuehrung varchar(50),
        pruefumfang varchar(50),
        verwendung varchar(50)
);
-- table `vaws_fluessigkeit`
CREATE TABLE `vaws_fluessigkeit` (
        id bigint unsigned(19) NOT NULL ,
        fluessigkeit varchar(50),
        idland int(10) NOT NULL 
);
-- table `vaws_gebuehrenarten`
CREATE TABLE `vaws_gebuehrenarten` (
        id bigint unsigned(19) NOT NULL ,
        gebuehrenart varchar(50)
);
-- table `vaws_gefaehrdungsstufen`
CREATE TABLE `vaws_gefaehrdungsstufen` (
        id bigint unsigned(19) NOT NULL ,
        gefaehrdungsstufen varchar(5)
);
-- table `vaws_kontrollen`
CREATE TABLE `vaws_kontrollen` (
        id int(10) NOT NULL ,
        behaelterid int(10) NOT NULL ,
        grundderpruefung varchar(50),
        pruefturnus float(12),
        pruefdatum date,
        pruefer varchar(15),
        pruefungabgeschlossen BIT,
        nachpruefung BIT,
        nachpruefdatum date,
        nachpruefer varchar(50),
        naechstepruefung date,
        pruefergebnis varchar(50)
);
-- table `vaws_material`
CREATE TABLE `vaws_material` (
        id bigint unsigned(19) NOT NULL ,
        material varchar(50)
);
-- table `vaws_pruefer`
CREATE TABLE `vaws_pruefer` (
        id bigint unsigned(19) NOT NULL ,
        pruefer varchar(50)
);
-- table `vaws_pruefergebnisse`
CREATE TABLE `vaws_pruefergebnisse` (
        id bigint unsigned(19) NOT NULL ,
        pruefergebnis varchar(50)
);
-- table `vaws_standortgghwsg`
CREATE TABLE `vaws_standortgghwsg` (
        id int(10) NOT NULL ,
        standortgg varchar(255)
);
-- table `vaws_vbfeinstufung`
CREATE TABLE `vaws_vbfeinstufung` (
        id bigint unsigned(19) NOT NULL ,
        vbfeinstufung varchar(50)
);
-- table `vaws_verwaltungsgebuehren`
CREATE TABLE `vaws_verwaltungsgebuehren` (
        id int(10) NOT NULL ,
        behaelterid int(10) NOT NULL ,
        datum date,
        gebuehrenart int(10),
        betrag float(12),
        abschnitt varchar(50),
        kassenzeichen varchar(10)
);
-- table `vaws_verwaltungsverf`
CREATE TABLE `vaws_verwaltungsverf` (
        id int(10) NOT NULL ,
        behaelterid int(10) NOT NULL ,
        datum date,
        massnahme varchar(50),
        wiedervorlage date,
        wvverwverf BIT,
        bemerkung varchar(255)
);
-- table `vaws_verwmassnahmen`
CREATE TABLE `vaws_verwmassnahmen` (
        id bigint unsigned(19) NOT NULL ,
        massnahmen varchar(50)
);
-- table `vaws_wassereinzugsgebiete`
CREATE TABLE `vaws_wassereinzugsgebiete` (
        id int(10) NOT NULL ,
        ezgbname varchar(255)
);
-- table `vaws_wgk`
CREATE TABLE `vaws_wgk` (
        id bigint unsigned(19) NOT NULL ,
        wassergef int(10)
);
-- table `vaws_wirtschaftszweige`
CREATE TABLE `vaws_wirtschaftszweige` (
        id int(10) NOT NULL ,
        wirtschaftszweig varchar(255)
);

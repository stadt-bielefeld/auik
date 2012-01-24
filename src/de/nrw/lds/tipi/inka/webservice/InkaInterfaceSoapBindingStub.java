/**
 * InkaInterfaceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.webservice;

public class InkaInterfaceSoapBindingStub extends org.apache.axis.client.Stub implements de.nrw.lds.tipi.inka.webservice.InkaInterfacePortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[63];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
        _initOperationDesc4();
        _initOperationDesc5();
        _initOperationDesc6();
        _initOperationDesc7();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("testServiceEcho");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inParameter"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "testServiceEchoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getServiceVersion");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getServiceVersionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDea_Analysemethode");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Analysemethode"), de.nrw.lds.tipi.inka.request.ReqDea_Analysemethode.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Analysemethode"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResDea_Analysemethode.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getDea_AnalysemethodeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDea_Anhang");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Anhang"), de.nrw.lds.tipi.inka.request.ReqDea_Anhang.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Anhang"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResDea_Anhang.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getDea_AnhangReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDea_Arbeitsstaetten");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Arbeitsstaetten"), de.nrw.lds.tipi.inka.request.ReqDea_Arbeitsstaetten.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Arbeitsstaetten"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResDea_Arbeitsstaetten.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getDea_ArbeitsstaettenReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDea_Einheiten");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Einheiten"), de.nrw.lds.tipi.inka.request.ReqDea_Einheiten.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Einheiten"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResDea_Einheiten.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getDea_EinheitenReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDea_Gemeinde");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Gemeinde"), de.nrw.lds.tipi.inka.request.ReqDea_Gemeinde.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Gemeinde"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResDea_Gemeinde.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getDea_GemeindeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDea_Klaeranlage");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Klaeranlage"), de.nrw.lds.tipi.inka.request.ReqDea_Klaeranlage.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Klaeranlage"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResDea_Klaeranlage.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getDea_KlaeranlageReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDea_Parameter");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Parameter"), de.nrw.lds.tipi.inka.request.ReqDea_Parameter.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Parameter"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResDea_Parameter.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getDea_ParameterReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDea_Probedauer");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Probedauer"), de.nrw.lds.tipi.inka.request.ReqDea_Probedauer.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Probedauer"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResDea_Probedauer.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getDea_ProbedauerReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDea_Stoffe");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Stoffe"), de.nrw.lds.tipi.inka.request.ReqDea_Stoffe.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Stoffe"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResDea_Stoffe.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getDea_StoffeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDea_Stua");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Stua"), de.nrw.lds.tipi.inka.request.ReqDea_Stua.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Stua"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResDea_Stua.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getDea_StuaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDea_Tk25");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Tk25"), de.nrw.lds.tipi.inka.request.ReqDea_Tk25.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Tk25"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResDea_Tk25.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getDea_Tk25Return"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDea_Wasserbehoerden");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Wasserbehoerden"), de.nrw.lds.tipi.inka.request.ReqDea_Wasserbehoerden.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Wasserbehoerden"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResDea_Wasserbehoerden.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getDea_WasserbehoerdenReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDea_Wz_Code");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Wz_Code"), de.nrw.lds.tipi.inka.request.ReqDea_Wz_Code.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Wz_Code"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResDea_Wz_Code.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getDea_Wz_CodeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDea_Adresse");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Adresse"), de.nrw.lds.tipi.inka.request.ReqDea_Adresse.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Adresse"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResDea_Adresse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getDea_AdresseReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInka_Anfallstelle");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Anfallstelle"), de.nrw.lds.tipi.inka.request.ReqInka_Anfallstelle.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Anfallstelle"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResInka_Anfallstelle.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getInka_AnfallstelleReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInka_Anfallst_Anlage");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Anfallst_Anlage"), de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Anlage.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Anfallst_Anlage"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResInka_Anfallst_Anlage.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getInka_Anfallst_AnlageReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInka_Anfallst_Messst");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Anfallst_Messst"), de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Messst.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Anfallst_Messst"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResInka_Anfallst_Messst.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getInka_Anfallst_MessstReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInka_Anfallst_Stoffe");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Anfallst_Stoffe"), de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Stoffe.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Anfallst_Stoffe"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResInka_Anfallst_Stoffe.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getInka_Anfallst_StoffeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInka_Anlage");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Anlage"), de.nrw.lds.tipi.inka.request.ReqInka_Anlage.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Anlage"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResInka_Anlage.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getInka_AnlageReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInka_Betrieb");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Betrieb"), de.nrw.lds.tipi.inka.request.ReqInka_Betrieb.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Betrieb"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResInka_Betrieb.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getInka_BetriebReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInka_Betriebseinrichtung");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Betriebseinrichtung"), de.nrw.lds.tipi.inka.request.ReqInka_Betriebseinrichtung.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Betriebseinrichtung"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResInka_Betriebseinrichtung.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getInka_BetriebseinrichtungReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInka_Fliessschema");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Fliessschema"), de.nrw.lds.tipi.inka.request.ReqInka_Fliessschema.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Fliessschema"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResInka_Fliessschema.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getInka_FliessschemaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInka_Genehmigung");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Genehmigung"), de.nrw.lds.tipi.inka.request.ReqInka_Genehmigung.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Genehmigung"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResInka_Genehmigung.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getInka_GenehmigungReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInka_Messstelle");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Messstelle"), de.nrw.lds.tipi.inka.request.ReqInka_Messstelle.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Messstelle"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResInka_Messstelle.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getInka_MessstelleReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[25] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInka_Messst_Anlage");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Messst_Anlage"), de.nrw.lds.tipi.inka.request.ReqInka_Messst_Anlage.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Messst_Anlage"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResInka_Messst_Anlage.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getInka_Messst_AnlageReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[26] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInka_Probenahme");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Probenahme"), de.nrw.lds.tipi.inka.request.ReqInka_Probenahme.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Probenahme"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResInka_Probenahme.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getInka_ProbenahmeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[27] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInka_Uebergabestelle");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Uebergabestelle"), de.nrw.lds.tipi.inka.request.ReqInka_Uebergabestelle.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Uebergabestelle"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResInka_Uebergabestelle.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getInka_UebergabestelleReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[28] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInka_Ueberwach_Ergebnis");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Ueberwach_Ergebnis"), de.nrw.lds.tipi.inka.request.ReqInka_Ueberwach_Ergebnis.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Ueberwach_Ergebnis"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResInka_Ueberwach_Ergebnis.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getInka_Ueberwach_ErgebnisReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[29] = oper;

    }

    private static void _initOperationDesc4(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInka_Ueberwachungswert");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Ueberwachungswert"), de.nrw.lds.tipi.inka.request.ReqInka_Ueberwachungswert.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Ueberwachungswert"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.response.ResInka_Ueberwachungswert.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getInka_UeberwachungswertReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[30] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setDea_Adresse");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Adresse"), de.nrw.lds.tipi.inka.request.ReqDea_Adresse.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "setDea_AdresseReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[31] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setInka_Anfallstelle");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Anfallstelle"), de.nrw.lds.tipi.inka.request.ReqInka_Anfallstelle.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "setInka_AnfallstelleReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[32] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setInka_Anfallst_Anlage");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Anfallst_Anlage"), de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Anlage.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "setInka_Anfallst_AnlageReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[33] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setInka_Anfallst_Messst");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Anfallst_Messst"), de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Messst.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "setInka_Anfallst_MessstReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[34] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setInka_Anfallst_Stoffe");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Anfallst_Stoffe"), de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Stoffe.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "setInka_Anfallst_StoffeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[35] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setInka_Anlage");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Anlage"), de.nrw.lds.tipi.inka.request.ReqInka_Anlage.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "setInka_AnlageReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[36] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setInka_Betrieb");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Betrieb"), de.nrw.lds.tipi.inka.request.ReqInka_Betrieb.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "setInka_BetriebReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[37] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setInka_Betriebseinrichtung");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Betriebseinrichtung"), de.nrw.lds.tipi.inka.request.ReqInka_Betriebseinrichtung.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "setInka_BetriebseinrichtungReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[38] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setInka_Fliessschema");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Fliessschema"), de.nrw.lds.tipi.inka.request.ReqInka_Fliessschema.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "setInka_FliessschemaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[39] = oper;

    }

    private static void _initOperationDesc5(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setInka_Genehmigung");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Genehmigung"), de.nrw.lds.tipi.inka.request.ReqInka_Genehmigung.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "setInka_GenehmigungReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[40] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setInka_Messstelle");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Messstelle"), de.nrw.lds.tipi.inka.request.ReqInka_Messstelle.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "setInka_MessstelleReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[41] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setInka_Messst_Anlage");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Messst_Anlage"), de.nrw.lds.tipi.inka.request.ReqInka_Messst_Anlage.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "setInka_Messst_AnlageReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[42] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setInka_Probenahme");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Probenahme"), de.nrw.lds.tipi.inka.request.ReqInka_Probenahme.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "setInka_ProbenahmeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[43] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setInka_Uebergabestelle");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Uebergabestelle"), de.nrw.lds.tipi.inka.request.ReqInka_Uebergabestelle.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "setInka_UebergabestelleReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[44] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setInka_Ueberwach_Ergebnis");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Ueberwach_Ergebnis"), de.nrw.lds.tipi.inka.request.ReqInka_Ueberwach_Ergebnis.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "setInka_Ueberwach_ErgebnisReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[45] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setInka_Ueberwachungswert");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Ueberwachungswert"), de.nrw.lds.tipi.inka.request.ReqInka_Ueberwachungswert.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "setInka_UeberwachungswertReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[46] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteDea_Adresse");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Adresse"), de.nrw.lds.tipi.inka.request.ReqDea_Adresse.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "deleteDea_AdresseReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[47] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteInka_Anfallstelle");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Anfallstelle"), de.nrw.lds.tipi.inka.request.ReqInka_Anfallstelle.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "deleteInka_AnfallstelleReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[48] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteInka_Anfallst_Anlage");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Anfallst_Anlage"), de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Anlage.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "deleteInka_Anfallst_AnlageReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[49] = oper;

    }

    private static void _initOperationDesc6(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteInka_Anfallst_Messst");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Anfallst_Messst"), de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Messst.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "deleteInka_Anfallst_MessstReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[50] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteInka_Anfallst_Stoffe");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Anfallst_Stoffe"), de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Stoffe.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "deleteInka_Anfallst_StoffeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[51] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteInka_Anlage");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Anlage"), de.nrw.lds.tipi.inka.request.ReqInka_Anlage.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "deleteInka_AnlageReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[52] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteInka_Betrieb");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Betrieb"), de.nrw.lds.tipi.inka.request.ReqInka_Betrieb.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "deleteInka_BetriebReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[53] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteInka_Betriebseinrichtung");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Betriebseinrichtung"), de.nrw.lds.tipi.inka.request.ReqInka_Betriebseinrichtung.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "deleteInka_BetriebseinrichtungReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[54] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteInka_Fliessschema");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Fliessschema"), de.nrw.lds.tipi.inka.request.ReqInka_Fliessschema.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "deleteInka_FliessschemaReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[55] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteInka_Genehmigung");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Genehmigung"), de.nrw.lds.tipi.inka.request.ReqInka_Genehmigung.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "deleteInka_GenehmigungReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[56] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteInka_Messstelle");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Messstelle"), de.nrw.lds.tipi.inka.request.ReqInka_Messstelle.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "deleteInka_MessstelleReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[57] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteInka_Messst_Anlage");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Messst_Anlage"), de.nrw.lds.tipi.inka.request.ReqInka_Messst_Anlage.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "deleteInka_Messst_AnlageReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[58] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteInka_Probenahme");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Probenahme"), de.nrw.lds.tipi.inka.request.ReqInka_Probenahme.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "deleteInka_ProbenahmeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[59] = oper;

    }

    private static void _initOperationDesc7(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteInka_Uebergabestelle");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Uebergabestelle"), de.nrw.lds.tipi.inka.request.ReqInka_Uebergabestelle.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "deleteInka_UebergabestelleReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[60] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteInka_Ueberwach_Ergebnis");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Ueberwach_Ergebnis"), de.nrw.lds.tipi.inka.request.ReqInka_Ueberwach_Ergebnis.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "deleteInka_Ueberwach_ErgebnisReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[61] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteInka_Ueberwachungswert");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "req"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Ueberwachungswert"), de.nrw.lds.tipi.inka.request.ReqInka_Ueberwachungswert.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard"));
        oper.setReturnClass(de.nrw.lds.tipi.inka.general.ResStandard.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "deleteInka_UeberwachungswertReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[62] = oper;

    }

    public InkaInterfaceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public InkaInterfaceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public InkaInterfaceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
        addBindings0();
        addBindings1();
    }

    private void addBindings0() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ReqStandard");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.general.ReqStandard.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResError");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.general.ResError.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResKatalog");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.general.ResKatalog.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://general.inka.tipi.lds.nrw.de", "ResStandard");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.general.ResStandard.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://general.tipi.lds.nrw.de", "HistoryObject");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.general.HistoryObject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Adresse");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Adresse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Analysemethode");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Analysemethode.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Anhang");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Anhang.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Arbeitsstaetten");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Arbeitsstaetten.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Einheiten");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Einheiten.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Gemeinde");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Gemeinde.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Klaeranlage");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Klaeranlage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Parameter");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Parameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Probedauer");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Probedauer.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Stoffe");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Stoffe.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Stua");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Stua.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Tk25");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Tk25.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Wasserbehoerden");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Wasserbehoerden.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Wz_Code");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Wz_Code.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Anfallst_Anlage");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Anfallst_Anlage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Anfallst_Messst");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Anfallst_Messst.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Anfallst_Stoffe");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Anfallst_Stoffe.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Anfallstelle");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Anfallstelle.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Anlage");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Anlage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Betrieb");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Betrieb.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Betriebseinrichtung");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Betriebseinrichtung.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Fliessschema");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Fliessschema.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Genehmigung");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Genehmigung.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Messst_Anlage");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Messst_Anlage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Messstelle");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Messstelle.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Probenahme");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Probenahme.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Uebergabestelle");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Uebergabestelle.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Ueberwach_Ergebnis");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Ueberwach_Ergebnis.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Ueberwachungswert");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Ueberwachungswert.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Adresse");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqDea_Adresse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Analysemethode");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqDea_Analysemethode.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Anhang");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqDea_Anhang.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Arbeitsstaetten");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqDea_Arbeitsstaetten.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Einheiten");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqDea_Einheiten.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Gemeinde");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqDea_Gemeinde.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Klaeranlage");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqDea_Klaeranlage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Parameter");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqDea_Parameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Probedauer");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqDea_Probedauer.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Stoffe");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqDea_Stoffe.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Stua");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqDea_Stua.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Tk25");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqDea_Tk25.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Wasserbehoerden");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqDea_Wasserbehoerden.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqDea_Wz_Code");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqDea_Wz_Code.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Anfallst_Anlage");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Anlage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Anfallst_Messst");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Messst.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Anfallst_Stoffe");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Stoffe.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Anfallstelle");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqInka_Anfallstelle.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Anlage");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqInka_Anlage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Betrieb");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqInka_Betrieb.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Betriebseinrichtung");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqInka_Betriebseinrichtung.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Fliessschema");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqInka_Fliessschema.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Genehmigung");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqInka_Genehmigung.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Messst_Anlage");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqInka_Messst_Anlage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Messstelle");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqInka_Messstelle.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Probenahme");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqInka_Probenahme.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Uebergabestelle");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqInka_Uebergabestelle.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Ueberwach_Ergebnis");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqInka_Ueberwach_Ergebnis.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://request.inka.tipi.lds.nrw.de", "ReqInka_Ueberwachungswert");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.request.ReqInka_Ueberwachungswert.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Adresse");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResDea_Adresse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Analysemethode");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResDea_Analysemethode.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Anhang");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResDea_Anhang.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Arbeitsstaetten");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResDea_Arbeitsstaetten.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Einheiten");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResDea_Einheiten.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Gemeinde");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResDea_Gemeinde.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Klaeranlage");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResDea_Klaeranlage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Parameter");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResDea_Parameter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Probedauer");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResDea_Probedauer.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Stoffe");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResDea_Stoffe.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Stua");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResDea_Stua.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Tk25");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResDea_Tk25.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Wasserbehoerden");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResDea_Wasserbehoerden.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResDea_Wz_Code");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResDea_Wz_Code.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Anfallst_Anlage");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResInka_Anfallst_Anlage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Anfallst_Messst");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResInka_Anfallst_Messst.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Anfallst_Stoffe");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResInka_Anfallst_Stoffe.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Anfallstelle");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResInka_Anfallstelle.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Anlage");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResInka_Anlage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Betrieb");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResInka_Betrieb.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Betriebseinrichtung");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResInka_Betriebseinrichtung.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Fliessschema");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResInka_Fliessschema.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Genehmigung");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResInka_Genehmigung.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Messst_Anlage");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResInka_Messst_Anlage.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Messstelle");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResInka_Messstelle.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Probenahme");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResInka_Probenahme.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Uebergabestelle");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResInka_Uebergabestelle.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Ueberwach_Ergebnis");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResInka_Ueberwach_Ergebnis.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://response.inka.tipi.lds.nrw.de", "ResInka_Ueberwachungswert");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.response.ResInka_Ueberwachungswert.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfDea_Adresse");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Adresse[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Adresse");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfDea_Analysemethode");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Analysemethode[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Analysemethode");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfDea_Anhang");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Anhang[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Anhang");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfDea_Arbeitsstaetten");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Arbeitsstaetten[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Arbeitsstaetten");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfDea_Einheiten");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Einheiten[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Einheiten");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfDea_Gemeinde");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Gemeinde[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Gemeinde");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfDea_Klaeranlage");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Klaeranlage[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Klaeranlage");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfDea_Parameter");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Parameter[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Parameter");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

    }
    private void addBindings1() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfDea_Probedauer");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Probedauer[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Probedauer");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfDea_Stoffe");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Stoffe[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Stoffe");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfDea_Stua");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Stua[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Stua");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfDea_Tk25");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Tk25[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Tk25");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfDea_Wasserbehoerden");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Wasserbehoerden[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Wasserbehoerden");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfDea_Wz_Code");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Dea_Wz_Code[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Dea_Wz_Code");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfInka_Anfallst_Anlage");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Anfallst_Anlage[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Anfallst_Anlage");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfInka_Anfallst_Messst");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Anfallst_Messst[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Anfallst_Messst");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfInka_Anfallst_Stoffe");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Anfallst_Stoffe[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Anfallst_Stoffe");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfInka_Anfallstelle");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Anfallstelle[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Anfallstelle");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfInka_Anlage");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Anlage[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Anlage");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfInka_Betrieb");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Betrieb[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Betrieb");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfInka_Betriebseinrichtung");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Betriebseinrichtung[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Betriebseinrichtung");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfInka_Fliessschema");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Fliessschema[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Fliessschema");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfInka_Genehmigung");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Genehmigung[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Genehmigung");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfInka_Messst_Anlage");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Messst_Anlage[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Messst_Anlage");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfInka_Messstelle");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Messstelle[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Messstelle");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfInka_Probenahme");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Probenahme[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Probenahme");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfInka_Uebergabestelle");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Uebergabestelle[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Uebergabestelle");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfInka_Ueberwach_Ergebnis");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Ueberwach_Ergebnis[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Ueberwach_Ergebnis");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "ArrayOfInka_Ueberwachungswert");
            cachedSerQNames.add(qName);
            cls = de.nrw.lds.tipi.inka.Inka_Ueberwachungswert[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://inka.tipi.lds.nrw.de", "Inka_Ueberwachungswert");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
                    _call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public java.lang.String testServiceEcho(java.lang.String inParameter) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "testServiceEcho"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {inParameter});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String getServiceVersion() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getServiceVersion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResDea_Analysemethode getDea_Analysemethode(de.nrw.lds.tipi.inka.request.ReqDea_Analysemethode req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getDea_Analysemethode"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResDea_Analysemethode) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResDea_Analysemethode) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResDea_Analysemethode.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResDea_Anhang getDea_Anhang(de.nrw.lds.tipi.inka.request.ReqDea_Anhang req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getDea_Anhang"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResDea_Anhang) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResDea_Anhang) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResDea_Anhang.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResDea_Arbeitsstaetten getDea_Arbeitsstaetten(de.nrw.lds.tipi.inka.request.ReqDea_Arbeitsstaetten req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getDea_Arbeitsstaetten"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResDea_Arbeitsstaetten) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResDea_Arbeitsstaetten) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResDea_Arbeitsstaetten.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResDea_Einheiten getDea_Einheiten(de.nrw.lds.tipi.inka.request.ReqDea_Einheiten req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getDea_Einheiten"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResDea_Einheiten) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResDea_Einheiten) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResDea_Einheiten.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResDea_Gemeinde getDea_Gemeinde(de.nrw.lds.tipi.inka.request.ReqDea_Gemeinde req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getDea_Gemeinde"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResDea_Gemeinde) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResDea_Gemeinde) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResDea_Gemeinde.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResDea_Klaeranlage getDea_Klaeranlage(de.nrw.lds.tipi.inka.request.ReqDea_Klaeranlage req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getDea_Klaeranlage"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResDea_Klaeranlage) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResDea_Klaeranlage) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResDea_Klaeranlage.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResDea_Parameter getDea_Parameter(de.nrw.lds.tipi.inka.request.ReqDea_Parameter req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getDea_Parameter"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResDea_Parameter) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResDea_Parameter) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResDea_Parameter.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResDea_Probedauer getDea_Probedauer(de.nrw.lds.tipi.inka.request.ReqDea_Probedauer req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getDea_Probedauer"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResDea_Probedauer) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResDea_Probedauer) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResDea_Probedauer.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResDea_Stoffe getDea_Stoffe(de.nrw.lds.tipi.inka.request.ReqDea_Stoffe req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getDea_Stoffe"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResDea_Stoffe) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResDea_Stoffe) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResDea_Stoffe.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResDea_Stua getDea_Stua(de.nrw.lds.tipi.inka.request.ReqDea_Stua req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getDea_Stua"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResDea_Stua) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResDea_Stua) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResDea_Stua.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResDea_Tk25 getDea_Tk25(de.nrw.lds.tipi.inka.request.ReqDea_Tk25 req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getDea_Tk25"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResDea_Tk25) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResDea_Tk25) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResDea_Tk25.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResDea_Wasserbehoerden getDea_Wasserbehoerden(de.nrw.lds.tipi.inka.request.ReqDea_Wasserbehoerden req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getDea_Wasserbehoerden"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResDea_Wasserbehoerden) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResDea_Wasserbehoerden) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResDea_Wasserbehoerden.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResDea_Wz_Code getDea_Wz_Code(de.nrw.lds.tipi.inka.request.ReqDea_Wz_Code req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getDea_Wz_Code"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResDea_Wz_Code) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResDea_Wz_Code) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResDea_Wz_Code.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResDea_Adresse getDea_Adresse(de.nrw.lds.tipi.inka.request.ReqDea_Adresse req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getDea_Adresse"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResDea_Adresse) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResDea_Adresse) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResDea_Adresse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResInka_Anfallstelle getInka_Anfallstelle(de.nrw.lds.tipi.inka.request.ReqInka_Anfallstelle req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getInka_Anfallstelle"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResInka_Anfallstelle) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResInka_Anfallstelle) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResInka_Anfallstelle.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResInka_Anfallst_Anlage getInka_Anfallst_Anlage(de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Anlage req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getInka_Anfallst_Anlage"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResInka_Anfallst_Anlage) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResInka_Anfallst_Anlage) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResInka_Anfallst_Anlage.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResInka_Anfallst_Messst getInka_Anfallst_Messst(de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Messst req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getInka_Anfallst_Messst"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResInka_Anfallst_Messst) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResInka_Anfallst_Messst) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResInka_Anfallst_Messst.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResInka_Anfallst_Stoffe getInka_Anfallst_Stoffe(de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Stoffe req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getInka_Anfallst_Stoffe"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResInka_Anfallst_Stoffe) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResInka_Anfallst_Stoffe) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResInka_Anfallst_Stoffe.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResInka_Anlage getInka_Anlage(de.nrw.lds.tipi.inka.request.ReqInka_Anlage req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getInka_Anlage"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResInka_Anlage) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResInka_Anlage) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResInka_Anlage.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResInka_Betrieb getInka_Betrieb(de.nrw.lds.tipi.inka.request.ReqInka_Betrieb req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getInka_Betrieb"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResInka_Betrieb) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResInka_Betrieb) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResInka_Betrieb.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResInka_Betriebseinrichtung getInka_Betriebseinrichtung(de.nrw.lds.tipi.inka.request.ReqInka_Betriebseinrichtung req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getInka_Betriebseinrichtung"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResInka_Betriebseinrichtung) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResInka_Betriebseinrichtung) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResInka_Betriebseinrichtung.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResInka_Fliessschema getInka_Fliessschema(de.nrw.lds.tipi.inka.request.ReqInka_Fliessschema req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getInka_Fliessschema"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResInka_Fliessschema) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResInka_Fliessschema) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResInka_Fliessschema.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResInka_Genehmigung getInka_Genehmigung(de.nrw.lds.tipi.inka.request.ReqInka_Genehmigung req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getInka_Genehmigung"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResInka_Genehmigung) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResInka_Genehmigung) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResInka_Genehmigung.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResInka_Messstelle getInka_Messstelle(de.nrw.lds.tipi.inka.request.ReqInka_Messstelle req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getInka_Messstelle"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResInka_Messstelle) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResInka_Messstelle) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResInka_Messstelle.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResInka_Messst_Anlage getInka_Messst_Anlage(de.nrw.lds.tipi.inka.request.ReqInka_Messst_Anlage req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[26]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getInka_Messst_Anlage"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResInka_Messst_Anlage) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResInka_Messst_Anlage) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResInka_Messst_Anlage.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResInka_Probenahme getInka_Probenahme(de.nrw.lds.tipi.inka.request.ReqInka_Probenahme req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[27]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getInka_Probenahme"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResInka_Probenahme) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResInka_Probenahme) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResInka_Probenahme.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResInka_Uebergabestelle getInka_Uebergabestelle(de.nrw.lds.tipi.inka.request.ReqInka_Uebergabestelle req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[28]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getInka_Uebergabestelle"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResInka_Uebergabestelle) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResInka_Uebergabestelle) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResInka_Uebergabestelle.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResInka_Ueberwach_Ergebnis getInka_Ueberwach_Ergebnis(de.nrw.lds.tipi.inka.request.ReqInka_Ueberwach_Ergebnis req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[29]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getInka_Ueberwach_Ergebnis"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResInka_Ueberwach_Ergebnis) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResInka_Ueberwach_Ergebnis) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResInka_Ueberwach_Ergebnis.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.response.ResInka_Ueberwachungswert getInka_Ueberwachungswert(de.nrw.lds.tipi.inka.request.ReqInka_Ueberwachungswert req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[30]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "getInka_Ueberwachungswert"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.response.ResInka_Ueberwachungswert) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.response.ResInka_Ueberwachungswert) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.response.ResInka_Ueberwachungswert.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard setDea_Adresse(de.nrw.lds.tipi.inka.request.ReqDea_Adresse req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[31]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "setDea_Adresse"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Anfallstelle(de.nrw.lds.tipi.inka.request.ReqInka_Anfallstelle req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[32]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "setInka_Anfallstelle"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Anfallst_Anlage(de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Anlage req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[33]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "setInka_Anfallst_Anlage"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Anfallst_Messst(de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Messst req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[34]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "setInka_Anfallst_Messst"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Anfallst_Stoffe(de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Stoffe req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[35]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "setInka_Anfallst_Stoffe"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Anlage(de.nrw.lds.tipi.inka.request.ReqInka_Anlage req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[36]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "setInka_Anlage"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Betrieb(de.nrw.lds.tipi.inka.request.ReqInka_Betrieb req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[37]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "setInka_Betrieb"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Betriebseinrichtung(de.nrw.lds.tipi.inka.request.ReqInka_Betriebseinrichtung req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[38]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "setInka_Betriebseinrichtung"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Fliessschema(de.nrw.lds.tipi.inka.request.ReqInka_Fliessschema req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[39]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "setInka_Fliessschema"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Genehmigung(de.nrw.lds.tipi.inka.request.ReqInka_Genehmigung req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[40]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "setInka_Genehmigung"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Messstelle(de.nrw.lds.tipi.inka.request.ReqInka_Messstelle req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[41]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "setInka_Messstelle"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Messst_Anlage(de.nrw.lds.tipi.inka.request.ReqInka_Messst_Anlage req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[42]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "setInka_Messst_Anlage"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Probenahme(de.nrw.lds.tipi.inka.request.ReqInka_Probenahme req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[43]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "setInka_Probenahme"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Uebergabestelle(de.nrw.lds.tipi.inka.request.ReqInka_Uebergabestelle req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[44]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "setInka_Uebergabestelle"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Ueberwach_Ergebnis(de.nrw.lds.tipi.inka.request.ReqInka_Ueberwach_Ergebnis req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[45]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "setInka_Ueberwach_Ergebnis"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard setInka_Ueberwachungswert(de.nrw.lds.tipi.inka.request.ReqInka_Ueberwachungswert req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[46]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "setInka_Ueberwachungswert"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard deleteDea_Adresse(de.nrw.lds.tipi.inka.request.ReqDea_Adresse req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[47]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "deleteDea_Adresse"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Anfallstelle(de.nrw.lds.tipi.inka.request.ReqInka_Anfallstelle req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[48]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "deleteInka_Anfallstelle"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Anfallst_Anlage(de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Anlage req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[49]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "deleteInka_Anfallst_Anlage"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Anfallst_Messst(de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Messst req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[50]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "deleteInka_Anfallst_Messst"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Anfallst_Stoffe(de.nrw.lds.tipi.inka.request.ReqInka_Anfallst_Stoffe req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[51]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "deleteInka_Anfallst_Stoffe"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Anlage(de.nrw.lds.tipi.inka.request.ReqInka_Anlage req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[52]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "deleteInka_Anlage"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Betrieb(de.nrw.lds.tipi.inka.request.ReqInka_Betrieb req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[53]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "deleteInka_Betrieb"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Betriebseinrichtung(de.nrw.lds.tipi.inka.request.ReqInka_Betriebseinrichtung req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[54]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "deleteInka_Betriebseinrichtung"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Fliessschema(de.nrw.lds.tipi.inka.request.ReqInka_Fliessschema req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[55]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "deleteInka_Fliessschema"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Genehmigung(de.nrw.lds.tipi.inka.request.ReqInka_Genehmigung req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[56]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "deleteInka_Genehmigung"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Messstelle(de.nrw.lds.tipi.inka.request.ReqInka_Messstelle req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[57]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "deleteInka_Messstelle"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Messst_Anlage(de.nrw.lds.tipi.inka.request.ReqInka_Messst_Anlage req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[58]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "deleteInka_Messst_Anlage"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Probenahme(de.nrw.lds.tipi.inka.request.ReqInka_Probenahme req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[59]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "deleteInka_Probenahme"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Uebergabestelle(de.nrw.lds.tipi.inka.request.ReqInka_Uebergabestelle req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[60]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "deleteInka_Uebergabestelle"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Ueberwach_Ergebnis(de.nrw.lds.tipi.inka.request.ReqInka_Ueberwach_Ergebnis req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[61]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "deleteInka_Ueberwach_Ergebnis"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public de.nrw.lds.tipi.inka.general.ResStandard deleteInka_Ueberwachungswert(de.nrw.lds.tipi.inka.request.ReqInka_Ueberwachungswert req) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[62]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "deleteInka_Ueberwachungswert"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {req});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (de.nrw.lds.tipi.inka.general.ResStandard) _resp;
            } catch (java.lang.Exception _exception) {
                return (de.nrw.lds.tipi.inka.general.ResStandard) org.apache.axis.utils.JavaUtils.convert(_resp, de.nrw.lds.tipi.inka.general.ResStandard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}

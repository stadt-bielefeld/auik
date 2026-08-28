module auik {
    requires java.desktop;
    requires java.naming;
    requires java.sql;

    requires com.fasterxml.jackson.annotation;
    requires jakarta.ws.rs;
    requires jasperreports;
    requires java.persistence;
    requires jcalendar;
    requires jgoodies.forms;
    requires jgoodies.looks;
    requires jersey.client;
    requires jersey.common;
    requires l2fprod.common.buttonbar;
    requires org.apache.commons.codec;
    requires org.apache.log4j;
    requires org.apache.logging.log4j.core;
    requires org.hibernate.orm.core;
    requires org.jfree.jfreechart;

    exports de.bielefeld.umweltamt.aui;
}

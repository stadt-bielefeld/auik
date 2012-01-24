/**
 * InkaInterfaceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.nrw.lds.tipi.inka.webservice;

public class InkaInterfaceLocator extends org.apache.axis.client.Service implements de.nrw.lds.tipi.inka.webservice.InkaInterface {

    public InkaInterfaceLocator() {
    }


    public InkaInterfaceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public InkaInterfaceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for InkaInterface
    private java.lang.String InkaInterface_address = "http://192.168.232.3:8080/inka_1_60gk/services/InkaInterface";

    public java.lang.String getInkaInterfaceAddress() {
        return InkaInterface_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String InkaInterfaceWSDDServiceName = "InkaInterface";

    public java.lang.String getInkaInterfaceWSDDServiceName() {
        return InkaInterfaceWSDDServiceName;
    }

    public void setInkaInterfaceWSDDServiceName(java.lang.String name) {
        InkaInterfaceWSDDServiceName = name;
    }

    public de.nrw.lds.tipi.inka.webservice.InkaInterfacePortType getInkaInterface() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(InkaInterface_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getInkaInterface(endpoint);
    }

    public de.nrw.lds.tipi.inka.webservice.InkaInterfacePortType getInkaInterface(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            de.nrw.lds.tipi.inka.webservice.InkaInterfaceSoapBindingStub _stub = new de.nrw.lds.tipi.inka.webservice.InkaInterfaceSoapBindingStub(portAddress, this);
            _stub.setPortName(getInkaInterfaceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setInkaInterfaceEndpointAddress(java.lang.String address) {
        InkaInterface_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (de.nrw.lds.tipi.inka.webservice.InkaInterfacePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                de.nrw.lds.tipi.inka.webservice.InkaInterfaceSoapBindingStub _stub = new de.nrw.lds.tipi.inka.webservice.InkaInterfaceSoapBindingStub(new java.net.URL(InkaInterface_address), this);
                _stub.setPortName(getInkaInterfaceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("InkaInterface".equals(inputPortName)) {
            return getInkaInterface();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "InkaInterface");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webservice.inka.tipi.lds.nrw.de", "InkaInterface"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("InkaInterface".equals(portName)) {
            setInkaInterfaceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}

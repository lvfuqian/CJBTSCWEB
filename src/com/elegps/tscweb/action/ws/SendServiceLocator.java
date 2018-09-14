/**
 * SendServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.elegps.tscweb.action.ws;

public class SendServiceLocator extends org.apache.axis.client.Service implements com.elegps.tscweb.action.ws.SendService {

    public SendServiceLocator() {
    }


    public SendServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SendServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SendServiceSoap
    private java.lang.String SendServiceSoap_address = "http://59.34.5.147:8017/SendService.asmx";

    public java.lang.String getSendServiceSoapAddress() {
        return SendServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SendServiceSoapWSDDServiceName = "SendServiceSoap";

    public java.lang.String getSendServiceSoapWSDDServiceName() {
        return SendServiceSoapWSDDServiceName;
    }

    public void setSendServiceSoapWSDDServiceName(java.lang.String name) {
        SendServiceSoapWSDDServiceName = name;
    }

    public com.elegps.tscweb.action.ws.SendServiceSoap getSendServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SendServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSendServiceSoap(endpoint);
    }

    public com.elegps.tscweb.action.ws.SendServiceSoap getSendServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.elegps.tscweb.action.ws.SendServiceSoapStub _stub = new com.elegps.tscweb.action.ws.SendServiceSoapStub(portAddress, this);
            _stub.setPortName(getSendServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSendServiceSoapEndpointAddress(java.lang.String address) {
        SendServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.elegps.tscweb.action.ws.SendServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.elegps.tscweb.action.ws.SendServiceSoapStub _stub = new com.elegps.tscweb.action.ws.SendServiceSoapStub(new java.net.URL(SendServiceSoap_address), this);
                _stub.setPortName(getSendServiceSoapWSDDServiceName());
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
        if ("SendServiceSoap".equals(inputPortName)) {
            return getSendServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://elegps.com/", "SendService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://elegps.com/", "SendServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SendServiceSoap".equals(portName)) {
            setSendServiceSoapEndpointAddress(address);
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

/**
 * SendServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.elegps.tscweb.action.ws;

public interface SendServiceSoap extends java.rmi.Remote {
    public boolean sendSMS(com.elegps.tscweb.action.ws.AccountInfo objAI, com.elegps.tscweb.action.ws.MessageInfo objMI, javax.xml.rpc.holders.StringHolder strErrorMsg) throws java.rmi.RemoteException;
    public java.lang.String getSMS(com.elegps.tscweb.action.ws.AccountInfo objAI, javax.xml.rpc.holders.StringHolder strErrorMsg) throws java.rmi.RemoteException;
    public java.lang.String sendSmsByString(java.lang.String strUnitName, java.lang.String strAccount, java.lang.String strAccountPwd, java.lang.String strAppSystem, java.lang.String strReceiptNo, java.lang.String strMsgContent) throws java.rmi.RemoteException;
}

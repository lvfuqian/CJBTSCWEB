package com.elegps.tscweb.action.ws;

public class SendServiceSoapProxy implements com.elegps.tscweb.action.ws.SendServiceSoap {
  private String _endpoint = null;
  private com.elegps.tscweb.action.ws.SendServiceSoap sendServiceSoap = null;
  
  public SendServiceSoapProxy() {
    _initSendServiceSoapProxy();
  }
  
  public SendServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initSendServiceSoapProxy();
  }
  
  private void _initSendServiceSoapProxy() {
    try {
      sendServiceSoap = (new com.elegps.tscweb.action.ws.SendServiceLocator()).getSendServiceSoap();
      if (sendServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sendServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sendServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sendServiceSoap != null)
      ((javax.xml.rpc.Stub)sendServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.elegps.tscweb.action.ws.SendServiceSoap getSendServiceSoap() {
    if (sendServiceSoap == null)
      _initSendServiceSoapProxy();
    return sendServiceSoap;
  }
  
  public boolean sendSMS(com.elegps.tscweb.action.ws.AccountInfo objAI, com.elegps.tscweb.action.ws.MessageInfo objMI, javax.xml.rpc.holders.StringHolder strErrorMsg) throws java.rmi.RemoteException{
    if (sendServiceSoap == null)
      _initSendServiceSoapProxy();
    return sendServiceSoap.sendSMS(objAI, objMI, strErrorMsg);
  }
  
  public java.lang.String getSMS(com.elegps.tscweb.action.ws.AccountInfo objAI, javax.xml.rpc.holders.StringHolder strErrorMsg) throws java.rmi.RemoteException{
    if (sendServiceSoap == null)
      _initSendServiceSoapProxy();
    return sendServiceSoap.getSMS(objAI, strErrorMsg);
  }
  
  public java.lang.String sendSmsByString(java.lang.String strUnitName, java.lang.String strAccount, java.lang.String strAccountPwd, java.lang.String strAppSystem, java.lang.String strReceiptNo, java.lang.String strMsgContent) throws java.rmi.RemoteException{
    if (sendServiceSoap == null)
      _initSendServiceSoapProxy();
    return sendServiceSoap.sendSmsByString(strUnitName, strAccount, strAccountPwd, strAppSystem, strReceiptNo, strMsgContent);
  }
  
  
}
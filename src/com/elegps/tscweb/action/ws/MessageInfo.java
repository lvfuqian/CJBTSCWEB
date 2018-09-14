/**
 * MessageInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.elegps.tscweb.action.ws;

public class MessageInfo  implements java.io.Serializable {
    private java.lang.String appSystem;

    private java.lang.String GUID;

    private java.lang.String receiptNo;

    private java.lang.String msgType;

    private java.lang.String msgContent;

    private java.lang.String sendTime;

    private int isTimer;

    private java.lang.String senderID;

    private java.lang.String senderName;

    private java.lang.String senderDept;

    public MessageInfo() {
    }

    public MessageInfo(
           java.lang.String appSystem,
           java.lang.String GUID,
           java.lang.String receiptNo,
           java.lang.String msgType,
           java.lang.String msgContent,
           java.lang.String sendTime,
           int isTimer,
           java.lang.String senderID,
           java.lang.String senderName,
           java.lang.String senderDept) {
           this.appSystem = appSystem;
           this.GUID = GUID;
           this.receiptNo = receiptNo;
           this.msgType = msgType;
           this.msgContent = msgContent;
           this.sendTime = sendTime;
           this.isTimer = isTimer;
           this.senderID = senderID;
           this.senderName = senderName;
           this.senderDept = senderDept;
    }


    /**
     * Gets the appSystem value for this MessageInfo.
     * 
     * @return appSystem
     */
    public java.lang.String getAppSystem() {
        return appSystem;
    }


    /**
     * Sets the appSystem value for this MessageInfo.
     * 
     * @param appSystem
     */
    public void setAppSystem(java.lang.String appSystem) {
        this.appSystem = appSystem;
    }


    /**
     * Gets the GUID value for this MessageInfo.
     * 
     * @return GUID
     */
    public java.lang.String getGUID() {
        return GUID;
    }


    /**
     * Sets the GUID value for this MessageInfo.
     * 
     * @param GUID
     */
    public void setGUID(java.lang.String GUID) {
        this.GUID = GUID;
    }


    /**
     * Gets the receiptNo value for this MessageInfo.
     * 
     * @return receiptNo
     */
    public java.lang.String getReceiptNo() {
        return receiptNo;
    }


    /**
     * Sets the receiptNo value for this MessageInfo.
     * 
     * @param receiptNo
     */
    public void setReceiptNo(java.lang.String receiptNo) {
        this.receiptNo = receiptNo;
    }


    /**
     * Gets the msgType value for this MessageInfo.
     * 
     * @return msgType
     */
    public java.lang.String getMsgType() {
        return msgType;
    }


    /**
     * Sets the msgType value for this MessageInfo.
     * 
     * @param msgType
     */
    public void setMsgType(java.lang.String msgType) {
        this.msgType = msgType;
    }


    /**
     * Gets the msgContent value for this MessageInfo.
     * 
     * @return msgContent
     */
    public java.lang.String getMsgContent() {
        return msgContent;
    }


    /**
     * Sets the msgContent value for this MessageInfo.
     * 
     * @param msgContent
     */
    public void setMsgContent(java.lang.String msgContent) {
        this.msgContent = msgContent;
    }


    /**
     * Gets the sendTime value for this MessageInfo.
     * 
     * @return sendTime
     */
    public java.lang.String getSendTime() {
        return sendTime;
    }


    /**
     * Sets the sendTime value for this MessageInfo.
     * 
     * @param sendTime
     */
    public void setSendTime(java.lang.String sendTime) {
        this.sendTime = sendTime;
    }


    /**
     * Gets the isTimer value for this MessageInfo.
     * 
     * @return isTimer
     */
    public int getIsTimer() {
        return isTimer;
    }


    /**
     * Sets the isTimer value for this MessageInfo.
     * 
     * @param isTimer
     */
    public void setIsTimer(int isTimer) {
        this.isTimer = isTimer;
    }


    /**
     * Gets the senderID value for this MessageInfo.
     * 
     * @return senderID
     */
    public java.lang.String getSenderID() {
        return senderID;
    }


    /**
     * Sets the senderID value for this MessageInfo.
     * 
     * @param senderID
     */
    public void setSenderID(java.lang.String senderID) {
        this.senderID = senderID;
    }


    /**
     * Gets the senderName value for this MessageInfo.
     * 
     * @return senderName
     */
    public java.lang.String getSenderName() {
        return senderName;
    }


    /**
     * Sets the senderName value for this MessageInfo.
     * 
     * @param senderName
     */
    public void setSenderName(java.lang.String senderName) {
        this.senderName = senderName;
    }


    /**
     * Gets the senderDept value for this MessageInfo.
     * 
     * @return senderDept
     */
    public java.lang.String getSenderDept() {
        return senderDept;
    }


    /**
     * Sets the senderDept value for this MessageInfo.
     * 
     * @param senderDept
     */
    public void setSenderDept(java.lang.String senderDept) {
        this.senderDept = senderDept;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MessageInfo)) return false;
        MessageInfo other = (MessageInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.appSystem==null && other.getAppSystem()==null) || 
             (this.appSystem!=null &&
              this.appSystem.equals(other.getAppSystem()))) &&
            ((this.GUID==null && other.getGUID()==null) || 
             (this.GUID!=null &&
              this.GUID.equals(other.getGUID()))) &&
            ((this.receiptNo==null && other.getReceiptNo()==null) || 
             (this.receiptNo!=null &&
              this.receiptNo.equals(other.getReceiptNo()))) &&
            ((this.msgType==null && other.getMsgType()==null) || 
             (this.msgType!=null &&
              this.msgType.equals(other.getMsgType()))) &&
            ((this.msgContent==null && other.getMsgContent()==null) || 
             (this.msgContent!=null &&
              this.msgContent.equals(other.getMsgContent()))) &&
            ((this.sendTime==null && other.getSendTime()==null) || 
             (this.sendTime!=null &&
              this.sendTime.equals(other.getSendTime()))) &&
            this.isTimer == other.getIsTimer() &&
            ((this.senderID==null && other.getSenderID()==null) || 
             (this.senderID!=null &&
              this.senderID.equals(other.getSenderID()))) &&
            ((this.senderName==null && other.getSenderName()==null) || 
             (this.senderName!=null &&
              this.senderName.equals(other.getSenderName()))) &&
            ((this.senderDept==null && other.getSenderDept()==null) || 
             (this.senderDept!=null &&
              this.senderDept.equals(other.getSenderDept())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAppSystem() != null) {
            _hashCode += getAppSystem().hashCode();
        }
        if (getGUID() != null) {
            _hashCode += getGUID().hashCode();
        }
        if (getReceiptNo() != null) {
            _hashCode += getReceiptNo().hashCode();
        }
        if (getMsgType() != null) {
            _hashCode += getMsgType().hashCode();
        }
        if (getMsgContent() != null) {
            _hashCode += getMsgContent().hashCode();
        }
        if (getSendTime() != null) {
            _hashCode += getSendTime().hashCode();
        }
        _hashCode += getIsTimer();
        if (getSenderID() != null) {
            _hashCode += getSenderID().hashCode();
        }
        if (getSenderName() != null) {
            _hashCode += getSenderName().hashCode();
        }
        if (getSenderDept() != null) {
            _hashCode += getSenderDept().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MessageInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://elegps.com/", "MessageInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appSystem");
        elemField.setXmlName(new javax.xml.namespace.QName("http://elegps.com/", "AppSystem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("GUID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://elegps.com/", "GUID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receiptNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://elegps.com/", "ReceiptNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://elegps.com/", "MsgType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgContent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://elegps.com/", "MsgContent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://elegps.com/", "SendTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isTimer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://elegps.com/", "IsTimer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://elegps.com/", "SenderID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://elegps.com/", "SenderName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderDept");
        elemField.setXmlName(new javax.xml.namespace.QName("http://elegps.com/", "SenderDept"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}

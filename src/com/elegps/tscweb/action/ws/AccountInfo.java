/**
 * AccountInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.elegps.tscweb.action.ws;

public class AccountInfo  implements java.io.Serializable {
    private java.lang.String appUnit;

    private java.lang.String appUnitName;

    private java.lang.String accountNO;

    private java.lang.String accountPWD;

    public AccountInfo() {
    }

    public AccountInfo(
           java.lang.String appUnit,
           java.lang.String appUnitName,
           java.lang.String accountNO,
           java.lang.String accountPWD) {
           this.appUnit = appUnit;
           this.appUnitName = appUnitName;
           this.accountNO = accountNO;
           this.accountPWD = accountPWD;
    }


    /**
     * Gets the appUnit value for this AccountInfo.
     * 
     * @return appUnit
     */
    public java.lang.String getAppUnit() {
        return appUnit;
    }


    /**
     * Sets the appUnit value for this AccountInfo.
     * 
     * @param appUnit
     */
    public void setAppUnit(java.lang.String appUnit) {
        this.appUnit = appUnit;
    }


    /**
     * Gets the appUnitName value for this AccountInfo.
     * 
     * @return appUnitName
     */
    public java.lang.String getAppUnitName() {
        return appUnitName;
    }


    /**
     * Sets the appUnitName value for this AccountInfo.
     * 
     * @param appUnitName
     */
    public void setAppUnitName(java.lang.String appUnitName) {
        this.appUnitName = appUnitName;
    }


    /**
     * Gets the accountNO value for this AccountInfo.
     * 
     * @return accountNO
     */
    public java.lang.String getAccountNO() {
        return accountNO;
    }


    /**
     * Sets the accountNO value for this AccountInfo.
     * 
     * @param accountNO
     */
    public void setAccountNO(java.lang.String accountNO) {
        this.accountNO = accountNO;
    }


    /**
     * Gets the accountPWD value for this AccountInfo.
     * 
     * @return accountPWD
     */
    public java.lang.String getAccountPWD() {
        return accountPWD;
    }


    /**
     * Sets the accountPWD value for this AccountInfo.
     * 
     * @param accountPWD
     */
    public void setAccountPWD(java.lang.String accountPWD) {
        this.accountPWD = accountPWD;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AccountInfo)) return false;
        AccountInfo other = (AccountInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.appUnit==null && other.getAppUnit()==null) || 
             (this.appUnit!=null &&
              this.appUnit.equals(other.getAppUnit()))) &&
            ((this.appUnitName==null && other.getAppUnitName()==null) || 
             (this.appUnitName!=null &&
              this.appUnitName.equals(other.getAppUnitName()))) &&
            ((this.accountNO==null && other.getAccountNO()==null) || 
             (this.accountNO!=null &&
              this.accountNO.equals(other.getAccountNO()))) &&
            ((this.accountPWD==null && other.getAccountPWD()==null) || 
             (this.accountPWD!=null &&
              this.accountPWD.equals(other.getAccountPWD())));
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
        if (getAppUnit() != null) {
            _hashCode += getAppUnit().hashCode();
        }
        if (getAppUnitName() != null) {
            _hashCode += getAppUnitName().hashCode();
        }
        if (getAccountNO() != null) {
            _hashCode += getAccountNO().hashCode();
        }
        if (getAccountPWD() != null) {
            _hashCode += getAccountPWD().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AccountInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://elegps.com/", "AccountInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appUnit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://elegps.com/", "AppUnit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appUnitName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://elegps.com/", "AppUnitName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://elegps.com/", "AccountNO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountPWD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://elegps.com/", "AccountPWD"));
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

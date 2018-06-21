package org.tempuri;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.2
 * 2015-10-13T14:16:26.623+08:00
 * Generated source version: 3.1.2
 * 
 */
@WebService(targetNamespace = "http://tempuri.org/", name = "WRServiceSoap")
@XmlSeeAlso({ObjectFactory.class})
public interface WRServiceSoap {

    /**
     * 根据营业执照号、销售日期段获取购买记录信息
     */
    @WebResult(name = "GetInfoListResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "GetInfoList", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetInfoList")
    @WebMethod(operationName = "GetInfoList", action = "http://tempuri.org/GetInfoList")
    @ResponseWrapper(localName = "GetInfoListResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetInfoListResponse")
    public java.lang.String getInfoList(
        @WebParam(name = "SysName", targetNamespace = "http://tempuri.org/")
        java.lang.String sysName,
        @WebParam(name = "SysPass", targetNamespace = "http://tempuri.org/")
        java.lang.String sysPass,
        @WebParam(name = "CorpNo", targetNamespace = "http://tempuri.org/")
        java.lang.String corpNo,
        @WebParam(name = "STime", targetNamespace = "http://tempuri.org/")
        java.lang.String sTime,
        @WebParam(name = "ETime", targetNamespace = "http://tempuri.org/")
        java.lang.String eTime
    );
}

package com.grandland.qdhx.client.app;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "SupplyChainService", targetNamespace = "http://service.webservice.emanager.ylwj.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface SupplyChainService {


    /**
     * 
     * @param code
     * @param messageType
     * @param titlePicture
     * @param businessId
     * @param messageId
     * @param title
     * @param content
     * @return
     *     returns com.grandland.qdhx.client.app.Result
     */
    @WebMethod
    @WebResult(partName = "return")
    public Result pushSupplyChainInfo(
            @WebParam(name = "messageId", partName = "messageId")
            String messageId,
            @WebParam(name = "businessId", partName = "businessId")
            String businessId,
            @WebParam(name = "code", partName = "code")
            String code,
            @WebParam(name = "title", partName = "title")
            String title,
            @WebParam(name = "titlePicture", partName = "titlePicture")
            String titlePicture,
            @WebParam(name = "content", partName = "content")
            String content,
            @WebParam(name = "messageType", partName = "messageType")
            String messageType);

}
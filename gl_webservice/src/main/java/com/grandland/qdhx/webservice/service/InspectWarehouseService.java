package com.grandland.qdhx.webservice.service;

import com.grandland.qdhx.webservice.bean.OperationResult;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

/**
 * 巡核库回传情况
 */
@WebService
@XmlSeeAlso({ArrayList.class})
public interface InspectWarehouseService {


    OperationResult inspection(@WebParam(name = "messageId") String messageId,
                               @WebParam(name = "businessId") String businessId,
                               @WebParam(name = "userId") String userId,
                               @WebParam(name = "corporate") String corporate,
                               @WebParam(name = "address") String address,
                               @WebParam(name = "location") String location,
                               @WebParam(name = "locIsAccurate") boolean locIsAccurate,
                               @WebParam(name = "inspectTime") String inspectTime,
                               @WebParam(name = "imgs") List<String> imgs,
                               @WebParam(name = "extra") String extra);


    /**
     * 获取公司(借款人名称)字典
     * @return
     */
    List<String> getCorporateDic();


    /**
     * 获取存货地址字典
     * @return
     */
    List<String> getAddressDic();
}

package com.grandland.qdhx.webservice.service;

import com.grandland.qdhx.webservice.bean.OperationResult;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 消息查看回执
 */
@WebService
public interface MessageViewService {

    OperationResult view(@WebParam(name = "businessId") String businessId,
                         @WebParam(name = "messageId") String messageId,
                         @WebParam(name = "type") String type,
                         @WebParam(name = "userId") String userId,
                         @WebParam(name = "viewTime") String viewTime);

}

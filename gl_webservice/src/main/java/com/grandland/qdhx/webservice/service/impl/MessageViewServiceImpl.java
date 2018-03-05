package com.grandland.qdhx.webservice.service.impl;

import javax.jws.WebParam;

import com.grandland.qdhx.webservice.bean.OperationResult;
import com.grandland.qdhx.webservice.service.MessageViewService;

/**
 * 消息查看回执实现
 */
@javax.jws.WebService(endpointInterface = "com.grandland.qdhx.webservice.service.MessageViewService")
public class MessageViewServiceImpl implements MessageViewService {
//    private static final Logger LOG = LoggerFactory.getLogger(MessageViewServiceImpl.class);

    @Override
    public OperationResult view(@WebParam(name = "businessId") String businessId,
                                @WebParam(name = "messageId") String messageId,
                                @WebParam(name = "type") String type,
                                @WebParam(name = "userId") String userId,
                                @WebParam(name = "viewTime") String viewTime) {

        OperationResult result = new OperationResult(true, "ok");
        return result;
    }
}

package com.grandland.qdhx.webservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.grandland.qdhx.webservice.bean.OperationResult;
import com.grandland.qdhx.webservice.service.InspectWarehouseService;

/**
 * 巡核库信息回传实现
 */
public class InspectWarehourceServiceImpl implements InspectWarehouseService {
//    private static final Logger LOG = LoggerFactory.getLogger(InspectWarehourceServiceImpl.class);

    @Override
    public OperationResult inspection(String messageId, String businessId, String userId, String corporate, String address, String location, boolean locIsAccurate, String inspectTime, List<String> imgs, String extra) {
        OperationResult result = new OperationResult(true, "ok");
        return result;
    }

    @Override
    public List<String> getCorporateDic() {
        List<String> list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
        return list;
    }

    @Override
    public List<String> getAddressDic() {
        List<String> list = new ArrayList<>();
        list.add("test2");
        list.add("test4");
        return list;
    }


}

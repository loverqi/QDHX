package com.grandland.qdhx.client.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 提示消息推送
 */
public class PushClient {
    private static final Logger LOG = LoggerFactory.getLogger(PushClient.class);

    private static SupplyChainService client = null;

    public static boolean pushRemidMsg(String messageId,
                                     String businessId,
                                     String messageType,
                                     String userId,
                                     String remindMsg,
                                     String detailHtml) {
        LOG.info("will push remiding msg, messageId:{}, besinessId:{}, messageType:{}, userId:{}, remindMsg:{}",
                                                    messageId, businessId, messageType, userId, remindMsg);
        SupplyChainService client = getClient();
        Result result = client.pushSupplyChainInfo(messageId, businessId, userId,
                                                   remindMsg, null, detailHtml, messageType);
        if (result.getCode() != 1) {
            LOG.error("[E]push msg failed![{}-{}-{}] code:{}, message:{}", businessId, messageId, messageType, result.getCode(), result.getMessage());
            return false;
        }
        LOG.info("push msg succeed![{}-{}-{}]", businessId, messageId, messageType);
        return true;
    }

    public static SupplyChainService getClient() {
        if (client == null) {
            SupplyChainServiceImplService impl = new SupplyChainServiceImplService();
            client = impl.getSupplyChainServiceImplPort();
        }
        return client;
    }

    public static void main(String[] args) {
//        boolean b = pushRemidMsg("1", "23", "1", "510203", "test", "testHtml");
    }
}

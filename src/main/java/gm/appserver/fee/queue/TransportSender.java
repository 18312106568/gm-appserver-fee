package gm.appserver.fee.queue;

import gm.appserver.fee.constant.QueueConstant;
import gm.common.utils.GsonUtils;
import gm.facade.fee.entity.wms.TransportBase;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@org.springframework.stereotype.Service
@Service(version = "${gm.appserver.fee.version}",
        timeout = 600000)
public class TransportSender implements gm.facade.fee.service.appserver.TransportSender {

    @Value("${dlxmq.message.expiration}")
    String expiration;

    @Autowired
    private AmqpTemplate template;

    public void sendTransportBase(TransportBase transportBase) {
        //1.主题交换机模式
        template.convertAndSend(QueueConstant.EXCHANGE_NAME,QueueConstant.QUEUE_NAME, GsonUtils.getGson().toJson(transportBase), message -> {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            message.getMessageProperties().setExpiration(expiration);
            return  message;
        });
    }
}

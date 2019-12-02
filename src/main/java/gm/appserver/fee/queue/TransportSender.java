package gm.appserver.fee.queue;

import gm.appserver.fee.constant.QueueConstant;
import gm.facade.fee.entity.wms.TransportBase;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransportSender {

    @Autowired
    private AmqpTemplate template;

    public void sendTransportBase(TransportBase transportBase) {
        //1.主题交换机模式
        template.convertAndSend(QueueConstant.EXCHANGE_NAME,QueueConstant.QUEUE_NAME,transportBase);
    }
}

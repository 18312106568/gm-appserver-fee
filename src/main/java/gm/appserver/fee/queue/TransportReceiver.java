package gm.appserver.fee.queue;

import gm.appserver.fee.constant.QueueConstant;
import gm.common.base.annotation.ParamterLog;
import gm.facade.fee.entity.wms.TransportBase;
import gm.facade.fee.service.TransportBaseHangUpService;
import gm.facade.fee.service.TransportBaseService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransportReceiver {

    @Reference(version = "1.0.0")
    TransportBaseHangUpService hangUpService;

    @ParamterLog
    @RabbitListener(bindings = {@QueueBinding(
            value=@org.springframework.amqp.rabbit.annotation.Queue(QueueConstant.QUEUE_NAME),
            exchange = @org.springframework.amqp.rabbit.annotation.Exchange(value = QueueConstant.EXCHANGE_NAME,type = "topic"),
            key = QueueConstant.QUEUE_NAME)})
    public void process(TransportBase transportBase) {
        hangUpService.hangUpTransportBase(transportBase);
    }

    /*@RabbitListener(bindings = {@QueueBinding(
            value=@org.springframework.amqp.rabbit.annotation.Queue(QueueConstant.QUEUE_NAME),
            exchange = @org.springframework.amqp.rabbit.annotation.Exchange(value = QueueConstant.EXCHANGE_NAME,type = "topic"),
            key = QueueConstant.QUEUE_NAME)})
    public void processA(String str) {
        System.out.println("ReceiveA:" + str);
    }*/
}

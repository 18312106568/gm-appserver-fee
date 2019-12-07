package gm.appserver.fee.queue;

import com.rabbitmq.client.Channel;
import gm.appserver.fee.constant.QueueConstant;
import gm.common.base.annotation.ParamterLog;
import gm.common.utils.GsonUtils;
import gm.facade.fee.entity.wms.TransportBase;
import gm.facade.fee.service.TransportBaseHangUpService;
import gm.facade.fee.service.TransportBaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

@Slf4j
@Component
public class TransportReceiver {

    @Reference(version = "1.0.0")
    TransportBaseHangUpService hangUpService;


    @Autowired
    private  RabbitTemplate rabbitTemplate;

    /*@RabbitListener(bindings = {@QueueBinding(
            value=@org.springframework.amqp.rabbit.annotation.Queue(QueueConstant.QUEUE_NAME),
            exchange = @org.springframework.amqp.rabbit.annotation.Exchange(value = QueueConstant.EXCHANGE_NAME,type = "topic"),
            key = QueueConstant.QUEUE_NAME)})
    public void processA(String str) {
        System.out.println("ReceiveA:" + str);
    }*/

    @ParamterLog
//    @RabbitListener(bindings = {@QueueBinding(
//            value=@org.springframework.amqp.rabbit.annotation.Queue(QueueConstant.QUEUE_NAME),
//            exchange = @org.springframework.amqp.rabbit.annotation.Exchange(value = QueueConstant.EXCHANGE_NAME,type = "topic"),
//            key = QueueConstant.QUEUE_NAME)})
    @RabbitListener(queues = QueueConstant.QUEUE_NAME)
    public void receive(String data) {
        TransportBase transportBase =
                GsonUtils.getGson().fromJson(data ,TransportBase.class);
        hangUpService.hangUpTransportBase(transportBase);
    }

    @RabbitListener(queues = QueueConstant.DLX_QUEUE_NAME)
    public void dtlReceive(Message message, Channel channel) throws IOException {
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(message.getBody());// 创建ByteArrayInputStream对象
//        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);// 创建ObjectInputStream对象
//        Object object = objectInputStream.readObject();
        String data = new String( message.getBody());
        TransportBase transportBase =
                GsonUtils.getGson().fromJson(data ,TransportBase.class);
        hangUpService.hangUpTransportBase(transportBase);

    }
}

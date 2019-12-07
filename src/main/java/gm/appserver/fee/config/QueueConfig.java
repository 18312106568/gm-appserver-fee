package gm.appserver.fee.config;

import gm.appserver.fee.constant.QueueConstant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class QueueConfig {

    /**
     * 队列
     * @return
     */
//    @Bean
//    public Queue queue() {
//        return new Queue(QueueConstant.QUEUE_NAME);
//    }

    /**
     * 定义交换机
     * @return
     */
    @Bean
    public DirectExchange topicExchange() {
        return new DirectExchange(QueueConstant.EXCHANGE_NAME);
    }

    @Bean
    public DirectExchange dlxExchange(){
        return new DirectExchange(QueueConstant.DLX_EXCHANGE_NAME);
    }

    @Bean
    public Queue dlxQueue(){
        return new Queue(QueueConstant.DLX_QUEUE_NAME);
    }

    @Bean
    public Binding dtlBinding(){
        return BindingBuilder.bind(dlxQueue())
                .to(dlxExchange()).withQueueName();
    }



    /**
     * 初始队列绑定死信队列
     * @return Queue
     */
    @Bean
    public Queue queue() {
        Map<String,Object> arguments = new HashMap<>();
        // 绑定该队列到私信交换机
        arguments.put("x-dead-letter-exchange",QueueConstant.DLX_EXCHANGE_NAME);
        arguments.put("x-dead-letter-routing-key",QueueConstant.DLX_QUEUE_NAME);
        return new Queue(QueueConstant.QUEUE_NAME,true,false,false,arguments);
    }



    /**
     * 绑定签收单队列到签收单交换机
     * @return Binding
     */
    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(queue())
                .to(topicExchange())
                .withQueueName();

    }

}

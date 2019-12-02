package gm.appserver.fee.config;

import gm.appserver.fee.constant.QueueConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    /**
     * 队列
     * @return
     */
    @Bean
    public Queue queue() {
        return new Queue(QueueConstant.QUEUE_NAME);
    }

    /**
     * 定义交换机
     * @return
     */
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(QueueConstant.EXCHANGE_NAME);
    }
}

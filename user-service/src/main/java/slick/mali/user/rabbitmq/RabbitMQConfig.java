package slick.mali.user.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    /**
     * RabbitMQ host
     */
    @Value("${spring.rabbitmq.host}")
    private String host;

    /**
     * RabbitMQ port
     */
    @Value("${spring.rabbitmq.port}")
    private String port;

    /**
     * RabbitMQ username
     */
    @Value("${spring.rabbitmq.username}")
    private String username;

    /**
     * RabbitMQ password
     */
    @Value("${spring.rabbitmq.password}")
    private String password;

    /**
     * 
     * Login queue name
     */
    private static final String LOGIN_QUEUE = "mali.user-service.login-queue";

    /**
     * Create login queue
     * @return Queue
     */
    @Bean
    Queue loginQueue() {
        return new Queue(LOGIN_QUEUE, true);
    }

    /**
     * Persist the RabbitMQ connection thru caching
     * @return ConnectionFactory
     */
    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }

    /**
     * Create a message listener container
     * @return simpleMessageListenerContainer
     */
    @Bean
    MessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setQueues(loginQueue());
        simpleMessageListenerContainer.setMessageListener(new LoginQueueMessageListener());
        return simpleMessageListenerContainer;
    }
}
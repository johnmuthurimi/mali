package slick.mali.userservice.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
 
@Configuration
public class RabbitConfig implements RabbitListenerConfigurer 
{
    public static final String QUEUE_EMAIL_VERIFICATION= "email_verification-queue";
    public static final String EXCHANGE_EMAIL_VERIFICATION= "email_verification-exchange";
    public static final String QUEUE_DEAD_EMAIL_VERIFICATION = "dead-email_verification-queue";

    @Bean
    Queue dataQueue() {
        return QueueBuilder.durable(QUEUE_EMAIL_VERIFICATION)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", QUEUE_DEAD_EMAIL_VERIFICATION)
                .withArgument("x-message-ttl", 15000)
                .build();
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(QUEUE_DEAD_EMAIL_VERIFICATION).build();
    }
 
    @Bean
    Exchange dataExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_EMAIL_VERIFICATION).build();
    }
 
    @Bean
    Binding binding(Queue dataQueue, TopicExchange dataExchange) {
        return BindingBuilder.bind(dataQueue).to(dataExchange).with(QUEUE_EMAIL_VERIFICATION);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }
 
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    @Bean
    MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
        messageHandlerMethodFactory.setMessageConverter(consumerJackson2MessageConverter());
        return messageHandlerMethodFactory;
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }
}
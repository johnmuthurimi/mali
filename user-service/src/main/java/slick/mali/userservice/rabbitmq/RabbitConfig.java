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
 
@Configuration
public class RabbitConfig  
{
    public static final String QUEUE_OTP= "otp-queue";
    public static final String EXCHANGE_OTP= "otp-exchange";
 
    @Bean
    Queue otpQueue() {
        return QueueBuilder.durable(QUEUE_OTP).build();
    }
 
    @Bean
    Exchange otpExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_OTP).build();
    }
 
    @Bean
    Binding binding(Queue otpQueue, TopicExchange otpExchange) {
        return BindingBuilder.bind(otpQueue).to(otpExchange).with(QUEUE_OTP);
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
}
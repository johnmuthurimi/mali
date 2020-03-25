package slick.mali.userservice.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slick.mali.userservice.model.EventRequest;
 
@Service
public class OTPMessageSender {
    /**
     * Inject the rabbit template
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Autowired
    private ObjectMapper objectMapper;
 
    /**
     * Send the OTP
     * @param event
     */
    public void sendOTP(EventRequest event) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_OTP, event);
        try {
            String orderJson = objectMapper.writeValueAsString(event);
            Message message = MessageBuilder
                                .withBody(orderJson.getBytes())
                                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                                .build();
            this.rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_OTP, message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
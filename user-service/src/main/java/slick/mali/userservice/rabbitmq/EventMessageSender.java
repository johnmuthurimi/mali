package slick.mali.userservice.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slick.mali.coreservice.model.EventRequest;
 
/**
 * This class is responsible for sending messages to Rabbit MQ
 */
@Service
public class EventMessageSender {
    /**
     * Inject the rabbit template
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    /**
     * Create a object mapper to JSON
     */
    @Autowired
    private ObjectMapper objectMapper;
 
    /**
     * Send the message to Rabbit MQ
     * @param event
     * @param queue
     */
    public void sendMessage(EventRequest event, String queue) {
        this.rabbitTemplate.convertAndSend(queue, event);
        try {
            String jsonData = objectMapper.writeValueAsString(event);
            Message message = MessageBuilder
                                .withBody(jsonData.getBytes())
                                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                                .build();
            this.rabbitTemplate.convertAndSend(queue, message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
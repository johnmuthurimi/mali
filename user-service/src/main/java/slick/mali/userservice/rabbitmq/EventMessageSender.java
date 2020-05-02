package slick.mali.userservice.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slick.mali.coreservice.model.EventRequest;
import slick.mali.coreservice.util.LoggerUtil;

/**
 * This class is responsible for sending messages to Rabbit MQ
 */
@Service
public class EventMessageSender {

    public  static final Logger LOGGER = LoggerFactory.getLogger(EventMessageSender.class);

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
        LoggerUtil.info(LOGGER, "Initiate RabbitMQ for OTP with  " + event.getEmail());
        this.rabbitTemplate.convertAndSend(queue, event);
        try {
            String jsonData = objectMapper.writeValueAsString(event);
            Message message = MessageBuilder
                                .withBody(jsonData.getBytes())
                                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                                .build();
            this.rabbitTemplate.convertAndSend(queue, message);
            LoggerUtil.info(LOGGER, "RabbitMQ for OTP with  " + event.getEmail() + " was successfully sent");
        } catch (JsonProcessingException e) {
            LoggerUtil.info(LOGGER, "Initiate RabbitMQ for OTP with  " + event.getEmail() + " Failed");
            LoggerUtil.error(LOGGER, e.getMessage());
        }
    }
}
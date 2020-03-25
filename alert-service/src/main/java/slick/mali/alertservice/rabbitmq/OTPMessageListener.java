package slick.mali.alertservice.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import slick.mali.alertservice.model;

@Component
public class OTPMessageListener {

    static final Logger logger = LoggerFactory.getLogger(OTPMessageListener.class);

    /**
     * Receive OTP message
     */
    @RabbitListener(queues = RabbitConfig.QUEUE_OTP)
    public void processOTP(EventResponse res) {
        logger.info("Event Received: "+res);
    }
}
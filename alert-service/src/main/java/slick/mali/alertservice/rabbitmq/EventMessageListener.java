package slick.mali.alertservice.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import slick.mali.alertservice.service.IAlertService;
import slick.mali.coreservice.model.EventResponse;

@Component
public class EventMessageListener {

    static final Logger logger = LoggerFactory.getLogger(EventMessageListener.class);

    @Autowired
    private IAlertService alertService;

    /**
     * Receive the Message from Rabbit MQ
     * This function should be overidden in the implementation class
     */
    @RabbitListener(queues = RabbitConfig.QUEUE_OTP)
    public void processMessage(EventResponse res) {
        logger.info("RabbitMQ Event Received: "+res);
        alertService.createOTPNotification(res);
    }
}
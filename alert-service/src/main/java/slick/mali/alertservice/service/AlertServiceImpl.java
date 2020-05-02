package slick.mali.alertservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import slick.mali.alertservice.dao.AlertDao;
import slick.mali.alertservice.utility.EmailTemplate;
import slick.mali.coreservice.constants.AlertType;
import slick.mali.coreservice.model.EventResponse;
import slick.mali.coreservice.model.alert.EmailRequest;
import slick.mali.coreservice.util.LoggerUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation for all alert operations
 */
@Service
public class AlertServiceImpl implements IAlertService {

    public  static final Logger LOGGER = LoggerFactory.getLogger(AlertServiceImpl.class);

    /**
     * Environment variable
     */
    @Autowired
    private Environment env;

     /**
     * AlertDao variable
     */
    @Autowired
    private AlertDao alertDao;

    /**
    * Insert newly created email notification
    * @param request
    */
    @Override
    public String createOTPNotification(EventResponse request) {
        String id = null;
        try {
            LoggerUtil.info(LOGGER, "creating email OTP for recipient: " + request.getEmail());
            String sender = env.getProperty("spring.mail.username");

            String message = generateMessage(request, AlertType.OTP);
            EmailRequest req = new EmailRequest();
            req.setSender(sender);
            req.setRecepient(request.getEmail());
            req.setMessage(message);

            id = alertDao.createNewEmailNotification(req);
            LoggerUtil.info(LOGGER, "Email OTP for recipient: " + request.getEmail() + " successfully created");

        } catch (Exception e) {
            LoggerUtil.info(LOGGER, "Email OTP for recipient: " + request.getEmail() + " Failed");
            LoggerUtil.error(LOGGER, e.getMessage());
        }

        return id;
    }


    /**
    * Returns a List of queue emails for sending
    * @return List<EmailRequest>
    */
    public List<EmailRequest> getQueuedEmailBatch() {
        return alertDao.getQueuedEmailBatch();
    }

    /**
     * Update the new batch after email service
     * @param list email List
     * @return list of int
     */
    public int[] updateQueuedEmailBatch(List<EmailRequest> list) {
        return alertDao.updateQueuedEmailBatch(list);
    }

    /**
     * Generate message type depending on alert type
     * @param request
     * @param alertType
     * @return
     */
    private String generateMessage(EventResponse request, int alertType) {
        Map<String,String> replacements = new HashMap<String,String>();
        String message;
        switch (alertType) {
            case 0:
                EmailTemplate template = new EmailTemplate("SendOtp.html");
                replacements.put("user", request.getFirstName());
                replacements.put("otp",  request.getOtp());
                message = template.getTemplate(replacements);
                LoggerUtil.info(LOGGER, "Email OTP message generated for recipient: " + request.getEmail());
                break;
            default:
                LoggerUtil.error(LOGGER, "Email OTP alert type does not exist");
                throw new IllegalStateException("Email OTP alert type does not exist: " + alertType);
        }
        return message;
    }

}
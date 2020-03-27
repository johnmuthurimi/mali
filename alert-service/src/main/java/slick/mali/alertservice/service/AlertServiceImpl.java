package slick.mali.alertservice.service;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slick.mali.coreservice.model.EventResponse;
import slick.mali.coreservice.model.alert.EmailRequest;
import slick.mali.alertservice.dao.AlertDao;

/**
 * Implementation for all alert operations
 */
@Service
public class AlertServiceImpl implements IAlertService {

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
    public String createNewEmailNotification(EventResponse request) {
        String sender = env.getProperty("spring.mail.username");
        EmailRequest req = new EmailRequest();
        req.setSender(sender);
        req.setRecepient(request.getEmail());
        req.setMessage("Test");
        return alertDao.createNewEmailNotification(req);
    }


}
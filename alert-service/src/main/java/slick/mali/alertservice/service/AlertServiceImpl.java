package slick.mali.alertservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import slick.mali.alertservice.dao.AlertDao;
import slick.mali.coreservice.model.EventResponse;
import slick.mali.coreservice.model.alert.EmailRequest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Implementation for all alert operations
 */
@Service
public class AlertServiceImpl implements IAlertService {

    @Value("classpath:email-template/otp.txt")
    Resource resourceFile;

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
     * emailService injection
     */
    @Autowired
    EmailService emailService;

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

        Resource resource = new ClassPathResource("emailTemplate/otp.html");
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            String data = new String(bdata, StandardCharsets.UTF_8);

            req.setMessage(data);
        } catch (IOException e) {
        }


        return alertDao.createNewEmailNotification(req);
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
    */
    public int[] updateQueuedEmailBatch(List<EmailRequest> list) {
        return alertDao.updateQueuedEmailBatch(list);
    }

    /**
     * Send emails 
     */
    public void sendEmail() {
        List<EmailRequest> queue = getQueuedEmailBatch();
        String subject = env.getProperty("spring.mail.subject");
        for (int i = 0; i < queue.size(); i++) {
            emailService.sendEmail(queue.get(i), subject);
        }
    }
}
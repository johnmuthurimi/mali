package slick.mali.alertservice.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import slick.mali.alertservice.service.IAlertService;
import slick.mali.coreservice.constants.AlertStatus;
import slick.mali.coreservice.model.alert.EmailRequest;
import slick.mali.coreservice.util.DateUtils;
import slick.mali.coreservice.util.LoggerUtil;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * Use this service for the sending of emails
 */
@Service
public class EmailService {

    /**
     * Logger class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    /**
     * Inject java mailer service
     */
    @Autowired
    JavaMailSender mailSender;

    /**
     * alertService injection
     */
    @Autowired
    IAlertService alertService;

    /**
     * Environment variable
     */
    @Autowired
    private Environment env;

    /**
     * This function is called from the email job
     */
    public void executeQueue() {
        List<EmailRequest> queue = alertService.getQueuedEmailBatch();
        LoggerUtil.trace(LOGGER, "EmailScheduler: fetched queue emails");
        String subject = env.getProperty("spring.mail.subject");
        for (int i = 0; i < queue.size(); i++) {
            try {
                LoggerUtil.trace(LOGGER, "EmailScheduler: sending email queue");
                sendEmail(queue.get(i), subject);
            } catch (Exception e) {
                queue.get(i).setStatus(AlertStatus.FAILED);
            }
        }
        // Update status of the sent emails
        alertService.updateQueuedEmailBatch(queue);
        LoggerUtil.trace(LOGGER, "EmailScheduler: updated email queue");
    }

    /**
     * Send email and update status delivered or failed
     * @param request email request
     * @param subject email subject
     */
    public void sendEmail(EmailRequest request, String subject) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setFrom(request.getSender());
            mimeMessageHelper.setTo(request.getRecepient());
            mimeMessageHelper.setText(request.getMessage(), true);
            request.setSentAt(DateUtils.now());
            mailSender.send(mimeMessageHelper.getMimeMessage());
            request.setDeliveredAt(DateUtils.now());
            request.setSentAt(DateUtils.now());
            request.setStatus(AlertStatus.DELIVERED);
            LoggerUtil.trace(LOGGER, "EmailScheduler: sending email for recipient: " + request.getRecepient() + " delivered");
        } catch (MessagingException e) {
            request.setStatus(AlertStatus.FAILED);
        } catch (Exception e) {
            request.setStatus(AlertStatus.FAILED);
            LoggerUtil.error(LOGGER, "EmailScheduler: sending email failed for recipient: " + request.getRecepient() + " failed");
        }
    }
}

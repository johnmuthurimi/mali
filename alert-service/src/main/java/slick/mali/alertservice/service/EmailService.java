package slick.mali.alertservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import slick.mali.coreservice.constants.AlertStatus;
import slick.mali.coreservice.model.alert.EmailRequest;
import slick.mali.coreservice.util.DateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Use this service for sending emails
 */
@Service
public class EmailService {

    @Autowired
    JavaMailSender mailSender;

    /**
     * Send Email to customer
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
        } catch (MessagingException e) {
            request.setStatus(AlertStatus.FAILED);
        } catch (Exception e) {
            request.setStatus(AlertStatus.FAILED);
        }
    }
}

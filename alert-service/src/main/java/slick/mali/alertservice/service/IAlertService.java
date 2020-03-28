package slick.mali.alertservice.service;

import java.util.List;
import slick.mali.coreservice.service.IBaseService;
import slick.mali.coreservice.model.EventResponse;
import slick.mali.coreservice.model.alert.EmailRequest;

/**
 * Interface for all alert operations
 */
public interface IAlertService extends IBaseService {

    /**
    * Insert newly created email notification
    * @param request
    */
    String createNewEmailNotification(EventResponse request);

    /**
    * Returns a List of queue emails for sending
    * @return List<EmailRequest>
    */
    public List<EmailRequest> getQueuedEmailBatch();

    /**
    * Update the new batch after email service
    */
    public int[] updateQueuedEmailBatch(List<EmailRequest> list);

    /**
     * Send emails to customers
     */
    public void sendEmail();
}
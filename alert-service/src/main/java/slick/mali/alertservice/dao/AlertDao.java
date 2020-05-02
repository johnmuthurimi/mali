package slick.mali.alertservice.dao;

import slick.mali.coreservice.dao.BaseDao;
import slick.mali.coreservice.model.alert.EmailRequest;

import java.util.List;

/**
 * Class for all the alert service database operations
 */
public interface AlertDao  extends BaseDao {
   
   /**
    * Insert newly created email notification
    * @param request
    */
    String createNewEmailNotification(EmailRequest request);

   /**
    * Returns a List of queue emails for sending
    * @return List<EmailRequest>
    */
   List<EmailRequest> getQueuedEmailBatch();

   /**
    * Update the new batch after email service
    */
   int[] updateQueuedEmailBatch(List<EmailRequest> list);
}
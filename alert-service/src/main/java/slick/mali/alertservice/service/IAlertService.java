package slick.mali.alertservice.service;

import slick.mali.coreservice.service.IBaseService;
import slick.mali.coreservice.model.EventResponse;


/**
 * Interface for all alert operations
 */
public interface IAlertService extends IBaseService {

    /**
    * Insert newly created email notification
    * @param request
    */
    String createNewEmailNotification(EventResponse request);
}
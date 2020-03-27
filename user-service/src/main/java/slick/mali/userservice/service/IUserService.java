package slick.mali.userservice.service;

import java.sql.SQLException;

import slick.mali.coreservice.model.Auth;
import slick.mali.coreservice.model.EventRequest;
import slick.mali.coreservice.service.IBaseService;


/**
 * Interface for all user operations
 */
public interface IUserService extends IBaseService {

    /**
     * This functions user credentials
     * 
     * @param pageNumber
     * @return User
     * @throws SQLException
     */
    Auth getAuth(String id);

    /**
     * This feature is responsible for registering a customer
     * 
     * @param User
     * @return Users
     * @throws Exception
     */
    Auth register(Auth user);

    /**
     *  Request to Rabbit MQ
     * @param event
     */
    void sendMesage(final EventRequest event);
}
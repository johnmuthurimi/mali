package slick.mali.userservice.service;

import slick.mali.coreservice.model.EventRequest;
import slick.mali.coreservice.model.User;
import slick.mali.coreservice.service.IBaseService;


/**
 * Interface for all user operations
 */
public interface IUserService extends IBaseService {

    /**
     * Check if user exist
     * @param user
     * @return
     */
    boolean checkUserExists(User user);
    /**
     * This functions getting registered user by id
     * 
     * @param id
     * @return User
     */
    User getUser(String id);

    /**
     * This feature is responsible for registering a customer
     * 
     * @param user
     * @return Users
     * @throws Exception
     */
    User signUp(User user);

    /**
     *  Request to Rabbit MQ
     * @param event
     */
    void sendMessage(final EventRequest event);

    /**
     * This functions verifies the user token sent via email link
     *
     * @param token
     * @return User
     */
    User isTokenValid(String token) throws Exception;

    /**
     * Login User to the platform
     * @param login
     * @return
     */
    User login(User login);
}
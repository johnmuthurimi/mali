package slick.mali.userservice.service;

import java.sql.SQLException;

import slick.mali.userservice.model.Auth;

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
    Auth getAuth(String id) throws SQLException;

    /**
     * This feature is responsible for registering a customer
     * 
     * @param User
     * @return Users
     * @throws Exception
     */
    Auth register(Auth user) throws SQLException;
}
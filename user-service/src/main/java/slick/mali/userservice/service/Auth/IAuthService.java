package slick.mali.userservice.service.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import slick.mali.coreservice.service.IBaseService;


/**
 * Interface for all user operations
 */
public interface IAuthService extends IBaseService {

    /**
     * Generate authentication token
     * @param authenticationManager
     * @param email
     * @param password
     */
    void generateAuthenticationToken(AuthenticationManager authenticationManager, String email, String password) throws Exception;

    /**
     * Validate user credentials
     * @param email
     * @param password
     * @return
     */
    boolean validateCredentials(String email, String password) throws Exception;

}
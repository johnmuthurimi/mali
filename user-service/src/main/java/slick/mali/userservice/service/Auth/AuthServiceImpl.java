package slick.mali.userservice.service.Auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import slick.mali.coreservice.util.PasswordUtils;
import slick.mali.userservice.service.user.IUserService;

/**
 * Implementation for all user operations
 */
@Service
public class AuthServiceImpl implements IAuthService {

    public  static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    /**
     * Inject the user service
     */
    @Autowired
    private IUserService userService;

    /**
     * Generate authentication token
     * @param authenticationManager
     * @param email
     * @param password
     */
    public void generateAuthenticationToken(AuthenticationManager authenticationManager, String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    /**
     * Validate user credentials
     * @param email
     * @param password
     * @return
     */
    public boolean validateCredentials(String email, String password) throws Exception {
        boolean isValid = false;
        try {
            slick.mali.coreservice.model.user.User user = userService.findByEmail(email);
            boolean valid = PasswordUtils.verifyUserPassword(password, user.getPassword(), user.getSalt());
            if (user.getEmail().equals(email) && valid) {
                isValid = true;
            }

            return isValid;
        } catch (Exception e) {
            throw new Exception("Invalid credentials", e);
        }
    }
}
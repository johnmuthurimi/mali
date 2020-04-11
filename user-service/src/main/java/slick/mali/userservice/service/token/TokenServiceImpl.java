package slick.mali.userservice.service.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slick.mali.coreservice.model.user.Token;
import slick.mali.userservice.dao.token.TokenDao;
import slick.mali.userservice.dao.user.UserDao;
import slick.mali.userservice.rabbitmq.EventMessageSender;

/**
 * Implementation for all user operations
 */
@Service
public class TokenServiceImpl implements ITokenService {

    public  static final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Autowired
    private EventMessageSender eventMessageSender;

    /**
     * Inject the token DAO
     */
    @Autowired
    private TokenDao tokenDao;

    /**
     * Inject the user DAO
     */
    @Autowired
    private UserDao userDao;

    /**
     * Verify the user token whether it is valid
     * @param token
     * @return
     */
    @Override
    public Token verifyToken(String token) throws Exception {
        try {
            Token result = tokenDao.findById(token);
            if (result != null) {
                if (result.isVerified()) {
                    throw new Exception("Token has already been used");
                } else {
                    // Update the token as verified
                    userDao.updateVerified(result.getUserId());

                    // Update the user as verified, enable the user and change status
                    tokenDao.updateVerified(result.getUserId());
                }

                return result;
            } else {
                throw new Exception("Token has already been used");
            }
        } catch(Exception e) {
            throw new Exception("Token has already been used");
        }
    }
}
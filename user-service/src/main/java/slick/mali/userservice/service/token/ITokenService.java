package slick.mali.userservice.service.token;

import slick.mali.coreservice.model.user.Token;
import slick.mali.coreservice.service.IBaseService;


/**
 * Interface for all user operations
 */
public interface ITokenService extends IBaseService {

    /**
     * Verify token by id
     * @param token
     * @return
     * @throws Exception
     */
    Token verifyToken(String token) throws Exception;
}
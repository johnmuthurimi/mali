package slick.mali.userservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import slick.mali.coreservice.model.Response;
import slick.mali.coreservice.model.user.Token;
import slick.mali.coreservice.model.user.User;
import slick.mali.coreservice.model.Request;
import slick.mali.userservice.service.token.ITokenService;
import slick.mali.userservice.service.user.IUserService;
import slick.mali.coreservice.controller.BaseController;

import javax.validation.Valid;


/**
 * This class handles all the users request for api v1
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Inject the user service
     */
    @Autowired
    private IUserService userService;

    /**
     * Inject the token Service
     */
    @Autowired
    private ITokenService tokenService;

    /**
     * Find by Id
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Response<User>> findById(@PathVariable final String id) {
        logger.error("====> This is an error log");
        logger.info("====> This is an info log");
        logger.warn("====> This is an warn log");

        try {
            User result = userService.findById(id);
            return this.successfulResponse(result);
        } catch (Exception e) {
            return this.errorResponse(e.getMessage());
        }
    }

    /**
     * This is end point is responsible registered user
     * @param req
     * @return
     */
    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response<User>> signUp(@Valid @RequestBody Request<User> req) {
        try {
            User result = userService.create(req.getParam());
            return this.successfulResponse(result);
        } catch (Exception e) {
            return this.errorResponse(e.getMessage());
        }
    }

    /**
     * This is end point for verification os user email
     * @param  token
     * @return User
     */
    @RequestMapping(value = "verify")
    @ResponseBody
    public ResponseEntity<Response<Token>> verifyUser(@RequestParam(value = "token") String token) {
        try {
            Token result = tokenService.verifyToken(token);
            return this.successfulResponse(null);
        } catch (Exception e) {
            return this.errorResponse(e.getMessage());
        }
    }
}

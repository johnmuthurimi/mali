package slick.mali.userservice.controller;

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
    @RequestMapping(value = "/user/{id}")
    @ResponseBody
    public ResponseEntity<Response<User>> findById(@PathVariable final String id) {
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
    @PostMapping(path = "/user", consumes = "application/json", produces = "application/json")
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
    @RequestMapping(value = "user/verify")
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

package slick.mali.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import slick.mali.coreservice.model.Response;
import slick.mali.coreservice.model.User;
import slick.mali.coreservice.model.Request;
import slick.mali.userservice.service.IUserService;
import slick.mali.coreservice.controller.BaseController;

import javax.validation.Valid;

/**
 * This class handles all the users request for api v1
 */
@RestController
public class UserController extends BaseController {

    /**
     * Inject the user service
     */
    @Autowired
    private IUserService userService;

    /**
     * This is end point is responsible getting registered user
     * @param  id
     * @return User
     */
    @RequestMapping(value = "/user")
    @ResponseBody
    public ResponseEntity<Response<User>> getUser(@RequestParam(value = "id") String id) {
        User result = new User();
        try {
            result = userService.getUser(id);
            return this.successfulResponse(result);
        } catch (Exception e) {
            return this.errorResponse(result, e.getMessage());
        }
    }

    /**
     * This is end point is responsible registered user
     * @param req
     * @return
     */
    @PostMapping(path = "/user", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response<User>> signUp(@Valid @RequestBody Request<User> req) {
        User result = new User();
        try {
            result = userService.signUp(req.getParam());
            if (result.getId() != null) {
                return this.successfulResponse(null);
            } else {
                return this.errorResponse(result,"User sign up Failed");
            }
        } catch (Exception e) {
            return this.errorResponse(result, e.getMessage());
        }
    }

    /**
     * This is end point for verification os user email
     * @param  token
     * @return User
     */
    @RequestMapping(value = "/verify")
    @ResponseBody
    public ResponseEntity<Response<User>> verifyUser(@RequestParam(value = "token") String token) {
        User result = new User();
        try {
            result = userService.isTokenValid(token);
            if (result != null) {
                return this.successfulResponse(null);
            } else {
                return this.errorResponse(result, "User verification failed");
            }
        } catch (Exception e) {
            return this.errorResponse(result, e.getMessage());
        }
    }
}

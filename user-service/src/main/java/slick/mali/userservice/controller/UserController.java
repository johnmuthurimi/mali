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
    public ResponseEntity<Response<User>> getUser(@RequestParam String id) {
        try {
            User result = userService.getUser(id);
            return this.successfulResponse(result);
        } catch (Exception e) {
            return this.failedResponse(e.getMessage());
        }
    }

    /**
     * This is end point is responsible registered user
     * @param req
     * @return
     */
    @PostMapping(path = "/user", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response<User>> signUp(@RequestBody Request<User> req) {

        try {
            User result = userService.signUp(req.getParam());
            return this.successfulResponse(result);
        } catch (Exception e) {
            return this.failedResponse(e.getMessage());
        }
    }
}

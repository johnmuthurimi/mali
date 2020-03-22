package slick.mali.userservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import slick.mali.userservice.model.Response;
import slick.mali.userservice.model.User;
import slick.mali.userservice.service.IUserService;

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
     * This function returns users from the databases It is envisioned for admin
     * module management
     * 
     * @param pageNumber
     * @return List of users
     */
    @RequestMapping(value = "/fetch")
    @ResponseBody
    public ResponseEntity<Response<List<User>>> userFetch(@RequestParam int pageNumber) {
        try {
            List<User> result = userService.userFetch(pageNumber);
            return this.successfulResponse(result);
        } catch (Exception e) {
            return this.failedResponse(e.getMessage());
        }
    }

    /**
     * sign up user using email address and passworda
     * 
     * @param user user object
     * @return user
     */
    @PostMapping(path = "/signUp", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response<User>> signUp(@RequestBody User user) {

        try {
            User result = userService.signUp(user);
            return this.successfulResponse(result);
        } catch (Exception e) {
            return this.failedResponse(e.getMessage());
        }
    }
}

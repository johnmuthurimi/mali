package slick.mali.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import slick.mali.userservice.model.Response;
import slick.mali.userservice.model.Auth;
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
    @RequestMapping(value = "/auth/get")
    @ResponseBody
    public ResponseEntity<Response<Auth>> getAuth(@RequestParam String id) {
        try {
            Auth result = userService.getAuth(id);
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
    @PostMapping(path = "/auth", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response<Auth>> signUp(@RequestBody Auth user) {

        try {
            Auth result = userService.register(user);
            return this.successfulResponse(result);
        } catch (Exception e) {
            return this.failedResponse(e.getMessage());
        }
    }
}

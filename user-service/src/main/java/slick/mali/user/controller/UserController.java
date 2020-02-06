package slick.mali.user.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import slick.mali.user.model.User;
import slick.mali.user.service.IUserService;

/**
 * This class handles all the users request for api v1
 */
@RestController
@RequestMapping(value = "/user")
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
     * @param page
     * @param row
     * @return List of users
     */
    @RequestMapping(value = "/fetch")
    @ResponseBody
    public ResponseEntity<List<User>> getUsers(@RequestParam long page, @RequestParam long row) {
        List<User> users = userService.getUsers(page, row);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}

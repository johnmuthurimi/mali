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
     * @param pageNumber
     * @return List of users
     */
    @RequestMapping(value = "/fetch")
    @ResponseBody
    public ResponseEntity<List<User>> userFetch(@RequestParam int pageNumber) {
        List<User> users = userService.userFetch(pageNumber);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /*
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<User>> userAdd(@RequestBody User user) {
        User result = userService.userAdd(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }*/


}

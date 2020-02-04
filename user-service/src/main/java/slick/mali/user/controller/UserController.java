package slick.mali.user.service;

import java.util.List;
import slick.mali.user.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * This functions returns users from the system
     */
    @GetMapping("/fetch/v1.0/{page}/{row}")
    public ResponseEntity<List<User>> getUsers(@PathVariable("page") Long page, @PathVariable("row") Long row) {
        List<User> users = userService.getUsers(page,row);
        if (users == null) {
            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
}



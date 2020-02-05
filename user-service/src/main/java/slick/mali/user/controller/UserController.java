package slick.mali.user.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import slick.mali.user.model.User;
import slick.mali.user.service.IUserService;

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
     * This functions returns users from the system
     */
    @GetMapping("/users/fetch/{page}/{row}")
    public List<User> getUsers(@PathVariable("page") Long page, @PathVariable("row") Long row) {
        List<User> users = userService.getUsers(page,row);
        return users;
    }
}



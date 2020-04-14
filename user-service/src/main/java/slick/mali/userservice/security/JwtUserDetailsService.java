package slick.mali.userservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import slick.mali.userservice.service.user.IUserService;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    /**
     * Inject the user service
     */
    @Autowired
    private IUserService userService;

    /**
     * Load user email
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        slick.mali.coreservice.model.user.User user = userService.findByEmail(email);
        if (user.getEmail().equals(email)) {
            return new User(user.getEmail(), user.getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
}
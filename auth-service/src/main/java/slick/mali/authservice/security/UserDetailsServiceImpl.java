package slick.mali.authservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import slick.mali.authservice.security.mapper.AppUserMapper;
import slick.mali.authservice.security.model.AppUser;

import java.util.List;

/**
 * It has to be annotated with @Service.
 * Implement to check user from the database
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

    /**
     * Password encoder
     */
    @Autowired
    private BCryptPasswordEncoder encoder;

    /**
     * Inject the JDBC template
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Get user from the database with username
     * Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
     * So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
     * The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
     * And used by auth manager to verify and check user authentication.
     * If user not found. Throw this exception.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = findByEmail(username);
        if (appUser != null) {
            if (appUser.getEmail().equals(username)) {
                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                        .commaSeparatedStringToAuthorityList("ROLE_" + appUser.getRole());
                return new User(appUser.getEmail(), appUser.getPassword(), grantedAuthorities);
            } else {
                throw new UsernameNotFoundException("Username or password is incorrect");
            }
        }
        throw new UsernameNotFoundException("Username: " + username + " not found");
    }

    /**
     * Make a database call to get user by email
     * @param username
     * @return
     */
    public AppUser findByEmail(String username) {
        String query = "SELECT id, email, password, salt, status, enabled, deleted "
                + "FROM users  "
                + "WHERE email = ? AND enabled = 1 AND deleted = 0 "
                + "LIMIT 1 ";
        RowMapper<AppUser> rowMapper = new AppUserMapper();
        List<AppUser> user = jdbcTemplate.query(query, rowMapper, username);
        if(user.isEmpty() ){
            return null;
        } else if (user.size() == 1) {
            return user.get(0);
        }

        return null;
    }


}
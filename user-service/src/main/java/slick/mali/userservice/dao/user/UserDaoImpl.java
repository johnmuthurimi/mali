package slick.mali.userservice.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import slick.mali.coreservice.constants.UserStatus;
import slick.mali.coreservice.model.user.User;
import slick.mali.userservice.dao.mapper.UserMapper;

import java.util.List;
import java.util.UUID;

/**
 * User DAO implementation
 */
@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    /**
     * Inject the JDBC template
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Find user by ID which is the unique identifier
     * @param id
     * @return
     */
    @Override
    public User findById(String id) {
        String query = "SELECT id, first_name, last_name, email, status, enabled, deleted "
                + "FROM users  "
                + "WHERE id = ? "
                + "LIMIT 1 ";
        RowMapper<User> rowMapper = new UserMapper();
        List<User> aut = jdbcTemplate.query(query, rowMapper, id);
        if(aut.isEmpty() ){
            return null;
        } else if (aut.size() == 1) {
            return aut.get(0);
        }

        return null;
    }

    /**
     * Get user by email
     * @param email
     * @return
     */
    @Override
    public User findByEmail(String email) {
        String query = "SELECT id, first_name, last_name, email, salt, password, status, enabled, deleted "
                + "FROM users  "
                + "WHERE email = ? AND deleted = 0 "
                + "LIMIT 1 ";
        RowMapper<User> rowMapper = new UserMapper();
        List<User> aut = jdbcTemplate.query(query, rowMapper, email);
        if(aut.isEmpty() ){
            return null;
        } else if (aut.size() == 1) {
            return aut.get(0);
        }

        return null;
    }

    /**
     * Create user in the database
     * @param user
     * @return
     */
    @Override
    public String create(User user) {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        String query = "INSERT INTO users(id, first_name, last_name, email, password, salt, status, created_by, updated_by, enabled, deleted) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, randomUUIDString, user.getFirstName(), user.getLastName(), user.getEmail(),  user.getPassword(),
                user.getSalt(), user.getStatus(),  randomUUIDString, randomUUIDString, user.isEnabled(), user.isDeleted());
        user.setId(randomUUIDString);
        return randomUUIDString;
    }

    /**
     * Delete user from the database
     * @param id
     */
    @Override
    public void delete(String id) {
        User user = findById(id);
        if (user != null) {
            String query = "DELETE FROM users WHERE id = ?";
            jdbcTemplate.update(query, id);
        }
    }

    /**
     * Updated user status
     * @param status
     * @param userId
     */
    @Override
    public void updateStatus(Integer status, String userId) {
        String query = "UPDATE users "
                + "SET status = ? "
                + "WHERE id = ? ";
        jdbcTemplate.update(query, status, userId);
    }

    /**
     * Update user is verified
     * @param userId
     */
    @Override
    public void updateVerified(String userId) {
        String query = "UPDATE users "
                + "SET status = ?, verified = ?, enabled = ? "
                + "WHERE id = ? AND deleted = 0 ";
        jdbcTemplate.update(query, UserStatus.ACTIVE, 1, 1, userId);
    }
}
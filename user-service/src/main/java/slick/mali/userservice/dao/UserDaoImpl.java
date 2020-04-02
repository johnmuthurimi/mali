package slick.mali.userservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import slick.mali.coreservice.constants.UserStatus;
import slick.mali.coreservice.model.User;
import slick.mali.userservice.dao.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Use this function to get user
     */
    @Override
    public User getUser(String id) {
        String query = "SELECT id, type, email, status, enabled"
                + "FROM user_user  "
                + "WHERE id = ? AND deleted = 0";
        RowMapper<User> rowMapper = new UserMapper();
        List<User> aut = new ArrayList<User>();
        aut = jdbcTemplate.query(query, rowMapper, id);
        if(aut.isEmpty() ){
            return null;
        } else if (aut.size() == 1) { 
            // list contains exactly 1 element
            return aut.get(0);
        } 
        return null;
    }

    /**
     * Register user into the database
     * @param user
     * @return
     */
    @Override
    public String signUp(User user) {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        String query = "INSERT INTO user_user(id, type, salt, email, password, status, enabled, deleted) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, randomUUIDString, user.getType(), user.getSalt(), user.getEmail(),
                    user.getPassword(), user.getStatus(), user.isEnabled(), user.isDeleted());
        user.setId(randomUUIDString);
        return randomUUIDString;
    }

    /**
     * Update user email has been sent and now pending
     * @param userId
     */
    @Override
    public void updateUserPending(String userId) {
        String query = "UPDATE user_user "
                + "SET status = ? "
                + "WHERE id = ? AND deleted = 0 ";
        jdbcTemplate.update(query, UserStatus.PENDING, userId);
    }

    /**
     * Update user has been verified
     * @param userId
     */
    @Override
    public void updateUserVerified(String userId) {
        String query = "UPDATE user_user "
                + "SET status = ?, verified = ?, enabled = ? "
                + "WHERE id = ? AND deleted = 0 ";
        jdbcTemplate.update(query, UserStatus.ACTIVE, 1, 1, userId);
    }

    /**
     * Delete user from the database
     * @param id
     */
    @Override
    public void deleteUser(String id) {
        User user = getUser(id);
        if (user != null) {
            String query = "DELETE FROM user_user WHERE id = ? AND status = 0)";
            jdbcTemplate.update(query, id);
        }
    }

    /**
     * Verify user by creating a token which will be sent via email
     * @param user
     * @return
     */
    @Override
    public String generateUserVerificationToken(User user) {
        UUID id = UUID.randomUUID();
        String randomId = id.toString();

        UUID token = UUID.randomUUID();
        String tokenId = token.toString();
        String query = "INSERT INTO user_verify(id, user_id, token) VALUES(?, ?, ?)";
        jdbcTemplate.update(query, randomId, user.getId(), tokenId);
        return tokenId;
    }

    /**
     * Use this function to get user token
     * @param tokenId
     * @return
     */
    @Override
    public User getToken(String tokenId) {
        String query = "SELECT token, user_id"
                + "FROM user_verify  "
                + "WHERE id = ? AND deleted = 0";
        return jdbcTemplate.queryForObject(query, new Object[]{tokenId}, new UserMapper());
    }

    /**
     * Use this function to get user token
     */
    @Override
    public void updateTokenVerified(String tokenId) {
        String query = "UPDATE user_verify "
                + "SET verified = ? "
                + "WHERE id = ? AND deleted = 0 ";
        jdbcTemplate.update(query, 1, tokenId);
    }

    /**
     * Delete user verification token
     * @param tokenId
     */
    @Override
    public void deleteToken(String tokenId) {
        User user = getToken(tokenId);
        if (user != null) {
            if (user.getId() != null) {
                String query = "DELETE FROM user_verify WHERE id = ? AND deleted = 0";
                jdbcTemplate.update(query, tokenId);
            }
        }
    }
}
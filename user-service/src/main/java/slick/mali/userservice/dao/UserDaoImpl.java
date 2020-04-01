package slick.mali.userservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import slick.mali.coreservice.model.User;
import slick.mali.userservice.dao.mapper.UserMapper;

import java.sql.SQLException;
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
     * 
     * @throws SQLException
     */
    @Override
    public User getUser(String id) {
        String query = "SELECT id, type, email, username, status "
                + "FROM user_user  "
                + "WHERE id = ?";
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
        String query = "INSERT INTO user_user(id, type, salt, email, username, password, status, enabled, deleted) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, randomUUIDString, user.getType(), user.getSalt(), user.getEmail(),
                    user.getusername(), user.getPassword(), user.getStatus(), user.isEnabled(), user.isDeleted());
        user.setId(randomUUIDString);
        return randomUUIDString;
    }
}
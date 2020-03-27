package slick.mali.userservice.dao.auth;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import slick.mali.coreservice.model.Auth;

@Transactional
@Repository
public class AuthDaoImpl implements AuthDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Use this function to get user auth
     * 
     * @throws SQLException
     */
    @Override
    public Auth getAuth(String id) {
        String query = "SELECT id, type, email, username, status "
                + "FROM user_auth  "
                + "WHERE id = ?";
        RowMapper<Auth> rowMapper = new AuthMapper();
        List<Auth> aut = new ArrayList<Auth>();
        aut = jdbcTemplate.query(query, rowMapper, id);
        if(aut.isEmpty() ){
            return null;
        } else if (aut.size() == 1) { 
            // list contains exactly 1 element
            return aut.get(0);
        } 
        return null;
    }

    @Override
    public String register(Auth auth) {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        String query = "INSERT INTO user_auth(id, type, salt, email, username, password, status, enabled, deleted) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, randomUUIDString, auth.getType(), auth.getSalt(), auth.getEmail(),
                    auth.getusername(), auth.getPassword(), auth.getStatus(), auth.isEnabled(), auth.isDeleted());
        auth.setId(randomUUIDString);
        return randomUUIDString;
    }
}
package slick.mali.userservice.dao.auth;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.flywaydb.core.internal.jdbc.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import slick.mali.userservice.model.Auth;

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
    public Auth getAuth(String id) throws SQLException {
        String query = "SELECT id, [type], user_email, username, [status] "
                + "INTO out_id, out_type, out_user_email, out_username, out_status " + "FROM user_auth  "
                + "WHERE id = ?";
        RowMapper<Auth> rowMapper = new AuthMapper();
        List<Auth> aut = new ArrayList<Auth>();
        try {
            aut = jdbcTemplate.query(query, rowMapper, id);
        } catch (SQLException e) {
            throw e;
        }
        return aut.get(0);
    }

    @Override
    public void register(Auth auth) throws SQLException {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        String query = "INSERT INTO user_auth(id, type, salt, email, username, password, status, enabled, deleted) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {

            jdbcTemplate.update(query, randomUUIDString, auth.getType(), auth.getSalt(), auth.getEmail(),
                    auth.getusername(), auth.getPassword(), auth.getStatus(), auth.isEnabled(), auth.isDeleted());
        } catch (SQLException e) {
            throw e;
        }

    }
}
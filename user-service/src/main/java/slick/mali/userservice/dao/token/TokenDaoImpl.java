package slick.mali.userservice.dao.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import slick.mali.coreservice.model.user.Token;
import slick.mali.coreservice.model.user.User;
import slick.mali.userservice.dao.mapper.TokenMapper;

import java.util.List;
import java.util.UUID;

/**
 * Token DAO implementation
 */
@Transactional
@Repository
public class TokenDaoImpl implements TokenDao {

    /**
     * Inject the JDBC template
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Created verification token
     * @param user
     * @return token
     */
    @Override
    public String create(User user) {
        UUID id = UUID.randomUUID();
        String tokenId = id.toString();

        String query = "INSERT INTO tokens(id, user_id, created_by) VALUES(?, ?, ?)";
        jdbcTemplate.update(query, tokenId, user.getId(), user.getId());
        return tokenId;
    }

    /**
     * find by token id
     * @param id
     * @return
     */
    @Override
    public Token findById(String id) {
        String query = "SELECT id, user_id, verified "
                + "FROM tokens  "
                + "WHERE id = ? AND deleted = 0 "
                + "LIMIT 1 ";
        RowMapper<Token> rowMapper = new TokenMapper();
        List<Token> aut = jdbcTemplate.query(query, rowMapper, id);
        if(aut.isEmpty() ){
            return null;
        } else if (aut.size() == 1) {
            return aut.get(0);
        }

        return null;
    }

    /**
     * Update token
     * @param id
     */
    @Override
    public void updateVerified(String id) {
        String query = "UPDATE tokens "
                + "SET verified = ? "
                + "WHERE id = ? AND deleted = 0 ";
        jdbcTemplate.update(query, 1, id);
    }

    /**
     * Delete user verification token
     * @param id
     */
    @Override
    public void delete(String id) {
        String query = "DELETE FROM verify WHERE id = ? AND deleted = 0";
        jdbcTemplate.update(query, id);
    }
}
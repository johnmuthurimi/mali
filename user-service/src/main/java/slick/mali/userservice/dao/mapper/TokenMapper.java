package slick.mali.userservice.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import slick.mali.coreservice.model.user.Token;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenMapper implements RowMapper<Token> {

   @Override
   public Token mapRow(ResultSet rs, int rowNum) throws SQLException {
        Token token = new Token();
        if (rs != null) {
            token.setId(rs.getString("id"));
            token.setUserId(rs.getString("user_id"));
            token.setVerified(rs.getBoolean("verified"));
        }
        return token;
   }
}
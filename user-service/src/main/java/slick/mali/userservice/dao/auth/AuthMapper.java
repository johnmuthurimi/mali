package slick.mali.userservice.dao.auth;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import slick.mali.coreservice.model.Auth;

public class AuthMapper implements RowMapper<Auth> {

   @Override
   public Auth mapRow(ResultSet rs, int rowNum) throws SQLException {
        Auth auth = new Auth();
        auth.setId(rs.getString("id"));
        auth.setType(rs.getString("type"));
        auth.setEmail(rs.getString("email"));
        auth.setusername(rs.getString("username"));
        auth.setStatus(rs.getInt("status"));
        return auth;
   }
}
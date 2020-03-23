package slick.mali.userservice.dao.auth;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.flywaydb.core.internal.jdbc.RowMapper;
import slick.mali.userservice.model.Auth;

public class AuthMapper implements RowMapper<Auth> {

   @Override
   public Auth mapRow(ResultSet rs) throws SQLException {
        Auth auth = new Auth();
        auth.setId(rs.getString("out_id"));
        auth.setType(rs.getString("out_type"));
        auth.setEmail(rs.getString("out_user_email"));
        auth.setusername(rs.getString("out_username"));
        auth.setStatus(rs.getInt("out_status"));
        return auth;
   }
}
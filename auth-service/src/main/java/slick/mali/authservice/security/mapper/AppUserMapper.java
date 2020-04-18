package slick.mali.authservice.security.mapper;

import org.springframework.jdbc.core.RowMapper;
import slick.mali.authservice.security.model.AppUser;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User mapper class
 */
public class AppUserMapper implements RowMapper<AppUser> {

   @Override
   public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
       AppUser user = new AppUser();
        if (rs != null) {
            user.setId(rs.getString("id"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
        }
        return user;
   }
}
package slick.mali.userservice.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import slick.mali.coreservice.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenMapper implements RowMapper<User> {

   @Override
   public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        if (rs != null) {
            user.setToken(rs.getString("token"));
            user.setId(rs.getString("user_id"));
        }
        return user;
   }
}
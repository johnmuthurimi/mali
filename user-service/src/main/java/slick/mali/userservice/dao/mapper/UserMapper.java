package slick.mali.userservice.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import slick.mali.coreservice.model.User;

public class UserMapper implements RowMapper<User> {

   @Override
   public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setType(rs.getString("type"));
        user.setEmail(rs.getString("email"));
        user.setusername(rs.getString("username"));
        user.setStatus(rs.getInt("status"));
        return user;
   }
}
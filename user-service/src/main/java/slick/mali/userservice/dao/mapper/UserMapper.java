package slick.mali.userservice.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import slick.mali.coreservice.model.user.User;

public class UserMapper implements RowMapper<User> {

   @Override
   public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        if (rs != null) {
            user.setId(rs.getString("id"));
            user.setEmail(rs.getString("email"));
            user.setStatus(rs.getInt("status"));
            user.setSalt(rs.getString("salt"));
            user.setPassword(rs.getString("password"));
        }
        return user;
   }
}
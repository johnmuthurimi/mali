package slick.mali.userservice.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import slick.mali.coreservice.model.user.OTP;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OtpMapper implements RowMapper<OTP> {

   @Override
   public OTP mapRow(ResultSet rs, int rowNum) throws SQLException {
        OTP otp = new OTP();
        if (rs != null) {
            otp.setId(rs.getString("id"));
            otp.setUserId(rs.getString("user_id"));
            otp.setVerified(rs.getBoolean("verified"));
            otp.setCreatedAt(rs.getTimestamp("created_at"));
        }
        return otp;
   }
}
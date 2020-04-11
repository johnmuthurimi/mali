package slick.mali.alertservice.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import slick.mali.coreservice.model.alert.EmailRequest;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * This class maps the email requests
 */
public class EmailRequestMapper implements RowMapper<EmailRequest> {

   /**
    * Map the class properties
    */
   @Override
   public EmailRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
      EmailRequest obj = new EmailRequest();
      obj.setId(rs.getString("id"));
      obj.setSender(rs.getString("sender"));
      obj.setRecepient(rs.getString("recepient"));
      obj.setMessage(rs.getString("message"));
      obj.setCreatedAt(rs.getTimestamp("createdAt"));
      obj.setSentAt(rs.getTimestamp("sentAt"));
      obj.setDeliveredAt(rs.getTimestamp("deliveredAt"));
      obj.setStatus(rs.getInt("status"));
      return obj;
   }
}
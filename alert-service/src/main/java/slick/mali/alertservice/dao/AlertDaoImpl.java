package slick.mali.alertservice.dao;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.transaction.annotation.Transactional;
import slick.mali.coreservice.constants.AlertStatus;
import slick.mali.coreservice.model.alert.EmailRequest;

/**
 * Implementing class for all the database operations
 */
@Transactional
@Repository
public class AlertDaoImpl implements AlertDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Environment variable
     */
    @Autowired
    private Environment env;

    /**
     * Insert newly created email notification
     */
    @Override
    public String createNewEmailNotification(EmailRequest request) {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        String query = "INSERT INTO alert_email_out(id, sender, recepient, message, status) VALUES(?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, randomUUIDString, request.getSender(), request.getRecepient(), request.getMessage(), AlertStatus.NEW);
        request.setId(randomUUIDString);
        return randomUUIDString;
    }

    /**
     * Returns a List of queue emails for sending
     */
    @Override
    public List<EmailRequest> getQueuedEmailBatch() {
        List<EmailRequest> list = getNewEmailBatch();
        if (list.isEmpty()) {
            return null;
        }

        queueNewBatch(list);
        return list;
    }

    /**
     * Get all emails newly inserted
     */
    public List<EmailRequest> getNewEmailBatch() {
        String query = "SELECT id, sender, recepient, message, createdAt, sentAt, deliveredAt, status"
                + "FROM alert_email_out  "
                + "WHERE status = ? "
                + "LIMIT ? ";
        int limit = Integer.parseInt(env.getProperty("spring.mail.batchSize"));
        RowMapper<EmailRequest> rowMapper = new EmailRequestMapper();
        List<EmailRequest> list = new ArrayList<EmailRequest>();
        return jdbcTemplate.query(query, rowMapper, AlertStatus.NEW, limit);
    }

    /**
     * Update the new batch to queued
     */
    public int[] queueNewBatch(List<EmailRequest> list) {
        return jdbcTemplate.batchUpdate(
                "UPDATE  alert_email_out SET status = ? WHERE id = ?",
                new BatchPreparedStatementSetter() {

                    public void setValues(PreparedStatement ps, int i) 
						throws SQLException {
                        ps.setString(1, list.get(i).getId());
                        ps.setInt(2, AlertStatus.QUEUED);
                    }

                    public int getBatchSize() {
                        return list.size();
                    }

                });
    }

    /**
     * Update the new batch after email service
     */
    @Override
    public int[] updateQueuedEmailBatch(List<EmailRequest> list) {
        return jdbcTemplate.batchUpdate(
                "UPDATE  alert_email_out SET status = ? WHERE id = ?",
                new BatchPreparedStatementSetter() {

                    public void setValues(PreparedStatement ps, int i) 
						throws SQLException {
                        ps.setString(1, list.get(i).getId());
                        ps.setInt(2, list.get(i).getStatus());
                    }

                    public int getBatchSize() {
                        return list.size();
                    }

                });
    }
}
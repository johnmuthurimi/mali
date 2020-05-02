package slick.mali.userservice.dao.otp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import slick.mali.coreservice.model.user.OTP;
import slick.mali.coreservice.model.user.User;
import slick.mali.userservice.dao.mapper.OtpMapper;

import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * OTP DAO implementation
 */
@Transactional
@Repository
public class OtpDaoImpl implements OtpDao {

    /**
     * Inject the JDBC template
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Created verification OTP
     * @param userId
     * @return OTP
     */
    @Override
    public String create(String userId) {
        UUID id = UUID.randomUUID();
        String rowId = id.toString();
        String otp = generateOTP();

        String query = "INSERT INTO otps(id, user_id, otp, created_by) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(query, rowId, userId, otp, userId);
        return otp;
    }

    /**
     * find by OTP otp number
     * @param otp
     * @return
     */
    @Override
    public OTP findByOtp(String otp) {
        String query = "SELECT id, user_id, verified, created_at "
                + "FROM otps  "
                + "WHERE otp = ? AND deleted = 0 "
                + "ORDER BY created_at DESC "
                + "LIMIT 1 ";
        RowMapper<OTP> rowMapper = new OtpMapper();
        List<OTP> aut = jdbcTemplate.query(query, rowMapper, otp);
        return singleResult(aut);
    }

    /**
     * Update OTP
     * @param id
     */
    @Override
    public void updateVerified(String id) {
        String query = "UPDATE otps "
                + "SET verified = ? "
                + "WHERE id = ? AND deleted = 0 ";
        jdbcTemplate.update(query, 1, id);
    }

    /**
     * Delete user OTP
     * @param id
     * @param userId
     */
    @Override
    public void delete(String id, String userId) {
        String query = "DELETE FROM otps WHERE id = ? AND user_id = ? AND deleted = 0";
        jdbcTemplate.update(query, id, userId);
    }

    /**
     * Delete user OTP
     * @param userId
     */
    @Override
    public void deleteByUserId(String userId) {
        String query = "UPDATE otps "
                + "SET deleted = ? "
                + "WHERE user_id = ? ";
        jdbcTemplate.update(query, 1, userId);
    }

    /**
     * Use this function to generate OTP
     * @return
     */
    public String generateOTP()
    {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

    /**
     * Single Result
     * @param aut
     * @param <T>
     * @return
     */
    private <T> T singleResult(List<T> aut) {
        if(aut.isEmpty() ){
            return null;
        } else if (aut.size() == 1) {
            return aut.get(0);
        }

        return null;
    }

}
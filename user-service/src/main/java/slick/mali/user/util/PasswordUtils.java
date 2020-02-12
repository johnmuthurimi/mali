package slick.mali.user.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import slick.mali.user.model.AuthParam;



/**
 * Use this class for Password encoding
 */
public class PasswordUtils {
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 512;

    /**
     * Generate user encoded password 
     * 
     * @param password
     * @param salt
     * @return
     */
    public static AuthParam generateSecurePassword(String password, String salt) {
        String returnValue = null;
        byte[] securePassword = hash(password.toCharArray(), salt.getBytes()); 
        returnValue = Base64.getEncoder().encodeToString(securePassword); 

        // Prepare return model
        AuthParam params = new AuthParam();
        params.setType("password");
        params.setPassword(returnValue);
        params.setSalt(salt);
        return params;
    }

    /**
     * Validate the user password field
     * 
     * @param providedPassword
     * @param securedPassword
     * @param salt
     * @return boolean
     */
    public static boolean verifyUserPassword(String providedPassword,
            String securedPassword, String salt)
    {
        boolean returnValue = false;        
        // Generate New secure password with the same salt
        AuthParam auth = generateSecurePassword(providedPassword, salt);        
        // Check if two passwords are equal
        returnValue = auth.getPassword().equalsIgnoreCase(securedPassword);        
        return returnValue;
    }

    /**
     * Generate salt
     * @param length
     * @return string
     */
    public static String getSalt(int length) {
        StringBuilder returnValue = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }

    /**
     * Generate user hash
     * 
     * @param password
     * @param salt
     * @return bytes
     */
    public static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }

    }
}
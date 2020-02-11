package slick.mali.user.util;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import slick.mali.user.model.AuthParam;

/**
 * Use this class for Password encoding
 */
public class PasswordEncoder {

    /**
     * Generate encoded password from clear password text
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public AuthParam generateStrongPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();
         
        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();

        // Prepare return model
        AuthParam params = new AuthParam();
        String pass = iterations + ":" + toHex(salt) + ":" + toHex(hash);
        params.setType("password");
        params.setPassword(pass);
        return params;
    }
     
    /**
     * Generate salt
     * @return bytes
     * @throws NoSuchAlgorithmException
     */
    private byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
     
    /**
     * Generate hex from bytes
     * @param array
     * @return string
     * @throws NoSuchAlgorithmException
     */
    private String toHex(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0) {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        } else{
            return hex;
        }
    }
}
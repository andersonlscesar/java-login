package util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * The type Encryptor.
 */
public class Encryptor {

    /**
     * Encrypt string string.
     *
     * @param plainText the plain text
     * @return the string
     */
    public static String encryptString(String plainText) {
        return BCrypt.hashpw(plainText, BCrypt.gensalt());
    }

    /**
     * Check password boolean.
     *
     * @param plainText      the plain text
     * @param hashedPassword the hashed password
     * @return the boolean
     */
    public static boolean checkPassword(String plainText, String hashedPassword) {
        return BCrypt.checkpw(plainText, hashedPassword);
    }
}

package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * Enhanced password algorithm that generates passwords containing
 * uppercase letters, lowercase letters, and digits (A–Z, a–z, 0–9).
 * This implementation uses {@link java.security.SecureRandom}.
 */
public class EnhancedPasswordAlgorithm implements PasswordAlgorithm {

    private static final String ALLOWED =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz" +
            "0123456789";

    private final SecureRandom random = new SecureRandom();

    /**
     * Generates a password containing characters from the allowed
     * uppercase letters, lowercase letters, and digits.
     *
     * @param length the number of characters in the password
     * @return a password of the requested length using the allowed characters
     * @throws IllegalArgumentException if length is negative
     */
    @Override
    public String generate(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("length must be non-negative");
        }

        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALLOWED.length());
            char c = ALLOWED.charAt(index);
            builder.append(c);
        }
        return builder.toString();
    }

    /**
     * Exposes the allowed character set for testing purposes.
     *
     * @return the string of allowed characters.
     */
    public static String getAllowedCharacters() {
        return ALLOWED;
    }
}

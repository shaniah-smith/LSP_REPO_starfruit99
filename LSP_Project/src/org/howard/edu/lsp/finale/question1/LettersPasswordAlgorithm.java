package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * Password algorithm that generates passwords containing letters only
 * (uppercase A–Z and lowercase a–z).
 */
public class LettersPasswordAlgorithm implements PasswordAlgorithm {

    private static final String LETTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz";

    private final SecureRandom random = new SecureRandom();

    /**
     * Generates a password consisting only of letters (no digits).
     *
     * @param length the number of characters in the password
     * @return a password of the requested length using letters only
     * @throws IllegalArgumentException if length is negative
     */
    @Override
    public String generate(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("length must be non-negative");
        }

        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(LETTERS.length());
            char c = LETTERS.charAt(index);
            builder.append(c);
        }
        return builder.toString();
    }
}

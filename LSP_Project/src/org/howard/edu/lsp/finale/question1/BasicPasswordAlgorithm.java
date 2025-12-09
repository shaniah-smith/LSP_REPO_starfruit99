package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * Basic password algorithm that generates passwords using digits only (0–9).
 * This implementation uses {@link java.util.Random}.
 */
public class BasicPasswordAlgorithm implements PasswordAlgorithm {

    private static final String DIGITS = "0123456789";
    private final Random random = new Random();

    /**
     * Generates a password containing only numeric digits.
     *
     * @param length the number of characters in the password
     * @return a password of the requested length consisting of digits 0–9
     * @throws IllegalArgumentException if length is negative
     */
    @Override
    public String generate(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("length must be non-negative");
        }

        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(DIGITS.length());
            char c = DIGITS.charAt(index);
            builder.append(c);
        }
        return builder.toString();
    }
}

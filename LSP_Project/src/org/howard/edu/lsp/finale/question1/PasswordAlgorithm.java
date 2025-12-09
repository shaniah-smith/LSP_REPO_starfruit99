package org.howard.edu.lsp.finale.question1;

/**
 * Strategy interface for password-generation algorithms.
 * Each implementation defines how a password is created.
 */
public interface PasswordAlgorithm {

    /**
     * Generates a password according to the algorithm's rules.
     *
     * @param length the number of characters in the password
     * @return the generated password string
     */
    String generate(int length);
}

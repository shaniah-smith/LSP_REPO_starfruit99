package org.howard.edu.lsp.finale.question1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit test suite for {@link PasswordGeneratorService}.
 * Verifies singleton behavior and the characteristics of each algorithm.
 */
public class PasswordGeneratorServiceTest {

    private PasswordGeneratorService service;

    /**
     * Obtains the singleton instance before each test.
     */
    @BeforeEach
    public void setup() {
        service = PasswordGeneratorService.getInstance();
    }

    /**
     * Verifies that the singleton instance is successfully created.
     */
    @Test
    public void checkInstanceNotNull() {
        assertNotNull(service, "Service instance should not be null");
    }

    /**
     * Verifies that getInstance() always returns the same object,
     * confirming true singleton behavior.
     */
    @Test
    public void checkSingleInstanceBehavior() {
        PasswordGeneratorService second = PasswordGeneratorService.getInstance();
        // Must be the exact same object in memory
        assertSame(service, second,
                "Both references should point to the same singleton instance");
    }

    /**
     * Verifies that calling generatePassword(int) before selecting an
     * algorithm results in an IllegalStateException.
     */
    @Test
    public void generateWithoutSettingAlgorithmThrowsException() {
        PasswordGeneratorService s = PasswordGeneratorService.getInstance();

        assertThrows(IllegalStateException.class,
                () -> s.generatePassword(10),
                "Calling generatePassword without setting an algorithm should throw IllegalStateException");
    }

    /**
     * Verifies that the basic algorithm produces a password of the
     * correct length containing digits only (0–9).
     */
    @Test
    public void basicAlgorithmGeneratesCorrectLengthAndDigitsOnly() {
        service.setAlgorithm("basic");
        String p = service.generatePassword(10);

        assertEquals(10, p.length(), "Password length must be 10 for basic algorithm");
        for (char c : p.toCharArray()) {
            assertTrue(Character.isDigit(c),
                    "Basic algorithm should generate digits only, but found: " + c);
        }
    }

    /**
     * Verifies that the enhanced algorithm produces a password of the
     * correct length containing only allowed characters (A–Z, a–z, 0–9).
     */
    @Test
    public void enhancedAlgorithmGeneratesCorrectCharactersAndLength() {
        service.setAlgorithm("enhanced");
        String p = service.generatePassword(12);

        assertEquals(12, p.length(), "Password length must be 12 for enhanced algorithm");

        String allowed = EnhancedPasswordAlgorithm.getAllowedCharacters();
        for (char c : p.toCharArray()) {
            assertTrue(allowed.indexOf(c) >= 0,
                    "Enhanced algorithm produced invalid character: " + c);
        }
    }

    /**
     * Verifies that the letters algorithm produces a password containing
     * letters only (no digits or other characters).
     */
    @Test
    public void lettersAlgorithmGeneratesLettersOnly() {
        service.setAlgorithm("letters");
        String p = service.generatePassword(8);

        assertEquals(8, p.length(), "Password length must be 8 for letters algorithm");
        for (char c : p.toCharArray()) {
            assertTrue(Character.isLetter(c),
                    "Letters algorithm should generate letters only, but found: " + c);
        }
    }

    /**
     * Verifies that switching algorithms at runtime changes the behavior
     * of the service so that each generated password obeys the rules of
     * the currently selected algorithm.
     */
    @Test
    public void switchingAlgorithmsChangesBehavior() {
        // basic: digits only
        service.setAlgorithm("basic");
        String p1 = service.generatePassword(10);
        for (char c : p1.toCharArray()) {
            assertTrue(Character.isDigit(c),
                    "Basic algorithm should generate digits only, but found: " + c);
        }

        // letters: letters only
        service.setAlgorithm("letters");
        String p2 = service.generatePassword(10);
        for (char c : p2.toCharArray()) {
            assertTrue(Character.isLetter(c),
                    "Letters algorithm should generate letters only, but found: " + c);
        }

        // enhanced: allowed letters and digits
        service.setAlgorithm("enhanced");
        String p3 = service.generatePassword(10);
        String allowed = EnhancedPasswordAlgorithm.getAllowedCharacters();
        for (char c : p3.toCharArray()) {
            assertTrue(allowed.indexOf(c) >= 0,
                    "Enhanced algorithm produced invalid character: " + c);
        }

        // Optional sanity check: behaviors should differ in general
        assertNotEquals(p1, p2, "Basic and letters passwords should typically differ");
    }
}

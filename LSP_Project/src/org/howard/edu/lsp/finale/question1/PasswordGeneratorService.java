package org.howard.edu.lsp.finale.question1;

/*
 * Design Patterns Used:
 *
 * 1. Singleton
 *    - PasswordGeneratorService is implemented as a singleton so that
 *      there is only one shared instance in the application.
 *      The getInstance() method returns this single object, and the
 *      constructor is private to prevent additional instances.
 *
 * 2. Strategy
 *    - Different password-generation behaviors (basic, enhanced, letters)
 *      are implemented as separate classes that share the
 *      PasswordAlgorithm interface.
 *      The setAlgorithm(String) method selects which strategy to use
 *      at runtime, and generatePassword(int) delegates to the currently
 *      selected strategy.
 *    - This design makes it easy to add new algorithms in the future
 *      without changing client code that uses PasswordGeneratorService.
 */

/**
 * Service responsible for generating passwords using different algorithms. This
 * class combines the Singleton and Strategy patterns to provide a single shared
 * access point whose behavior can be changed at runtime.
 */
public class PasswordGeneratorService {

	/**
	 * The single shared instance of the service.
	 */
	private static PasswordGeneratorService instance;

	/**
	 * The currently selected password-generation algorithm (strategy).
	 */
	private PasswordAlgorithm algorithm;

	/**
	 * Private constructor to enforce the Singleton pattern.
	 */
	private PasswordGeneratorService() {
		// no external instantiation
	}

	/**
	 * Returns the single shared instance of the PasswordGeneratorService. If the
	 * instance does not yet exist, it is created.
	 *
	 * @return the singleton PasswordGeneratorService instance
	 */
	public static synchronized PasswordGeneratorService getInstance() {
		if (instance == null) {
			instance = new PasswordGeneratorService();
		}
		return instance;
	}

	/**
	 * Selects which password-generation algorithm to use.
	 *
	 * @param name the algorithm name: "basic" — digits only (0–9), Random-based
	 *             "enhanced" — letters and digits, SecureRandom-based "letters" —
	 *             letters only (A–Z, a–z)
	 * @throws IllegalArgumentException if the algorithm name is not supported
	 */
	public void setAlgorithm(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Algorithm name must not be null.");
		}

		String normalized = name.toLowerCase();

		switch (normalized) {
		case "basic":
			algorithm = new BasicPasswordAlgorithm();
			break;
		case "enhanced":
			algorithm = new EnhancedPasswordAlgorithm();
			break;
		case "letters":
			algorithm = new LettersPasswordAlgorithm();
			break;
		default:
			throw new IllegalArgumentException("Unsupported algorithm: " + name);
		}
	}

	/**
	 * Generates a password using the currently selected algorithm.
	 *
	 * @param length the number of characters in the password
	 * @return the generated password
	 * @throws IllegalStateException    if no algorithm has been set before calling
	 *                                  this method
	 * @throws IllegalArgumentException if the length is invalid for the underlying
	 *                                  algorithm
	 */
	public String generatePassword(int length) {
		if (algorithm == null) {
			throw new IllegalStateException("No password-generation algorithm has been set.");
		}
		return algorithm.generate(length);
	}
}

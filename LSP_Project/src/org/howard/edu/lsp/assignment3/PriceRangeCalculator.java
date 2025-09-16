package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Utility class for categorizing product prices into ranges.
 * <p>
 * Price ranges are defined as:
 * <ul>
 *   <li>{@code Low}: prices ≤ 10.00</li>
 *   <li>{@code Medium}: prices ≤ 100.00</li>
 *   <li>{@code High}: prices ≤ 500.00</li>
 *   <li>{@code Premium}: prices &gt; 500.00</li>
 * </ul>
 */
public class PriceRangeCalculator {

    /**
     * Calculates the price range category for a given price.
     *
     * @param price the product price to evaluate (non-null)
     * @return a string label representing the price range:
     *         {@code Low}, {@code Medium}, {@code High}, or {@code Premium}
     */
    public static String calculate(BigDecimal price) {
        if (price.compareTo(new BigDecimal("10.00")) <= 0) {
            return "Low";
        } else if (price.compareTo(new BigDecimal("100.00")) <= 0) {
            return "Medium";
        } else if (price.compareTo(new BigDecimal("500.00")) <= 0) {
            return "High";
        } else {
            return "Premium";
        }
    }
}


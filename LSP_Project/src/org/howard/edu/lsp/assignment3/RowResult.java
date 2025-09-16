package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Represents the outcome of transforming a single {@link Product}.
 * <p>
 * Each row result contains:
 * <ul>
 *   <li>The original product ID</li>
 *   <li>The uppercase product name</li>
 *   <li>The final price (discounts applied and rounded)</li>
 *   <li>The final category after any reclassification</li>
 *   <li>The computed price range label</li>
 * </ul>
 * This class is immutable once created since all fields are {@code final}.
 */
public class RowResult {
    /** Identifier of the product. */
    final int productId;

    /** Uppercased name of the product. */
    final String upperName;

    /** Final price of the product after transformation. */
    final BigDecimal finalPrice;

    /** Final category of the product after any recategorization. */
    final String finalCategory;

    /** Computed price range classification. */
    final String priceRange;

    /**
     * Constructs a new {@code RowResult} with transformed product attributes.
     *
     * @param productId    the identifier of the product
     * @param upperName    the product name in uppercase
     * @param finalPrice   the transformed product price
     * @param finalCategory the transformed or recategorized product category
     * @param priceRange   the price range label assigned to the product
     */
    public RowResult(int productId, String upperName, BigDecimal finalPrice, String finalCategory, String priceRange) {
        this.productId = productId;
        this.upperName = upperName;
        this.finalPrice = finalPrice;
        this.finalCategory = finalCategory;
        this.priceRange = priceRange;
    }
}

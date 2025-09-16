package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Represents a product entity with basic attributes.
 * <p>
 * Each product contains:
 * <ul>
 *   <li>A unique product ID</li>
 *   <li>A name</li>
 *   <li>A price represented as {@link BigDecimal}</li>
 *   <li>A category label</li>
 * </ul>
 * This class is immutable once created since its fields are {@code final}.
 */
public class Product {
    /** Unique identifier for the product. */
    final int productId;

    /** The product's name. */
    final String name;

    /** The product's price as a {@link BigDecimal}. */
    final BigDecimal price;

    /** The product's category (e.g., Electronics, Clothing). */
    final String category;

    /**
     * Constructs a new {@code Product} with the specified attributes.
     *
     * @param productId the unique product identifier
     * @param name      the product name
     * @param price     the product price
     * @param category  the product category
     */
    public Product(int productId, String name, BigDecimal price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
    }
}

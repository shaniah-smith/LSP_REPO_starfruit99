package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

public class RowResult {
    final int productId;
    final String upperName;
    final BigDecimal finalPrice;
    final String finalCategory;
    final String priceRange;

    // Add the constructor that accepts arguments
    public RowResult(int productId, String upperName, BigDecimal finalPrice, String finalCategory, String priceRange) {
        this.productId = productId;
        this.upperName = upperName;
        this.finalPrice = finalPrice;
        this.finalCategory = finalCategory;
        this.priceRange = priceRange;
    }
}

package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

public class Product {
    final int productId;
    final String name;
    final BigDecimal price;
    final String category;

    public Product(int productId, String name, BigDecimal price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
    }
}

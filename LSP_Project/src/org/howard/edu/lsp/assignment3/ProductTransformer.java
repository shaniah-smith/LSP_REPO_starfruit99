package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductTransformer {

    public List<RowResult> transform(List<Product> products) {
        List<RowResult> transformedProducts = new ArrayList<>();
        for (Product product : products) {
            RowResult result = transformProduct(product);
            transformedProducts.add(result);
        }
        return transformedProducts;
    }

    private RowResult transformProduct(Product product) {
        // Uppercase Name
        String upperName = product.name.toUpperCase(Locale.ROOT);

        // Discount if Electronics
        BigDecimal price = product.price;
        if ("electronics".equalsIgnoreCase(product.category)) {
            price = price.multiply(new BigDecimal("0.90"));
        }

        // Round to two decimals
        price = price.setScale(2, RoundingMode.HALF_UP);

        // Recategorize to Premium Electronics if price > $500
        String finalCategory = product.category;
        if ("electronics".equalsIgnoreCase(product.category) && price.compareTo(new BigDecimal("500.00")) > 0) {
            finalCategory = "Premium Electronics";
        }

        // Calculate price range
        String priceRange = PriceRangeCalculator.calculate(price);

        return new RowResult(product.productId, upperName, price, finalCategory, priceRange);
    }
}

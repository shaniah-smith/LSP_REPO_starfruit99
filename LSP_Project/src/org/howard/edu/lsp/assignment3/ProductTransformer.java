package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Handles transformation logic for {@link Product} objects.
 * <p>
 * Transformations applied:
 * <ul>
 *   <li>Converts the product name to uppercase.</li>
 *   <li>Applies a 10% discount to electronics.</li>
 *   <li>Rounds prices to two decimal places.</li>
 *   <li>Recategorizes expensive electronics (&gt; $500) as "Premium Electronics".</li>
 *   <li>Determines a price range using {@link PriceRangeCalculator}.</li>
 * </ul>
 */
public class ProductTransformer {

    /**
     * Transforms a list of {@link Product} objects into {@link RowResult} outputs.
     *
     * @param products the list of input products
     * @return a list of {@link RowResult} objects containing transformed data
     */
    public List<RowResult> transform(List<Product> products) {
        List<RowResult> transformedProducts = new ArrayList<>();
        for (Product product : products) {
            RowResult result = transformProduct(product);
            transformedProducts.add(result);
        }
        return transformedProducts;
    }

    /**
     * Applies transformation rules to a single product.
     *
     * @param product the product to transform
     * @return a {@link RowResult} containing transformed values
     */
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

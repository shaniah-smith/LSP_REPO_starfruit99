package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

public class ETLPipeline {

    // Relative paths (run from project root so "data/" resolves)
    private static final Path INPUT_PATH  = Paths.get("data", "products.csv");
    private static final Path OUTPUT_PATH = Paths.get("data", "transformed_products.csv");

    // Summary counters
    private static int rowsRead = 0;        // data rows (excluding header)
    private static int rowsTransformed = 0; // successfully written rows
    private static int rowsSkipped = 0;     // invalid/malformed rows

    public static void main(String[] args) {
        try {
            run();
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

    // Extract → Transform → Load
    private static void run() throws IOException {
        if (!Files.exists(INPUT_PATH)) {
            System.err.println("ERROR: Input file not found at \"" + INPUT_PATH + "\".");
            System.err.println("Create data/products.csv (relative path) and run again.");
            // Do NOT create an output file if input is missing.
            return;
        }

        if (!Files.exists(OUTPUT_PATH.getParent())) {
            Files.createDirectories(OUTPUT_PATH.getParent());
        }

        try (BufferedReader reader = Files.newBufferedReader(INPUT_PATH, StandardCharsets.UTF_8);
             BufferedWriter writer = Files.newBufferedWriter(OUTPUT_PATH, StandardCharsets.UTF_8)) {

            // Always write header to output
            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();

            // Skip input header
            String header = reader.readLine();
            if (header == null) {
                printSummary();
                return; // empty input → output has only header
            }

            String line;
            while ((line = reader.readLine()) != null) {
                rowsRead++;
                String transformed = transformLine(line);
                if (transformed != null) {
                    writer.write(transformed);
                    writer.newLine();
                    rowsTransformed++;
                } else {
                    rowsSkipped++;
                }
            }
        }

        printSummary();
    }

    /**
     * Transform one CSV row.
     * Input columns: ProductID,Name,Price,Category
     * Output columns: ProductID,Name,Price,Category,PriceRange
     * Transform order per spec:
     *  (1) uppercase name
     *  (2) discount (if Electronics) & round HALF_UP to 2 decimals
     *  (3) recategorization if post-discount price > 500 and original Electronics
     *  (4) PriceRange from final price
     */
    private static String transformLine(String line) {
        String[] cols = line.split(",", -1); // CSV guaranteed simple
        if (cols.length != 4) return null;

        String productIdStr = cols[0].trim();
        String name         = cols[1].trim();
        String priceStr     = cols[2].trim();
        String category     = cols[3].trim();

        // Validate ProductID
        int productId;
        try {
            productId = Integer.parseInt(productIdStr);
        } catch (NumberFormatException e) {
            return null;
        }

        // Validate Price
        BigDecimal price;
        try {
            price = new BigDecimal(priceStr);
        } catch (NumberFormatException e) {
            return null;
        }
        if (price.compareTo(BigDecimal.ZERO) < 0) return null;

        // (1) Uppercase Name
        String upperName = name.toUpperCase(Locale.ROOT);

        // (2) Discount if Electronics
        boolean originalElectronics = category.equalsIgnoreCase("Electronics");
        if (originalElectronics) {
            price = price.multiply(new BigDecimal("0.90")); // 10% off
        }
        // Round after discount
        price = price.setScale(2, RoundingMode.HALF_UP);

        // (3) Recategorize Electronics > 500
        String finalCategory;
        if (originalElectronics && price.compareTo(new BigDecimal("500.00")) > 0) {
            finalCategory = "Premium Electronics";
        } else if (originalElectronics) {
            finalCategory = "Electronics";
        } else {
            finalCategory = category;
        }

        // (4) PriceRange inclusive boundaries
        String priceRange;
        if (price.compareTo(new BigDecimal("10.00")) <= 0) {
            priceRange = "Low";
        } else if (price.compareTo(new BigDecimal("100.00")) <= 0) {
            priceRange = "Medium";
        } else if (price.compareTo(new BigDecimal("500.00")) <= 0) {
            priceRange = "High";
        } else {
            priceRange = "Premium";
        }

        return productId + "," + upperName + "," + price.toPlainString() + "," + finalCategory + "," + priceRange;
    }

    private static void printSummary() {
        System.out.println("=== ETL Run Summary ===");
        System.out.println("Input:  " + INPUT_PATH);
        System.out.println("Output: " + OUTPUT_PATH);
        System.out.println("Rows read (data only):       " + rowsRead);
        System.out.println("Rows transformed & written:  " + rowsTransformed);
        System.out.println("Rows skipped (invalid/malformed): " + rowsSkipped);
    }
}

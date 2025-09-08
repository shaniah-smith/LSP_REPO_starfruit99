package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Locale;

public class ETLPipeline {
    // Relative paths from the project root
    private static final Path INPUT_PATH  = Paths.get("data", "products.csv");
    private static final Path OUTPUT_PATH = Paths.get("data", "transformed_products.csv");

    public static void main(String[] args) {
        try {
            Files.createDirectories(OUTPUT_PATH.getParent());
            run();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    private static void run() {
        int rowsRead = 0, rowsTransformed = 0, rowsSkipped = 0;

        try (BufferedReader in = Files.newBufferedReader(INPUT_PATH, StandardCharsets.UTF_8);
             BufferedWriter out = Files.newBufferedWriter(
                     OUTPUT_PATH, StandardCharsets.UTF_8,
                     StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {

            // Always write header
            out.write("ProductID,Name,Price,Category,PriceRange");
            out.newLine();

            // Input header (don’t transform)
            String headerIn = in.readLine();
            if (headerIn == null) {
                // empty input file (no rows)
                printSummary(rowsRead, rowsTransformed, rowsSkipped);
                return;
            }

            String line;
            while ((line = in.readLine()) != null) {
                if (line.trim().isEmpty()) { rowsSkipped++; continue; }

                rowsRead++;
                String[] c = line.split(",", -1); // no quoted commas per spec
                if (c.length < 4) { rowsSkipped++; continue; }

                String idStr     = c[0].trim();
                String name      = c[1].trim();
                String priceStr  = c[2].trim();
                String category  = c[3].trim();

                try {
                    int productId = Integer.parseInt(idStr);
                    if (productId < 0) { rowsSkipped++; continue; }

                    BigDecimal price = new BigDecimal(priceStr);
                    if (price.compareTo(BigDecimal.ZERO) < 0) { rowsSkipped++; continue; }

                    RowResult r = transform(productId, name, price, category);

                    // Column order: ProductID,Name,Price,Category,PriceRange
                    out.write(r.productId + "," + r.upperName + "," +
                              r.finalPrice.toPlainString() + "," +
                              r.finalCategory + "," + r.priceRange);
                    out.newLine();
                    rowsTransformed++;

                } catch (NumberFormatException nfe) {
                    rowsSkipped++;
                }
            }

            out.flush();
            printSummary(rowsRead, rowsTransformed, rowsSkipped);

        } catch (NoSuchFileException nsfe) {
            System.err.println("ERROR: Input file not found at \"data/products.csv\".");
            System.err.println("Create data/products.csv (relative path) and run again.");
        } catch (IOException ioe) {
            System.err.println("ERROR reading/writing files: " + ioe.getMessage());
        }
    }

    // Transform order: 1) uppercase name → 2) discount → 3) recategorize (if >500 & orig Electronics) → 4) price range
    private static RowResult transform(int productId, String name, BigDecimal price, String category) {
        // (1) Uppercase Name
        String upper = name.toUpperCase(Locale.ROOT);

        // Keep original category for logic
        boolean originalElectronics = "electronics".equalsIgnoreCase(category);

        // (2) Discount if Electronics
        BigDecimal p = price;
        if (originalElectronics) {
            p = p.multiply(new BigDecimal("0.90")); // 10% off
        }

        // (3) Round to two decimals (HALF_UP)
        p = p.setScale(2, RoundingMode.HALF_UP);

        // (4) Recategorize to "Premium Electronics" if post-discount price > 500 and original category was Electronics
        String finalCategory;
        if (originalElectronics && p.compareTo(new BigDecimal("500.00")) > 0) {
            finalCategory = "Premium Electronics";
        } else if (originalElectronics) {
            finalCategory = "Electronics";
        } else {
            finalCategory = category;
        }

        // (5) PriceRange from final price (inclusive edges)
        String range;
        if (p.compareTo(new BigDecimal("10.00")) <= 0) {
            range = "Low";
        } else if (p.compareTo(new BigDecimal("100.00")) <= 0) {
            range = "Medium";
        } else if (p.compareTo(new BigDecimal("500.00")) <= 0) {
            range = "High";
        } else {
            range = "Premium";
        }

        return new RowResult(productId, upper, p, finalCategory, range);
    }

    private static void printSummary(int read, int transformed, int skipped) {
        System.out.println("=== ETL Run Summary ===");
        System.out.println("Input:  data/products.csv");
        System.out.println("Output: data/transformed_products.csv");
        System.out.println("Rows read (data only):   " + read);
        System.out.println("Rows transformed & written: " + transformed);
        System.out.println("Rows skipped (invalid/malformed): " + skipped);
    }

    private static class RowResult {
        final int productId;
        final String upperName;
        final BigDecimal finalPrice;
        final String finalCategory;
        final String priceRange;
        RowResult(int id, String upperName, BigDecimal finalPrice, String finalCategory, String priceRange) {
            this.productId = id; this.upperName = upperName;
            this.finalPrice = finalPrice; this.finalCategory = finalCategory; this.priceRange = priceRange;
        }
    }
}
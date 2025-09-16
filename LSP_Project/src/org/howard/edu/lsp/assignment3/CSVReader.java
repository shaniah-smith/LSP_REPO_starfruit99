package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for reading product data from a CSV file.
 * <p>
 * Each row in the file is parsed into a {@link Product} object. 
 * The CSV is expected to have the format:
 * <pre>
 * ProductID,Name,Price,Category
 * </pre>
 * The first header line is skipped automatically. Empty lines and malformed
 * rows (fewer than four columns) are ignored.
 */
public class CSVReader {

    /**
     * Reads products from a CSV file and converts them into {@link Product} objects.
     *
     * @param path the path to the CSV file containing product data
     * @return a list of parsed {@link Product} instances; may be empty if no valid rows are found
     * @throws IOException if an I/O error occurs while reading the file
     * @throws NumberFormatException if a product ID or price cannot be parsed
     */
    public static List<Product> readProducts(Path path) throws IOException {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            reader.readLine();  // Skip header line
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;  // Skip empty lines

                String[] columns = line.split(",");
                if (columns.length < 4) continue;  // Skip malformed rows

                int productId = Integer.parseInt(columns[0].trim());
                String name = columns[1].trim();
                BigDecimal price = new BigDecimal(columns[2].trim());
                String category = columns[3].trim();

                products.add(new Product(productId, name, price, category));
            }
        }
        return products;
    }
}


package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Utility class for writing transformed product data to a CSV file.
 * <p>
 * This writer outputs a header row followed by the transformed results of
 * the ETL process. Each row corresponds to a {@link RowResult}, containing
 * product ID, uppercase name, final price, category, and price range.
 */
public class CSVWriter {

    /**
     * Writes the list of transformed product rows to a CSV file.
     * <p>
     * The output file will include a header row with the following columns:
     * <pre>
     * ProductID,Name,Price,Category,PriceRange
     * </pre>
     *
     * @param path    the path to the CSV file where results should be written
     * @param results the list of {@link RowResult} objects to output
     * @throws IOException if an I/O error occurs while creating or writing the file
     */
    public static void writeProducts(Path path, List<RowResult> results) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            // Write header
            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();

            // Write transformed data
            for (RowResult result : results) {
                writer.write(result.productId + "," + result.upperName + ","
                        + result.finalPrice.toPlainString() + "," + result.finalCategory + ","
                        + result.priceRange);
                writer.newLine();
            }
        }
    }
}


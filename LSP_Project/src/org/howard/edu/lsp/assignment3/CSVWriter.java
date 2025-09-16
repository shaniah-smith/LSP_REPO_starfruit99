package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CSVWriter {
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

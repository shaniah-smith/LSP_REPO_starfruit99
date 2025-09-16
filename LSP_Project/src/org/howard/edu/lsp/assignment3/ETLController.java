package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class ETLController {
    private static final Path INPUT_PATH = Paths.get("data", "products.csv");
    private static final Path OUTPUT_PATH = Paths.get("data", "transformed_products.csv");

    public static void main(String[] args) {
        try {
            Files.createDirectories(OUTPUT_PATH.getParent());

            // Read products from CSV
            List<Product> products = CSVReader.readProducts(INPUT_PATH);

            // Transform products
            ProductTransformer transformer = new ProductTransformer();
            List<RowResult> transformedProducts = transformer.transform(products);

            // Write transformed products to CSV
            CSVWriter.writeProducts(OUTPUT_PATH, transformedProducts);

            System.out.println("ETL Process Completed!");

        } catch (IOException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }
}

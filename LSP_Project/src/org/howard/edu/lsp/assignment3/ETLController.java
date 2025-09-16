package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Orchestrates the ETL (Extract–Transform–Load) pipeline for products.
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Reads input data from a CSV file.</li>
 *   <li>Transforms product records via {@link ProductTransformer}.</li>
 *   <li>Writes transformed results to a CSV file.</li>
 * </ul>
 * <p>
 * The input and output file locations are defined by {@code INPUT_PATH} and {@code OUTPUT_PATH}.
 */
public class ETLController {

    /** Path to the input CSV file containing raw product rows. */
    private static final Path INPUT_PATH = Paths.get("data", "products.csv");

    /** Path to the output CSV file where transformed rows will be written. */
    private static final Path OUTPUT_PATH = Paths.get("data", "transformed_products.csv");

    /**
     * Entry point for running the ETL pipeline.
     * <p>
     * Steps performed:
     * <ol>
     *   <li>Create the output directory if it does not already exist.</li>
     *   <li>Read {@link Product} records from {@link #INPUT_PATH} using {@link CSVReader}.</li>
     *   <li>Transform records into {@link RowResult} objects via {@link ProductTransformer}.</li>
     *   <li>Write transformed results to {@link #OUTPUT_PATH} using {@link CSVWriter}.</li>
     * </ol>
     *
     * @param args command-line arguments (not used)
     */
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

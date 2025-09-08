## Assignment #2 — CSV ETL Pipeline

**How to run**
- In Eclipse: right-click `ETLPipeline.java` → Run As → Java Application
- Input: `data/products.csv` (relative path)
- Output: `data/transformed_products.csv` (overwritten each run)

**Design Notes (Reflection)**
- Transform order (exact): Uppercase `Name` → 10% discount if original `Category` is `Electronics` → round to **2 decimals (HALF_UP)** → if post-discount price > 500 and original was `Electronics`, set `Category = "Premium Electronics"` → compute `PriceRange` from final price.
- Output column order: `ProductID,Name,Price,Category,PriceRange`.
- Uses `BigDecimal` to avoid floating-point rounding issues.

**Assumptions**
- CSV fields have no embedded commas/quotes; first row is a header.
- `ProductID` is integer; `Price` is non-negative decimal.

**Error Handling**
- Missing input file prints: `ERROR: Input file not found at "data/products.csv".`
- Empty input (header-only) still writes an output file with just the header.
- Malformed rows are skipped and counted.

**Testing**
- Case A: normal sample input (6 rows) → verified output matches expected preview.
- Case B: header-only file → output contains just header.
- Case C: missing `data/products.csv` → program prints clear error and exits.

**AI & Internet Source Documentation (Positive Credit)**
- **AI use (summary):** I used an AI assistant for targeted help confirming the `BigDecimal` discount+rounding lines and the required transform order. I wrote the rest (I/O, header handling, recategorization, ranges, summary).
- **Example prompt:**  
  “Can you implement `ETLPipeline.java` code that applies a 10% discount using `BigDecimal` and then rounds to 2 decimals with `RoundingMode.HALF_UP`?”
- **Short excerpt I incorporated:**
  ```java
  price = price.multiply(new BigDecimal("0.90"));
  price = price.setScale(2, RoundingMode.HALF_UP);
  #How I used it
  #I integrated those lines into my transform step and kept my own structure, tests, and error handling.
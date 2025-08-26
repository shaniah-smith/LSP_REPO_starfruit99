# ETL Pipeline (Assignment)

**Student:** Shaniah  
**Course:** LSP — Assignment (Professor Woolfolk)

## How to Run
- In Eclipse: right-click `ETLPipeline.java` → **Run As → Java Application**.
- Input: `data/products.csv`  
- Output: `data/transformed_products.csv` (created/overwritten each run)

## Design Notes (Reflection)
**Exact transform order:**
1. Uppercase `Name`
2. If original `Category` is `Electronics`, apply **10% discount**
3. Round to **2 decimals (HALF_UP)** after discount
4. If post-discount price **> 500.00** and original category = Electronics → `Category = "Premium Electronics"`
5. Compute `PriceRange` from the **final** price (inclusive bands):
   - `0.00–10.00` → Low
   - `10.01–100.00` → Medium
   - `100.01–500.00` → High
   - `500.01+` → Premium

**Assumptions**
- CSV has no embedded commas/quotes.
- `ProductID` is an integer; `Price` is non-negative decimal.
- Invalid rows are skipped; program continues.

**Testing performed**
- Missing input file → prints clear error; does **not** create output.
- Empty input (only header) → output is only the header.
- Malformed row → skipped and counted in the summary.

## AI & Internet Source Documentation (Positive Credit)
**AI use (summary):**  
I used an AI assistant to guide Eclipse steps and generate a single-file Java solution with relative paths, explicit `BigDecimal` arithmetic, HALF_UP rounding, and the exact transform order.

**Brief example):**  
> Assistant: “Apply the **10% discount**, then **round HALF_UP to 2 decimals**, then check if the **post-discount price > 500** to set `Premium Electronics`, and finally compute `PriceRange` from the final price.”

**What changed in my code because of AI help:**  
I avoided `double` math and used:
```java
price = price.multiply(new BigDecimal("0.90"));
price = price.setScale(2, RoundingMode.HALF_UP);
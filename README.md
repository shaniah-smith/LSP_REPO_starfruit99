# ETL Pipeline (Assignment 2)

**Student:** Shaniah Smith  
**Course:** LSP — Assignment 2 (Professor Woolfolk)

---

## How to Run
- In Eclipse: right-click `ETLPipeline.java` → **Run As → Java Application**  
- Input: `data/products.csv`  
- Output: `data/transformed_products.csv` (created/overwritten each run)  

---

## Design Notes (Reflection)

**Exact transform order**
1. Uppercase the `Name` field  
2. If original `Category` is `Electronics`, apply **10% discount**  
3. Round to **2 decimals (HALF_UP)** **after** the discount  
4. If post-discount price **> 500.00** and original category was `Electronics`, recategorize as **Premium Electronics**  
5. Compute `PriceRange` from the **final price** (inclusive bands):  
   - 0.00–10.00 → Low  
   - 10.01–100.00 → Medium  
   - 100.01–500.00 → High  
   - 500.01+ → Premium  

**Assumptions**
- Input CSV has no embedded commas/quotes.  
- `ProductID` is an integer; `Price` is a non-negative decimal.  
- Invalid or malformed rows are **skipped**; program continues and reports counts.  

**Testing performed**
- Missing input file → prints clear error; no output created.  
- Empty input (only header) → output is just the header.  
- Malformed row (e.g., non-numeric price) → skipped and counted in summary.  
- Normal input → output matches expected values (e.g., Laptop → 899.99; Smartphone → 629.54).

---

## AI & Internet Source Documentation (Positive Credit)

**AI use (summary):**  
I used an AI assistant for targeted help and to **implement some `ETLPipeline.java` code**, specifically around precise monetary arithmetic and placing the rounding step correctly. I wrote the rest of the program logic myself (I/O, header handling, recategorization, price bands, and run summary).

**Example AI prompt I asked:**  
“Can you implement some `ETLPipeline.java` code that applies a 10% discount using `BigDecimal` and then rounds to two decimals with `RoundingMode.HALF_UP`?”

**AI response (short excerpt):**  
```java
price = price.multiply(new BigDecimal("0.90")); // apply 10% discount
price = price.setScale(2, RoundingMode.HALF_UP); // round to 2 decimals after discount

# How I used it:
# I incorporated those lines into my transform step to avoid double precision issues. I kept my own structure for reading/writing CSVs via relative paths, enforcing transform order, recategorizing Premium Electronics, computing PriceRange, and printing the run summary.

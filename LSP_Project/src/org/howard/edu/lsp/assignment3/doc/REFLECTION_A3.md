# Comparison of Assignment 2 and Assignment 3

## Assignment 2  
In **Assignment 2**, the ETL pipeline was implemented as a single class, `ETLPipeline`. That design centralized all responsibilities — reading input files, transforming the data, handling errors, and writing output. While it worked correctly and produced the expected `transformed_products.csv`, the design suffered from several weaknesses:

- **Monolithic structure:** The single class held too much logic, which made the program harder to read and maintain.  
- **Low cohesion:** Data transformations, file I/O, and control flow were mixed together.  
- **Minimal abstraction:** Concepts like “product,” “transformation,” or “calculation” were implicit in the code but not represented as distinct objects.  
- **Limited extensibility:** Any change, such as adding new transformations or switching input sources, would require modifying the same large class.  

---

## Assignment 3  
In **Assignment 3**, I decomposed the solution into multiple classes, each with a well-defined responsibility:

- **`Product`:** Encapsulates product attributes as an object rather than raw row data.  
- **`CSVReader`:** Handles input parsing and converts CSV rows into `Product` objects.  
- **`CSVWriter`:** Responsible for writing transformed data to the output CSV.  
- **`ProductTransformer`:** Applies transformations to `Product` objects (e.g., formatting, normalization).  
- **`PriceRangeCalculator`:** Encapsulates logic for computing price ranges, which improves testability and reuse.  
- **`RowResult`:** Represents the outcome of a transformation, capturing both success and error states.  
- **`ETLController`:** Orchestrates the pipeline, coordinating between reading, transforming, and writing.  

This decomposition makes the codebase more **modular** and aligns with fundamental object-oriented principles:  

- **Encapsulation:** Each class hides its implementation details, exposing only necessary methods (e.g., `CSVReader.read()` or `PriceRangeCalculator.calculate()`).  
- **Abstraction:** Higher-level concepts (products, transformations, results) are modeled explicitly.  
- **Polymorphism (potential):** The design supports future extensibility — for example, new transformer classes could implement a common interface.  
- **Inheritance (future use):** While not deeply used here, the design could allow different product types or specialized readers/writers to subclass the existing ones.  

---

## Benefits of the Object-Oriented Redesign

1. **Readability:** Each class now has a single purpose, which makes the code easier to understand.  
2. **Testability:** Individual components like `ProductTransformer` or `PriceRangeCalculator` can be unit-tested independently.  
3. **Extensibility:** The pipeline can be extended with minimal changes — for example, adding a new transformation step would not require rewriting the core logic.  
4. **Maintainability:** Encapsulation reduces the risk of unintended side effects when updating one part of the pipeline.  

---

## Testing and Verification

To ensure Assignment 3 produced the same results as Assignment 2, I:  

- Ran the pipeline with the same input CSV (`products.csv`) and compared the generated output file (`transformed_products.csv`) against Assignment 2’s results. Both matched exactly.  
- Verified error handling by testing with missing input files and empty input files, as required. Both versions produced the same error messages and behavior.  
- Confirmed that transformations (such as product normalization and price range calculation) were consistent with Assignment 2.  

---

## Conclusion  

Assignment 2 provided a working pipeline but demonstrated the limitations of a monolithic design. **Assignment 3** addresses those limitations through object-oriented decomposition, resulting in a cleaner, more modular, and extensible solution. This redesign demonstrates the power of encapsulation, abstraction, and modularity while still fulfilling the same functional requirements.  

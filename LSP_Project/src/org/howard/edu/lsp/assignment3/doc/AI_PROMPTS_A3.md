# AI_PROMPTS_A3.md

## Overview
As required by the assignment, this file documents several of the prompts I used with a generative AI assistant (ChatGPT) during the redesign of my ETL pipeline for Assignment 3. It also includes short excerpts of responses. These interactions helped me brainstorm object-oriented designs, generate documentation, and compare implementations.

---

## Example Prompts and Responses

### Prompt 1
**User:**  
“How can I redesign my ETL pipeline from Assignment 2 to be more object-oriented?”  

**AI Response (excerpt):**  
“You can break down the pipeline into multiple classes with single responsibilities: a `CSVReader` to parse the input, a `CSVWriter` for writing the output, a `Product` class to represent each row, and a `ProductTransformer` to handle transformations. The `ETLController` can act as the orchestrator.”

---

### Prompt 2
**User:**  
“What object-oriented concepts can I apply in my Assignment 3 redesign?”  

**AI Response (excerpt):**  
“You should focus on encapsulation (hiding implementation details inside each class), abstraction (representing concepts like Product or Transformation explicitly), and polymorphism (allowing different transformation strategies). Inheritance can also be introduced later if you extend to new product types.”

---

### Prompt 3
**User:**  
“Can you suggest a way to handle error reporting for rows that fail transformations?”  

**AI Response (excerpt):**  
“Introduce a `RowResult` class that represents either a successful transformation or an error message. This makes error handling explicit and allows the ETL pipeline to continue processing other rows without failing completely.”

---

### Prompt 4
**User:**  
“Write Javadoc comments for my `CSVReader` class.”  

**AI Response (excerpt):**  
“```java  
/**  
 * The CSVReader class is responsible for reading product data from a CSV file  
 * and converting each row into a Product object.  
 * It provides methods for handling input parsing and validation.  
 */  
```”

---

### Prompt 5
**User:**  
“Help me write the Reflection document comparing Assignment 2 and Assignment 3.”  

**AI Response (excerpt):**  
“In Assignment 2, your ETL pipeline was monolithic and procedural, while Assignment 3 is more modular and object-oriented. The new design uses encapsulation, abstraction, and polymorphism, and splits responsibilities across multiple classes…”

---

## Reflection on AI Use
I used AI primarily for:
- Brainstorming class decomposition and OO design choices.  
- Drafting initial Javadocs, which I then reviewed and edited for accuracy.  
- Structuring the Reflection document.  

I adapted AI suggestions to fit the specific requirements of the assignment and coding standards from class. The final implementation represents my own understanding, with AI as a supporting tool.  

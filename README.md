# Grocery Store - Discount and Pricing Service

This is a Spring Boot-based backend application that calculates total costs, applies discounts, and generates receipts for grocery store purchases. It supports different types of items (bread, vegetables, and beer) and provides REST APIs for interacting with the service.

### Features:
- **Item Pricing**: Set prices for different types of grocery items (bread, vegetables, beer).
- **Discount Rules**: Apply dynamic discount rules based on item types (e.g., bread age, vegetable weight, beer packs).
- **Receipt Generation**: Generate detailed receipts with price breakdowns.
- **REST APIs**: Exposes APIs for calculating the total order cost and fetching current pricing and discount rules.

---

## Prerequisites

Before you can run this application locally, ensure you have the following installed:

1. **Java 8+**: This project is built using Java 8 or later.
    - You can check if Java is installed by running:
      ```bash
      java -version
      ```

2. **Maven** (or **Gradle** if you're using it as the build tool):
    - You can check if Maven is installed by running:
      ```bash
      mvn -v
      ```

3. **IDE**: It is recommended to use an IDE like **IntelliJ IDEA** or **Eclipse** to import and run the project.

---

## Importing the Project

To start working on the project, follow these steps:

### Option 1: Clone the Repository
If the project is hosted on a Git repository (GitHub, GitLab, etc.), you can clone it by running:

```bash
git clone https://github.com/your-username/grocery-store.git
```
## Option 2: Using GitHub Desktop or Other Git Clients
If you're not comfortable using the command line, you can use a Git client like GitHub Desktop to clone the repository.

### Setting Up the Project

1. **Navigate to the Project Directory:**
   After cloning the repository, open the project directory:

   ```bash
   cd grocery-store
   ```

2. **Build and Run the Project:** Use Maven to build and run the project:
    ```bash
    mvn clean spring-boot:run
    ```
This will start a local server on http://localhost:8080.

3. **Run Unit Tests:** To ensure everything is working as expected, you can run the unit tests:
   ```bash
    mvn test
   ```
This will run all the unit tests and verify that your application is functioning correctly.

### Using the API
Once the application is running locally, you can interact with it via the following REST APIs:


#### 1. POST /api/calculateTotal
Request Body (Example):
  ```bash
{
"items": [
{ "type": "Bread", "pricePerUnit": 1.00, "ageInDays": 3 ,"quantity":3},
{ "type": "Vegetable", "pricePerUnitPer100g": 1.00, "weightInGrams": 200 },
{ "type": "Beer", "pricePerUnit": 0.50, "origin": "Dutch", "quantity": 6 }
]
}
 ```

Response:
  ```bash
{
"totalCost": 4.86
}
```

#### 2. POST /api/generateReceipt
This API generates a detailed receipt for the order, showing the price breakdown of each item.

Request Body (Example):
  ```bash
{
"items": [
{ "type": "Bread", "pricePerUnit": 1.00, "ageInDays": 3, "quantity":3},
{ "type": "Vegetable", "pricePerUnitPer100g": 1.00, "weightInGrams": 200 },
{ "type": "Beer", "pricePerUnit": 0.50, "origin": "Dutch", "quantity": 6 }
]
}
```

Response:
  ```bash
{
  Receipt:
  Bread - €2.0
  Vegetable - €1.86
  Beer - €1.0
  Total: €4.86
}
```


#### 3.GET /api/discountRules
This API returns the current discount rules applied to each item type.
Response (Example):
```bash
{
"bread": "Buy 1, Get 2 (for 3-day-old bread)",
"vegetables": "5% off for 0-100g, 7% for 100-500g, 10% off for >500g",
"beer": "€3.00 off per pack of Belgium beer, €2.00 off per pack of Dutch beer, €4.00 off per pack of German beer"
}
```


#### 4.GET /api/itemPrices
This API returns the current prices for the different types of items in the store.
```bash
{
"bread": "€1.00",
"vegetable": "€1.00 per 100g",
"beer": "€0.50 per bottle"
}
```


## How Discount Logic Works

### Bread:
- If the bread is 1 day old or newer, there is no discount.
- If the bread is 3 days old, the discount is "Buy 1, Get 2".
- If the bread is 6 days old, the discount is "Buy 1, Get 3".
- Breads older than 6 days cannot be added to the order.

### Vegetables:
- If the weight is between 0-100g, a 5% discount is applied.
- If the weight is between 100-500g, a 7% discount is applied.
- If the weight exceeds 500g, a 10% discount is applied.

### Beer:
Beers are only discounted when bought in packs of 6. The discounts are:
- €3.00 off per pack of Belgium beer.
- €2.00 off per pack of Dutch beer.
- €4.00 off per pack of German beer.

## Play with Swagger
https://localhost:8080/swagger-ui/index.html
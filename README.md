# Variate JPA API

Variate JPA API is a Spring Boot-based e-commerce application backend that provides a RESTful API for managing categories, products, orders, order items, payments, and reviews. The application uses PostgreSQL as its database, and the database name is `variatespring`.

## Features

- **Category Management**: Create, read, update, patch, and delete product categories.
- **Product Management**: Manage products under different categories with detailed information like price, description, and availability.
- **Order Management**: Manage customer orders, including creating, updating, and viewing orders.
- **Order Item Management**: Handle the individual items within an order.
- **Payment Management**: Manage payment details related to customer orders.
- **Review Management**: Manage customer reviews on products.

## Technologies Used

- **Java 22**
- **Spring Boot 3.3.3**
- **Spring Data JPA**
- **Spring Security**
- **PostgreSQL**
- **H2 (for in-memory testing)**
- **ModelMapper**
- **JWT (for authentication and security)**
- **Maven** (for dependency management)

## Database

The project uses a PostgreSQL database called `variatespring`. Ensure PostgreSQL is installed and configured on your local environment. You can adjust the database settings in the `application.properties` file.

## Endpoints

### Category Endpoints

- `POST /api/categories`: Create a new category
- `GET /api/categories/{id}`: Get category by ID
- `GET /api/categories`: Get all categories
- `PUT /api/categories/{id}`: Update an existing category
- `PATCH /api/categories/{id}`: Partially update a category
- `DELETE /api/categories/{id}`: Delete a category

### Product Endpoints

- `POST /api/products`: Create a new product
- `GET /api/products/{id}`: Get product by ID
- `GET /api/products`: Get all products
- `PUT /api/products/{id}`: Update a product
- `PATCH /api/products/{id}`: Partially update a product
- `DELETE /api/products/{id}`: Delete a product

### Order Endpoints

- `POST /api/orders`: Create a new order
- `GET /api/orders/{id}`: Get order by ID
- `GET /api/orders`: Get all orders
- `PUT /api/orders/{id}`: Update an order
- `PATCH /api/orders/{id}`: Partially update an order
- `DELETE /api/orders/{id}`: Delete an order

### Order Item Endpoints

- `POST /api/order-items`: Create a new order item
- `GET /api/order-items/{id}`: Get order item by ID
- `GET /api/order-items`: Get all order items
- `PUT /api/order-items/{id}`: Update an order item
- `PATCH /api/order-items/{id}`: Partially update an order item
- `DELETE /api/order-items/{id}`: Delete an order item

### Payment Endpoints

- `POST /api/payments`: Create a new payment
- `GET /api/payments/{id}`: Get payment by ID
- `GET /api/payments`: Get all payments
- `PUT /api/payments/{id}`: Update a payment
- `PATCH /api/payments/{id}`: Partially update a payment
- `DELETE /api/payments/{id}`: Delete a payment

### Review Endpoints

- `POST /api/reviews`: Create a new review
- `GET /api/reviews/{id}`: Get review by ID
- `GET /api/reviews`: Get all reviews
- `PUT /api/reviews/{id}`: Update a review
- `PATCH /api/reviews/{id}`: Partially update a review
- `DELETE /api/reviews/{id}`: Delete a review

## Running the Application

To run this application locally, follow these steps:

1. Clone the repository:
   ```bash
   git clone https://github.com/7irelo/variate-jpa-api.git
   ```

2. Navigate to the project directory:
   ```bash
   cd variate-jpa-api
   ```

3. Configure PostgreSQL in `application.properties` with your database credentials:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/variatespring
   spring.datasource.username=yourUsername
   spring.datasource.password=yourPassword
   ```

4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

## Development

This project uses Maven for dependency management. If you're developing locally, you can utilize the included dev tools for a better development experience.

### Dependencies

- `spring-boot-starter-web`
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-security`
- `spring-boot-devtools` (for live reloading during development)
- `postgresql` (PostgreSQL JDBC Driver)
- `modelmapper` (for DTO mapping)
- `jjwt` (for JWT-based authentication)

## Contributing

1. Fork the repository.
2. Create your feature branch: `git checkout -b feature/new-feature`.
3. Commit your changes: `git commit -m 'Add some feature'`.
4. Push to the branch: `git push origin feature/new-feature`.
5. Open a pull request.

## License

This project is licensed under the MIT License.

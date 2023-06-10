# Mini Automotive Parts Sales E-Commerce Project

This project is an example of a mini automotive parts sales e-commerce application. It provides a set of APIs for managing products, shopping cart items, orders, and user authentication. The APIs are documented using the OpenAPI 3 specification and can be explored using Swagger UI.

## API Documentation

The API documentation is available in the Swagger UI format. You can access the documentation by running the application and visiting the following URL in your browser:

http://localhost:8086/swagger-ui.html


## API Endpoints

### Product Controller

- **PUT /api/v1/product/{productId}**
    - Update a product by ID

- **DELETE /api/v1/product/{productId}**
    - Delete a product by ID

- **GET /api/v1/product**
    - Get all products

- **POST /api/v1/product**
    - Save a new product

- **GET /api/v1/product/filter**
    - Sort all products

### Cart Item Controller

- **PUT /api/v1/cart/{id}**
    - Update the quantity of a cart item

- **GET /api/v1/cart/{userId}/items**
    - List all cart items by user ID

- **POST /api/v1/cart/{userId}/items**
    - Add an item to the cart

- **DELETE /api/v1/cart/items/{id}**
    - Delete an item from the cart by ID

### Order Controller

- **POST /api/v1/orders**
    - Place an order

### Authentication Controller

- **POST /api/v1/auth/register**
    - Register a new user

- **POST /api/v1/auth/login**
    - Authenticate and login a user

### User Controller

- **GET /api/v1/activation/activate**
    - Activate user account

## Data Models

The following data models are used in the API:

- ProductRequest
- ProductResponse
- CartItemRequest
- OrderRequest
- CreditCard
- GrantedAuthority
- Order
- OrderItem
- Product
- User
- RegisterRequest
- RegisterResponse
- LoginRequest
- LoginResponse
- CartItemResponse


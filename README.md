# Togakade POS System

## Overview
Togakade POS System is a web-based Point of Sale (POS) application developed to handle basic operations such as adding, searching, updating, and deleting records. The frontend is built using HTML, CSS, JavaScript, and jQuery, while the backend is powered by Java EE with Tomcat.

## Features
### Customer Management:
- **Add new customers**
- **Search existing customers**
- **Update customer information**
- **Delete customers**

### Item Management:
- **Add new items to inventory**
- **Search existing items**
- **Update item information**
- **Delete items from inventory**

### Order Management:
- **Create new orders**
- **Retrieve and view existing orders**

## Technologies Used
- **Frontend:** HTML, CSS, JavaScript, jQuery
- **Backend:** Java EE, Tomcat
- **API Documentation:** [Postman Documentation](https://documenter.getpostman.com/view/35384192/2sA3s1oXj9)

## Controllers and Endpoints

### Customer Controller
- **Add Customer:** `POST /customer` - Adds a new customer.
- **Search Customer:** `GET /customer/{id}` - Retrieves customer details by ID.
- **Update Customer:** `PUT /customer/{id}` - Updates the details of an existing customer.
- **Delete Customer:** `DELETE /customer/{id}` - Deletes a customer by ID.
- **Get Customers:** `GET /customer` - Retrieves a list of all customers.

### Item Controller
- **Add Item:** `POST /item` - Adds a new item to the inventory.
- **Search Item:** `GET /item/{id}` - Retrieves item details by ID.
- **Update Item:** `PUT /item/{id}` - Updates the details of an existing item.
- **Delete Item:** `DELETE /item/{id}` - Deletes an item by ID.
- **Get Items:** `GET /item` - Retrieves a list of all items.

### Order Controller
- **Add Order:** `POST /order` - Creates a new order.
- **Get Orders:** `GET /order` - Retrieves a list of all orders.

## Getting Started
1. **Prerequisites:** Ensure you have a Java EE environment set up with Tomcat installed.
2. **Clone the Repository:** `git clone https://github.com/amliyanage/Thogakade-Pos-System.git`
3. **Setup Database:** Configure your database settings in `application.properties`.
4. **Build and Run:** Deploy the WAR file to Tomcat and access the application.

## API Documentation
Refer to the [Postman Documentation](https://documenter.getpostman.com/view/35384192/2sA3s1oXj9) for detailed API endpoints and usage instructions.

## License
This project is licensed under the [MIT License](LICENSE).

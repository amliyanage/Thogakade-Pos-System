Togakade POS System
Overview
Togakade POS System is a web-based Point of Sale (POS) application developed to handle basic operations such as adding, searching, updating, and deleting records. The frontend is built using HTML, CSS, JavaScript, and jQuery, while the backend is powered by Java EE with Tomcat.

Features
Customer Management:
Add new customers
Search existing customers
Update customer information
Delete customers
Item Management:
Add new items to inventory
Search existing items
Update item information
Delete items from inventory
Order Management:
Create new orders
Retrieve and view existing orders
Technologies Used
Frontend: HTML, CSS, JavaScript, jQuery
Backend: Java EE, Tomcat
API Documentation: Postman Documentation
Controllers and Endpoints
Customer Controller
Add Customer: POST /customers - Adds a new customer.
Search Customer: GET /customers/{id} - Retrieves customer details by ID.
Update Customer: PUT /customers/{id} - Updates the details of an existing customer.
Delete Customer: DELETE /customers/{id} - Deletes a customer by ID.
Item Controller
Add Item: POST /items - Adds a new item to the inventory.
Search Item: GET /items/{id} - Retrieves item details by ID.
Update Item: PUT /items/{id} - Updates the details of an existing item.
Delete Item: DELETE /items/{id} - Deletes an item by ID.
Order Controller
Add Order: POST /orders - Creates a new order.
Get Orders: GET /orders - Retrieves a list of all orders.
Getting Started
Prerequisites: Ensure you have a Java EE environment set up with Tomcat installed.
Clone the Repository: git clone https://github.com/your-repo-name
Setup Database: Configure your database settings in application.properties.
Build and Run: Deploy the WAR file to Tomcat and access the application.
API Documentation
Refer to the Postman Documentation for detailed API endpoints and usage instructions.

Contributing
Contributions are welcome! Please fork the repository and submit pull requests.

License
This project is licensed under the MIT License.

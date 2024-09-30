#🛒 eCommerce Full Stack Application - Microservices Architecture (Spring Boot, MySQL, MongoDB, Angular)

##This is a full-stack eCommerce application built using microservices architecture, with the backend developed in Spring Boot, MySQL, and MongoDB for database storage, and the frontend in Angular. The system also incorporates Kafka for event-driven communication, Keycloak for authentication and authorization, Resilience4J for circuit breakers and fault tolerance, and Docker for containerized services.

#🗂️ Table of Contents
Tech Stack
Core Features
Microservices Architecture
System Components
Backend Setup
Frontend Setup
Database Setup
Kafka Setup
API Gateway and Service Discovery
Docker Setup for Keycloak, Zipkin, and Kafka
Running the Application
Contributing
🖥️ Tech Stack
Backend
Frontend
🌟 Core Features
Microservices Architecture: Independent services for products, orders, inventory, and users.
API Gateway: A single entry point to the microservices using Spring Cloud Gateway.
Service Discovery: Eureka for dynamic service registration.
Authentication & Authorization: Keycloak for role-based access control.
Event-Driven Communication: Using Kafka for inter-service communication.
Circuit Breakers: Integrated Resilience4J for circuit breakers, retries, and fault tolerance.
Distributed Tracing: Using Zipkin for tracing requests across services.
Angular Frontend: A responsive frontend application built with Angular and Angular Material.
🏗️ Microservices Architecture
This project consists of several microservices, each responsible for a specific business domain:

Product Service: Manages the product catalog.
Inventory Service: Tracks inventory and stock levels.
Order Service: Manages orders and processes payments.
API Gateway: Routes requests to the appropriate service.
Auth Service (Keycloak): Handles authentication and authorization.
Notification Service (Optional): Sends notifications such as order confirmations.
🔧 System Components
1. Backend Setup
The backend consists of multiple microservices, each responsible for specific functionality (e.g., Product Service, Inventory Service). These services communicate via REST and Kafka events.

Steps to Run the Backend:
Clone the Repository:
bash
Copy code
git clone https://github.com/adityasarfare/springboot-microservices.git
cd springboot-microservices
Install Maven Dependencies: Run the following in each microservice's directory to install the necessary dependencies:
bash
Copy code
mvn clean install
Configure Application Properties: Update the application.properties or application.yml in each microservice to connect to your MySQL and MongoDB databases (see Database Setup for details).
Run the Microservices: To start the individual microservices (e.g., Product Service), run:
bash
Copy code
mvn spring-boot:run
Repeat this for all microservices: Product Service, Inventory Service, Order Service, API Gateway, etc.
Start Kafka and Zookeeper: Ensure Kafka and Zookeeper are running. If not, see the Kafka Setup section.
Run Eureka Service: Make sure the Eureka Server is running to handle service discovery.
2. Frontend Setup
The frontend of the eCommerce platform is built using Angular and communicates with the backend via REST APIs exposed by the API Gateway.

Steps to Run the Frontend:
Install Node.js: Download and install Node.js from here.
Install Angular CLI: Run the following command to install the Angular CLI globally:
bash
Copy code
npm install -g @angular/cli
Navigate to the Frontend Directory:
bash
Copy code
cd frontend
Install Node Modules: Install the required dependencies by running:
bash
Copy code
npm install
Configure API Endpoints: Update the API endpoint URLs in the frontend code to point to your backend API Gateway (e.g., http://localhost:8080).
Run the Frontend Application: Start the Angular development server:
bash
Copy code
ng serve
Access the Frontend: Once the frontend is running, open your browser and navigate to:
bash
Copy code
http://localhost:4200
3. Database Setup
MySQL (For Relational Data):
Install MySQL: Install MySQL server from here.
Create Databases:
sql
Copy code
CREATE DATABASE product_db;
CREATE DATABASE order_db;
Configure the Connection: In the application.properties or application.yml files of each microservice, update the MySQL configuration:
properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/product_db
spring.datasource.username=root
spring.datasource.password=password
MongoDB (For Non-Relational Data):
Install MongoDB: Install MongoDB from here.
Run MongoDB:
bash
Copy code
mongod
Configure the Connection: In the application.properties of microservices that use MongoDB (e.g., Inventory Service), update the MongoDB connection settings:
properties
Copy code
spring.data.mongodb.uri=mongodb://localhost:27017/inventory_db
4. Kafka Setup
Kafka is used for event-driven communication between the microservices.

Steps to Run Kafka and Zookeeper:
Install Kafka: Download Kafka from here.
Run Zookeeper:
bash
Copy code
bin/zookeeper-server-start.sh config/zookeeper.properties
Run Kafka:
bash
Copy code
bin/kafka-server-start.sh config/server.properties
Create Kafka Topics:
bash
Copy code
bin/kafka-topics.sh --create --topic order-events --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
5. API Gateway and Service Discovery
API Gateway:
The API Gateway is the single entry point for the frontend application. It routes requests to appropriate backend microservices based on the URL path.

Run the API Gateway service:
bash
Copy code
cd api-gateway
mvn spring-boot:run
Eureka Service Discovery:
Eureka handles service registration and discovery.

Run the Eureka server:
bash
Copy code
cd eureka-server
mvn spring-boot:run
6. Docker Setup for Keycloak, Zipkin, and Kafka
To streamline running Keycloak, Zipkin, and Kafka, we will use Docker containers.

Docker Setup
Keycloak: Keycloak is used for managing authentication and authorization.

Create a docker-compose.yml file for Keycloak:

yaml
Copy code
version: '3'
services:
  keycloak:
    image: jboss/keycloak:latest
    environment:
      - KEYCLOAK_USER=admin
      - KEYCLOAK_PASSWORD=admin
    ports:
      - 8080:8080
Start Keycloak using Docker Compose:

bash
Copy code
docker-compose up -d keycloak
Zipkin: Zipkin is used for distributed tracing across services.

Pull the Zipkin image and run it using Docker:

bash
Copy code
docker run -d -p 9411:9411 openzipkin/zipkin
The Zipkin UI will be accessible at:

bash
Copy code
http://localhost:9411
Kafka: We can use Docker to set up Kafka and Zookeeper together.

Create a docker-compose.yml file for Kafka and Zookeeper:

yaml
Copy code
version: '3'
services:
  zookeeper:
    image: wurstmeister/zookeeper:3.4.6
    ports:
      - "2181:2181"
  kafka:
    image: wurstmeister/kafka:latest
    ports:
      - "9092:9092"
    environment:
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
Start Kafka and Zookeeper:

bash
Copy code
docker-compose up -d
🚀 Running the Application
Run Docker Containers: Start the Docker containers for Keycloak, Zipkin, and Kafka using Docker Compose.

Run the Backend Microservices: Follow the steps in Backend Setup to start each microservice.

Run the Frontend: Follow the steps in Frontend Setup to start the Angular application.

Access Services:

Keycloak: http://localhost:8181
Zipkin: http://localhost:9411
Frontend: http://localhost:4200
🤝 Contributing
Contributions are welcome! Feel free to submit a pull request or open an issue to improve this project.

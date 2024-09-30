# eCommerce Full Stack Application - Microservices Architecture (Spring Boot, MySQL, MongoDB, Angular)

## Overview

This is a full-stack eCommerce application built using microservices architecture, with the backend developed in Spring Boot, MySQL, and MongoDB for database storage, and the frontend in Angular. The system also incorporates Kafka for event-driven communication, Keycloak for authentication and authorization, and Resilience4J for circuit breakers and fault tolerance.

## Table of Contents

- [Tech Stack](#tech-stack)
- [Core Features](#core-features)
- [Microservices Architecture](#microservices-architecture)
- [System Components](#system-components)
  - [Backend Setup](#backend-setup)
  - [Frontend Setup](#frontend-setup)
  - [Database Setup](#database-setup)
  - [Kafka Setup](#kafka-setup)
  - [API Gateway and Service Discovery](#api-gateway-and-service-discovery)
- [Running the Application](#running-the-application)
- [Contributing](#contributing)

## Tech Stack

### Backend

- Spring Boot
- Spring Cloud Gateway
- Spring Cloud Eureka (Service Discovery)
- Spring Security (with Keycloak for OAuth2 Authentication)
- Kafka (for Event-Driven Communication)
- MySQL (for relational data storage)
- MongoDB (for non-relational data)
- Resilience4J (for circuit breakers and fault tolerance)
- Zipkin (for distributed tracing)
- Docker (for containerization)

### Frontend

- Angular (for the frontend UI)
- Angular Material (for responsive UI components)

## Core Features

- **Microservices Architecture**: Independent services for products, orders, inventory, and users.  
- **API Gateway**: A single entry point to the microservices using Spring Cloud Gateway.  
- **Service Discovery**: Eureka for dynamic service registration.  
- **Authentication & Authorization**: Keycloak for role-based access control.  
- **Event-Driven Communication**: Using Kafka for inter-service communication.  
- **Circuit Breakers**: Integrated Resilience4J for circuit breakers, retries, and fault tolerance.  
- **Distributed Tracing**: Using Zipkin for tracing requests across services.  
- **Angular Frontend**: A responsive frontend application built with Angular and Angular Material.  

## Microservices Architecture

This project consists of several microservices, each responsible for a specific business domain:

- **Product Service**: Manages the product catalog.  
- **Inventory Service**: Tracks inventory and stock levels.  
- **Order Service**: Manages orders and processes payments.  
- **API Gateway**: Routes requests to the appropriate service.  
- **Auth Service (Keycloak)**: Handles authentication and authorization.  
- **Notification Service (Optional)**: Sends notifications such as order confirmations.  

## System Components

### 1. Backend Setup

The backend consists of multiple microservices, each responsible for specific functionality (e.g., Product Service, Inventory Service). These services communicate via REST and Kafka events.

#### Steps to Run the Backend

1. **Clone the Repository**:

    ```bash
    git clone https://github.com/adityasarfare/springboot-microservices.git
    cd springboot-microservices
    ```

2. **Install Maven Dependencies**: Run the following in each microservice's directory to install the necessary dependencies:

    ```bash
    mvn clean install
    ```

3. **Configure Application Properties**: Update the `application.properties` or `application.yml` in each microservice to connect to your MySQL and MongoDB databases (see Database Setup for details).

4. **Run the Microservices**: To start the individual microservices (e.g., Product Service), run:

    ```bash
    mvn spring-boot:run
    ```

   Repeat this for all microservices: Product Service, Inventory Service, Order Service, API Gateway, etc.

5. **Start Kafka and Zookeeper**: Ensure Kafka and Zookeeper are running. If not, see the Kafka Setup section.

6. **Run Eureka Service**: Make sure the Eureka Server is running to handle service discovery.

### 2. Frontend Setup

The frontend of the eCommerce platform is built using Angular and communicates with the backend via REST APIs exposed by the API Gateway.

#### Steps to Run the Frontend

1. **Install Node.js**: Download and install Node.js from [here](https://nodejs.org/).

2. **Install Angular CLI**: Run the following command to install the Angular CLI globally:

    ```bash
    npm install -g @angular/cli
    ```

3. **Navigate to the Frontend Directory**: Go to the frontend directory inside the project:

    ```bash
    cd frontend
    ```

4. **Install Node Modules**: Install the required dependencies by running:

    ```bash
    npm install
    ```

5. **Configure API Endpoints**: Update the API endpoint URLs in the frontend code to point to your backend API Gateway (e.g., http://localhost:8080).

6. **Run the Frontend Application**: Start the Angular development server:

    ```bash
    ng serve
    ```

7. **Access the Frontend**: Once the frontend is running, open your browser and navigate to:

    ```bash
    http://localhost:4200
    ```

### 3. Database Setup

#### MySQL (For Relational Data)

1. **Install MySQL**: Install MySQL server from [here](https://www.mysql.com/).

2. **Create Databases**: Create the required databases (for example, `product_db`, `order_db`, etc.):

    ```sql
    CREATE DATABASE product_db;
    CREATE DATABASE order_db;
    ```

3. **Configure the Connection**: In the `application.properties` or `application.yml` files of each microservice, update the MySQL configuration:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/product_db
    spring.datasource.username=root
    spring.datasource.password=password
    ```

#### MongoDB (For Non-Relational Data)

1. **Install MongoDB**: Install MongoDB from [here](https://www.mongodb.com/).

2. **Run MongoDB**: Start the MongoDB service:

    ```bash
    mongod
    ```

3. **Create Databases (if necessary)**: You can use MongoDB shell or MongoDB Compass to create databases as required.

4. **Configure the Connection**: In the `application.properties` of microservices that use MongoDB (e.g., Inventory Service), update the MongoDB connection settings:

    ```properties
    spring.data.mongodb.uri=mongodb://localhost:27017/inventory_db
    ```

### 4. Kafka Setup

Kafka is used for event-driven communication between the microservices (for example, sending inventory updates when an order is placed).

#### Steps to Run Kafka and Zookeeper:

1. **Install Kafka**: Download Kafka from [here](https://kafka.apache.org/downloads).

2. **Run Zookeeper**: Kafka requires Zookeeper to manage brokers. Start Zookeeper with the following command:

    ```bash
    bin/zookeeper-server-start.sh config/zookeeper.properties
    ```

3. **Run Kafka**: Once Zookeeper is running, start Kafka:

    ```bash
    bin/kafka-server-start.sh config/server.properties
    ```

4. **Create Kafka Topics**: Create the necessary topics for your application:

    ```bash
    bin/kafka-topics.sh --create --topic order-events --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
    ```

5. **Configure Kafka in Microservices**: In your microservices, configure the `application.properties` to connect to the Kafka server:

    ```properties
    spring.kafka.bootstrap-servers=localhost:9092
    ```

### 5. API Gateway and Service Discovery

#### API Gateway:

The API Gateway is the single entry point for the frontend application. It routes requests to appropriate backend microservices based on the URL path.

1. **Run the API Gateway service**:

    ```bash
    cd api-gateway
    mvn spring-boot:run
    ```

#### Eureka Service Discovery:

Eureka handles service registration and discovery, allowing services to dynamically find each other.

1. **Run the Eureka server**:

    ```bash
    cd eureka-server
    mvn spring-boot:run
    ```

## Running the Application

1. **Run the Backend Microservices**: Follow the steps in Backend Setup to start each microservice.
2. **Run Kafka**: Ensure Kafka and Zookeeper are running, as described in Kafka Setup.
3. **Run the API Gateway and Eureka**: Start the API Gateway and Eureka for routing and service discovery.
4. **Run the Frontend Application**: Start the Angular frontend by following the steps in Frontend Setup.

### Access the Application:

- **Frontend**: [http://localhost:4200](http://localhost:4200)  
- **Backend API Gateway**: [http://localhost:8080](http://localhost:8080)  
- **Keycloak**: [http://localhost:8181](http://localhost:8181)  
- **Zipkin**: [http://localhost:9411](http://localhost:9411)  

## Contributing

Contributions are welcome! Please create an issue or submit a pull request with your improvements.

---

### Docker Setup for Keycloak, Zipkin, and Kafka

To set up Keycloak, Zipkin, and Kafka using Docker, you can use the following commands. Ensure you have Docker installed on your machine.

1. **Start Keycloak**:

    ```bash
    docker run -d --name keycloak -p 8181:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin -e DB_VENDOR=h2 jboss/keycloak
    ```

2. **Start Zipkin**:

    ```bash
    docker run -d --name zipkin -p 9411:9411 openzipkin/zipkin
    ```

3. **Start Kafka and Zookeeper**:

    You can also set up Kafka using Docker:

    ```bash
    docker run -d --name zookeeper -p 2181:2181 zookeeper
    docker run -d --name kafka --link zookeeper -p 9092:9092 wurstmeister/kafka
    ```

By following these steps, you'll be able to run your eCommerce application locally using Docker containers for Keycloak, Zipkin, and Kafka.

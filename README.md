# Spring Boot Application with Redis Docker Setup
===============================================

- This project demonstrates how to run a Spring Boot application in Docker, connecting it to a Redis database also running in Docker. This setup uses Docker Compose for container orchestration.
----

## Prerequisites
-------------

Before you begin, ensure you have the following installed:

- Docker Engine
- Docker Compose

## Getting Started
---------------

Follow these steps to set up and run the project:

1. Clone the Repository:
-   git clone <repository-url>
-   cd <project-directory>
----
2. Build the Spring Boot Application:
- ./mvnw clean package
----
3. Create a Docker Network
- docker network create spring-redis-network
----
4. Start Redis Container
- docker run -d --name redis-container --network spring-redis-network redis
----
5. Configure and Start the Spring Boot Application:
- docker-compose up -d
----
6. Verify Containers
- docker ps
----
7. Access the Spring Boot Application:
- curl -X POST http://localhost:9090/v1.0.0/apis/db/execute -H "Content-Type: application/json" -d "\"CREATE TABLE users (id INTEGER, name STRING)\""
- curl -X POST http://localhost:9090/v1.0.0/apis/db/execute -H "Content-Type: application/json" -d "\"INSERT INTO users VALUES (1, 'John Doe')\""
----
 ## Configuration:
 1. SPRING_REDIS_HOST: redis-container
 2. SPRING_REDIS_PORT: 6379
-----
## License:
- This project is licensed under the MIT License.

# Order Management Spring Boot Application

This project is a simple e-commerce platform built using Spring Boot and integrated with a PostgreSQL database. It provides REST API's for managing orders and order-items.

## Requirements

The fully fledged server uses the following:

- Java 11
- Maven 3.1.1 or higher
- PostgreSQL database server
- Docker

## Dependencies

There are a number of third-party dependencies used in the project. Browse the Maven `pom.xml` file for details of libraries and versions used.

## Configuration

- Configure the database connection in `src/main/resources/application.properties`.

```properties
spring.datasource.url=jdbc:postgresql://postgres:5432/order_management_db
spring.datasource.username=postgres
spring.datasource.password=Techm@123
```

- Configure the commands required to assemble images of the application in `DockerFile`.

```properties
FROM openjdk:11-oracle
COPY target/*.jar ordermanagement.jar
EXPOSE 8989
ENTRYPOINT ["java", "-jar", "/ordermanagement.jar"]
```
 
- Configure your Docker application’s services, networks, volumes etc in `docker-compose.yml` file.

```properties
services:
  postgres:
    image: postgres:latest
    environment:
      POSTGRES_PASSWORD: Techm@123
      POSTGRES_USER: postgres
      POSTGRES_DB: order_management_db
    ports:
      - 5432:5432
  
  ordermanagement:
    container_name: ordermanagement_con
    image: ordermanagement:latest
    build: .
    ports:
      - 8989:8989
    depends_on:
      - postgres
    
volumes:
  postgres-data:
```

## Building the project

Clone the project and use Maven to build the server.

```bash
$ mvn clean install -DskipTests
```

## Running the application on Docker

To run the application in a Docker container, follow these steps:

- Open terminal and traverse to Project path.

```bash
$ ProjectOrderManagement>
```

- Run the Docker container:

```bash
$ docker compose up -d
```

- Check whether the Docker containers are up and running

```bash
$ docker ps
```
The application will be accessible at [http://localhost:8989](http://localhost:8989)

## API Documentation

You can access the API documentation using Swagger UI:

- Swagger UI: [http://localhost:8989/swagger-ui/index.html](http://localhost:8989/swagger-ui.html)

The documentation provides details about available endpoints, request/response formats, and allows you to interact with the API directly.


## Error Handling

The application handles errors gracefully and returns appropriate HTTP status codes for different scenarios.
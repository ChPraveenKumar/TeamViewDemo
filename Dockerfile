# Use the official OpenJDK base image
   FROM openjdk:11-jre-slim

 

   # Set the working directory inside the container
   WORKDIR /app

 

   # Copy the compiled Spring Boot JAR to the container
   COPY target/*.jar app.jar

 

   # Expose the port your Spring Boot app runs on
   EXPOSE 8080

 

   # Command to run your Spring Boot application
   CMD ["java", "-jar", "app.jar"]
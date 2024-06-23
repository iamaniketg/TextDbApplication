#FROM openjdk:21
#LABEL key="dbapp"
#ARG JAR_FILE=target/dbapp.jar
#COPY ${JAR_FILE} dbapp.jar
#EXPOSE 8080
#ENTRYPOINT ["java" ,"-jar","/dbapp.jar"]

# Use the official OpenJDK image as the base image
FROM openjdk:21

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/dbapp.jar dbapp.jar

# Expose the port that the application runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "dbapp.jar"]

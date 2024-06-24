#FROM openjdk:21
#LABEL key="dbapp"
#ARG JAR_FILE=target/dbapp.jar
#COPY ${JAR_FILE} dbapp.jar
#EXPOSE 8080
#ENTRYPOINT ["java" ,"-jar","/dbapp.jar"]

FROM openjdk:17-alpine
WORKDIR /app
COPY target/dbapp.jar dbapp.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "dbapp.jar"]

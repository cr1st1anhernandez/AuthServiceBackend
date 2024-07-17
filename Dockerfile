FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY build/libs/auth-service-0.0.1-SNAPSHOT.jar /app/auth-service.jar
EXPOSE 8080
CMD ["java", "-jar", "auth-service.jar"]

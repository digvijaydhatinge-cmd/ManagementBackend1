# Stage 1: Build
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
# Note: We will fix the folder path in Render settings
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

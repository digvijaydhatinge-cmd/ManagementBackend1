# Step 1: Build the app using a reliable image
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy files from the current folder (StudBackend)
COPY pom.xml .
COPY src ./src

# Build the jar file
RUN mvn clean package -DskipTests

# Step 2: Run the app
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy the built jar from the first stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

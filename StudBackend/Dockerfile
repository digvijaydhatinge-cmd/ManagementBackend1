# Step 1: Build the app
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the pom and source from the StudBackend folder
COPY StudBackend/pom.xml .
COPY StudBackend/src ./src

# Build the jar
RUN mvn clean package -DskipTests

# Step 2: Run the app
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

# Use Java 21
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy all project files
COPY . .

RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose port (Render will override with $PORT)
EXPOSE 8080

# Run the jar
CMD ["sh", "-c", "java -jar target/*.jar"]


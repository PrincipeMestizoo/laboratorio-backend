# ---- Stage 1: Build ----
FROM eclipse-temurin:21-jdk-alpine AS builder

# Instalar Maven directamente (evita problema con .mvn wrapper vacío)
RUN apk add --no-cache maven

WORKDIR /app

# Copiar pom.xml primero para cachear dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar código fuente
COPY src src

# Build sin tests
RUN mvn package -DskipTests -B

# ---- Stage 2: Runtime ----
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENV DB_URL=jdbc:mysql://localhost:3306/productodb \
    DB_USER=root \
    DB_PASS=root \
    CORS_ORIGINS=http://localhost:4200

ENTRYPOINT ["java", "-jar", "app.jar"]
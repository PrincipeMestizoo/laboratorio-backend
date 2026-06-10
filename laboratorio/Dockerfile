# ---- Stage 1: Build ----
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

# Copiar archivos de Maven primero (mejor cache)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Dar permisos al wrapper
RUN chmod +x mvnw

# Descargar dependencias (se cachea si pom.xml no cambia)
RUN ./mvnw dependency:go-offline -B

# Copiar código fuente
COPY src src

# Build sin tests para producción
RUN ./mvnw package -DskipTests -B

# ---- Stage 2: Runtime ----
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copiar solo el JAR generado
COPY --from=builder /app/target/*.jar app.jar

# Render asigna el puerto dinámicamente vía $PORT
EXPOSE 8080

# Variables de entorno con valores por defecto (Render las sobreescribe)
ENV DB_URL=jdbc:mysql://localhost:3306/productodb \
    DB_USER=root \
    DB_PASS=root \
    CORS_ORIGINS=http://localhost:4200

# Ejecutar con perfil de producción
ENTRYPOINT ["java", "-jar", "app.jar"]

# Usar la imagen oficial de Eclipse Temurin
FROM eclipse-temurin:21-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar SOLO lo necesario explícitamente
COPY gradlew ./gradlew
COPY gradle ./gradle
COPY build.gradle settings.gradle ./
COPY src ./src

# Ver para depurar qué hay en /app
RUN ls -la

# Dar permiso de ejecución al wrapper de Gradle
RUN chmod +x gradlew

# Construir el proyecto (sin tests)
RUN ./gradlew clean build -x test

# Ejecutar el JAR generado (coge el único JAR de build/libs)
CMD ["sh", "-c", "java -jar build/libs/*.jar"]

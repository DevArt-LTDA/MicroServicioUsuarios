# Usar la imagen oficial de Eclipse Temurin
FROM eclipse-temurin:21-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar TODO el proyecto del módulo Usuarios
COPY . ./

# Dar permiso de ejecución al wrapper de Gradle
RUN chmod +x gradlew

# Construir el JAR ejecutable de Spring Boot (bootJar), sin tests
RUN ./gradlew bootJar -x test

# Ejecutar el JAR correcto (excluyendo el -plain.jar)
CMD ["sh", "-c", "java -jar $(ls build/libs/*.jar | grep -v plain | head -n 1)"]

# Usar la imagen oficial de Eclipse Temurin (Java 21)
FROM eclipse-temurin:21-jdk-alpine

# Directorio base dentro del contenedor
WORKDIR /app

# Copiar todo el repo al contenedor
COPY . ./

# Ir al módulo Usuarios (donde está gradlew, build.gradle, src, etc.)
WORKDIR /app/Usuarios

# Dar permiso de ejecución al wrapper de Gradle
RUN chmod +x gradlew

# Construir el JAR ejecutable de Spring Boot (bootJar), sin tests
RUN ./gradlew bootJar -x test

# Ejecutar el JAR correcto (excluyendo el -plain.jar)
CMD ["sh", "-c", "java -jar $(ls build/libs/*.jar | grep -v plain | head -n 1)"]

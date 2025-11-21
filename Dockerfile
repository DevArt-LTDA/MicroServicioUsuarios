FROM eclipse-temurin:21-jdk-alpine

# Carpeta base dentro del contenedor
WORKDIR /DEVART

# Copiar todo el repo
COPY . ./

# Ya estás en la raíz del proyecto Gradle (donde está gradlew)
# Construir solo el módulo Usuarios
RUN ./gradlew :Usuarios:bootJar -x test

# Ejecutar el JAR del módulo Usuarios
CMD ["sh", "-c", "java -jar Usuarios/build/libs/*.jar"]

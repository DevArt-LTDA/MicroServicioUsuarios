FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app
COPY . ./
RUN chmod +x gradlew
RUN ./gradlew :Usuarios:bootJar -x test
CMD ["sh", "-c", "java -jar $(ls Usuarios/build/libs/*.jar | grep -v plain | head -n 1)"]

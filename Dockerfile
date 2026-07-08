FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app
COPY ../../../../Proyecto/Ultima%20actualizacion/biblioteca-api/pom.xml .
COPY ../../../../Proyecto/Ultima%20actualizacion/biblioteca-api/src ./src
COPY ../../../../Proyecto/Ultima%20actualizacion/biblioteca-api/mvnw .
COPY ../../../../Proyecto/Ultima%20actualizacion/biblioteca-api/.mvn ./.mvn
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
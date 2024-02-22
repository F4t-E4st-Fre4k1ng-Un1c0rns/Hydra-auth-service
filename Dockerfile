FROM maven:3.9.6-sapmachine-21 AS build

COPY pom.xml ./
COPY .mvn .mvn

COPY src src

RUN mvn clean install

FROM eclipse-temurin:21-jre-jammy 

COPY --from=build target/*.jar app/ays-be.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/ays-be.jar"]
FROM maven:3.8.6-eclipse-temurin-11-alpine AS build

MAINTAINER cherrysoft.com

WORKDIR /app

# Download maven dependencies
COPY ./crawler-service/pom.xml .

RUN mvn verify --fail-never

# Build Crawler service application
COPY ./crawler-service/ .

RUN mvn clean package -DskipTests

# Running Crawler service application
FROM amazoncorretto:11-alpine3.16

COPY --from=build /app/target/crawler-service-0.0.1-SNAPSHOT.jar /app/crawler-service.jar

ENTRYPOINT ["java", "-jar", "/app/crawler-service.jar"]

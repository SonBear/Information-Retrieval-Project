FROM maven:3.8.6-eclipse-temurin-11-alpine AS build

MAINTAINER cherrysoft.com

WORKDIR /app

# Download maven dependencies
COPY ./document-indexer-service/pom.xml .

RUN mvn verify --fail-never

# Build Document indexer service application
COPY ./document-indexer-service/ .

RUN mvn clean package -DskipTests

# Running Document indexer service application
FROM amazoncorretto:11-alpine3.16

COPY --from=build /app/target/document-indexer-service-0.0.1-SNAPSHOT.jar /app/document-indexer.jar

ENTRYPOINT ["java", "-jar", "/app/document-indexer.jar"]

FROM maven:3.8.6-eclipse-temurin-11-alpine AS build

MAINTAINER cherrysoft.com

WORKDIR /app

# Download maven dependencies
COPY ./server-registry/pom.xml .

RUN mvn verify --fail-never

# Build Api Gateway application
COPY ./api-gateway/ .

RUN mvn clean package -DskipTests

# Running Api Gateway application
FROM amazoncorretto:11-alpine3.16

COPY --from=build /app/target/api-gateway-0.0.1-SNAPSHOT.jar /app/api-gateway.jar

ENTRYPOINT ["java", "-jar", "/app/api-gateway.jar"]

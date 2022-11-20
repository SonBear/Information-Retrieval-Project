FROM maven:3.8.6-eclipse-temurin-11-alpine AS build

MAINTAINER cherrysoft.com

WORKDIR /app

# Download maven dependencies
COPY ./server-registry/pom.xml .

RUN mvn verify --fail-never

# Build Server Registry application
COPY ./server-registry/ .

RUN mvn package -DskipTests

# Running Server Registry application
FROM amazoncorretto:11-alpine3.16

# Install curl to do health checks
RUN apk --no-cache add curl

COPY --from=build /app/target/server-registry-0.0.1-SNAPSHOT.jar /app/server-registry.jar

ENTRYPOINT ["java", "-jar", "/app/server-registry.jar"]

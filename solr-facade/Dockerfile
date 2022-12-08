FROM maven:3.8.6-eclipse-temurin-11-alpine AS build

MAINTAINER cherrysoft.com

WORKDIR /app

# Download maven dependencies
COPY ./solr-facade/pom.xml .

RUN mvn verify --fail-never

# Build Solr facade application
COPY ./solr-facade/ .

RUN mvn clean package -DskipTests

# Running Solr facade application
FROM amazoncorretto:11-alpine3.16

COPY --from=build /app/target/solr-facade-0.0.1-SNAPSHOT.jar /app/solr-facade.jar

ENTRYPOINT ["java", "-jar", "/app/solr-facade.jar"]

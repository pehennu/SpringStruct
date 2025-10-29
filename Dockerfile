# Build stage
FROM maven:3.5.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/spring-struct.jar .

# Script de entrada
RUN echo '#!/bin/sh' > /entrypoint.sh && \
    echo 'java -jar /app/spring-struct.jar "$@"' >> /entrypoint.sh && \
    chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]
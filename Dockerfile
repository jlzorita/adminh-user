FROM maven:3.8.1-openjdk-11-slim as maven_builder
WORKDIR /workdir
COPY pom.xml .

COPY src src
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:11-jre-slim-buster
WORKDIR /app
COPY --from=maven_builder /workdir/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]
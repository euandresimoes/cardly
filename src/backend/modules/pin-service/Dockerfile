FROM amazoncorretto:21-alpine-jdk

COPY . .

RUN chmod +X ./mvnw

RUN ./mvnw clean install -DskipTests

ENTRYPOINT ["java", "-jar", "target/pin-service-0.0.1-SNAPSHOT.jar"]
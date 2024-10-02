
FROM eclipse-temurin:17-jdk-focal as builder
WORKDIR /opt/app
COPY bookstore_project/.mvn/ .mvn/
COPY bookstore_project/mvnw bookstore_project/pom.xml ./
RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline
COPY bookstore_project/src/ src/
RUN ./mvnw clean install -DskipTests
RUN cp target/*.jar app.jar

FROM eclipse-temurin:17-jre-alpine
COPY --from=builder /opt/app/app.jar /opt/app/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]
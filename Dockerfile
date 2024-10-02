
FROM eclipse-temurin:17-jdk-focal as builder
WORKDIR /opt/app
COPY ["bookstore 18.26.32/.mvn", ".mvn"]
COPY ["bookstore 18.26.32/mvnw", "bookstore 18.26.32/pom.xml", "./"]
RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline
COPY ["bookstore 18.26.32/src", "src"]
RUN ./mvnw clean install -DskipTests
RUN cp target/*.jar app.jar


FROM eclipse-temurin:17-jre-alpine
COPY --from=builder /opt/app/app.jar /opt/app/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]
FROM eclipse-temurin:17-jre-alpine

# dodanie użytkownika bez uprawnień roota
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

WORKDIR /app
COPY target/social-media-post-app-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
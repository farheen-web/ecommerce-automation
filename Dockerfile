
FROM maven:3.9.16-eclipse-temurin-17

WORKDIR /app

COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src
COPY testng.xml .

CMD ["mvn", "clean", "test", "-Dsurefire.reportsDirectory=/app/test-output/surefire-reports"]

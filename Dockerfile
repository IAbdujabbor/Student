FROM maven:3.10.1-eclipse-temurin-21 AS build

WORKDIR /build
COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN mvn -B dependency:go-offline


COPY src ./src
RUN mvn -B package -DskipTests


FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=build /build/target/*-runner.jar /app/app.jar
EXPOSE 8081
ENV QUARKUS_DATASOURCE_URL=jdbc:oracle:thin:@db:1521/XEPDB1
ENV QUARKUS_DATASOURCE_USERNAME=system
ENV QUARKUS_DATASOURCE_PASSWORD=oracle
ENV JAVA_OPTS="-Xms256m -Xmx512m"
CMD ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY seed seed
COPY target/*.jar bookstore-0.0.1.jar
ENTRYPOINT ["java","-jar","/bookstore-0.0.1.jar"]

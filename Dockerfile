FROM openjdk:8
ADD target/*.jar  /hio-api.jar
EXPOSE 8080
CMD ["java", "-jar", "/hio-api.jar"]
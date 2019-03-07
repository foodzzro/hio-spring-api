FROM openjdk:8
ADD target/*.jar  /hio-api.jar
EXPOSE 8085
CMD ["java", "-jar", "/hio-api.jar"]
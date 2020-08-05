FROM openjdk:8-jdk-alpine
ADD target/*.jar samuraiServiceLibrary.jar
ENTRYPOINT ["java","-jar","samuraiServiceLibrary.jar"]
EXPOSE 8084
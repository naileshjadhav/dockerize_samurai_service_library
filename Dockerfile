FROM openjdk:8-jdk-alpine
ADD target/*.jar samuraiServiceLibrary.jar
#ADD waitForMySQL.sh /root/
#RUN chmod +x /root/waitForMySQL.sh
#CMD ["sh","/root/waitForMySQL.sh"]
ENTRYPOINT ["java","-jar","samuraiServiceLibrary.jar"]
EXPOSE 8084
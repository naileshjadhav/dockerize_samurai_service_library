FROM openjdk:8-jdk-alpine
ADD target/*.jar samuraiIntegrated.jar
## If shell script file name changes here then also change file name in properties file
ADD test2.sh /uploads/test2.sh
#ADD waitForMySQL.sh /root/
#RUN chmod +x /root/waitForMySQL.sh
#CMD ["sh","/root/waitForMySQL.sh"]
ENTRYPOINT ["java","-jar","samuraiIntegrated.jar"]
EXPOSE 8084
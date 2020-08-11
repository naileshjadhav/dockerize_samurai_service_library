FROM openjdk:8-jdk-alpine
ADD target/*.jar samuraiServiceLibrary.jar
#ADD waitForMySQL.sh /root/
#ADD src/main/resources/zeva_installation_guide.txt /BOOT-INF/classes
#ADD src/main/resources/schema-mysql.sql /BOOT-INF/classes
#ADD src/main/resources/data-mysql.sql /BOOT-INF/classes
#RUN chmod +x /root/waitForMySQL.sh
#CMD ["sh","/root/waitForMySQL.sh"]
ENTRYPOINT ["java","-jar","samuraiServiceLibrary.jar"]
EXPOSE 8084
FROM openjdk:15
ADD target/eureka-server*.jar eureka_server.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","eureka_server.jar"]
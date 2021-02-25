FROM openjdk:15
ADD target/auth-server*.jar auth-server.jar
EXPOSE 8092
ENTRYPOINT ["java","-jar","auth-server.jar"]
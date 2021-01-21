FROM openjdk:15
ADD target/authorization-service*.jar authorization-service.jar
EXPOSE 8092
ENTRYPOINT ["java","-jar","authorization-service.jar"]
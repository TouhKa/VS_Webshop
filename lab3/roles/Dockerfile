FROM openjdk:15
ADD target/roles*.jar roles.jar
EXPOSE 8089
ENTRYPOINT ["java","-jar","roles.jar"]
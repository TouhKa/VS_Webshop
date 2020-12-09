FROM openjdk:15
ADD target/users*.jar users.jar
EXPOSE 8088
ENTRYPOINT ["java","-jar","users.jar"]
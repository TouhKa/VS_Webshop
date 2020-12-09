FROM openjdk:15
ADD target/account*.jar account.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","account.jar"]
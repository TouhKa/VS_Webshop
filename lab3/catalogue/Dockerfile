FROM openjdk:15
ADD target/catalogue*.jar catalogue.jar
EXPOSE 8087
ENTRYPOINT ["java","-jar","catalogue.jar"]
FROM openjdk:15
ADD target/products*.jar products.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","products.jar"]
FROM openjdk:15
ADD target/categories*.jar categories.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","categories.jar"]
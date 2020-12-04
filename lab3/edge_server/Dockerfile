FROM openjdk:15
ADD target/edge_server*.jar edge_server.jar
EXPOSE 8091
ENTRYPOINT ["java","-jar","edge_server.jar"]
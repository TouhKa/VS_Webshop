## Docker 
A docker-compose is used here. In the following the commands are shown, which are necessary to build the docker images, to start and end the container in the background. 
<br />
1. Open a terminal in the folder "docker
2. Build: `docker-compose build`
3. Starting the containers `docker-compose up -d`
In Docker Desktop you will find a container wrapper with the default name "docker" after a successful start. Inside you find the containers
- vslab/eureka
- vslab/zuul
- vslab/product
...
4. Ending the application and removing the containers:
`docker-compose down`

## Port Belegung

- Eureka Server: 8761
- Core Services:
    - /product/: 8085
    - Category: 8086
    - /users/: 8088
    - /roles/: 8089
- Composite Services:
    - Catalogue: 8087
    - Account: 8090
- API Services: 8091
- Authorization Service: 8092

## Hystrix Dashboard
The Dashboard tracks the Interfaces of the composite services.
When opening http://service-url:service-port/hystrix you need to enter http://service-url:service-port/actuator/hystrix.stream as URL.
Before the composite service receives any request the dashboard has the status "loading"

## URLs
Authorization Endpoint: /oauth/authorize
Token Endpoint: /oauth/token <- this is where the client application gets a token
Key Endpoint: /oauth/keys <- this is where all other services get the key to verify the token they got from the client application.

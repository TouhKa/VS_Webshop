## Port Belegung

- Eureka Server: 8761
- Core Services:
    - Product: 8085
    - Category: 8086
    - User: 8088
    - Role: 8089
- Composite Services:
    - Catalogue: 8087
    - Account: 8090
- API Services: 

## Hystrix Dashboard
The Dashboard tracks the Interfaces of the composite services.
When opening http://service-url:service-port/hystrix you need to enter http://service-url:service-port/actuator/hystrix.stream as URL.
Before the composite service receives any request the dashboard has the status "loading"

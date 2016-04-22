# SAD Team 4 Spring Cloud Assignment

## Running the app
To do this you need to run the services in different terminal windows.

 1. In the first window, build the application using `mvn clean package`
 1. In the same window run: `java -jar target/spring-cloud-microservices-0.0.1-SNAPSHOT.jar registration`
 1. Switch to the second window and run: `java -jar target/spring-cloud-microservices-0.0.1-SNAPSHOT.jar accounts`
 1. In the third window run: `java -jar target/spring-cloud-microservices-0.0.1-SNAPSHOT.jar web`
 1. In your favorite browser open the same two links: [Eureka Server](http://localhost:8761) and [Web Server](http://localhost:8080)

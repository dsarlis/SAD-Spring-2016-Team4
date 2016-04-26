# SAD Team 4 Spring Cloud Assignment

## Running the app
To do this you need to run the services in different terminal windows.

 1. In the first window, build the application using `mvn clean package`
 1. In the same window run: `java -jar target/spring-cloud-microservices-0.0.1-SNAPSHOT.jar registration`
 1. Switch to the second window and run: `java -jar target/spring-cloud-microservices-0.0.1-SNAPSHOT.jar accounts`
 1. In the third window run: `java -jar target/spring-cloud-microservices-0.0.1-SNAPSHOT.jar products`
 1. In the fourth window run: `java -jar target/spring-cloud-microservices-0.0.1-SNAPSHOT.jar movie`
 1. In the fifth window run: `java -jar target/spring-cloud-microservices-0.0.1-SNAPSHOT.jar web`
 1. In your favorite browser open the same two links: [Eureka Server](http://localhost:8761) and [Web Server](http://localhost:8080)

Note: It may take 30s for Account and Product services to finish initializing, then you can use search function in Web Server.

To run accounts-server or products-server using a different port number:
 1. In a new window, run up a second account-server using HTTP port 5050:
    `java -jar target/spring-cloud-microservices-0.0.1-SNAPSHOT.jar accounts 5050`
 2. Allow it to register itself
 3. Kill the first account-server and see the web-server switch to using the new account-server - no loss of service.
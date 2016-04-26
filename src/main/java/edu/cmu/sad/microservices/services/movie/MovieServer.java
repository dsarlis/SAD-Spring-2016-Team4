package edu.cmu.sad.microservices.services.movie;

import edu.cmu.sad.microservices.products.ProductRepository;
import edu.cmu.sad.microservices.products.ProductsWebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import java.util.logging.Logger;

/**
 * Run as a micro-service, registering with the Discovery Server (Eureka).
 * <p>
 * Note that the configuration for this application is imported from
 * {@link ProductsWebApplication}. This is a deliberate separation of concerns
 * and allows the application to run:
 * <ul>
 * <li>Standalone - by executing {@link ProductsWebApplication#main(String[])}</li>
 * <li>As a microservice - by executing {@link edu.cmu.sad.microservices.services.products.ProductsServer#main(String[])}</li>
 * </ul>
 */
@SpringBootApplication
@EnableDiscoveryClient

public class MovieServer {

    /**
     * Run the application using Spring Boot and an embedded servlet engine.
     *
     * @param args
     *            Program arguments - ignored.
     */
    public static void main(String[] args) {
        // Tell server to look for accounts-server.properties or
        // movie-server.yml
        System.setProperty("spring.config.name", "movie-server");

        SpringApplication.run(MovieServer.class, args);
    }
}

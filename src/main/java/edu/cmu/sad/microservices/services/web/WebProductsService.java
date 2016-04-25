package edu.cmu.sad.microservices.services.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Hide the access to the microservice inside this local service.
 */
@Service
public class WebProductsService {

    @Autowired
    protected RestTemplate restTemplate;

    protected String serviceUrl;

    protected Logger logger = Logger.getLogger(WebProductsService.class
            .getName());

    public WebProductsService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
                : "http://" + serviceUrl;
    }

    /**
     * The RestTemplate works because it uses a custom request-factory that uses
     * Ribbon to look-up the service to use. This method simply exists to show
     * this.
     */
    @PostConstruct
    public void demoOnly() {
        // Can't do this in the constructor because the RestTemplate injection
        // happens afterwards.
        logger.warning("The RestTemplate request factory is "
                + restTemplate.getRequestFactory());
    }

    public edu.cmu.sad.microservices.products.Product findByNumber(String productNumber) {

        logger.info("findByNumber() invoked: for " + productNumber);
        return restTemplate.getForObject(serviceUrl + "/products/{number}",
                edu.cmu.sad.microservices.products.Product.class, productNumber);
    }

    public List<edu.cmu.sad.microservices.products.Product> byNameContains(String name) {
        logger.info("byNameContains() invoked:  for " + name);
        edu.cmu.sad.microservices.products.Product[] products = null;

        try {
            products = restTemplate.getForObject(serviceUrl
                    + "/products/name/{name}", edu.cmu.sad.microservices.products.Product[].class, name);
        } catch (HttpClientErrorException e) { // 404
            // Nothing found
        }

        if (products == null || products.length == 0)
            return null;
        else
            return Arrays.asList(products);
    }
}

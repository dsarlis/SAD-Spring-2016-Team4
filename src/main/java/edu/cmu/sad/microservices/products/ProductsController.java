package edu.cmu.sad.microservices.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/**
 * A RESTFul controller for accessing product information.
 */
@RestController
public class ProductsController {

    protected Logger logger = Logger.getLogger(ProductsController.class
            .getName());
    protected ProductRepository productRepository;

    /**
     * Create an instance plugging in the respository of Products.
     *
     * @param productRepository
     *            An product repository implementation.
     */
    @Autowired
    public ProductsController(ProductRepository productRepository) {
        this.productRepository = productRepository;

        logger.info("ProductRepository says system has "
                + productRepository.countProducts() + " products");
    }

    /**
     * Fetch an product with the specified product number.
     *
     * @param productNumber
     *            A numeric, 9 digit product number.
     * @return The product if found.
     * @throws ProductNotFoundException
     *             If the number is not recognised.
     */
    @RequestMapping("/products/{productNumber}")
    public Product byNumber(@PathVariable("productNumber") String productNumber) {

        logger.info("products-service byNumber() invoked: " + productNumber);
        Product product = productRepository.findByNumber(productNumber);
        logger.info("products-service byNumber() found: " + product);

        if (product == null)
            throw new ProductNotFoundException(productNumber);
        else {
            return product;
        }
    }

    /**
     * Fetch products with the specified name. A partial case-insensitive match
     * is supported. So <code>http://.../products/name/a</code> will find any
     * products with upper or lower case 'a' in their name.
     *
     * @param partialName
     * @return A non-null, non-empty set of products.
     * @throws ProductNotFoundException
     *             If there are no matches at all.
     */
    @RequestMapping("/products/name/{name}")
    public List<Product> byName(@PathVariable("name") String partialName) {
        logger.info("products-service byName() invoked: "
                + productRepository.getClass().getName() + " for "
                + partialName);

        List<Product> products = productRepository
                .findByNameIgnoreCase(partialName);
        logger.info("products-service byName() found: " + products);

        if (products == null || products.size() == 0)
            throw new ProductNotFoundException(partialName);
        else {
            return products;
        }
    }
}

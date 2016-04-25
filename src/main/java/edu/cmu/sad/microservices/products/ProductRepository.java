package edu.cmu.sad.microservices.products;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Repository for Product data implemented using Spring Data JPA.
 */
public interface ProductRepository extends Repository<Product, Long> {
    /**
     * Find an product with the specified product number.
     *
     * @param productNumber
     * @return The product if found, null otherwise.
     */
    public Product findByNumber(String productNumber);

    /**
     * Find products whose owner name contains the specified string
     *
     * @param partialName
     *            Any alphabetic string.
     * @return The list of matching products - always non-null, but may be
     *         empty.
     */
    public List<Product> findByNameIgnoreCase(String partialName);

    /**
     * Fetch the number of products known to the system.
     *
     * @return The number of products.
     */
    @Query("SELECT count(*) from Product")
    public int countProducts();
}

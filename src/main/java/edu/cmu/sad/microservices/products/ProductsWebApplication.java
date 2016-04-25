package edu.cmu.sad.microservices.products;

import edu.cmu.sad.microservices.services.products.ProductsServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

/**
 * The products web-application. This class has two uses:
 * <ol>
 * <li>Provide configuration and setup for {@link ProductsServer} ... or</li>
 * <li>Run as a stand-alone Spring Boot web-application for testing (in which
 * case there is <i>no</i> microservice registration</li>
 * </ol>
 * <p>
 * To execute as a microservice, run {@link ProductsServer} instead.
 */
@SpringBootApplication
@EntityScan("edu.cmu.sad.microservices.products")
@EnableJpaRepositories("edu.cmu.sad.microservices.products")
@PropertySource("classpath:db-config.properties")
public class ProductsWebApplication {

    protected Logger logger = Logger.getLogger(ProductsWebApplication.class
            .getName());

    /**
     * Run the application using Spring Boot and an embedded servlet engine.
     *
     * @param args
     *            Program arguments - ignored.
     */
    public static void main(String[] args) {
        SpringApplication.run(ProductsWebApplication.class, args);
    }

    /**
     * Creates an in-memory "rewards" database populated with test data for fast
     * testing
     */
    @Bean
    public DataSource dataSource() {
        logger.info("dataSource() invoked");

        // Create an in-memory H2 relational database containing some demo
        // products.
        DataSource dataSource = (new EmbeddedDatabaseBuilder())
                .addScript("classpath:testdb/schema.sql")
                .addScript("classpath:testdb/data.sql").build();

        logger.info("dataSource = " + dataSource);

        // Sanity check
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> accounts = jdbcTemplate
                .queryForList("SELECT number FROM T_ACCOUNT");
        logger.info("System has " + accounts.size() + " accounts");
        List<Map<String, Object>> products = jdbcTemplate
                .queryForList("SELECT number FROM T_PRODUCT");
        logger.info("System has " + products.size() + " products");

        // Populate with random balances
        Random rand = new Random();

        for (Map<String, Object> item : accounts) {
            String number = (String) item.get("number");
            BigDecimal balance = new BigDecimal(rand.nextInt(10000000) / 100.0)
                    .setScale(2, BigDecimal.ROUND_HALF_UP);
            jdbcTemplate.update(
                    "UPDATE T_ACCOUNT SET balance = ? WHERE number = ?",
                    balance, number);
        }



        return dataSource;
    }
}

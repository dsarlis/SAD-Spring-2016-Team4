package edu.cmu.sad.microservices.products;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Persistent account entity with JPA markup. Products are stored in an H2
 * relational database.
 */
@Entity
@Table(name = "T_PRODUCT")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    public static Long nextId = 0L;

    @Id
    protected Long id;

    protected String number;

    @Column(name = "name")
    protected String name;

    protected BigDecimal price;

    protected static Long getNextId() {
        synchronized (nextId) {
            return nextId++;
        }
    }

    /**
     * Default constructor for JPA only.
     */
    protected Product() {
        price = BigDecimal.ZERO;
    }

    public Product(String number, String name) {
        id = getNextId();
        this.number = number;
        this.name = name;
        price = BigDecimal.ZERO;
    }

    public long getId() {
        return id;
    }

    /**
     * Set JPA id - for testing and JPA only. Not intended for normal use.
     *
     * @param id
     *            The new id.
     */
    protected void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    protected void setNumber(String accountNumber) {
        this.number = accountNumber;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }


    @Override
    public String toString() {
        return number + " [" + name + "]: $" + price;
    }

}

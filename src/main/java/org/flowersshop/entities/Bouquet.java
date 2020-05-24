package org.flowersshop.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "bouquets")
public class Bouquet {
    @Id
    private Long id;
    private String name;
    private BigDecimal price;
    @ManyToMany(mappedBy = "bouquets")
    private List<Sale> sales;

    public Bouquet() {
    }

    public Bouquet(Long id, String name, BigDecimal price, List<Sale> sales) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sales = sales;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
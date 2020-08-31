package org.flowersshop.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@Entity(name = "bouquets")
public class Bouquet {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private BigDecimal price;

    @ManyToMany(mappedBy = "bouquets",fetch = FetchType.EAGER)
    private List<Sale> sales;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "flowers_in_bouquet",
            joinColumns = @JoinColumn(name = "flowers_id"),
            inverseJoinColumns = @JoinColumn(name = "bouquet_id"))
    private Set<Flower> flowers;
}
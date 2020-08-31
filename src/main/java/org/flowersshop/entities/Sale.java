package org.flowersshop.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@ToString(exclude = "bouquets")
@Entity(name = "sales")
public class Sale {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    @Column(name = "sum_total")
    private BigDecimal totalSum;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "sale_detail",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "bouquet_id"))
    private List<Bouquet> bouquets;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
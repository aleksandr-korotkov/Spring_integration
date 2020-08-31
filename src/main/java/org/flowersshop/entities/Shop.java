package org.flowersshop.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@ToString(exclude = "sales")
@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "shop",fetch = FetchType.EAGER)
    private Set<Sale> sales;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adress")
    private Address address;
}
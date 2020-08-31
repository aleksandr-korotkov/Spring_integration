package org.flowersshop.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString(exclude = "shop")
@Entity
@Table(name = "adresses")
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    private String city;
    private String street;
    private int house;
    @Column(nullable = true)
    private Integer office;
    @OneToOne(optional = false, mappedBy = "address")
    private Shop shop;
}
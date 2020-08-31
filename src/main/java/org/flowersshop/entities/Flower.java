package org.flowersshop.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@ToString(exclude = "bouquets")
@Entity(name = "flowers")
public class Flower {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "flowers",fetch = FetchType.EAGER)
    private List<Bouquet> bouquets;
}
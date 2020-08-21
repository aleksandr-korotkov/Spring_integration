package org.flowersshop.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "sales")
public class Sale {
    @Id
    private Long id;
    private LocalDate date;
    @Column(name = "sum_total")
    private BigDecimal totalSum;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "shop_id")
    private Long shopId;
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "sale_detail",
//            joinColumns = @JoinColumn(name = "sale_id"),
//            inverseJoinColumns = @JoinColumn(name = "bouquet_id"))
    @Transient
    private List<Bouquet> bouquets;


    public Sale() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public List<Bouquet> getBouquets() {
        return bouquets;
    }

    public void setBouquets(List<Bouquet> bouquets) {
        this.bouquets = bouquets;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", date=" + date +
                ", totalSum=" + totalSum +
                ", customerId=" + customerId +
                ", shopId=" + shopId +
                ", bouquets=" + bouquets +
                '}';
    }

}
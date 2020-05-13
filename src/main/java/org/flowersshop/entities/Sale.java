package org.flowersshop.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Component
@Scope(value = "prototype")
public class Sale {
    private Long id;
    private LocalDate date;
    private BigDecimal totalSum;
    private Long customerId;
    private Long shopId;

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

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", date=" + date +
                ", totalSum=" + totalSum +
                ", customerId=" + customerId +
                ", shopId=" + shopId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(date, sale.date) &&
                Objects.equals(totalSum, sale.totalSum) &&
                Objects.equals(customerId, sale.customerId) &&
                Objects.equals(shopId, sale.shopId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, totalSum, customerId, shopId);
    }
}
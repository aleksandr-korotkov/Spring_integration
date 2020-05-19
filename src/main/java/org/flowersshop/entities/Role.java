package org.flowersshop.entities;

import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public class Role implements GrantedAuthority {
    private Long id;
    private String name;
    private Set<Customer> customers;

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}

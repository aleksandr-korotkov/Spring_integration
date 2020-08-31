package org.flowersshop.entities;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.List;

@Data
@ToString(exclude = "sales")
@Entity
@Table(name = "customers")
public class Customer implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    private String phone;
    private String password;
    @Transient
    private String passwordConfirm;
    @Transient
    private List<Role> roles;
    @Transient
    private String userName;
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Sale> sales;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
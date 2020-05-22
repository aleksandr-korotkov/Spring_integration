package org.flowersshop.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;
import java.util.List;

public class Customer implements UserDetails {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String password;
    private String passwordConfirm;
    private List<Role> roles;
    private String userName;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String phone, String password, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.password = password;
        this.userName = userName;
    }

    public Customer(Long id, String firstName, String lastName, String phone,String password, String userName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.password = password;
        this.userName = userName;
    }

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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", roles=" + roles +
                ", userName='" + userName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(phone, customer.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phone, password, passwordConfirm, roles, userName);
    }
}
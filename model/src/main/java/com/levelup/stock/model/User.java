package com.levelup.stock.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USER")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    private String email;
    private String password;
    private String name;
    @OneToMany(targetEntity = Deal.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="USER_ID", referencedColumnName = "ID")
    List<Deal> deals;

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String login) {
        this.email = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(User user){
        this.name=user.getName();
        this.id = user.getId();
        this.password= user.getPassword();
        this.email=user.getEmail();
    }
}

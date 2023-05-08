package com.example.jpareldemo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class  User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = false) // join (inner join), because optional = false
    private Profile profile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL) // left join, because optional = true (default)
    private Address address;

    public void setProfile(Profile profile) {
        this.profile = profile;
        profile.setUser(this);
    }

    public void setAddress(Address address) {
        address.setUser(this);
        this.address = address;
    }

    public void removeAddress(Address address) {
        this.address = null;
        address.setUser(null);
    }

}
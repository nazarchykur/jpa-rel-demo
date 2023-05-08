package com.example.jpareldemo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "guilds")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "employees")
public class Guild {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    private String name;

    @ManyToMany(mappedBy = "guilds", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Employee> employees = new ArrayList<>();

    public Guild(String name) {
        this.name = name;
    }

    public void addEmployee(Employee employee) {
        employee.getGuilds().add(this);
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employee.getGuilds().remove(this);
        employees.remove(employee);
    }
}
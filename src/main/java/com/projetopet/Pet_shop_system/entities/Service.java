package com.projetopet.Pet_shop_system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetopet.Pet_shop_system.entities.enums.ServiceType;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "service")
public class Service implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Double price;
    private Integer minutDuration;
    @JsonIgnore
    @OneToMany(mappedBy = "service")
    private Set<Employee> employees = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private ServiceType type;
    public Service(){
    }

    public Service(Long id, String description, Double price, Integer minutDuration, Employee employee, ServiceType type) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.minutDuration = minutDuration;
        addEmployees(employee);
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getMinutDuration() {
        return minutDuration;
    }

    public void setMinutDuration(Integer minutDuration) {
        this.minutDuration = minutDuration;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }
    public void addEmployees(Employee employee){
        employees.add(employee);
    }

    public ServiceType getType() {
        return type;
    }

    public void setType(ServiceType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Objects.equals(id, service.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

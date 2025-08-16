package com.projetopet.Pet_shop_system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetopet.Pet_shop_system.entities.enums.Cargo;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 50)
    private String name;
    @Column(nullable = false,unique = true,length = 15)
    private String cpf;
    private LocalDate birthDate;
    @Column(nullable = false,unique = true,length = 50)
    private String email;
    private String phone;
    @OneToOne
    @JoinColumn(name = "id_address")
    private Address address;
    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_service")
    private Service service;
    @Enumerated(EnumType.STRING)
    private Cargo cargo;
    @JsonIgnore
    @ManyToMany(mappedBy = "employees")
    private Set<ServiceRequest> requests = new HashSet<>();
    public Employee(){
    }

    public Employee(Long id, String name, String cpf, LocalDate birthDate, String email, String phone, Address address,Cargo cargo) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        addAddress(address);
        this.cargo = cargo;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void addAddress(Address address){
        address.setEmployee(this);
        this.address = address;
    }
    public void removeAddress(Address address){
        this.address = null;
        address.setEmployee(null);
    }
    public User geUser() {
        return this.user;
    }

    public void addUser(User user){
        this.user = user;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Set<ServiceRequest> getRequests() {
        return requests;
    }

    public void addRequests(ServiceRequest request) {
        this.requests.add(request);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package com.projetopet.Pet_shop_system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private String email;
    private String phone;
    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;
    @OneToOne
    @JoinColumn(name = "id_address")
    private Address address;
    @OneToMany(mappedBy = "client",cascade = CascadeType.PERSIST)
    private Set<Pet> pets = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private Set<ServiceRequest> servRequests = new HashSet<>();
    public Client(){
    }

    public Client(Long id, String name, String cpf, LocalDate birthDate, String email, String phone, Address address, Pet pet) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.address = address;
        addPet(pet);
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
    public void addUser(User user){
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public Set<Pet> getPets() {
        return pets;
    }
    public Pet getOnePet(String name){
        for (Pet pet: pets){
            if (pet.getName().equalsIgnoreCase(name)){
                return pet;
            }
        }
        throw new IllegalArgumentException("pet doesn't existe! ");
    }

    public void addPet(Pet pet){
        pet.setClient(this);
        pets.add(pet);
    }
    public void removePet(Pet pet){
        pet.setClient(null);
        this.pets.remove(pet);
    }

    public Set<ServiceRequest> getServRequests() {
        return servRequests;
    }

    public void addServRequests(ServiceRequest servRequest) {
        this.servRequests.add(servRequest);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

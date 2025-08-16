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
@Table(name = "pet")
public class Pet implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    private LocalDate birthDate;
    private Double weight;
    @ManyToOne
    @JoinColumn(name = "id_breed")
    private Breed breed;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
    @JsonIgnore
    @ManyToMany(mappedBy = "pets")
    private Set<ServiceRequest>servRequest = new HashSet<>();
    public Pet(){
    }

    public Pet(Long id, String name, LocalDate birthDate, Double weight, Breed breed) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.weight = weight;
        this.breed = breed;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Breed getBreed() {
        return this.breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<ServiceRequest> getServRequest() {
        return servRequest;
    }

    public void addServRequest(ServiceRequest servReques) {
        this.servRequest.add(servReques);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

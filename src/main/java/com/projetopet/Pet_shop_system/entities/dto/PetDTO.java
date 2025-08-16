package com.projetopet.Pet_shop_system.entities.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetopet.Pet_shop_system.entities.Breed;
import com.projetopet.Pet_shop_system.entities.Client;
import com.projetopet.Pet_shop_system.entities.Pet;
import com.projetopet.Pet_shop_system.entities.ServiceRequest;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PetDTO {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private Double weight;
    private Long breedId;
    private String breedName;
    private Long clientId;
    private String clientName;
    public PetDTO(){
    }
    public PetDTO(Pet pet){
        this.id = pet.getId();
        this.name = pet.getName();
        this.birthDate = pet.getBirthDate();
        this.weight = pet.getWeight();
        if (pet.getBreed() != null){
            this.breedId = pet.getBreed().getId();
            this.breedName = pet.getBreed().getName();
        }
        if (pet.getClient() != null){
            this.clientId = pet.getClient().getId();
            this.clientName = pet.getClient().getName();
        }
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

    public Long getBreedId() {
        return breedId;
    }

    public void setBreedId(Long breedId) {
        this.breedId = breedId;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}

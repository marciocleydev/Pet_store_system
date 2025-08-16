package com.projetopet.Pet_shop_system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "specie")
public class Specie implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "specie")
    private Set<Breed> breeds = new HashSet<>();


    public Specie(){
    }

    public Specie(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public Set<Breed> getBreeds() {
        return breeds;
    }
    public void addBreed(Breed breed){
        breeds.add(breed);
    }
    public void removeBreed(Breed breed){
        breeds.remove(breed);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specie specie = (Specie) o;
        return Objects.equals(id, specie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package com.projetopet.Pet_shop_system.services;
import com.projetopet.Pet_shop_system.entities.Breed;
import com.projetopet.Pet_shop_system.repositories.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BreedService {
    @Autowired
    private BreedRepository repository;

    public List<Breed> findAll(){
        return repository.findAll();
    }
    public Breed findById(Long id){
        Optional<Breed> breed = repository.findById(id);
        return breed.get();
    }
}

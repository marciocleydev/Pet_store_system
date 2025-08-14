package com.projetopet.Pet_shop_system.services;

import com.projetopet.Pet_shop_system.repositories.PetRepository;
import com.projetopet.Pet_shop_system.entities.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    private PetRepository repository;

    public List<Pet> findAll(){
        return repository.findAll();
    }
    public Pet findById(Long id){
        Optional<Pet> pet = repository.findById(id);
        return pet.get();
    }
}

package com.projetopet.Pet_shop_system.services;

import com.projetopet.Pet_shop_system.entities.Specie;
import com.projetopet.Pet_shop_system.repositories.SpecieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecieService {
    @Autowired
    private SpecieRepository repository;

    public List<Specie> findAll(){
        return repository.findAll();
    }
    public Specie findById(Long id){
        Optional<Specie> Specie = repository.findById(id);
        return Specie.get();
    }
}


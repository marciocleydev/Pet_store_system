package com.projetopet.Pet_shop_system.resources;

import com.projetopet.Pet_shop_system.services.BreedService;
import com.projetopet.Pet_shop_system.entities.Breed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/breeds")
public class BreedResource {
    @Autowired
    private BreedService service;

    @GetMapping
    public ResponseEntity<List<Breed>> findAll(){
        List<Breed> breeds = service.findAll();
        return ResponseEntity.ok().body(breeds);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Breed> findById( @PathVariable Long id){
        Breed breed = service.findById(id);
        return ResponseEntity.ok().body(breed);
    }
}

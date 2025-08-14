package com.projetopet.Pet_shop_system.resources;

import com.projetopet.Pet_shop_system.entities.Pet;
import com.projetopet.Pet_shop_system.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pets")
public class PetResource {
    @Autowired
    private PetService service;

    @GetMapping
    public ResponseEntity<List<Pet>> findAll(){
        List<Pet> pets = service.findAll();
        return ResponseEntity.ok().body(pets);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Pet> findById( @PathVariable Long id){
        Pet pet = service.findById(id);
        return ResponseEntity.ok().body(pet);
    }
}

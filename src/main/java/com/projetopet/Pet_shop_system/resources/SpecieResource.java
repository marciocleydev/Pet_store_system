package com.projetopet.Pet_shop_system.resources;

import com.projetopet.Pet_shop_system.entities.Specie;
import com.projetopet.Pet_shop_system.services.PetService;
import com.projetopet.Pet_shop_system.services.SpecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/species")
public class SpecieResource {
    @Autowired
    private SpecieService service;

    @GetMapping
    public ResponseEntity<List<Specie>> findAll(){
        List<Specie> specie = service.findAll();
        return ResponseEntity.ok().body(specie);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Specie> findById( @PathVariable Long id){
        Specie specie = service.findById(id);
        return ResponseEntity.ok().body(specie);
    }
}

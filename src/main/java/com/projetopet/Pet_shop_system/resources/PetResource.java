package com.projetopet.Pet_shop_system.resources;

import com.projetopet.Pet_shop_system.entities.dto.PetDTO;
import com.projetopet.Pet_shop_system.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/pets")
public class PetResource {
    @Autowired
    private PetService service;

    @GetMapping
    public ResponseEntity<List<PetDTO>> findAll(){
        List<PetDTO> dto = service.findAll();
        return ResponseEntity.ok().body(dto);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<PetDTO> findById( @PathVariable Long id){
        PetDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<PetDTO>update(@RequestBody PetDTO dto,@PathVariable Long id){
        PetDTO dtoUpdated = service.update(dto, id);
        return ResponseEntity.ok().body(dtoUpdated);
    }
    @PostMapping
    public ResponseEntity<PetDTO> insert(@RequestBody PetDTO newPet){
        PetDTO dto = service.insert(newPet);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void>deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

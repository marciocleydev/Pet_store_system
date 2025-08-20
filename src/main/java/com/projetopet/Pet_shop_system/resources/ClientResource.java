package com.projetopet.Pet_shop_system.resources;

import com.projetopet.Pet_shop_system.dto.ClientDTO;
import com.projetopet.Pet_shop_system.dto.PetDTO;
import com.projetopet.Pet_shop_system.dto.ServiceRequestDTO;
import com.projetopet.Pet_shop_system.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {
    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll(){
        List<ClientDTO> dto = service.findAll();
        return ResponseEntity.ok().body(dto);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById( @PathVariable Long id){
        ClientDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }
    @GetMapping(value = "/{id}/requests")
    public ResponseEntity<List<ServiceRequestDTO>> findRequests(@PathVariable Long id){
        List<ServiceRequestDTO> requestsDTO = service.findClientRequests(id);
        return ResponseEntity.ok().body(requestsDTO);
    }
    @GetMapping(value = "/{id}/pets")
    public ResponseEntity<List<PetDTO>> findPets(@PathVariable Long id){
        List<PetDTO> petsDTO = service.findPets(id);
        return ResponseEntity.ok().body(petsDTO);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO>update(@RequestBody ClientDTO dto, @PathVariable Long id){
        ClientDTO dtoUpdated = service.update(dto, id);
        return ResponseEntity.ok().body(dtoUpdated);
    }
    @PostMapping
    public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO newClient){
        ClientDTO dto = service.insert(newClient);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void>deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

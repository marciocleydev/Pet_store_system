package com.projetopet.Pet_shop_system.resources;

import com.projetopet.Pet_shop_system.dto.PetDTO;
import com.projetopet.Pet_shop_system.dto.ServiceRequestDTO;
import com.projetopet.Pet_shop_system.dto.ServiceRequestFullDTO;
import com.projetopet.Pet_shop_system.services.PetService;
import com.projetopet.Pet_shop_system.services.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/requests")
public class ServiceRequestResource {
    @Autowired
    private ServiceRequestService service;

    @GetMapping
    public ResponseEntity<List<ServiceRequestDTO>> findAll(){
        List<ServiceRequestDTO> dto = service.findAll();
        return ResponseEntity.ok().body(dto);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ServiceRequestFullDTO> findById(@PathVariable Long id){
        ServiceRequestFullDTO dto = service.findFullById(id);
        return ResponseEntity.ok().body(dto);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<ServiceRequestDTO>update(@RequestBody ServiceRequestDTO dto,@PathVariable Long id){
        ServiceRequestDTO dtoUpdated = service.update(dto, id);
        return ResponseEntity.ok().body(dtoUpdated);
    }
    @PostMapping
    public ResponseEntity<ServiceRequestDTO> insert(@RequestBody ServiceRequestDTO newrequest){
        ServiceRequestDTO dto = service.insert(newrequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void>deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

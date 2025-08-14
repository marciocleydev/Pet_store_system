package com.projetopet.Pet_shop_system.resources;

import com.projetopet.Pet_shop_system.entities.Client;
import com.projetopet.Pet_shop_system.services.ClientService;
import com.projetopet.Pet_shop_system.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {
    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<List<Client>> findAll(){
        List<Client> clients = service.findAll();
        return ResponseEntity.ok().body(clients);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> findById( @PathVariable Long id){
        Client client = service.findById(id);
        return ResponseEntity.ok().body(client);
    }
}

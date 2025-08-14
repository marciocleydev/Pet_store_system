package com.projetopet.Pet_shop_system.services;

import com.projetopet.Pet_shop_system.entities.Client;
import com.projetopet.Pet_shop_system.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public List<Client> findAll(){
        return repository.findAll();
    }
    public Client findById(Long id){
        Optional<Client> client = repository.findById(id);
        return client.get();
    }
}

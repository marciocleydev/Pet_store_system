package com.projetopet.Pet_shop_system.services;

import com.projetopet.Pet_shop_system.dto.ClientDTO;
import com.projetopet.Pet_shop_system.dto.PetDTO;
import com.projetopet.Pet_shop_system.dto.ServiceRequestDTO;
import com.projetopet.Pet_shop_system.entities.Client;
import com.projetopet.Pet_shop_system.entities.Pet;
import com.projetopet.Pet_shop_system.exceptions.IntegrityViolationException;
import com.projetopet.Pet_shop_system.exceptions.NotFoundException;
import com.projetopet.Pet_shop_system.mappers.ClientMapper;
import com.projetopet.Pet_shop_system.mappers.PetMapper;
import com.projetopet.Pet_shop_system.mappers.ServiceRequestMapper;
import com.projetopet.Pet_shop_system.repositories.BreedRepository;
import com.projetopet.Pet_shop_system.repositories.ClientRepository;
import com.projetopet.Pet_shop_system.repositories.PetRepository;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;
    @Autowired
    ClientMapper clientMapper;
    @Autowired
    ServiceRequestMapper requestMapper;
    @Autowired
    PetMapper petMapper;


    public List<ClientDTO> findAll() {
        return repository.findAll().stream().map(clientMapper::toDTO).collect(Collectors.toList());
    }

    public ClientDTO findById(Long id) {
        Client client = repository.findById(id).orElseThrow(() -> new NotFoundException("Client not found with id: " + id));
        return clientMapper.toDTO(client);
    }
    public List<ServiceRequestDTO> findClientRequests(Long id){
        Client client = repository.findById(id).orElseThrow(() -> new NotFoundException("Client not found with id: " + id));
        if (!client.getServRequests().isEmpty()){
            return client.getServRequests().stream().map(requestMapper::toDTO).toList();
        }
        else {
            throw new NotFoundException("this client id: " + id + " does not have requests yet!");
        }
    }
    public List<PetDTO> findPets(Long id){
        Client client = repository.findById(id).orElseThrow(() -> new NotFoundException("Client not found with id: " + id));
        return client.getPets().stream().map(petMapper::toDTO).toList();
    }

    public ClientDTO insert(ClientDTO dto) {
        if (dto.getId() != null){
            throw new IntegrityViolationException("Id must be null when inserting a new Client");
        }
        Client client = repository.save(clientMapper.fromDTO(dto));
        return clientMapper.toDTO(client);
    }

    public ClientDTO update(ClientDTO newClient, Long id) {
        Client existingClient = repository.findById(id).orElseThrow(() -> new NotFoundException("Client not found with id: " + id));
        Client clientFromDTO = clientMapper.fromDTO(newClient);

        existingClient.setName(clientFromDTO.getName());
        existingClient.setPhone(clientFromDTO.getPhone());
        existingClient.setEmail(clientFromDTO.getEmail());
        existingClient.setBirthDate(clientFromDTO.getBirthDate());
        existingClient.setCpf(clientFromDTO.getCpf());
        return clientMapper.toDTO(repository.save(existingClient));
    }

    public void deleteById(Long id) {
        try {
            if(!repository.existsById(id)){
                throw new NotFoundException("Client not found with id: " + id);
            }
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IntegrityViolationException(e.getMessage());
        }

    }

}

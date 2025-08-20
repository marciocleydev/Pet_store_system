package com.projetopet.Pet_shop_system.services;

import com.projetopet.Pet_shop_system.dto.ServiceRequestDTO;
import com.projetopet.Pet_shop_system.dto.PetDTO;
import com.projetopet.Pet_shop_system.exceptions.IntegrityViolationException;
import com.projetopet.Pet_shop_system.exceptions.NotFoundException;
import com.projetopet.Pet_shop_system.mappers.PetMapper;
import com.projetopet.Pet_shop_system.mappers.ServiceRequestMapper;
import com.projetopet.Pet_shop_system.repositories.BreedRepository;
import com.projetopet.Pet_shop_system.repositories.ClientRepository;
import com.projetopet.Pet_shop_system.repositories.PetRepository;
import com.projetopet.Pet_shop_system.entities.Pet;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {
    @Autowired
    private PetRepository repository;
    @Autowired
    PetMapper petMapper;
    @Autowired
    ServiceRequestMapper requestMapper;

    public List<PetDTO> findAll() {
            return repository.findAll().stream().map(petMapper::toDTO).collect(Collectors.toList());
    }

    public PetDTO findById(Long id) {
        Pet pet = repository.findById(id).orElseThrow(() -> new NotFoundException("Pet not found with id: " + id));
        return petMapper.toDTO(pet);
    }
    public List<ServiceRequestDTO> findPetRequests(Long id){
        Pet pet = repository.findById(id).orElseThrow(() -> new NotFoundException("Pet not found with id: " + id));
        if (!pet.getServRequest().isEmpty()){
            return pet.getServRequest().stream().map(requestMapper::toDTO).toList();
        }
        else {
            throw new NotFoundException("this pet id: " + id + " does not have requests yet!");
        }
    }

    public PetDTO insert(PetDTO dto) {
        if (dto.getId() != null){
            throw new IntegrityViolationException("Id must be null when inserting a new Pet");
        }
        Pet pet = repository.save(petMapper.fromDTO(dto));
        return petMapper.toDTO(pet);
    }

    public PetDTO update(PetDTO newPet, Long id) {
        Pet existingPet = repository.findById(id).orElseThrow(() -> new NotFoundException("Pet not found with id: " + id));
        Pet petFromDTO = petMapper.fromDTO(newPet);

        existingPet.setBreed(petFromDTO.getBreed());
        existingPet.setWeight(petFromDTO.getWeight());
        existingPet.setName(petFromDTO.getName());
        existingPet.setBirthDate(petFromDTO.getBirthDate());
        return petMapper.toDTO(repository.save(existingPet));
    }

    public void deleteById(Long id) {
        try {
            if(!repository.existsById(id)){
                throw new NotFoundException("Pet not found with id: " + id);
            }
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IntegrityViolationException(e.getMessage());
        }

    }

}

package com.projetopet.Pet_shop_system.services;

import com.projetopet.Pet_shop_system.entities.Breed;
import com.projetopet.Pet_shop_system.entities.Client;
import com.projetopet.Pet_shop_system.dto.PetDTO;
import com.projetopet.Pet_shop_system.exceptions.IntegrityViolationException;
import com.projetopet.Pet_shop_system.exceptions.NotFoundException;
import com.projetopet.Pet_shop_system.mappers.PetMapper;
import com.projetopet.Pet_shop_system.repositories.BreedRepository;
import com.projetopet.Pet_shop_system.repositories.ClientRepository;
import com.projetopet.Pet_shop_system.repositories.PetRepository;
import com.projetopet.Pet_shop_system.entities.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {
    @Autowired
    private PetRepository repository;
    @Autowired
    private BreedRepository breedRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    PetMapper mapper;

    public List<PetDTO> findAll() {
            return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public PetDTO findById(Long id) {
        Pet pet = repository.findById(id).orElseThrow(() -> new NotFoundException("Pet not found with id: " + id));
        return mapper.toDTO(pet);
    }

    public PetDTO insert(PetDTO dto) {
        if (dto.getId() != null){
            throw new IntegrityViolationException("Id must be null when inserting a new Pet");
        }
        Pet pet = repository.save(mapper.fromDTO(dto));
        return mapper.toDTO(pet);
    }

    public PetDTO update(PetDTO newPet, Long id) {
        Pet existingPet = repository.findById(id).orElseThrow(() -> new NotFoundException("Pet not found with id: " + id));
        Pet petFromDTO = mapper.fromDTO(newPet);

        existingPet.setClient(petFromDTO.getClient());
        existingPet.setBreed(petFromDTO.getBreed());
        existingPet.setWeight(petFromDTO.getWeight());
        existingPet.setName(petFromDTO.getName());
        existingPet.setBirthDate(petFromDTO.getBirthDate());
        return mapper.toDTO(repository.save(existingPet));
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

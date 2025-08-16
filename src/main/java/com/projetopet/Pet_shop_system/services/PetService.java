package com.projetopet.Pet_shop_system.services;

import com.projetopet.Pet_shop_system.entities.Breed;
import com.projetopet.Pet_shop_system.entities.Client;
import com.projetopet.Pet_shop_system.entities.dto.PetDTO;
import com.projetopet.Pet_shop_system.repositories.BreedRepository;
import com.projetopet.Pet_shop_system.repositories.ClientRepository;
import com.projetopet.Pet_shop_system.repositories.PetRepository;
import com.projetopet.Pet_shop_system.entities.Pet;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<PetDTO> findAll(){
      return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }
    public PetDTO findById(Long id){
        Optional<Pet> pet = repository.findById(id);
        return toDTO(pet.get());
    }

    public PetDTO insert(PetDTO dto){
        Pet pet =  repository.save(fromDTO(dto));
        return toDTO(pet);
    }

    public PetDTO update(PetDTO newPet, Long id){
        Pet pet = repository.findById(id).get();
         updateData(pet, newPet);
         return toDTO(repository.save(pet));
    }

    private void updateData(Pet oldPet, PetDTO newPet){
        oldPet.setName(newPet.getName());
        oldPet.setBirthDate(newPet.getBirthDate());
        oldPet.setWeight(newPet.getWeight());

        if (newPet.getBreedId() != null){
            Breed breed = breedRepository.findById(newPet.getBreedId()).orElseThrow(()-> new RuntimeException("Breed doesn't existe!"));
            oldPet.setBreed(breed);
        }
        if (newPet.getClientId() != null){
            Client client = clientRepository.findById(newPet.getClientId()).orElseThrow(()-> new RuntimeException("Breed doesn't existe!"));
            oldPet.setClient(client);
        }
    }
    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Pet fromDTO(PetDTO dto){
        Pet pet = new Pet();
        pet.setId(dto.getId());
        pet.setName(dto.getName());
        pet.setBirthDate(dto.getBirthDate());
        pet.setWeight(dto.getWeight());

        if (dto.getBreedId() != null){
            Breed breed = breedRepository.findById(dto.getBreedId()).orElseThrow(()-> new RuntimeException("Breed doesn't exite!"));
            pet.setBreed(breed);
        }
        if (dto.getClientId() != null){
            Client client = clientRepository.findById(dto.getClientId()).orElseThrow(() -> new RuntimeException("Client doesn't exite!"));
            pet.setClient(client);
        }
        return pet;
    }
    public PetDTO toDTO(Pet pet){
        return new PetDTO(pet);
    }
}

package com.projetopet.Pet_shop_system.mappers;

import com.projetopet.Pet_shop_system.dto.PetDTO;
import com.projetopet.Pet_shop_system.entities.Breed;
import com.projetopet.Pet_shop_system.entities.Client;
import com.projetopet.Pet_shop_system.entities.Pet;
import com.projetopet.Pet_shop_system.exceptions.NotFoundException;
import com.projetopet.Pet_shop_system.repositories.BreedRepository;
import com.projetopet.Pet_shop_system.repositories.ClientRepository;
import com.projetopet.Pet_shop_system.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {
    @Autowired
    BreedRepository breedRepository;
    @Autowired
    ClientRepository clientRepository;
    public Pet fromDTO(PetDTO dto) {
        Pet pet = new Pet();
        pet.setId(dto.getId());
        pet.setName(dto.getName());
        pet.setBirthDate(dto.getBirthDate());
        pet.setWeight(dto.getWeight());

        if (dto.getBreedId() != null) {
            Breed breed = breedRepository.findById(dto.getBreedId()).orElseThrow(()-> new NotFoundException("Breed not found with id: " + dto.getBreedId()));
            pet.setBreed(breed);
        }
        if (dto.getClientId() != null) {
            Client client = clientRepository.findById(dto.getClientId()).orElseThrow(()-> new NotFoundException("Client not found with id: " + dto.getClientId()));
            pet.setClient(client);
        }
        return pet;
    }

    public PetDTO toDTO(Pet pet) {
        return new PetDTO(pet);
    }
}

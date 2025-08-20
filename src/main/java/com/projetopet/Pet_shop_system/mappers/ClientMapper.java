package com.projetopet.Pet_shop_system.mappers;

import com.projetopet.Pet_shop_system.dto.ClientDTO;
import com.projetopet.Pet_shop_system.entities.Address;
import com.projetopet.Pet_shop_system.entities.Client;
import com.projetopet.Pet_shop_system.entities.User;
import com.projetopet.Pet_shop_system.exceptions.NotFoundException;
import com.projetopet.Pet_shop_system.repositories.AddressRepository;
import com.projetopet.Pet_shop_system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserRepository userRepository;
    public ClientDTO toDTO(Client client){
        return new ClientDTO(client);
    }
    public Client fromDTO(ClientDTO dto){
        Client client = new Client();
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setId(dto.getId());
        client.setPhone(dto.getPhone());
        client.setBirthDate(dto.getBirthDate());
        client.setEmail(dto.getEmail());

        if(dto.getAddressId() != null){
            Address address = addressRepository.findById(dto.getAddressId()).orElseThrow(()-> new NotFoundException("Address not found! id: " + dto.getAddressId()));
            client.setAddress(address);
        }
        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId()).orElseThrow(()-> new NotFoundException("User not found! id: " + dto.getUserId()));
            client.setUser(user);
        }
        return client;
    }
}

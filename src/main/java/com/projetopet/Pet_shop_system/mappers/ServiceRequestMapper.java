package com.projetopet.Pet_shop_system.mappers;

import com.projetopet.Pet_shop_system.dto.ServiceRequestDTO;
import com.projetopet.Pet_shop_system.dto.ServiceRequestFullDTO;
import com.projetopet.Pet_shop_system.entities.Client;
import com.projetopet.Pet_shop_system.entities.ServiceRequest;
import com.projetopet.Pet_shop_system.entities.enums.StatusServRequest;
import com.projetopet.Pet_shop_system.exceptions.NotFoundException;
import com.projetopet.Pet_shop_system.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceRequestMapper {

    @Autowired
    ClientRepository repository;

    public ServiceRequestDTO toDTO(ServiceRequest request) {
        return new ServiceRequestDTO(request);
    }

    public ServiceRequest fromDTO(ServiceRequestDTO dto) {
        ServiceRequest request = new ServiceRequest();
        request.setId(dto.getId());
        request.setDateHour(dto.getDateHour());
        request.setStatus(StatusServRequest.valueOf(dto.getStatus()));
        request.setObservation(dto.getObservation());

        if (dto.getClientId() != null) {
            Client client = repository.findById(dto.getClientId()).orElseThrow(() -> new NotFoundException("Client does not found!"));
            request.setClient(client);
        }
        return request;
    }

    public ServiceRequestFullDTO toFullDTO(ServiceRequest request) {
        return new ServiceRequestFullDTO(request);
    }
}

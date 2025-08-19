package com.projetopet.Pet_shop_system.services;

import com.projetopet.Pet_shop_system.dto.PetDTO;
import com.projetopet.Pet_shop_system.dto.ServiceRequestDTO;
import com.projetopet.Pet_shop_system.entities.Pet;
import com.projetopet.Pet_shop_system.entities.ServiceRequest;
import com.projetopet.Pet_shop_system.exceptions.IntegrityViolationException;
import com.projetopet.Pet_shop_system.exceptions.NotFoundException;
import com.projetopet.Pet_shop_system.mappers.PetMapper;
import com.projetopet.Pet_shop_system.mappers.ServiceRequestMapper;
import com.projetopet.Pet_shop_system.repositories.BreedRepository;
import com.projetopet.Pet_shop_system.repositories.ClientRepository;
import com.projetopet.Pet_shop_system.repositories.PetRepository;
import com.projetopet.Pet_shop_system.repositories.ServRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceRequestService {
    @Autowired
    private ServRequestRepository repository;
    @Autowired
    ServiceRequestMapper mapper;

    public List<ServiceRequestDTO> findAll() {
            return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public ServiceRequestDTO findById(Long id) {
        ServiceRequest request = repository.findById(id).orElseThrow(() -> new NotFoundException("ServiceRequest not found with id: " + id));
        return mapper.toDTO(request);
    }

    public ServiceRequestDTO insert(ServiceRequestDTO dto) {
        if (dto.getId() != null){
            throw new IntegrityViolationException("Id must be null when inserting a new ServiceRequest");
        }
        ServiceRequest request = repository.save(mapper.fromDTO(dto));
        return mapper.toDTO(request);
    }

    public ServiceRequestDTO update(ServiceRequestDTO newServiceRequest, Long id) {
        ServiceRequest existingServiceRequest = repository.findById(id).orElseThrow(() -> new NotFoundException("ServiceRequest not found with id: " + id));
        ServiceRequest requestFromDTO = mapper.fromDTO(newServiceRequest);

        existingServiceRequest.setDateHour(requestFromDTO.getDateHour());
        existingServiceRequest.setObservation(requestFromDTO.getObservation());
        existingServiceRequest.setStatus(requestFromDTO.getStatus());
        existingServiceRequest.setClient(requestFromDTO.getClient());
        return mapper.toDTO(repository.save(existingServiceRequest));
    }

    public void deleteById(Long id) {
        try {
            if(!repository.existsById(id)){
                throw new NotFoundException("ServiceRequest not found with id: " + id);
            }
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IntegrityViolationException(e.getMessage());
        }

    }

}

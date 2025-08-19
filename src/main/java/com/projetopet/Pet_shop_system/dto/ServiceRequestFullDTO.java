package com.projetopet.Pet_shop_system.dto;

import com.projetopet.Pet_shop_system.entities.ServiceRequest;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServiceRequestFullDTO extends ServiceRequestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    List<ServiceItemDTO> serviceItemsDTO;
    public ServiceRequestFullDTO(){
    }
    public ServiceRequestFullDTO(ServiceRequest request){
        super(request);
        serviceItemsDTO = request.getServiceItems().stream().map(ServiceItemDTO::new).toList();
    }

}

package com.projetopet.Pet_shop_system.dto;

import com.projetopet.Pet_shop_system.entities.ServiceRequest;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class ServiceRequestFullDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    ServiceRequestDTO requestDTO;
    List<ServiceItemDTO> serviceItemsDTO;
    List<PaymentDTO> paymentsDTO;
    public ServiceRequestFullDTO(){
    }
    public ServiceRequestFullDTO(ServiceRequest request){
        this.requestDTO = new ServiceRequestDTO(request);
        serviceItemsDTO = request.getServiceItems().stream().map(ServiceItemDTO::new).toList();
        paymentsDTO = request.getPayments().stream().map(PaymentDTO::new).toList();
    }

    public ServiceRequestDTO getRequestDTO() {
        return requestDTO;
    }

    public List<ServiceItemDTO> getServiceItemsDTO() {
        return serviceItemsDTO;
    }

    public void setServiceItemsDTO(List<ServiceItemDTO> serviceItemsDTO) {
        this.serviceItemsDTO = serviceItemsDTO;
    }

    public List<PaymentDTO> getPaymentsDTO() {
        return paymentsDTO;
    }

    public void setPaymentsDTO(List<PaymentDTO> paymentsDTO) {
        this.paymentsDTO = paymentsDTO;
    }
}

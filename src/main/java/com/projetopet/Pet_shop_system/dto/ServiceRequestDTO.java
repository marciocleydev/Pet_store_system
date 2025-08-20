package com.projetopet.Pet_shop_system.dto;

import com.projetopet.Pet_shop_system.entities.ServiceRequest;
import com.projetopet.Pet_shop_system.entities.enums.StatusServRequest;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ServiceRequestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private LocalDateTime DateHour;
    private String observation;
    private String status;
    private Double totalValue;
    private Long ClientId;
    private String ClientName;
    public ServiceRequestDTO(){
    }
    public ServiceRequestDTO(ServiceRequest request){
        this.id = request.getId();
        this.DateHour = request.getDateHour();
        this.observation = request.getObservation();
        this.totalValue = request.getTotal();
        this.status = request.getStatus().toString();
        this.ClientId = request.getClient().getId();
        this.ClientName = request.getClient().getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateHour() {
        return DateHour;
    }

    public void setDateHour(LocalDateTime dateHour) {
        DateHour = dateHour;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public Long getClientId() {
        return ClientId;
    }

    public void setClientId(Long clientId) {
        ClientId = clientId;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }
}

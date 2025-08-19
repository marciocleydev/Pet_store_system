package com.projetopet.Pet_shop_system.dto;

import com.projetopet.Pet_shop_system.entities.PK.ServiceItemPK;
import com.projetopet.Pet_shop_system.entities.ServiceItem;
import jakarta.persistence.EmbeddedId;

import java.io.Serial;
import java.io.Serializable;

public class ServiceItemDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long servRequestId;
    private Long serviceId;
    private Double price;
    public ServiceItemDTO(){
    }
    public ServiceItemDTO(ServiceItem item){
        this.servRequestId = item.getServiceRequest().getId();
        this.serviceId = item.getService().getId();
        this.price = item.getPrice();
    }

    public Long getServRequestId() {
        return servRequestId;
    }

    public void setServRequestId(Long servRequestId) {
        this.servRequestId = servRequestId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

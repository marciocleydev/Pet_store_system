package com.projetopet.Pet_shop_system.entities.PK;

import com.projetopet.Pet_shop_system.entities.Service;
import com.projetopet.Pet_shop_system.entities.ServiceRequest;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
@Embeddable
public class ServiceItemPK implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name = "id_servrequest")
    private ServiceRequest serviceRequest;
    @ManyToOne
    @JoinColumn(name = "id_service")
    private Service service;

    public ServiceRequest getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(ServiceRequest serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceItemPK that = (ServiceItemPK) o;
        return Objects.equals(serviceRequest, that.serviceRequest) && Objects.equals(service, that.service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceRequest, service);
    }
}

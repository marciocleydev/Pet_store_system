package com.projetopet.Pet_shop_system.entities.enums;

import com.projetopet.Pet_shop_system.entities.PK.ServiceItemPK;
import com.projetopet.Pet_shop_system.entities.Service;
import com.projetopet.Pet_shop_system.entities.ServiceRequest;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "service_item")
public class ServiceItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private ServiceItemPK id = new ServiceItemPK();
    private Double price;
    public ServiceItem(){
    }

    public ServiceItem(Service service, ServiceRequest request, Double price) {
        setServiceRequest(request);
        setService(service);
        this.price = price;
    }

    public Service getService() {
        return id.getService();
    }
    public ServiceRequest getServiceRequest() {
        return id.getServiceRequest();
    }
    public void setService(Service service) {
        this.id.setService(service);
    }
    public void setServiceRequest(ServiceRequest request) {
        this.id.setServiceRequest(request);
    }

    public void setId(ServiceItemPK id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceItem that = (ServiceItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

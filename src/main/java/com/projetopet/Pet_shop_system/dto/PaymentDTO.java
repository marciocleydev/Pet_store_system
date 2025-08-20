package com.projetopet.Pet_shop_system.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetopet.Pet_shop_system.entities.Payment;
import com.projetopet.Pet_shop_system.entities.ServiceRequest;
import com.projetopet.Pet_shop_system.entities.enums.PaymentStatus;
import com.projetopet.Pet_shop_system.entities.enums.PaymentType;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

public class PaymentDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private Double price;
    private Integer currentInstallment;

    private String type;

    private String status;

    public PaymentDTO() {
    }

    public PaymentDTO(Payment payment) {
        this.id = payment.getId();
        this.type = payment.getType().toString();
        this.status = payment.getStatus().toString();
        this.price = payment.getPrice();
        this.currentInstallment = payment.getCurrentInstallment();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCurrentInstallment() {
        return currentInstallment;
    }

    public void setCurrentInstallment(Integer currentInstallment) {
        this.currentInstallment = currentInstallment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

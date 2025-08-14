package com.projetopet.Pet_shop_system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetopet.Pet_shop_system.entities.enums.PaymentStatus;
import com.projetopet.Pet_shop_system.entities.enums.PaymentType;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "payment")
public class Payment implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;
    @Transient
    private Integer installments;
    private Integer currentInstallment;
    @Enumerated(EnumType.STRING)
    private PaymentType type;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_serv_request")
    private ServiceRequest servRequest;
    public Payment(){
    }

    public Payment(Long id, Double price, Integer installments, PaymentType type, PaymentStatus status) {
        this.id = id;
        this.price = price;
        this.installments = installments;
        this.type = type;
        this.status = status;
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

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public ServiceRequest getServRequest() {
        return servRequest;
    }

    public void setServRequest(ServiceRequest servRequest) {
        this.servRequest = servRequest;
    }

    public Integer getInstallments() {
        return installments;
    }

    public Integer getCurrentInstallment() {
        return currentInstallment;
    }

    public void setCurrentInstallment(Integer currentInstallment) {
        this.currentInstallment = currentInstallment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

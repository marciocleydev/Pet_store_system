package com.projetopet.Pet_shop_system.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Integer installments;
    private Integer currentInstallment;

    private PaymentType type;

    private PaymentStatus status;
    private ServiceRequest servRequest;
}

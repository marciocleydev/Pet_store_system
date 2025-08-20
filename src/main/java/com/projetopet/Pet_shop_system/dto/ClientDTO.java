package com.projetopet.Pet_shop_system.dto;

import com.projetopet.Pet_shop_system.entities.Client;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class ClientDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private String email;
    private String phone;
    private Long userId;
    private Long addressId;

    public ClientDTO() {
    }
    public ClientDTO(Client client){
        this.id = client.getId();
        this.cpf = client.getCpf();
        this.name = client.getName();
        this.email = client.getEmail();
        this.phone = client.getPhone();
        this.birthDate = client.getBirthDate();
        this.userId = client.getUser().getId();
        this.addressId = client.getAddress().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}

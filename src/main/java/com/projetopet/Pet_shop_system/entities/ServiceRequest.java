package com.projetopet.Pet_shop_system.entities;

import com.projetopet.Pet_shop_system.entities.enums.StatusServiceRequest;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "serv_request")
public class ServiceRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime DateHour;
    private String observation;
    private Double totalValue;
    @Enumerated(EnumType.STRING)
    private StatusServiceRequest status;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
    @ManyToMany
    @JoinTable(name = "pet_servrequest",joinColumns = @JoinColumn(name = "id_servrequest"),
            inverseJoinColumns = @JoinColumn(name = "id_pet"))
    private Set<Pet>pets = new HashSet<>();
    @OneToMany(mappedBy = "servRequest",cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();
    public ServiceRequest(){
    }

    public ServiceRequest(Long id, LocalDateTime dateHour, String observation, StatusServiceRequest status,Client client, Pet pet, Payment payment) {
        this.id = id;
        DateHour = dateHour;
        this.observation = observation;
        this.status = status;
        this.client = client;
        addPet(pet);
        setPayments(payment);
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

    public StatusServiceRequest getStatus() {
        return status;
    }

    public void setStatus(StatusServiceRequest status) {
        this.status = status;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void addPet(Pet pet) {
        this.pets.add(pet);
    }

    public void setPayments(Payment payment) {
        payments.clear();
        Integer installments = payment.getInstallments();
        this.totalValue = payment.getPrice();
        Double value = (double) this.totalValue / installments;
        for( Integer i = 1; i<= installments; i++){
            payment.setPrice(value);
            Payment pay = new Payment(null,value,installments,payment.getType(),payment.getStatus());
            pay.setCurrentInstallment(i);
            pay.setServRequest(this);
           payments.add(pay);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceRequest that = (ServiceRequest) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

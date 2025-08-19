package com.projetopet.Pet_shop_system.entities;

import com.projetopet.Pet_shop_system.entities.enums.StatusServRequest;
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
    private java.lang.String observation;
    @Enumerated(EnumType.STRING)
    private StatusServRequest status;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
    @ManyToMany
    @JoinTable(name = "employee_servrequest",joinColumns = @JoinColumn(name = "id_servrequest"),
            inverseJoinColumns = @JoinColumn(name = "id_employee"))
    private Set<Employee> employees = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "pet_servrequest",joinColumns = @JoinColumn(name = "id_servrequest"),
            inverseJoinColumns = @JoinColumn(name = "id_pet"))
    private Set<Pet>pets = new HashSet<>();
    @OneToMany(mappedBy = "servRequest",cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();
    @OneToMany(mappedBy = "id.serviceRequest")
    private Set<ServiceItem> serviceItems = new HashSet<>();
    public ServiceRequest(){
    }

    public ServiceRequest(Long id, LocalDateTime dateHour, java.lang.String observation, StatusServRequest status, Client client) {
        this.id = id;
        DateHour = dateHour;
        this.observation = observation;
        this.status = status;
        this.client = client;
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

    public java.lang.String getObservation() {
        return observation;
    }

    public void setObservation(java.lang.String observation) {
        this.observation = observation;
    }

    public StatusServRequest getStatus() {
        return status;
    }

    public void setStatus(StatusServRequest status) {
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

    public Set<ServiceItem> getServiceItems() {
        return serviceItems;
    }

    public void addServiceItem(ServiceItem serviceItem) {
        this.serviceItems.add(serviceItem);
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public Double getTotal() {
      double sum = 0.0;
      for (ServiceItem item: serviceItems){
          sum += item.getPrice();
      }
      return sum;
    }

    public void setPayment(Payment payment) {
        payments.clear();
        Integer installments = payment.getInstallments();
        Double total = payment.getPrice();
        Double value = (double) total / installments;
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

package com.projetopet.Pet_shop_system.config;

import com.projetopet.Pet_shop_system.entities.*;
import com.projetopet.Pet_shop_system.entities.enums.*;
import com.projetopet.Pet_shop_system.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Configuration
@Profile(value = "dev")
public class DevConfing implements CommandLineRunner {
    @Autowired
    private SpecieRepository specieRepository;
    @Autowired
    private ClientRepository clientRespository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    ServRequestRepository servRequestRepository;
    @Autowired
    ServiceItemRepository serviceItemRepository;

    @Override
    public void run(String... args) throws Exception {
        //dog
        Breed breed1 = new Breed(null, "labrador");
        Breed breed2 = new Breed(null, "puddle");
        //cat
        Breed breed3 = new Breed(null, "Siamese");


        Specie specie1 = new Specie(null, "Dog", breed1);
        Specie specie2 = new Specie(null, "Dog", breed2);
        Specie specie3 = new Specie(null, "Cat", breed3);
        specieRepository.saveAll(Arrays.asList(specie1, specie2, specie3));


        Pet pet1 = new Pet(null,"Rex", LocalDate.parse("2024-06-12"), 12.0, specie1);
        Pet pet2 = new Pet(null, "Bob", LocalDate.parse("2020-02-10"),13.0, specie2);
        Pet pet3 = new Pet(null, "Hulk", LocalDate.parse("2021-08-30"),11.0,specie3);


        Address address1 = new Address(null,"890680-61","Erich belz","Itoupava Central",1555, "blumenau", "Brasil", "Tabacaria");
        Address address2 = new Address(null,"890611-23","Humberto de campos","Ilha de leonor",1635, "pinheiro", "Brasil", "Mercado");
        Address address3 = new Address(null,"890610-85","Tarcízio","Portões",4555, "curitiba", "Brasil", "Relógioaria");
        Address address4 = new Address(null,"89500-64","Batista","Itoupava norte",1542, "blumenau", "Brasil", "restaurante amizade");
        Address address5 = new Address(null,"823611-23","leonardo de campos","Ilha do peixe",1555, "pacas", "Brasil", "komprao");
        Address address6 = new Address(null,"890350-85","Genesio","Portões",1552, "curitiba", "Brasil", "bar do rock");
        addressRepository.saveAll(Arrays.asList(address1, address2,address3, address4, address5, address6));

        Client client1 = new Client(null,"Marcio", "612.1167.33-59",LocalDate.parse("1998-07-23"),"marciodev@gmail.com","47 988752166", address1,pet1);
        Client client2 = new Client(null,"Camilla", "964.1167.33-59",LocalDate.parse("1999-06-21"),"camilla@gmail.com","47 985986320", address2,pet3);
        Client client3 = new Client(null,"Carlinho", "851.1167.33-59",LocalDate.parse("1970-05-13"),"carlinho@gmail.com","47 988665463", address3,pet2);

        Employee employee1 = new Employee(null,"Roberto", "789.326.254-96",LocalDate.parse("1985-12-02"),"roberto@gmail.com", "47 965854548",address4, Cargo.VET);
        Employee employee2 = new Employee(null,"Maria", "789.326.235-42",LocalDate.parse("1987-10-22"),"maria@gmail.com", "47 936321548",address5,Cargo.ATTENDANT);
        Employee employee3 = new Employee(null,"Julia", "789.326.523-25",LocalDate.parse("1987-11-03"),"julia@gmail.com", "47 452321548",address6,Cargo.CLIPPER);

        User userClient1 = new User(null,client1.getEmail(),client1.getCpf());
        User userClient2 = new User(null,client2.getEmail(),client2.getCpf());
        User userClient3 = new User(null,client3.getEmail(),client3.getCpf());
        userRepository.saveAll(Arrays.asList(userClient1,userClient2,userClient3));

        client1.addUser(userClient1);
        client2.addUser(userClient2);
        client3.addUser(userClient3);
        clientRespository.saveAll(Arrays.asList(client1, client2, client3));

        User userEmployee1 = new User(null,employee1.getEmail(),employee1.getCpf());
        User userEmployee2 = new User(null,employee2.getEmail(),employee2.getCpf());
        User userEmployee3 = new User(null,employee3.getEmail(),employee3.getCpf());
        userRepository.saveAll(Arrays.asList(userEmployee1,userEmployee2,userEmployee3));


        employee1.addUser(userEmployee1);
        employee2.addUser(userEmployee2);
        employee3.addUser(userEmployee3);
        employeeRepository.saveAll(Arrays.asList(employee1,employee2,employee3));

        Service service1 = new Service(null, "banho com shampoo seda", 150.0,30,employee3, ServiceType.BATH);
        Service service2 = new Service(null, "entregar exames", 200.0,15,employee1, ServiceType.APPOINTMENT);
        Service service3 = new Service(null, "pet nao come a 3 dias", 200.0,20,employee1,ServiceType.APPOINTMENT);
        serviceRepository.saveAll(Arrays.asList(service1, service2, service3));

        employee1.setService(service2);
        employee1.setService(service3);
        employee3.setService(service1);
        employeeRepository.saveAll(Arrays.asList(employee1,employee2,employee3));

        Payment payment1 = new Payment(null,200.0, 10, PaymentType.CREDIT_CARD, PaymentStatus.PENDING);

        ServiceRequest servRequest1 = new ServiceRequest(null, LocalDateTime.now(),"Primeiro atendimento", StatusServiceRequest.SCHEDULED,client1,payment1);
        servRequest1.addPet(client1.getOnePet("rex"));
        servRequest1.addEmployee(employee3);
        servRequestRepository.save(servRequest1);

        ServiceItem serviceItem1 = new ServiceItem(service1,servRequest1,servRequest1.getTotalValue());
        serviceItemRepository.save(serviceItem1);
    }
}

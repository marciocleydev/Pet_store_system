package com.projetopet.Pet_shop_system.repositories;

import com.projetopet.Pet_shop_system.entities.Client;
import com.projetopet.Pet_shop_system.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}

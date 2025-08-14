
package com.projetopet.Pet_shop_system.repositories;

import com.projetopet.Pet_shop_system.entities.Payment;
import com.projetopet.Pet_shop_system.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}

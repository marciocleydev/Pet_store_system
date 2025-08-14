
package com.projetopet.Pet_shop_system.repositories;

import com.projetopet.Pet_shop_system.entities.Payment;
import com.projetopet.Pet_shop_system.entities.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServRequestRepository extends JpaRepository<ServiceRequest,Long> {
}

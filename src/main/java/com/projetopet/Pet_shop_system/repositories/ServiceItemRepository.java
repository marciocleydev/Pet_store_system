package com.projetopet.Pet_shop_system.repositories;

import com.projetopet.Pet_shop_system.entities.ServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceItemRepository extends JpaRepository<ServiceItem,Long> {
}

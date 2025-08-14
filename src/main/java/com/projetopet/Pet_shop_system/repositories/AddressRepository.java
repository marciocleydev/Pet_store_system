package com.projetopet.Pet_shop_system.repositories;

import com.projetopet.Pet_shop_system.entities.Address;
import com.projetopet.Pet_shop_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}

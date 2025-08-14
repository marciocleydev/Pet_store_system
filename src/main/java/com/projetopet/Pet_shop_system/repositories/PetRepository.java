package com.projetopet.Pet_shop_system.repositories;

import com.projetopet.Pet_shop_system.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet,Long> {
}

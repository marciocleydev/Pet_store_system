package com.projetopet.Pet_shop_system.repositories;

import com.projetopet.Pet_shop_system.entities.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedRepository extends JpaRepository<Breed,Long> {
}

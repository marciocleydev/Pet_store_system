package com.projetopet.Pet_shop_system.repositories;

import com.projetopet.Pet_shop_system.entities.Breed;
import com.projetopet.Pet_shop_system.entities.Specie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecieRepository extends JpaRepository<Specie,Long> {
}

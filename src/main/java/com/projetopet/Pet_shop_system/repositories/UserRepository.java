package com.projetopet.Pet_shop_system.repositories;

import com.projetopet.Pet_shop_system.entities.Pet;
import com.projetopet.Pet_shop_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}

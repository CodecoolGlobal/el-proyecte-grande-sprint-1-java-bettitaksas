package com.fridgemaster.repository;

import com.fridgemaster.model.Fridge;
import com.fridgemaster.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FridgeRepository extends JpaRepository<Fridge,Long> {
    Fridge findFridgeByUser(User user);
}

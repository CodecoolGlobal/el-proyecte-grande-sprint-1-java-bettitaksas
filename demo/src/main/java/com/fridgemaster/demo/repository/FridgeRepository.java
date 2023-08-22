package com.fridgemaster.demo.repository;

import com.fridgemaster.demo.model.Fridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FridgeRepository extends JpaRepository<Fridge,Long> {
}
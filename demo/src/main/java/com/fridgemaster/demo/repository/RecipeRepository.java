package com.fridgemaster.demo.repository;

import com.fridgemaster.demo.model.ItemType;
import com.fridgemaster.demo.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query("SELECT DISTINCT r FROM Recipe r JOIN r.ingredients i WHERE i.type = :itemType")
    Optional<Recipe> findRecipeByItemType(@Param("itemType") ItemType itemType);

    @Query("SELECT DISTINCT r FROM Recipe r JOIN r.ingredients i WHERE i.type = :itemType")
    List<Recipe> findRecipesByItemType(@Param("itemType") ItemType itemType);

}

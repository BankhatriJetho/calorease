package com.calorease.calorease.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.calorease.calorease.entity.Meal;

public interface MealRepository extends JpaRepository<Meal, Integer> {
	List<Meal> findByUserId(Integer userId);
	
	@Query("SELECT m FROM Meal m " +
	           "WHERE (:userId IS NULL OR m.user.id = :userId) " +
	           "AND (:minCalories IS NULL OR m.calories >= :minCalories) " +
	           "AND (:maxCalories IS NULL OR m.calories <= :maxCalories) " +
	           "AND (:date IS NULL OR m.date = :date)")
	    Page<Meal> findFilteredMeals(
	        @Param("userId") Integer userId,
	        @Param("minCalories") Integer minCalories,
	        @Param("maxCalories") Integer maxCalories,
	        @Param("date") LocalDate date,
	        Pageable pageable
	    );

}

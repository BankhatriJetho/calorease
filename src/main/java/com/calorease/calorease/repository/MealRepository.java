package com.calorease.calorease.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.calorease.calorease.entity.Meal;

public interface MealRepository extends JpaRepository<Meal, Integer> {

	@Query("SELECT m FROM Meal m " +
	           "WHERE (:minCalories IS NULL OR m.calories >= :minCalories) " +
	           "AND (:maxCalories IS NULL OR m.calories <= :maxCalories) " +
	           "AND (:date IS NULL OR m.date = :date)")
	List<Meal> findFilteredMeals(
	        @Param("minCalories") Integer minCalories,
	        @Param("maxCalories") Integer maxCalories,
	        @Param("date") LocalDate date
	    );
}

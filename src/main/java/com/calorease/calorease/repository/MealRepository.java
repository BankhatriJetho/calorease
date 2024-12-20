package com.calorease.calorease.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.calorease.calorease.entity.Meal;

public interface MealRepository extends JpaRepository<Meal, Integer> {
	List<Meal> findByUserId(Integer userId);

}

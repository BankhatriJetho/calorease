package com.calorease.calorease.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.calorease.calorease.dto.MealDTO;
import com.calorease.calorease.entity.Meal;
import com.calorease.calorease.service.MealService;

@RestController
@RequestMapping("/meals")
public class MealController {
	private final MealService mealService;

    @Autowired
    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping
    public MealDTO logMeal(@RequestBody MealDTO mealDTO) {
    	Meal meal = Meal.from(mealDTO);
        Meal savedMeal = mealService.logMeal(meal);
        return MealDTO.from(savedMeal);
    }

    @GetMapping
    public List<MealDTO> getAllMeals() {
        return mealService.getAllMeals().stream().map(MealDTO::from).toList();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMeal(@PathVariable Integer id) {
        mealService.deleteMeal(id);
        return ResponseEntity.ok("Meal deleted!");
    }
    
    @GetMapping("/stats")
    public ResponseEntity<String> getTotalCalories() {
        Integer totalCalories = mealService.getTotalCalories();
        return ResponseEntity.ok("Total Calories: " + totalCalories);
    }
}

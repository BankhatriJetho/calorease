package com.calorease.calorease.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        Meal savedMeal = mealService.logMeal(meal,mealDTO.getUserId());
        return MealDTO.from(savedMeal);
    }

    @GetMapping
    public List<MealDTO> getMealsByUser(@RequestParam Integer userId) {
        return mealService.getMealsByUser(userId)
            .stream()
            .map(MealDTO::from)
            .collect(Collectors.toList());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMeal(@PathVariable Integer id) {
        mealService.deleteMeal(id);
        return ResponseEntity.ok("Meal with ID " + id + " has been deleted successfully!");
    }
    
    //test point
    @GetMapping("/test")
    public String testEndpoint() {
        return "MealController is working!";
    }


}

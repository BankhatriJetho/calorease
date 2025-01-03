package com.calorease.calorease.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.calorease.calorease.dto.MealDTO;
import com.calorease.calorease.entity.Meal;
import com.calorease.calorease.repository.MealRepository;

@Service
public class MealService {
	private final MealRepository mealRepository;

    @Autowired
    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public Meal logMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }
    
    public void deleteMeal(Integer id) {
        if (!mealRepository.existsById(id)) {
            throw new RuntimeException("Meal with ID " + id + " not found");
        }
        mealRepository.deleteById(id);
    }
    
    public Meal updateMeal(Integer mealId, MealDTO mealDTO) {
    	return mealRepository.findById(mealId)
                .map(existingMeal -> {
                    existingMeal.setName(mealDTO.getName());
                    existingMeal.setCalories(mealDTO.getCalories());
                    existingMeal.setDate(mealDTO.getDate());
                    return mealRepository.save(existingMeal);
                })
                .orElseThrow(() -> new RuntimeException("Meal not found with ID: " + mealId));
    	
    }
    
    public List<MealDTO> getFilteredMeals(Integer minCalories, Integer maxCalories, LocalDate date) {
    	List<Meal> meals = mealRepository.findFilteredMeals(minCalories, maxCalories, date);

    	// Map to DTO
    	return meals.stream().map(MealDTO::from).toList();
    }
    
    public Integer getTotalCalories() {
        List<Meal> meals = mealRepository.findAll();
        return meals.stream().mapToInt(Meal::getCalories).sum();
    }
}

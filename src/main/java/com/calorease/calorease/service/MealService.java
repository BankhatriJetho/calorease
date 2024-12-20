package com.calorease.calorease.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.calorease.calorease.dto.MealDTO;
import com.calorease.calorease.entity.Meal;
import com.calorease.calorease.entity.User;
import com.calorease.calorease.repository.MealRepository;
import com.calorease.calorease.repository.UserRepository;

@Service
public class MealService {
	private final MealRepository mealRepository;
	private final UserRepository userRepository;

    @Autowired
    public MealService(MealRepository mealRepository, UserRepository userRepository) {
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
    }

    public Meal logMeal(Meal meal, Integer userId) {
    	User user = userRepository.findById(userId)
    			.orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    	meal.setUser(user);
        return mealRepository.save(meal);
    }

    public List<Meal> getMealsByUser(Integer userId) {
        return mealRepository.findByUserId(userId);
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
    
    public Page<MealDTO> getFilteredMeals(Integer userId, Integer minCalories, Integer maxCalories, LocalDate date, int page, int size) {
    	Pageable pageable = PageRequest.of(page, size);
    	Page<Meal> meals = mealRepository.findFilteredMeals(userId, minCalories, maxCalories, date, pageable);

    	// Map to DTO
    	return meals.map(MealDTO::from);
    }
}

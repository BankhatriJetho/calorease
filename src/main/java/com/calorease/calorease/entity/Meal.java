package com.calorease.calorease.entity;

import java.time.LocalDate;
import com.calorease.calorease.dto.MealDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "meals")
public class Meal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer calories;

    @Column(nullable = false)
    private LocalDate date;

	// Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public static Meal from(MealDTO mealDTO) {
        Meal meal = new Meal();
        meal.setId(mealDTO.getId());
        meal.setName(mealDTO.getName());
        meal.setCalories(mealDTO.getCalories());
        meal.setDate(mealDTO.getDate());
        return meal;
    }
}
package com.calorease.calorease.dto;

import java.time.LocalDate;
import com.calorease.calorease.entity.Meal;

public class MealDTO {
	private Integer id;
	private String name;
	private Integer calories;
	private LocalDate date;
	
	public MealDTO() {}

	public MealDTO(Integer id, String name, Integer calories, LocalDate date) {
		this.id = id;
		this.name = name;
		this.calories = calories;
		this.date = date;
	}
	
	public static MealDTO from(Meal meal) {
		return new MealDTO(meal.getId(), meal.getName(), meal.getCalories(), meal.getDate());
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getCalories() {
		return calories;
	}

	public LocalDate getDate() {
		return date;
	}
}

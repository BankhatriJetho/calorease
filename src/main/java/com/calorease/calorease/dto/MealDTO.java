package com.calorease.calorease.dto;

import java.time.LocalDate;

import com.calorease.calorease.entity.Meal;

public class MealDTO {
	private Integer id;
	private String name;
	private Integer calories;
	private LocalDate date;
	private Integer userId;
	
	public MealDTO() {}
	
	public MealDTO(Integer id, String name, Integer calories, LocalDate date,Integer userId) {
		this.id = id;
		this.name = name;
		this.calories = calories;
		this.date = date;
		this.userId = userId;
	}
	
	public static MealDTO from(Meal meal) {
		return new MealDTO(meal.getId(), meal.getName(), meal.getCalories(), meal.getDate(), meal.getUser().getId());
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}

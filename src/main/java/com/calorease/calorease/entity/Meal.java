package com.calorease.calorease.entity;

import java.time.LocalDate;

import com.calorease.calorease.dto.MealDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate date;
    
  
	//Getters and Setters
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

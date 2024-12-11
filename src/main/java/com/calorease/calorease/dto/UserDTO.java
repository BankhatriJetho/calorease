package com.calorease.calorease.dto;

import com.calorease.calorease.entity.User;


public class UserDTO {
    private Integer id;
    private String name;
    private String email;
    
    //Default constructor
    public UserDTO() {}
    
    //parameterized constructor
    public UserDTO(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // getters and setters
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public static UserDTO from(User user) {
	    return new UserDTO(user.getId(), user.getName(), user.getEmail());
	}
	
	@Override
	public String toString() {
		return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
	}
	
}

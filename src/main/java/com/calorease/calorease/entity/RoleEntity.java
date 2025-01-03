package com.calorease.calorease.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Enumerated(EnumType.STRING)
	    @Column(length = 20, nullable = false)
	    private Role name;

	    // Constructors
	    public RoleEntity() {}

	    public RoleEntity(Role name) {
	        this.name = name;
	    }

	    // Getters and Setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public Role getName() {
	        return name;
	    }

	    public void setName(Role name) {
	        this.name = name;
	    }

}

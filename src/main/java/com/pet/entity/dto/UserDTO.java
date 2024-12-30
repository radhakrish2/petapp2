package com.pet.entity.dto;

import com.pet.entity.User.Role;

import lombok.Data;

	@Data
	public class UserDTO {
	
	    private Long id;
	    private String name;
	    private String email;
	    private Role role; // OWNER, VOLUNTEER, ADMIN
	    private String phone;
	    private String address;
	
	    // Getters and Setters
	}
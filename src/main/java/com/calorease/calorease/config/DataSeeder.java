package com.calorease.calorease.config;

import com.calorease.calorease.entity.RoleEntity;
import com.calorease.calorease.entity.Role;
import com.calorease.calorease.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component 
public class DataSeeder implements CommandLineRunner {
	
	private final RoleRepository roleRepository;

    public DataSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        if (roleRepository.findByName(Role.ADMIN).isEmpty()) {
            roleRepository.save(new RoleEntity(Role.ADMIN));
        }
        if (roleRepository.findByName(Role.USER).isEmpty()) {
            roleRepository.save(new RoleEntity(Role.USER));
        }
    }

}

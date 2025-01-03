package com.calorease.calorease.repository;

import com.calorease.calorease.entity.RoleEntity;
import com.calorease.calorease.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	Optional<RoleEntity> findByName(Role name);

}

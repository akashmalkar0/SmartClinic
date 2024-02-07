package com.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import com.clinic.entity.*;

@Repository

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);
}
package com.clinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import com.clinic.entity.*;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
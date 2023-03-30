package com.fintechapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintechapp.entities.UserEntity;



public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	UserEntity findByEmail(String email);

	//UserEntity findByEmailVerificationToken(String confirmationToken);

}


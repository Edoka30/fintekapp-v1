package com.fintechapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintechapp.DTO.LoginDTo;
import com.fintechapp.entities.LoginEntity;
import com.fintechapp.entities.UserEntity;


public interface LoginRepository extends JpaRepository<LoginEntity, Integer> {

	LoginEntity findByEmailVerificationToken(String confirmationToken);

	LoginEntity findByEmail(String string);

}

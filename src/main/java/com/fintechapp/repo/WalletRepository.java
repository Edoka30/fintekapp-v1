package com.fintechapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintechapp.entities.WalletEntity;

public interface WalletRepository extends JpaRepository<WalletEntity, Integer> {

	WalletEntity findByAccountNumber(String acctnumber);

	WalletEntity findByUserEmail(String email);

}

package com.fintechapp.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fintechapp.entities.TransactionEntity;

public interface TransactionRepo extends JpaRepository<TransactionEntity, Integer> {

	Page<TransactionEntity> findByAccNumber(String accNumber, PageRequest of);

	List<TransactionEntity> findByAccNumber(String accNumber);

	

}

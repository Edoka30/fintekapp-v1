package com.fintechapp.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.fintechapp.DTO.TransactionDTO;
import com.fintechapp.entities.TransactionEntity;
import com.fintechapp.entities.WalletEntity;

public interface WalletService {
	
public	ResponseEntity generateAcctNum(String email);
public WalletEntity fundMyAccount(TransactionDTO transactionDTO) throws Exception;

public TransactionEntity sendMoney(TransactionDTO transDTO) throws Exception;

Page<TransactionEntity> transactionHistoryPaged(String accNumber, Optional<Integer> page, Optional<String> sortBy) throws Exception;


}

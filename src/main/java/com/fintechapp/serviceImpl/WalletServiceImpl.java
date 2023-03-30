package com.fintechapp.serviceImpl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fintechapp.DTO.TransactionDTO;
import com.fintechapp.entities.TransactionEntity;
import com.fintechapp.entities.UserEntity;
import com.fintechapp.entities.WalletEntity;
import com.fintechapp.exception.BadRequestException;
import com.fintechapp.repo.TransactionRepo;
import com.fintechapp.repo.UserRepository;
import com.fintechapp.repo.WalletRepository;
import com.fintechapp.service.WalletService;
import com.fintechapp.utils.GenerateRandomNumbers;

@Service
public class WalletServiceImpl implements WalletService {

	@Autowired
	WalletRepository walletRepo;
	@Autowired
	UserRepository userRepository;
	@Autowired
	TransactionRepo transactionRepo;

	GenerateRandomNumbers accountNumber = new GenerateRandomNumbers();

	@Override
	public ResponseEntity generateAcctNum(String email) {
		String acctnumber = accountNumber.generate();
		// String accountNumber = AccountIdGenerator.generate();
		UserEntity user = new UserEntity();
		WalletEntity valUser = walletRepo.findByUserEmail(email);
		if (valUser == null) {
			WalletEntity valAcct = walletRepo.findByAccountNumber(acctnumber);
			if (valAcct != null) {
				System.out.println("please try again");
			}
			user = userRepository.findByEmail(email);
			WalletEntity wallet = new WalletEntity();
			wallet.setAccountNumber(acctnumber);
			wallet.setAvailableBalance(0);
			wallet.setUserEmail(email);
			// wallet.setBVN(bvn);
			wallet.setStatus("Active");
			wallet.setUser(user);
			walletRepo.save(wallet);

			return ResponseEntity.status(HttpStatus.CREATED).body("Account Generated " + acctnumber);
		}
		return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("User " + email + " Already have account");

	}

	public WalletEntity fundMyAccount(TransactionDTO transactionDTO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionEntity sendMoney(TransactionDTO transactionDTO) throws Exception {

		TransactionEntity saveSender = new TransactionEntity();
		TransactionEntity saveReceiver = new TransactionEntity();
		WalletEntity validateAccount = walletRepo.findByUserEmail(transactionDTO.getEmail());
		if (validateAccount != null) {
			double accountBal = validateAccount.getAvailableBalance();
			if (accountBal < transactionDTO.getAmount()) {
				throw new BadRequestException("400", "INSUFFICIANT BALANCE");
			}
			WalletEntity validateReceiver = walletRepo.findByAccountNumber(transactionDTO.getRecipientAccountNumber());
			if (validateReceiver == null) {
				System.out.println(transactionDTO.getRecipientAccountNumber());
				throw new BadRequestException("400", "Invalid Acount Number");
			}
			// SETTING DEBIT/SENDER INFO...............
			saveSender.setTransType("Debit");
			saveSender.setAmount(transactionDTO.getAmount());
			saveSender.setDebitAmount(transactionDTO.getAmount());
			saveSender.setNarration(transactionDTO.getNarration());
			saveSender.setDate(LocalDate.now());
			saveSender.setUserEmail(transactionDTO.getEmail());
			saveSender.setAccNumber(validateAccount.getAccountNumber());
			validateAccount.setAvailableBalance((long) (accountBal - transactionDTO.getAmount()));
			saveSender.setBalanceAt(accountBal - transactionDTO.getAmount());
			transactionRepo.save(saveSender);

			// SETTING CREDIT/RECEIVER INFO
			saveReceiver.setTransType("Credit");
			saveReceiver.setCreditAmount(transactionDTO.getAmount());
			saveReceiver.setNarration(transactionDTO.getNarration());
			saveReceiver.setDate(LocalDate.now());
			saveReceiver.setUserEmail(validateReceiver.getUserEmail());
			saveReceiver.setAccNumber(transactionDTO.getRecipientAccountNumber());
			validateReceiver
					.setAvailableBalance((long) (validateReceiver.getAvailableBalance() + transactionDTO.getAmount()));
			transactionRepo.save(saveReceiver);
			saveReceiver.setBalanceAt(validateReceiver.getAvailableBalance() + transactionDTO.getAmount());

		}
		return saveSender;
	}

//	public Page<User> pageabledAllUsers(Optional<Integer> page, Optional<String> sortBy) throws Exception {
//		return repos.findAll(PageRequest.of(page.orElse(0), 10, Sort.Direction.ASC, sortBy.orElse("id")));
//	}
	@Override
	public Page<TransactionEntity> transactionHistoryPaged(String accNumber, Optional<Integer> page,
			Optional<String> sortBy) throws Exception {
		return transactionRepo.findByAccNumber(accNumber,
				PageRequest.of(page.orElse(0), 10, Sort.Direction.ASC, sortBy.orElse("date")));
	}

}

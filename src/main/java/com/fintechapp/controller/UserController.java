package com.fintechapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fintechapp.DTO.LoginDTo;
import com.fintechapp.DTO.SignUpDto;
import com.fintechapp.DTO.TransactionDTO;
import com.fintechapp.entities.LoginEntity;
import com.fintechapp.entities.TransactionEntity;
import com.fintechapp.entities.UserEntity;
import com.fintechapp.entities.WalletEntity;
import com.fintechapp.service.UserService;
import com.fintechapp.service.WalletService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	WalletService walletService;

	@PostMapping("/createUser")
	public String createUser(@RequestBody SignUpDto userDto) throws Exception {

		return userService.createUser(userDto);

	}

	@GetMapping("/viewAllUsers")
	public List<UserEntity> getAllusers() throws Exception {
		return userService.viewAllUsers();

	}

	@GetMapping("/confirm-account{token}")
	public String confrimEmail(@RequestParam String token) throws Exception {
		System.out.println("token =" + token);
		return userService.emailVerification(token);

	}

	@GetMapping("/viewLogers")
	public List<LoginEntity> getLoggers() throws Exception {
		return userService.viewLogers();

	}

	@PostMapping("/login")
	public LoginEntity login(@RequestBody LoginDTo loginDTO) throws Exception {
		System.out.println("token =" + loginDTO.getEmail());
		return userService.loggin(loginDTO);

	}

	@PostMapping("/generateAcctNum")
	public ResponseEntity generateAcctNum(@RequestParam String email) throws Exception {
		System.out.println("welcome to controller" + email);
		return walletService.generateAcctNum(email);

	}

	@PostMapping("/sendmoney")
	public TransactionEntity sendMoney(@RequestBody TransactionDTO transDTO) throws Exception {

		return walletService.sendMoney(transDTO);
	}

	@GetMapping(value = "/transHistoryByAccNumber")
	public Page<TransactionEntity> PagedViewAllUsers(@RequestParam String accNumber, @RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy)
			throws Exception {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return walletService.transactionHistoryPaged(accNumber, page, sortBy);
	}
}

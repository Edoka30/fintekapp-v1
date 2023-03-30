package com.fintechapp.serviceImpl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.fintechapp.DTO.LoginDTo;
import com.fintechapp.DTO.SignUpDto;
import com.fintechapp.emailsender.MailService;
import com.fintechapp.entities.LoginEntity;
import com.fintechapp.entities.UserEntity;
import com.fintechapp.exception.BadRequestException;
import com.fintechapp.repo.LoginRepository;
import com.fintechapp.repo.UserRepository;
import com.fintechapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	MailService emailService;
	@Autowired
	LoginRepository loginRepository;

	public String createUser(SignUpDto userDto) throws Exception {
		UserEntity emailVal = userRepository.findByEmail(userDto.getEmail());
		if (emailVal == null) {
			// return emailVal;
			LocalDate date = LocalDate.now();
			LocalDateTime startTime = LocalDateTime.now();
			String confirmationToken = UUID.randomUUID().toString();
			String phoneToken = UUID.randomUUID().toString();
			String userid = UUID.randomUUID().toString();

			UserEntity userEntity = new UserEntity();
			LoginEntity loginEntity = new LoginEntity();

			userEntity.setUserid(userid);
			userEntity.setSurname(userDto.getSurname());
			userEntity.setFirstName(userDto.getFirstName());
			userEntity.setMiddleName(userDto.getMiddleName());
			userEntity.setGender(userDto.getGender());
			userEntity.setDob(userDto.getDob());
			userEntity.setPhone(userDto.getPhone());
			userEntity.setEmail(userDto.getEmail());
			userEntity.setAddress(userDto.getAddress());
			userEntity.setCreatedAt(date.now());

			loginEntity.setPassword(userDto.getPassword());
			loginEntity.setEmailVerificationToken(confirmationToken);
			loginEntity.setTokenTime(startTime);
			loginEntity.setEmail(userDto.getEmail());
			loginEntity.setUserEntity(userEntity);

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(userEntity.getEmail());
			mailMessage.setSubject("Complete Registration!");
			mailMessage.setFrom("thomasedoka30@gmail.com");
			mailMessage.setText("To confirm your account, please click here : "
					+ "http://localhost:8080/confirm-account?token=" + loginEntity.getEmailVerificationToken());

			emailService.sendMail(mailMessage);
			userRepository.save(userEntity);

			loginRepository.save(loginEntity);

			String encodedEmail = userEntity.getEmail();
			return "User Created Successfully, check email " + userEntity.getEmail() + " for confirmation mail";

		}
		// return "could not create user";

		throw new BadRequestException("400", "EMAIL ALREADY EXIST");

	}

	@Override
	public List<UserEntity> viewAllUsers() throws Exception {
		if (userRepository.findAll() == null) {

			throw new BadRequestException("400", "Querry on Empty Entity");
		}
		return userRepository.findAll();
	}

	@Override
	public String emailVerification(String confirmationToken) throws Exception {

		LoginEntity loginEntity = loginRepository.findByEmailVerificationToken(confirmationToken);
		String setToken = loginEntity.getEmailVerificationToken();
		LocalDateTime endTime = LocalDateTime.now();
		loginEntity.getTokenTime();
		Duration duration = Duration.between(loginEntity.getTokenTime(), endTime);
		System.out.println("duration = " + duration);

		if (loginEntity != null && setToken.equalsIgnoreCase(confirmationToken)) {
			System.out.println("duration = " + duration);
			if (duration.toHours() > 1) {
				return "Token has expired";
			}
			loginEntity.setEmailStatus("1");
			loginEntity.setStatus("ACTIVE");
			System.out.println("in the method ..... ");
			return "Email successfully verified";
		}
		return "Unable to verify";
	}

	@Override
	public List<LoginEntity> viewLogers() throws Exception {
		// TODO Auto-generated method stub
		return loginRepository.findAll();
	}

	@Override
	public LoginEntity loggin(LoginDTo loginDTO) throws Exception {
		// TODO Auto-generated method stub

		LoginEntity login = loginRepository.findByEmail(loginDTO.getEmail());
		if (login != null) {
			System.out.println("In the methood");
			System.out.println("  Password---- " + loginDTO.getPassword());
			if (loginDTO.getPassword().equals(login.getPassword()) && login.getStatus().equalsIgnoreCase("Active")) {
				return login;

			}
			throw new BadRequestException("400", " Invalid Password or user not active");

		} else {
			throw new BadRequestException("400", " Email " + loginDTO.getEmail() + " does not exist or not active");
		}

	}

}

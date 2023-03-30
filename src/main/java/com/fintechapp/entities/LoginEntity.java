package com.fintechapp.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "login")
public class LoginEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tid;
	private String password;
	private String email;
	private String phoneVerificationToken;
	private String emailVerificationToken;
	private LocalDateTime tokenTime;
	private String phoneStatus;

	private String emailStatus;
	private String status;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")

	private UserEntity user;

	public LoginEntity(int tid, String userId, String password, String phoneVerificationToken,
			String emailVerificationToken, LocalDateTime tokenTime, String phoneStatus, String emailStatus,
			String status, UserEntity userEntity) {

		this.tid = tid;
		// UserId = userId;
		this.password = password;
		this.phoneVerificationToken = phoneVerificationToken;
		this.emailVerificationToken = emailVerificationToken;
		this.tokenTime = tokenTime;
		this.phoneStatus = phoneStatus;
		this.emailStatus = emailStatus;
		this.status = status;
		this.user = userEntity;
	}

	public LoginEntity() {

	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

//	public String getUserId() {
//		return UserId;
//	}
//	public void setUserId(String userId) {
//		UserId = userId;
//	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneVerificationToken() {
		return phoneVerificationToken;
	}

	public void setPhoneVerificationToken(String phoneVerificationToken) {
		this.phoneVerificationToken = phoneVerificationToken;
	}

	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}

	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}

	public LocalDateTime getTokenTime() {
		return tokenTime;
	}

	public void setTokenTime(LocalDateTime startTime) {
		this.tokenTime = startTime;
	}

	public String getPhoneStatus() {
		return phoneStatus;
	}

	public void setPhoneStatus(String phoneStatus) {
		this.phoneStatus = phoneStatus;
	}

	public String getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserEntity getUserEntity() {
		return user;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.user = userEntity;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

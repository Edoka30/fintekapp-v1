package com.fintechapp.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wallet")
public class WalletEntity {
	@Id
	@GeneratedValue
	private int accid;
	private String userEmail;
	private String accountNumber;
	private String BVN;
	private long availableBalance;
	private String status;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")

	private UserEntity user;
	public WalletEntity() {
		
	}
	public WalletEntity(int accid, String userEmail, String accountNumber, String bVN, long availableBalance,
			String status, UserEntity user) {
		
		this.accid = accid;
		this.userEmail = userEmail;
		this.accountNumber = accountNumber;
		BVN = bVN;
		this.availableBalance = availableBalance;
		this.status = status;
		this.user = user;
	}
	public int getAccid() {
		return accid;
	}
	public void setAccid(int accid) {
		this.accid = accid;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBVN() {
		return BVN;
	}
	public void setBVN(String bVN) {
		BVN = bVN;
	}
	public long getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(long availableBalance) {
		this.availableBalance = availableBalance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	
	

}
